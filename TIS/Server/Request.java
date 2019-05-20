package Server;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Request
{
    private InputStream reader;
    private Map<String, String> params;

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

    private void ParseLine(String line)
    {
        //System.out.println(line);
        String[] splitted = line.split(" ");

        if(splitted.length < 2) return;

        switch(splitted[0])
        {
            case "GET":
                String path = splitted[1];
                String httpVer = splitted[2];

                params.put("path", path);
                params.put("http_ver", httpVer);
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
    

    public Map<String, String> GetParams()
    {
        return this.params;
    }

    public String GetPath()
    {
        return params.get("path");
    }


}