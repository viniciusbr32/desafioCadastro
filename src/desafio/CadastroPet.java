package desafio;

import java.util.Scanner;

public class CadastroPet {
    public Pet cadastrarPet() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Qual o nome do pet? ");
        String nome = scanner.nextLine();

        System.out.print("Qual o so nome do pet? ");
        String sobrenome = scanner.nextLine();


        System.out.print("Qual o tipo do pet (Cachorro/Gato)? ");
        TipoPet tipo = TipoPet.valueOf(scanner.nextLine());


        System.out.print("Qual o sexo do pet (Macho/Fêmea)? ");
        SexoPet sexo = SexoPet.valueOf(scanner.nextLine());

        System.out.print("Qual o endereço ? ");
        String endereco = scanner.nextLine();


        System.out.print("Qual a idade do pet (em anos)? ");
        int idade = Integer.parseInt(scanner.nextLine());


        System.out.print("Qual o peso do pet (em kg)? ");
        double peso = Double.parseDouble(scanner.nextLine());


        System.out.print("Qual a raça do pet? ");
        String raca = scanner.nextLine();

        Pet pet = new Pet(nome, sobrenome, tipo, sexo, endereco, idade, peso, raca);

        return pet;
    }

}
