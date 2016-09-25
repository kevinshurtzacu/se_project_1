package implementation;

/**
 * Represents a single course offered by ACU.  Maintains data
 * for
 * <ul>
 *     <li>CRN (course registration number)</li>
 *     <li>course subject</li>
 *     <li>course title</li>
 *     <li>course number</li>
 * </ul>
 *
 * <p>
 *     Instances of Course will also contain CRNs, by which they may
 *     look up data from the coresponding CourseDescription.
 * </p>
 *
 * @author Kevin Shurtz
 * @version 1.0
 */
public class CourseDescription
{
    // Course characteristics
    int courseCRN;          // Course Registration Number
    String courseSubject;   // Course subject (BIBL, CS, IT, etc.)
    int courseNumber;       // Course number (101, 102, etc.)
    String courseTitle;     // Course title (without number)

    /**
     * Constructs a Course object to represent a student's course.  Course
     * delegates the instantiation of instance variables to method setCourse.
     *
     * @param crnNum    course registration number
     * @param subject   course subject, such as 'BIBL', 'CS', 'IT', etc
     * @param title     course title, such as 'Software Engineering', 'Networking', etc
     * @param courseNum course number, such as 220, 221, etc
     * @see             Course#setCourse(int, int, String, int, String, int, Term, double, boolean)
     */
    public CourseDescription(int crnNum, String subject, int courseNum, String title)
    {
        setCourse(crnNum, subject, courseNum, title);
    }

    /**
     * Assigns values to Course instance variables.  The function
     * delegate assignment to each of the assignment functions
     * for each instance variable, many of which validate the input.
     *
     * @param crnNum    course registration number
     * @param title     course title, such as 'Software Engineering', 'Networking', etc
     * @param courseNum course number, such as 220, 221, etc
     * @param ext       course number extension, such as 01, H1, etc
     * @param year      year the course is taking place in
     * @param cTerm     term the coures is taking place in
     * @param grade     current grade for the course out of 100 points
     * @param now       whether the course is being actively taken
     * @throws IllegalArgumentException If one of the arguements provided
     *                                  was unacceptable
     */
    public void setCourse(int crnNum, String subject, int courseNum, String title)
    {
        setCourseCRN(crnNum);           // Assign crn
        setCourseSubject(subject);      // Assign subject
        setCourseNumber(courseNum);     // Assign course number
        setCourseTitle(courseTitle);    // Assign title
    }

    /**
     * Assigns the course registration number.  No validation is conducted.
     *
     * @param cnrNum    course registration number, a unique course identifier
     */
    public void setCourseCRN(int crnNum)
    {
        courseCRN = crnNum;
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
    public void setCourseNumber(int courseNum)
    {
        if (Integer.toString(courseNum).length() != 3)
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
     * Returns the CRN.  The CRN is the course registration number.
     *
     * @return  the course CRN
     */
    public int getCourseCRN()
    {
        return courseCRN;
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
    public int getCourseNumber()
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
        if (courseCRN != other.getCourseCRN())
            return false;

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
        if (courseCRN == other.getCourseCRN())
            return true;

        return false;
    }
}