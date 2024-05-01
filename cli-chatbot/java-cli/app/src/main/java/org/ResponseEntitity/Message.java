package org.ResponseEntitity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@JsonPropertyOrder({ "content", "role"})
public class Message{
    @JsonProperty("role")
    private String role;
    @JsonProperty("content")
    private String content;

    public Message(String role, String content) {
        this.role = role;
        this.content = content;
    }

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
