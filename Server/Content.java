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
    
    public Content()
    {
    	this.extension = "text/html";
    	this.status = "200";
    }
    
    public void Write(String text)
    {
    	this.data = text.getBytes();
    }
    
    
    public void UpdateLength()
    {
    	this.contentSize = data.length;
    }
}