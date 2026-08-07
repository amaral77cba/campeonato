

--dados de TipoDisputa	
INSERT INTO tipodisputa (nome_tipodisputa) VALUES ('Pontos Corridos');
INSERT INTO tipodisputa (nome_tipodisputa) VALUES ('Mata-Mata');
INSERT INTO tipodisputa (nome_tipodisputa) VALUES ('Grupos');
INSERT INTO tipodisputa (nome_tipodisputa) VALUES ('Eliminatoria');
INSERT INTO tipodisputa (nome_tipodisputa) VALUES ('Hexagonal');
INSERT INTO tipodisputa (nome_tipodisputa) VALUES ('Quadrangular');

--dados de criteriodesempate
INSERT INTO criteriodesempate (nome_criteriodesempate, codigo_criteriodesempate, sentido_ordenacao, calculo_automatico) VALUES ('Maior número de vitórias', 'VITORIAS', 'DESC', TRUE);
INSERT INTO criteriodesempate (nome_criteriodesempate, codigo_criteriodesempate, sentido_ordenacao, calculo_automatico) VALUES ('Maior saldo de gols', 'SALDO_GOLS', 'DESC', TRUE);
INSERT INTO criteriodesempate (nome_criteriodesempate, codigo_criteriodesempate, sentido_ordenacao, calculo_automatico) VALUES ('Maior número de gols marcados', 'GOLS_PRO', 'DESC', TRUE);
INSERT INTO criteriodesempate (nome_criteriodesempate, codigo_criteriodesempate, sentido_ordenacao, calculo_automatico) VALUES ('Menor número de gols sofridos', 'GOLS_CONTRA', 'ASC', TRUE);
INSERT INTO criteriodesempate (nome_criteriodesempate, codigo_criteriodesempate, sentido_ordenacao, calculo_automatico) VALUES ('Confronto direto', 'CONFRONTO_DIRETO', 'DESC', TRUE);
INSERT INTO criteriodesempate (nome_criteriodesempate, codigo_criteriodesempate, sentido_ordenacao, calculo_automatico) VALUES ('Maior número de vitórias fora', 'VITORIAS_FORA', 'DESC', TRUE);
INSERT INTO criteriodesempate (nome_criteriodesempate, codigo_criteriodesempate, sentido_ordenacao, calculo_automatico) VALUES ('Maior número de gols fora', 'GOLS_FORA', 'DESC', TRUE);
INSERT INTO criteriodesempate (nome_criteriodesempate, codigo_criteriodesempate, sentido_ordenacao, calculo_automatico) VALUES ('Flair Play', 'FAIR_PLAY', 'ASC', TRUE);
INSERT INTO criteriodesempate (nome_criteriodesempate, codigo_criteriodesempate, sentido_ordenacao, calculo_automatico) VALUES ('Menor número de cartões amarelos', 'CARTOES_AMARELOS', 'ASC', TRUE);
INSERT INTO criteriodesempate (nome_criteriodesempate, codigo_criteriodesempate, sentido_ordenacao, calculo_automatico) VALUES ('Menor número de cartões vermelhos', 'CARTOES_VERMELHOS', 'ASC', TRUE);
INSERT INTO criteriodesempate (nome_criteriodesempate, codigo_criteriodesempate, sentido_ordenacao, calculo_automatico) VALUES ('Ranking técnico', 'RANKING', 'ASC', TRUE);
INSERT INTO criteriodesempate (nome_criteriodesempate, codigo_criteriodesempate, sentido_ordenacao, calculo_automatico) VALUES ('Sorteio', 'SORTEIO', 'ASC', FALSE);

--dados do campeonato
INSERT INTO campeonato (nome_campeonato, ano, data_inicio, data_fim) VALUES ('Brasileirão - Serie A', 2026, '01/01/2026', null);
INSERT INTO campeonato (nome_campeonato, ano, data_inicio, data_fim) VALUES ('Copa do Brasil', 2026, '01/03/2026', null);

--dados da cidade
INSERT INTO cidade (nome_cidade, uf) VALUES ('Rio de Janeiro', 'RJ');
INSERT INTO cidade (nome_cidade, uf) VALUES ('São Paulo', 'SP');
INSERT INTO cidade (nome_cidade, uf) VALUES ('Curitiba', 'PR');
INSERT INTO cidade (nome_cidade, uf) VALUES ('Salvador', 'BA');
INSERT INTO cidade (nome_cidade, uf) VALUES ('Bragança Paulista', 'SP');
INSERT INTO cidade (nome_cidade, uf) VALUES ('Belo Horizonte', 'MG');
INSERT INTO cidade (nome_cidade, uf) VALUES ('Mirassol', 'SP');
INSERT INTO cidade (nome_cidade, uf) VALUES ('Porto Alegre', 'RS');
INSERT INTO cidade (nome_cidade, uf) VALUES ('Belem', 'PA');
INSERT INTO cidade (nome_cidade, uf) VALUES ('Chapeco', 'SC');
INSERT INTO cidade (nome_cidade, uf) VALUES ('Santos', 'SP');
INSERT INTO cidade (nome_cidade, uf) VALUES ('Cuiabá', 'MT');

--dados de equipe
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Vasco da Gama', 'VAS', 1, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Botafogo', 'BOT', 1, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Fluminense', 'FLU', 1, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Flamengo', 'FLA', 1, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Palmeiras', 'PAL', 2, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('São Paulo', 'SAO', 2, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Corinthians', 'COR', 2, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Athetico-PR', '', 3, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Coritiba', 'CTB', 3, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Bahia', 'BAH', 4, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('EC Vioria', 'VIT', 4, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Red bull Bragantino', '', 5, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Cruzeiro', 'CRU', 6, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Atlético-MG', '', 6, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Mirassol', 'MIR', 7, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Internacional', 'INT', 8, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Gremio', 'GRE', 8, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Remo', 'REM', 9, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Chapecoense', 'CHA', 10, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Santos', 'SAN', 11, '');
INSERT INTO equipe (nome_equipe, sigla_equipe, iden_cidade, caminho_escudo) VALUES ('Cuiabá', 'CUI', 12, '');


--dados das equipes do campeonato
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 1);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 2);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 3);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 4);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 5);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 6);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 7);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 8);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 9);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 10);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 11);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 12);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 13);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 14);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 15);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 16);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 17);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 18);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 19);
INSERT INTO campeonatoequipe (iden_campeonato, iden_equipe) VALUES (1, 20);

--dados das fases do campeonato
INSERT INTO fasecampeonato (iden_campeonato, nome_fasecampeonato, ordem, qtdclassificam, possuiidavolta, iden_tipodisputa) VALUES (1, 'Fase Única', 1, null, TRUE, 1);
INSERT INTO fasecampeonato (iden_campeonato, nome_fasecampeonato, ordem, qtdclassificam, possuiidavolta, iden_tipodisputa) VALUES (2, 'Primeira Fase', 1, 40, FALSE, 2);
INSERT INTO fasecampeonato (iden_campeonato, nome_fasecampeonato, ordem, qtdclassificam, possuiidavolta, iden_tipodisputa) VALUES (2, 'Segunda Fase', 2, 20, FALSE, 2);
INSERT INTO fasecampeonato (iden_campeonato, nome_fasecampeonato, ordem, qtdclassificam, possuiidavolta, iden_tipodisputa) VALUES (2, 'Terceira Fase', 3, 16, TRUE, 2);
INSERT INTO fasecampeonato (iden_campeonato, nome_fasecampeonato, ordem, qtdclassificam, possuiidavolta, iden_tipodisputa) VALUES (2, 'Oitavas de Final', 4, 8, TRUE, 2);
INSERT INTO fasecampeonato (iden_campeonato, nome_fasecampeonato, ordem, qtdclassificam, possuiidavolta, iden_tipodisputa) VALUES (2, 'Quartas de Final', 5, 4, TRUE, 2);
INSERT INTO fasecampeonato (iden_campeonato, nome_fasecampeonato, ordem, qtdclassificam, possuiidavolta, iden_tipodisputa) VALUES (2, 'Semifinal', 6, 2, TRUE, 2);
INSERT INTO fasecampeonato (iden_campeonato, nome_fasecampeonato, ordem, qtdclassificam, possuiidavolta, iden_tipodisputa) VALUES (2, 'Final', 7, 1, TRUE, 2);

