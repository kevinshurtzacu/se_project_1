import implementation.DataModule;
import implementation.UIModule;
import java.io.*;

public class Main 
{
    public static void main(String[] args) throws IOException 
    {
        if (args.length >= 2 && args[0].toLowerCase().equals("test") &&
                Integer.parseInt(args[1]) == 1)
        {
            UIModule.start();
        }
        else if (args.length >= 2 && args[0].toLowerCase().equals("test") &&
                Integer.parseInt(args[1]) == 2)
        {
            DataModule.loadHistory("implementation/cs374_anon.csv");
            UIModule.start();
        }
        else if (args.length >= 2 && args[0].toLowerCase().equals("test") &&
                Integer.parseInt(args[1]) == 3)
        {
            DataModule.loadPrereqs("implementation/prereq_catalog.csv");
            UIModule.start();
        }
        else if (args.length >= 1 && args[0].toLowerCase().equals("test"))
        {
            DataModule.loadHistory("implementation/cs374_anon.csv");
            DataModule.loadPrereqs("implementation/prereq_catalog.csv");
        }
        else
        {
            System.out.print("Loading Course and Student History...");
            DataModule.loadHistory("implementation/cs374_anon.csv");
        
            System.out.println("\nLoading Prerequisite Data...");
            DataModule.loadPrereqs("implementation/prereq_catalog.csv");
        
            System.out.println("\nDone!");
            UIModule.showHelp();
            UIModule.start();
        }
    }
}

