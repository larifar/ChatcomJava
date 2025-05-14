package unit.model;

import challenge.service.Listener;
import challenge.service.ServerListener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ServerListenerTest {
    //ouvir mensagens de vários clientes
    @Test
    public void ServerListensMessagesFromClients() throws InterruptedException, IOException {
        List<String> messages = List.of("mensagem1", "mensagem2", "mensagem3", "mensagem4", "mensagem5");
        ServerListener serverListener = new ServerListener();
        for (String message : messages) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(message.getBytes())));
            serverListener.addListener(new Listener(reader, serverListener));
        }
        while (!serverListener.getMessages().containsAll(messages)) {
            System.out.println(serverListener.getMessages());
            Thread.sleep(100);
        }
        Assertions.assertTrue(serverListener.getMessages().containsAll(messages));
        serverListener.stop();
    }
}
