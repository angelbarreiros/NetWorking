package es.udc.redes.tutorial.tcp.server;
import java.net.*;
import java.io.*;

/** Thread that processes an echo server connection. */

public class ServerThread extends Thread {

  private final Socket socket;

  public ServerThread(Socket s) {
    this.socket=s;
  }

  public void run() {
    try {

      // Set the input channel
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

    } catch (SocketTimeoutException e) {
      System.err.println("Nothing received in 300 secs");
    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    } finally {
      try {
        this.socket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}