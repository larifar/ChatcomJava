package unit.model;

import challenge.model.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
    @Mock
    ServerSocket serverSocketMock;

    @Test
    public void serverShouldBeOnlineWhenCreated() throws IOException {
        int port = 2000;
        ServerSocket serverSocket = new ServerSocket(port);
        Server server = new Server(serverSocket);
        //servidor foi criado?
        int portFalse = 3444;
        Assertions.assertEquals(serverSocket,server.isOnline());
        Assertions.assertNotEquals(new ServerSocket(portFalse), server.isOnline());
    }
}
