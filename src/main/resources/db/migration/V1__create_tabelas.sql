CREATE TABLE cidade (
    iden_cidade BIGSERIAL PRIMARY KEY,
    nome_cidade VARCHAR(200) NOT NULL,
    uf VARCHAR(2) NOT NULL,
    CONSTRAINT chk_cidade_uf
        CHECK (uf IN ('AC', 'AL', 'AP', 'AM', 'BA', 'CE', 'DF', 'ES', 'GO', 'MA', 'MT', 'MS', 'MG', 'PA', 'PB', 'PR', 'PE', 'PI', 'RJ', 'RN', 'RS', 'RO', 'RR', 'SC', 'SP', 'SE', 'TO'))
);

CREATE TABLE estadio (
    iden_estadio BIGSERIAL PRIMARY KEY,
    nome_estadio VARCHAR(200) NOT NULL,
	apelido_estadio VARCHAR(200),
    iden_cidade BIGINT NOT NULL,

    CONSTRAINT fk_estadio_cidade
        FOREIGN KEY (iden_cidade)
        REFERENCES cidade (iden_cidade)
);

CREATE TABLE equipe (
    iden_equipe BIGSERIAL PRIMARY KEY,
    nome_equipe VARCHAR(200) NOT NULL,
	sigla_equipe VARCHAR(10),
    iden_cidade BIGINT NOT NULL,	
	caminho_escudo VARCHAR(500),
	iden_estadio_padrao BIGINT,

    CONSTRAINT fk_equipe_cidade
        FOREIGN KEY (iden_cidade)
        REFERENCES cidade (iden_cidade), 
		
	CONSTRAINT uk_equipe_nome_cidade
		UNIQUE (nome_equipe, iden_cidade), 
		
	CONSTRAINT fk_equipe_estadio_padrao
        FOREIGN KEY (iden_estadio_padrao)
        REFERENCES estadio (iden_estadio)
);


CREATE TABLE tipodisputa (
    iden_tipodisputa BIGSERIAL PRIMARY KEY,
    nome_tipodisputa VARCHAR(100) NOT NULL,	--ex: Pontos corridos, Mata-mata
	status_tipodisputa VARCHAR(20) NOT NULL DEFAULT 'INATIVO',    
	

	CONSTRAINT uk_tipodisputa_nome
        UNIQUE (nome_tipodisputa),
		
	CONSTRAINT chk_tipodisputa_status
        CHECK (status_tipodisputa IN ('ATIVA', 'INATIVO'))
);


CREATE TABLE campeonato (
    iden_campeonato BIGSERIAL PRIMARY KEY,
    nome_campeonato VARCHAR(200) NOT NULL,	    
	ano INTEGER NOT NULL,
	data_inicio DATE,
	data_fim DATE,	
	
	CONSTRAINT uk_campeonato_nome_ano
        UNIQUE (nome_campeonato, ano),
	
	CONSTRAINT chk_campeonato_datas
        CHECK (
            data_fim IS NULL
            OR data_inicio IS NULL
            OR data_fim >= data_inicio
        )
);

CREATE TABLE campeonatoequipe (
    iden_campeonatoequipe BIGSERIAL PRIMARY KEY,
    iden_campeonato BIGINT NOT NULL,
    iden_equipe BIGINT NOT NULL,	
    data_inscricao DATE NOT NULL DEFAULT CURRENT_DATE,    	
	cabeca_chave BOOLEAN NOT NULL DEFAULT FALSE,
    status_participacao VARCHAR(20) NOT NULL DEFAULT 'ATIVA',    

    CONSTRAINT fk_campeonatoequipe_campeonato
        FOREIGN KEY (iden_campeonato)
        REFERENCES campeonato (iden_campeonato),

    CONSTRAINT fk_campeonatoequipe_equipe
        FOREIGN KEY (iden_equipe)
        REFERENCES equipe (iden_equipe),

    CONSTRAINT uk_campeonatoequipe_campeonato_equipe
        UNIQUE (iden_campeonato, iden_equipe),    

    CONSTRAINT chk_campeonatoequipe_status
        CHECK (status_participacao IN ('ATIVA', 'DESISTENTE', 'ELIMINADA', 'DESCLASSIFICADA', 'SUSPENSA'))
);

CREATE TABLE fasecampeonato (
    iden_fasecampeonato BIGSERIAL PRIMARY KEY,
	iden_campeonato BIGINT NOT NULL,
    nome_fasecampeonato VARCHAR(200) NOT NULL,	
    ordem INTEGER NOT NULL,
	qtdclassificam INTEGER,
	possuiidavolta BOOLEAN NOT NULL DEFAULT FALSE,
	iden_tipodisputa BIGINT NOT NULL,	--pontos corridos, mata mata
	
	CONSTRAINT fk_fasecampeonato_campeonato
        FOREIGN KEY (iden_campeonato)
        REFERENCES campeonato (iden_campeonato),
		
	CONSTRAINT fk_fasecampeonato_tipodisputa
        FOREIGN KEY (iden_tipodisputa)
        REFERENCES tipodisputa (iden_tipodisputa),
		
	CONSTRAINT uk_fasecampeonato_ordem
        UNIQUE (iden_campeonato, ordem), 
		
	CONSTRAINT chk_fasecampeonato_ordem
		CHECK (ordem > 0),		
		
	CONSTRAINT chk_fasecampeonato_qtdclassificam
		CHECK (qtdclassificam IS NULL OR qtdclassificam > 0)		
);

CREATE TABLE faixaclassificacao (
    iden_faixaclassificacao BIGSERIAL PRIMARY KEY,
    iden_fasecampeonato BIGINT NOT NULL,
    nome_faixa VARCHAR(100) NOT NULL,        
    codigo_cor VARCHAR(20),

    CONSTRAINT fk_faixaclassificacao_fase
        FOREIGN KEY (iden_fasecampeonato)
        REFERENCES fasecampeonato (iden_fasecampeonato),    
		
	CONSTRAINT uk_faixaclassificacao_fase_nome
		UNIQUE (iden_fasecampeonato, nome_faixa)		
	
);

CREATE TABLE faixaclassificacaovigencia (
    iden_faixaclassificacaovigencia BIGSERIAL PRIMARY KEY,
    iden_faixaclassificacao BIGINT NOT NULL,
    posicao_inicial INTEGER NOT NULL,
    posicao_final INTEGER NOT NULL,
    data_inicio_vigencia DATE NOT NULL,
    data_fim_vigencia DATE,--permite null, o registro null eh o registro "ATIVO"
    motivo_alteracao VARCHAR(500),

    CONSTRAINT fk_faixaclassificacaovigencia_faixa
        FOREIGN KEY (iden_faixaclassificacao)
        REFERENCES faixaclassificacao (iden_faixaclassificacao),

    CONSTRAINT chk_faixavigencia_posicoes
        CHECK (posicao_inicial > 0 AND posicao_final >= posicao_inicial),

    CONSTRAINT chk_faixavigencia_datas
        CHECK (data_fim_vigencia IS NULL OR data_fim_vigencia >= data_inicio_vigencia),

    CONSTRAINT uk_faixavigencia_inicio
        UNIQUE (iden_faixaclassificacao, data_inicio_vigencia)
		
);

CREATE TABLE rodada (
    iden_rodada BIGSERIAL PRIMARY KEY,
    iden_fasecampeonato BIGINT NOT NULL,	
    ordem_rodada INTEGER NOT NULL,    	
	nome_rodada VARCHAR(100),
	data_inicio DATE,
	data_fim DATE,
	status_rodada VARCHAR(20) NOT NULL DEFAULT 'PROGRAMADA',
	
	CONSTRAINT fk_rodada_fasecampeonato
        FOREIGN KEY (iden_fasecampeonato)
        REFERENCES fasecampeonato (iden_fasecampeonato), 
		
	CONSTRAINT uk_rodada_ordem
        UNIQUE (iden_fasecampeonato, ordem_rodada),

    CONSTRAINT chk_rodada_ordem
        CHECK (ordem_rodada > 0), 
		
	CONSTRAINT chk_rodada_datas
        CHECK (data_fim IS NULL OR data_inicio IS NULL OR data_fim >= data_inicio),
		
	CONSTRAINT chk_rodada_status
        CHECK (status_rodada IN ('PROGRAMADA', 'EM_ANDAMENTO', 'FINALIZADA', 'CANCELADA'))	
);

CREATE TABLE partida (
    iden_partida BIGSERIAL PRIMARY KEY,
	iden_rodada BIGINT NOT NULL,
	iden_campeonatoequipe_mandante BIGINT NOT NULL,
	iden_campeonatoequipe_visitante BIGINT NOT NULL,
	data_hora_partida TIMESTAMP,
	iden_estadio BIGINT,
	status_partida VARCHAR(20) NOT NULL DEFAULT 'PROGRAMADA',
	
	CONSTRAINT fk_partida_rodada
        FOREIGN KEY (iden_rodada)
        REFERENCES rodada (iden_rodada),
		
	CONSTRAINT fk_partida_campeonatoequipe_mandante
		FOREIGN KEY (iden_campeonatoequipe_mandante)
		REFERENCES campeonatoequipe (iden_campeonatoequipe),
		
	CONSTRAINT fk_partida_campeonatoequipe_visitante
    FOREIGN KEY (iden_campeonatoequipe_visitante)
    REFERENCES campeonatoequipe (iden_campeonatoequipe), 
		
	CONSTRAINT fk_partida_estadio
		FOREIGN KEY (iden_estadio)
		REFERENCES estadio (iden_estadio),	
		
	CONSTRAINT chk_partida_equipes_diferentes
		CHECK (iden_campeonatoequipe_mandante <> iden_campeonatoequipe_visitante), 
		
	CONSTRAINT uk_partida_rodada_equipes
		UNIQUE (iden_rodada, iden_campeonatoequipe_mandante, iden_campeonatoequipe_visitante),
		
	CONSTRAINT chk_partida_status
        CHECK (status_partida IN ('PROGRAMADA', 'EM_ANDAMENTO', 'FINALIZADA', 'ADIADA', 'CANCELADA', 'SUSPENSA'))		
);

CREATE TABLE regrapontuacao (
    iden_regrapontuacao BIGSERIAL PRIMARY KEY,
    iden_fasecampeonato BIGINT NOT NULL,
    pontos_vitoria INTEGER NOT NULL DEFAULT 3,
    pontos_empate INTEGER NOT NULL DEFAULT 1,
    pontos_derrota INTEGER NOT NULL DEFAULT 0,
    
    CONSTRAINT fk_regrapontuacao_fasecampeonato
        FOREIGN KEY (iden_fasecampeonato)
        REFERENCES fasecampeonato (iden_fasecampeonato),

    CONSTRAINT uk_regrapontuacao_fasecampeonato
        UNIQUE (iden_fasecampeonato),

    CONSTRAINT chk_regrapontuacao_vitoria
        CHECK (pontos_vitoria >= 0),

    CONSTRAINT chk_regrapontuacao_empate
        CHECK (pontos_empate >= 0),

    CONSTRAINT chk_regrapontuacao_derrota
        CHECK (pontos_derrota >= 0)
);

CREATE TABLE resultadopartida (
    iden_resultadopartida BIGSERIAL PRIMARY KEY,
    iden_partida BIGINT NOT NULL,
    gols_equipe_mandante INTEGER NOT NULL,
    gols_equipe_visitante INTEGER NOT NULL,
    data_registro TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,    

    CONSTRAINT fk_resultadopartida_partida
        FOREIGN KEY (iden_partida)
        REFERENCES partida (iden_partida),

    CONSTRAINT uk_resultadopartida_partida
        UNIQUE (iden_partida),

    CONSTRAINT chk_resultadopartida_gols_mandante
        CHECK (gols_equipe_mandante >= 0),

    CONSTRAINT chk_resultadopartida_gols_visitante
        CHECK (gols_equipe_visitante >= 0)
);

CREATE TABLE criteriodesempate (
    iden_criteriodesempate BIGSERIAL PRIMARY KEY,
    nome_criteriodesempate VARCHAR(100) NOT NULL,
    codigo_criteriodesempate VARCHAR(50) NOT NULL,
    sentido_ordenacao VARCHAR(4) NOT NULL,    
	calculo_automatico BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT uk_criteriodesempate_nome
        UNIQUE (nome_criteriodesempate),

    CONSTRAINT uk_criteriodesempate_codigo
        UNIQUE (codigo_criteriodesempate),

    CONSTRAINT chk_criteriodesempate_sentido
        CHECK (sentido_ordenacao IN ('ASC', 'DESC'))
);

CREATE TABLE configuracaocriteriodesempate (
    iden_configcriteriodesempate BIGSERIAL PRIMARY KEY,
    iden_fasecampeonato BIGINT NOT NULL,
    iden_criteriodesempate BIGINT NOT NULL,
    ordem INTEGER NOT NULL,    

    CONSTRAINT fk_configcriterio_fasecampeonato
        FOREIGN KEY (iden_fasecampeonato)
        REFERENCES fasecampeonato (iden_fasecampeonato),

    CONSTRAINT fk_configcriterio_criteriodesempate
        FOREIGN KEY (iden_criteriodesempate)
        REFERENCES criteriodesempate (iden_criteriodesempate),

    CONSTRAINT uk_configcriterio_fase_criterio
        UNIQUE (iden_fasecampeonato, iden_criteriodesempate),

    CONSTRAINT uk_configcriterio_fase_ordem
        UNIQUE (iden_fasecampeonato, ordem),

    CONSTRAINT chk_configcriterio_ordem
        CHECK (ordem > 0)
);

CREATE TABLE classificacao (
    iden_classificacao BIGSERIAL PRIMARY KEY,
    iden_fasecampeonato BIGINT NOT NULL,
    iden_campeonatoequipe BIGINT NOT NULL,

    posicao INTEGER,
    pontos INTEGER NOT NULL DEFAULT 0,
    jogos INTEGER NOT NULL DEFAULT 0,
    vitorias INTEGER NOT NULL DEFAULT 0,
    empates INTEGER NOT NULL DEFAULT 0,
    derrotas INTEGER NOT NULL DEFAULT 0,
    gols_pro INTEGER NOT NULL DEFAULT 0,
    gols_contra INTEGER NOT NULL DEFAULT 0,    
	saldo_gols INTEGER GENERATED ALWAYS AS (gols_pro - gols_contra) STORED,

    data_atualizacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	ultimos_jogos VARCHAR(5), --guarda o resultado dos ultimos 5 jogos ex: 'VVEDV'
   

    CONSTRAINT fk_classificacao_fasecampeonato
        FOREIGN KEY (iden_fasecampeonato)
        REFERENCES fasecampeonato (iden_fasecampeonato),

    CONSTRAINT fk_classificacao_campeonatoequipe
		FOREIGN KEY (iden_campeonatoequipe)
		REFERENCES campeonatoequipe (iden_campeonatoequipe),
		
	CONSTRAINT uk_classificacao_fase_campeonatoequipe
		UNIQUE (iden_fasecampeonato, iden_campeonatoequipe),

    CONSTRAINT chk_classificacao_posicao
        CHECK (posicao IS NULL OR posicao > 0),

    CONSTRAINT chk_classificacao_jogos
        CHECK (jogos >= 0),

    CONSTRAINT chk_classificacao_vitorias
        CHECK (vitorias >= 0),

    CONSTRAINT chk_classificacao_empates
        CHECK (empates >= 0),

    CONSTRAINT chk_classificacao_derrotas
        CHECK (derrotas >= 0),

    CONSTRAINT chk_classificacao_gols_pro
        CHECK (gols_pro >= 0),

    CONSTRAINT chk_classificacao_gols_contra
        CHECK (gols_contra >= 0),

    CONSTRAINT chk_classificacao_jogos_resultados
        CHECK (jogos = vitorias + empates + derrotas)
);

