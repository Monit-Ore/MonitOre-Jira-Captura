package monitore.com.dashboard.itens;

public class FaixaResolucao {
    private String faixa;
    private int prioridadeAltaOuCritica;
    private int demaisPrioridades;
    private int total;

    public String getFaixa() {
        return faixa;
    }

    public void setFaixa(String faixa) {
        this.faixa = faixa;
    }

    public int getPrioridadeAltaOuCritica() {
        return prioridadeAltaOuCritica;
    }

    public void setPrioridadeAltaOuCritica(int prioridadeAltaOuCritica) {
        this.prioridadeAltaOuCritica = prioridadeAltaOuCritica;
    }

    public int getDemaisPrioridades() {
        return demaisPrioridades;
    }

    public void setDemaisPrioridades(int demaisPrioridades) {
        this.demaisPrioridades = demaisPrioridades;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
