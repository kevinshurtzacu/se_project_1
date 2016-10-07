package implementation;

import java.util.HashSet;
import java.util.Iterator;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


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
    static private HashSet<CourseDescription> courseDescriptions = new HashSet<CourseDescription>();
    static private HashSet<StudentProfile> studentProfiles = new HashSet<StudentProfile>();

    public static void addCourseDescription(CourseDescription courseDescription)
    {
        courseDescriptions.add(courseDescription);
    }

    public static void addStudentProfile(StudentProfile studentProfile)
    {
        studentProfiles.add(studentProfile);
    }

    public static String getCourseSubject(Course course)
    {
        // Sets do not have accessors methods, making iteration necessary
        Iterator<CourseDescription> it = courseDescriptions.iterator();

        while (it.hasNext())
        {
            CourseDescription description = it.next();

            if (description.equals(course))
                return description.getCourseSubject();
        }

        // This event should never happen
        return null;
    }

    public static int getCourseNumber(Course course)
    {
        // Sets do not have accessors methods, making iteration necessary
        Iterator<CourseDescription> it = courseDescriptions.iterator();

        while (it.hasNext())
        {
            CourseDescription description = it.next();

            if (description.equals(course))
                return description.getCourseNumber();
        }

        // This event should never happen
        return 0;
    }

    public static String getCourseTitle(Course course)
    {
        // Sets do not have accessors methods, making iteration necessary
        Iterator<CourseDescription> it = courseDescriptions.iterator();

        while (it.hasNext())
        {
            CourseDescription description = it.next();

            if (description.equals(course))
                return description.getCourseTitle();
        }

        // This event should never happen
        return null;
    }

    // load in a CSV file
    public static void load(String path) throws IOException
    {
        Scanner scan = new Scanner(new FileReader(path));
        String line;        // each row of data from the csv (String)
        String[] record;    // each row of data from the csv (Array)

        // discard the first line
        scan.nextLine();

        while (scan.hasNextLine())
        {
            line = scan.nextLine();
            record = line.split(",");

            // CourseDescription - These work
            System.out.println("Course subject: " + record[40]);
            System.out.println("Course title: " + record[44]);
            System.out.println("Course number: " + record[42]);
            System.out.println("CRN: " + record[35]);

            // Course - This works
            // System.out.println("Course Ext: " + record[43]);

            // StudentProfile - These work (and account for all of Student Profile)
            // System.out.println("Banner ID: " + record[57]);
            // System.out.println("First: " + record[59]);
            // System.out.println("Last: " + record[60]);
            // System.out.println("ACU Email: " + record[65]);

            System.out.println();
        }
    }

    private StudentProfile makeStudentProfile(Integer banner, String first, String last, String mail)
    {
        if (banner == null || first == null || last == null || mail == null)
            return null;

        return new StudentProfile(banner, first, last, mail);
    }

    // private Student makeStudent()

    private CourseDescription makeCourseDescription(Integer crnNum, String subject, Integer courseNum, String title)
    {
        if (crnNum == null || subject == null || courseNum == null || title == null)
            return null;

        return new CourseDescription(crnNum, subject, courseNum, title);
    }

    private Course makeCourse(Integer crnNum, String ext, Integer year, String cTerm, Double grade, Boolean now)
    {
        if (crnNum == null || ext == null || ext == null || year == null || cTerm == null || grade == null || now == null)
            return null;

        return null;
    }
}