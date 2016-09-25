Feature: Course description
  CourseDescription will be an object to represent a single course
  offered by the university.  This defers from Course, which contains
  data about a single student's and will will possessed by the StudentProfile
  object.

  Scenario Outline: The system asks for the course description crn
    Given the course description has the "crn" <crn>
      And the course description has the "subject" <subject>
      And the course description has the "number" <number>
      And the course description has the "title" <title>
    When I ask for the course description "crn"
    Then I receive the integer <result> from course description

    Examples:
      | crn   | subject | number | title                       | result |
      | 12345 | IT      | 221    | Networking                  | 12345  |
      | 54321 | IT      | 220    | Intro to Databases and DBMS | 54321  |
      | 13524 | CS      | 120    | Programming I               | 13524  |
      | 13542 | CS      | 130    | Programming II              | 13542  |
      | 53142 | BIBL    | 101    | Jesus Life and Teachings    | 53142  |

  Scenario Outline: The system asks for the course description subject
    Given the course description has the "crn" <crn>
      And the course description has the "subject" <subject>
      And the course description has the "number" <number>
      And the course description has the "title" <title>
    When I ask for the course description "subject"
    Then I receive the string <result> from course description

    Examples:
      | crn   | subject | number | title                       | result |
      | 12345 | IT      | 221    | Networking                  | IT     |
      | 54321 | IT      | 220    | Intro to Databases and DBMS | IT     |
      | 13524 | CS      | 120    | Programming I               | CS     |
      | 13542 | CS      | 130    | Programming II              | CS     |
      | 53142 | BIBL    | 101    | Jesus Life and Teachings    | BIBL   |

  Scenario Outline: The system asks for the course description number
    Given the course description has the "crn" <crn>
      And the course description has the "subject" <subject>
      And the course description has the "number" <number>
      And the course description has the "title" <title>
    When I ask for the course description "number"
    Then I receive the integer <result> from course description

    Examples:
      | crn   | subject | number | title                        | result |
      | 12345 | IT      | 221    | Networking                   | 221    |
      | 54321 | IT      | 220    | Intro to Databases and DBMS  | 220    |
      | 13524 | CS      | 120    | Programming I                | 120    |
      | 13542 | CS      | 130    | Programming II               | 130    |
      | 53142 | BIBL    | 101    | Jesus Life and Teachings     | 101    |

  Scenario Outline: The system asks for the course description title
    Given the course description has the "crn" <crn>
      And the course description has the "subject" <subject>
      And the course description has the "number" <number>
      And the course description has the "title" <title>
    When I ask for the course description "title"
    Then I receive the string <result> from course description

    Examples:
      | crn   | subject | number | title                        | result                       |
      | 12345 | IT      | 221    | Networking                   | Networking                   |
      | 54321 | IT      | 220    | Intro to Databases and DBMS  | Intro to Databases and DBMS  |
      | 13524 | CS      | 120    | Programming I                | Programming I                |
      | 13542 | CS      | 130    | Programming II               | Programming II               |
      | 53142 | BIBL    | 101    | Jesus: Life and Teachings    | Jesus: Life and Teachings    |
