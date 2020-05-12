package connectFour;
/*
 * Exceptions that regard a move that is not allowed such as inserting a chip in a full column 
 */
public class IllegalMoveException extends Exception{
	
	public IllegalMoveException() {
		super("This move was not possible, try another move");
	} 
	
	public IllegalMoveException(String message) {
		super(message);
	}

}
