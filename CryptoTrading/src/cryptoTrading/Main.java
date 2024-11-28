package cryptoTrading;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
	private static final String jsonLocation = "C:\\Users\\marti\\git\\MartinRepo\\CryptoTrading\\src\\cryptoTrading\\trades.json";
	private static final Gson gson = new Gson();

	public static void main(String[] args) throws FileNotFoundException {
		//Wenns noch kein File gibt, File erstellen

		File file = new File(jsonLocation);
		JsonReader reader = new JsonReader(new FileReader(file));
		Trade[] trades = gson.fromJson(reader, Trade[].class);
		List<Trade> tradeArrayList = new ArrayList<>(Arrays.asList(trades));
		Trade trade = new Trade();
		boolean addTrade = Tools.getBooleanFromUser("Willst du ein Trade hinzufügen? (true or false): ");

		//Trade Attribute:
		if(addTrade) {
			trade.setCoinName(Tools.getStringFromUser("Welchen Coin hast du gekauft?"));
			trade.setBuyPrice(Tools.getDoubleFromUser("Bei wie viel eingekauft? (in Dollar)(x,yz)"));
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
				System.out.println("Es war eine Long Position.");
			else
				System.out.println("Es war eine Short Position.");

			if (trade.getWin())
				System.out.println("Du hast " + trade.getPAndL() + "$ Profit gemacht!");
			else
				System.out.println("Du hast " + trade.getPAndL() + "$ minus gemacht.");

			System.out.println("Dein return on investment war bei " + trade.getROE() + "%");
			System.out.println("----------------------");
		}

		tradeArrayList.add(trade);

		try {
			String json = gson.toJson(tradeArrayList);
			BufferedWriter writer = new BufferedWriter(new FileWriter(jsonLocation));
			writer.write(json);
			System.out.println(json);
			System.out.println("json file written");
			writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		boolean wantsToSeeTrades = Tools.getBooleanFromUser("Möchtest du deine Trades einsehen? (true or false): ");
		//alle daten aus der json in tradesArrayList
		//alle daten printen
		if(wantsToSeeTrades)
			tradeArrayList.forEach(trade1 -> System.out.println(trade1.toString()));
		boolean wantsToSeeStatistics = Tools.getBooleanFromUser("Möchtest du deine Statistiken sehen? (true or false): ");
		if(wantsToSeeStatistics){
		//daten aus tradesArrayList auswerten und printen
			System.out.println();
		}
	}
}
