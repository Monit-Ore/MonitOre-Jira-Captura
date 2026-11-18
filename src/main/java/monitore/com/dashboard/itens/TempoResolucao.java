package monitore.com.dashboard.itens;

import java.util.List;

public class TempoResolucao {
    private String unidade;
    private List<FaixaResolucao> faixas;

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public List<FaixaResolucao> getFaixas() {
        return faixas;
    }

    public void setFaixas(List<FaixaResolucao> faixas) {
        this.faixas = faixas;
    }
}
