import java.net.*;

public class WebServer
{
    private int port = 8080;
    private ServerSocket server;
    private IServer instance;


    public WebServer(IServer instance, int port)
    {
        this.instance = instance;
        this.port = port;
    }

    public boolean Initialize()
    {
        try
        {
            this.server = new ServerSocket(port);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }

    public boolean Close()
    {
        if(server == null)
            return false;
        try {
            this.server.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private Socket Accept()
    {
        try
        {
            return server.accept();
        }
        catch(Exception ex)
        {
            return null;
        }
    }

    public void Run()
    {
        Boolean exiting = false;
        while(!exiting)
        {
            // accept client
            Socket sockClient = Accept();
            if(sockClient == null) continue;

            // client
            Client cliente = new Client(sockClient);
            cliente.run();


            //exiting = true;
        }

        Close();
    }
}