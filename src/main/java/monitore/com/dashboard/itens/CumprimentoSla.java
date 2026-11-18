package monitore.com.dashboard.itens;

public class CumprimentoSla {
    private int resolvidosNoPrazo;
    private int totalElegiveis;
    private int percentualAnterior;
    private int metaPercentual;

    public int getResolvidosNoPrazo() {
        return resolvidosNoPrazo;
    }

    public void setResolvidosNoPrazo(int resolvidosNoPrazo) {
        this.resolvidosNoPrazo = resolvidosNoPrazo;
    }

    public int getTotalElegiveis() {
        return totalElegiveis;
    }

    public void setTotalElegiveis(int totalElegiveis) {
        this.totalElegiveis = totalElegiveis;
    }

    public int getPercentualAnterior() {
        return percentualAnterior;
    }

    public void setPercentualAnterior(int percentualAnterior) {
        this.percentualAnterior = percentualAnterior;
    }

    public int getMetaPercentual() {
        return metaPercentual;
    }

    public void setMetaPercentual(int metaPercentual) {
        this.metaPercentual = metaPercentual;
    }
}
