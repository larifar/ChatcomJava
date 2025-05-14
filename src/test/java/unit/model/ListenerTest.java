package unit.model;

import challenge.service.Listener;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListenerTest {

    //ouvir mensagens enviadas do servidor
    @Test
    public void clientListenerReceiveMessageFromServer() throws InterruptedException, IOException {
        String message = "Olá tudo bem?";
        String received = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader
                (new ByteArrayInputStream(
                        (message+"\n").getBytes())));
        Listener listener = new Listener(reader);
        listener.start();
        while(received == null || received.isEmpty()){
            received = listener.getListMessages().poll();
            Thread.sleep(100);
        }
        System.out.println(received);
        Assertions.assertEquals(message, received);
        listener.stop();
    }
}
