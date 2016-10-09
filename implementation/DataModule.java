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

    // These will be calculated later
    static private currentYear = 2016;
    static private currentTerm = Term.FALL;

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

        while (it.hasNext())
        {
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

            // entities to load
            CourseDescription courseDesc;
            Course course;
            Student student;
            StudentProfile studentProfile;

            // create course description
            String subject = record[40];
            String title = record[44];
            String number = record[42];

            courseDesc = makeCourseDescription(subject, title, number);

            // create course and student
            subject = record[40];
            title = record[44];
            String section;
            Integer banner;
            Integer year;
            Term term;
            String grade;
            Boolean isActive;

            // determine the year
            section = record[43];
            banner = record[56];
            String timeFrame = record[1];
            year = Integer.parseInt(timeFrame.substring(0, 4));

            // determine the term
            int termCode = Integer.parseInt(timeFrame.substring(4, 6));

            if (termCode == 10)
                term = Term.FALL;
            else if (termCode == 20)
                term = Term.SPRING;
            else if (termCode == 30)
                term = Term.SUMMER;
            else
                term = null;

            grade = record[55];

            // determine if active
            if (term == currentTerm && year == currentYear)
                isActive = true;
            else
                isActive = false;

            course = makeCourse(subject, number, section, year, term, grade, isActive);
            student = makeStudent(banner, section, year, term, grade, isActive);

            // create student profile
            banner = record[56];
            String first;
            String last;
            String email;

            first = record[57];
            last = record[58];
            email = record[64];

            studentProfile = makeStudentProfile(banner, first, last, email);
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

    private Student makeStudent(Integer banner, String section, Integer year, Term cTerm, String grade, Boolean now)
    {
        if (banner == null || section == null || year == null || cTerm == null || grade == "" || now == null)
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
        if (subject == "" || courseNum == "" || section == "" || year == 0 || cTerm == null || grade == "" || now == null)
            return null;

        return new Course(subject, courseNum, section, year, cTerm, grade, now);
    }
}