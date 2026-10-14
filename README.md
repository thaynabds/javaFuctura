# javaFuctura

Exercícios de Java da Academia Java Full-Stack, Fuctura Tecnologia.

O repositório tinha só `.class` e um `.jar`. Quem abria o projeto não via o programa. Esta versão traz o código fonte das aulas, com regras separadas da leitura do teclado, e testes automatizados.

Java 21. O bytecode antigo está na versão 65 da JVM, que é a do Java 21.

## Aulas recuperadas

### 06/06/2026, variáveis e empréstimo

Classe original: `Aula1.App`.

Guarda saldo, idade, nome, sexo e um interruptor. Divide o saldo por 2, soma 10, 15 e 25, e calcula um empréstimo de R$ 1000 com juros de 10%. O total fica em R$ 1100, acima do limite de R$ 1000, então a resposta é que o empréstimo passou do limite.

```bash
java -cp target/classes br.com.thaynabds.javafuctura.aula0606.VariaveisApp
```

### 11/07/2026, Banco FUC

Classe original: `mains.App`.

Menu de consulta, saque, depósito e saída. Saldo inicial de R$ 1500. Saque maior que o saldo é recusado. Valor zero ou negativo também.

```bash
java -cp target/classes br.com.thaynabds.javafuctura.aula1107.BancoFuc
```

Use ponto como separador decimal, por exemplo `50.5`.

### 01/08/2026, loja de produtos

O arquivo `Aula4.01.08.2026.jar` não executava. O Eclipse gravou as classes com erro de compilação: o tipo `Produto` estava no pacote `models` e ao mesmo tempo dentro de `aula_4`. A loja foi reescrita com cadastro, listagem, atualização, remoção e compra com baixa de estoque.

```bash
java -cp target/classes br.com.thaynabds.javafuctura.aula0108.LojaApp
```

## Exercícios extras

- Boletim: média e situação (aprovado, recuperação, reprovado).
- Tabuada de 1 a 10.
- Maior nota, menor nota e média de um array.
- Conta corrente, com taxa mensal, e conta poupança, com rendimento.
- Agenda de contatos em memória.

## Testes

```bash
./mvnw test
```

Precisa de JDK 21. O Maven Wrapper baixa o Maven 3.9.9 na primeira execução.

## Autora

Thayná Batista da Silva  
[GitHub](https://github.com/thaynabds) · [LinkedIn](https://www.linkedin.com/in/thaynabds)

## Referências

- Versão do class file: [JVM Specification, Java SE 21, capítulo 4](https://docs.oracle.com/javase/specs/jvms/se21/html/jvms-4.html)
- JUnit 5: [User Guide](https://junit.org/junit5/docs/current/user-guide/)
- Maven: [Maven in 5 minutes](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
- GitHub Actions com Maven: [Building and testing Java with Maven](https://docs.github.com/en/actions/tutorials/build-and-test-code/java-with-maven)
