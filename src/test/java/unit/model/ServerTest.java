package unit.model;

import challenge.model.Client;
import challenge.model.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerTest {
    private Thread clientThread(Client client){
        return new Thread(()-> {
            try {
                client.setSocket();
                Assertions.assertTrue(client.isConnected());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }



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

    @Test
    public void serverShouldReceiveMessageFromClient() throws IOException {
        String message = "Mensagem enviada.";
        int port = 2006;
        Client client = new Client("localhost", port);
        Server server = new Server(new ServerSocket(port));
        Thread clientThread = new Thread(()->{
            try {
                client.setSocket();
                Assertions.assertTrue(client.isConnected());
            } catch (IOException e){
                Assertions.fail(e);
            }
        });
        clientThread.start();
        server.accept();
        client.send(message+"\n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(server.getClients().get(0).getInputStream()));
        String received = reader.readLine();
        Assertions.assertEquals(message, received);
        client.close();
        server.close();
    }

    @Test
    public void serverShouldReceiveMessageFromClientAndSendToOthers() throws IOException, InterruptedException {
        String message = "Mensagem enviada.";
        int port = 2006;
        int numberClients = 3;
        Client clientRemetente = new Client("localhost", port);
        Server server = new Server(new ServerSocket(port));
        Thread acceptsClients = new Thread(()->{
           for (int c =0; c<numberClients; c++){
                try {
                    server.accept();
                } catch (IOException e) {
                    e.printStackTrace();
                }
           }
        });
        acceptsClients.start();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i<numberClients; i++){
            Thread t =  i == 0 ? clientThread(clientRemetente):clientThread(new Client("localhost",port));
            threads.add(t);
            t.start();
            Thread.sleep(5);
        }
        for (Thread t : threads) {
            t.join();
        }
        acceptsClients.join();
        Assertions.assertTrue(clientRemetente.isConnected());
        clientRemetente.send(message+"\n");
        BufferedReader reader = new BufferedReader(new InputStreamReader(server.getClients().get(0).getInputStream()));
        String received = reader.readLine();
        for (int i = 1; i< numberClients-1; i++){
            Assertions.assertTrue(server.send(received,server.getClients().get(i)));
        }
        server.close();
    }

    @Test
    public void serverShouldSendMessageToClient() throws IOException {
        String message = "Mensagem enviada.";
        int port = 2009;
        Server server = new Server(new ServerSocket(port));
        Client client = new Client("localhost", port);

        Thread clientThread = clientThread(client);
        clientThread.start();
        server.accept();

        Assertions.assertTrue(server.send(message, client.getSocket()));
    }
}
