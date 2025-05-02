package unit.model;

import challenge.model.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerTest {


    @Test
    public void serverShouldBeOnlineWhenCreated() throws IOException {
        int port = 2000;
        ServerSocket serverSocket = new ServerSocket(port);
        Server server = new Server(serverSocket);
        int portFalse = 3444;
        Assertions.assertEquals(serverSocket,server.isOnline());
        Assertions.assertNotEquals(new ServerSocket(portFalse), server.isOnline());
    }

    @Test
    public void shouldListenAndAcceptConnection() throws IOException {
        int port = 2000;
        String host = "localhost";
        ServerSocket serverSocket = new ServerSocket(port);
        Server server = new Server(serverSocket);
        Thread clientThread = new Thread(()->{
            try {
                Socket client = new Socket(host, port);
                Assertions.assertTrue(client.isConnected());
                client.close();
            } catch (IOException e){
                Assertions.fail(e);
            }
        });
        clientThread.start();
        Socket clientSocket = server.accept();
        Assertions.assertNotNull(clientSocket);
        clientSocket.close();
        server.close();

    }

    @Test
    public void serverShouldAcceptSeveralClients() throws IOException, InterruptedException {
        int port = 2000;
        int numberClients = 3;
        ServerSocket serverSocket = new ServerSocket(port);
        Server server = new Server(serverSocket);
        List<Thread> threads = new ArrayList<>();
        for (int i =0; i<numberClients; i++){
            Thread t = new Thread(()->{
                try {
                    Socket client = new Socket("localhost", port);
                    Assertions.assertTrue(client.isConnected());
                    client.close();
                } catch (IOException e){
                    Assertions.fail(e);
                }
            });
            threads.add(t);
            t.start();
        }

        for (int i =0; i< numberClients;i++){
            Socket clientSocket = server.accept();
            Assertions.assertNotNull(clientSocket);
        }
        Assertions.assertEquals(numberClients, server.getConnectedClients());
        for (Thread t : threads) t.join();
        server.close();
    }
}
