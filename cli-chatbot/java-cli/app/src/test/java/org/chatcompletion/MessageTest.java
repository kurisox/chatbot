package org.chatcompletion;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testGetRole() {
        Message message = new Message("user", "Hello!");
        assertEquals("user", message.getRole());
    }

    @Test
    public void testGetContent() {
        Message message = new Message("user", "Hello!");
        assertEquals("Hello!", message.getContent());
    }
}