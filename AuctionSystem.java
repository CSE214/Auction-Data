package auctionDatabase;

import java.text.DecimalFormat;

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
	private static InputHandler in; // Takes input from user
	private static AuctionTable auctionTable;
	private static String username;

	/**
	 * Initializes the program parameters.
	 */
	public static void init() {
		in = new InputHandler();
		auctionTable = new AuctionTable();
		System.out.print("Please select a username: ");
		username = in.nextLine();
	}

	/**
	 * Prints a list of commands for the user.
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>The list of commands has been printed.</dd>
	 * </dl>
	 * 
	 */
	public static void printMenu() {
		System.out.println("\n(D) Import Data from URL\r\n" + "(A) Create a New Auction\r\n" + "(B) Bid on an Item\r\n"
				+ "(I) Get Info on Auction\r\n" + "(P) Print All Auctions\r\n" + "(R) Remove Expired Auctions\r\n"
				+ "(T) Let Time Pass\r\n" + "(Q) Quit" + "\n");
	}

	/**
	 * Imports new data into the auctionTable.
	 */
	public static void importData() {
		System.out.print("Please enter a URL: ");
		String url = in.nextLine();
		System.out.println("\nLoading...");
		try {
			auctionTable = AuctionTable.buildFromURL(url);
			System.out.println("Auction data loaded successfully!");
		} catch (Exception e) {
			System.out.println("Something went wrong - please try again.");
		}
	}

	/**
	 * Creates a user-defined auction and adds it to the table.
	 */
	public static void createAuction() {
		System.out.println("Creating a new auction as " + username + ".");
		System.out.print("Please enter an Auction ID: ");
		String id = in.nextLine();
		System.out.print("Please enter an Auction time (hours): ");
		int hours = in.nextNonNegativeInt();
		System.out.print("Please enter some Item Info: ");
		String info = in.nextLine();

		auctionTable.put(id, new Auction(hours, 0, id, username, "", info));
		System.out.println("\nAuction " + id + " inserted into table.");
	}

	/**
	 * Places a bid on an existing auction.
	 */
	public static void makeBid() {
		System.out.print("Please enter an Auction ID: ");
		String id = in.nextLine();
		Auction auction = auctionTable.getAuction(id);
		if (auction == null) {
			System.out.println("\nThat auction does not exist.");
		} else if (auction.timeRemaining == 0) {
			DecimalFormat df = new DecimalFormat("0.00");
			System.out.println("\nAuction " + auction.getAuctionID() + " is CLOSED.");
			System.out.println("\tCurrent Bid: $" + df.format(auction.getCurrentBid()));
			System.out.println("\nYou can no longer bid on this item.");
		} else {
			DecimalFormat df = new DecimalFormat("0.00");
			System.out.println("\nAuction " + auction.getAuctionID() + " is OPEN.");
			System.out.println("\tCurrent Bid: $" + df.format(auction.getCurrentBid()));
			System.out.print("\nWhat would you like to bid?: ");
			double bid = in.nextPositiveDouble();
			try {
				auction.newBid(username, bid);
				System.out.println("Bid accepted.");
			} catch (ClosedAuctionException e) {
				System.out.println("The auction is already closed.");
			} catch (InvalidBidException e) {
				System.out.println("That bid is not high enough.");
			}
		}
	}

	/**
	 * Prints out information on the desired auction to the user.
	 */
	public static void printInfo() {
		System.out.print("Please enter an Auction ID: ");
		String id = in.nextLine();
		Auction auction = auctionTable.getAuction(id);

		System.out.println("\nAuction " + auction.getAuctionID() + ":");
		System.out.println("\tSeller: " + auction.getSellerName());
		System.out.println("\tBuyer: " + auction.getBuyerName());
		System.out.println("\tTime: " + auction.getTimeRemaining() + " hours");
		System.out.println("\tInfo: " + auction.getItemInfo());
	}

	/**
	 * Prints the current auctionTable to the user in tabular format.
	 */
	public static void printTable() {
		auctionTable.printTable();
	}

	/**
	 * Removes all expired auctions from the auctionTable
	 */
	public static void removeExpiredAuctions() {
		System.out.println("Removing expired auctions...");
		auctionTable.removeExpiredAuctions();
		System.out.println("All expired auctions removed.");
	}

	/**
	 * Lets the specified number of hours pass in the simulation. All auctions will
	 * have their time remaining updates.
	 */
	public static void letTimePass() {
		System.out.print("How many hours should pass?: ");
		int hours = in.nextPositiveInt();

		System.out.println("\nTime passing...");
		auctionTable.letTimePass(hours);
		System.out.println("Auction times updated.");
	}

	/**
	 * Delegates which function to run depending on the command entered..
	 * 
	 * <dl>
	 * <dt>Postconditions</dt>
	 * <dd>The command has called the appropriate function.</dd>
	 * </dl>
	 */
	public static void commandManager() {
		printMenu();
		System.out.print("Enter a selection: ");
		String command = in.nextLine().trim();
		System.out.print("\n");
		switch (command.toUpperCase()) {
		case ("D"): {
			importData();
			break;
		}
		case ("A"): {
			createAuction();
			break;
		}
		case ("B"): {
			makeBid();
			break;
		}
		case ("I"): {
			printInfo();
			break;
		}
		case ("P"): {
			printTable();
			break;
		}
		case ("R"): {
			removeExpiredAuctions();
			break;
		}
		case ("T"): {
			letTimePass();
			break;
		}
		default: {
			System.out.println("That command is not valid. Please try again.");
		}
		}
		commandManager();
	}

	public static void main(String[] args) {
		System.out.println("Starting...");
		System.out.println("No previous auction table detected.");
		System.out.println("Creating new table...\n");
		init();
		commandManager();
	}
}
