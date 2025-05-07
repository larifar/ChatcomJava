package challenge.model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    private List<Socket> clients;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.clients = new ArrayList<>();
    }

    public ServerSocket isOnline() {
        return this.serverSocket;
    }

    public Socket accept() throws IOException {
        Socket socketClient = serverSocket.accept();
        clients.add(socketClient);
        return socketClient;
    }

    public void close() throws IOException {
        serverSocket.close();
    }

    public int getConnectedClients() {
        return clients.size();
    }
    public List<Socket> getClients(){
        return this.clients;
    }

    public boolean send(String message, Socket client) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            writer.write(message);
            writer.write("\n");
            writer.flush();
            return true;
        }catch (RuntimeException e){
            e.printStackTrace();
            return false;
        }
    }
}
