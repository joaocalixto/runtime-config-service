# Feature Flags - Projeto base (aula)

## Objetivo
Este branch e um **projeto base** para a aula de refatoracao com Feature Flags.
O aluno vai implementar a solucao em duas etapas:
1) Versao **sem singleton** (ingenua).
2) Versao **com singleton** (refatorada).

## Estado atual do projeto
- Existe o arquivo `feature-flags.properties` com as chaves.
- Existe uma classe utilitaria para leitura do arquivo:
  - `util.FeatureFlagFileReader`
- O `Application` ja executa um fluxo simples de teste.
- As classes de servico e loader estao **intencionamente incompletas**.

## Requisitos do exercicio (o que fazer)
### Parte 1 — Versao ingenua (sem singleton)
- Implementar `config.FeatureFlagLoader`:
  - deve ler `feature-flags.properties` usando `FeatureFlagFileReader`;
  - cada nova instancia deve ler o arquivo novamente;
  - manter as flags em `Map<String, Boolean>`.
- Implementar `service.PricingService`:
  - criar sua propria instancia de `FeatureFlagLoader`;
  - usar as flags para ajustar o calculo de preco.
- Implementar `service.OrderService`:
  - criar sua propria instancia de `FeatureFlagLoader`;
  - usar as flags para controlar logs (ex.: verbose, auditoria).
- Os logs devem mostrar varias leituras e varias instancias.

### Parte 2 — Versao singleton (refatorada)
- Criar novos pacotes `config.singleton` e `service.singleton`.
- Implementar `FeatureFlagRegistry` como singleton lazy e thread-safe.
- Ler o arquivo **apenas uma vez** e compartilhar o estado.
- Criar `PricingService` e `OrderService` nessa nova estrutura.
- Trocar o `Application` para usar a versao singleton na etapa final.

## Como rodar
```
mvn -q exec:java -Dexec.mainClass="app.Application"
```

## Dica de comparacao
No fim da aula, compare os logs:
- **Ingenua:** varias leituras do arquivo e muitas instancias.
- **Singleton:** uma leitura do arquivo e uma unica instancia compartilhada.
