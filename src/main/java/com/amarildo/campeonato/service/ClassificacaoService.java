package com.amarildo.campeonato.service;

import com.amarildo.campeonato.dto.ClassificacaoRequestDTO;
import com.amarildo.campeonato.dto.ClassificacaoResponseDTO;
import com.amarildo.campeonato.entity.CampeonatoEquipe;
import com.amarildo.campeonato.entity.Classificacao;
import com.amarildo.campeonato.entity.FaseCampeonato;
import com.amarildo.campeonato.repository.CampeonatoEquipeRepository;
import com.amarildo.campeonato.repository.ClassificacaoRepository;
import com.amarildo.campeonato.repository.FaseCampeonatoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClassificacaoService {

    private final ClassificacaoRepository classificacaoRepository;
    private final FaseCampeonatoRepository faseCampeonatoRepository;
    private final CampeonatoEquipeRepository campeonatoEquipeRepository;

    public ClassificacaoService(ClassificacaoRepository classificacaoRepository,
                                FaseCampeonatoRepository faseCampeonatoRepository,
                                CampeonatoEquipeRepository campeonatoEquipeRepository) {
        this.classificacaoRepository = classificacaoRepository;
        this.faseCampeonatoRepository = faseCampeonatoRepository;
        this.campeonatoEquipeRepository = campeonatoEquipeRepository;
    }

    public List<ClassificacaoResponseDTO> listarTodos() {
        return classificacaoRepository.findAll()
                .stream()
                .map(this::converterParaResponseDTO)
                .toList();
    }

    public Optional<ClassificacaoResponseDTO> buscarPorId(Long id) {
        return classificacaoRepository.findById(id)
                .map(this::converterParaResponseDTO);
    }

    public ClassificacaoResponseDTO salvar(ClassificacaoRequestDTO dto) {
        Classificacao classificacao = new Classificacao();
        preencherClassificacao(classificacao, dto);
        return converterParaResponseDTO(classificacaoRepository.save(classificacao));
    }

    public Optional<ClassificacaoResponseDTO> atualizar(Long id, ClassificacaoRequestDTO dto) {
        return classificacaoRepository.findById(id)
                .map(classificacao -> {
                    preencherClassificacao(classificacao, dto);
                    return converterParaResponseDTO(classificacaoRepository.save(classificacao));
                });
    }

    public void excluir(Long id) {
        classificacaoRepository.deleteById(id);
    }

    public boolean existePorId(Long id) {
        return classificacaoRepository.existsById(id);
    }

    private void preencherClassificacao(Classificacao classificacao, ClassificacaoRequestDTO dto) {
        validarCamposObrigatorios(dto);

        Integer pontos = valorOuZero(dto.getPontos());
        Integer jogos = valorOuZero(dto.getJogos());
        Integer vitorias = valorOuZero(dto.getVitorias());
        Integer empates = valorOuZero(dto.getEmpates());
        Integer derrotas = valorOuZero(dto.getDerrotas());
        Integer golsPro = valorOuZero(dto.getGolsPro());
        Integer golsContra = valorOuZero(dto.getGolsContra());

        validarNumeros(dto.getPosicao(), pontos, jogos, vitorias, empates, derrotas, golsPro, golsContra);
        validarJogosResultados(jogos, vitorias, empates, derrotas);
        validarUltimosJogos(dto.getUltimosJogos());

        FaseCampeonato faseCampeonato = faseCampeonatoRepository.findById(dto.getIdenFaseCampeonato())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Fase do campeonato nao encontrada: " + dto.getIdenFaseCampeonato()
                ));

        CampeonatoEquipe campeonatoEquipe = campeonatoEquipeRepository.findById(dto.getIdenCampeonatoEquipe())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Equipe do campeonato nao encontrada: " + dto.getIdenCampeonatoEquipe()
                ));

        validarClassificacaoDuplicada(classificacao, dto);

        classificacao.setFaseCampeonato(faseCampeonato);
        classificacao.setCampeonatoEquipe(campeonatoEquipe);
        classificacao.setPosicao(dto.getPosicao());
        classificacao.setPontos(pontos);
        classificacao.setJogos(jogos);
        classificacao.setVitorias(vitorias);
        classificacao.setEmpates(empates);
        classificacao.setDerrotas(derrotas);
        classificacao.setGolsPro(golsPro);
        classificacao.setGolsContra(golsContra);
        classificacao.setDataAtualizacao(
                dto.getDataAtualizacao() != null ? dto.getDataAtualizacao() : LocalDateTime.now()
        );
        classificacao.setUltimosJogos(dto.getUltimosJogos());
    }

    private void validarCamposObrigatorios(ClassificacaoRequestDTO dto) {
        if (dto.getIdenFaseCampeonato() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Fase do campeonato deve ser informada");
        }

        if (dto.getIdenCampeonatoEquipe() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Equipe do campeonato deve ser informada");
        }
    }

    private Integer valorOuZero(Integer valor) {
        return valor != null ? valor : 0;
    }

    private void validarNumeros(Integer posicao,
                                Integer pontos,
                                Integer jogos,
                                Integer vitorias,
                                Integer empates,
                                Integer derrotas,
                                Integer golsPro,
                                Integer golsContra) {
        if (posicao != null && posicao <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Posicao deve ser maior que zero");
        }

        if (pontos < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Pontos deve ser maior ou igual a zero");
        }

        if (jogos < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Jogos deve ser maior ou igual a zero");
        }

        if (vitorias < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vitorias deve ser maior ou igual a zero");
        }

        if (empates < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Empates deve ser maior ou igual a zero");
        }

        if (derrotas < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Derrotas deve ser maior ou igual a zero");
        }

        if (golsPro < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gols pro deve ser maior ou igual a zero");
        }

        if (golsContra < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Gols contra deve ser maior ou igual a zero");
        }
    }

    private void validarJogosResultados(Integer jogos, Integer vitorias, Integer empates, Integer derrotas) {
        if (!jogos.equals(vitorias + empates + derrotas)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Jogos deve ser igual a soma de vitorias, empates e derrotas"
            );
        }
    }

    private void validarUltimosJogos(String ultimosJogos) {
        if (ultimosJogos != null && ultimosJogos.length() > 5) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ultimos jogos deve ter no maximo 5 caracteres");
        }
    }

    private void validarClassificacaoDuplicada(Classificacao classificacao, ClassificacaoRequestDTO dto) {
        boolean classificacaoDuplicada;

        if (classificacao.getIdenClassificacao() == null) {
            classificacaoDuplicada = classificacaoRepository
                    .existsByFaseCampeonato_IdenFaseCampeonatoAndCampeonatoEquipe_IdenCampeonatoEquipe(
                            dto.getIdenFaseCampeonato(),
                            dto.getIdenCampeonatoEquipe()
                    );
        } else {
            classificacaoDuplicada = classificacaoRepository
                    .existsByFaseCampeonato_IdenFaseCampeonatoAndCampeonatoEquipe_IdenCampeonatoEquipeAndIdenClassificacaoNot(
                            dto.getIdenFaseCampeonato(),
                            dto.getIdenCampeonatoEquipe(),
                            classificacao.getIdenClassificacao()
                    );
        }

        if (classificacaoDuplicada) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ja existe classificacao para esta equipe na fase informada"
            );
        }
    }

    private ClassificacaoResponseDTO converterParaResponseDTO(Classificacao classificacao) {
        ClassificacaoResponseDTO response = new ClassificacaoResponseDTO();

        response.setIdenClassificacao(classificacao.getIdenClassificacao());
        response.setIdenFaseCampeonato(classificacao.getFaseCampeonato().getIdenFaseCampeonato());
        response.setNomeFaseCampeonato(classificacao.getFaseCampeonato().getNomeFaseCampeonato());
        response.setIdenCampeonatoEquipe(classificacao.getCampeonatoEquipe().getIdenCampeonatoEquipe());
        response.setNomeEquipe(classificacao.getCampeonatoEquipe().getEquipe().getNomeEquipe());
        response.setPosicao(classificacao.getPosicao());
        response.setPontos(classificacao.getPontos());
        response.setJogos(classificacao.getJogos());
        response.setVitorias(classificacao.getVitorias());
        response.setEmpates(classificacao.getEmpates());
        response.setDerrotas(classificacao.getDerrotas());
        response.setGolsPro(classificacao.getGolsPro());
        response.setGolsContra(classificacao.getGolsContra());
        response.setSaldoGols(classificacao.getSaldoGols());
        response.setDataAtualizacao(classificacao.getDataAtualizacao());
        response.setUltimosJogos(classificacao.getUltimosJogos());
        return response;
    }
}
