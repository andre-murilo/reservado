import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.nio.file.Files;
import java.util.Map;
import java.io.*;


public class Response
{
    final String error404 = "<h3>Error 404</h3><h3>File not found</h3>";
    final String error500 = "<h3>Error 500</h3><h3>Ocorreu um erro interno</3>";
    final String webDir = "./web";
    final String indexFile = "index.html";
    //final String rootDefault = "<h3>Ola mundo, bem vindo ao WebServer</h3>";



    private class Content
    {
        public String data;
        public String extension;
        public String status;
        public int contentSize;

        public Content(String data, String ext, String status, int size)
        {
            this.data = data;
            this.extension = ext;
            this.status = status;
            this.contentSize = size;
        }
    }


    private BufferedWriter writter;
    private Request request;

    

    private Boolean ExistPath(String path)
    {
        File file = new File(path);
        return file.exists();
    }

    private String ReadFromFile(String path)
    {
        try
        {
            File f = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(f)); 
            StringBuilder sb = new StringBuilder();

            String st; 
            while ((st = br.readLine()) != null) 
                sb.append(st);
            
            return sb.toString();
        }
        catch(Exception ex)
        {
            return error500;
        }
    }

    private String GetFileExtension(String fileName)
    {
        String ext = fileName.substring(fileName.lastIndexOf("."));

        String result = "";
        switch(ext)
        {
            case ".htm":
                result = "text/html";
                break;

            case ".html":
                result = "text/html";
                break;
            
            case ".css":
                result = "text/css";
                break;
            
            case ".js":
                result = "application/javascript";
                break;

            case ".ico":
                result = "image/x-icon";
                break;

            case ".gif":
                result = "image/gif";
                break;

            case ".jpg":
                result = "image/jpeg";
                break;

            case ".jpeg":
                result = "image/jpeg";
                break;

            case ".png":
                result = "image/png";
                break;

            case ".xml":
                result = "application/xml";
                break;

            case ".pdf":
                result = "application/pdf";
                break;

            case ".json":
                result = "application/json";
                break;

            default:
                result = "text/html";
            break;
        }
        return result;
    }

    private Content LoadFileContent(String path)
    {

        // if is root request
        if(path.equals("/"))
        {
            String defaultFilePath = webDir + "/" + indexFile; 

            if(ExistPath(defaultFilePath))
            {
                System.out.println("Enviando /");

                String data = ReadFromFile(defaultFilePath);

                return new Content(data, "text/html", "200", data.length());
            }
            else
            {
                return new Content(error500, "text/html", "500", error500.length());
            }
        }

        path = webDir + path;

        // if is file request
        if(ExistPath(path))
        {
            String ext = GetFileExtension(path);
            

            String content = ReadFromFile(path);
            int length = content.length();


            return new Content(content, ext, "200", length);
        }
        else
        {
            // return 404
            return new Content(error404, "text/html", "404", error404.length());
        }

    }

    public Response(BufferedWriter writter, Request request)
    {
        this.writter = writter;
        this.request = request;
    }

    public void WriteAll()
    {
        // get path
        Map<String, String> params = request.GetParams();
        String path = params.get("path");
        
        // get 
        System.out.println("Requested: " + path.trim());
        Content content = LoadFileContent(path.trim());
     

        // write data
        try 
        {
            // head
            writter.write("HTTP/1.1 " + content.status + " OK\r\n");
            writter.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n");
            writter.write("Server: Apache/0.8.4\r\n");
            writter.write("Content-Type: " + content.extension + "\r\n");
            writter.write("Content-Length: " + content.contentSize + "\r\n");
            writter.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
            writter.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
            writter.write("\r\n");

            // data
            writter.write(content.data);

            

            // flush
            writter.flush();
        } 
        catch (Exception e) 
        {
            
        }


            
    
    }



}