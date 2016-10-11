package implementation;

import java.util.Scanner;

public class UIModule
{
    public static void start()
    {
        boolean run = true;
        Scanner scan = new Scanner(System.in);

        while (run) {
            String input = "";

            prompt();
            input = scan.nextLine();

            // createt tokens from user input
            String[] tokens;

            if (!input.contains(" "))
            {
                tokens = new String[1];
                tokens[0] = input;
            }
            else
                tokens = input.split("\\w+");

            // Help
            if (tokens[0].toUpperCase().equals("HELP"))
                showHelp();

            // Query
            if (tokens[0].toUpperCase().equals("SHOW"))
            {
                if (tokens[1].toUpperCase().equals("INELIGABLE") &&
                        tokens[2].toUpperCase().equals("IN"))
                {

                }
            }

            // Exit
            if (tokens[0].toUpperCase().equals("EXIT"))
                run = false;
        }
    }

    private static void showHelp()
    {
        System.out.println("Version 1.0");
        System.out.println("Authors:\n" +
            "\tKevin Shurtz\n" +
            "\tIsaak Ramirez\n" +
            "\tVirginia Pettit\n");

        System.out.println("\nThis system allows users to identify all students currently " +
                "in a course who do not have sufficient prerequisites to take it.  All " +
                "user prompts follow the '>' character, and are case insensitive.");

        System.out.println("\nTo request all ineligable students in a course, enter:");
        System.out.println("\t'SHOW INELIGABLE IN [SUBJECT] [NUMBER] [TITLE]'");

        System.out.println("\nTo exit, enter:");
        System.out.println("\t'EXIT'");

        System.out.println("\nTo show help, enter:");
        System.out.println("\t'HELP'\n");
    }

    private static void prompt()
    {
        System.out.print("> ");
    }
}