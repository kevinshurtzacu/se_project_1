package implementation;

import java.util.Scanner;
import java.io.IOException;

public class UIModule
{
    public static void start()
    {
        boolean run = true;
        Scanner scan = new Scanner(System.in);

        while (run) {
            String input = "";

            // present input prompt icon
            prompt();

            // receive user input
            input = scan.nextLine();

            // created tokens from user input
            String[] tokens;

            if (!input.contains(" "))
            {
                tokens = new String[1];
                tokens[0] = input;
            }
            else
                tokens = input.split("\\s+");

            if (tokens[0].toUpperCase().equals("HELP"))
            {
                // Help
                showHelp();
            }
            else if (tokens[0].toUpperCase().equals("SHOW"))
            {
                // Query
                if (tokens[1].toUpperCase().equals("INELIGIBLE") &&
                        tokens[2].toUpperCase().equals("IN"))
                {
                    // Get subject and number
                    String subject = tokens[3];
                    String number = tokens[4];

                    System.out.println("\t" + subject + " " + number);

                    // query for data
                    DataModule.printIneligible(subject, number);
                    DataModule.printEligible(subject, number);
                }
            }
            else if (tokens[0].toUpperCase().equals("SET"))
            {
                // Set Term and Year
                if (tokens[1].toUpperCase().equals("TERM"))
                {
                    if (tokens[2].toUpperCase().equals("FALL"))
                    {
                        DataModule.currentTerm = Term.FALL;
                    }
                    else if (tokens[2].toUpperCase().equals("SPRING"))
                    {
                        DataModule.currentTerm = Term.SPRING;
                    }
                    else if (tokens[2].toUpperCase().equals("SUMMER"))
                    {
                        DataModule.currentTerm = Term.SUMMER;
                    }
                    else
                        showError("term value not recognized, terms are 'FALL', 'SPRING', 'FALL'");
                }
                else if (tokens[1].toUpperCase().equals("YEAR"))
                {
                    // Check that the value is a number
                    boolean isNumber = true;

                    for (char character : tokens[2].toCharArray())
                    {
                        if (!Character.isDigit(character))
                            isNumber = false;
                    }

                    if (isNumber)
                        DataModule.currentYear = Integer.parseInt(tokens[2]);
                    else
                        showError("please enter a numeric value");
                }
                else
                {
                    // Error
                    showError("entity not recognized, please use 'TERM' or 'YEAR'");
                }
            }
            else if (tokens[0].toUpperCase().equals("EXIT"))
            {
                // Exit
                run = false;
            }
            else
            {
                // Error
                showError("No such query.  Type 'HELP' for help.");
            }

            /*
            This code does not really work yet.

            else if (tokens[0].toUpperCase().equals("RELOAD"))
            {
                try
                {
                    // Reload Courses/Students or Prereqs
                    if (tokens[1].toUpperCase().equals("HISTORY"))
                    {
                        System.out.println("Reloading history...");
                        DataModule.loadPrereqs("cs374_anon.csv");
                        System.out.println("Done!");
                    }

                    if (tokens[1].toUpperCase().equals("PREREQUISITES"))
                    {
                        System.out.println("Reloading prerequisites...");
                        DataModule.loadHistory("prereq_catalog.csv");
                        System.out.println("Done!");
                    }
                }
                catch (IOException e)
                {
                    System.out.println("File not found - file may have been renamed or removed");
                }
                catch (Exception e)
                {
                    System.out.println("An unknown exception has occurred");
                }
            }
            */
        }
    }

    public static void showInfo()
    {
        System.out.println("Version 1.0");
        System.out.println("Authors:\n" +
                "* Kevin Shurtz\n" +
                "* Isaak Ramirez\n" +
                "* Virginia Pettit\n");
    }

    public static void showHelp()
    {
        System.out.println("\nThis system allows users to identify all students currently " +
                "in a course who do not have sufficient prerequisites to take it.  All " +
                "user prompts follow the '>' character, and are case insensitive.");

        System.out.println("\nBasic Query Syntax:");
        System.out.println("'SHOW INELIGIBLE IN [SUBJECT] [NUMBER]'");

        System.out.println("\nGeneral Commands");
        System.out.println("'EXIT'\t\texits application'");
        System.out.println("'HELP'\t\trequest this help page");

        System.out.println("\nChange date or term:");
        System.out.println("'SET TERM [FALL|SPRING|SUMMER]'");
        System.out.println("'SET YEAR [VALUE]");
    }

    public static void showError(String errorDesc)
    {
        // Print error message
        System.out.println("Error: " + errorDesc);
    }

    public static void showError()
    {
        System.out.println("Error: command not recognized");
    }

    private static void prompt()
    {
        System.out.print("> ");
    }
}