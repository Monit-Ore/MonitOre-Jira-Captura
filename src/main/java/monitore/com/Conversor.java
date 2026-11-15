package monitore.com;

import com.fasterxml.jackson.databind.JsonNode;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Conversor {
    private static final DateTimeFormatter FORMATO_DATA_JIRA =
            new DateTimeFormatterBuilder()
                    .appendPattern("uuuu-MM-dd'T'HH:mm:ss")
                    .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
                    .appendPattern("Z")
                    .toFormatter();
    private static ZoneId fuso = ZoneId.of("America/Sao_Paulo");


    public Instant converterData(JsonNode valorJson) {
        if (valorJson.isMissingNode() || valorJson.isNull()
                || valorJson.asText().isBlank()) {
            return null;
        }

        return OffsetDateTime
                .parse(valorJson.asText(), FORMATO_DATA_JIRA)
                .toInstant();
    }




    public Instant getPrazoFinalConvertido(LocalDate dataEntrega){
        Instant prazoFinal = dataEntrega
                .atTime(23, 59, 59)
                .atZone(fuso)
                .toInstant();

        return prazoFinal;
    }

    public long getSegundosRestantes(Instant prazoFinal){
        long segundosRestantes =
                Duration.between(Instant.now(), prazoFinal).getSeconds();
        return segundosRestantes;
    }

    public Boolean getSlaVencido(long segundosRestantes){
        Boolean slaVencido = segundosRestantes < 0;
        return slaVencido;
    }

}
