package desafio.file;

import desafio.pet.Pet;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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

    public static List<Path> buscarArquivos(String path, Scanner scanner) {
        Path dir = Paths.get(path);


        try {
            List<Path> arquivos = Files.list(dir).collect(Collectors.toList());

            if (arquivos.isEmpty()) {
                System.out.println("Nenhum arquivo encontrado.");
                return arquivos;
            }


            System.out.println("Arquivos no diretório:");
            for (int i = 0; i < arquivos.size(); i++) {
                System.out.println(i + " - " + arquivos.get(i).getFileName());
            }

            return arquivos;


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void deletarArquivos() {
        Scanner scanner = new Scanner(System.in);
        String path = "petsCadastrados";

        List<Path> arquivos = buscarArquivos(path, scanner);

        System.out.print("Digite o número do arquivo a excluir: ");
        int escolha = scanner.nextInt();

        if (escolha < 0 || escolha > arquivos.size()) {
            System.out.println("Indice invalido");
            return;
        }

        try {
            Path arquivoDeletar = arquivos.get(escolha);
            Files.delete(arquivoDeletar);
            System.out.println("Arquivo excluido com sucesso " + arquivoDeletar.getFileName());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        scanner.close();
    }

    public static void formatarArray(List<String[]> listaPets) {
        int numero = 1;

        for (String[] pet : listaPets) {
            String resultado = String.format("%d. %s - %s - %s - %s - %s - %s - %s",
                    numero++,
                    pet[0],  // Nome
                    pet[1],  // Tipo
                    pet[2],  // Sexo
                    pet[3],  // Endereço
                    pet[4],  // Idade
                    pet[5],  // Peso
                    pet[6]   // Raça
            );
            System.out.println(resultado);
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

    public static List<String[]> buscarPets() {
        File diretorio = new File("petscadastrados");
        List<String[]> listaPets = new ArrayList<>();


        if (!diretorio.exists()) {
            System.out.println("Diretorio não existe");
            return listaPets;
        }

        File[] arquivos = diretorio.listFiles();

        if (arquivos.length < 1) {
            System.out.println("Não existe arquivos dentro da pasta");
            return listaPets;
        }


        for (File arquivo : arquivos) {
            try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
                String linha;
                String nomePet = null, tipoPet = null, sexoPet = null, endereco = null, idade = null, peso = null, raca = null;

                while ((linha = br.readLine()) != null) {

                    if (linha.startsWith("1 - ")) {
                        nomePet = linha.substring(4);

                    } else if (linha.startsWith("2 - ")) {
                        tipoPet = linha.substring(4);

                    } else if (linha.startsWith("3 - ")) {
                        sexoPet = linha.substring(4);

                    } else if (linha.startsWith("4 - ")) {
                        endereco = linha.substring(4);


                    } else if (linha.startsWith("5 - ")) {
                        idade = linha.substring(4);


                    } else if (linha.startsWith("6 - ")) {
                        peso = linha.substring(4);


                    } else if (linha.startsWith("7 - ")) {
                        raca = linha.substring(4);

                    }
                }


                String[] resultado = {nomePet, tipoPet, sexoPet, endereco, idade, peso, raca};

                listaPets.add(resultado);

            } catch (IOException e) {
                System.out.println("Erro ao ler o arquivo " + arquivo.getName() + ": " + e.getMessage());
            }

        }
        return listaPets;
    }

    public static List<String[]> filterPets(String tipoPet, String valor1, String valor2) {
        List<String[]> listaPets = buscarPets();
        List<String[]> petsFiltrados = new ArrayList<>();

        for (String[] pet : listaPets) {
            if (pet[1] == null || !pet[1].equalsIgnoreCase(tipoPet)) {
                continue;
            }

            boolean atendeFiltro = true;

            if (valor1 != null && !valor1.isEmpty()) {
                boolean encontrouValor1 = false;
                for (String campo : pet) {
                    if (campo != null && campo.toLowerCase().contains(valor1.toLowerCase())) {
                        encontrouValor1 = true;
                        break;
                    }
                }
                if (!encontrouValor1) {
                    atendeFiltro = false;
                }
            }

            if (valor2 != null && !valor2.isEmpty()) {
                boolean encontrouValor2 = false;
                for (String campo : pet) {
                    if (campo != null && campo.toLowerCase().contains(valor2.toLowerCase())) {
                        encontrouValor2 = true;
                        break;
                    }
                }
                if (!encontrouValor2) {
                    atendeFiltro = false;
                }
            }

            if (atendeFiltro) {
                petsFiltrados.add(pet);
            }
        }
        return petsFiltrados;
    }


}
