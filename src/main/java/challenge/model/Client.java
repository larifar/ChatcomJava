package challenge.model;

import java.io.IOException;
import java.io.OutputStreamWriter;
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

    public boolean send(String message) throws IOException {
        try {
            OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream());
            writer.write(message);
            writer.write("\n");
            writer.flush();
            return true;
        }catch (RuntimeException e){
            return false;
        }
    }
    public void close() throws IOException {
        socket.close();
    }

    public Socket getSocket() {
        return this.socket;
    }
}
