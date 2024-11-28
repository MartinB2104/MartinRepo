package cryptoTrading;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	private static final String JSON_LOCATION = "C:\\Users\\marti\\git\\MartinRepo\\CryptoTrading\\src\\cryptoTrading\\trades.json";
	private static final Gson GSON = new Gson();
	private static List<Trade> tradeArrayList = new ArrayList<>();

	public static void main(String[] args) {
		loadTradesFromFile();

		boolean addTrade = Tools.getBooleanFromUser("Willst du einen Trade hinzufügen? (true or false): ");
		if (addTrade) {
			Trade trade = createNewTrade();
			tradeArrayList.add(trade);
			saveTradesToFile();
		}

		boolean wantsToSeeTrades = Tools.getBooleanFromUser("Möchtest du deine Trades einsehen? (true or false): ");
		if (wantsToSeeTrades) {
			tradeArrayList.forEach(System.out::println);
		}

		boolean wantsToSeeStatistics = Tools.getBooleanFromUser("Möchtest du deine Statistiken sehen? (true or false): ");
		if (wantsToSeeStatistics) {
			// Hier könnte Statistik-Logik implementiert werden
			System.out.println("Statistiken sind noch nicht implementiert.");
		}
	}

	private static void loadTradesFromFile() {
		File file = new File(JSON_LOCATION);

		if (!file.exists()) {
			try {
				if (file.createNewFile()) {
					System.out.println("Neue Datei erstellt: " + JSON_LOCATION);
				}
			} catch (IOException e) {
				throw new RuntimeException("Fehler beim Erstellen der Datei", e);
			}
		}

		try (JsonReader reader = new JsonReader(new FileReader(file))) {
			Trade[] trades = GSON.fromJson(reader, Trade[].class);
			if (trades != null) {
				tradeArrayList = new ArrayList<>(Arrays.asList(trades));
			}
		} catch (FileNotFoundException e) {
			System.err.println("Datei nicht gefunden: " + e.getMessage());
		} catch (JsonSyntaxException e) {
			System.err.println("Fehler beim Lesen der JSON-Datei: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
		}
	}

	private static void saveTradesToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(JSON_LOCATION))) {
			String json = GSON.toJson(tradeArrayList);
			writer.write(json);
			System.out.println("Daten gespeichert:\n" + json);
		} catch (IOException e) {
			System.err.println("Fehler beim Speichern der Datei: " + e.getMessage());
		}
	}

	private static Trade createNewTrade() {
		Trade trade = new Trade();
		trade.setCoinName(Tools.getStringFromUser("Welchen Coin hast du gekauft?"));
		trade.setBuyPrice(Tools.getDoubleFromUser("Bei wie viel eingekauft? (in Dollar, z.B. 1.23): "));
		trade.setMarge(Tools.getDoubleFromUser("Mit wie viel Geld bist du im Trade? (in Dollar, z.B. 1.23): "));
		trade.setIsLong(Tools.getBooleanFromUser("Ist es eine Long-Position? (true or false): "));
		trade.setpAndL(Tools.getDoubleFromUser("Wie war der Profit and Loss? (z.B. 1.23): "));
		trade.setROE(Tools.getDoubleFromUser("Wie war der Return on Investment? (z.B. 0.12 für 12%): "));
		trade.setWin(Tools.getBooleanFromUser("War der Trade im Plus? (true oder false): "));

		System.out.println("\nZusammenfassung des Trades:");
		System.out.println(trade);

		return trade;
	}
}