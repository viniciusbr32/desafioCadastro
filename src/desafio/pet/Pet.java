package desafio.pet;

import desafio.tipos.SexoPet;
import desafio.tipos.TipoPet;

public class Pet {
    private String nome;
    private String sobrenome;
    private TipoPet tipoPet;
    private SexoPet sexoPet;
    private String endereco;
    private int idade;
    private double peso;
    private String raca;


    public Pet(String nome, String sobrenome, TipoPet tipoPet, SexoPet sexoPet, String endereco,
               int idade, double peso, String raca) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.tipoPet = tipoPet;
        this.sexoPet = sexoPet;
        this.endereco = endereco;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
    }
}
