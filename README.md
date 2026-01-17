# Runtime Config Service

## Objetivo da refatoracao
Este projeto tem duas versoes do mesmo comportamento:
- **Ingenua (naive)**: cada servico cria seu proprio loader e le o arquivo toda hora.
- **Refatorada**: um unico registro de flags e compartilhado por toda a aplicacao.

A diferenca fica clara nos logs: na versao ingenua aparecem varias criacoes e varias leituras do arquivo. Na refatorada, o arquivo e lido uma vez so e o mesmo registro e reutilizado.

## O que mudou na refatoracao
- Foram criadas classes novas em `config/singleton` e `service/singleton`.
- A aplicacao passou a usar o registro unico de flags (singleton).
- O codigo antigo foi mantido para comparacao.

## Por que isso melhora
- Menos leitura de arquivo.
- Menos objetos criados.
- Estado consistente entre servicos.
- Logs mais limpos e previsiveis.

## Metodos incomuns (explicacao simples)
### `Collections.unmodifiableMap(...)`
Esse metodo cria uma visao **somente leitura** do mapa.
- Evita que alguem altere as flags por acidente.
- Deixa claro que as flags sao carregadas uma vez e depois ficam fixas.
- Ajuda a manter o estado consistente entre os servicos.

Usamos isso porque o objetivo e ter um registro unico e estavel. Se o mapa pudesse ser alterado, o ganho do singleton ficaria confuso.

## Como rodar
### Versao ingenua (main branch)
```
mvn -q exec:java -Dexec.mainClass="app.Application"
```

### Versao refatorada (branch refactor/singleton-feature-flags)
```
mvn -q exec:java -Dexec.mainClass="app.Application"
```

## O que observar nos logs
- **Ingenua:** muitas linhas de "Creating FeatureFlagLoader" e "Loading feature flags".
- **Refatorada:** apenas uma criacao do registro e uma leitura do arquivo.
