package desafio;

import java.io.File;
import java.util.Scanner;

public class Menu {
    public static void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        while (opcao < 1) {

            System.out.println("Escolha uma opção:");
            System.out.println("1. Cadastrar um novo pet");
            System.out.println("2. Alterar os dados do pet cadastrado");
            System.out.println("3. Deletar um pet cadastrado");
            System.out.println("4. Listar todos os pets cadastrados");
            System.out.println("5. Listar pets por algum critério (idade, nome, raça)");
            System.out.println("6. Sair");

            opcao = sc.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Cadastrar um novo pet...");
                    exibirMenuCadastro(sc);
                    break;
                case 2:
                    System.out.println("Alterar os dados do pet...");

                    break;
                case 3:
                    System.out.println("Deletar um pet...");

                    break;
                case 4:
                    System.out.println("Listar todos os pets cadastrados...");

                    break;
                case 5:
                    System.out.println("Listar pets por critério...");

                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        sc.close();
    }

    public static void exibirMenuCadastro(Scanner scanner) {
        File file = new File("formulario.txt");
        System.out.println();

        System.out.println("Você está no menu de cadastro de pets.");
        System.out.println("Preencha os dados solicitados para o cadastro.");

        System.out.println();

        TextFileReader.lerArquivo(file);

        System.out.println("Cadastro do pet concluído!");
        System.out.println("Deseja voltar para o menu principal? (S/N)");

        String resposta = scanner.next();

        if (resposta.equalsIgnoreCase("s")) {
            System.out.println("Voltando para o menu principal...");
            menuPrincipal();
            return;
        }
        System.out.println("Você pode continuar o cadastro ou sair.");
    }
}
