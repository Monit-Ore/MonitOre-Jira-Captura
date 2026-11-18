package monitore.com.dashboard.itens;

import java.time.Instant;

public class MetadadosDashboard {
    private String nomeRelatorio;
    private String fonte;
    private Instant extraidoEm;
    private String periodoConsultado;
    private String periodoComparacao;
    private boolean dadosDemonstrativos;
    private String observacao;

    public String getNomeRelatorio() {
        return nomeRelatorio;
    }

    public void setNomeRelatorio(String nomeRelatorio) {
        this.nomeRelatorio = nomeRelatorio;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public Instant getExtraidoEm() {
        return extraidoEm;
    }

    public void setExtraidoEm(Instant extraidoEm) {
        this.extraidoEm = extraidoEm;
    }

    public String getPeriodoConsultado() {
        return periodoConsultado;
    }

    public void setPeriodoConsultado(String periodoConsultado) {
        this.periodoConsultado = periodoConsultado;
    }

    public String getPeriodoComparacao() {
        return periodoComparacao;
    }

    public void setPeriodoComparacao(String periodoComparacao) {
        this.periodoComparacao = periodoComparacao;
    }

    public boolean isDadosDemonstrativos() {
        return dadosDemonstrativos;
    }

    public void setDadosDemonstrativos(boolean dadosDemonstrativos) {
        this.dadosDemonstrativos = dadosDemonstrativos;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
