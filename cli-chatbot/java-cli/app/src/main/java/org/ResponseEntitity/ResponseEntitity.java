package org.ResponseEntitity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@JsonPropertyOrder({ "id", "object","created","model","choices"})
public class ResponseEntitity {
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("object")
    private String object;
    
    @JsonProperty("created")
    private long created;
    
    @JsonProperty("model")
    private String model;
    
    @JsonProperty("choices")
    private Choices[] choices;

    @JsonProperty("usage")
    private Usage usage;

    public ResponseEntitity( String id, String object, long created, String model, Choices[] choices) {
        this.id = id;
        this.object = object;
        this.created = created;
        this.model = model;
        this.choices = choices;
    }
}