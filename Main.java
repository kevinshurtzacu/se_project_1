import implementation.DataModule;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        DataModule.loadHistory("implementation/cs374_anon.csv");
        DataModule.loadPrereqs("implementation/prereq_catalog.csv");
    }
}

