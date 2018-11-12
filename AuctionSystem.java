package auctionDatabase;

/**
 * This class will allow the user to interact with the database by listing open
 * auctions, make bids on open auctions, and create new auctions for different
 * items. In addition, the class should provide the functionality to load a
 * saved (serialized) AuctionTable or create a new one if a saved table does not
 * exist.
 *
 * @author Sayan Sivakumaran e-mail: sayan.sivakumaran@stonybrook.edu Stony
 *         Brook ID: 110261379
 **/
public class AuctionSystem {
	public static void main(String[] args) {
		AuctionTable.buildFromURL("http://tinyurl.com/nbf5g2h");
	}
}
