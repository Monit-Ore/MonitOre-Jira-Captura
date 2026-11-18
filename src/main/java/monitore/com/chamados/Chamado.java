package monitore.com.chamados;

import java.time.Instant;
import java.time.LocalDate;

public class Chamado {
    private String chave;
    private String resumo;
    private String status;
    private String prioridade;
    private String responsavel;
    private Instant criadoEm;
    private Instant atualizadoEm;
    private Instant resolvidoEm;
    private LocalDate dataEntrega;
    private Long prazoSlaEm;
    private Boolean slaVencido;
    private String projeto;
    private String tipo;

    public Chamado(String chave, String resumo, String status, String prioridade, String responsavel, Instant criadoEm, Instant atualizadoEm, Instant resolvidoEm, LocalDate dataEntrega, Long prazoSlaEm, Boolean slaVencido, String projeto, String tipo) {
        this.chave = chave;
        this.resumo = resumo;
        this.status = status;
        this.prioridade = prioridade;
        this.responsavel = responsavel;
        this.criadoEm = criadoEm;
        this.atualizadoEm = atualizadoEm;
        this.resolvidoEm = resolvidoEm;
        this.dataEntrega = dataEntrega;
        this.prazoSlaEm = prazoSlaEm;
        this.slaVencido = slaVencido;
        this.projeto = projeto;
        this.tipo = tipo;
    }

    public String getProjeto() {
        return projeto;
    }

    public String getTipo() {
        return tipo;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public Long getPrazoSlaEm() {
        return prazoSlaEm;
    }

    public Boolean getSlaVencido() {
        return slaVencido;
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
                ", dataEntrega=" + dataEntrega +
                ", prazoSlaEm=" + prazoSlaEm +
                ", slaVencido=" + slaVencido +
                ", projeto='" + projeto + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}
