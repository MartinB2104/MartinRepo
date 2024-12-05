package golfCard;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;

public class GolfCard {
	private static final String JSON_LOCATION = "C:\\\\Users\\\\marti\\\\git\\\\MartinRepo\\\\GolfCard\\\\src\\\\golfCard\\\\GolfRunde.json";
	private static final Gson GSON = new Gson();
	// List of Bahn objects representing holes
	private static ArrayList<Bahn> scoreCardArrayList = new ArrayList<>();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		// Creating Bahn objects for each hole
		scoreCardArrayList.add(new Bahn("Bahn1", 495, 5));
		scoreCardArrayList.add(new Bahn("Bahn2", 159, 3));
		scoreCardArrayList.add(new Bahn("Bahn3", 363, 4));
		scoreCardArrayList.add(new Bahn("Bahn4", 458, 5));
		scoreCardArrayList.add(new Bahn("Bahn5", 140, 3));
		scoreCardArrayList.add(new Bahn("Bahn6", 455, 5));
		scoreCardArrayList.add(new Bahn("Bahn7", 370, 4));
		scoreCardArrayList.add(new Bahn("Bahn8", 99, 3));
		scoreCardArrayList.add(new Bahn("Bahn9", 360, 4));

		if (Tools.getBooleanFromUser("Möchtest du eine neue Runde starten? (true or false): ")) {
			// Loop through each hole and get user input
			for (Bahn bahn : scoreCardArrayList) {
				displayHoleDetails(bahn);
				prozessFairway(bahn);
				processGreen(bahn);
				processBunkerAndUpDown(bahn);

				Bahn.setPuttCount(Tools.getIntegerFromUser("Wie viele Putts hast du gemacht?"));
				System.out.println("Du hast bisher " + Bahn.getPuttCount() + " Putts gemacht");
				bahn.setErgebnis(Tools.getIntegerFromUser("Was hast du gespielt?"));
				Bahn.setErgebnisToPar(bahn.getPar(), bahn.getErgebnis());
				// Calculate and display result relative to par
				int ergebnisToPar = bahn.getErgebnis() - bahn.getPar();
				if (ergebnisToPar > 0) {
					System.out.println("Du liegst " + ergebnisToPar + " über Par");
				} else if (ergebnisToPar < 0) {
					System.out.println("Du liegst " + ergebnisToPar + " unter Par");
				} else {
					System.out.println("Du liegst even Par!");
				}

				System.out.println("----------------------------------------------------------------");
			}
		}
		//Wenn User will, Runden einsehen:
		if (Tools.getBooleanFromUser("Möchtest du deine Runden Einträge sehen? (true or false): ")) {
			loadScoreCardFromFile();
			for (Bahn bahn : scoreCardArrayList) {
				System.out.println(bahn.toString());
			}
		}
		//Json Datei Runde speichern
		if (Tools.getBooleanFromUser("Möchtest du die Runde speichern? (true or false): ")) {
			saveScoreCardToFile();
		}
		// Summary of the entire course
		int totalPar = 0;
		int totalErgebnis = 0;
		for (Bahn bahn : scoreCardArrayList) {
			totalPar += bahn.getPar();
			totalErgebnis += bahn.getErgebnis();
		}

		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Gesamt Par des Kurses: " + totalPar);
		System.out.println("Gesamtes Ergebnis: " + totalErgebnis);
		int resultToPar = totalErgebnis - totalPar;
		if (resultToPar > 0) {
			System.out.println("Gesamt Ergebnis zu Par: +" + resultToPar);
		} else if (resultToPar < 0) {
			System.out.println("Gesamt Ergebnis zu Par: -" + resultToPar);
		} else {
			System.out.println("Gesamt Ergebnis zu Par: even Par");
		}

		scan.close();
	}

	private static void processBunkerAndUpDown(Bahn bahn) {
		if (!bahn.getGiR()) {

			bahn.setHadUpAndDown(Tools.getBooleanFromUser("Hattest du ein up and down? (true or false): "));

			bahn.setHadBunkerShot(Tools.getBooleanFromUser("War es ein up and down Versuch aus dem Bunker? (true or false): "));
			if (bahn.getHadBunkerShot()) {
				bahn.hadBunkerShot(Tools.getBooleanFromUser("Hast du das Bunker up and down gemacht? (true or false): "));
				System.out.println("Deine Bunker Up and Down Prozentzahl liegt bei: " + Bahn.getBunkerPerc() + "%");
			}

			if (bahn.getHadUpAndDown() & !bahn.getHadBunkerShot()) {
				bahn.hadUpAndDown(Tools.getBooleanFromUser("Hast du das up and down gemacht? (true or false): "));
				System.out.println("Deine Up and Down Prozentzahl liegt bei: " + Bahn.getUpAndDownPerc() + "%");
			}
		}
	}

	private static void processGreen(Bahn bahn) {
		bahn.hadGiR(Tools.getBooleanFromUser("Hast du das Grün in regulation getroffen? (true or false): "));
		System.out.println("Deine Grüntreffer Prozentzahl liegt bei: " + Bahn.getGiRPerc() + "%");
	}

	private static void prozessFairway(Bahn bahn) {
		if (bahn.getPar() != 3) {
			bahn.setFiR(Tools.getBooleanFromUser("Hast du das Fairway getroffen? (true or false): "));
			System.out.println("Deine Fairway Treffer Prozentzahl liegt bei: " + Bahn.getFiRPerc() + "%");
		}
	}

	private static void displayHoleDetails(Bahn bahn) {
		System.out.println("----------------------------------------------------------------");
		System.out.println("Name der Bahn: " + bahn.getName());
		System.out.println("Länge der Bahn: " + bahn.getBahnLaenge());
		System.out.println("Bahn Par ist: " + bahn.getPar());
		System.out.println("Willkommen auf " + bahn.getName());
	}

	private static void saveScoreCardToFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(JSON_LOCATION))) {
			String json = GSON.toJson(scoreCardArrayList);
			writer.write(json);
			System.out.println("Daten gespeichert:\n" + json);
		}catch(IOException e){
			System.err.println("Fehler beim Speichern der Datei: " + e.getMessage());
		}
	}
	private static void loadScoreCardFromFile() {
		File file = new File(JSON_LOCATION);

		if(!file.exists()){
			try {
				if (file.createNewFile()){
					System.out.println("Daten gespeichert:\n" + JSON_LOCATION);
				}
			} catch (IOException e) {
				throw new RuntimeException("Fehler beim Speichern der Datei: " + e.getMessage());
			}
		}

		try (JsonReader reader = new JsonReader(new FileReader(file))){
			Bahn[] bahnArray = GSON.fromJson(reader, Bahn[].class);
			if (bahnArray !=null) {
				scoreCardArrayList = new ArrayList<>(Arrays.asList(bahnArray));
			}
		} catch (FileNotFoundException e){
			System.err.println("Fehler beim Speichern der Datei: " + e.getMessage());
		} catch (JsonSyntaxException e){
			System.err.println("Fehler beim Lesen der Json Datei: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
		}
	}
}