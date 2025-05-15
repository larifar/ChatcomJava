package unit.model;

import challenge.model.Client;
import challenge.model.Server;
import challenge.service.ServerListener;
import challenge.service.ServerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerServiceTest {

    private Runnable createClient(){
        return ()-> {
            Client client = new Client("localhost", 3456);
            try {
                while (client.getSocket() == null){
                    client.setSocket();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };
    }

    @Test
    public void serverServiceShouldAcceptClients() throws IOException, InterruptedException {
        Server server = new Server(new ServerSocket(3456));
        ServerService serverService = new ServerService(server, new ServerListener());
        serverService.start();
        ExecutorService executorService = Executors.newCachedThreadPool();
        //clients
        executorService.execute(createClient());
        executorService.execute(createClient());
        //just for test
        Thread.sleep(400);
        Assertions.assertEquals(2,serverService.getServer().getClients().size());
        serverService.stopAll();
    }

//    @Test
//    public void ServerShouldReceiveMessageFromClientAndSendToAnother() throws IOException {
//        tem server, um cliente remetente, outro cliente destinatario, uma mensagem e 3 listener
//        String message = "OI";
//        String received = null;
//        Server server = new Server(new ServerSocket(3456));
//        ServerService serverService = new ServerService(server, new ServerListener());
//        serverService.start();
//
//        Assertions.assertEquals(message, received);
//    }
}
