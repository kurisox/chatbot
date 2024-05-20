package org.ResponseEntitity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "index", "message", "logprobs", "finish_reason" })

public record Choices(
    @JsonProperty("index") int index,
    @JsonProperty("message") Message message,
    @JsonProperty("logprobs") String logProbs,
    @JsonProperty("finish_reason") String finishReason) {
}
