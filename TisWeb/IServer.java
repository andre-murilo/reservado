public interface IServer
{
    void OnReceiveRequest(Request request, Response response);
}