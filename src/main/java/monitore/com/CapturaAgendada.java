package monitore.com;

import io.github.cdimascio.dotenv.Dotenv;
import monitore.com.JiraClient;
import monitore.com.chamados.Chamado;
import monitore.com.chamados.ChamadoMapper;
import monitore.com.chamados.ChamadoService;
import monitore.com.dashboard.DashboardJsonWriter;
import monitore.com.dashboard.DashboardSuporte;
import monitore.com.dashboard.DashboardSuporteBuilder;
import monitore.com.s3.DashboardS3Uploader;
import monitore.com.s3.S3Provider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.time.LocalDateTime;

public class CapturaAgendada {
    public void Rodar20min(String baseUrl, String autenticacao, String regiaoAws, String bucketGold) {
        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();

        System.out.println("[" + LocalDateTime.now() + "] Agendamento ativo: primeira captura imediata; próximas a cada 20 minutos.");
        scheduler.scheduleAtFixedRate(() -> {
            long inicio = System.nanoTime();
            System.out.println("[" + LocalDateTime.now() + "] Iniciando nova captura do Jira.");
            try {
                capturarNovoDado(baseUrl,autenticacao,regiaoAws,bucketGold);
                long duracaoMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - inicio);
                System.out.println("[" + LocalDateTime.now() + "] Captura concluída com sucesso em " + duracaoMs + " ms.");
            } catch (Exception e) {
                long duracaoMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - inicio);
                System.out.println("[" + LocalDateTime.now() + "] Falha na captura após " + duracaoMs + " ms: " + e.getMessage());
                e.printStackTrace(System.out);
            }
        }, 0, 20, TimeUnit.MINUTES);
    }

    private static void capturarNovoDado(String baseUrl, String autenticacao, String regiaoAws, String bucketGold) throws IOException, InterruptedException {
        Dotenv env = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        JiraClient jiraClient = new JiraClient();
        ChamadoMapper chamadoMapper = new ChamadoMapper();
        ChamadoService chamadoService =
                new ChamadoService(jiraClient, chamadoMapper);

        List<Chamado> chamados =
                chamadoService.buscarTodos(baseUrl, autenticacao);
        System.out.println("[" + LocalDateTime.now() + "] Consulta ao Jira concluída. Chamados recebidos: " + chamados.size() + ".");

        DashboardSuporteBuilder builder = new DashboardSuporteBuilder(chamados);
        DashboardSuporte dashboard = builder.construir();

        DashboardJsonWriter jsonWriter = new DashboardJsonWriter();
        String jsonFinal = jsonWriter.escrever(dashboard);

        S3Provider s3Provider = new S3Provider(env.get("AWS_PROFILE"));
        S3Client s3Client = s3Provider.getS3Client(Region.of(regiaoAws));
        try (DashboardS3Uploader uploader = new DashboardS3Uploader(
                s3Client,
                bucketGold,
                env.get("S3_PREFIX")
        )) {
            String chave = uploader.enviarJson(jsonFinal);
            System.out.println("[" + LocalDateTime.now() + "] Arquivo enviado ao S3: s3://" + bucketGold + "/" + chave);
        }
    }
}
