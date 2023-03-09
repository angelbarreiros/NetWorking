package es.udc.redes.tutorial.tcp.server;
import java.io.IOException;
import java.net.*;

/** Multithread TCP echo server. */

public class TcpServer {

    public static void main(String[] argv)  {
        Socket socket=null;
        if (argv.length != 1) {
            System.err.println("Format: es.udc.redes.tutorial.tcp.server.TcpServer <port>");
            System.exit(-1);
        }
        try {
            // Create a server socket
            ServerSocket server= new ServerSocket(Integer.parseInt(argv[0]));
            server.setSoTimeout(300000);

            // Set a timeout of 300 secs
            while (true) {
                socket=server.accept();
                // Wait for connections
                ServerThread hilo=new ServerThread(socket);
                hilo.start();

                // Create a ServerThread object, with the new connection as parameter
                // Initiate thread using the start() method
            }

        } catch (SocketTimeoutException e) {
            System.err.println("Nothing received in 300 secs");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally{
            try{
                if (socket!=null){
                    socket.close();
                }

            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
