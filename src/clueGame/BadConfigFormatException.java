package clueGame;
public class BadConfigFormatException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String badFile;
	public BadConfigFormatException(String badFile) throws Throwable {
		super();
		this.badFile = badFile;
		System.out.println("Bad File");
	}
}
