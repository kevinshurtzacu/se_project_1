package implementation;

import java.util.HashMap;
import java.io.FileReader;
import java.io.FileNotFoundException;
import com.opencsv.CSVReader;


/**
 * Contains methods to load and store information to and from the database.  The
 * DataModule also allows other modules and objects to request information
 * about corresponding CourseDescription and StudentProfile objects.
 *
 * Eventually, the DataModule will allow Courses to check if they meet
 * their prerequsites.
 *
 * @author Kevin Shurtz
 * @version 1.0
 */
public class DataModule
{
    // The canonical list of courseDescriptions and studentProfiles
    static private HashMap<CourseDescription> courseDescriptions = new HashMap<CourseDescription>();
    static private HashMap<StudentProfile> studentProfiles = new HashMap<StudentProfile>();

    public static addCourseDescription(CourseDescription courseDescription)
    {
        courseDescriptions.add(courseDescription);
    }

    public static addStudentProfile(StudentProfile studentProfile)
    {
        studentProfiles.add(studentProfile);
    }

    public static void addStudentProfile()
    {
        // this will be filled shortly
    }

    public static String getCourseSubject(Course course)
    {
        return courseDescriptions.get(course).getCourseSubject();
    }

    public static int getCourseNumber(Course course)
    {
        return courseDescriptions.get(course).getCourseSubject();
    }

    public static String getCourseTitle(Course course)
    {
        return courseDescriptions.get(course).getCourseSubject();
    }

    // load in a CSV file
    public static load(String path) throws FileNotFoundException
    {
        int count = 0; // temporarily limit the number of records produced.
        CSVReader reader = new CSVReader(new FileReader(path));
        String[] record;

        while (record = reader.nextLine())
        {
            for (String value : record)
                System.out.print(value + ", ");
            System.out.println();
        }
    }
}