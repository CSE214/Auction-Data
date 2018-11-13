package auctionDatabase;

import java.util.HashMap;

import big.data.DataSource;

/**
 * <code>AuctionTable</code> stores a collection of open auctions using a hash
 * table.
 *
 * @author Sayan Sivakumaran e-mail: sayan.sivakumaran@stonybrook.edu Stony
 *         Brook ID: 110261379
 **/
public class AuctionTable extends HashMap<String, Auction> {
	private static final long serialVersionUID = 8196374631832076761L;

	/**
	 * Uses the BigData library to construct an AuctionTable from a remote data
	 * source.
	 *
	 * <dl>
	 * <dt>Preconditions</dt>
	 * <dd>URL represents a data source which can be connected to using the BigData
	 * library. The data source has proper syntax.</dd>
	 * </dl>
	 * 
	 * @param URL URL to fetch data from
	 * @return A constructed AuctionTable reflecting the fetched XML data
	 * @throws IllegalArgumentException Thrown if the URL does not represent a valid
	 *                                  datasource (can't connect or invalid syntax)
	 */
	public static AuctionTable buildFromURL(String URL) throws IllegalArgumentException {
		try {
			DataSource ds = DataSource.connectXML(URL);
			ds.load();
			String[] sellerNames = ds.fetchStringArray("listing/seller_info/seller_name");
			String[] currentBids = ds.fetchStringArray("listing/auction_info/current_bid");
			String[] timeLeft = ds.fetchStringArray("listing/auction_info/time_left");
			String[] idNums = ds.fetchStringArray("listing/auction_info/id_num");
			String[] bidderNames = ds.fetchStringArray("listing/auction_info/high_bidder/bidder_name");
			String[] memoryInfo = ds.fetchStringArray("listing/item_info/memory");
			String[] hardDriveInfo = ds.fetchStringArray("listing/item_info/hard_drive");
			String[] cpuInfo = ds.fetchStringArray("listing/item_info/cpu");
			AuctionTable auctionTable = new AuctionTable();

			for (int i = 0; i < idNums.length; i++) {
				Auction newAuction = new Auction(BigDataParser.getTimeLeft(timeLeft[i]),
						BigDataParser.getCurrentBid(currentBids[i]), idNums[i], sellerNames[i], bidderNames[i],
						BigDataParser.getItemInfo(memoryInfo[i], hardDriveInfo[i], cpuInfo[i]));
				auctionTable.put(idNums[i], newAuction);
			}

			return auctionTable;
		} catch (Exception e) {
			throw new IllegalArgumentException("Sorry - we can't seem to connect to the URL.");
		}
	}

	/**
	 * Manually post an auction, and add it to the table.
	 * 
	 * @param auctionID The ID of the auction
	 * @param auction   The auction
	 * @throws IllegalArgumentException If the auctionID is already in the table
	 * 
	 */
	public void putAuction(String auctionID, Auction auction) throws IllegalArgumentException {
		if (get(auctionID) == null) {
			put(auctionID, auction);
		} else {
			throw new IllegalArgumentException("There can only be one auction per auction ID.");
		}
	}

	/**
	 * Get the information of an Auction that contains the given ID as key
	 * 
	 * @param auctionID ID of the desired auction
	 * 
	 * @return The obtained auction, or null if there is no match
	 */
	public Auction getAuction(String auctionID) {
		return get(auctionID);
	}

	/**
	 * Simulates the passing of time. Decrease the timeRemaining of all Auction
	 * objects by the amount specified. The value cannot go below 0.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>All Auctions in the table have their timeRemaining timer decreased. If
	 * the original value is less than the decreased value, set the value to 0.</dd>
	 * </dl>
	 * 
	 * @param numHours The amount of time to pass
	 * 
	 * @throws IllegalArgumentException if numHours is non-positive
	 */
	public void letTimePass(int numHours) throws IllegalArgumentException {
		if (numHours <= 0)
			throw new IllegalArgumentException("numHours must be positive.");
		for (Auction auction : values()) {
			auction.decrementTimeRemaining(numHours);
		}
	}

	/**
	 * Iterates over all Auction objects in the table and removes them if they are
	 * expired (timeRemaining == 0).
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>Only open auctions remain in the table.</dd>
	 * </dl>
	 */
	public void removeExpiredAuctions() {
		for (String key : keySet()) {
			if (get(key).getTimeRemaining() == 0)
				remove(key);
		}
	}
}
