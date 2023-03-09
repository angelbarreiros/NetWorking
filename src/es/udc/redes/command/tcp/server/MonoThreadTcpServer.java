package es.udc.redes.tutorial.tcp.server;

import java.net.*;
import java.io.*;

/**
 * MonoThread TCP echo server.
 */
public class MonoThreadTcpServer {

    public static void main(String[] argv)  {
        Socket socket=null;
        if (argv.length != 1) {
            System.err.println("Format: es.udc.redes.tutorial.tcp.server.MonoThreadTcpServer <port>");
            System.exit(-1);
        }
        try {
            ServerSocket server=new ServerSocket(Integer.parseInt(argv[0]));
            server.setSoTimeout(300000);
            // Create a server socket

            // Set a timeout of 300 secs

            while (true) {
                // Set the input channel
                socket=server.accept();
                BufferedReader sInput = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                // Set the output channel
                PrintWriter sOutput = new PrintWriter(socket.getOutputStream(), true);
                // Receive the message from the client
                String received = sInput.readLine();
                System.out.println("Server: Recieve " + received +
                        socket.getInetAddress().toString() +
                        ":" + socket.getPort());
                // Send message to the server

                // Sent the echo message to the client

                sOutput.println(received);
                // Close the streams
                sOutput.close();
                sInput.close();


            }
            // Uncomment next catch clause after implementing the logic
        } catch (SocketTimeoutException e) {
            System.err.println("Nothing received in 300 secs ");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {

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
