package implementation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

/**
 * Represents a single course offered by ACU.  Maintains data for
 * <ul>
 *     <li>course subject</li>
 *     <li>course number</li>
 *     <li>course title</li>
 *     <li>prerequisite list</li>
 *     <li>student set</li>
 * </ul>
 *
 * <p>
 *     Instances of CourseDescription will also contain a subject and number,
 *     by which Courses may look up data from their coresponding CourseDescription.
 * </p>
 *
 * @author Kevin Shurtz
 * @version 1.0
 */
public class CourseDescription
{
    // Course characteristics
    private String courseSubject;               // Course subject (BIBL, CS, IT, etc.)
    private String courseNumber;                // Course number (101, 102, etc.)
    private String courseTitle;                 // Course title (without number)
	private ArrayList<Prereq> prereqsNeeded;    // List of prereqs needed for a certain class
    private HashSet<Student> students;          // Set of students in course history

    /**
     * Constructs a Course object to represent a student's course.  Course
     * delegates the instantiation of instance variables to method setCourse.
     *
     * @param subject   course subject, such as 'BIBL', 'CS', 'IT', etc
     * @param title     course title, such as 'Software Engineering', 'Networking', etc
     * @param courseNum course number, such as 220, 221, etc
     * @throws IllegalArgumentException If one of the arguements provided
     *                                  was unacceptable
     * @see             CourseDescription#setCourseDescription(int, String, int, String)
     */
    public CourseDescription(String subject, String courseNum, String title)
    {
        setCourseDescription(subject, courseNum, title);
    }

    /**
     * Assigns values to CourseDescription instance variables. The function
     * delegates assignment to each of the assignment functions
     * for each instance variable, many of which validate the input.
     *
     * @param crnNum    course registration number
     * @param subject   course subject, such as 'BIBL', 'CS', 'IT', etc
     * @param title     course title, such as 'Software Engineering', 'Networking', etc
     * @param courseNum course number, such as 220, 221, etc
     * @throws IllegalArgumentException If one of the arguements provided
     *                                  was unacceptable
     */
    public void setCourseDescription(String subject, String courseNum, String title)
    {
        setCourseSubject(subject);                  // Assign subject
        setCourseNumber(courseNum);                 // Assign course number
        setCourseTitle(title);                      // Assign title
        prereqsNeeded = new ArrayList<Prereq>();    // instantiate prerequisite list
        students = new HashSet<Student>();          // instnatiate student list
    }

    /**
     * Adds a Prereq to CourseDescriptions's List of Prereqs.
     *
     * @param prerequisite  a course prerequisite
     */
    public void addPrereq(Prereq prerequisite)
    {
        prereqsNeeded.add(prerequisite);
    }

    /**
     * Adds a student to CourseDescription's Set of Students.
     *
     * @param student   a student that has taken the CourseDescription's course
     */
    public void addStudent(Student student)
    {
        students.add(student);
    }

    /**
     * Assigns the course subject.  No validation is conducted on the string,
     * but typically a course subject is fully capitalized, and no longer
     * than four characters.
     *
     * @param subject       course subject, such as BIBL, CS, IT, etc
     */
    public void setCourseSubject(String subject)
    {
        courseSubject = subject;
    }

    /**
     * Assigns the course number.  Examples include 101, 102, 374, etc.  Extensions to
     * course numbers, like 'H1' in '101.H1', are not stored in this field.  Rather,
     * extensions are stored as a String in setCourseNumberExt.  If a course number
     * does not consist of three digits, an exception is thrown.
     *
     * @param courseNum     course number
     * @throws IllegalArgumentException If the course number is not three digits
     * @see                 Course#setCourseNumberExt(String)
     */
    public void setCourseNumber(String courseNum)
    {
        if (courseNum.length() != 3)
            throw new IllegalArgumentException("Course number is not three digits");

        courseNumber = courseNum;
    }

    /**
     * Assigns the course title.  No validation is conducted.  The title is not
     * to include the course's number, or other identifying information.
     *
     * @param title     the title of the course, such as 'Software Engineering', or 'Networking'
     */
    public void setCourseTitle(String title)
    {
        courseTitle = title;
    }
	
    /**
     * Returns the course subject.  The course subject is output in an abbreviated format such
     * as 'BIBL', 'CS', 'IT', etc.
     *
     * @return  the course subject
     */
    public String getCourseSubject()
    {
        return courseSubject;
    }

    /**
     * Returns the course number.
     *
     * @return  the course number
     */
    public String getCourseNumber()
    {
        return courseNumber;
    }

    /**
     * Returns the course title.
     *
     * @return  the course title
     */
    public String getCourseTitle()
    {
        return courseTitle;
    }

    /**
     * Returns a deeply copied ArrayList of Prereqs.
     *
     * @return      a copy of the Prereq list for the CourseDescription
     */
    public ArrayList<Prereq> getPrereqList()
    {
        // deep copy and return
        ArrayList<Prereq> prereqList = new ArrayList<Prereq>();

        for (Prereq prerequisite : prereqsNeeded)
        {
            // Note: While Strings are objects, they are immutable; this works for a deep copy
            String subject = prerequisite.getPrereqSubject();
            String number = prerequisite.getPrereqNumber();
            String title = prerequisite.getPrereqTitle();
            String grade = prerequisite.getPrereqGrade();

            prereqList.add(new Prereq(subject, number, title, grade));
        }

        return prereqList;
    }

    /**
     * Returns a deeply copied HashSet of Students.
     *
     * @return      a copy of the Student set for the CourseDescription
     */
    public HashSet<Student> getStudentSet()
    {
        // deep copy and return
        HashSet<Student> studentSet = new HashSet<Student>();

        for (Student student : students)
        {
            // Note: While Strings are objects, they are immutable; this works for a deep copy
            int bannerID = student.getStudentBannerID();
            String section = student.getStudentSection();
            int year = student.getStudentYear();
            Term term = student.getStudentTerm();
            String grade = student.getStudentGrade();
            boolean now = student.isTakingNow();

            studentSet.add(new Student(bannerID, section, year, term, grade, now));
        }

        return studentSet;
    }

    /**
     * Returns a deeply copied HashSet of Students currently in the course.
     *
     * @return      a copy of the Student set for the CourseDescription for all current students
     */
    public HashSet<Student> getCurrentStudentSet()
    {
        // deep copy and return
        HashSet<Student> studentSet = new HashSet<Student>();

        for (Student student : students)
        {
            // Note: While Strings are objects, they are immutable; this works for a deep copy
            int bannerID = student.getStudentBannerID();
            String section = student.getStudentSection();
            int year = student.getStudentYear();
            Term term = student.getStudentTerm();
            String grade = student.getStudentGrade();
            boolean now = student.isTakingNow();

            // If the student is current
            if (year == DataModule.currentYear && Term == DataModule.currentTerm)
                studentSet.add(new Student(bannerID, section, year, term, grade, now));
        }

        return studentSet;
    }

    /**
     * Determines if the CourseDescription is equal to another CourseDescripion or Course.
     * This function delegates the equals operation to one of the two private equals
     * functions in CourseDescription.
     *
     * @param other     either a CourseDescription or Course object
     * @return          whether the CourseDescription is equal to the other object
     */
    @Override
    public boolean equals(Object other)
    {
        if (other == null)
            return false;

        if (other == this)
            return true;

        if (other instanceof CourseDescription)
            return equals((CourseDescription)other);

        if (other instanceof Course)
            return equals((Course)other);

        return false;
    }

    /**
     * Checks if two CourseDescription objects are equal.
     *
     * @param other the other CourseDescription object
     * @return      whether or not the CourseDescription objects are identical
     */
    private boolean equals(CourseDescription other)
    {
        if (other == null)
            return false;

        if (!getCourseSubject().equals(other.getCourseSubject()))
            return false;

        if (!getCourseNumber().equals(other.getCourseNumber()))
            return false;

        if (!getCourseTitle().equals(other.getCourseTitle()))
            return false;

        return true;
    }

    /**
     * Checks if a Course object is represented by a CourseDescription object.
     *
     * @param other the Course object looking up the appropriate CourseDescription object
     * @return      whether or not the Course object is represetned by the CourseDescription object
     */
    private boolean equals(Course other)
    {
        if (other == null)
            return false;

        if (!courseSubject.equals(other.getCourseSubject()))
            return false;

        if (!courseNumber.equals(other.getCourseNumber()))
            return false;

        return true;
    }

    /**
     * Returns a hash for CourseDescription.  The hash utilizes the Objects library
     * hash function, and uses the course subject and number to generate the hash.
     *
     * Course should hash to the same value.
     *
     * @return      a hash for CourseDescription
     */
    @Override
    public int hashCode()
    {
        return Objects.hash(getCourseSubject(), getCourseNumber(), getCourseTitle());
    }
}