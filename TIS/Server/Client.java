package Server;

import java.io.*;
import java.net.*;

public class Client implements Runnable {
    private Socket sock;
    private IServerCallbackable instance;

    public Client(IServerCallbackable instance, Socket s) {
        this.instance = instance;
        this.sock = s;
    }

    private InputStream GetClienteReader(Socket client) {
        try {
            return client.getInputStream();
        } catch (Exception e) {
            System.out.println("Failed to get InputStream");
            return null;
        }
    }

    private OutputStream GetClientWritter(Socket client) {
        try {
            return client.getOutputStream();
        } catch (Exception e) {
            System.out.println("Failed to get OutputStream");
            return null;
        }
    }

    public void run() {
        //System.out.println("New Client connected!");

        // get stream flux
        InputStream reader = GetClienteReader(sock);
        //System.out.println("Reader getted!");
        OutputStream writter = GetClientWritter(sock);
        //System.out.println("Writter getted!");
        if (reader == null || writter == null)
            return;

        // sleep to get info
        try {
            Thread.sleep(250);
        } catch (InterruptedException e1) {
        }


        // request
        Request req = new Request(reader);
        req.ParseRequest();

        // response
        Response resp = new Response(writter, req);

        // call callback
        instance.OnReceiveRequest(req, resp);

   

        // close
        try 
        {
            writter.close();
            reader.close();
            sock.close();
        } 
        catch (Exception e) 
        {
            System.out.println("Failed to close stream and socket");
        }

    }
}