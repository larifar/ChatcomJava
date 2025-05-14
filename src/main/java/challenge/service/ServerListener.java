package challenge.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerListener {
    private List<Listener> listeners;
    private LinkedBlockingQueue<String> messages;

    public ServerListener() {
        this.listeners = new ArrayList<>();
        this.messages = new LinkedBlockingQueue<>();
    }

    public boolean addListener(Listener listener) {
        if (listener != null) {
            listeners.add(listener);
            listener.start();
            return true;
        }
        return false;
    }

    public boolean addMessage(String msg) {
        if (msg != null) {
            messages.add(msg);
            return true;
        }
        return false;
    }

    public void stop() throws IOException {
        for (Listener l : listeners) {
            l.stop();
        }
    }

    public LinkedBlockingQueue<String> getMessages() {
        return messages;
    }
}
