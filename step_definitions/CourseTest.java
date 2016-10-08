package step_definitions;

import implementation.Course;
import implementation.Term;
import implementation.CourseDescription;
import implementation.DataModule;
import cucumber.api.java.en.*;
import cucumber.api.PendingException;
import static org.junit.Assert.*;

/**
 * These defintions will be used by the cucumber_progress.sh scripts at the root of the project
 * directory to test Course features, referencing the course.feature in the
 * features directory.
 *
 * @author  Kevin Shurtz
 * @version 1.0
 */
public class CourseTest
{
    // Course object to be tested
    private static Course course = new Course(12345, "", 2015, Term.FALL, 90.5, false);

    // Course objects for testing comparisons
    private static Course firstCourse = new Course(12345, "", 2015, Term.FALL, 90.5, false);
    private static Course secondCourse = new Course(12345, "", 2015, Term.FALL, 90.5, false);

    // CourseDescription object to compare to
    private static CourseDescription coursedesc = new CourseDescription(0, "", 100, "");

    // result of each test
    private static Object result = null;

    // Givens
    @Given("^the course has the crn (\\d+)$")
    public void theCourseHasTheCrn(int crn) throws Throwable
    {
        course.setCourseCRN(crn);
    }

    @Given("^the course has the section (.*?)$")
    public void theCourseHasSection(String value) throws Throwable
    {
        course.setCourseSection(value);
    }

    @Given("^the (first|second) course has the section (.*?)$")
    public void oneOfTheCoursesHasSection(String which, String value) throws Throwable
    {
        if (which.equals("first"))
            firstCourse.setCourseSection(value);

        if (which.equals("second"))
            secondCourse.setCourseSection(value);
    }

    @Given("^the course is (?:in the|a) (.*?) (?:term|course)$")
    public void theCourseIsInTheTerm(String term) throws Throwable
    {
        term = term.toUpperCase();

        if (term.equals("FALL"))
            course.setCourseTerm(Term.FALL);

        if (term.equals("SPRING"))
            course.setCourseTerm(Term.SPRING);

        if (term.equals("SUMMER"))
            course.setCourseTerm(Term.SUMMER);
    }

    @Given("^the (first|second) course is (?:in the|a) (.*?) (?:term|course)$")
    public void oneCourseIsInTheTerm(String which, String term) throws Throwable
    {
        term = term.toUpperCase();

        if (which.equals("first"))
        {
            if (term.equals("FALL"))
                firstCourse.setCourseTerm(Term.FALL);

            if (term.equals("SPRING"))
                firstCourse.setCourseTerm(Term.SPRING);

            if (term.equals("SUMMER"))
                firstCourse.setCourseTerm(Term.SUMMER);
        }

        if (which.equals("second"))
        {
            if (term.equals("FALL"))
                secondCourse.setCourseTerm(Term.FALL);

            if (term.equals("SPRING"))
                secondCourse.setCourseTerm(Term.SPRING);

            if (term.equals("SUMMER"))
                secondCourse.setCourseTerm(Term.SUMMER);
        }
    }

    @Given("^the course is in the year (\\d+)$")
    public void theCourseIsInTheYear(int year) throws Throwable
    {
        course.setCourseYear(year);
    }

    @Given("^the (first|second) course is in the year (\\d+)$")
    public void oneCourseIsInTheYear(String which, int year) throws Throwable
    {
        if (which.equals("first"))
            firstCourse.setCourseYear(year);

        if (which.equals("second"))
            secondCourse.setCourseYear(year);
    }

    @Given("^the course(?:'s)? grade is (.*?)$")
    public void theCourseGradeIs(String grade) throws Throwable
    {
        boolean isNum = true;

        for (char character : grade.toCharArray())
        {
            if (!Character.isDigit(character))
                isNum = false;
        }

        if (isNum)
            course.setCourseGrade(Double.parseDouble(grade));
        else
            course.setCourseGrade(grade);
    }

    @Given("^the (first|second) course(?:'s)? grade is (\\d+(?:\\.?\\d+)?)$")
    public void oneCourseGradeIs(String which, double grade) throws Throwable
    {
        if (which.equals("first"))
            firstCourse.setCourseGrade(grade);

        if (which.equals("second"))
            secondCourse.setCourseGrade(grade);
    }

    @Given("^the course (is not|is) being taken by (?:the|a) student$")
    public void theCourseBeingTaken(String active) throws Throwable
    {
        boolean inCourse;

        if (active.equals("is"))
            inCourse = true;
        else
            inCourse = false;

        course.setInCourseNow(inCourse);
    }

    @Given("^the (first|second) course (is not|is) being taken by (?:the|a) student$")
    public void oneCourseBeingTaken(String which, String active) throws Throwable
    {
        boolean inCourse;

        if (active.equals("is"))
            inCourse = true;
        else
            inCourse = false;

        if (which.equals("first"))
            firstCourse.setInCourseNow(inCourse);

        if (which.equals("second"))
            secondCourse.setInCourseNow(inCourse);
    }

    @Given("^there is a course description with a \"(.*?)\" of (.*?)$")
    public void thereIsACourseDescriptionWithAOf(String attribute, String value) throws Throwable
    {
        attribute = attribute.toLowerCase();

        if (attribute.equals("crn"))
            coursedesc.setCourseCRN(Integer.parseInt(value));

        if (attribute.equals("subject"))
            coursedesc.setCourseSubject(value);

        if (attribute.equals("number"))
            coursedesc.setCourseNumber(Integer.parseInt(value));

        if (attribute.equals("title"))
            coursedesc.setCourseTitle(value);
    }

    // Whens
    @When("^I ask for the course (\\w+)$")
    public void iAskForTheCourse(String request) throws Throwable
    {
        request = request.toLowerCase();

        if (request.equals("section"))
            result = course.getCourseSection();

        if (request.equals("year"))
            result = course.getCourseYear();

        if (request.equals("term"))
            result = course.getCourseTerm();

        if (request.equals("grade"))
            result = course.getCourseGrade();

        if (request.equals("title"))
            result = DataModule.getCourseTitle(course);
    }

    @When("^I ask if the course is being taken by (?:the|a) student$")
    public void iAskIfCourseBeingTaken() throws Throwable
    {
        result = course.isInCourseNow();
    }

    @When("^I ask if the courses are equal$")
    public void iAskIfTheCoursesAreEqual() throws Throwable {
        result = firstCourse.equals(secondCourse);
    }

    // Thens
    @Then("^I receive the string (.*?) from(?:\\sthe)? course$")
    public void iRecieveTheString(String expected) throws Throwable
    {
        assertEquals(expected, (String)result);
    }

    @Then("^I receive the integer (\\d+) from(?:\\sthe)? course?$")
    public void iReceiveTheInteger(Integer expected) throws Throwable
    {
        assertEquals(expected, (Integer)result);
    }

    @Then("^I receive the term (.*?) from(?:\\sthe)? course?$")
    public void iReceiveTheTerm(String expected) throws Throwable
    {
        Term term = Term.UNDEFINED;
        expected = expected.toUpperCase();

        if (expected.equals("FALL"))
            term = Term.FALL;

        if (expected.equals("SPRING"))
            term = Term.SPRING;

        if (expected.equals("SUMMER"))
            term = Term.SUMMER;

        assertEquals(term, (Term)result);
    }

    @Then("^I receive the double (\\d+(?:\\.?\\d+)) from(?:\\sthe)? course$")
    public void iReceiveTheDouble(Double expected) throws Throwable
    {
        assertEquals(expected, (Double)result);
    }

    @Then("^I am told that it (is|is not) being taken by (?:the|a) student$")
    public void iAmToldThatBeingTaken(String active) throws Throwable
    {
        boolean expected;

        if (active.equals("is"))
            expected = true;
        else
            expected = false;

        assertEquals(expected, (Boolean)result);
    }

    @Then("^I am told that the first course and second course (are|are not) equal$")
    public void iAmToldThatEqual(String equal) throws Throwable
    {
        Boolean expected;

        if (equal.equals("are"))
            expected = true;
        else
            expected = false;

        assertEquals(expected, (Boolean)result);

    }
}

