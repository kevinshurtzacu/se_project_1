package implementation;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;


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
public class DataModule {
    // The canonical list of courseDescriptions and studentProfiles
    static private HashSet<CourseDescription> courseDescriptions = new HashSet<CourseDescription>();
    static private HashSet<StudentProfile> studentProfiles = new HashSet<StudentProfile>();

    public static void addCourseDescription(CourseDescription courseDescription) {
        courseDescriptions.add(courseDescription);
    }

    public static void addStudentProfile(StudentProfile studentProfile) {
        studentProfiles.add(studentProfile);
    }

    public static String getCourseSubject(Course course) {
        // Sets do not have accessors methods, making iteration necessary
        Iterator<CourseDescription> it = courseDescriptions.iterator();

        while (it.hasNext()) {
            CourseDescription description = it.next();

            if (description.equals(course))
                return description.getCourseSubject();
        }

        // This event should never happen
        return null;
    }

    public static String getCourseNumber(Course course) {
        // Sets do not have accessors methods, making iteration necessary
        Iterator<CourseDescription> it = courseDescriptions.iterator();

        while (it.hasNext()) {
            CourseDescription description = it.next();

            if (description.equals(course))
                return description.getCourseNumber();
        }

        // This event should never happen
        return "";
    }

    public static String getCourseTitle(Course course) {
        // Sets do not have accessors methods, making iteration necessary
        Iterator<CourseDescription> it = courseDescriptions.iterator();

        while (it.hasNext()) {
            CourseDescription description = it.next();

            if (description.equals(course))
                return description.getCourseTitle();
        }

        // This event should never happen
        return null;
    }

    // load in a CSV file
    public static void load(String path) throws IOException {
        Scanner scan = new Scanner(new FileReader(path));
        String line;        // each row of data from the csv (String)
        String[] record;    // each row of data from the csv (Array)

        // discard the first line
        String temp = scan.nextLine();
        String[] headers = parseRecord(temp);

        while (scan.hasNextLine()) {
            line = scan.nextLine();
            record = parseRecord(line);

            System.out.println(record[50]);


//            System.out.println("\n___________________________");
//
//            // CourseDescription - These work
//            System.out.println("CourseDescription: ");
//            System.out.println("?   Course identifier: ?");
//            System.out.println("40  Course subject: " + record[40]);
//            System.out.println("44  Course title: " + record[44]);
//            System.out.println("42  Course number: " + record[42]);
//            System.out.println();
//
//            // CRN is 35
//
//            // Course - This works
//            System.out.println("\nCourse: ");
//            System.out.println("?   Course identifier: ?");
//            System.out.println("43  Course section: " + record[43]);
//            System.out.println("1   Course year: " + record[1]);
//            System.out.println("1   Course term: " + record[1]);
//            System.out.println("55  Course grade: " + record[55]);
//            System.out.println("1   Is active: " + record[1]);
//
//            // StudentProfile - These work (and account for all of Student Profile)
//            System.out.println("\nStudentProfile: ");
//            System.out.println("56  Banner ID: " + record[56]);
//            System.out.println("57  First: " + record[57]);
//            System.out.println("58  Last: " + record[58]);
//            System.out.println("64  ACU Email: " + record[64]);
//
//            // Student
//            System.out.println("\nStudent: ");
//            System.out.println("57  Banner ID: " + record[56]);
//            System.out.println("43  Student section: " + record[43]);
//            System.out.println("1   Student year: " + record[1]);
//            System.out.println("1   Student term: " + record[1]);
//            System.out.println("55  Student grade: " + record[55]);
//            System.out.println("1   Is active: " + record[1]);

        }
    }

    /**
     * Parses a single record from a CSV file.  This parser may not perfectly adhere to
     * RFC 4180 standards.  Escaping, for example, is implemented naively, and may behave
     * unpredictably in some edge cases.
     *
     * This parse function separates on commas.  A field wrapped in quotes may have commas
     * inside without issue.  As per RFC standards, a quote may be escaped by another quote.
     * For example, "This "" String" would be parsed as 'This " String'.
     *
     * This function can be easily overloaded with variants that use different delimeters,
     * clause markers, and escape characters.
     *
     * @param line  a line of data to be parsed, separated by commas
     * @return      a String array of tokens parsed from the line
     */
    private static String[] parseRecord(String line)
    {
        char[] record = line.toCharArray();                         // the array of characters in the line
        ArrayList<Character> token = new ArrayList<Character>();    // the characters of a single token
        ArrayList<String> result = new ArrayList<String>();         // the final array of tokens
        int startPoint;                                             // the beginning index of a parsable String

        // settings
        boolean delimOn = true;     // whether the delimeter is being recognized
        char delim = ',';           // the character used to separate tokens
        char clause = '"';          // the character used to designate a field, which may include delimeters
        char escape = '"';          // the character used to escape a special character

        // prepare for the first iteration
        startPoint = 0;

        // parse each token
        for (int index = 0; index < record.length; ++index)
        {
            char curChar = record[index];

            // if we are in a clause, we ignore the delimeter; otherwise, we proceed as usual
            if (!delimOn)
            {
                // handle special characters
                if (record[index] == escape && (index + 1 < record.length && record[index + 1] == clause))
                {
                    token.add(clause);  // add the escaped clause begin/end character
                    ++index;            // move up two indeces by the end of the iteration
                }
                else if (record[index] == clause)
                {
                    delimOn = true;     // end a clause
                }
                else
                {
                    token.add(record[index]);   // add a character to the token
                }
            }
            else
            {
                if (record[index] == clause)
                {
                    delimOn = false;    // begin a clause
                }
                else if (record[index] == delim || index == record.length)
                {
                    StringBuilder completeToken = new StringBuilder();

                    for (char character : token)
                    {
                        completeToken.append(character);
                    }

                    token.clear();                          // begin a new token
                    result.add(completeToken.toString());   // add the new token
                    startPoint = index + 1;                 // start point for next token
                }
                else
                {
                    token.add(record[index]);   // add a character to the token
                }
            }
        }

        // return the String array of tokens
        return result.toArray(new String[result.size()]);
    }

    private StudentProfile makeStudentProfile(Integer banner, String first, String last, String mail)
    {
        if (banner == null || first == null || last == null || mail == null)
            return null;

        return new StudentProfile(banner, first, last, mail);
    }

    private Student makeStudent(Integer banner, String section, Integer year, Term cTerm, Double grade, Boolean now)
    {
        if (banner == null || section == null || year == null || cTerm == null || grade == null || now == null)
            return null;

        return new Student(banner, section, year, cTerm, grade, now);
    }

    private CourseDescription makeCourseDescription(String subject, String courseNum, String title)
    {
        if (subject == null || courseNum == null || title == null)
            return null;

        return new CourseDescription(subject, courseNum, title);
    }

    private Course makeCourse(String subject, String courseNum, String section, Integer year, Term cTerm, String grade, Boolean now)
    {
        if (section == "" || courseNum == "" || year == 0 || cTerm == null || grade == "" || now == null)
            return null;

        return new Course(subject, courseNum, section, year, cTerm, grade, now);
    }
}