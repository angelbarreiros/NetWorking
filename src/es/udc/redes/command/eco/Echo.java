package es.udc.redes.tutorial.copy;
import java.io.*;
public class Echo {

    public static void main(String[] args) throws IOException {
        try (FileInputStream input = new FileInputStream(args[0]); FileOutputStream output = new FileOutputStream(args[1])) {
            int c;
            while ((c = input.read()) != -1) {
                output.write(c);
            }
        }
    }

    
}
