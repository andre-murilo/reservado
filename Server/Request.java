package Server;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;
import java.util.*;
import java.util.AbstractMap.SimpleImmutableEntry;

import org.omg.CORBA.Environment;

public class Request
{
    private InputStream reader;
    private Map<String, String> params;
    private String method;
    private HashMap<String, String> postParams;

    public Request(InputStream reader)
    {
        this.reader = reader;
        this.params = new HashMap<String, String>();
    }



    private byte[] getBytesFromInputStream(InputStream is) throws IOException {
        byte[] buffer = new byte[reader.available()];
        reader.read(buffer);
        return buffer;
    }
    
    

    public void ParseRequest()
    {
        //System.out.println("Parsing request...");

        try 
        {
            byte[] data = getBytesFromInputStream(reader);
            //System.out.println("Bytes getted!");

            String str = new String(data);

            String[] lines = str.split("\\r?\\n");

            // parse line by line
            for (String l : lines) {
                ParseLine(l);
            }
        } 
        catch (Exception e) 
        {
            System.out.println("Failed to get data!");
            return;
        }
    }

    private Boolean nextIsValue = false;
    private void ParseLine(String line)
    {

        if(line.equals(""))
        {
            System.out.println("Achou!");
            nextIsValue = true;
            return;
        }

        if(nextIsValue) {
            // parse post data
            postParams = ParsePostData(line);
            return;
        }


        String[] splitted = line.split(" ");

        

        if(splitted.length < 2) return;

        switch(splitted[0])
        {
            case "GET":
                String path = splitted[1];
                String httpVer = splitted[2];

                params.put("path", path);
                params.put("http_ver", httpVer);

                this.method = "GET";
            break;

            case "POST":
                String queryPath = splitted[1];
                params.put("path", queryPath);
                params.put("queryPath", queryPath);
                //System.out.println(queryPath);

                this.method = "POST";
            break;

            case "Host:":
                String host = splitted[1];

                params.put("host", host);
            break;
            case "User-Agent:":
            break;
            case "Accept-Language:":
            break;
            case "Accept-Encoding:":
            break;
            case "Connection":
            break;
            case "Upgrade-Insecure-Requests:":
            break;
            case "Pragma":
            break;
            case "Cache-Control":
            break;
        }


        //System.out.println(line);
    }
    
    private HashMap<String, String> ParsePostData(String noFormated)
    {
        System.out.println(noFormated);

        String[] fields = noFormated.split("&");
        String[] kv = null;

        HashMap<String, String> things = new HashMap<String, String>();


        for (int i = 0; i < fields.length; ++i)
        {
            kv = fields[i].split("=");
            if (kv.length == 2)
            {
                things.put(kv[0], kv[1]); 
            }
        }

        return things;
    }

    

    public Map<String, String> GetParams()
    {
        return this.params;
    }

    public String GetPath()
    {
        return params.get("path");
    }

    public String GetPostPath()
    {
        return params.get("queryPath");
    }

    public HashMap<String, String> GetPostParams()
    {
        return this.postParams;
    }

    public String GetMethod()
    {
        return this.method;
    }

}