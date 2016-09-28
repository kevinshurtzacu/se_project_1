package implementation;
import java.util.HashMap;

public class DataModule
{
    static HashMap<CourseDescription> courseDescriptions;
    static HashMap<StudentProfile> studentProfiles;

    public DataModule()
    {
        courseDescriptions = new HashMap<CourseDescription>();
        studentProfiles = new HashMap<StudentProfile>();
    }
}