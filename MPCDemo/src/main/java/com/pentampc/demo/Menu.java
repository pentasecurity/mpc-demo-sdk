package com.pentampc.demo;

public class Menu {

    public static int First() {
        Options options = new Options();
        options.addOption(new Option(1, "Login"));
        options.addOption(new Option(2, "Create member"));
        options.addOption(new Option(9, "Exit"));

        options.printMenu();
        return options.InputMenu("Select Menu.");
    }
    public static int MainMenu() {
        Options options = new Options();
        options.addOption(new Option(1, "Creation of MPC Group"));
        options.addOption(new Option(2, "Signing"));
        options.addOption(new Option(3, "Member List"));
        options.addOption(new Option(4, "Update AccessToken"));
        options.addOption(new Option(5, "My MPC Group"));
        options.addOption(new Option(9, "Exit"));

        options.printMenu();
        return options.InputMenu("Select Menu.");
    }

    public static int MPCGroupCreationMenu() {
        Options options = new Options();
        options.addOption(new Option(1, "MPC Group Participation Request"));
        options.addOption(new Option(2, "Join in the creation of MPC Group"));
        options.addOption(new Option(8, "Previous Menu"));
        options.addOption(new Option(9, "Exit"));

        options.printMenu();
        return options.InputMenu("Select Menu.");
    }


    public static int SigningMenu() {
        Options options = new Options();
        options.addOption(new Option(1, "Signing Participation Request"));
        options.addOption(new Option(2, "Join in the Signing"));
        options.addOption(new Option(8, "Previous Menu"));
        options.addOption(new Option(9, "Exit"));

        options.printMenu();
        return options.InputMenu("Select Menu.");
    }

}
