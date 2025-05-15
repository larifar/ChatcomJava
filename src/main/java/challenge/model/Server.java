package challenge.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private ServerSocket serverSocket;
    private List<Socket> clients;
    private OutputStreamWriter writer;
    private List<BufferedReader> readers;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.clients = new ArrayList<>();
        this.readers = new ArrayList<>();
    }

    public boolean isOnline() {
        return this.serverSocket != null;
    }

    public Socket accept() throws IOException {
        Socket socketClient = serverSocket.accept();
        clients.add(socketClient);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
        readers.add(reader);

        return socketClient;
    }

    public void close() throws IOException{
        for (BufferedReader reader: readers){
            reader.close();
        }
        serverSocket.close();
    }

    public int getConnectedClients() {
        return clients.size();
    }
    public List<Socket> getClients(){
        return this.clients;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public boolean send(String message, Socket client) throws IOException {
        try {
            this.writer = new OutputStreamWriter(client.getOutputStream());
            writer.write(message);
            writer.write("\n\r");
            writer.flush();
            return true;
        }catch (RuntimeException e){
            e.printStackTrace();
            return false;
        }
    }
}
