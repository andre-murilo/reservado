package Server;

public interface IServerCallbackable
{
    void OnReceiveRequest(Request request, Response response);
}