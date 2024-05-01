package org.ResponseEntitity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@JsonPropertyOrder({ "index", "message", "logprobs", "finish_reason" })
public class Choices {
    @JsonProperty("index")
    private int index;

    @JsonProperty("message")
    private Message message;

    @JsonProperty("logprobs")
    private String logProbs;

    @JsonProperty("finish_reason")
    private String finishReason;

    public Choices(int index, Message message, String logProbs, String finishReason) {
        this.index = index;
        this.message = message;
        this.logProbs = logProbs;
        this.finishReason = finishReason;
    }
}
