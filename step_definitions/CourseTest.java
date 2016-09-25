package step_definitions;

import implementation.Course;
import implementation.Term;
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

    // result of each test
    private static Object result = null;

    // Givens
    @Given("^the course has the (.*?(?:\\sextension)?) (.*?)$")
    public void theCourseHas(String attribute, String value) throws Throwable
    {
        attribute = attribute.toLowerCase();

        if (attribute.equals("number extension"))
            course.setCourseNumberExt(value);
    }

    @Given("^the course is (?:in the|a) (.*?) (?:term|course)$")
    public void theCourseIsInTheTerm(String term) throws Throwable
    {
        term = term.toUpperCase();

        if (term.equals("FALL"))
            course.setCourseTerm(Term.FALL);

        if (term.equals("SPRING"))
            course.setCourseTerm(Term.SPRING);

        if (term.equals("JANUARY SHORT"))
            course.setCourseTerm(Term.JAN_SHORT);

        if (term.equals("MAY") || term.equals("MAYMESTER"))
            course.setCourseTerm(Term.MAY);
    }

    @Given("^the course is in the year (\\d+)$")
    public void theCourseIsInTheYear(int year) throws Throwable
    {
        course.setCourseYear(year);
    }

    @Given("^the course(?:'s)? grade is (\\d+(?:\\.?\\d+)?)$")
    public void theCourseGradeIs(double grade) throws Throwable
    {
        course.setCourseGrade(grade);
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

    // Whens
    @When("^I ask for the course (\\w+)$")
    public void iAskForTheCourse(String request) throws Throwable
    {
        request = request.toLowerCase();

        if (request.equals("extension"))
            result = course.getCourseNumberExt();

        if (request.equals("year"))
            result = course.getCourseYear();

        if (request.equals("term"))
            result = course.getCourseTerm();

        if (request.equals("grade"))
            result = course.getCourseGrade();
    }

    @When("^I ask if the course is being taken by (?:the|a) student$")
    public void iAskIfCourseBeingTaken() throws Throwable
    {
        result = course.isInCourseNow();
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

        if (expected.equals("JANUARY SHORT"))
            term = Term.JAN_SHORT;

        if (expected.equals("MAY") || expected.equals("MAYMESTER"))
            term = Term.MAY;

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
        boolean inCourse;

        if (active.equals("is"))
            inCourse = true;
        else
            inCourse = false;

        assertEquals(inCourse, course.isInCourseNow());
    }
}

