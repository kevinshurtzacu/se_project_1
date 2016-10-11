package implementation;

/**
 * Represents a single course taken by an ACU student.  Maintains data for
 * <ul>
 *     <li>course subject</li>
 *     <li>course number</li>
 *     <li>course section</li>
 *     <li>course year</li>
 *     <li>course term</li>
 *     <li>current grade</li>
 *     <li>whether student course is active</li>
 * </ul>
 *
 * <p>
 *     Courses and CourseDescriptions will be able to reference one another
 *     through their subjects and numbers.  CourseDescription is presently
 *     capable of equating itself with a Course object by use of the default
 *     equals method.
 * </p>
 *
 * @author Kevin Shurtz
 * @version 1.0
 */
public class Course
{
    // Course characteristics
    String courseNumber;    // Coures number (101, 102, etc)
    String courseSubject;   // Course subject (ACCT, IT, CS, etc)
    String courseSection;   // Course section (1, 2, H2, etc)

    // Time characteristics
    int courseYear;         // Year course is taken
    Term courseTerm;        // Term during which course is taken
    
    // Student data
    String courseGrade;     // Student's grade in course (from 0.0 to 100.0)
    boolean inCourseNow;    // Whether the student is in the course now

    /**
     * Constructs a Course object to represent a student's course.  Course
     * delegates the instantiation of instance variables to method setCourse.
     *
     * @param section   course section, such as 01, H1, etc
     * @param year      year the course is taking place in
     * @param cTerm     term the coures is taking place in
     * @param grade     current grade for the course out of 100 points
     * @param now       whether the course is being actively taken
     * @see             Course#setCourse(int, String, int, Term, double, boolean)
     */
	public Course(String subject, String number, String section, int year, Term cTerm, double grade, boolean now)
	{
	    setCourse(subject, number, section, year, cTerm, grade, now);
	}

    /**
     * Constructs a Course object to represent a student's course.  Course
     * delegates the instantiation of instance variables to method setCourse.
     *
     * @param section   course section, such as 01, H1, etc
     * @param year      year the course is taking place in
     * @param cTerm     term the coures is taking place in
     * @param grade     current grade for the course as a letter (A, B, etc)
     * @param now       whether the course is being actively taken
     * @see             Course#setCourse(int, String, int, Term, double, boolean)
     */
    public Course(String subject, String number, String section, int year, Term cTerm, String grade, boolean now)
    {
        setCourse(subject, number, section, year, cTerm, grade, now);
    }

    /**
     * Assigns values to Course instance variables.  The function
     * delegate assignment to each of the assignment functions
     * for each instance variable, many of which validate the input.
     *
     * @param section   course section, such as 01, H1, etc
     * @param year      year the course is taking place in
     * @param cTerm     term the coures is taking place in
     * @param grade     current grade for the course out of 100 points
     * @param now       whether the course is being actively taken
     * @throws IllegalArgumentException If one of the arguements provided
     *                                  was unacceptable
     */
	public void setCourse(String subject, String number, String section, int year, Term cTerm, double grade, boolean now)
    {
        setCourseSubject(subject);  // Assign course subject
        setCourseNumber(number);    // Assign course number
        setCourseSection(section);  // Assign course section
        setCourseYear(year);        // Assign course year
        setCourseTerm(cTerm);       // Assign course term
        setCourseGrade(grade);      // Assign course grade
        setInCourseNow(now);        // Assign if in course now
    }

    /**
     * Assigns values to Course instance variables.  The function
     * delegate assignment to each of the assignment functions
     * for each instance variable, many of which validate the input.
     *
     * @param section   course section, such as 01, H1, etc
     * @param year      year the course is taking place in
     * @param cTerm     term the coures is taking place in
     * @param grade     current grade for the course as a letter (A, B, etc)
     * @param now       whether the course is being actively taken
     * @throws IllegalArgumentException If one of the arguements provided
     *                                  was unacceptable
     */
    public void setCourse(String subject, String number, String section, int year, Term cTerm, String grade, boolean now)
    {
        setCourseSubject(subject);  // Assign course subject
        setCourseNumber(number);    // Assign course number
        setCourseSection(section);  // Assign course section
        setCourseYear(year);        // Assign course year
        setCourseTerm(cTerm);       // Assign course term
        setCourseGrade(grade);      // Assign course grade
        setInCourseNow(now);        // Assign if in course now
    }

    /**
     * Assigns the course subject.  Examples include 'ACCT', 'IT', etc.
     * This value is part of a course's unique identifier, in conjunction
     * with the course number.
     *
     * @param subject   the course subject, such as 'ACCT', 'IT', etc
     */
    public void setCourseSubject(String subject)
    {
        courseSubject = subject;
    }

    /**
     * Assigns the course number.  Examples include '101', 102', etc.
     * This value is part of the unique identifier, in conjunction with
     * the course subject.
     *
     * @param number    the course number, such as '101', '102', etc
     */
    public void setCourseNumber(String number)
    {
        courseNumber = number;
    }

    /**
     * Assigns the course section.  Examples include '01', '02', and 'H2'.
     * The full identifier for a course can be created by concatenating the
     * course number with its extension, joined by a '.'.  For example,
     * 101.H1
     *
     * @param ext       course section
     */
    public void setCourseSection(String section)
    {
        courseSection = section;
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
     * a 100.0 or below a 0.0.  This grade is converted into a letter grade.
     *
     * @param grade     the grade a student has in an instance of Course
     * @throws IllegalArgumentException If a student is assigned a value above 100.0 or
     *                                  if a student is assigned a value below 0.0.
     */
    public void setCourseGrade(double grade)
    {
        // Check for exceptions
        if (grade > 100.0)
            throw new IllegalArgumentException("Grade above 100% not possible");

        if (grade < 0.0)
            throw new IllegalArgumentException("Grade below 0% not possible");

        // Assign grades
        if (grade >= 90)
            courseGrade = "A";
        else if (grade >= 80)
            courseGrade = "B";
        else if (grade >= 70)
            courseGrade = "C";
        else if (grade >= 60)
            courseGrade = "D";
        else
            courseGrade = "F";
    }

    /**
     * Assigns the course grade.  The grade must be a letter grade,
     * such as (A, B, C, D, F, P, S, U, CR, NC, NG, AU, W, WF, I, or IP).
     *
     * 'W' signifies 'withdraw', and means a student did not finish a course.  This does not
     * affect a student's GPA.  'WF' is a 'withdraw' that does affect a student's GPA.  'F' is
     * just a typical failure.
     *
     * 'IP' is primarily for graduate students and means 'in progress'.  'I' means 'incomplete', and is also
     * more common for graduate students.
     *
     * 'P' is for 'pass', and is exclusive to pass/fail courses.  Similarly, 'NP' stands for 'no pass'
     *
     * 'CR' stands for 'credit', and is for students who sign up for a course on a credit/no credit basis.
     * Similarly, 'NC' stands for 'no credit'.
     *
     * 'AU' represents a 'course audit', and typically results in no course credit.
     *
     * 'NG' stands for 'no grade', and typically represents a course in progress.
     *
     * 'S' stands for 'satisfactory', and 'U' for 'unsatisfactory'.
     *
     * @param grade     the grade a student has in an instance of Course
     * @throws IllegalArgumentException If a student is assigned something besides (A, B, C, D, F, P, S, U, CR, NC, NG, AU, W, WF, I, or IP).
     */
    public void setCourseGrade(String grade)
    {
        grade = grade.toUpperCase();

        // Check for exceptions
        if (!(grade.equals("A")    ||
                grade.equals("B")  ||
                grade.equals("C")  ||
                grade.equals("D")  ||
                grade.equals("F")  ||
                grade.equals("P")  ||
                grade.equals("S")  ||
                grade.equals("U")  ||
                grade.equals("NP") ||
                grade.equals("CR") ||
                grade.equals("NC") ||
                grade.equals("NG") ||
                grade.equals("AU") ||
                grade.equals("W")  ||
                grade.equals("WF") ||
                grade.equals("I")  ||
                grade.equals("IP")))
            throw new IllegalArgumentException("Grade must be 'A', 'B', 'C', 'D', 'F', " +
                    "'P', 'S', 'U', 'NP', 'CR', 'NC', 'NG', 'AU', 'W', 'WF', 'I', or 'IP'; Invalid Argument: " + grade);

        // Assign grade
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
     * Returns the course section.  Courses often have extensions
     * for their course numbers to differentiate different instances of the
     * course, represented by a mixture of digits and characters.  Examples
     * include '01', '02', 'H2', etc.
     *
     * Often, the full course identifier is presented as the subject, course
     * number and course section, ajoined by a period.  For example,
     * BIBL 101.H2.  At ACU, this represnts Bible 101 Jesus: Life and Teachings.
     * The H2 signifies that this is the "Honors 2" class.
     *
     * @return  the course section
     */
    public String getCourseSection() {
        return courseSection;
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
     * Returns the course grade.  This represents the final letter grade for
     * the course, or the ongoing grade as an active semester progresses.
     *
     * @return  the course grade
     */
    public String getCourseGrade()
    {
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
     * Returns the course subject by sending a request to the DataModule.
     * This infomration is stored in a single CourseDescription object.
     *
     * @return  the course subject
     */
    public String getCourseSubject()
    {
        return courseSubject;
    }

    /**
     * Returns the course title by sending a request to the DataModule.
     * This infomration is stored in a single CourseDescription object.
     *
     * @return  the course title
     */
    public String getCourseTitle()
    {
        return DataModule.getCourseTitle(this);
    }

    /**
     * Returns the course number by sending a request to the DataModule.
     * This infomration is stored in a single CourseDescription object.
     *
     * @return  the course number.
     */
    public String getCourseNumber()
    {
        return courseNumber;
    }

    /**
     * Returns true if two Courses have the same subject, number,
     * and are taken at the same time.
     *
     * @param other     the other Course
     * @return          whether the Courses are the same
     */
    public boolean equals(Course other)
    {
        if (getCourseSubject() != other.getCourseSubject())
            return false;

        if (getCourseNumber() != other.getCourseNumber())
            return false;

        if (getCourseYear() != other.getCourseYear())
            return false;

        if (getCourseTerm() != other.getCourseTerm())
            return false;

        if (getCourseGrade() != other.getCourseGrade())
            return false;

        return true;
    }

    /**
     * Returns true if a Course can be related to a CourseDescription
     * via its subject and number.
     *
     * @param other     the CourseDescription being compared
     * @return          whether the Course relates to the CourseDescription
     */
    public boolean equals(CourseDescription other)
    {
        if (getCourseSubject() != other.getCourseSubject())
            return false;

        if (getCourseNumber() != other.getCourseNumber())
            return false;

        return true;
    }
}

