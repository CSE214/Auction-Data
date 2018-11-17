package auctionDatabase;

/**
 * Parses the information coming from the big data library for use in the
 * application.
 *
 * @author Sayan Sivakumaran e-mail: sayan.sivakumaran@stonybrook.edu Stony
 *         Brook ID: 110261379
 **/
public class BigDataParser {
	/**
	 * Parses the XML time left into hours.
	 * 
	 * @param timeLeft The time_left field in the XML
	 * @return The number of hours represented by timeLeft
	 */
	public static int getTimeLeft(String timeLeft) {
		String[] words = timeLeft.split(" ");
		if (words.length == 2) {
			if (words[1].contains("day")) {
				return Integer.parseInt(words[0]) * 24;
			} else if (words[1].contains("hour")) {
				return Integer.parseInt(words[0]);
			}
		}
		return Integer.parseInt(words[0]) * 24 + Integer.parseInt(words[2]);
	}

	public static double getCurrentBid(String currentBid) {
		return Double.parseDouble(currentBid.replace("$", "").replaceAll(",", ""));
	}

	/**
	 * Returns item information given various types of sub information.
	 * 
	 * @param memory    Memory of auction item
	 * @param hardDrive Hard drive of auction item
	 * @param cpu       CPU of auction item
	 * @return String summarizing item info
	 */
	public static String getItemInfo(String memory, String hardDrive, String cpu) {
		return cpu + hardDrive + " - " + memory;
	}
}
