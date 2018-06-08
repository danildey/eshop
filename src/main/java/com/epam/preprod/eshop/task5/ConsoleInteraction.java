package com.epam.preprod.eshop.task5;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsoleInteraction {
    private final String NUMBER_REGEXP = "^\\d*$";
    private final String BOOLEAN_REGEXP = "^[10]$";
    private final String EXTENSION_REGEXP = "^\\.[^.]+$";
    private Scanner sc;
    private DateTimeFormatter dateTimeFormatter;

    public ConsoleInteraction() {
        sc = new Scanner(System.in);
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }


    public Boolean useFilter(String msg) {
        String line;
        System.out.println(msg);
        while (sc.hasNext()) {
            line = sc.nextLine();
            if (line.matches(BOOLEAN_REGEXP)) {
                return line.equals("1");
            } else {
                System.out.println("' " + line + " ' Not valid, try again.");
            }
        }
        return null;
    }

    public String readExtension(String msg) {
        String line;
        System.out.println(msg);
        while (sc.hasNext()) {
            line = sc.nextLine();
            if (line.matches(EXTENSION_REGEXP)) {
                return line;
            } else {
                System.out.println("' " + line + " ' Not valid to ' .extension ', try again.");
            }
        }
        return null;
    }

    public String readString(String msg) {
        System.out.println(msg);
        if (sc.hasNext()) {
            return sc.nextLine();
        }
        return null;
    }

    public Long readLong(String msg) {
        String line;
        System.out.println(msg);
        while (sc.hasNext()) {
            line = sc.nextLine();
            if (line.matches(NUMBER_REGEXP)) {
                return Long.parseLong(line);
            } else {
                System.out.println("' " + line + " ' Not valid, try again.");
            }
        }
        return null;
    }

    public LocalDateTime readLocalDate(String msg) {
        String line;
        System.out.println(msg);
        while (sc.hasNext()) {
            line = sc.nextLine();
            try {
                return LocalDateTime.parse(line, dateTimeFormatter);
            } catch (DateTimeParseException ex) {
                System.out.println("' " + line + " ' Not valid to 'YYYY-MM-DD HH:MM', try again ");
            }
        }
        return null;
    }
}
