# Feature Flags - Projeto base (aula)

## Objetivo
Este branch e um **projeto base** para a aula de refatoracao com Feature Flags.
O aluno deve:
1) Implementar primeiro a versao **sem singleton** (ingenua).
2) Depois refatorar para **singleton**.

## O que ja existe
- `feature-flags.properties` com as chaves.
- Uma classe utilitaria para ler o arquivo:
  - `util.FeatureFlagFileReader`
- Um `Application` simples para testar o fluxo.

## O que falta (para o aluno implementar)
- `config.FeatureFlagLoader` (versao ingênua):
  - cada servico cria sua propria instancia
  - cada instancia le o arquivo
- `service.PricingService` e `service.OrderService`:
  - usar o loader
  - aplicar as flags no comportamento

Depois, criar a versao singleton em novos pacotes (ex.: `config.singleton` e `service.singleton`).

## Metodo incomum: `Collections.unmodifiableMap(...)`
Voce pode ver esse metodo em exemplos de singleton.
Em linguagem simples:
- Ele cria um mapa **somente leitura**.
- Evita que alguem altere as flags por acidente.
- Ajuda a manter o estado consistente quando varias classes usam o mesmo mapa.

Por isso ele e util na etapa de singleton.

## Como rodar
```
mvn -q exec:java -Dexec.mainClass="app.Application"
```

## Dica de comparacao
No fim da aula, compare os logs da versao ingênua (muitas leituras do arquivo)
com os logs da versao singleton (uma leitura apenas).
