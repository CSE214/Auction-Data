

/**
 * <code>ClosedAuctionException</code> is thrown when a user attempts to bid on
 * a closed auction.
 *
 * @author Sayan Sivakumaran e-mail: sayan.sivakumaran@stonybrook.edu Stony
 *         Brook ID: 110261379
 **/
public class ClosedAuctionException extends Exception {
	private static final long serialVersionUID = -8568120812429473972L;

	/**
	 * Returns an instance of <code>ClosedAuctionException</code>.
	 */
	public ClosedAuctionException() {
		super();
	}

	/**
	 * Returns an instance of <code>ClosedAuctionException</code> along with the
	 * specified message.
	 * 
	 * @param message The message that accompanies the error.
	 */
	public ClosedAuctionException(String message) {
		super(message);
	}
}
