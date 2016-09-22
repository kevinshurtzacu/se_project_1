/* Student.java
 */

public class Student
{
	public static void main(String[] args) {
		System.out.println("I'm a student");
	}


	/**
	 * Assignes the Banner ID
	 * @param bannerNum
	 */
	private void setBanner(int bannerNum)
	{
		// If the Banner ID is the wrong size
		if (Integer.parseInt(banner).length() != 9)
			throw new IllegalArgumentException("Banner ID has incorrect number of digits (has " + Integer.parseInt(banner).length() + ", needs 9)");

		// Otherwise, set the Banner ID
		banner = bannerNum;
	}

}
