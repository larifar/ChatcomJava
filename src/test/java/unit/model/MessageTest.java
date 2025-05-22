package unit.model;

import challenge.model.Client;
import challenge.model.Message;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.mockito.Mockito.mock;

public class MessageTest {
    @Test
    public void messageCreated(){
        Client client = mock(Client.class);
        String content = "Olá";
        LocalDateTime time = LocalDateTime.now();
        Message msg = new Message(content, client);
        msg.setTime(time);
        Assertions.assertEquals(content, msg.getContent());
        Assertions.assertEquals(client, msg.getSender());
        Assertions.assertEquals(time, msg.getTimestamp());
    }
}
