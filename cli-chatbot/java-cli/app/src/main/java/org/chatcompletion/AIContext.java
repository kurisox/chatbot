package org.chatcompletion;

import java.util.ArrayList;
import java.util.List;

import org.ResponseEntitity.Message;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonPropertyOrder({ "model", "messages"})
public class AIContext {
    private final String MODEL = "gpt-3.5-turbo";
    private List<Message> messages;

    public AIContext() {
        this.messages = new ArrayList<>();
        this.messages.add(new Message("system", "You are a helpful assistant."));
    }

    public boolean addMessage(Message message) {
        this.messages.add(message);
        return this.messages.contains(message);
    }
}
