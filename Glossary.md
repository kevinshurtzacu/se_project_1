# Glossary
## Java Objects / Database Entities
| Term              | Definition                                                                                                        |
|:-----------------:|:------------------------------------------------------------------------------------------------------------------|
| Course            | An instance of a course that is being taken by a student, contains data specific to student                       |
| CourseDescription | The canonical identity of a Course taken by a student; every Course corresponds to exactly one CourseDescription  |
| Student           | A person enrolled in a course; identified by Banner ID; example: Billy Bob, Banner ID 000444555                   |
| StudentProfile    | The canonical identity of a Student, containing a student's name, banner ID, email, and similar information       |
| Prereqisite       | An entity that represents a set of courses that satisfy an expectation for a CourseDescription                    |
| Term              | An entity that represents a time when a class was taken; Values: FALL, SRING, JAN_SHORT, MAY                      |
| JavaDoc           | Documentation generated for the reference of clients and developers, viewable as a series of HTML pages           |
| Grade             | This represents the grade point avarage a student has in a class; measured on a 100 point scale                   |
| CRN               | Course Registration Number, the unique identification number for a course maintained by a CourseDescription       |


## Cucumber Terms
| Term             | Definition                                                                                                         |
|:----------------:|:-------------------------------------------------------------------------------------------------------------------|
| Course           | A certain class that has happened, regardless of date, example: Programming 1 aka CS120                            |
| Feature File     | A file that describes a feature in "Gherkin" syntax, which allows Cucumber to generate test cases from the file    |
| Step Definition  | A java file that defines a procedure that each step does; provides instructions to use test data                   |
| RegEx            | Pattern-matching strings used find patterns in text; used to match feature files to step definitions.              |
| Cucumber_pretty  | A script that runs the Cucumber testing tool; output explains every test in detail                                 |
| Cucumber_progress| A script that runs the Cucumber testing tool; output is very succinct and incates progress through each test       |




