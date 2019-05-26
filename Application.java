import Server.*;
import Reservado.*;

import java.util.HashMap;

import Database.*;

public class Application implements IServerCallbackable
{
    private Reservado reservado;
    private Database database;


    private void ParseGetRequest(Request req, Response resp)
    {
        String path = req.GetPath();

        // write header
        resp.WriteHeader("Date", "Fri, 17 May 2019 23:59:59 GMT");
        resp.WriteHeader("Server", "Reservado-Server-1.0");
        resp.WriteHeader("Expires", "Fri, 17 May 2019 23:59:59 GMT");
        resp.WriteHeader("Last-modified", "Fri, 17 May 2019 23:59:59 GMT");
        resp.WriteHeader("Connection", "Closed");

        // load content File & Send
        Content content = resp.LoadFileContent(path);
        resp.Send(content);
    }


    private void ParsePostRequest(Request req, Response resp)
    {
        String path = req.GetPostPath();
        HashMap<String, String> params = req.GetPostParams();

        System.out.println("POST REQUEST: " + path);

        // write header
        resp.WriteHeader("Date", "Fri, 17 May 2019 23:59:59 GMT");
        resp.WriteHeader("Server", "Reservado-Server-1.0");
        resp.WriteHeader("Connection", "Closed");
        // resp.WriteHeader("Expires", "Fri, 17 May 2019 23:59:59 GMT");
        // resp.WriteHeader("Last-modified", "Fri, 17 May 2019 23:59:59 GMT");

        Content content = new Content();
        StringBuilder sb = new StringBuilder();

        switch(path)
        {
            case "/ajax/getStats":
                sb.append(ParseGetStats());
            break;

            case "/ajax/cadastrar":
                sb.append(ParseCadastroRestaurante(params));
            break;

            default:
                sb.append("error 404");
            break;
        }

        content.Write(sb.toString());
        content.UpdateLength();

        resp.Send(content);
    }

    private String ParseGetStats()
    {
        StringBuilder sb = new StringBuilder();

        int clientsCount = 0;
        int restaurantes = 0;
        int reservas = 0;
        
        clientsCount = reservado.GetClientes().size();
        restaurantes = reservado.GetRestaurantes().size();
        
        for(Restaurante rest : reservado.GetRestaurantes())
        {
        	reservas += rest.GetReservas().size();
        }
        
        // make json format
        
        /*	{
         * 		"clientes": 0,
         * 		"restaurantes": 0,
         * 		"reservas": 0
         * 	}
         * */

        sb.append("{\n");
        sb.append("\"clientes\": "+clientsCount+",\n");
        sb.append("\"restaurantes\": "+restaurantes+",\n");
        sb.append("\"reservas\": "+reservas + "\n");
        sb.append("\n}");
        
        return sb.toString();
    }

    private String ParseCadastroRestaurante(HashMap<String, String> params)
    {
        String nome = params.get("name");
        String endereco = params.get("endereco");
        String mesas = params.get("mesas");


        String formated = String.format("%s|%s|%s\n", nome, endereco, mesas);
        System.out.println(formated);

        return formated;
    }




    @Override
    public void OnReceiveRequest(Request request, Response response) 
    {
        String path = request.GetPath();
        String method = request.GetMethod();
        if(path == null) return;
       
        if(method.equals("GET")) {
            ParseGetRequest(request, response);
        } else if(method.equals("POST")) {
            ParsePostRequest(request, response);
        }

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