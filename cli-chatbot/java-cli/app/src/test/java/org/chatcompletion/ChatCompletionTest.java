package org.chatcompletion;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ChatCompletionTest {
    private ChatCompletion chatCompletion;

    @Before
    public void setUp() {
        chatCompletion = new ChatCompletion();
    }

    @Test
    public void testGetAIcontext() {
        assertNotNull(chatCompletion.getAIcontext());
    }
}