package challenge.model;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    private Socket socket;
    private String ip;
    private int port;
    private OutputStreamWriter writer;
    public Client(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public void setSocket() throws IOException {
        this.socket = new Socket(this.ip, this.port);
        this.writer = new OutputStreamWriter(socket.getOutputStream());
    }

    public boolean isConnected() {
        return socket.isConnected();
    }

    public boolean send(String message) throws IOException {
        try {
            writer.write(message);
            writer.write("\n\r");
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
