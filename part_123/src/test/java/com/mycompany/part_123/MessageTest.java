package com.mycompany.part_123;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MessageTest {

    @Test
    public void testValidRecipientInternational() {
        Message msg = new Message("+27718693000", "Hello there", 1);
        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testValidRecipientLocal() {
        Message msg = new Message("0718693000", "Hello there", 2);
        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testInvalidRecipient() {
        Message msg = new Message("718693000", "Hello there", 3);
        assertFalse(msg.checkRecipientCell());
    }

    @Test
    public void testValidMessageLength() {
        Message msg = new Message("0718693000", "Short message", 4);
        assertTrue(msg.checkMessageLength());
    }

    @Test
    public void testInvalidMessageLength() {
        String longText = "a".repeat(300);
        Message msg = new Message("0718693000", longText, 5);
        assertFalse(msg.checkMessageLength());
    }

    @Test
    public void testMessageHashContainsWords() {
        Message msg = new Message("0718693000", "Hello world", 6);
        String hash = msg.getMessageHash();
        assertNotNull(hash);
        assertTrue(hash.contains("HELLO#"));
        assertTrue(hash.contains("WORLD#"));
    }
}
