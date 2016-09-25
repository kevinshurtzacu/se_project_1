package implementation;

/**
 * Represents a term (semester, short-course, etc).
 *
 * Term can represent:
 * <ul>
 *     <li>Fall</li>
 *     <li>Spring</li>
 *     <li>January short-course</li>
 *     <li>Maymester course</li>
 * </ul>
 *
 * The Term enum can be extended at a later point to represent
 * other similar terms.
 */
public enum Term
{
    FALL,       // Fall term
    SPRING,     // Spring term
    JAN_SHORT,  // January short-course
    MAY,        // "Maymester" short-course
}