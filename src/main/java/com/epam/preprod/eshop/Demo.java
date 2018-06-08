package com.epam.preprod.eshop;

import com.epam.preprod.eshop.command.Command;
import com.epam.preprod.eshop.command.CommandController;

import java.util.Scanner;

public class Demo {

    public static void main(String[] args) {
        Initializer init = new Initializer();
        CommandController cc = init.getController();
        cc.getCommand(11).execute();
        Scanner sc = new Scanner(System.in);
        String commandNumber;
        do {
            commandNumber = sc.next();
            try {
                Command command = cc.getCommand(Integer.parseInt(commandNumber));
                command.execute();
            } catch (NumberFormatException ex) {
                System.out.println(("' " + commandNumber + " ' is not a number."));
            }
        }
        while (!commandNumber.equals("0"));
        sc.close();
    }
}
