package org.chatcompletion;

import java.util.ArrayList;
import java.util.List;

import org.ResponseEntitity.Message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@JsonPropertyOrder({ "model", "max_tokens","messages"})
public class AIContext {
    
    @JsonProperty("model")
    private final String MODEL = "gpt-3.5-turbo";
    @JsonProperty("max_tokens")
    private final int MAX_TOKENS = 1024;
    @JsonProperty("messages")
    private List<Message> messages;
    private String aiPersona;

    public AIContext() {
        this.messages = new ArrayList<>();
        aiPersona = null;
        this.messages.add(new Message("system", "You are a helpful assistant."));
    }

    public AIContext(String aiPersona) {
        this.messages = new ArrayList<>();
        this.aiPersona = aiPersona;
        this.messages.add(new Message("system", this.aiPersona));
    }

    public boolean addMessage(Message message) {
        this.messages.add(message);
        return this.messages.contains(message);
    }

    public void resetContent(String responseMessage) {
        this.messages.clear();
        if(this.aiPersona != null) {
            this.messages.add(new Message("system", this.aiPersona + "\n" + responseMessage));
        }else{
            this.messages.add(new Message("system", "You are a helpful assistant.\n" + responseMessage));
        }
    }
}
