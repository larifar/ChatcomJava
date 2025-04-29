package challenge.model;

public class Server {
    private int port;

    public Server(int port){
        this.port = port;
    }

    public boolean isOnline(int port){
       return this.port == port;
    }
}
