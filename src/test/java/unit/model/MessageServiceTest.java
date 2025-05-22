package unit.model;

import challenge.model.Client;
import challenge.model.Message;
import challenge.service.MessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class MessageServiceTest {
    @Test
    public void messageServiceCreates(){
        MessageService messageService = new MessageService();
        Assertions.assertNotNull(messageService);
    }

    @Test
    public void createMessage(){
        MessageService messageService = new MessageService();
        String content = "Content";
        Client clientMock = mock(Client.class);
        Message messageCreated = messageService.create(content, clientMock);
        Assertions.assertEquals(content, messageCreated.getContent());
        Assertions.assertEquals(clientMock, messageCreated.getSender());
    }

    @Test
    public void failsIfTryCreateMessageWithNoContent(){
        MessageService messageService = new MessageService();
        Client clientMock = mock(Client.class);
        Assertions.assertThrows(IllegalArgumentException.class, ()-> messageService.create("", clientMock));

    }
}
