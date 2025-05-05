package unit.model;

import challenge.model.Client;
import challenge.model.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
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
}
