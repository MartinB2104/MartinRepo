package cryptoTrading;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class Main {

	public static void main(String[] args) {
		
		Trade trade = new Trade();
		//Trade Attribute:
		trade.setCoinName(Tools.getStringFromUser("Welchen Coin hast du gekauft?"));
		trade.setBuyPrice(Tools.getDoubleFromUser("Bei wieviel eingekauft? (in Dollar)(x,yz)"));
		trade.setMarge(Tools.getDoubleFromUser("Mit wie viel Geld bist du im Trade? (in Dollar)(x,yz)"));
		trade.setIsLong(Tools.getBooleanFromUser("Ist es eine Long Position? (true or false): "));
		trade.setpAndL(Tools.getDoubleFromUser("Wie war der profit and loss? (x,yz)"));
		trade.setROE(Tools.getDoubleFromUser("Wie war der return on invest? In Dezimal bzw. Prozent (x,yz)"));
		trade.setWin(Tools.getBooleanFromUser("War der Trade im Plus? (true or false): "));
	
		System.out.println("----------------------");
		System.out.println("Der Trade von Coin " + trade.getCoinName() + " verlief folgendermaßen: ");
		System.out.println("Du hast bei " + trade.getBuyPrice() + " eingekauft.");
		System.out.println("Du warst mit " + trade.getMarge() + "$ im Trade drin.");
		
		if (trade.getIsLong())
			System.out.println("Es war eine Longposition.");
		else 
			System.out.println("Es war eine Shortposition.");
		
		if (trade.getWin())
			System.out.println("Du hast " + trade.getPAndL() + "$ Profit gemacht!");
		else 
			System.out.println("Du hast " + trade.getPAndL() + "$ minus gemacht.");
		
		System.out.println("Dein return on investment war bei " + trade.getROE() + "%");
		System.out.println("----------------------");
		
		String filePath = "C:\\Users\\marti\\git\\MartinRepo\\CryptoTrading\\src\\cryptoTrading\\trades.json";
		
	}
}
