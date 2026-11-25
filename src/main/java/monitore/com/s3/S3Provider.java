package monitore.com.s3;

import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

/** Cria o cliente AWS S3 usando a cadeia padrão de credenciais do SDK. */
public class S3Provider {
    private final AwsCredentialsProvider credentialsProvider;

    public S3Provider(String nomePerfil) {

        if (nomePerfil == null || nomePerfil.isBlank()) {
            this.credentialsProvider = DefaultCredentialsProvider.create();
        } else {
            this.credentialsProvider = ProfileCredentialsProvider.create(nomePerfil);
        }
    }

    public S3Client getS3Client(Region region) {
        return S3Client.builder()
                .region(region)
                .credentialsProvider(credentialsProvider)
                .build();
    }
}
