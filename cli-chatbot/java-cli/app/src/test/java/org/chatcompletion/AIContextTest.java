package org.chatcompletion;

import org.junit.Test;
import static org.junit.Assert.*;

import org.ResponseEntitity.Message;
import org.junit.Before;
import java.util.ArrayList;
import java.util.List;

public class AIContextTest {
    private AIContext context;

    @Before
    public void setUp() {
        context = new AIContext();
    }

    @Test
    public void testGetModel() {
        assertEquals("gpt-3.5-turbo", context.getMODEL());
    }

    @Test
    public void testGetMessages() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message("system", "You are a helpful assistant."));
        assertTrue(messages.equals(context.getMessages()));
    }

    @Test
    public void testAddMessage() {
        Message message = new Message("user", "Hello!");
        assertTrue(context.addMessage(message));
    }
}