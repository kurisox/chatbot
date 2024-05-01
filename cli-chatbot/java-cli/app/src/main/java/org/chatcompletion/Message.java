package org.chatcompletion;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Message{
    private String role;
    private String content;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Message other = (Message) obj;
        return role.equals(other.getRole()) && content.equals(other.getContent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, content);
    }
}
