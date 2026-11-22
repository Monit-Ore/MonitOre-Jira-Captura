package monitore.com;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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

public class Main {
    public static void main(String[] args) throws Exception {
        String baseUrl = System.getenv("JIRA_BASE_URL");
        String email = System.getenv("JIRA_EMAIL");
        String token = System.getenv("JIRA_API_TOKEN");
        String bucketGold = System.getenv("S3_BUCKET_NAME");
        String regiaoAws = System.getenv("AWS_REGION");

        List<String> configuracoesAusentes = new ArrayList<>();
        if (estaVazio(baseUrl)) configuracoesAusentes.add("JIRA_BASE_URL");
        if (estaVazio(email)) configuracoesAusentes.add("JIRA_EMAIL");
        if (estaVazio(token)) configuracoesAusentes.add("JIRA_API_TOKEN");
        if (estaVazio(bucketGold)) configuracoesAusentes.add("S3_BUCKET_NAME");
        if (estaVazio(regiaoAws)) configuracoesAusentes.add("AWS_REGION");

        if (!configuracoesAusentes.isEmpty()) {
            System.out.println("Configure estas variáveis na configuração de execução do IntelliJ: "
                    + String.join(", ", configuracoesAusentes));
            return;
        }

        String credenciais = email + ":" + token;
        String autenticacao = Base64.getEncoder()
                .encodeToString(credenciais.getBytes(StandardCharsets.UTF_8));

        JiraClient jiraClient = new JiraClient();
        ChamadoMapper chamadoMapper = new ChamadoMapper();
        ChamadoService chamadoService =
                new ChamadoService(jiraClient, chamadoMapper);

        List<Chamado> chamados =
                chamadoService.buscarTodos(baseUrl, autenticacao);

        DashboardSuporteBuilder builder = new DashboardSuporteBuilder(chamados);
        DashboardSuporte dashboard = builder.construir();

        DashboardJsonWriter jsonWriter = new DashboardJsonWriter();
        String jsonFinal = jsonWriter.escrever(dashboard);

        System.out.println(jsonFinal);

        S3Provider s3Provider = new S3Provider();
        S3Client s3Client = s3Provider.getS3Client(Region.of(regiaoAws));
        try (DashboardS3Uploader uploader = new DashboardS3Uploader(
                s3Client,
                bucketGold,
                System.getenv("S3_PREFIX")
        )) {
            String chave = uploader.enviarJson(jsonFinal);
            System.out.println("JSON enviado para s3://" + bucketGold + "/" + chave);
        }
    }

    private static boolean estaVazio(String valor) {
        return valor == null || valor.isBlank();
    }
}
