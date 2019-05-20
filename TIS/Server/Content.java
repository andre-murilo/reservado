package Server;

public class Content {
    public byte[] data;
    public String extension;
    public String status;
    public int contentSize;

    public Content(byte[] data, String ext, String status, int size) {
        this.data = data;
        this.extension = ext;
        this.status = status;
        this.contentSize = size;
    }
}