package desafio.file;

import desafio.pet.Pet;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class PetArquivo {

    public static void lerArquivo(String pathname) throws IOException {
        File arquivo = new File(pathname);

        if (!arquivo.exists()) {
            throw new IOException("O arquivo não foi encontrado " + pathname);
        }

        try (BufferedReader br = new BufferedReader(new FileReader(pathname))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro na leitura do arquivo" + e.getMessage());
        }
    }

    public static void criarCadastroPet(Pet pet) {
        LocalDateTime date = LocalDateTime.now();
        String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm"));
        String nomeArquivo = formattedDate + '-' + pet.getNomeESobrenome().replaceAll("\\s+", "").toUpperCase() + ".txt";


        File fileDiretorio = new File("petsCadastrados");
        if (!fileDiretorio.exists() && !fileDiretorio.mkdirs()) {
            System.err.println("Erro ao criar diretório!");
            return;
        }

        File fileArquivo = new File(fileDiretorio, nomeArquivo);

        escreverArquivo(fileArquivo, pet);

    }

    private static String formatarIdade(double idade) {
        if (idade == -1) return Pet.NAO_INFORMADO;
        return idade < 1 ? String.format("%.1f", idade) + " anos" : (int) idade + " anos";
    }

    private static String formatarPeso(double peso) {
        if (peso == -1) return Pet.NAO_INFORMADO;
        return peso < 1 ? String.format("%.1f", peso) + " kg" : (int) peso + " kg";
    }

    private static void escreverArquivo(File arquivo, Pet pet) {
        try (FileWriter fw = new FileWriter(arquivo, true); BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write("1 - " + pet.getNomeESobrenome() + "\n");
            bw.write("2 - " + pet.getTipoPet() + System.lineSeparator());
            bw.write("3 - " + pet.getSexoPet() + System.lineSeparator());
            bw.write("4 - " + pet.getEndereco() + System.lineSeparator());
            bw.write("5 - " + formatarIdade(pet.getIdade()) + System.lineSeparator());
            bw.write("6 - " + formatarPeso(pet.getPeso()) + System.lineSeparator());
            bw.write("7 - " + pet.getRaca() + System.lineSeparator());
            bw.flush();

        } catch (IOException e) {
            throw new RuntimeException("Erro ao escrever no arquivo!", e);
        }
    }

    public static void buscarPets() {
        File diretorio = new File("petscadastrados");

        String criterio = "1 - thor Moreira";

        if (!diretorio.exists()) {
            System.out.println("Diretorio não existe");
            return;
        }

        File[] arquivos = diretorio.listFiles();

        if (arquivos.length < 1) {
            System.out.println("Não existe arquivos dentro da pasta");
            return;
        }

        for (File arquivo : arquivos) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                String nomePet = null, tipoPet = null, sexoPet = null, endereco = null, idade = null, peso = null, raca = null;

                while ((linha = br.readLine()) != null) {
                    linha = linha.trim();

                    if (linha.startsWith("1 - ")) {
                        nomePet = linha.substring(3);


                    } else if (linha.startsWith("2 - ")) {
                        tipoPet = linha.substring(3);


                    } else if (linha.startsWith("3 - ")) {
                        sexoPet = linha.substring(3);


                    } else if (linha.startsWith("4 - ")) {
                        endereco = linha.substring(3);


                    } else if (linha.startsWith("5 - ")) {
                        idade = linha.substring(3);


                    } else if (linha.startsWith("6 - ")) {
                        peso = linha.substring(3);


                    } else if (linha.startsWith("7 - ")) {
                        raca = linha.substring(3);

                    }
                }
                String resultado = String.format("%s - %s - %s - %s - %s - %s - %s",
                        nomePet, tipoPet, sexoPet, endereco, idade, peso, raca);
                System.out.println(resultado);

            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo " + arquivo.getName() + ": " + e.getMessage());
            }

        }

    }

}
