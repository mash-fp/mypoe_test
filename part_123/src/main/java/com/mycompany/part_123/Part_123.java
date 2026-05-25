package com.mycompany.part_123;

import java.util.Scanner;

public class Part_123 
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to QuickChat");
        System.out.print("Enter recipient cell number: ");
        String recipient = scanner.nextLine();

        System.out.print("Enter your message: ");
        String text = scanner.nextLine();

        Message msg = new Message(recipient, text, 1);

        msg.sendMessage(msg.getMessageId());
        msg.display();

        if (msg.isSent()) {
            System.out.println("✅ Message sent successfully!");
        } else {
            System.out.println("❌ Message failed to send.");
        }

        scanner.close();
    }
}
