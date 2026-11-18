package monitore.com.dashboard.itens;

import java.time.LocalDate;

public class PontoTemporal {
    private LocalDate data;
    private int chamadosRecebidos;
    private int chamadosResolvidos;

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getChamadosRecebidos() {
        return chamadosRecebidos;
    }

    public void setChamadosRecebidos(int chamadosRecebidos) {
        this.chamadosRecebidos = chamadosRecebidos;
    }

    public int getChamadosResolvidos() {
        return chamadosResolvidos;
    }

    public void setChamadosResolvidos(int chamadosResolvidos) {
        this.chamadosResolvidos = chamadosResolvidos;
    }
}
