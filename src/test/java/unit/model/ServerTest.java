package unit.model;

import challenge.model.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
}
