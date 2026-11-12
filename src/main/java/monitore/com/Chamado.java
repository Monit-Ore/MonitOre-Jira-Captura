package monitore.com;

import java.time.Instant;

public class Chamado {
    private String chave;
    private String resumo;
    private String status;
    private String prioridade;
    private String responsavel;
    private Instant criadoEm;
    private Instant atualizadoEm;
    private Instant resolvidoEm;


    public Chamado(
            String chave,
            String resumo,
            String status,
            String prioridade,
            String responsavel,
            Instant criadoEm,
            Instant atualizadoEm,
            Instant resolvidoEm
    ) {
        this.chave = chave;
        this.resumo = resumo;
        this.status = status;
        this.prioridade = prioridade;
        this.responsavel = responsavel;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.resolvidoEm = resolvidoEm;
    }

    public String getChave() {
        return chave;
    }

    public String getResumo() {
        return resumo;
    }

    public String getStatus() {
        return status;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public Instant getAtualizadoEm() {
        return atualizadoEm;
    }

    public Instant getResolvidoEm() {
        return resolvidoEm;
    }

    @Override
    public String toString() {
        return "Chamado{" +
                "chave='" + chave + '\'' +
                ", resumo='" + resumo + '\'' +
                ", status='" + status + '\'' +
                ", prioridade='" + prioridade + '\'' +
                ", responsavel='" + responsavel + '\'' +
                ", criadoEm=" + criadoEm +
                ", atualizadoEm=" + atualizadoEm +
                ", resolvidoEm=" + resolvidoEm +
                '}';
    }
}
