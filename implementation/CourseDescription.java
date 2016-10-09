package implementation;
import java.util.ArrayList;

/**
 * Represents a single course offered by ACU.  Maintains data for
 * <ul>
 *     <li>course subject</li>
 *     <li>course number</li>
 *     <li>course title</li>
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
    String courseSubject;       // Course subject (BIBL, CS, IT, etc.)
    String courseNumber;        // Course number (101, 102, etc.)
    String courseTitle;         // Course title (without number)
	ArrayList<Prereq> prereqsNeeded; // List of prereqs needed for a certain class

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
        setCourseSubject(subject);      // Assign subject
        setCourseNumber(courseNum);     // Assign course number
        setCourseTitle(courseTitle);    // Assign title
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
     * Checks if two CourseDescription objects are equal.
     *
     * @param other the other CourseDescription object
     * @return      whether or not the CourseDescription objects are identical
     */
    public boolean equals(CourseDescription other)
    {
        if (courseSubject != other.getCourseSubject())
            return false;

        if (courseNumber != other.getCourseNumber())
            return false;

        if (courseTitle != other.getCourseTitle())
            return false;

        return true;
    }

    /**
     * Checks if a Course object is represented by a CourseDescription object.
     *
     * @param other the Course object looking up the appropriate CourseDescription object
     * @return      whether or not the Course object is represetned by the CourseDescription object
     */
    public boolean equals(Course other)
    {
        if (courseSubject != other.getCourseSubject())
            return false;

        if (courseNumber != other.getCourseNumber())
            return false;

        return true;
    }
	
	public void addPrereqs()
	{
		prereqsNeeded = new ArrayList<Prereqs>();
	}
}