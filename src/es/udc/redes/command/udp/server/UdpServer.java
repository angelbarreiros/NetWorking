package es.udc.redes.tutorial.udp.server;

import java.net.*;

/**
 * Implements a UDP echo server.
 */
public class UdpServer {
    public static void main(String[] argv) {
        if (argv.length != 1) {
            System.err.println("Format: es.udc.redes.tutorial.udp.server.UdpServer <port_number>");
            System.exit(-1);
        }
        try (DatagramSocket serversocket = new DatagramSocket(Integer.parseInt(argv[0]))) {
            // Create a server socket
            // Set maximum timeout to 300 secs
            serversocket.setSoTimeout(30000);

            while (true) {
                byte[] array = new byte[1024];
                DatagramPacket packet = new DatagramPacket(array, array.length);

                // Prepare datagram for reception
                serversocket.receive(packet);
                DatagramPacket packet1 = new DatagramPacket(packet.getData(), packet.getOffset(), packet.getLength(), packet.getSocketAddress());
                // Receive the message
                serversocket.send(packet1);
                // Prepare datagram to send response
                System.out.println("CLIENT: Received "
                        + new String(packet1.getData(), 0, packet1.getLength())
                        + " from  " + packet1.getAddress().toString() + ":"
                        + packet1.getPort());
                // Send response

            }


        } catch (SocketTimeoutException e) {
            System.err.println("No requests received in 300 secs ");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
