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
        response.WriteHeader("Date", "Fri, 17 May 2019 23:59:59 GMT");
        response.WriteHeader("Server", "Reservado-Server-1.0");
        response.WriteHeader("Expires", "Fri, 17 May 2019 23:59:59 GMT");
        response.WriteHeader("Last-modified", "Fri, 17 May 2019 23:59:59 GMT");
        
        if(path.equals("/getClients"))
        {
        	System.out.println("Enviar clientes");
        	
        	Content c = new Content();
        	StringBuilder sb = new StringBuilder();
        	for(Cliente client : reservado.GetClientes())
        	{
        		String formated = String.format("%d|%s|%s<br>", client.GetCodigo(), client.GetNome(), client.GetTelefone());
        		sb.append(formated);
        	}
        	
        	c.Write(sb.toString());
        	c.UpdateLength();
        	
        	response.Send(c);
        	return;
        }
        
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
        
        this.database.Save();
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