public class Application implements IServer
{

    @Override
    public void OnReceiveRequest(Request req, Response resp) 
    {

    }

    public Application()
    {

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