package desafio.menu;


import desafio.file.PetArquivo;
import desafio.pet.CadastroPet;
import desafio.pet.Pet;
import desafio.tipos.TipoPet;


import java.io.IOException;
import java.util.List;
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
                    PetArquivo.deletarArquivos();
                    break;
                case 4:
                    System.out.println("Listar todos os pets cadastrados...");
                    PetArquivo.formatarArray(PetArquivo.buscarPets());
                    break;
                case 5:
                    System.out.println("Listar pets por critério...");
                    Menu.exibirMenuFiltrarPets();
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

        try {
            PetArquivo.lerArquivo("formulario.txt");
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return;
        }

        CadastroPet cadastroPet = new CadastroPet();
        System.out.println();

        System.out.println("Você está no menu de cadastro de pets.");
        System.out.println("Preencha os dados solicitados para o cadastro.");

        System.out.println();

        Pet novoPet = cadastroPet.cadastrarPet();
        PetArquivo.criarCadastroPet(novoPet);


        System.out.println("Cadastro do pet concluído!");
        System.out.println("Deseja voltar para o menu principal? (S/N)");

        String resposta = scanner.next();

        if (resposta.equalsIgnoreCase("s")) {
            System.out.println("Voltando para o menu principal...");
            menuPrincipal();
            return;
        }
        System.out.println("Saindo...");

        scanner.close();
    }


    public static void exibirMenuFiltrarPets() {
        Scanner scanner = new Scanner(System.in);
        String tipoPet;
        String valor1 = null;
        String valor2 = null;

        while (true) {
            System.out.println("Escolha o tipo de pet que deseja buscar:");
            System.out.println("1 - Cachorro");
            System.out.println("2 - Gato");

            if (scanner.hasNextInt()) {
                int opcao = scanner.nextInt();
                scanner.nextLine();

                if (opcao == 1) {
                    tipoPet = TipoPet.CACHORRO.getDescricao();
                    break;
                } else if (opcao == 2) {
                    tipoPet = TipoPet.GATO.getDescricao();
                    break;
                }
            } else {
                scanner.next();
                System.out.println("Opção inválida! Escolha 1 para Cachorro ou 2 para Gato.");
            }
        }

        while (true) {
            System.out.println("Escolha 1 ou 2 critérios para refinar a busca:");
            System.out.println("1 - Nome ou Sobrenome");
            System.out.println("2 - Idade");
            System.out.println("3 - sexo");
            System.out.println("4 - Peso");
            System.out.println("5 - Raça");
            System.out.println("6 - Endereço");

            int criterio1;
            if (scanner.hasNextInt()) {
                criterio1 = scanner.nextInt();
                scanner.nextLine();

                if (criterio1 < 1 || criterio1 > 5) {
                    System.out.println(" Opção inválida. Escolha um número entre 1 e 5.");
                    continue;
                }

                System.out.print("Digite o valor para esse critério: ");
                valor1 = scanner.nextLine();

                System.out.println("Deseja adicionar mais um critério?");
                System.out.println("1 - Sim");
                System.out.println("2 - Não");
                System.out.print("Opção: ");
                int opcao = scanner.nextInt();
                scanner.nextLine();

                if (opcao == 1) {
                    int criterio2;
                    while (true) {
                        System.out.print("Escolha o segundo critério: ");
                        if (scanner.hasNextInt()) {
                            criterio2 = scanner.nextInt();
                            scanner.nextLine();

                            if (criterio2 < 1 || criterio2 > 6 || criterio2 == criterio1) {
                                System.out.println("Opção inválida! Escolha um critério diferente do primeiro.");
                                continue;
                            }
                            break;
                        } else {
                            scanner.next();
                            System.out.println("Opção inválida! Escolha um número entre 1 e 6.");
                        }
                    }

                    System.out.print("Digite o valor para esse critério: ");
                    valor2 = scanner.nextLine();
                }

                break;
            } else {
                scanner.next();
                System.out.println("Opção inválida! Escolha um número válido.");
            }
        }

        List<String[]> petsFiltrados = PetArquivo.filterPets(tipoPet, valor1, valor2);

        if (petsFiltrados.isEmpty()) {
            System.out.println("Nenhum pet encontrado com os critérios informados.");
        } else {
            System.out.println("Pets encontrados:");
            int contador = 1;
            for (String[] pet : petsFiltrados) {
                System.out.printf("%d. %s - %s - %s - %s - %s - %s - %s%n",
                        contador++, pet[0], pet[1], pet[2], pet[3], pet[4], pet[5], pet[6]);
            }
        }
    }

}
