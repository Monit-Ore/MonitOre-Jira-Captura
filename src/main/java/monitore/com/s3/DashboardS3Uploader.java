package monitore.com.s3;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/** Envia uma captura JSON do dashboard para o bucket S3 Gold. */
public class DashboardS3Uploader implements AutoCloseable {
    private static final DateTimeFormatter FORMATO_DATA_HORA =
            DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmmss'Z'").withZone(ZoneOffset.UTC);

    private final S3Client s3Client;
    private final String bucket;
    private final String prefixo;

    public DashboardS3Uploader(
            S3Client s3Client,
            String bucket,
            String prefixo
    ) {
        this.s3Client = s3Client;
        this.bucket = bucket;
        this.prefixo = limparPrefixo(prefixo);
    }

    /**
     * Faz upload do JSON como um novo objeto e retorna a chave criada no bucket.
     * A chave inclui a data da captura para preservar o histórico entre execuções.
     */
    public String enviarJson(String json) {
        Instant agora = Instant.now();
        String pastaData = DateTimeFormatter.ofPattern("yyyy/MM")
                .withZone(ZoneOffset.UTC)
                .format(agora);
        String nomeArquivo = "dashboard-" + FORMATO_DATA_HORA.format(agora)
                + "-" + UUID.randomUUID() + ".json";
        String chave = prefixo + "/" + pastaData + "/" + nomeArquivo;

        PutObjectRequest requisicao = PutObjectRequest.builder()
                .bucket(bucket)
                .key(chave)
                .contentType("application/json; charset=utf-8")
                .build();

        s3Client.putObject(
                requisicao,
                RequestBody.fromString(json, StandardCharsets.UTF_8)
        );

        return chave;
    }

    private String limparPrefixo(String valor) {
        if (valor == null || valor.isBlank()) {
            return "dashboard-suporte";
        }
        return valor.replaceAll("^/+|/+$", "");
    }

    @Override
    public void close() {
        s3Client.close();
    }
}
