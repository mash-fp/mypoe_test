package com.mycompany.part_123;

import java.util.Scanner;

public class Part_123 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // --- Registration ---
        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print("Enter cell phone number (+27...): ");
        String cellPhone = sc.nextLine();

        Login user = new Login(username, password, cellPhone);
        System.out.println(user.registerUser());

        // --- Login ---
        System.out.print("Enter username to login: ");
        String loginUser = sc.nextLine();

        System.out.print("Enter password to login: ");
        String loginPass = sc.nextLine();

        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();

        String loginStatus = user.returnLoginStatus(loginUser, loginPass, firstName, lastName);
        System.out.println(loginStatus);

        // --- Unlock QuickChat only if login succeeds ---
        if (loginStatus.startsWith("Welcome")) {
            System.out.println("Welcome to QuickChat.");

            // Test Data
            new Message("+27834557896", "Did you get the cake?", "Sent");
            new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored");
            new Message("08388884567", "It is dinner time!", "Sent");
            new Message("+27838884567", "Ok, I am leaving without you.", "Stored");
            new Message("+27834484567", "Yohoooo, I am at your gate.", "Disregard");

            int choice;
            do {
                System.out.println("\nQuickChat Menu:");
                System.out.println("1. Display longest stored message");
                System.out.println("2. Search messages by recipient");
                System.out.println("3. Search message by ID");
                System.out.println("4. Delete message by hash");
                System.out.println("5. Display report");
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        Message longest = Message.findLongestMessage(Message.storedMessages);
                        System.out.println("Longest stored message: " + longest.getText());
                        break;
                    case 2:
                        System.out.print("Enter recipient: ");
                        String rec = sc.nextLine();
                        for (Message m : Message.searchByRecipient(rec)) {
                            System.out.println("Found: " + m.getText());
                        }
                        break;
                    case 3:
                        System.out.print("Enter message ID: ");
                        String id = sc.nextLine();
                        Message found = Message.searchById(id);
                        System.out.println(found != null ? "Found: " + found.getText() : "Message not found.");
                        break;
                    case 4:
                        System.out.print("Enter hash to delete: ");
                        String hash = sc.nextLine();
                        boolean deleted = Message.deleteByHash(hash);
                        System.out.println(deleted ? "Message deleted." : "Message not found.");
                        break;
                    case 5:
                        Message.displayReport();
                        break;
                }
            } while (choice != 0);
        } else {
            System.out.println("Access denied. Please restart and try again.");
        }

        sc.close();
    }
}
