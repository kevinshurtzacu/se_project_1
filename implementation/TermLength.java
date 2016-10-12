package implementation;

/**
 * Represents a term's length (Intensive, Full Term, Session I, etc).
 *
 * Term length can represent:
 * <ul>
 *     <li>ACU Worldwide Session 1</li>
 *     <li>ACU Worldwide Session 2</li>
 *     <li>DMIN Intensive Course</li>
 *     <li>Full Term</li>
 *     <li>Intensive Course</li>
 *     <li>January Intensive Course</li>
 *     <li>Session I</li>
 *     <li>Session I Extended Course</li>
 *     <li>Session II</li>
 *     <li>Session II Extended Course</li>
 *     <li>Session III</li>
 *     <li>Session III Extended Course</li>
 *     <li>Session IV</li>
 *     <li>Session IV Extended Course</li>
 * </ul>
 */
public enum TermLength
{
    UNDEFINED,                  // Default value
    ACU_WORLDWIDE_SESSION_1,
    ACU_WORLDWIDE_SESSION_2,
    DMIN_INTENSIVE_COURSE,
    FULL_TERM,
    INTENSIVE_COURSE,
    JANUARY_INTENSIVE_COURSE,
    SESSION_I,
    SESSION_I_EXTENDED_COURSE,
    SESSION_II,
    SESSION_II_EXTENDED_COURSE,
    SESSION_III,
    SESSION_III_EXTENDED_COURSE,
    SESSION_IV,
    SESSION_IV_EXTENDED_COURSE,
}