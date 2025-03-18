package desafio.pet;

import desafio.tipos.SexoPet;
import desafio.tipos.TipoPet;

public class Pet {
    public static final String NAO_INFORMADO = "Não Informado";

    private String nomeESobrenome;
    private TipoPet tipoPet;
    private SexoPet sexoPet;
    private String endereco;
    private double idade;
    private double peso;
    private String raca;


    public Pet(String nomeESobrenome, TipoPet tipoPet, SexoPet sexoPet, String endereco, double idade,
               double peso, String raca) {
        this.nomeESobrenome = nomeESobrenome;
        this.tipoPet = tipoPet;
        this.sexoPet = sexoPet;
        this.endereco = endereco;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
    }

    public String getNomeESobrenome() {
        return nomeESobrenome;
    }

    public String getTipoPet() {
        return tipoPet.getDescricao();
    }

    public String getSexoPet() {
        return sexoPet.getSexo();
    }

    public String getEndereco() {
        return endereco;
    }

    public double getIdade() {
        return idade;
    }

    public double getPeso() {
        return peso;
    }

    public String getRaca() {
        return raca;
    }
}
