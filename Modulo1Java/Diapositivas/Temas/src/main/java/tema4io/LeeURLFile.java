package tema4io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class LeeURLFile {
    public static void main(String[] args) throws IOException {
        String urlPath = "https://maven.apache.org/guides/mini/guide-repository-ssl.html";
        URL url = new URL(urlPath);
        try (InputStream fs = url.openStream();
             InputStreamReader isr = new InputStreamReader(fs);
             BufferedReader br = new BufferedReader(isr)) {
            String linea = br.readLine();
            while (linea != null) {
                System.out.println(linea);
                linea = br.readLine();
            }
        }  catch (IOException e)  {
            System.out.println(e.getMessage());
            throw e;
        }
    }
}

