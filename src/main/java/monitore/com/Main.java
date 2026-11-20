package monitore.com;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import monitore.com.chamados.Chamado;
import monitore.com.chamados.ChamadoMapper;
import monitore.com.chamados.ChamadoService;
import monitore.com.dashboard.DashboardJsonWriter;
import monitore.com.dashboard.DashboardSuporte;
import monitore.com.dashboard.DashboardSuporteBuilder;

public class Main {
    public static void main(String[] args) throws Exception {
        String baseUrl = System.getenv("JIRA_BASE_URL");
        String email = System.getenv("JIRA_EMAIL");
        String token = System.getenv("JIRA_API_TOKEN");

        if (baseUrl == null || email == null || token == null) {
            System.out.println("Configure as três variáveis de ambiente antes de executar.");
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
    }
}
