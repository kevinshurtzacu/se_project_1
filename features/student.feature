Feature: Student instance maintains data about itself
  Student is an object to represent one of a a student associated with a course description.
  It contains information particular a particular student in a course description, such as
  his/her grade, and the time that he/she takes it.

  Student can refer to a present student, or a past student.

  Scenario Outline: System asks for the course number extension
    Given the student has the number extension <extension>
      And the student is in the year <year>
      And the student is in the <term> term
      And the student grade is <grade>
      And the student <active> taking the course
    When I ask for the course extension
    Then I receive the string <result> from student

    Examples:
      | extension | year | term     | grade | active | result |
      | 01        | 2016 | Spring   | 92    | is not | 01     |
      | 02        | 2016 | Fall     | 70    | is     | 02     |
      | H1        | 2015 | Fall     | 90    | is not | H1     |
      | 05        | 2016 | Spring   | 88    | is not | 05     |
      | H2        | 2016 | Spring   | 91    | is not | H2     |

  Scenario Outline: System asks for the year in which the student took the course
    Given the student has the number extension <extension>
      And the student is in the year <year>
      And the student is in the <term> term
      And the student grade is <grade>
      And the course <active> taking the course
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
      And the course <active> taking the course
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
      And the course <active> taking the course
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
      And the course <active> taking the course
    When I ask if the course is taking the course
    Then I am told that it <result> being taken by the student

    Examples:
      | extension | year | term     | grade | active | result |
      |           | 2016 | Spring   | 92    | is not | is not |
      |           | 2016 | Fall     | 70    | is     | is     |
      |           | 2016 | Fall     | 90    | is     | is     |
      |           | 2016 | Spring   | 88    | is not | is not |
      |           | 2016 | Spring   | 91    | is not | is not |
