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
public class AuctionTable extends HashMap {
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
	public static void buildFromURL(String URL) throws IllegalArgumentException {
		DataSource ds = DataSource.connectXML(URL);
		ds.load();
		String[] sellerNames = ds.fetchStringArray("listing/seller_info/seller_name");
		String[] currentBid = ds.fetchStringArray("listing/auction_info/current_bid");
		String[] timeLeft = ds.fetchStringArray("listing/auction_info/time_left");
		String[] idNum = ds.fetchStringArray("listing/auction_info/id_num");
		String[] bidderNames = ds.fetchStringArray("listing/auction_info/high_bidder/bidder_name");
		String[] memoryInfo = ds.fetchStringArray("listing/item_info/memory");
		String[] hardDriveInfo = ds.fetchStringArray("listing/item_info/hard_drive");
		String[] cpuInfo = ds.fetchStringArray("listing/item_info/cpu");

	}
}