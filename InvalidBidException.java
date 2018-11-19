

/**
 * <code>InvalidBidException</code> is thrown when a bid isn't high enough to
 * overtake the current bid.
 *
 * @author Sayan Sivakumaran e-mail: sayan.sivakumaran@stonybrook.edu Stony
 *         Brook ID: 110261379
 **/
public class InvalidBidException extends Exception {
	private static final long serialVersionUID = -8568120812429473972L;

	/**
	 * Returns an instance of <code>InvalidBidException</code>.
	 */
	public InvalidBidException() {
		super();
	}

	/**
	 * Returns an instance of <code>InvalidBidException</code> along with the
	 * specified message.
	 * 
	 * @param message The message that accompanies the error.
	 */
	public InvalidBidException(String message) {
		super(message);
	}
}
