package monitore.com.dashboard.itens;

public class IndicadoresSuporte {
    private int chamadosAbertos;
    private int variacaoChamadosAbertos;
    private int chamadosParadosSeteDias;
    private int chamadosCriticos;
    private int chamadosCriticosSemResponsavel;
    private int chamadosCriticosVencidos;
    private int slaEmRisco;
    private int slaVencemProximaHora;
    private int slaVencido;
    private long slaVencidoMaisAntigoHoras;
    private CumprimentoSla cumprimentoSla;

    public int getChamadosAbertos() {
        return chamadosAbertos;
    }

    public void setChamadosAbertos(int chamadosAbertos) {
        this.chamadosAbertos = chamadosAbertos;
    }

    public int getVariacaoChamadosAbertos() {
        return variacaoChamadosAbertos;
    }

    public void setVariacaoChamadosAbertos(int variacaoChamadosAbertos) {
        this.variacaoChamadosAbertos = variacaoChamadosAbertos;
    }

    public int getChamadosParadosSeteDias() {
        return chamadosParadosSeteDias;
    }

    public void setChamadosParadosSeteDias(int chamadosParadosSeteDias) {
        this.chamadosParadosSeteDias = chamadosParadosSeteDias;
    }

    public int getChamadosCriticos() {
        return chamadosCriticos;
    }

    public void setChamadosCriticos(int chamadosCriticos) {
        this.chamadosCriticos = chamadosCriticos;
    }

    public int getChamadosCriticosSemResponsavel() {
        return chamadosCriticosSemResponsavel;
    }

    public void setChamadosCriticosSemResponsavel(int chamadosCriticosSemResponsavel) {
        this.chamadosCriticosSemResponsavel = chamadosCriticosSemResponsavel;
    }

    public int getChamadosCriticosVencidos() {
        return chamadosCriticosVencidos;
    }

    public void setChamadosCriticosVencidos(int chamadosCriticosVencidos) {
        this.chamadosCriticosVencidos = chamadosCriticosVencidos;
    }

    public int getSlaEmRisco() {
        return slaEmRisco;
    }

    public void setSlaEmRisco(int slaEmRisco) {
        this.slaEmRisco = slaEmRisco;
    }

    public int getSlaVencemProximaHora() {
        return slaVencemProximaHora;
    }

    public void setSlaVencemProximaHora(int slaVencemProximaHora) {
        this.slaVencemProximaHora = slaVencemProximaHora;
    }

    public int getSlaVencido() {
        return slaVencido;
    }

    public void setSlaVencido(int slaVencido) {
        this.slaVencido = slaVencido;
    }

    public long getSlaVencidoMaisAntigoHoras() {
        return slaVencidoMaisAntigoHoras;
    }

    public void setSlaVencidoMaisAntigoHoras(long slaVencidoMaisAntigoHoras) {
        this.slaVencidoMaisAntigoHoras = slaVencidoMaisAntigoHoras;
    }

    public CumprimentoSla getCumprimentoSla() {
        return cumprimentoSla;
    }

    public void setCumprimentoSla(CumprimentoSla cumprimentoSla) {
        this.cumprimentoSla = cumprimentoSla;
    }
}
