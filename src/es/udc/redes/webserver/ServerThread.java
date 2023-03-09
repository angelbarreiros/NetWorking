package es.udc.redes.webserver;

import java.net.*;
import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class ServerThread extends Thread {

    private final Socket socket;


    public ServerThread(Socket s) {
        this.socket=s;
    }
    private void error400( PrintStream out,String type,String f) throws IOException, ParseException {
        File file = new File("/home/angel/java-labs-angelbarreiros/p1-files/"+f );
        long time1 = file.lastModified();
        DateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        String last=sdf.format(time1);
        String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(last);
        java.util.Date utilDate = new java.util.Date();
        out.println("HTTP/1.0 " + "400 Bad Request");
        out.println("Date: "+utilDate);
        out.println("WebServer_267");
        if (type.equals("otro")){
            out.println("Content-Length:"+0);
        }
        else{
            out.println("Content-Length:"+file.length());
        }
        out.println("Content-Type:text/" + "html");
        out.println("Last-Modified:"+date);
        out.println();

        if (type.equals("GET")){
            try (FileInputStream input = new FileInputStream(f)) {
                int c;
                while ((c = input.read()) != -1) {
                    out.write(c);
                }
            }
        }

    }
    private void error404(PrintStream out,String funcion,String f) throws IOException, ParseException {
        File file = new File("/home/angel/java-labs-angelbarreiros/p1-files/"+f);
        long time1 = file.lastModified();
        DateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        String last=sdf.format(time1);
        String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(last);
        java.util.Date utilDate = new java.util.Date();
        out.println("HTTP/1.0 " + "404 Not Found");
        out.println("Date: "+utilDate);
        out.println("WebServer_267");
        out.println("Content-Length:"+0);
        out.println("Content-Type:text/html");



        out.println("Last-Modified:"+date);
        out.println();

        if (funcion.equals("GET")){
            try (FileInputStream input = new FileInputStream(f)) {
                int c;
                while ((c = input.read()) != -1) {
                    out.write(c);
                }
            }
        }
    }
    private void get(String strar, PrintStream out, String type) throws ParseException, IOException {

        File file = new File("/home/angel/java-labs-angelbarreiros/p1-files/"+strar);
        long time1 = file.lastModified();
        DateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        String last=sdf.format(time1);
        String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(last);
        java.util.Date utilDate = new java.util.Date();


        System.out.println("Server recieve " + strar);
        if (file.length()!=0){
            out.println("HTTP/1.0 " + "200 OK");
            out.println("Date: "+utilDate);
            out.println("WebServer_267");
            out.println("Content-Length:"+file.length());
            switch (type) {
                case "html" -> out.println("Content-Type:text/" + type);
                case "txt"->out.println("Content-Type:text/plain");
                case "png", "gif" -> out.println("Content-Type:image/" + type);
                case "actet_stream" -> out.println("Content-Type:application/" + type);
                case default ->out.println("Content-Type:text/"+type);
            }
            out.println("Last-Modified:"+date);
            out.println();


            try (FileInputStream input = new FileInputStream(strar)) {
                int c;
                while ((c = input.read()) != -1) {
                    out.write(c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            error404(out,type,"error404.html");
        }
    }
    private void modiget(String strar, PrintStream out, String type,Date modified) throws ParseException, IOException{
        File file = new File("/home/angel/java-labs-angelbarreiros/p1-files/"+strar);
        long time1 = file.lastModified();
        DateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        String last=sdf.format(time1);
        String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(last);
        java.util.Date utilDate = new java.util.Date();


        System.out.println("Server recieve " + strar);
        if (file.length()!=0){
            if (!date.before(modified)){
                out.println("HTTP/1.0 " + "304 Not Modified");
            }else{
                out.println("HTTP/1.0 " + "200 OK");
            }
            out.println("Date: "+utilDate);
            out.println("WebServer_267");
            out.println("Content-Length:"+file.length());
            switch (type) {
                case "html" -> out.println("Content-Type:text/" + type);
                case "txt"->out.println("Content-Type:text/plain");
                case "png", "gif" -> out.println("Content-Type:image/" + type);
                case "actet_stream" -> out.println("Content-Type:application/" + type);
                case default ->out.println("Content-Type:text/"+type);
            }
            out.println("Last-Modified:"+date);
            out.println();


            try (FileInputStream input = new FileInputStream(strar)) {
                int c;
                while ((c = input.read()) != -1) {
                    out.write(c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            error404(out,type,"error404.html");
        }
    }
    private void header(String strar,PrintStream out,String type) throws ParseException, IOException {
        File file = new File("/home/angel/java-labs-angelbarreiros/p1-files/"+strar);
        long time1 = file.lastModified();
        DateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
        String last=sdf.format(time1);
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = simpleDateFormat.parse(last);
        java.util.Date utilDate = new java.util.Date();


        System.out.println("Server recieve " + strar);
        if (file.length()!=0){
            out.println("HTTP/1.0 " + "200 OK");
            out.println("Date: "+utilDate);
            out.println("WebServer_267");
            out.println("Content-Length:"+file.length());
            switch (type) {
                case "html" -> out.println("Content-Type:text/" + type);
                case "txt"->out.println("Content-Type:text/plain");
                case "png", "gif" -> out.println("Content-Type:image/" + type);
                case "actet_stream" -> out.println("Content-Type:application/" + type);
                case default ->out.println("Content-Type:text/"+type);
            }
            out.println("Last-Modified:"+date);
            out.println();
        }
        else {
            error404(out,type,"error404.html");
        }
    }
    public void run() {
        try {
            BufferedReader sInput = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            PrintStream sOutput = new PrintStream(socket.getOutputStream(), true);
            String received = sInput.readLine();
            sInput.readLine();sInput.readLine();
            String received4 = sInput.readLine();
            String modified;
            System.out.println("Server: Recieve " + received +
                    socket.getInetAddress().toString() +
                    ":" + socket.getPort());
            String[] partes = received.split("\\s+");
            if (partes.length > 1) {
                partes[1] = partes[1].substring(1);
                String strar = partes[1];
                String[] partes2 = strar.split("\\.+");
                if (partes2.length > 1) {
                    String type = partes2[1];
                    if (received4.length()>1){
                        modified=received4.substring(19,47);
                        DateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy",Locale.ENGLISH);
                        Date dataFormateada = sdf.parse(modified.trim());
                        modiget(strar,sOutput,type,dataFormateada);
                    }
                    if (partes[0].equals("GET")) {
                        get(strar, sOutput, type);
                    } else if (partes[0].equals("HEAD")) {
                        header(strar, sOutput, type);
                    } else {
                        error400(sOutput, "otro", "error400.html");
                    }

                } else {
                    error404(sOutput, "GET", "error404.html");


                }
            } else {
                if (partes[0].equals("HEAD")) {
                    error400(sOutput, "HEAD", "error400.html");
                } else if (partes[0].equals("GET")) {
                    error400(sOutput, "GET", "error400.html");
                }
            }

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
