package com.ogawalucas.sistemarquivos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReader {

    public void read(Path path) {
        // TODO ler o conteudo do arquivo e mostrar no console
        try {
            Files.readAllLines(path)
                .forEach(System.out::println);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
