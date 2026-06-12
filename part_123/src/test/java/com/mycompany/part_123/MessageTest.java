package com.mycompany.part_123;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class MessageTest {

    @BeforeEach
    public void setUp() {
        // Clear arrays before each test
        Message.sentMessages.clear();
        Message.storedMessages.clear();
        Message.disregardedMessages.clear();
        Message.messageHashes.clear();
        Message.messageIDs.clear();

        // Add test data
        new Message("+27834557896", "Did you get the cake?", "Sent");
        new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
        new Message("08388884567", "It is dinner time!", "Sent");
        new Message("+27838884567", "Ok, I am leaving without you.", "Stored");
        new Message("+27834484567", "Yohoooo, I am at your gate.", "Disregard");
    }

    @Test
    public void testArraysPopulated() {
        assertEquals(2, Message.sentMessages.size());
        assertEquals(2, Message.storedMessages.size());
        assertEquals(1, Message.disregardedMessages.size());
    }

    @Test
    public void testFindLongestMessage() {
        Message longest = Message.findLongestMessage(Message.storedMessages);
        assertEquals("Where are you? You are late! I have asked you to be on time.", longest.getText());
    }

    @Test
    public void testSearchByRecipient() {
        List<Message> results = Message.searchByRecipient("+27838884567");
        assertEquals(2, results.size());
    }

    @Test
    public void testSearchById() {
        String id = Message.storedMessages.get(0).getId();
        Message found = Message.searchById(id);
        assertNotNull(found);
        assertEquals("Where are you? You are late! I have asked you to be on time.", found.getText());
    }

    @Test
    public void testDeleteByHash() {
        String hash = Message.sentMessages.get(0).getHash();
        boolean deleted = Message.deleteByHash(hash);
        assertTrue(deleted);
        assertEquals(1, Message.sentMessages.size()); // one removed
    }

    @Test
    public void testMessageLengthValidation() {
        Message msg = new Message("+27834557896", "Short message", "Sent");
        assertEquals("Message ready to send.", msg.checkMessageLength());

        Message longMsg = new Message("+27834557896", "x".repeat(300), "Sent");
        assertTrue(longMsg.checkMessageLength().contains("Message exceeds 250 characters"));
    }

    @Test
    public void testRecipientValidation() {
        Message validMsg = new Message("+27834557896", "Hello", "Sent");
        assertEquals("Cell phone number successfully captured.", validMsg.checkRecipientCell());

        Message invalidMsg = new Message("08966553", "Hello", "Sent");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code.", invalidMsg.checkRecipientCell());
    }
}
