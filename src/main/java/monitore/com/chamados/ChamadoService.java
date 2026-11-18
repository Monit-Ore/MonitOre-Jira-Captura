package monitore.com.chamados;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.JsonNode;
import monitore.com.JiraClient;

import java.net.http.HttpResponse;
    public class ChamadoService {
        private final JiraClient jiraClient;
        private final ChamadoMapper chamadoMapper;
        private final ObjectMapper leitorJson = new ObjectMapper();

        public ChamadoService(JiraClient jiraClient, ChamadoMapper chamadoMapper) {
            this.jiraClient = jiraClient;
            this.chamadoMapper = chamadoMapper;
        }

        public List<Chamado> buscarTodos(String baseUrl, String autenticacao)
                throws IOException, InterruptedException {
            List<Chamado> todosOsChamados = new ArrayList<>();
            String nextPageToken = null;

            while (true) {
                HttpResponse<String> resposta =
                        jiraClient.buscarPagina(baseUrl, autenticacao, nextPageToken);

                if (resposta.statusCode() != 200) {
                    throw new IllegalStateException(
                            "O Jira respondeu com HTTP " + resposta.statusCode()
                    );
                }

                JsonNode pagina = leitorJson.readTree(resposta.body());
                todosOsChamados.addAll(
                        chamadoMapper.converterParaChamados(resposta.body())
                );

                if (pagina.path("isLast").asBoolean()) {
                    break;
                }

                JsonNode tokenJson = pagina.path("nextPageToken");
                if (tokenJson.isMissingNode()
                        || tokenJson.isNull()
                        || tokenJson.asText().isBlank()) {
                    throw new IllegalStateException(
                            "A resposta indicou outra página, mas não trouxe nextPageToken."
                    );
                }

                nextPageToken = tokenJson.asText();
            }

            return todosOsChamados;
        }
    }

