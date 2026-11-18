package monitore.com.dashboard.itens;

public class CapacidadeEquipe {
    private String responsavel;
    private int chamadosAtivos;
    private int chamadosPrioridadeAltaOuCritica;
    private int chamadosPrioridadeCritica;
    private int chamadosPrioridadeAlta;
    private int chamadosDemaisPrioridades;

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }
    public int getChamadosAtivos() { return chamadosAtivos; }
    public void setChamadosAtivos(int chamadosAtivos) { this.chamadosAtivos = chamadosAtivos; }
    public int getChamadosPrioridadeAltaOuCritica() { return chamadosPrioridadeAltaOuCritica; }
    public void setChamadosPrioridadeAltaOuCritica(int chamadosPrioridadeAltaOuCritica) { this.chamadosPrioridadeAltaOuCritica = chamadosPrioridadeAltaOuCritica; }
    public int getChamadosPrioridadeCritica() { return chamadosPrioridadeCritica; }
    public void setChamadosPrioridadeCritica(int chamadosPrioridadeCritica) { this.chamadosPrioridadeCritica = chamadosPrioridadeCritica; }
    public int getChamadosPrioridadeAlta() { return chamadosPrioridadeAlta; }
    public void setChamadosPrioridadeAlta(int chamadosPrioridadeAlta) { this.chamadosPrioridadeAlta = chamadosPrioridadeAlta; }
    public int getChamadosDemaisPrioridades() { return chamadosDemaisPrioridades; }
    public void setChamadosDemaisPrioridades(int chamadosDemaisPrioridades) { this.chamadosDemaisPrioridades = chamadosDemaisPrioridades; }
}
