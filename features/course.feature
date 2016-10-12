Feature: Course instance maintains data about itself
  Course is an object to represent one of a student's classes.  It contains information
  regarding particular to a student variant of the class, such as his/her grade,
  and the time that he/she takes it.

  Course can refer to classes a student has actively taken, as well as classes he/she
  has taken in the past.

  Scenario Outline: System asks for the course subject
    Given the course has the subject <subject>
    When I ask for the course subject
    Then I receive the string <result> from course

    Examples:
      | subject | result |
      | ACCT    | ACCT   |
      | BIBL    | BIBL   |
      | IT      | IT     |
      | CS      | CS     |
      | IS      | IS     |

  Scenario Outline: System asks for the course number
    Given the course has the number <number>
    When I ask for the course number
    Then I receive the string <result> from course

    Examples:
      | number  | result |
      | 101     | 101    |
      | 374     | 374    |
      | 102     | 102    |
      | 603     | 603    |
      | 220     | 220    |

  Scenario Outline: System asks for the course section
    Given the course has the section <section>
    When I ask for the course section
    Then I receive the string <result> from course

    Examples:
      | section | result |
      | 01      | 01     |
      | 02      | 02     |
      | H1      | H1     |
      | 05      | 05     |
      | H2      | H2     |

  Scenario Outline: System asks for the year in which the course was offered
    Given the course is in the year <year>
    When I ask for the course year
    Then I receive the integer <result> from course

    Examples:
      | year | result |
      | 2016 | 2016   |
      | 2013 | 2013   |
      | 2012 | 2012   |
      | 2014 | 2014   |
      | 2015 | 2015   |

  Scenario Outline: System asks for the term in which the course was offered
    Given the course is in the <term> term
    When I ask for the course term
    Then I receive the term <result> from course

    Examples:
      | term     | result |
      | Spring   | Spring |
      | Fall     | Fall   |
      | Fall     | Fall   |
      | Spring   | Spring |
      | Spring   | Spring |

  Scenario Outline: System asks for the student's grade in the course
    Given the course grade is <grade>
    When I ask for the course grade
    Then I receive the string <result> from course

    Examples:
      | grade | result |
      | A     | A      |
      | C     | C      |
      | 90    | A      |
      | 88    | B      |
      | F     | F      |

  Scenario Outline: System asks if student is active in course
    Given the course <active> being taken by the student
    When I ask if the course is being taken by the student
    Then I am told that it <result> being taken by the student

    Examples:
      | active | result |
      | is not | is not |
      | is     | is     |
      | is     | is     |
      | is not | is not |
      | is not | is not |

  Scenario Outline: System asks if two courses are equal
    Given both courses have the subject <subject>
      And both courses have the number <number>
      And both courses have the section <section>
      And both courses are in the year <year>
      And both courses are in the <term> term
      And both courses have the grade <grade>
      And both courses <active> being taken by the student
    When I ask if the courses are equal
    Then I am told that the first course and second course <result> equal

    Examples:
      | subject | number | section | year | term   | grade | active  | result  |
      | IT      | 220    |         | 2016 | Fall   | 70    | are     | are     |
      | BIBL    | 201    |         | 2016 | Spring | 88    | are not | are     |
      | CS      | 101    |         | 2016 | Spring | 72    | are not | are     |


    Scenario Outline: System asks if two courses are equal
      Given the first course grade is <grade 1>
        And the second course grade is <grade 2>
      When I ask if the courses are equal
      Then I am told that the first course and second course <result> equal

      Examples:
        | grade 1 | grade 2 | result  |
        | A       | B       | are     |
        | A       | A       | are     |
        | F       | F       | are     |
        | C       | D       | are     |
