package desafio.tipos;

public enum SexoPet {
    MACHO(1, "Macho"),
    FEMEA(2, "Femea");

    private final int codigo;
    private final String sexoDescricao;

    SexoPet(int codigo, String sexoDescricao) {
        this.codigo = codigo;
        this.sexoDescricao = sexoDescricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getSexo() {
        return sexoDescricao;
    }
}
