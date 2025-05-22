package challenge.model;

import java.time.LocalDateTime;

public class Message {
    private String content;
    private Client sender;
    private LocalDateTime timestamp;
    public Message(String content, Client name) {
        this.content = content;
        this.sender = name;
        setTime(LocalDateTime.now());
    }

    public void setTime(LocalDateTime time){
        this.timestamp = time;
    }

    public String getContent(){
        return content;
    }

    public Client getSender(){
        return sender;
    }

    public LocalDateTime getTimestamp(){
        return timestamp;
    }
}
