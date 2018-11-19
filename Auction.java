package auctionDatabase;

import java.text.DecimalFormat;

/**
 * <code>Auction</code> represents an auction currently in the database.
 *
 * @author Sayan Sivakumaran e-mail: sayan.sivakumaran@stonybrook.edu Stony
 *         Brook ID: 110261379
 **/
public class Auction {
	int timeRemaining; // time remaining in hours
	double currentBid; // current highest bid
	String auctionID; // id of auction
	String sellerName; // name of seller
	String buyerName; // name of buyer
	String itemInfo; // item info

	/**
	 * @return The timeRemaining of this instance
	 */
	public int getTimeRemaining() {
		return timeRemaining;
	}

	/**
	 * @return The currentBid of this instance
	 */
	public double getCurrentBid() {
		return currentBid;
	}

	/**
	 * @return The auctionID of this instance
	 */
	public String getAuctionID() {
		return auctionID;
	}

	/**
	 * @return The sellerName of this instance
	 */
	public String getSellerName() {
		return sellerName;
	}

	/**
	 * @return The buyerName of this instance
	 */
	public String getBuyerName() {
		return buyerName;
	}

	/**
	 * @return The itemInfo of this instance
	 */
	public String getItemInfo() {
		return itemInfo;
	}

	/**
	 * Decreases the time remaining for this auction by the specified amount. If
	 * time is greater than the current remaining time for the auction, then the
	 * time remaining is set to 0 (i.e. no negative times).
	 * 
	 * @param time The amount of time to decrement by
	 */
	public void decrementTimeRemaining(int time) {
		if (time >= timeRemaining) {
			this.timeRemaining = 0;
		} else {
			this.timeRemaining -= time;
		}
	}

	/**
	 * Makes a new bid on this auction. If bidAmt is larger than currentBid, then
	 * the value of currentBid is replaced by bidAmt and buyerName is is replaced by
	 * bidderName.
	 * 
	 * <dl>
	 * <dt>Preconditions</dt>
	 * <dd>The auction is not closed. In other words, timeRemaining > 0.</dd>
	 * </dl>
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd><code>currentBid</code> reflects the largest bid placed on this object.
	 * If the auction is closed, throw a ClosedAuctionException.</dd>
	 * </dl>
	 * 
	 * @param bidderName The name of the bidder
	 * @param bidAmt     The amount that is being put up for bid
	 * @throws ClosedAuctionException If the auction is closed
	 * @throws InvalidBidException    If the bid is not high enough
	 */
	public void newBid(String bidderName, double bidAmt) throws ClosedAuctionException, InvalidBidException {
		if (this.timeRemaining == 0) {
			throw new ClosedAuctionException("You can't bid on a closed auction");
		}
		if (bidAmt <= this.currentBid) {
			throw new InvalidBidException("That bid isn't high enough");
		}
		this.currentBid = bidAmt;
		this.buyerName = bidderName;
	}

	/**
	 * Returns an instance of Auction
	 * 
	 * @param timeRemaining The time remaining in hours
	 * @param currentBid    The current highest bid on the auction
	 * @param auctionID     The ID of the auction
	 * @param sellerName    The name of the seller
	 * @param buyerName     The name of the buyer
	 * @param itemInfo      Item information
	 */
	public Auction(int timeRemaining, double currentBid, String auctionID, String sellerName, String buyerName,
			String itemInfo) {
		super();
		this.timeRemaining = timeRemaining;
		this.currentBid = currentBid;
		this.auctionID = auctionID;
		this.sellerName = sellerName;
		this.buyerName = buyerName;
		this.itemInfo = itemInfo;
	}

	@Override
	public String toString() {
		DecimalFormat df = new DecimalFormat("0.00");
		return String.format("| %-12s| $%-12s| %-24s| %-24s| %-11s| %-110s|", auctionID, df.format(currentBid),
				sellerName, buyerName, timeRemaining + " hours", itemInfo);
	}
}
