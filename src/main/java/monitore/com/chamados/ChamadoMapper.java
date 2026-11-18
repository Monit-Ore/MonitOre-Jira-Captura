package monitore.com.chamados;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import monitore.com.Conversor;

import java.time.Instant;
import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class ChamadoMapper {

    public List<Chamado> converterParaChamados(String json) throws JsonProcessingException {
        List<Chamado> chamados = new ArrayList<>();
        ObjectMapper leitorJson = new ObjectMapper();
        JsonNode raiz = leitorJson.readTree(json);
        Conversor conversor = new Conversor();

        JsonNode chamadosJson = raiz.path("issues");
        System.out.println(chamadosJson);
        for (JsonNode chamado : chamadosJson) {
            JsonNode campos = chamado.path("fields");

            String chave = chamado.path("key").asText();
            String resumo = campos.path("summary").asText();
            String status = campos.path("status").path("name").asText();
            String prioridade = campos.path("priority").path("name").asText();
            String responsavel = campos.path("assignee").path("displayName")
                    .asText("Sem responsável");
            Instant criadoEm = conversor.converterData(campos.path("created"));
            Instant atualizadoEm = conversor.converterData(campos.path("updated"));
            Instant resolvidoEm = conversor.converterData(campos.path("resolutiondate"));
            JsonNode dataEntregaJson = campos.path("duedate");
            LocalDate dataEntrega = null;
            Long prazoSlaEm = null;
            Boolean slaVencido = null;

            if (!dataEntregaJson.isMissingNode()
                    && !dataEntregaJson.isNull()
                    && !dataEntregaJson.asText().isBlank()) {
                dataEntrega = LocalDate.parse(dataEntregaJson.asText());
                Instant prazoFinal = conversor.getPrazoFinalConvertido(dataEntrega);
                prazoSlaEm = conversor.getSegundosRestantes(prazoFinal);
                slaVencido = conversor.getSlaVencido(prazoSlaEm);
            }

            Chamado chamado1 = new Chamado(
                    chave, resumo, status, prioridade, responsavel,
                    criadoEm, atualizadoEm, resolvidoEm,
                    dataEntrega, prazoSlaEm, slaVencido
            );
            chamados.add(chamado1);
        }

        return chamados;
    }
}
