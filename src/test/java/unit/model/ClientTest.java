package unit.model;

import challenge.model.Client;
import challenge.model.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;

public class ClientTest {
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
        Thread serverThread = new Thread(()->{
            try {
                Server server = new Server(new ServerSocket(port));
                server.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        serverThread.start();
        client.setSocket();
        Assertions.assertEquals(true, client.isConnected());
    }
}
