package challenge.service;

import challenge.model.Server;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerService {
    private Server server;
    private ServerListener serverListener;

    private ExecutorService executor;

    public ServerService(Server server, ServerListener serverListener) {
        this.server = server;
        this.serverListener = serverListener;
    }

    public boolean isOnline() {
        return server.isOnline() && serverListener != null;
    }

    public void start() {
        this.executor = Executors.newCachedThreadPool();
        executor.execute(acceptClients());
        executor.execute(awaitMessage());
    }

    public void stopAll() {
        try {
            executor.shutdown();
            serverListener.stop();
            server.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Server getServer() {
        return server;
    }

    public ServerListener getServerListener() {
        return serverListener;
    }

    private Runnable awaitMessage() {
        return () -> {
            try {
                while (isOnline()) {
                    String msg = serverListener.getMessages().take();
                    for (Socket client : server.getClients()) {
                        server.send(msg, client);
                    }
                }
            } catch (InterruptedException | IOException e) {
                System.out.println(e.getMessage());
            }
        };
    }

    private Runnable acceptClients() {
        return () -> {
            try {
                while (isOnline()) {
                    server.accept();
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        };
    }
}
