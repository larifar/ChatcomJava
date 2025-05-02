package challenge.model;

import java.io.IOException;
import java.net.Socket;

public class Client {
    private Socket socket;
    private String ip;
    private int port;
    public Client(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public void setSocket() throws IOException {
        this.socket = new Socket(this.ip, this.port);
    }

    public boolean isConnected() {
        return socket.isConnected();
    }
}
