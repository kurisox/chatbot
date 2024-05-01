package org;

import java.util.Scanner;

import org.aiconnection.AIConnector;

public class Application {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your question: ");
        String question = scanner.nextLine();
        scanner.close();

        AIConnector.getInstance().askAi(question);
    }
}
