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
		
		for (Bahn bahn : scoreCardArrayList) {
			System.out.println(bahn.toString());
		}
		
		for(int i = 0; i < 9; i++) {

			System.out.println("name der Bahn: " + scoreCardArrayList.get(i).getName());
			System.out.println("Länge der Bahn: " + scoreCardArrayList.get(i).getBahnLaenge());
			System.out.println("bahn.par: " + scoreCardArrayList.get(i).getPar());
			System.out.println("Willkommen auf Bahn " + (i + 1) + " --------------------");
			
			if (scoreCardArrayList.get(i).getPar() != 3) {
				System.out.println("Hast du das Fairway getroffen? (true or false): ");
				scoreCardArrayList.get(i).setFiR(scan.nextBoolean());
				System.out.println("Deine Fairwaytreffer Prozentzahl liegt bei: " + Bahn.getFiRPerc() + "%");
			}
			
			System.out.println("Hast du das Grün in regulation getroffen? (true or false): ");
			scoreCardArrayList.get(i).hadGiR(scan.nextBoolean());
			System.out.println("Deine Grüntreffer Prozentzahl liegt bei: " + Bahn.getGiRPerc() + "%");
			
			if (!scoreCardArrayList.get(i).getGiR()) {
				System.out.println("Hattest du ein up and down? (true or false): ");
				scoreCardArrayList.get(i).setHadUpAndDown(scan.nextBoolean());;
				
				if (scoreCardArrayList.get(i).getHadUpAndDown()) {
					System.out.println("Hast du das up and down gemacht? (true or false): ");
					scoreCardArrayList.get(i).hadUpAndDown(scan.nextBoolean());
					System.out.println("Deine Up and Down Prozentzahl liegt bei: " + Bahn.getUpAndDownPerc() + "%");
				}
			}
			
			System.out.println("Was hast du gespielt?");
			scoreCardArrayList.get(i).setErgebnis(scan.nextInt());
			Bahn.setErgebnisToPar(scoreCardArrayList.get(i).getPar(), scoreCardArrayList.get(i).getErgebnis());
			
			if (scoreCardArrayList.get(i).getErgebnisToPar() > 0)
				System.out.println("Du liegst " + scoreCardArrayList.get(i).getErgebnisToPar() + " über Par");
			else if (scoreCardArrayList.get(i).getErgebnisToPar() < 0)
				System.out.println("Du liegst " + scoreCardArrayList.get(i).getErgebnisToPar() + " unter Par");
			else if (scoreCardArrayList.get(i).getErgebnisToPar() == 0)
				System.out.println("Du liegst even Par!");
			System.out.println("paruntilnow: " + Bahn.getParUntilNow());
			System.out.println("getErgebnisUntilNow: " + Bahn.getErgebnisUntilNow());
			
			if (i == 3) {
				
				System.out.println("-----------------------------");
				System.out.println("auf bahn 3 GiR: " + bahn4.getGiR());
				System.out.println("hoffentlich das gleiche GiR:" + scoreCardArrayList.get(i).getGiR());
				System.out.println("par until now:" + Bahn.getParUntilNow());
				System.out.println("ErgebnisUntilNow: " + Bahn.getErgebnisUntilNow());
				System.out.println("-----------------------------");
			}
		}
		scan.close();
	}

}