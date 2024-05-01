package org.chatcompletion;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ChatCompletionTest {

    private ChatCompletion chatCompletion;

    @Before
    public void setUp() {
        chatCompletion = ChatCompletion.getInstance();
    }

    @Test
    public void testGetInstanceNotNull() {
        assertNotNull(chatCompletion);
    }

    // Add more test methods as needed to test the functionality of the ChatCompletion class

}