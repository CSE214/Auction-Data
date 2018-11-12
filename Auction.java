package auctionDatabase;

public class Auction {
	int timeRemaining;
	double currentBid;
	String auctionID;
	String sellerName;
	String buyerName;
	String itemInfo;

	/**
	 * @return The timeRemaining of this instance
	 */
	public int getTimeRemaining() {
		return timeRemaining;
	}

	/**
	 * @param timeRemaining The new timeRemaining to set
	 */
	public void setTimeRemaining(int timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	/**
	 * @return The currentBid of this instance
	 */
	public double getCurrentBid() {
		return currentBid;
	}

	/**
	 * @param currentBid The new currentBid to set
	 */
	public void setCurrentBid(double currentBid) {
		this.currentBid = currentBid;
	}

	/**
	 * @return The auctionID of this instance
	 */
	public String getAuctionID() {
		return auctionID;
	}

	/**
	 * @param auctionID The new auctionID to set
	 */
	public void setAuctionID(String auctionID) {
		this.auctionID = auctionID;
	}

	/**
	 * @return The sellerName of this instance
	 */
	public String getSellerName() {
		return sellerName;
	}

	/**
	 * @param sellerName The new sellerName to set
	 */
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	/**
	 * @return The buyerName of this instance
	 */
	public String getBuyerName() {
		return buyerName;
	}

	/**
	 * @param buyerName The new buyerName to set
	 */
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	/**
	 * @return The itemInfo of this instance
	 */
	public String getItemInfo() {
		return itemInfo;
	}

	/**
	 * @param itemInfo The new itemInfo to set
	 */
	public void setItemInfo(String itemInfo) {
		this.itemInfo = itemInfo;
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
}
