package monitore.com;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        HttpResponse<String> resposta = jiraClient.buscarChamadosJson(baseUrl, autenticacao);
        System.out.println("Status HTTP: " + resposta.statusCode());


        ChamadoMapper chamadoMapper = new ChamadoMapper();
        List<Chamado> chamados = chamadoMapper.converterParaChamados(resposta.body());

        for (Chamado chamado : chamados) {
            System.out.println(chamado);
        }
    }
}
