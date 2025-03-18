package desafio.file;

import desafio.pet.Pet;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CreateFileCadastro {

    public static void CriarArquivoCadastro(Pet pet) {

        LocalDateTime date = LocalDateTime.now();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm"));
        String nomeArquivo = formattedDate + '-' + pet.getNomeESobrenome().replaceAll("\\s+", "").toUpperCase() + ".txt";



        File fileDiretorio = new File("petsCadastrados");
        if (!fileDiretorio.exists() && !fileDiretorio.mkdirs()) {
            System.err.println("Erro ao criar diretório!");
            return;
        }

        File fileArquivo = new File(fileDiretorio, nomeArquivo);

        try {
            if (!fileArquivo.exists()) {
                fileArquivo.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar arquivo!", e);
        }


        try (FileWriter fw = new FileWriter(fileArquivo, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write("1 - " + pet.getNomeESobrenome() + "\n");
            bw.write("2 - " + pet.getTipoPet() + System.lineSeparator());
            bw.write("3 - " + pet.getSexoPet() + System.lineSeparator());
            bw.write("4 - " + pet.getEndereco() + System.lineSeparator());
            bw.write("5 - " + pet.getIdade() + " anos" + System.lineSeparator());
            bw.write("6 - " + (int) pet.getPeso() + " kg" + System.lineSeparator());
            bw.write("7 - " + pet.getRaca() + System.lineSeparator());
            bw.flush();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever no arquivo!", e);
        }
    }
}
