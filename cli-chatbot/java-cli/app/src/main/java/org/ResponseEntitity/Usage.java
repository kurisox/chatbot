package org.ResponseEntitity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "prompt_tokens", "completion_tokens", "total_tokens"})
public record Usage(
    @JsonProperty("prompt_tokens") int promptTokens,
    @JsonProperty("completion_tokens") int completionTokens,
    @JsonProperty("total_tokens") int totalTokens) {    
}
