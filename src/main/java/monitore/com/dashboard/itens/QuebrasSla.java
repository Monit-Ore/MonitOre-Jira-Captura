package monitore.com.dashboard.itens;

import java.util.List;

public class QuebrasSla {
    private List<QuebraSla> prioridade;
    private List<QuebraSla> tipo;

    public List<QuebraSla> getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(List<QuebraSla> prioridade) {
        this.prioridade = prioridade;
    }

    public List<QuebraSla> getTipo() {
        return tipo;
    }

    public void setTipo(List<QuebraSla> tipo) {
        this.tipo = tipo;
    }
}
