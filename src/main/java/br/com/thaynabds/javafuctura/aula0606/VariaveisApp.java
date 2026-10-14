package br.com.thaynabds.javafuctura.aula0606;

public final class VariaveisApp {

    private VariaveisApp() {
    }

    public static void main(String[] args) {
        double saldoUsuario = 4874.34;
        int idadeUsuario = 25;
        String nomeUsuario = "Antonio Henrique";
        char sexo = 'M';
        boolean ativo = true;
        double novoSaldo = saldoUsuario / 2.0;

        System.out.println(novoSaldo);
        System.out.println("Cliente: " + nomeUsuario
                + ", idade " + idadeUsuario
                + ", sexo " + sexo
                + ", ativo " + ativo);

        int numero1 = 10;
        int numero2 = 15;
        int numero3 = 25;
        int resultado = numero1 + numero2 + numero3;
        System.out.println("O resultado foi: " + resultado);

        double valorEmprestimo = 1000.0;
        double taxaDeJuros = 0.1;
        double valorTotal = CalculadoraEmprestimo.valorComJuros(valorEmprestimo, taxaDeJuros);
        System.out.println("Valor original: R$ " + valorEmprestimo);
        System.out.println("Valor total com juros: R$ " + valorTotal);
        System.out.println(CalculadoraEmprestimo.mensagem(valorTotal));
    }
}
