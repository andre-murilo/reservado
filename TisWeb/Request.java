import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Request
{
    private BufferedReader reader;
    private Map<String, String> params;

    public Request(BufferedReader reader)
    {
        this.reader = reader;
        this.params = new HashMap<String, String>();

        ParseRequest(reader);
    }


    private void ParseRequest(BufferedReader reader)
    {
        List<String> lines = new ArrayList<String>();
  
        String line = "";
        while(true)
        {
            try 
            {
                line = reader.readLine();
                if(line.isEmpty())
                    break;

                lines.add(line);
            }
            catch (Exception e) 
            {
                break;
            }
        }

        String[] array = new String[lines.size()];
        for(int i = 0; i < array.length; i++)
        array[i] = lines.get(i);


        for (String l : array) {
            ParseLine(l);
        }


    }

    private void ParseLine(String line)
    {
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


}