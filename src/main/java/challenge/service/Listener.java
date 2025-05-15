package challenge.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Listener {
    private BufferedReader reader;
    private volatile boolean isOnline;

    private ExecutorService executor;

    private Runnable runnableTask;
    private LinkedBlockingQueue<String> listMessages;

    private ServerListener serverListener;

    public Listener(BufferedReader reader, ServerListener serverListener) {
        this.reader = reader;
        this.serverListener = serverListener;
        this.listMessages = new LinkedBlockingQueue<>();
    }

    public Listener(BufferedReader reader) {
        this.reader = reader;
        this.listMessages = new LinkedBlockingQueue<>();
    }

    public LinkedBlockingQueue<String> getListMessages() {
        return listMessages;
    }

    private Runnable createTask() {
        return () -> {
            String msg = null;
            while (isOnline) {
                try {
                    msg = listen();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                addMessage(msg);
                sleep();
            }
        };
    }

    private void addMessage(String msg) {
        if (msg != null) {
            listMessages.add(msg);
            if (serverListener != null) {
                serverListener.addMessage(msg);
            }
        }
    }

    public void start() {
        this.isOnline = true;
        this.executor = Executors.newCachedThreadPool();
        this.runnableTask = createTask();
        executor.execute(runnableTask);
    }

    public void stop() throws IOException {
        this.isOnline = false;
        reader.close();
        executor.shutdown();
    }

    public String listen() throws IOException {
        return reader.readLine();
    }

    private void sleep() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("erro sleep");
        }
    }
}
