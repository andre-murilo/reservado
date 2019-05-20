import Server.*;
import Reservado.*;
import Database.*;

public class Application implements IServerCallbackable
{
    private Reservado reservado;
    private Database database;

    @Override
    public void OnReceiveRequest(Request request, Response response) 
    {
        String path = request.GetPath();
        if(path == null) return;

        System.out.println("Request: " + path);

        // write header
        response.WriteHeader("Date", "Fri, 31 Dec 1999 23:59:59 GMT");
        response.WriteHeader("Server", "Reservado-Server-1.0");
        response.WriteHeader("Expires", "Sat, 01 Jan 2000 00:59:59 GMT");
        response.WriteHeader("Last-modified", "Fri, 09 Aug 1996 14:21:40 GMT");

        // write body
        Content content = response.LoadFileContent(path);

        // send
        response.Send(content);
    }


    public Application()
    {
        this.reservado = new Reservado();
        this.database = new Database(reservado);
        this.database.Load();
    }   

    
    public void Initialize()
    {
        System.out.println("Simple WebServer");


        WebServer server = new WebServer(this, 8080);
        if(!server.Initialize()) {
            System.out.println("Failed to inialize server");
            return;
        }
        
        server.Run();
    }

    
}