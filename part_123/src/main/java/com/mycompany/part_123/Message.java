package com.mycompany.part_123;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message {
    private String id;
    private String recipient;
    private String text;
    private String hash;
    private String flag; // Sent, Stored, Disregard

    // Arrays (lists) for storing messages
    public static List<Message> sentMessages = new ArrayList<>();
    public static List<Message> storedMessages = new ArrayList<>();
    public static List<Message> disregardedMessages = new ArrayList<>();
    public static List<String> messageHashes = new ArrayList<>();
    public static List<String> messageIDs = new ArrayList<>();

    public Message(String recipient, String text, String flag) {
        this.id = generateMessageID();
        this.recipient = recipient;
        this.text = text;
        this.hash = createMessageHash();
        this.flag = flag;

        // Store in correct array
        switch (flag.toLowerCase()) {
            case "sent":
                sentMessages.add(this);
                break;
            case "stored":
                storedMessages.add(this);
                break;
            case "disregard":
                disregardedMessages.add(this);
                break;
        }

        messageHashes.add(this.hash);
        messageIDs.add(this.id);
    }

    // --- Validation ---
    public boolean checkMessageID() {
        return id.length() <= 10;
    }

    public String checkRecipientCell() {
        if (recipient.startsWith("+27") && recipient.length() <= 12) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code.";
        }
    }

    public String checkMessageLength() {
        if (text.length() <= 250) {
            return "Message ready to send.";
        } else {
            int excess = text.length() - 250;
            return "Message exceeds 250 characters by " + excess + "; please reduce the size.";
        }
    }

    // --- Hash Creation ---
    public String createMessageHash() {
        String[] words = text.split(" ");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();
        return id.substring(0, 2) + ":" + messageIDs.size() + ":" + firstWord + lastWord;
    }

    // --- Search Methods ---
    public static Message findLongestMessage(List<Message> messages) {
        Message longest = null;
        for (Message m : messages) {
            if (longest == null || m.text.length() > longest.text.length()) {
                longest = m;
            }
        }
        return longest;
    }

    public static List<Message> searchByRecipient(String recipient) {
        List<Message> results = new ArrayList<>();
        for (Message m : sentMessages) if (m.recipient.equals(recipient)) results.add(m);
        for (Message m : storedMessages) if (m.recipient.equals(recipient)) results.add(m);
        return results;
    }

    public static Message searchById(String id) {
        for (Message m : sentMessages) if (m.id.equals(id)) return m;
        for (Message m : storedMessages) if (m.id.equals(id)) return m;
        return null;
    }

    public static boolean deleteByHash(String hash) {
        for (Message m : sentMessages) {
            if (m.hash.equals(hash)) {
                sentMessages.remove(m);
                return true;
            }
        }
        for (Message m : storedMessages) {
            if (m.hash.equals(hash)) {
                storedMessages.remove(m);
                return true;
            }
        }
        return false;
    }

    // --- Report ---
    public static void displayReport() {
        System.out.println("=== Message Report ===");
        for (Message m : sentMessages) {
            System.out.println("Sent: " + m.id + " | Hash: " + m.hash + " | Recipient: " + m.recipient + " | Message: " + m.text);
        }
        for (Message m : storedMessages) {
            System.out.println("Stored: " + m.id + " | Hash: " + m.hash + " | Recipient: " + m.recipient + " | Message: " + m.text);
        }
        for (Message m : disregardedMessages) {
            System.out.println("Disregarded: " + m.id + " | Hash: " + m.hash + " | Recipient: " + m.recipient + " | Message: " + m.text);
        }
    }

    // --- Getters ---
    public String getId() { return id; }
    public String getText() { return text; }
    public String getHash() { return hash; }
    public String getRecipient() { return recipient; }

    // --- Generate Random ID ---
    private String generateMessageID() {
        Random rand = new Random();
        long num = 1000000000L + (long)(rand.nextDouble() * 999999999L);
        return String.valueOf(num).substring(0, 10);
    }
}
