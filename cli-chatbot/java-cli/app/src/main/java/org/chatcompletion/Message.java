package org.chatcompletion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Message{
    private String role;
    private String content;
}
