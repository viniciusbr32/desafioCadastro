package desafio.pet;

import desafio.tipos.SexoPet;
import desafio.tipos.TipoPet;

import java.util.Scanner;

public class CadastroPet {
    public Pet cadastrarPet() {
        Scanner scanner = new Scanner(System.in);

        String nomeESobrenome;
        TipoPet tipo;
        SexoPet sexo;
        String rua;
        int numeroDaCasa;
        String cidade;
        String endereco;
        double idade;
        double peso;
        String raca;

        while (true) {
            System.out.print("Qual o nome e sobrenome do pet? ");
            nomeESobrenome = scanner.nextLine();

            if (nomeESobrenome.isEmpty()) {
                nomeESobrenome = Pet.NAO_INFORMADO;
                break;
            }

            if (PetValidator.NomeValido(nomeESobrenome)) {
                break;
            }
            System.out.println("Nome inválido! Digite um nome válido com pelo menos um nome e um sobrenome, ambos compostos apenas por letras.");
        }


        while (true) {
            System.out.print("Qual o tipo do pet (Cachorro/Gato)? ");
            try {
                tipo = TipoPet.valueOf(scanner.nextLine().toUpperCase());

                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Só aceitamos os valores Cachorro/Gato, por favor corrige");
            }
        }


        while (true) {
            System.out.print("Qual o sexo do pet ((Macho/Fêmea))? ");
            try {
                sexo = SexoPet.valueOf(scanner.nextLine().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Só aceitamos os valores Macho/Fêmea, por favor corrige");
            }
        }

        System.out.println("Vamo cadastrar o Endereço");

        while (true) {
            System.out.println("Qual o numero da casa ?");
            if (!scanner.hasNextInt()) {
                System.out.println("Só aceitamos Numeros inteiros");
                scanner.nextLine();
                continue;
            }
            numeroDaCasa = scanner.nextInt();
            scanner.nextLine();
            break;
        }

        while (true) {
            System.out.println("Qual a cidade ? ");
            cidade = scanner.nextLine();

            if (cidade.isEmpty()) {
                System.out.println("Digite um nome de cidade");
                continue;
            }

            if (!PetValidator.Cidadevalida(cidade)) {
                System.out.println("Formato invalido, por favor digite algo valido");
                continue;
            }
            break;
        }

        while (true) {
            System.out.println("Qual a rua ? ");
            rua = scanner.nextLine();

            if (rua.isEmpty() || rua.equals(" ")) {
                System.out.println("Digite o nome da rua");
                continue;
            }
            break;
        }


        while (true) {
            System.out.println("Qual a idade aproximada do pet ? Pode usar virgula ou ponto ");
            String idadeDigitada = scanner.nextLine();

            idadeDigitada = idadeDigitada.replace(",", ".");

            try {
                idade = Double.parseDouble(idadeDigitada);
                if (idade < 1) {
                    idade = (double) Math.round(idade * 10) / 10;
                }
                System.out.println(idade);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número válido.");
                System.out.println(idadeDigitada);
            }
        }


        while (true) {
            System.out.print("Qual o peso do pet (em kg)? ");
            String pesoDigitado = scanner.nextLine();

            pesoDigitado = pesoDigitado.replace(",", ".");

            try {
                peso = Double.parseDouble(pesoDigitado);

                if (peso < 0.5 || peso > 60) {
                    throw new IllegalArgumentException("Por favor, digite um peso correspondente à raça do animal (entre 0.5kg e 60kg).");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Por favor, digite um número válido.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }


        while (true) {
            System.out.print("Qual a raça do pet? ");
            raca = scanner.nextLine();
            if (PetValidator.RacaValida(raca)) {
                break;
            }
            System.out.println("Por favor Digite um formato valido sem numeros e caracteres " +
                    "especiais");

            if (raca.isEmpty()) {
                raca = Pet.NAO_INFORMADO;
                break;
            }
        }

        endereco = rua + ", " + numeroDaCasa + ", " + cidade;
        
        Pet pet = new Pet(nomeESobrenome, tipo, sexo, endereco, idade, peso, raca);

        return pet;
    }

}
