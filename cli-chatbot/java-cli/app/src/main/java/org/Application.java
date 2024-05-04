package org;

import java.util.Scanner;

import org.chatcompletion.ChatCompletion;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatCompletion chatCompletion = new ChatCompletion();
        boolean loop = true;
        String question;
        String input;
        while (loop) {
            System.out.println("Ask your question: ");
            question = scanner.nextLine();
            System.out.println(chatCompletion.askAi(question));
            System.out.println("Do you want to continue? (y/n)");
            input = scanner.nextLine();
            if (input.toLowerCase().equals("n")) {
                loop = false;
                scanner.close();
            }
        }

    }
}
