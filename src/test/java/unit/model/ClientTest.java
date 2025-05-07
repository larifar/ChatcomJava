package unit.model;

import challenge.model.Client;
import challenge.model.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

public class ClientTest {

    private Thread serverThread(Server server){
       return new Thread(()->{
            try {
                server.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    @Test
    public void createClientBeforeConnectToServer(){
        int port = 3567;
        String ip = "localhost";
        Client client = new Client(ip, port);
        Assertions.assertEquals(Client.class, client.getClass());
    }

    @Test
    public void cliendShouldConnectToServerAndCreateSocket() throws IOException {
        int port = 3567;
        String ip = "localhost";
        Client client = new Client(ip, port);
        Server server = new Server(new ServerSocket(port));
        Thread serverThread = serverThread(server);
        serverThread.start();
        client.setSocket();
        Assertions.assertEquals(true, client.isConnected());
        server.close();
    }

    @Test
    public void clientSendMessage() throws IOException {
        int port = 3567;
        String ip = "localhost";
        Client client = new Client(ip, port);
        String message = "Olá estou enviando uma mensagem";
        Server server = new Server(new ServerSocket(port));
        Thread serverThread = serverThread(server);
        serverThread.start();
        client.setSocket();
        Assertions.assertTrue(client.send(message));
        server.close();
    }

    @Test
    public void clientShouldReceiveMessageFromServer() throws IOException, InterruptedException {
        int port = 3333;
        String ip = "localhost";
        Client client = new Client(ip, port);
        String message = "Mensagem do servidor"+"\n\r";
        Server server = new Server(new ServerSocket(port));
        Thread thread = serverThread(server);
        thread.start();
        Thread.sleep(100);
        client.setSocket();
        thread.join();
        BufferedReader reader = new BufferedReader(new InputStreamReader(client.getSocket().getInputStream()));

        Assertions.assertTrue(client.isConnected());
        Thread readerThread = new Thread(()->{
            try {
                String received = reader.readLine();
                Assertions.assertEquals(message, received);
                reader.close();
            } catch (IOException e) {
                e.getMessage();
            }
        });
        readerThread.start();

        server.send(message, client.getSocket());

        readerThread.join(10);
        client.close();
        server.close();
    }
}
