package desafio.pet;

public class PetValidator {
    public static boolean NomeValido(String nome) {
        return nome != null && nome.matches("^[a-zA-Z]+\\s([a-zA-Z]+)+$");
    }

    public static boolean Cidadevalida(String cidade) {
        return cidade != null && cidade.matches("^[a-zA-Zá-úÁ-Ú]+(?: [a-zA-Zá-úÁ-Ú]+)*$");
    }

    public static boolean RacaValida(String raca) {
        return raca != null && raca.matches("[a-zA-Zá-úÁ-Ú]+");

    }


}
