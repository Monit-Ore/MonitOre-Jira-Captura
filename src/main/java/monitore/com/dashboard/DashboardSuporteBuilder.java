package monitore.com.dashboard;

import monitore.com.chamados.Chamado;
import monitore.com.dashboard.itens.CapacidadeEquipe;
import monitore.com.dashboard.itens.ChamadoAtencao;
import monitore.com.dashboard.itens.CumprimentoSla;
import monitore.com.dashboard.itens.IndicadoresSuporte;
import monitore.com.dashboard.itens.MetadadosDashboard;
import monitore.com.dashboard.itens.PontoTemporal;
import monitore.com.dashboard.itens.PontoTendenciaSla;
import monitore.com.dashboard.itens.QuebraSla;
import monitore.com.dashboard.itens.QuebrasSla;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DashboardSuporteBuilder {
    private List<Chamado> chamados;

    public DashboardSuporteBuilder(List<Chamado> chamados) {
        this.chamados = chamados;
    }

    public DashboardSuporte construir() {
        DashboardSuporte dashboard = new DashboardSuporte();

        dashboard.setMetadados(criarMetadados());
        CumprimentoSla cumprimentoSla = calcularCumprimentoSla(chamados);
        dashboard.setIndicadores(calcularIndicadores(chamados, cumprimentoSla));
        dashboard.setCapacidadeDaEquipe(calcularCapacidadeDaEquipe(chamados));
        dashboard.setSerieTemporal(calcularSerieTemporal(chamados));
        dashboard.setTendenciaSla(calcularTendenciaSla(chamados));
        dashboard.setQuebrasSla(calcularQuebrasSla(chamados));
        dashboard.setChamadosQuePrecisamDeAtencao(calcularChamadosAtencao(chamados));

        return dashboard;
    }

        private MetadadosDashboard criarMetadados() {
            MetadadosDashboard metadados = new MetadadosDashboard();

            metadados.setNomeRelatorio("Visão geral do suporte");
            metadados.setFonte("JIRA");
            metadados.setExtraidoEm(Instant.now());
            metadados.setPeriodoConsultado("Últimos 30 dias");
            metadados.setPeriodoComparacao("30 dias anteriores");
            metadados.setDadosDemonstrativos(false);
            metadados.setObservacao(null);
            return metadados;
        }

    private IndicadoresSuporte calcularIndicadores(
            List<Chamado> chamados,
            CumprimentoSla cumprimentoSla
    ) {
        IndicadoresSuporte indicadores = new IndicadoresSuporte();

        int totalChamadosAbertos = 0;
        int totalChamadosCriticos = 0;
        int criticosSemResponsavel = 0;
        int criticosVencidos = 0;
        int slaEmRisco = 0;
        int slaVencemProximaHora = 0;
        int slaVencido = 0;
        int chamadosParadosSeteDias = 0;
        long maiorAtrasoSegundos = 0;

        Instant agora = Instant.now();

        for (Chamado chamado : chamados) {
            String status = chamado.getStatus();

            boolean aberto = status != null
                    && (status.equalsIgnoreCase("A Fazer")
                    || status.equalsIgnoreCase("Em andamento"));

            if (!aberto) {
                continue;
            }

            totalChamadosAbertos++;

            boolean critico = "Crítica".equalsIgnoreCase(chamado.getPrioridade());

            if (critico) {
                totalChamadosCriticos++;

                String responsavel = chamado.getResponsavel();
                boolean semResponsavel = responsavel == null
                        || responsavel.isBlank()
                        || responsavel.equalsIgnoreCase("Sem responsável");

                if (semResponsavel) {
                    criticosSemResponsavel++;
                }
            }

            Long prazoSlaEm = chamado.getPrazoSlaEm();
            boolean vencido = Boolean.TRUE.equals(chamado.getSlaVencido());

            if (vencido) {
                slaVencido++;

                if (critico) {
                    criticosVencidos++;
                }

                if (prazoSlaEm != null) {
                    long atrasoSegundos = Math.max(0L, -prazoSlaEm);
                    maiorAtrasoSegundos = Math.max(maiorAtrasoSegundos, atrasoSegundos);
                }
            } else if (prazoSlaEm != null && prazoSlaEm > 0) {
                if (prazoSlaEm <= 4L * 60 * 60) {
                    slaEmRisco++;
                }

                if (prazoSlaEm <= 60L * 60) {
                    slaVencemProximaHora++;
                }
            }

            Instant atualizadoEm = chamado.getAtualizadoEm();
            if (atualizadoEm != null
                    && Duration.between(atualizadoEm, agora).toHours() >= 7L * 24) {
                chamadosParadosSeteDias++;
            }
        }

        indicadores.setChamadosAbertos(totalChamadosAbertos);
        indicadores.setChamadosCriticos(totalChamadosCriticos);
        indicadores.setChamadosCriticosSemResponsavel(criticosSemResponsavel);
        indicadores.setChamadosCriticosVencidos(criticosVencidos);
        indicadores.setSlaEmRisco(slaEmRisco);
        indicadores.setSlaVencemProximaHora(slaVencemProximaHora);
        indicadores.setSlaVencido(slaVencido);
        indicadores.setSlaVencidoMaisAntigoHoras(maiorAtrasoSegundos / 3600);
        indicadores.setChamadosParadosSeteDias(chamadosParadosSeteDias);
        indicadores.setCumprimentoSla(cumprimentoSla);

        return indicadores;
    }

    private List<CapacidadeEquipe> calcularCapacidadeDaEquipe(List<Chamado> chamados) {
        Map<String, CapacidadeEquipe> capacidadePorResponsavel = new LinkedHashMap<>();

        for (Chamado chamado : chamados) {
            boolean aberto = chamado.getStatus() != null
                    && (chamado.getStatus().equalsIgnoreCase("A Fazer")
                    || chamado.getStatus().equalsIgnoreCase("Em andamento"));

            if (!aberto) {
                continue;
            }

            String responsavel = chamado.getResponsavel();
            if (responsavel == null
                    || responsavel.isBlank()
                    || responsavel.equalsIgnoreCase("Sem responsável")) {
                responsavel = "Sem responsável";
            }

            CapacidadeEquipe capacidade = capacidadePorResponsavel.get(responsavel);

            if (capacidade == null) {
                capacidade = new CapacidadeEquipe();
                capacidade.setResponsavel(responsavel);
                capacidadePorResponsavel.put(responsavel, capacidade);
            }

            capacidade.setChamadosAtivos(capacidade.getChamadosAtivos() + 1);

            if ("Crítica".equalsIgnoreCase(chamado.getPrioridade())) {
                capacidade.setChamadosPrioridadeCritica(
                        capacidade.getChamadosPrioridadeCritica() + 1
                );
                capacidade.setChamadosPrioridadeAltaOuCritica(
                        capacidade.getChamadosPrioridadeAltaOuCritica() + 1
                );
            } else if ("Alta".equalsIgnoreCase(chamado.getPrioridade())) {
                capacidade.setChamadosPrioridadeAlta(
                        capacidade.getChamadosPrioridadeAlta() + 1
                );
                capacidade.setChamadosPrioridadeAltaOuCritica(
                        capacidade.getChamadosPrioridadeAltaOuCritica() + 1
                );
            } else {
                capacidade.setChamadosDemaisPrioridades(
                        capacidade.getChamadosDemaisPrioridades() + 1
                );
            }
        }

        return new ArrayList<>(capacidadePorResponsavel.values());
    }

    private List<PontoTemporal> calcularSerieTemporal(List<Chamado> chamados) {
        ZoneId fusoSaoPaulo = ZoneId.of("America/Sao_Paulo");
        Map<LocalDate, PontoTemporal> pontosPorData = new TreeMap<>();

        for (Chamado chamado : chamados) {
            if (chamado.getCriadoEm() != null) {
                LocalDate dataCriacao = chamado.getCriadoEm()
                        .atZone(fusoSaoPaulo)
                        .toLocalDate();

                PontoTemporal ponto = pontosPorData.computeIfAbsent(
                        dataCriacao,
                        this::criarPontoTemporal
                );
                ponto.setChamadosRecebidos(ponto.getChamadosRecebidos() + 1);
            }

            if (chamado.getResolvidoEm() != null) {
                LocalDate dataResolucao = chamado.getResolvidoEm()
                        .atZone(fusoSaoPaulo)
                        .toLocalDate();

                PontoTemporal ponto = pontosPorData.computeIfAbsent(
                        dataResolucao,
                        this::criarPontoTemporal
                );
                ponto.setChamadosResolvidos(ponto.getChamadosResolvidos() + 1);
            }
        }

        return new ArrayList<>(pontosPorData.values());
    }

    private PontoTemporal criarPontoTemporal(LocalDate data) {
        PontoTemporal ponto = new PontoTemporal();
        ponto.setData(data);
        ponto.setChamadosRecebidos(0);
        ponto.setChamadosResolvidos(0);
        return ponto;
    }

    private CumprimentoSla calcularCumprimentoSla(List<Chamado> chamados) {
        ZoneId fusoSaoPaulo = ZoneId.of("America/Sao_Paulo");
        LocalDate hoje = LocalDate.now(fusoSaoPaulo);
        LocalDate inicioPeriodoAtual = hoje.minusDays(29);
        LocalDate inicioPeriodoAnterior = inicioPeriodoAtual.minusDays(30);

        int totalAtual = 0;
        int noPrazoAtual = 0;
        int totalAnterior = 0;
        int noPrazoAnterior = 0;

        for (Chamado chamado : chamados) {
            if (!ehElegivelParaSla(chamado)) {
                continue;
            }

            LocalDate dataResolucao = dataEmSaoPaulo(chamado.getResolvidoEm());
            boolean noPrazo = foiResolvidoNoPrazo(chamado);

            if (!dataResolucao.isBefore(inicioPeriodoAtual)) {
                totalAtual++;
                if (noPrazo) noPrazoAtual++;
            } else if (!dataResolucao.isBefore(inicioPeriodoAnterior)) {
                totalAnterior++;
                if (noPrazo) noPrazoAnterior++;
            }
        }

        CumprimentoSla cumprimento = new CumprimentoSla();
        cumprimento.setResolvidosNoPrazo(noPrazoAtual);
        cumprimento.setTotalElegiveis(totalAtual);
        cumprimento.setPercentualAnterior(calcularPercentual(noPrazoAnterior, totalAnterior));
        cumprimento.setMetaPercentual(95);
        return cumprimento;
    }

    private List<PontoTendenciaSla> calcularTendenciaSla(List<Chamado> chamados) {
        ZoneId fusoSaoPaulo = ZoneId.of("America/Sao_Paulo");
        LocalDate inicioPeriodo = LocalDate.now(fusoSaoPaulo).minusDays(29);
        Map<LocalDate, PontoTendenciaSla> pontosPorData = new TreeMap<>();

        for (Chamado chamado : chamados) {
            if (!ehElegivelParaSla(chamado)) {
                continue;
            }

            LocalDate dataResolucao = dataEmSaoPaulo(chamado.getResolvidoEm());
            if (dataResolucao.isBefore(inicioPeriodo)) {
                continue;
            }

            PontoTendenciaSla ponto = pontosPorData.computeIfAbsent(
                    dataResolucao,
                    this::criarPontoTendenciaSla
            );
            ponto.setTotalElegiveis(ponto.getTotalElegiveis() + 1);
            if (foiResolvidoNoPrazo(chamado)) {
                ponto.setResolvidosNoPrazo(ponto.getResolvidosNoPrazo() + 1);
            }
        }

        for (PontoTendenciaSla ponto : pontosPorData.values()) {
            ponto.setPercentual(calcularPercentual(
                    ponto.getResolvidosNoPrazo(),
                    ponto.getTotalElegiveis()
            ));
        }

        return new ArrayList<>(pontosPorData.values());
    }

    private PontoTendenciaSla criarPontoTendenciaSla(LocalDate data) {
        PontoTendenciaSla ponto = new PontoTendenciaSla();
        ponto.setData(data);
        return ponto;
    }

    private QuebrasSla calcularQuebrasSla(List<Chamado> chamados) {
        ZoneId fusoSaoPaulo = ZoneId.of("America/Sao_Paulo");
        LocalDate inicioPeriodo = LocalDate.now(fusoSaoPaulo).minusDays(29);
        Map<String, QuebraSla> porPrioridade = new LinkedHashMap<>();
        Map<String, QuebraSla> porTipo = new LinkedHashMap<>();

        for (Chamado chamado : chamados) {
            if (!ehElegivelParaSla(chamado)
                    || dataEmSaoPaulo(chamado.getResolvidoEm()).isBefore(inicioPeriodo)) {
                continue;
            }

            adicionarQuebraSla(porPrioridade, chamado.getPrioridade(), foiResolvidoNoPrazo(chamado));
            adicionarQuebraSla(porTipo, chamado.getTipo(), foiResolvidoNoPrazo(chamado));
        }

        QuebrasSla quebras = new QuebrasSla();
        quebras.setPrioridade(new ArrayList<>(porPrioridade.values()));
        quebras.setTipo(new ArrayList<>(porTipo.values()));
        return quebras;
    }

    private void adicionarQuebraSla(
            Map<String, QuebraSla> quebras,
            String categoria,
            boolean resolvidoNoPrazo
    ) {
        String chave = categoria == null || categoria.isBlank() ? "Não informado" : categoria;
        QuebraSla quebra = quebras.computeIfAbsent(chave, this::criarQuebraSla);
        quebra.setTotalElegiveis(quebra.getTotalElegiveis() + 1);
        if (resolvidoNoPrazo) {
            quebra.setResolvidosNoPrazo(quebra.getResolvidosNoPrazo() + 1);
        }
    }

    private QuebraSla criarQuebraSla(String categoria) {
        QuebraSla quebra = new QuebraSla();
        quebra.setCategoria(categoria);
        return quebra;
    }

    private List<ChamadoAtencao> calcularChamadosAtencao(List<Chamado> chamados) {
        Instant agora = Instant.now();
        List<ChamadoAtencao> resultado = new ArrayList<>();

        for (Chamado chamado : chamados) {
            if (!ehAberto(chamado) || chamado.getPrazoSlaEm() == null) {
                continue;
            }

            boolean vencido = Boolean.TRUE.equals(chamado.getSlaVencido());
            boolean emRisco = !vencido
                    && chamado.getPrazoSlaEm() > 0
                    && chamado.getPrazoSlaEm() <= 4L * 60 * 60;
            boolean critico = "Crítica".equalsIgnoreCase(chamado.getPrioridade());

            if (!vencido && !emRisco && !critico) {
                continue;
            }

            ChamadoAtencao item = new ChamadoAtencao();
            item.setChave(chamado.getChave());
            item.setResumo(chamado.getResumo());
            item.setProjeto(chamado.getProjeto());
            item.setTipo(chamado.getTipo());
            item.setPrioridade(chamado.getPrioridade());
            item.setStatus(chamado.getStatus());

            boolean semResponsavel = chamado.getResponsavel() == null
                    || chamado.getResponsavel().isBlank()
                    || chamado.getResponsavel().equalsIgnoreCase("Sem responsável");
            item.setSemResponsavel(semResponsavel);
            item.setResponsavel(semResponsavel ? null : chamado.getResponsavel());
            item.setPrazoSlaEm(chamado.getPrazoSlaEm());
            item.setSlaVencido(vencido);
            item.setTempoDesdeUltimaAtualizacaoHoras(
                    chamado.getAtualizadoEm() == null
                            ? 0
                            : Math.max(0, Duration.between(chamado.getAtualizadoEm(), agora).toHours())
            );
            item.setEndereco(null);
            resultado.add(item);
        }

        resultado.sort(Comparator.comparing(ChamadoAtencao::isSlaVencido)
                .reversed()
                .thenComparingLong(ChamadoAtencao::getPrazoSlaEm));
        return resultado;
    }

    private boolean ehAberto(Chamado chamado) {
        return chamado.getStatus() != null
                && (chamado.getStatus().equalsIgnoreCase("A Fazer")
                || chamado.getStatus().equalsIgnoreCase("Em andamento"));
    }

    private boolean ehElegivelParaSla(Chamado chamado) {
        return chamado.getResolvidoEm() != null && chamado.getDataEntrega() != null;
    }

    private boolean foiResolvidoNoPrazo(Chamado chamado) {
        Instant prazoFinal = chamado.getDataEntrega()
                .atTime(23, 59, 59)
                .atZone(ZoneId.of("America/Sao_Paulo"))
                .toInstant();
        return !chamado.getResolvidoEm().isAfter(prazoFinal);
    }

    private LocalDate dataEmSaoPaulo(Instant data) {
        return data.atZone(ZoneId.of("America/Sao_Paulo")).toLocalDate();
    }

    private int calcularPercentual(int parte, int total) {
        if (total == 0) {
            return 0;
        }
        return Math.round((parte * 100.0f) / total);
    }
}
