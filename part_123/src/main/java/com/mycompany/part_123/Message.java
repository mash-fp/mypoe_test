package com.mycompany.part_123;

public class Message {
    private String recipientCell;
    private String messageText;
    private int messageId;
    private boolean sent;

    public Message(String recipientCell, String messageText, int messageId) {
        this.recipientCell = recipientCell;
        this.messageText = messageText;
        this.messageId = messageId;
        this.sent = false;
    }

    public boolean checkRecipientCell() {
        if (recipientCell.matches("\\+27\\d{9}")) {
            return true;
        } else if (recipientCell.matches("0\\d{9}")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkMessageLength() {
        if (messageText != null && messageText.length() <= 250) {
            return true;
        } else {
            return false;
        }
    }

    public String getMessageHash() {
        if (messageText == null || messageText.isEmpty()) {
            return "";
        }
        String[] words = messageText.split("\\s+");
        StringBuilder hashBuilder = new StringBuilder();
        for (String word : words) {
            hashBuilder.append(word.toUpperCase());
            hashBuilder.append("#");
        }
        return hashBuilder.toString();
    }

    public void sendMessage(int id) {
        if (checkRecipientCell() && checkMessageLength()) {
            this.sent = true;
            System.out.println("Message " + id + " sent to " + recipientCell);
        } else {
            this.sent = false;
            System.out.println("Message " + id + " failed validation.");
        }
    }

    public boolean isSent() {
        return sent;
    }

    public void display() {
        System.out.println("Message ID: " + messageId);
        System.out.println("Recipient: " + recipientCell);
        System.out.println("Text: " + messageText);
        System.out.println("Hash: " + getMessageHash());
        if (sent) {
            System.out.println("Status: Sent");
        } else {
            System.out.println("Status: Not Sent");
        }
    }

    public int getMessageId() {
        return messageId;
    }
}
