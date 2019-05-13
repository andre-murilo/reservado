import java.io.*;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Client implements Runnable
{
    private Socket sock;

    public Client(Socket s)
    {
        this.sock = s;
    }

    private BufferedReader GetClienteReader(Socket client)
    {
        try {
            return new BufferedReader(new InputStreamReader(client.getInputStream()));   
        } catch (Exception e) {
            return null;
        }
    }

    private BufferedWriter GetClientWritter(Socket client)
    {
        try {
            return new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (Exception e) {
            return null;
        }
    }

   

    @Override
    public void run() 
    {
        //System.out.println("New Client connected!");

        // get stream flux
        BufferedReader reader  = GetClienteReader(sock);
        BufferedWriter writter = GetClientWritter(sock);
        if(reader == null || writter == null) return;

        // request
        Request req = new Request(reader);
        Map<String, String> params = req.GetParams();
        //String path = params.get("path");
        //System.out.println("Browser request: " + path);

        // response
        Response resp = new Response(writter, req);
        resp.WriteAll();

        // close
        try 
        {
            writter.close();
            reader.close();
            sock.close();
        } 
        catch (Exception e) 
        {

        }

    }
}