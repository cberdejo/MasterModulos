package amigos;

import java.io.IOException;

public class ClubManager {

    private String fEntrada;
    private String fSalida;
    private String delimitadores;
    private Club club;
    private boolean consola;

    ///Crea un `ClubManager` a partir de un `Club`
    /// @param club el club a gestionar
    public ClubManager(Club club) {
        this.club = club;
    }

    public ClubManager setConsola(boolean consola) {
        this.consola = consola;
        return this;
    }

    public ClubManager setSalida(String fSalida) {
        this.fSalida = fSalida;
        return this;
    }
    /// Establece de donde se obtienen los amigos del amigo invisible y los delimitadores para obtener los nombres del fichero
    /// @param fEntrada el fichero de entrada
    /// @param delimitadores el delimitador
    public ClubManager setEntrada(String fEntrada, String delimitadores) {
        this.delimitadores = delimitadores;
        this.fEntrada = fEntrada;
        return this;
    }

    /// Verifica que los datos que contiene el club son correctos, es decir:
    ///- Hay un fichero de entrada de datos (no es null).
    ///- Hay una salida, o bien a fichero o a consola (ambas pueden estar a la vez).
    ///@throws  AmigoException en caso de que algo no este bien
    private void verify(){
        if (fEntrada == null) {
            throw new AmigoException("No se ha especificado un fichero de entrada de datos");
        }
        if (fSalida == null && !consola) {
            throw new AmigoException("No se ha especificado un fichero de salida ni la consola para presentar los resultados");
        }


    }


    /// 1. Verifica que los datos del club manager son correctos
    /// 1. Lee el fichero de entrada
    /// 1. Empareja los socios
    /// 1. Imprime los resultados en el fichero de salida o en consola
    /// @trows  IOException en caso de que exista un error al leer el fichero
    public void build() throws IOException {
        verify();
        club.lee(fEntrada, delimitadores);
        club.hacerAmigos();

        if (consola) {
            System.out.println(club);
        } else {
            club.presentaAmigos(fSalida);

        }
    }
}
