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
			calculateAndPrintStatistics();
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
	private static void calculateAndPrintStatistics() {
		if (tradeArrayList.isEmpty()) {
			System.out.println("Keine Trades vorhanden, um Statistiken zu berechnen.");
			return;
		}

		int totalTrades = tradeArrayList.size();
		int winTrades = 0;
		int lossTrades = 0;
		double totalProfitLoss = 0.0;
		double totalROE = 0.0;
		double longProfitLoss = 0.0;
		double shortProfitLoss = 0.0;
		int longTrades = 0;
		int shortTrades = 0;

		// Statistiken berechnen
		for (Trade trade : tradeArrayList) {
			if (trade.getWin()) {
				winTrades++;
			} else {
				lossTrades++;
			}

			totalProfitLoss += trade.getPAndL();
			totalROE += trade.getROE();

			if (trade.getIsLong()) {
				longProfitLoss += trade.getPAndL();
				longTrades++;
			} else {
				shortProfitLoss += trade.getPAndL();
				shortTrades++;
			}
		}

		// Durchschnittswerte
		double avgProfitLoss = totalProfitLoss / totalTrades;
		double successRate = (winTrades / (double) totalTrades) * 100;
		double avgROE = totalROE / totalTrades;
		double avgLongProfitLoss = longTrades > 0 ? longProfitLoss / longTrades : 0.0;
		double avgShortProfitLoss = shortTrades > 0 ? shortProfitLoss / shortTrades : 0.0;

		// Ergebnisse ausgeben
		System.out.println("------- Gesamtstatistiken -------");
		System.out.println("Anzahl der Trades: " + totalTrades);
		System.out.println("Gewinn Trades: " + winTrades);
		System.out.println("Verlust Trades: " + lossTrades);
		System.out.printf("Gesamter Gewinn/Verlust: %.2f $%n", totalProfitLoss);
		System.out.printf("Durchschnittlicher Gewinn/Verlust: %.2f $%n", avgProfitLoss);
		System.out.printf("Erfolgsquote: %.2f %% %n", successRate);
		System.out.printf("Durchschnittlicher ROE: %.2f %% %n", avgROE);
		System.out.printf("Durchschnittlicher Gewinn/Verlust (Long): %.2f $%n", avgLongProfitLoss);
		System.out.printf("Durchschnittlicher Gewinn/Verlust (Short): %.2f $%n", avgShortProfitLoss);
		System.out.println("----------------------------------");
	}
}