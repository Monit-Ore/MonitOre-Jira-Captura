package monitore.com;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Iniciando serviço de captura Jira → S3.");

        Dotenv env = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        String baseUrl = env.get("JIRA_BASE_URL");
        String email = env.get("JIRA_EMAIL");
        String token = env.get("JIRA_API_TOKEN");
        String bucketGold = env.get("S3_BUCKET_NAME");
        String regiaoAws = env.get("AWS_REGION");

        List<String> configuracoesAusentes = new ArrayList<>();
        if (estaVazio(baseUrl)) configuracoesAusentes.add("JIRA_BASE_URL");
        if (estaVazio(email)) configuracoesAusentes.add("JIRA_EMAIL");
        if (estaVazio(token)) configuracoesAusentes.add("JIRA_API_TOKEN");
        if (estaVazio(bucketGold)) configuracoesAusentes.add("S3_BUCKET_NAME");
        if (estaVazio(regiaoAws)) configuracoesAusentes.add("AWS_REGION");

        if (!configuracoesAusentes.isEmpty()) {
            System.out.println("Configuração incompleta. Variáveis ausentes: "
                    + String.join(", ", configuracoesAusentes));
            return;
        }

        String credenciais = email + ":" + token;
        String autenticacao = Base64.getEncoder()
                .encodeToString(credenciais.getBytes(StandardCharsets.UTF_8));

        CapturaAgendada capturaAgendada = new CapturaAgendada();
        capturaAgendada.Rodar20min(baseUrl,autenticacao,regiaoAws,bucketGold);
    }

    private static boolean estaVazio(String valor) {
        return valor == null || valor.isBlank();
    }
}
