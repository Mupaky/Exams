package com.supplyboost;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Registration {

    public static void main(String[] args) {
        String regex = "U\\$[A-Z][a-z]{2,}U\\$P\\@\\$[a-zA-z]{5,}\\d{1,}P\\@\\$";
        Pattern pattern = Pattern.compile(regex);

        int successfulRegistrations = 0;

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        String[] inputs = new String[n];

        for (int i = 0; i < n; i++) {
            inputs[i] = scanner.nextLine();
        }
        checkRregistration(inputs, pattern, successfulRegistrations);

        scanner.close();
    }

    private static void checkRregistration(String[] inputs, Pattern pattern, int successfullRegistraionsCounter) {
        for(String input : inputs){
            Matcher matcher = pattern.matcher(input);
            if (matcher.matches()) {
                System.out.println("Registration was successful");
                printUserName(input);
                printPassword(input);
                successfullRegistraionsCounter += 1;

            } else {
                System.out.println("Invalid username or password");
            }
        }
        System.out.println("Successful registrations: " + successfullRegistraionsCounter);
    }

    private static void printUserName(String input){
        String regex = "U\\$(.*?)U\\$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            System.out.printf("Username: " + matcher.group(1));
        }
    }

    private static void printPassword(String input){
        String regex = "P\\@\\$(.*?)P\\@\\$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            System.out.println(", Password: " + matcher.group(1));
        }
    }


}
