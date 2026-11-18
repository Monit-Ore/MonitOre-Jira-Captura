package monitore.com.dashboard.itens;

public class ChamadoAtencao {
    private String chave;
    private String resumo;
    private String projeto;
    private String tipo;
    private String prioridade;
    private String status;
    private String responsavel;
    private boolean semResponsavel;
    private long prazoSlaEm;
    private boolean slaVencido;
    private long tempoDesdeUltimaAtualizacaoHoras;
    private String endereco;

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getProjeto() {
        return projeto;
    }

    public void setProjeto(String projeto) {
        this.projeto = projeto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public boolean isSemResponsavel() {
        return semResponsavel;
    }

    public void setSemResponsavel(boolean semResponsavel) {
        this.semResponsavel = semResponsavel;
    }

    public long getPrazoSlaEm() {
        return prazoSlaEm;
    }

    public void setPrazoSlaEm(long prazoSlaEm) {
        this.prazoSlaEm = prazoSlaEm;
    }

    public boolean isSlaVencido() {
        return slaVencido;
    }

    public void setSlaVencido(boolean slaVencido) {
        this.slaVencido = slaVencido;
    }

    public long getTempoDesdeUltimaAtualizacaoHoras() {
        return tempoDesdeUltimaAtualizacaoHoras;
    }

    public void setTempoDesdeUltimaAtualizacaoHoras(long tempoDesdeUltimaAtualizacaoHoras) {
        this.tempoDesdeUltimaAtualizacaoHoras = tempoDesdeUltimaAtualizacaoHoras;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
