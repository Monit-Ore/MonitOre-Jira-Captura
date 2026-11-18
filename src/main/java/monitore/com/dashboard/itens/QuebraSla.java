package monitore.com.dashboard.itens;

public class QuebraSla {
    private String categoria;
    private int resolvidosNoPrazo;
    private int totalElegiveis;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
}
