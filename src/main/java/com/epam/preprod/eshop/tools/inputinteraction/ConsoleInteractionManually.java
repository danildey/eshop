package com.epam.preprod.eshop.tools.inputinteraction;

import com.epam.preprod.eshop.consoleio.FacadeDataIo;
import com.epam.preprod.eshop.entity.Product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Scanner;

public class ConsoleInteractionManually implements InputInteraction {
    private String PRODUCT_PATH = "com.epam.preprod.eshop.entity.";
    private final String NUMBER_REGEXP = "^\\d*$";
    private final String BOOLEAN_REGEXP = "^(true|false)$";
    private FacadeDataIo dataIo;
    private Scanner sc;
    private DateTimeFormatter dateTimeFormatter;

    public ConsoleInteractionManually(FacadeDataIo dataIo) {
        this.dataIo = dataIo;
        sc = new Scanner(dataIo.in());
        dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    }

    @Override
    public Integer readInteger(String msg) {
        String line;
        dataIo.print(msg);
        while (sc.hasNext()) {
            line = sc.nextLine();
            if (line.matches(NUMBER_REGEXP)) {
                return Integer.parseInt(line);
            } else {
                dataIo.print("' " + line + " ' Not valid, try again.");
            }
        }
        return null;
    }

    @Override
    public Boolean readBoolean(String msg) {
        String line;
        dataIo.print(msg);
        while (sc.hasNext()) {
            line = sc.nextLine();
            if (line.matches(BOOLEAN_REGEXP)) {
                return Boolean.parseBoolean(line);
            } else {
                dataIo.print("' " + line + " ' Not valid to (true|false), try again.");
            }
        }
        return null;
    }

    @Override
    public String readString(String msg) {
        dataIo.print(msg);
        while (sc.hasNext()) {
            return sc.nextLine();
        }
        return null;
    }

    @Override
    public Long readLong(String msg) {
        String line;
        dataIo.print(msg);
        while (sc.hasNext()) {
            line = sc.nextLine();
            if (line.matches(NUMBER_REGEXP)) {
                return Long.parseLong(line);
            } else {
                dataIo.print("' " + line + " ' Not valid, try again.");
            }
        }
        return null;
    }

    @Override
    public LocalDateTime readLocaleDate(String msg) {
        String line;
        dataIo.print(msg);
        while (sc.hasNext()) {
            line = sc.nextLine();
            try {
                return LocalDateTime.parse(line, dateTimeFormatter);
            } catch (DateTimeParseException ex) {
                dataIo.print("' " + line + " ' Not valid to 'YYYY-MM-DD HH:MM', try again ");
            }
        }
        return null;
    }

    @Override
    public Class<? extends Product> readProductClass(String msg) {
        String line;
        Class<? extends Product> productClass = null;
        dataIo.print(msg);
        while (Objects.isNull(productClass)) {
            line = sc.nextLine();
            try {
                productClass = (Class<? extends Product>) Class.forName(PRODUCT_PATH + line);
            } catch (ClassNotFoundException e) {
                dataIo.print("Wrong ProductName.");
            }
        }
        return productClass;
    }
}
