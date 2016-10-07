package step_definitions;

import implementation.Student;
import implementation.Term;
import cucumber.api.java.en.*;
import cucumber.api.PendingException;
import static org.junit.Assert.*;

/**
 * These defintions will be used by the cucumber_progress.sh scripts at the root of the project
 * directory to test Student features, referencing the student.feature in the
 * features directory.
 *
 * @author  Kevin Shurtz
 * @version 1.0
 */
public class StudentTest
{
    // Course object to be tested
    private static Student student = new Student(12345, "", 2015, Term.FALL, 90.5, false);

    // result of each test
    private static Object result = null;

    // Givens
    @Given("^the student's course has the (.*?(?:\\sextension)?) (.*?)$")
    public void theStudentHas(String attribute, String value) throws Throwable
    {
        attribute = attribute.toLowerCase();

        if (attribute.equals("number extension"))
            student.setCourseNumberExt(value);
    }

    @Given("^the student is (?:in the|a) (.*?) (?:term|course)$")
    public void theStudentIsInTheTerm(String term) throws Throwable
    {
        term = term.toUpperCase();

        if (term.equals("FALL"))
            student.setCourseTerm(Term.FALL);

        if (term.equals("SPRING"))
            student.setCourseTerm(Term.SPRING);

        if (term.equals("JANUARY SHORT"))
            student.setCourseTerm(Term.JAN_SHORT);

        if (term.equals("MAY") || term.equals("MAYMESTER"))
            student.setCourseTerm(Term.MAY);
    }

    @Given("^the student is in the year (\\d+)$")
    public void theStudentIsInTheYear(int year) throws Throwable
    {
        student.setCourseYear(year);
    }

    @Given("^the student(?:'s)? grade is (\\d+(?:\\.?\\d+)?)$")
    public void theStudentGradeIs(double grade) throws Throwable
    {
        student.setCourseGrade(grade);
    }

    @Given("^the student (is not|is) taking (?:the|a) course$")
    public void theStudentTakes(String active) throws Throwable
    {
        boolean inCourse;

        if (active.equals("is"))
            inCourse = true;
        else
            inCourse = false;

        student.setInCourseNow(inCourse);
    }

    // Whens
    @When("^I ask for the (?:student|student's course) (\\w+)$")
    public void iAskForTheStudent(String request) throws Throwable
    {
        request = request.toLowerCase();

        if (request.equals("extension"))
            result = student.getCourseNumberExt();

        if (request.equals("year"))
            result = student.getCourseYear();

        if (request.equals("term"))
            result = student.getCourseTerm();

        if (request.equals("grade"))
            result = student.getCourseGrade();
    }

    @When("^I ask if the student is taking (?:the|a) course$")
    public void iAskIfStudentTakes() throws Throwable
    {
        result = student.isInCourseNow();
    }

    // Thens
    @Then("^I receive the string (.*?) from(?:\\sthe)? student$")
    public void iRecieveTheString(String expected) throws Throwable
    {
        assertEquals(expected, (String)result);
    }

    @Then("^I receive the integer (\\d+) from(?:\\sthe)? student$")
    public void iReceiveTheInteger(Integer expected) throws Throwable
    {
        assertEquals(expected, (Integer)result);
    }

    @Then("^I receive the term (.*?) from(?:\\sthe)? student$")
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

    @Then("^I receive the double (\\d+(?:\\.?\\d+)) from(?:\\sthe)? student$")
    public void iReceiveTheDouble(Double expected) throws Throwable
    {
        assertEquals(expected, (Double)result);
    }

    @Then("^I am told that (?:the|a) student (is|is not) taking (?:the|a) course$")
    public void iAmToldThatBeingTaken(String active) throws Throwable
    {
        boolean inCourse;

        if (active.equals("is"))
            inCourse = true;
        else
            inCourse = false;

        assertEquals(inCourse, student.isInCourseNow());
    }
}

