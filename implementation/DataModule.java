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
public class DataModule
{
    // The canonical list of courseDescriptions and studentProfiles
    static private HashSet<CourseDescription> courseDescriptions = new HashSet<CourseDescription>();
    static private HashSet<StudentProfile> studentProfiles = new HashSet<StudentProfile>();

    // All prerequisites for all course
    static private ArrayList<Prereq> allPrereqs = new ArrayList<Prereq>();

    // These will be calculated later
    static public int currentYear = 2015;
    static public Term currentTerm = Term.FALL;

    /**
     * Adds a CourseDescription to the set of CourseDescriptions.  This function exists
     * primarily for testing purposes.
     *
     * @param courseDescription a CourseDescription object
     */
    public static void addCourseDescription(CourseDescription courseDescription)
    {
        courseDescriptions.add(courseDescription);
    }

    /**
     * Adds a StudentProfile to the set of StudentProfiles.  This function exists
     * primarily for testing purposes.
     *
     * @param studentProfile    a StudentProfile object
     */
    public static void addStudentProfile(StudentProfile studentProfile) {
        studentProfiles.add(studentProfile);
    }

    /**
     * A helper function to locate a CourseDescription and insert a Student object.
     *
     * @param student   the Student object to be added
     * @param subject   the CourseDescription subject (to identify the CourseDescription)
     * @param courseNum the CourseDescription number (to identify the CourseDescription)
     * @param title     the CourseDescription title (to identify the CourseDescription)
     */
    private static void addStudent(Student student, String subject, String courseNum)
    {
        // add a student to a course description
        for (CourseDescription description : courseDescriptions)
        {
            if (description.getCourseSubject().equals(subject) &&
                    description.getCourseNumber().equals(courseNum))
            {
                description.addStudent(student);
            }
        }
    }

    /**
     * A helper function to iterate through the student profiles and return the one
     * that matches the Student object.
     *
     * @param student   the Student object to be matched to a StudentProfile
     * @return          the StudentProfile that matches student
     */
    private static StudentProfile getStudentProfile(Student student)
    {
        for (StudentProfile profile : studentProfiles)
        {
            if (profile.equals(student))
                return profile;
        }

        // this should never happen
        return null;
    }

    /**
     * A helper function to iterate through the course descriptions and return the one
     * that matches the Course object.
     *
     * @param course    the CourseDescription object to be matched to a Course
     * @return          the CourseDescription that matches course
     */
    private static CourseDescription getCourseDescription(Course course)
    {
        for (CourseDescription description : courseDescriptions)
        {
            if (description.equals(course))
                return description;
        }

        // this should never happen
        return null;
    }

    /**
     * A helper function to iterate through the course descriptions and return the one
     * that matches the Course object.
     *
     * @param course    the CourseDescription object to be matched to a Course
     * @return          the CourseDescription that matches course
     */
    private static CourseDescription getCourseDescription(String subject, String courseNum)
    {
        for (CourseDescription description : courseDescriptions)
        {
            if (description.getCourseSubject().equals(subject) &&
                    description.getCourseNumber().equals(courseNum))
            {
                return description;
            }
        }

        // this should never happen
        return null;
    }

    /**
     * A helper function to locate a CourseDescription and insert a Student object.
     *
     * @param student   the Student object to be added
     * @param subject   the CourseDescription subject (to identify the CourseDescription)
     * @param courseNum the CourseDescription number (to identify the CourseDescription)
     * @param title     the CourseDescription title (to identify the CourseDescription)
     */
    private static void addCourse(Course course, int bannerID)
    {
        // add a student to a course description
        for (StudentProfile profile : studentProfiles)
        {
            if (profile.getBannerID() == bannerID)
                profile.addCourse(course);
        }
    }

    /**
     * Load in a CSV file for students, courses, course descriptions, and student profiles.
     *
     * @param path      the path to the CSV file to be loaded in
     * @throws IOException
     */
    public static void loadHistory(String path) throws IOException
    {
        Scanner scan = new Scanner(new FileReader(path));
        String line;        // each row of data from the csv (String)
        String[] record;    // each row of data from the csv (Array)

        // discard the first line
        scan.nextLine();

        while (scan.hasNextLine())
        {
            line = scan.nextLine();
            record = parseRecord(line);

            // entities to load
            CourseDescription courseDesc;
            Course course;
            Student student;
            StudentProfile studentProfile;

            // create course description
            String subject = null;
            String number = null;
            String title = null;

            // assign subject, record, number
            subject = record[40];
            number = record[42];
            title = record[44];

            // make course description
            courseDesc = makeCourseDescription(subject, number, title);

            // create course and student
            subject = record[40];   // used previously
            number = record[42];    // used previously
            title = record[44];     // used previously

            Integer banner = null;
            String section = null;
            Integer year = null;
            Term term = null;
            String grade = null;
            Boolean isActive = null;

            // assign banner
            banner = Integer.parseInt(record[56]);

            // assign section
            section = record[43];

            String timeFrame = record[1];

            if (timeFrame.length() > 0)
            {
                // determine the year
                year = Integer.parseInt(timeFrame.substring(0, 4));

                // determine the term
                int termCode = Integer.parseInt(timeFrame.substring(4, 6));

                if (termCode == 10)
                    term = Term.FALL;
                else if (termCode == 20)
                    term = Term.SPRING;
                else if (termCode == 30)
                    term = Term.SUMMER;
            }

            grade = record[55];

            // determine if active
            if (term == currentTerm && year == currentYear)
                isActive = true;
            else
                isActive = false;

            // make course and student
            course = makeCourse(subject, number, title, section, year, term, grade, isActive);
            student = makeStudent(banner, section, year, term, grade, isActive);

            // create student profile
            banner = Integer.parseInt(record[56]);
            String first;
            String last;
            String email;

            first = record[57];
            last = record[58];
            email = record[64];

            // make student profile
            studentProfile = makeStudentProfile(banner, first, last, email);

            // System.out.println(banner);
            // System.out.println(subject + " " + number);

            // add and assemble entities
            if (courseDesc != null)
                courseDescriptions.add(courseDesc);

            if (studentProfile != null)
            {
                studentProfiles.add(studentProfile);
            }

            // if (studentProfile != null)
            //     studentProfiles.add(studentProfile);

            // System.out.println(courseDesc.getStudentBannerID());
            if (student != null && courseDesc != null)
            {
                addStudent(student,
                        courseDesc.getCourseSubject(),
                        courseDesc.getCourseNumber());
            }

            if (course != null && studentProfile != null)
                addCourse(course, studentProfile.getBannerID());

            // add course to student profiles once such methods are implemented
        }

        // In actuality, there are about 1605      unique courses
        //                         about 9359-9485 unique students


        System.out.println(" [Set Size: " + courseDescriptions.size() + "]");
    }

    public static int count = 0;
    /**
     * Applies all Prereqs to their respective CourseDescriptions.  This function
     * is only useful if loadHistory has already been called.  Otherwise, there
     * exist no CourseDescriptions to which a Prereq may be applied.
     *
     * @param path  the file path to the CSV of prerequisites
     * @throws IOException
     */
    public static void loadPrereqs(String path) throws IOException
    {
        Scanner scan = new Scanner(new FileReader(path));
        String line;        // each row of data from the csv (String)
        String[] record;    // each row of data from the csv (Array)

        // find and apply each CSV
        while (scan.hasNextLine())
        {
            line = scan.nextLine();
            record = parseRecord(line);

            // Identify which course description
            String[] descriptionID = record[0].split(",");
            String descriptionSubject = descriptionID[0];
            String descriptionNumber = descriptionID[1];
            String descriptionTitle = descriptionID[2];

            String[] prereqID;
            String prereqSubject;
            String prereqNumber;
            String prereqTitle;
            String prereqGrade;

            /*
            System.out.println("\n" + "LINE " + (++count));
            System.out.println("p subject: " + descriptionSubject);
            System.out.println("p number: " + descriptionNumber);
            System.out.println("p title: " + descriptionTitle);
            */

            // System.out.println(" RF: " + record.length);

            // System.out.print("PREREQ: " + descriptionSubject + " " + descriptionNumber + "\t");
            // boolean flag = false;
            for (CourseDescription description : courseDescriptions)
            {
                // if there is a course description that matches the course description for this record
                if (description.getCourseSubject().equals(descriptionSubject) &&
                        description.getCourseNumber().equals(descriptionNumber))
                {
                    // System.out.println("\tMATCH:  " + descriptionSubject + " " + descriptionNumber + " " + descriptionTitle);
                    /*
                    flag = true;
                    boolean flag2 = false;
                    if (descriptionSubject.equals("CS") && descriptionNumber.equals("130"))
                        flag2 = true;
                    // add each Prereq to the corresponding CourseDescription
                    System.out.println(record.length);
                    */
                    for (int index = 1; index < record.length; ++index)
                    {
                        // System.out.println("\tCHECK SPOT: " + index);
                        prereqID = record[index].split(",");

                        /*
                        if (flag2) {
                            for (String aarg : prereqID)
                                System.out.print(aarg + " ");
                            System.out.println();
                        }
                        */

                        prereqSubject = prereqID[0];
                        prereqNumber = prereqID[1];
                        prereqTitle = prereqID[2];
                        prereqGrade = prereqID[3];

                        /*
                        if (flag2) {
                            System.out.println("\t" + prereqSubject);
                            System.out.println("\t" + prereqNumber);
                            System.out.println("\t" + prereqTitle);
                            System.out.println("\t" + prereqGrade);

                            System.out.println("\nADD PREREQ");
                            System.out.println("\tsubject: " + prereqSubject);
                            System.out.println("\tnumber: " + prereqNumber);
                            System.out.println("\ttitle: " + prereqTitle);
                            System.out.println("\tgrade: " + prereqGrade);
                        }
                        */
                        description.addPrereq(new Prereq(prereqSubject, prereqNumber, prereqTitle, prereqGrade));
                    }
                }
            }
            // System.out.println(flag ? "MATCH" : "NO MATCH");

            /*
            for (CourseDescription description : courseDescriptions)
            {
                System.out.println("\n" + description.getCourseSubject() + " " + description.getCourseNumber() +
                        " " + description.getCourseTitle());
                System.out.println("c subject: " + description.getCourseSubject());
                System.out.println("c number: " + description.getCourseNumber());
                System.out.println("c title: " + description.getCourseTitle());
            }
            */
        }

        /*
        // Prints out course descriptions with prereqs
        for (CourseDescription cd : courseDescriptions) {
            System.out.println(cd.getCourseSubject() + " " + cd.getCourseNumber() + " " + cd.getCourseTitle());

            ArrayList<Prereq> prereqList = cd.getPrereqList();

            for (Prereq pr : prereqList)
                System.out.println("\t" + " " +
                        pr.getPrereqSubject() + " " +
                        pr.getPrereqNumber() + " " +
                        pr.getPrereqTitle());
        }
        */
    }

    /**
     * Prints all of the ineligable students for a given course.
     *
     * @param subject       the subject of the CourseDescription being queried
     * @param courseNum     the number of the CourseDescription being queried
     */
    public static void printIneligible(String subject, String courseNum)
    {
        // uniquely identify course description under review
        CourseDescription description = getCourseDescription(subject, courseNum);

        // get all prerequisites
        ArrayList<Prereq> prereqList = description.getPrereqList();

        // System.out.println("Prereq list size: " + prereqList.size());

        // get all current students
        HashSet<Student> currentStudents = description.getCurrentStudentSet();

        // print the number of students enrolled
        System.out.println("Students Enrolled: " + currentStudents.size());

        // check each student's history agains the list of prereqs
        System.out.println("Fail to satisfy prerequisites:");

        int studentsFound = 0;

        for (Student student : currentStudents)
        {
            // find the matching student profile
            StudentProfile profile = getStudentProfile(student);

            // identify all courses taken by the student
            HashSet<Course> courseSet = profile.getCourseSet();

            // capture all the prereqs unsatisfied by the student's history
            boolean satisfies = true;
            ArrayList<Prereq> stillNeeds = new ArrayList<Prereq>();

            // Compare each prerequisite against each student's course history
            for (Prereq prerequisite : prereqList)
            {
                if (!courseSet.contains(prerequisite))
                {
                    satisfies = false;
                    stillNeeds.add(prerequisite);
                }
            }

            // show all needed courses
            if (!satisfies)
            {
                // The student does not contain a prerequisite - notify the user
                System.out.println("* " + profile.getFirstName() + " " + profile.getLastName() +
                        " (" + profile.getBannerID() + ") needs: ");

                for (Prereq prerequisite : stillNeeds)
                {
                    // Tell the user about the missing prerequisite
                    System.out.println("  * " + prerequisite.getPrereqSubject() + " " + prerequisite.getPrereqNumber() +
                            ": " + prerequisite.getPrereqTitle());
                }

                // increment students found
                ++studentsFound;
            }
        }

        // Mark the total number of students found
        System.out.println("[" + studentsFound + " Results]\n");
    }

    /**
     * Prints all of the eligable students for a given course.
     *
     * @param subject       the subject of the CourseDescription being queried
     * @param courseNum     the number of the CourseDescription being queried\
     */
    public static void printEligible(String subject, String courseNum)
    {
        // uniquely identify course description under review
        CourseDescription description = getCourseDescription(subject, courseNum);

        // get all prerequisites
        ArrayList<Prereq> prereqList = description.getPrereqList();

        // System.out.println("Prereq list size: " + prereqList.size());

        // get all current students
        HashSet<Student> currentStudents = description.getCurrentStudentSet();

        // print the number of students enrolled
        System.out.println("Students Enrolled: " + currentStudents.size());

        // check each student's history agains the list of prereqs
        System.out.println("Satisty prerequisites: ");

        int studentsFound = 0;

        for (Student student : currentStudents)
        {
            // find the matching student profile
            StudentProfile profile = getStudentProfile(student);

            // identify all courses taken by the student
            HashSet<Course> courseSet = profile.getCourseSet();

            boolean satisfies = true;

            // Compare each prerequisite against each student's course history
            for (Prereq prerequisite : prereqList)
            {
                // System.out.println("INFO: " + prerequisite.getPrereqSubject() + " " + prerequisite.getPrereqNumber() + " " + prerequisite.getPrereqGrade());
                if (!courseSet.contains(prerequisite))
                    satisfies = false;

                if (satisfies)
                {
                    // Tell the user about students that satisfy prerequisites
                    System.out.println("* " + profile.getFirstName() + " " +
                            profile.getLastName() + " (" + profile.getBannerID() + ")");

                    // Increment studentsFound
                    ++studentsFound;
                }
            }
        }

        // Mark the total number of students found
        System.out.println("[" + studentsFound + " Results]\n");
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

        // System.out.println();

        // parse each token
        for (int index = 0; index < record.length; ++index)
        {
            char curChar = record[index];

            // if we are in a clause, we ignore the delimeter; otherwise, we proceed as usual
            if (!delimOn)
            {
                if (record[index] == escape && (index + 1 < record.length && record[index + 1] == clause))
                {
                    token.add(clause);  // add the escaped clause begin/end character
                    ++index;            // move up two indeces by the end of the iteration
                }
                else if (record[index] == clause && index == record.length - 1)
                {
                    StringBuilder completeToken = new StringBuilder();

                    for (char character : token)
                    {
                        completeToken.append(character);
                    }

                    token.clear();                          // begin a new token
                    result.add(completeToken.toString());   // add the new token
                    startPoint = index + 1;                 // start point for next token

                    delimOn = true;     // end a clause
                }
                else if (record[index] == clause)
                {
                    delimOn = true;     // end a clause
                }
                // handle special characters
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
                else if (record[index] == delim || index == record.length - 1)
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

        // System.out.print("RL: " + result.size() + " RLA: " + result.toArray(new String[result.size()]).length);

        // return the String array of tokens
        return result.toArray(new String[result.size()]);
    }

    private static StudentProfile makeStudentProfile(Integer banner, String first, String last, String mail)
    {
        if (banner == null || first.equals("") || last.equals("") || mail.equals(""))
            return null;

        return new StudentProfile(banner, first, last, mail);
    }

    private static Student makeStudent(Integer banner, String section, Integer year, Term cTerm, String grade, Boolean now)
    {
        if (banner == null || section.equals("") || year.equals("") || cTerm == null || grade.equals("") || now == null)
            return null;

        return new Student(banner, section, year, cTerm, grade, now);
    }

    private static CourseDescription makeCourseDescription(String subject, String courseNum, String title)
    {
        if (subject.equals("") || courseNum.equals("") || title.equals(""))
            return null;

        return new CourseDescription(subject, courseNum, title);
    }

    private static Course makeCourse(String subject, String courseNum, String title, String section, Integer year, Term cTerm, String grade, Boolean now)
    {
        // System.out.println(grade);
        if (subject.equals("") || courseNum.equals("") || title.equals("") || section.equals("") || year == 0 || cTerm == null || grade.equals("") || now == null)
            return null;

        return new Course(subject, courseNum, title, section, year, cTerm, grade, now);
    }
}