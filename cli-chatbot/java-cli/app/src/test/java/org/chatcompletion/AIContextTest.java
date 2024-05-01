import org.junit.Test;
import static org.junit.Assert.*;

public class AIContextTest {

    @Test
    public void testGetModel() {
        String model = "gpt-3.5-turbo";
        Message[] messages = {new Message("system", "You are a helpful assistant."), new Message("user", "Can you count from 1 to 10?")};
        AIContext aiContext = new AIContext(model, messages);

        assertEquals(model, aiContext.getMODEL());
    }

    @Test
    public void testGetMessages() {
        String model = "gpt-3.5-turbo";
        Message[] messages = {new Message("system", "You are a helpful assistant."), new Message("user", "Can you count from 1 to 10?")};
        AIContext aiContext = new AIContext(model, messages);

        assertArrayEquals(messages, aiContext.getMessages());
    }
}