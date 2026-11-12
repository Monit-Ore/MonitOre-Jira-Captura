package monitore.com;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JiraClient {

    public HttpResponse<String> buscarChamadosJson(String baseUrl, String autenticacao){
        String corpoJson = """
                {
                  "jql": "project = KAN ORDER BY created DESC",
                  "fields": ["summary", "status", "priority", "assignee", "created"],
                  "maxResults": 10
                }
                """;

        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/rest/api/3/search/jql"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + autenticacao)
                .POST(HttpRequest.BodyPublishers.ofString(corpoJson))
                .build();

        HttpClient cliente = HttpClient.newHttpClient();
        HttpResponse<String> resposta = null;
        try {
            resposta = cliente.send(
                    requisicao,
                    HttpResponse.BodyHandlers.ofString()
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return resposta;
    }

}
