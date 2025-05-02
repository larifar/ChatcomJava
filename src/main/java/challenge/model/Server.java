package challenge.model;

import java.io.IOException;
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
}
