package monitore.com.dashboard;

import monitore.com.dashboard.itens.*;

import java.util.List;

public class DashboardSuporte {
    private MetadadosDashboard metadados;
    private IndicadoresSuporte indicadores;
    private List<PontoTemporal> serieTemporal;
    private List<PontoTendenciaSla> tendenciaSla;
    private QuebrasSla quebrasSla;
    private List<CapacidadeEquipe> capacidadeDaEquipe;
    private List<ChamadoAtencao> chamadosQuePrecisamDeAtencao;

    // Construtor, getters e setters


    public DashboardSuporte(MetadadosDashboard metadados, IndicadoresSuporte indicadores, List<PontoTemporal> serieTemporal, List<PontoTendenciaSla> tendenciaSla, QuebrasSla quebrasSla, List<CapacidadeEquipe> capacidadeDaEquipe, List<ChamadoAtencao> chamadosQuePrecisamDeAtencao) {
        this.metadados = metadados;
        this.indicadores = indicadores;
        this.serieTemporal = serieTemporal;
        this.tendenciaSla = tendenciaSla;
        this.quebrasSla = quebrasSla;
        this.capacidadeDaEquipe = capacidadeDaEquipe;
        this.chamadosQuePrecisamDeAtencao = chamadosQuePrecisamDeAtencao;
    }

    public DashboardSuporte() {
    }


    public MetadadosDashboard getMetadados() {
        return metadados;
    }

    public void setMetadados(MetadadosDashboard metadados) {
        this.metadados = metadados;
    }

    public IndicadoresSuporte getIndicadores() {
        return indicadores;
    }

    public void setIndicadores(IndicadoresSuporte indicadores) {
        this.indicadores = indicadores;
    }

    public List<PontoTemporal> getSerieTemporal() {
        return serieTemporal;
    }

    public void setSerieTemporal(List<PontoTemporal> serieTemporal) {
        this.serieTemporal = serieTemporal;
    }

    public List<PontoTendenciaSla> getTendenciaSla() {
        return tendenciaSla;
    }

    public void setTendenciaSla(List<PontoTendenciaSla> tendenciaSla) {
        this.tendenciaSla = tendenciaSla;
    }

    public QuebrasSla getQuebrasSla() {
        return quebrasSla;
    }

    public void setQuebrasSla(QuebrasSla quebrasSla) {
        this.quebrasSla = quebrasSla;
    }

    public List<CapacidadeEquipe> getCapacidadeDaEquipe() {
        return capacidadeDaEquipe;
    }

    public void setCapacidadeDaEquipe(List<CapacidadeEquipe> capacidadeDaEquipe) {
        this.capacidadeDaEquipe = capacidadeDaEquipe;
    }

    public List<ChamadoAtencao> getChamadosQuePrecisamDeAtencao() {
        return chamadosQuePrecisamDeAtencao;
    }

    public void setChamadosQuePrecisamDeAtencao(List<ChamadoAtencao> chamadosQuePrecisamDeAtencao) {
        this.chamadosQuePrecisamDeAtencao = chamadosQuePrecisamDeAtencao;
    }
}
