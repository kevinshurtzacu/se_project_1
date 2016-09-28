package implementation;

/**
 * Represents a single course taken by an ACU student.  Maintains data for
 * <ul>
 *     <li>CRN (course registration number)</li>
 *     <li>course number extension</li>
 *     <li>course year</li>
 *     <li>course term</li>
 *     <li>current grade</li>
 *     <li>whether student course is active</li>
 * </ul>
 *
 * <p>
 *     Courses and CourseDescriptions will be able to reference one another
 *     through their CRNs.  CourseDescription is presently capable of equating
 *     itself with a Course object by use of the default equals method.
 * </p>
 *
 * @author Kevin Shurtz
 * @author Virginia Pettit
 * @version 1.0
 */
public class Course
{
    // Course characteristics
    int courseCRN;          // Course Registration Number
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
     * @param ext       course number extension, such as 01, H1, etc
     * @param year      year the course is taking place in
     * @param cTerm     term the coures is taking place in
     * @param grade     current grade for the course out of 100 points
     * @param now       whether the course is being actively taken
     * @see             Course#setCourse(int, String, int, Term, double, boolean)
     */
	public Course(int crnNum, String ext, int year, Term cTerm, double grade, boolean now)
	{
	    setCourse(crnNum, ext, year, cTerm, grade, now);
	}

    /**
     * Assigns values to Course instance variables.  The function
     * delegate assignment to each of the assignment functions
     * for each instance variable, many of which validate the input.
     *
     * @param crnNum    course registration number
     * @param ext       course number extension, such as 01, H1, etc
     * @param year      year the course is taking place in
     * @param cTerm     term the coures is taking place in
     * @param grade     current grade for the course out of 100 points
     * @param now       whether the course is being actively taken
     * @throws IllegalArgumentException If one of the arguements provided
     *                                  was unacceptable
     */
	public void setCourse(int crnNum, String ext, int year, Term cTerm, double grade, boolean now)
    {
        setCourseCRN(crnNum);           // Assign crn
        setCourseNumberExt(ext);        // Assign course number extension
        setCourseYear(year);            // Assign course year
        setCourseTerm(cTerm);           // Assign course term
        setCourseGrade(grade);          // Assign course grade
        setInCourseNow(now);            // Assign if in course now
    }

    /**
     * Assigns the course registration number.  No validation is conducted.
     *
     * @param crnNum    course registration number, a unique course identifier
     */
    public void setCourseCRN(int crnNum)
    {
        courseCRN = crnNum;
    }

    /**
     * Assigns the course number extension.  Examples include '01', '02', and 'H2'.
     * The full identifier for a course can be created by concatenating the
     * course number with its extension, joined by a '.'.  For example,
     * 101.H1
     *
     * @param ext       course number extension
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
     * a Term enum, which can assume one of several predefined values.  Term exists
     * independently of the Course object.
     *
     * @param cTerm     the term in which a course is taken
     * @see   Term
     */
    public void setCourseTerm(Term cTerm)
    {
        courseTerm = cTerm;
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

    /**
     * Returns the course registration number.  This can be used to look up
     * a CourseDescription for this course.
     *
     * @return  the course registration number
     */
    public int getCourseCRN()
    {
        return courseCRN;
    }

    /**
     * Returns the course number extension.  Courses often have extensions
     * for their course numbers to differentiate different instances of the
     * course, represented by a mixture of digits and characters.  Examples
     * include '01', '02', 'H2', etc.
     *
     * Often, the full course identifier is presented as the subject, course
     * number and course number extension, ajoined by a period.  For example,
     * BIBL 101.H2.  At ACU, this represnts Bible 101 Jesus: Life and Teachings.
     * The H2 signifies that this is the "Honors 2" class.
     *
     * @return  the course number extension
     */
    public String getCourseNumberExt() {
        return courseNumberExt;
    }

    /**
     * Returns the course year.  Examples include, 2012, 2014, 2016, etc.
     *
     * @return the course year
     */
    public int getCourseYear() {
        return courseYear;
    }

    /**
     * Returns the course Term, represented by an enum.
     *
     * Course terms are limited in number.  Thus, they have been represented as
     * enumerated types to insure strong typing.  Exmamples include FALL,
     * SPRING, JAN_SHORT (January Short), and MAY (Maymester).
     *
     * @return  the course term
     */
    public Term getCourseTerm()
    {
        return courseTerm;
    }

    /**
     * Returns the course grade.  This represents the final grade for the course,
     * or the ongoing grade as an active semester progresses.
     *
     * @return  the course grade
     */
    public double getCourseGrade() {
        return courseGrade;
    }

    /**
     * Returns whether the course is actively being taken by a student.
     *
     * @return  whether the course is actively being taken by a student
     */
    public boolean isInCourseNow() {
        return inCourseNow;
    }

    /**
     * Returns true if two Courses have the same CRN and are taken
     * at the same time.
     *
     * @param other     the other Course
     * @return          whether the Courses are the same
     */
    public boolean equals(Course other)
    {
        if (getCourseCRN() != other.getCourseCRN())
            return false;

        if (getCourseYear() != other.getCourseYear())
            return false;

        if (getCourseTerm() != other.getCourseTerm())
            return false;

        return true;
    }

    /**
     * Returns true if a Course can be related to a CourseDescription
     * via its CRN.
     *
     * @param other     the CourseDescription being compared
     * @return          whether the Course relates to the CourseDescription
     */
    public boolean equals(CourseDescription other)
    {
        if (getCourseCRN() != other.getCourseCRN())
            return false;

        return true;
    }
}

