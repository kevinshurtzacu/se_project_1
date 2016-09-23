
package implementation;

/**
 * Represents a single course taken by an ACU student.  Maintains data
 * for
 * <ul>
 *     <li>CRN (course registration number)</li>
 *     <li>course subject</li>
 *     <li>course title</li>
 *     <li>course number</li>
 *     <li>course number extension</li>
 *     <li>course year</li>
 *     <li>course term</li>
 *     <li>current grade</li>
 *     <li>whether student course is active</li>
 * </ul>
 * <p>
 *     Future versions of Course may break into a Course and
 *     CourseDescription object, so that data pertinent to state can
 *     be separated from data only pertinent to a course's identity.
 *     CourseDescriptions could then be looked up through the CRN
 *     number of a Course object.
 * </p>
 *
 * @author Kevin Shurtz
 * @author Virginia Pettit
 * @version 1.0
 */
public class Course
{
    /**
     * Represents a term (semester, short-coruse, etc).
     *
     * Term can represent:
     * <ul>
     *     <li>Fall</li>
     *     <li>Spring</li>
     *     <li>January short-course</li>
     *     <li>Maymester course</li>
     * </ul>
     *
     * The Term enum can be extended at a later point to represent
     * other similar terms.
     */
    public enum Term
    {
        FALL,       // Fall term
        SPRING,     // Spring term
        JAN_SHORT,  // January short-course
        MAY,        // "Maymester" short-course
    }

    // Course characteristics
    int crn;                // Course Registration Number

    String courseSubject;   // Course subject (BIBL, CS, IT, etc.)
    String courseTitle;     // Course title (without number)
    int courseNumber;       // Course number (101, 102, etc.)
    String courseNumberExt; // Course number extension (1, 2, H2, etc.)

    // Time characteristics
    int courseYear;         // Year course is taken
    Term courseTerm;        // Term during which course is taken
    
    // Student data
    double courseGrade;     // Student's grade in course (from 0.0 to 100.0)
    boolean inCourseNow;    // Whether the student is in the course now

    /**
     * Constructs a Course object to represent a student's course.  Course
     * delegates the instantiation of instance variables to method setCourse.
     *
     * @param crnNum    course registration number
     * @param title     course title, such as 'Software Engineering', 'Networking', etc
     * @param courseNum course number, such as 220, 221, etc
     * @param ext       course number extension, such as 01, H1, etc
     * @param year      year the course is taking place in
     * @param cTerm     term the coures is taking place in
     * @param grade     current grade for the course out of 100 points
     * @param now       whether the course is being actively taken
     * @see             Course#setCourse(int, int, String, int, String, int, Term, double, boolean)
     */
	public Course(int crnNum, String subject, String title, int courseNum, String ext, int year, Term cTerm, double grade, boolean now)
	{
	    setCourse(crnNum, subject, title, courseNum, ext, year, cTerm,
	            grade, now);
	}

    public Course(int crnNum, String subject, String title, int courseNum, String ext, int year, String cTerm, double grade, boolean now)
    {
        setCourse(crnNum, subject, title, courseNum, ext, year, cTerm,
                grade, now);
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
	public void setCourse(int crnNum, String subject, String title, int courseNum, String ext, int year, Term cTerm, double grade, boolean now)
    {
        setCRN(crnNum);                 // Assign crn
        setCourseSubject(subject);      // Assign subject
        setCourseTitle(courseTitle);    // Assign title
        setCourseNumber(courseNum);     // Assign course number
        setCourseNumberExt(ext);        // Assign course number extension
        setCourseYear(year);            // Assign course year
        setCourseTerm(cTerm);           // Assign course term
        setCourseGrade(grade);          // Assign course grade
        setInCourseNow(now);              // Assign if in course now
    }

    public void setCourse(int crnNum, String subject, String title, int courseNum, String ext, int year, String cTerm, double grade, boolean now)
    {
        setCRN(crnNum);                 // Assign crn
        setCourseSubject(subject);      // Assign subject
        setCourseTitle(courseTitle);    // Assign title
        setCourseNumber(courseNum);     // Assign course number
        setCourseNumberExt(ext);        // Assign course number extension
        setCourseYear(year);            // Assign course year
        setCourseTerm(cTerm);           // Assign course term
        setCourseGrade(grade);          // Assign course grade
        setInCourseNow(now);              // Assign if in course now
    }

    /**
     * Assigns the course registration number.  No validation is conducted.
     *
     * @param cnrNum    course registration number, a unique course identifier
     */
    public void setCRN(int crnNum)
    {
        crn = crnNum;
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
     * Assigns the course number extension.  Examples include '01', '02', and 'H2'.
     * The full identifier for a course can be created by concatenating the
     * course number with its extension, joined by a '.'.  For example,
     * 101.H1
     *
     * @param ext       course number extension
     * @see   Course#setCourseNumber(int)
     */
    public void setCourseNumberExt(String ext)
    {
        courseNumberExt = ext;
    }

    /**
     * Assigns the year in which the course is taken.  Validation insures that the
     * year is a reasonable input.
     *
     * @param year      the year in which the course is being taken
     * @throws IllegalArgumentException If the year is from before 1906,
     *                                  more than four characters, or
     *                                  less than four characters
     */
    public void setCourseYear(int year)
    {
        // If the year is from before ACU was founded
        if (year < 1906)
            throw new IllegalArgumentException("Invalid year (before 1906)");
        
        // If a typo has rendered a year with more than four characters
        if (Integer.toString(year).length() > 4)
            throw new IllegalArgumentException("Invalid year (more than 4 characters)");
        
        // If a typo has rendered a year with less than four characters
        if (Integer.toString(year).length() < 4)
            throw new IllegalArgumentException("Invalid year (less than 4 characters");
        
        courseYear = year;
    }

    /**
     * Assigns the term in which a course is taken.  The term is represented as
     * a Term enum, which can assume one of several predefined values.  Term is
     * possessed by the Course object.
     *
     * @param cTerm     the term in which a course is taken
     * @see   Term
     */
    private void setCourseTerm(Term cTerm)
    {
        courseTerm = cTerm;
    }

    /**
     * Assigns the term in which a course is taken.  The term is represented as
     * a Term enum, which can assume one of several predefined values.  Term is
     * possessed by the Course object.
     *
     * @param cTerm     the term in which a course is taken as a string
     * @see   Term
     */
    public void setCourseTerm(String cTerm)
    {
        if (cTerm.toUpperCase().equals("FALL"))
            courseTerm = Term.FALL;
        else if (cTerm.toUpperCase().equals("SPRING"))
            courseTerm = Term.SPRING;
        else if (cTerm.toUpperCase().equals("JAN_SHORT"))
            courseTerm = Term.JAN_SHORT;
        else if (cTerm.toUpperCase().equals("MAY"))
            courseTerm = Term.MAY;
        else
            courseTerm = null;
    }

    /**
     * Assigns the grade a student has in a course.  The grade cannot be above
     * a 100.0 or below a 0.0.
     *
     * @param grade     the grade a student has in an instance of Course
     * @throws IllegalArgumentException If a student is assigned a value above 100.0 or
     *                                  if a student is assigned a value below 0.0.
     */
    public void setCourseGrade(double grade)
    {
        if (grade > 100.0)
            throw new IllegalArgumentException("Grade above 100% not possible");
        
        if (grade < 0.0)
            throw new IllegalArgumentException("Grade below 0% not possible");
        
        courseGrade = grade;
    }

    /**
     * Assigns boolean regarding whether a student is actively taking the course.
     * There is no validation that can occur in the function.  This value is
     * used to differentiate between past and current courses.
     *
     * @param now       whether a student is actively taking the course
     */
    public void setInCourseNow(boolean now)
    {
        inCourseNow = now;
    }

    public int getCrn() {
        return crn;
    }

    public String getCourseSubject() {
        return courseSubject;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public String getCourseNumberExt() {
        return courseNumberExt;
    }

    public int getCourseYear() {
        return courseYear;
    }

    public String getCourseTerm()
    {
        if (courseTerm == Term.FALL)
            return "Fall";

        if (courseTerm == Term.SPRING)
            return "Spring";

        if (courseTerm == Term.JAN_SHORT)
            return "January Short";

        if (courseTerm == Term.MAY)
            return "May";

        return "";
    }

    public double getCourseGrade() {
        return courseGrade;
    }

    public boolean isInCourseNow() {
        return inCourseNow;
    }
}

