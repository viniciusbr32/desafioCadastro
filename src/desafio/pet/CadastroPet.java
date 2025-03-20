package desafio.pet;

import desafio.file.CreateFileCadastro;
import desafio.tipos.SexoPet;
import desafio.tipos.TipoPet;

import java.util.Scanner;

public class CadastroPet {
    private static final Scanner scanner = new Scanner(System.in);

    public Pet cadastrarPet() {


        String nomeESobrenome = obterNomeESobrenome();
        TipoPet tipo = obterTipoPet();
        SexoPet sexo = obterSexoPet();
        String endereco = obterEndereco();
        double idade = obterIdade();
        double peso = obterPeso();
        String raca = obterRaca();


        return new Pet(nomeESobrenome, tipo, sexo, endereco, idade, peso, raca);
    }

    private String obterNomeESobrenome() {
        while (true) {
            System.out.println("Qual o nome e sobrenome do pet? ");
            String nomeESobrenome = scanner.nextLine();

            if (nomeESobrenome.isEmpty()) {
                return Pet.NAO_INFORMADO;

            }

            if (PetValidator.NomeValido(nomeESobrenome)) {
                return nomeESobrenome;

            }
            System.out.println("Nome inválido! Digite um nome válido com pelo menos um nome e um sobrenome, ambos compostos apenas por letras.");
        }
    }


    private TipoPet obterTipoPet() {
        while (true) {
            System.out.print("Qual o tipo do pet (Cachorro/Gato)? ");
            try {
                return TipoPet.valueOf(scanner.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Só aceitamos os valores Cachorro/Gato, por favor corrija");
            }
        }
    }


    private SexoPet obterSexoPet() {
        while (true) {
            System.out.print("Qual o sexo do pet ((Macho/Fêmea))? ");
            try {
                return SexoPet.valueOf(scanner.nextLine().toUpperCase());

            } catch (IllegalArgumentException e) {
                System.out.println("Só aceitamos os valores Macho/Fêmea, por favor corrige");
            }
        }
    }


    private String obterEndereco() {
        System.out.println("Vamo cadastrar o Endereço");
        String rua = obterRua();
        int numeroDaCasa = obterNumeroDaCasa();
        String cidade = obterCidade();
        String numero = numeroDaCasa == -1 ? Pet.NAO_INFORMADO : String.valueOf(numeroDaCasa);

        return rua + ", " + numero + ", " + cidade;

    }

    private String obterCidade() {
        while (true) {
            System.out.println("Qual a cidade ? ");
            String cidade = scanner.nextLine();

            if (!cidade.isEmpty() && PetValidator.Cidadevalida(cidade)) {
                return cidade;
            }

            System.out.println("Formato invalido, por favor digite algo valido");
        }
    }

    private int obterNumeroDaCasa() {
        while (true) {
            System.out.println("Qual o número da casa?");

            String numeroDigitado = scanner.nextLine().trim();


            if (numeroDigitado.isEmpty()) {
                return -1;
            }

            try {
                return Integer.parseInt(numeroDigitado);
            } catch (NumberFormatException e) {
                System.out.println("Só aceitamos números inteiros");
            }
        }
    }


    private String obterRua() {
        while (true) {
            System.out.println("Qual a rua ? ");
            String rua = scanner.nextLine();

            if (!rua.isEmpty() && !rua.equals(" ")) {
                return rua;
            }

            System.out.println("Digite o nome da rua");
        }
    }

    private double obterIdade() {
        while (true) {
            System.out.println("Qual a idade aproximada do pet ? Pode usar virgula ou ponto ");
            String idadeDigitada = scanner.nextLine().replace(",", ".");

            if (idadeDigitada.trim().isEmpty()) {
                return -1;
            }

            try {
                double idade = Double.parseDouble(idadeDigitada);
                if (idade < 1) {
                    return (double) Math.round(idade * 10) / 10;
                }
                return idade;

            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número válido.");

            }
        }
    }


    private double obterPeso() {
        while (true) {
            System.out.print("Qual o peso do pet (em kg)? ");
            String pesoDigitado = scanner.nextLine().replace(",", ".");

            if (pesoDigitado.trim().isEmpty()) {
                return -1;
            }

            try {
                double peso = Double.parseDouble(pesoDigitado);

                if (peso < 0.5 || peso > 60) {
                    throw new IllegalArgumentException("Por favor, digite um peso correspondente à raça do animal (entre 0.5kg e 60kg).");
                }
                return peso;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, digite um número válido.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private String obterRaca() {
        while (true) {
            System.out.print("Qual a raça do pet? ");
            String raca = scanner.nextLine();

            if (PetValidator.RacaValida(raca)) {
                return raca;
            }
            if (raca.isEmpty()) {
                return Pet.NAO_INFORMADO;

            }
            System.out.println("Por favor Digite um formato valido sem numeros e caracteres " +
                    "especiais");
        }
    }
}

