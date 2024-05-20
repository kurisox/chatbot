package org.ResponseEntitity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "content", "role"})
public record Message(
    @JsonProperty("role") String role,
    @JsonProperty("content") String content) {
}
