package org;

import java.util.Scanner;

import org.chatcompletion.ChatCompletion;

public class Application {
    public static void main(String[] args) {
        ChatCompletion chatCompletion;
        Scanner scanner = new Scanner(System.in);
        if (args.length > 0 && args.length < 3) {
            int tokens = Integer.parseInt(args[0]);   
            String aiPersona = "";     
            if(args[1].equals("true") || args[1].equals("false")) {
                System.out.println("Enter your persona: ");
                aiPersona= scanner.nextLine();
            }
            chatCompletion = new ChatCompletion(tokens, aiPersona);
        } else {
            chatCompletion = new ChatCompletion();
        }
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
