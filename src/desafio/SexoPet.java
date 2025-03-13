package desafio;

public enum SexoPet {
    MACHO(1, "Macho"),
    FEMEA(2, "Femea");

    private final int codigo;
    private final String sexo;

    SexoPet(int codigo, String sexo) {
        this.codigo = codigo;
        this.sexo = sexo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getSexo() {
        return sexo;
    }
}
