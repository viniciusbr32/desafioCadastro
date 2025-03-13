package desafio;

public enum TipoPet {
    CACHORRO(1, "Cachorro"),
    GATO(2, "Gato");

    private final int codigo;
    private final String descricao;

    TipoPet(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }
}
