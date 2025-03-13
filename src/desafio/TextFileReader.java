package desafio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TextFileReader {
    public static void lerArquivo(File pathname) {
        try (BufferedReader br = new BufferedReader(new FileReader(pathname))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.out.println("Erro na leitura do arquivo" + e.getMessage());
        }
    }
}
