package unit.model;

import challenge.model.Server;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.Socket;

public class ServerTest {
    @Test
    public void serverIsOnline(){
        int port = 2000;
        Server server = new Server(port);
        //servidor esta ouvindo porta?
        Assertions.assertTrue(server.isOnline(port));
        Assertions.assertFalse(server.isOnline(4000));
    }

    @Test
    public void serverAcceptsConexionAndReturnSocket(){
        int port = 3000;
        Server server = new Server(port);
        Socket socket = new Socket();
        Assertions.assertEquals(socket ,server.accepts());
    }
}
