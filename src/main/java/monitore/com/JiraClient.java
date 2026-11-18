package monitore.com;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JiraClient {

    public HttpResponse<String> buscarPagina(
            String baseUrl,
            String autenticacao,
            String nextPageToken
    ) throws IOException, InterruptedException {
        ObjectMapper json = new ObjectMapper();
        ObjectNode corpo = json.createObjectNode();

        corpo.put("jql", "project = KAN ORDER BY created DESC");
        corpo.put("maxResults", 10);

        ArrayNode fields = corpo.putArray("fields");
        fields.add("summary");
        fields.add("status");
        fields.add("priority");
        fields.add("assignee");
        fields.add("created");
        fields.add("updated");
        fields.add("resolutiondate");
        fields.add("duedate");

        if (nextPageToken != null) {
            corpo.put("nextPageToken", nextPageToken);
        }

        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/rest/api/3/search/jql"))
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic " + autenticacao)
                .POST(HttpRequest.BodyPublishers.ofString(
                        json.writeValueAsString(corpo)
                ))
                .build();

        return HttpClient.newHttpClient().send(
                requisicao,
                HttpResponse.BodyHandlers.ofString()
        );
    }

}
