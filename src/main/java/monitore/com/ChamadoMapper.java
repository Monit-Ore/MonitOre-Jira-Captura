package monitore.com;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import java.util.ArrayList;
import java.util.List;

public class ChamadoMapper {
    private static final DateTimeFormatter FORMATO_DATA_JIRA =
            new DateTimeFormatterBuilder()
                    .appendPattern("uuuu-MM-dd'T'HH:mm:ss")
                    .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
                    .appendPattern("Z")
                    .toFormatter();

    private Instant converterData(JsonNode valorJson) {
        if (valorJson.isMissingNode() || valorJson.isNull()
                || valorJson.asText().isBlank()) {
            return null;
        }

        return OffsetDateTime
                .parse(valorJson.asText(), FORMATO_DATA_JIRA)
                .toInstant();
    }

    public List<Chamado> converterParaChamados(String json) throws JsonProcessingException {
        List<Chamado> chamados = new ArrayList<>();

        ObjectMapper leitorJson = new ObjectMapper();
        JsonNode raiz = leitorJson.readTree(json);

        JsonNode chamadosJson = raiz.path("issues");

        for (JsonNode chamado : chamadosJson) {
            JsonNode campos = chamado.path("fields");

            String chave = chamado.path("key").asText();
            String resumo = campos.path("summary").asText();
            String status = campos.path("status").path("name").asText();
            String prioridade = campos.path("priority").path("name").asText();
            String responsavel = campos.path("assignee").path("displayName")
                    .asText("Sem responsável");
            Instant criadoEm = converterData(campos.path("created"));
            Instant atualizadoEm = converterData(campos.path("updated"));
            Instant resolvidoEm = converterData(campos.path("resolutiondate"));

            Chamado chamado1 = new Chamado(
                    chave, resumo, status, prioridade, responsavel,
                    criadoEm, atualizadoEm, resolvidoEm
            );
            chamados.add(chamado1);
        }

        return chamados;
    }
}
