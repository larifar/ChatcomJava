package unit.model;

import challenge.model.Server;
import challenge.service.ServerListener;
import challenge.service.ServerService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import static org.mockito.Mockito.*;

public class ServerServiceTest {

    @Test
    public void serverServiceShouldAcceptClients() throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Server server = mock(Server.class);
        ServerListener listener = mock(ServerListener.class);
        when(server.isOnline()).thenReturn(true).thenReturn(false);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        ServerService serverService = new ServerService(server, listener);
        Method method = ServerService.class.getDeclaredMethod("acceptClients");
        method.setAccessible(true);
        executor.execute((Runnable) method.invoke(serverService));

        verify(server, atLeastOnce()).accept();
    }

    @Test
    public void ServerShouldReceiveMessageFromClientAndSendToAnother() throws IOException, InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // tem server, um cliente remetente, outro cliente destinatario, uma mensagem e 3 listener
        String message = "OI";
        Server server = mock(Server.class);
        ServerListener listener = mock(ServerListener.class);
        Socket sender = mock(Socket.class);
        Socket receiver = mock(Socket.class);

        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
        queue.add(message);

        when(server.isOnline()).thenReturn(true);
        when(server.getClients()).thenReturn(List.of(sender, receiver));
        when(listener.getMessages()).thenReturn(queue);

        ServerService serverService = new ServerService(server, listener);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Method method = serverService.getClass().getDeclaredMethod("awaitMessage");
        method.setAccessible(true);
        executor.execute((Runnable) method.invoke(serverService));

        Thread.sleep(200);

        verify(server, times(1)).send(message, sender);
        verify(server, times(1)).send(message, receiver);

        executor.shutdownNow();
    }
}
