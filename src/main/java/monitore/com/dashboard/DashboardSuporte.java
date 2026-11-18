package monitore.com.dashboard;

import monitore.com.dashboard.itens.*;

import java.util.List;

public class DashboardSuporte {
    private MetadadosDashboard metadados;
    private IndicadoresSuporte indicadores;
    private List<StatusQuantidade> distribuicaoPorStatus;
    private List<PontoTemporal> serieTemporal;
    private List<PontoTendenciaSla> tendenciaSla;
    private QuebrasSla quebrasSla;
    private List<CapacidadeEquipe> capacidadeDaEquipe;
    private TempoResolucao tempoDeResolucao;
    private List<ChamadoAtencao> chamadosQuePrecisamDeAtencao;

    // Construtor, getters e setters


    public DashboardSuporte(MetadadosDashboard metadados, IndicadoresSuporte indicadores, List<StatusQuantidade> distribuicaoPorStatus, List<PontoTemporal> serieTemporal, List<PontoTendenciaSla> tendenciaSla, QuebrasSla quebrasSla, List<CapacidadeEquipe> capacidadeDaEquipe, TempoResolucao tempoDeResolucao, List<ChamadoAtencao> chamadosQuePrecisamDeAtencao) {
        this.metadados = metadados;
        this.indicadores = indicadores;
        this.distribuicaoPorStatus = distribuicaoPorStatus;
        this.serieTemporal = serieTemporal;
        this.tendenciaSla = tendenciaSla;
        this.quebrasSla = quebrasSla;
        this.capacidadeDaEquipe = capacidadeDaEquipe;
        this.tempoDeResolucao = tempoDeResolucao;
        this.chamadosQuePrecisamDeAtencao = chamadosQuePrecisamDeAtencao;
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

    public List<StatusQuantidade> getDistribuicaoPorStatus() {
        return distribuicaoPorStatus;
    }

    public void setDistribuicaoPorStatus(List<StatusQuantidade> distribuicaoPorStatus) {
        this.distribuicaoPorStatus = distribuicaoPorStatus;
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

    public TempoResolucao getTempoDeResolucao() {
        return tempoDeResolucao;
    }

    public void setTempoDeResolucao(TempoResolucao tempoDeResolucao) {
        this.tempoDeResolucao = tempoDeResolucao;
    }

    public List<ChamadoAtencao> getChamadosQuePrecisamDeAtencao() {
        return chamadosQuePrecisamDeAtencao;
    }

    public void setChamadosQuePrecisamDeAtencao(List<ChamadoAtencao> chamadosQuePrecisamDeAtencao) {
        this.chamadosQuePrecisamDeAtencao = chamadosQuePrecisamDeAtencao;
    }
}