package alturas;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


import java.util.List;
/// @Author: Christian Berdejo
/// @Version 1.0
public class Mundo {
    private List<Pais> paises;

    public Mundo(){}

    public List<Pais> getPaises() {
        return paises;
    }

    /// Lee el fichero y crea la lista de paises
    /// @param file fichero con la lista de paises
    /// @throws  IOException
    public void leePaises(String file) throws IOException {
        Path fichero = Path.of(file);
        for (String linea : Files.readAllLines(fichero)){
            //TO DO
        };
    }
}
