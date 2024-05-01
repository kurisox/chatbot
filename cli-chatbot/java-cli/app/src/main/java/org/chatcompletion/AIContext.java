package org.chatcompletion;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AIContext {
    private final String MODEL;
    private Message[] messages;

    public AIContext(String MODEL, Message[] messages) {
        this.MODEL = MODEL;
        this.messages = messages;
    }
}
