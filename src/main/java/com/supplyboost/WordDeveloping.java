package com.supplyboost;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordDeveloping {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder commands = new StringBuilder();

        while (true) {
            String input = scanner.nextLine();
            if (input.equals("End")) {
                break;
            }

            String[] commandParts = input.split(" ", 2);
            String command = commandParts[0];

            switch (command) {
                case "Add":
                    String stringToBeAdded = commandParts[1];
                    commands.append(stringToBeAdded);
                    break;
                case "Upgrade":
                    char charToUpgrade = commandParts[1].charAt(0);
                    upgradeCharacter(commands, charToUpgrade);
                    break;
                case "Print":
                    System.out.println(commands);
                    break;
                case "Index":
                    char charToFind = commandParts[1].charAt(0);
                    printIndexes(commands, charToFind);
                    break;
                case "Remove":
                    String substringToRemove = commandParts[1];
                    removeSubstring(commands, substringToRemove);
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }

    }

    private static void upgradeCharacter(StringBuilder sb, char charToUpgrade) {
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == charToUpgrade) {
                sb.setCharAt(i, (char) (charToUpgrade + 1));
            }
        }
    }

    private static void printIndexes(StringBuilder sb, char charToFind) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == charToFind) {
                indexes.add(i);
            }
        }
        if (indexes.isEmpty()) {
            System.out.println("None");
        } else {
            for (int index : indexes) {
                System.out.print(index + " ");
            }
            System.out.println();
        }
    }

    private static void removeSubstring(StringBuilder sb, String substringToRemove) {
        int index = sb.indexOf(substringToRemove);
        while (index != -1) {
            sb.delete(index, index + substringToRemove.length());
            index = sb.indexOf(substringToRemove);
        }
    }
}
