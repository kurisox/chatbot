package org.chatcompletion;

import java.util.ArrayList;
import java.util.List;

import org.ResponseEntitity.Message;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


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

    /**
     * Adds a message to the list of messages
     *
     * @param  message  the message to be added
     * @return          true if the message successfully added, false otherwise
     */
    public boolean addMessage(Message message) {
        this.messages.add(message);
        return this.messages.contains(message);
    }

    /**
     * Resets the content of the messages list and starts a new conversation.
     *
     * @return the size of the updated messages list
     */
    public int resetContent() {
        this.messages.clear();
        if (this.aiPersona != null) {
            this.messages.add(new Message("system", this.aiPersona));
        }else{
            this.messages.add(new Message("system", "You are a helpful assistant."));
        }
        return this.messages.size();
    }

    /**
     * Resets the content of the AIContext and adds a new message with the summarized context.
     *
     * @param  summarizedContext  the summarized context to be added to the AIContext
     * @return                    the size of the updated messages list
     */
    public int resetContent(String summarizedContext) {
        this.messages.clear();
        if(this.aiPersona != null) {
            this.messages.add(new Message("system", this.aiPersona + "\n" + summarizedContext));
        }else{
            this.messages.add(new Message("system", "You are a helpful assistant.\n" + summarizedContext));
        }
        return this.messages.size();
    }
}
