package Server;

import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Response {
    final String error404 = "<h3>Error 404</h3><h3>File not found</h3>";
    final String error500 = "<h3>Error 500</h3><h3>Ocorreu um erro interno</3>";
    final String webDir = "./web";
    final String indexFile = "index.html";
    // final String rootDefault = "<h3>Ola mundo, bem vindo ao WebServer</h3>";

    
    private OutputStream writter;
    private Request request;
    private Map<String, String> headers;


    public Response(OutputStream writter, Request request) {
        this.writter = writter;
        this.request = request;
        this.headers = new HashMap<>();
    }


    private String FormatPath(String path) {
        if (path.contains("?")) {
            // System.out.println("Possui file query!");
            path = path.split("\\?")[0];
        }

        return path;
    }

    private Boolean ExistPath(String path) {
        // System.out.println("Requested: " + path);

        File file = new File(path);
        return file.exists();
    }

    private byte[] ReadFromFile(String path) {
        try {
            File f = new File(path);
            byte[] data = Files.readAllBytes(f.toPath());
            return data;
        } catch (Exception ex) {
            return null;
        }
    }

    private String GetFileExtension(String fileName) {
        String ext = fileName.substring(fileName.lastIndexOf("."));
        String result = "";
        switch (ext) {
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

    public Content LoadFileContent(String path) {

        // if is root request
        if (path.equals("/")) {
            String defaultFilePath = webDir + "/" + indexFile;

            if (ExistPath(defaultFilePath)) {
                // System.out.println("Enviando /");

                byte[] data = ReadFromFile(defaultFilePath);
                // System.out.println("Data length: " + data.length);

                return new Content(data, "text/html", "200", data.length);
            } else {
                return new Content(error500.getBytes(), "text/html", "500", error500.length());
            }
        }

        path = webDir + path;
        path = FormatPath(path);

        // if is file request
        if (ExistPath(path)) {
            // send file binary data
            String ext = GetFileExtension(path);
            byte[] content = ReadFromFile(path);

            return new Content(content, ext, "200", content.length);
        } else {
            // return 404
            return new Content(error404.getBytes(), "text/html", "404", error404.length());
        }

    }


    public void WriteHeader(String k, String v) {
        this.headers.put(k, v);
    }


    public void Send(Content content)
    {
        byte[] breakLine = { 13, 10 };

        String host = "HTTP/1.1 " + content.status + " OK";
        String contentType = "Content-Type: " + content.extension;
        String contentLength = "Content-Length: " + content.contentSize;

        try {
            writter.write(host.getBytes());
            writter.write(breakLine);

            writter.write(contentType.getBytes());
            writter.write(breakLine);

            writter.write(contentLength.getBytes());
            writter.write(breakLine);

        } catch (Exception e) {
        }


        for (Map.Entry<String, String> pair : this.headers.entrySet()) 
        {
            try {
                String formated = String.format("%s: %s", pair.getKey(), pair.getValue());
                writter.write(formated.getBytes());
                writter.write(breakLine);
            } catch (Exception e) {
            }
        }

        try {
            writter.write(breakLine);
            writter.write(content.data);

            writter.flush();
        } catch (Exception e) {
        }

    }


    public void WriteAll()
    {
        // get path
        Map<String, String> params = request.GetParams();
        String path = params.get("path");
        
        // get 
        //System.out.println("Requested: " + path.trim());
        Content content = LoadFileContent(path.trim());
     

        // write data
        try 
        {
            // HEADER
            String host = "HTTP/1.1 " + content.status + " OK";
            String data = "Date: Fri, 31 Dec 1999 23:59:59 GMT";
            String server = "Server: Apache/0.8.4";
            String contentType = "Content-Type: " + content.extension;
            String contentLength = "Content-Length: " + content.contentSize;
            String expires = "Expires: Sat, 01 Jan 2000 00:59:59 GMT";
            String lastModified = "Last-modified: Fri, 09 Aug 1996 14:21:40 GMT";
            //String breakLine = System.lineSeparator();

            byte[] breakLine = {13, 10};

            writter.write(host.getBytes());
            writter.write(breakLine);

            writter.write(data.getBytes());
            writter.write(breakLine);

            writter.write(server.getBytes());
            writter.write(breakLine);

            writter.write(contentType.getBytes());
            writter.write(breakLine);

            writter.write(contentLength.getBytes());
            writter.write(breakLine);

            writter.write(expires.getBytes());
            writter.write(breakLine);
            
            writter.write(lastModified.getBytes());
            writter.write(breakLine);

            writter.write(breakLine);

            // BODY DATA
            writter.write(content.data);
            

            // flush
            writter.flush();
        } 
        catch (Exception e) 
        {
            
        }
    }
}