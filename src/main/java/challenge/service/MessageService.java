package challenge.service;

import challenge.model.Client;
import challenge.model.Message;

public class MessageService {
    public Message create(String content, Client client){
        if (content.isEmpty()){
            throw new IllegalArgumentException("Mensagem deve ter conteúdo.");
        }
        return new Message(content, client);
    }
}
