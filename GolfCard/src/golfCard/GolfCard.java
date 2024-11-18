package golfCard;

import java.util.ArrayList;
import java.util.Scanner;

public class GolfCard {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Bahn bahn1 = new Bahn("bahn1", 495, 5);
		Bahn bahn2 = new Bahn("bahn2", 159, 3);
		Bahn bahn3 = new Bahn("bahn3", 363, 4);
		Bahn bahn4 = new Bahn("bahn4", 458, 5);
		Bahn bahn5 = new Bahn("bahn5", 140, 3);
		Bahn bahn6 = new Bahn("bahn6", 455, 5);
		Bahn bahn7 = new Bahn("bahn7", 370, 4);
		Bahn bahn8 = new Bahn("bahn8",  99, 3);
		Bahn bahn9 = new Bahn("bahn9", 360, 4);
		Bahn bahnGesamt = new Bahn("gesamt" , 2899, 36);
		ArrayList<Bahn> scoreCardArrayList = new ArrayList<Bahn>();
		scoreCardArrayList.add(bahn1);
		scoreCardArrayList.add(bahn2);
		scoreCardArrayList.add(bahn3);
		scoreCardArrayList.add(bahn4);
		scoreCardArrayList.add(bahn5);
		scoreCardArrayList.add(bahn6);
		scoreCardArrayList.add(bahn7);
		scoreCardArrayList.add(bahn8);
		scoreCardArrayList.add(bahn9);
		scoreCardArrayList.add(bahnGesamt);
		
		for (Bahn bahn : scoreCardArrayList) {
			System.out.println(bahn.toString());
		}
		
		for (int i = 0; i < 9; i++) {

			System.out.println("name der Bahn: " + scoreCardArrayList.get(i).getName());
			System.out.println("Länge der Bahn: " + scoreCardArrayList.get(i).getBahnLaenge());
			System.out.println("bahn.par: " + scoreCardArrayList.get(i).getPar());
			System.out.println("Willkommen auf Bahn " + (i + 1) + " --------------------");
			System.out.println("");
			
			if (scoreCardArrayList.get(i).getPar() != 3) {
				scoreCardArrayList.get(i).setFiR(Tools.getBooleanFromUser("Hast du das Fairway getroffen? (true or false): "));
				System.out.println("Deine Fairwaytreffer Prozentzahl liegt bei: " + Bahn.getFiRPerc() + "%");
			}
			
		
			scoreCardArrayList.get(i).hadGiR(Tools.getBooleanFromUser("Hast du das Grün in regulation getroffen? (true or false): "));
			System.out.println("Deine Grüntreffer Prozentzahl liegt bei: " + Bahn.getGiRPerc() + "%");
			
			if (!scoreCardArrayList.get(i).getGiR()) {
			
				scoreCardArrayList.get(i).setHadUpAndDown(Tools.getBooleanFromUser("Hattest du ein up and down? (true or false): "));
			
				if (scoreCardArrayList.get(i).getHadUpAndDown()) {
						System.out.println("Hast du das up and down gemacht? (true or false): ");
						scoreCardArrayList.get(i).hadUpAndDown(Tools.getBooleanFromUser("Hast du das up and down gemacht? (true or false): "));
						System.out.println("Deine Up and Down Prozentzahl liegt bei: " + Bahn.getUpAndDownPerc() + "%");
				}
			}
			
			Bahn.setPuttCount(Tools.getIntegerFromUser("Wie viele Putts hast du gemacht?"));
			System.out.println("Du hast bisher " + Bahn.getPuttCount() + " Putts gemacht");
			scoreCardArrayList.get(i).setErgebnis(Tools.getIntegerFromUser("Was hast du gespielt?"));
			Bahn.setErgebnisToPar(scoreCardArrayList.get(i).getPar(), scoreCardArrayList.get(i).getErgebnis());
			
			if (scoreCardArrayList.get(i).getErgebnisToPar() > 0)
				System.out.println("Du liegst " + scoreCardArrayList.get(i).getErgebnisToPar() + " über Par");
			else if (scoreCardArrayList.get(i).getErgebnisToPar() < 0)
				System.out.println("Du liegst " + scoreCardArrayList.get(i).getErgebnisToPar() + " unter Par");
			else if (scoreCardArrayList.get(i).getErgebnisToPar() == 0)
				System.out.println("Du liegst even Par!");
			
			System.out.println("paruntilnow: " + Bahn.getParUntilNow());
			System.out.println("getErgebnisUntilNow: " + Bahn.getErgebnisUntilNow());
			System.out.println("----------------------------------------------------------------");
		}
		
		//Gesamt:
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Der C Kurs hat eine Länge von " + scoreCardArrayList.get(9).getBahnLaenge() 
				+ " und hat ein Par von " + scoreCardArrayList.get(9).getPar() + " Schlägen.");
		System.out.println("Deine Fairwaytrefferquote liegt bei " + Bahn.getFiRPerc() + "%.");
		System.out.println("Deine Grüntrefferquote liegt bei " + Bahn.getGiRPerc() + "%.");
		System.out.println("Deine Anzahl an Putts liegt bei " + Bahn.getPuttCount() + ".");
		System.out.println("Dein Ergebnis zu Par ist " + (Bahn.getErgebnisUntilNow() - bahnGesamt.getPar()));
		
		scan.close();
	}

}