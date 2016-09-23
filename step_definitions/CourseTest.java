/**
 * These defintions will be used by the cucumber_progress.sh scripts at the root of the project
 * directory to test Course features, referencing the course.feature in the
 * features directory.
 *
 * @author  Kevin Shurtz
 * @version 1.0
 */
package step_definitions;

import cucumber.api.java.en.*;
import cucumber.api.PendingException;
import implementation.Course;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

public class CourseTest
{
    private Course course = new Course(12345, "CS", "Programming I", 130, "", 2015, "FALL", 90.5, false);; // Course object to be tested
    private Object result = null; // result of each test

    /*
    @Before
    public void setUp()
    {
        course = new Course(12345, "CS", "Programming I", 130, "", 2015, "FALL", 90.5, false);
        result = null;
    }
*/

    @Given("^the course has the subject (.*?)$")
    public void theCourseHasTheSubject(String subject) throws Throwable
    {
        System.out.println(subject);
        System.out.println(course);
        course.setCourseSubject(subject);
    }

    @Given("^the course has the title ((?:\\w+\\s?)+)$")
    public void theCourseHasTheTitle(String title) throws Throwable
    {
        course.setCourseTitle(title);
    }

    @Given("^the course has the number (\\d+)$")
    public void theCourseHasTheNumber(int courseNumber) throws Throwable
    {
        course.setCourseNumber(courseNumber);
    }

    @Given("^the course has the number extension (.*?)?$")
    public void theCourseHasTheNumberExtension(String ext) throws Throwable
    {
        course.setCourseNumberExt(ext);
    }

    @Given("^the course is in the year (\\d+)$")
    public void theCourseIsInTheYear(int year) throws Throwable
    {
        course.setCourseYear(year);
    }

    @Given("^the course is in the (.*?) term$")
    public void theCourseIsInTheTerm(String term) throws Throwable
    {
        course.setCourseTerm(term);
    }

    @Given("^the student has a grade of (\\d+)$")
    public void theStudentHasAGradeOf(int grade) throws Throwable
    {
        course.setCourseGrade(grade);
    }

    @Given("^the student (is not|is) actively taking the course$")
    public void theStudentActivelyTakingTheCourse(String active) throws Throwable
    {
        boolean inCourse;

        if (active.equals("is"))
            inCourse = true;
        else
            inCourse = false;

        course.setInCourseNow(inCourse);
    }

    @When("^I ask for the course (\\w+)$")
    public void iAskForTheCourse(String request) throws Throwable
    {
        if (request.equals("subject"))
            result = course.getCourseSubject();

        if (request.equals("title"))
            result = course.getCourseTitle();

        if (request.equals("number"))
            result = course.getCourseNumber();

        if (request.equals("extension"))
            result = course.getCourseNumberExt();

        if (request.equals("year"))
            result = course.getCourseYear();

        if (request.equals("term"))
            result = course.getCourseTerm();

        if (request.equals("grade"))
            result = course.getCourseGrade();
    }

    @When("^I ask if the student is active in the course$")
    public void iAskIfTheStudentIsActiveInTheCourse() throws Throwable
    {
        result = course.isInCourseNow();
    }

    @Then("^I receive the string ((?:\\w+\\s?)+)$")
    public void iRecieve(String expectation) throws Throwable
    {
        String realResult = (String)result;
        assertEquals(expectation, realResult);
    }

    @Then("^I receive the number (\\d+)$")
    public void iReceiveTheNumber(Integer number) throws Throwable
    {
        Integer realResult = (Integer)result;
        assertEquals(number, realResult);
    }

    @Then("^I receive the double (\\d+(?:\\.\\d+)?)$")
    public void iReceiveTheDouble(Double grade) throws Throwable
    {
        Double realResult = (Double)result;
        assertEquals(grade, realResult);
    }

    @Then("^I am told that he (is|is not) active$")
    public void iAmToldThatHeIsActive(String active) throws Throwable
    {
        boolean inCourse;

        if (active.equals("is"))
            inCourse = true;
        else
            inCourse = false;

        assertEquals(inCourse, course.isInCourseNow());
    }
}

