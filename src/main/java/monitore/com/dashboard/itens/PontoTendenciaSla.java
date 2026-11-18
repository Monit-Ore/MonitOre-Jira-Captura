package monitore.com.dashboard.itens;

import java.time.LocalDate;

public class PontoTendenciaSla {
    private LocalDate data;
    private int resolvidosNoPrazo;
    private int totalElegiveis;
    private int percentual;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

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

    public int getPercentual() {
        return percentual;
    }

    public void setPercentual(int percentual) {
        this.percentual = percentual;
    }
}
