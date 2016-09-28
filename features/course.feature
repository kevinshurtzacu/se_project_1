Feature: Course instance maintains data about itself
  Course is an object to represent one of a student's classes.  It contains information
  regarding particular to a student variant of the class, such as his/her grade,
  and the time that he/she takes it.

  Course can refer to classes a student has actively taken, as well as classes he/she
  has taken in the past.

  Scenario Outline: System asks for the course number extension
    Given the course has the number extension <extension>
      And the course is in the year <year>
      And the course is in the <term> term
      And the course grade is <grade>
      And the course <active> being taken by the student
    When I ask for the course extension
    Then I receive the string <result> from course

    Examples:
      | extension | year | term     | grade | active | result |
      | 01        | 2016 | Spring   | 92    | is not | 01     |
      | 02        | 2016 | Fall     | 70    | is     | 02     |
      | H1        | 2015 | Fall     | 90    | is not | H1     |
      | 05        | 2016 | Spring   | 88    | is not | 05     |
      | H2        | 2016 | Spring   | 91    | is not | H2     |

  Scenario Outline: System asks for the year in which the course was offered
    Given the course has the number extension <extension>
      And the course is in the year <year>
      And the course is in the <term> term
      And the course grade is <grade>
      And the course <active> being taken by the student
    When I ask for the course year
    Then I receive the integer <result> from course

    Examples:
      | extension | year | term     | grade | active | result |
      |           | 2016 | Spring   | 92    | is not | 2016   |
      |           | 2013 | Fall     | 70    | is     | 2013   |
      |           | 2012 | Fall     | 90    | is not | 2012   |
      |           | 2014 | Spring   | 88    | is not | 2014   |
      |           | 2015 | Spring   | 91    | is not | 2015   |

  Scenario Outline: System asks for the term in which the course was offered
    Given the course has the number extension <extension>
      And the course is in the year <year>
      And the course is in the <term> term
      And the course grade is <grade>
      And the course <active> being taken by the student
    When I ask for the course term
    Then I receive the term <result> from course

    Examples:
      | extension | year | term     | grade | active | result |
      |           | 2016 | Spring   | 92    | is not | Spring |
      |           | 2016 | Fall     | 70    | is     | Fall   |
      |           | 2015 | Fall     | 90    | is not | Fall   |
      |           | 2016 | Spring   | 88    | is not | Spring |
      |           | 2016 | Spring   | 91    | is not | Spring |

  Scenario Outline: System asks for the student's grade in the course
    Given the course has the number extension <extension>
      And the course is in the year <year>
      And the course is in the <term> term
      And the course grade is <grade>
      And the course <active> being taken by the student
    When I ask for the course grade
    Then I receive the double <result> from course

    Examples:
      | extension | year | term     | grade | active | result |
      |           | 2016 | Spring   | 92    | is not | 92     |
      |           | 2016 | Fall     | 70    | is     | 70     |
      |           | 2015 | Fall     | 90    | is not | 90     |
      |           | 2016 | Spring   | 88    | is not | 88     |
      |           | 2016 | Spring   | 91    | is not | 91     |

  Scenario Outline: System asks if student is active in course
    Given the course has the number extension <extension>
      And the course is in the year <year>
      And the course is in the <term> term
      And the course grade is <grade>
      And the course <active> being taken by the student
    When I ask if the course is being taken by the student
    Then I am told that it <result> being taken by the student

    Examples:
      | extension | year | term     | grade | active | result |
      |           | 2016 | Spring   | 92    | is not | is not |
      |           | 2016 | Fall     | 70    | is     | is     |
      |           | 2016 | Fall     | 90    | is     | is     |
      |           | 2016 | Spring   | 88    | is not | is not |
      |           | 2016 | Spring   | 91    | is not | is not |

  Scenario Outline: System asks checks if two courses are equal
    Given the first course has the number extension <extension 1>
      And the first course is in the year <year 1>
      And the first course is in the <term 1> term
      And the first course grade is <grade 1>
      And the first course <active 1> being taken by the student
    Given the second course has the number extension <extension 2>
      And the second course is in the year <year 2>
      And the second course is in the <term 2> term
      And the second course grade is <grade 2>
      And the second course <active 2> being taken by the student
    When I ask if the courses are equal
    Then I am told that the first course and second course <result> equal

    Examples:
      | extension 1 | year 1 | term 1   | grade 1 | active 1 | extension 2 | year 2 | term 2   | grade 2 | active 2 | result  |
      |             | 2016   | Spring   | 92      | is not   |             | 2016   | Fall     | 92      | is not   | are not |
      |             | 2016   | Fall     | 70      | is       |             | 2016   | Fall     | 80      | is       | are     |
      |             | 2015   | Fall     | 90      | is       |             | 2016   | Fall     | 90      | is       | are not |
      |             | 2016   | Spring   | 88      | is not   |             | 2016   | Spring   | 88      | is       | are     |
      |             | 2016   | Spring   | 72      | is not   |             | 2016   | Spring   | 91      | is       | are     |

  Scenario Outline: System asks for course description information
    Given the course has the crn <course crn>
      And the course is in the year <course year>
      And the course is in the <course term> term
    Given there is a course description with a "crn" of <cd crn>
      And there is a course description with a "number" of <cd num>
      And there is a course description with a "subject" of <cd subject>
      And there is a course description with a "title" of <cd title>
    When I ask for the course title
    Then I receive the string <result> from student profile

    Examples:
      | course crn | course year | course term | cd crn | cd num | cd subject | cd title                  | result                    |
      | 12345      | 2016        | Spring      | 12345  | 101    | BIBL       | Jesus: Life and Teachings | Jesus: Life and Teachings |
      | 54321      | 2015        | Fall        | 54321  | 374    | CS         | Software Engineering      | Software Engineering      |
      | 12345      | 2012        | Maymester   | 12345  | 220    | IT         | Databases and DBMS        | Databases and DBMS        |