package golfCard;

public class Bahn{
	//Der Name ist die Bahn + i (1-9) 
	private String name;
	private int bahnLaenge;
	// 3,4 oder 5 ist die Vorgabe vom Platz
	private int par;
	private int ergebnis;
	//Ein Up and Down ist, wenn man am Grün ist und probiert mit 1 Schlag aufs und mit 1 Schlag ins Loch zu kommen
	private boolean hadUpAndDown = false;
	//Das Up and Down gemacht?
	private boolean upAndDownMade = false;
	//War das Up and Down ein Bunkerschlag?
	private boolean hadBunkerShot = false;
	private boolean bunkerShotMade = false;

	//Wie man zur Vorgabe vom Platz steht
	private static int ergebnisToPar;
	//Anzahl an Schlägen bis jetzt
	private static int ergebnisUntilNow;
	//Gesamt Platzvorgabe bis jetzt
	private static int parUntilNow;

	//Anzahl an Putts auf dem Grün
	private static int puttCount;
	//Anzahl möglicher Fairwaytreffer
	private static int FiRMoeglichCount = 0;
	//Anzahl Fairwaytreffer
    private static int FiRCount = 0;
    //Anzahl mögliche Grün in Regulation treffer
    private static int GiRMoeglichCount = 0;
    //Anzahl gemachter Green in Regulation
    private static int GiRMadeCount = 0;
    //Anzahl möglicher Up And Down's 
    private static int upAndDownMoeglichCount = 0;
    //Anzahl gemachter Up and Down's
    private static int upAndDownMadeCount = 0;
    //Anzahl möglicher Bunkerschläge up and down's 
    private static int bunkerShotMoeglichCount = 0;
    //Anzahl gemachter Bunker Up and Down's
    private static int bunkerShotMadeCount = 0;
    //Fairway in Regulation, also ob man den Abschlag aufs Fairway geschlagen hat
    private boolean FiR;
    //Fairway Treffer Prozentzahl
    private static double FiRPerc = (FiRCount * 100.0) / (FiRMoeglichCount * 1.0);
    //Green in Regulation: auf Par 3 mit dem 1. Schlag auf Par 4 mit 2 und auf par 5 Bahnen mit dem 3. Schlag
    private boolean GiR = false;
    // Green in Regulation Prozentzahl
    private static double GiRPerc = (GiRMadeCount * 100.0) / (GiRMoeglichCount * 1.0);
    //Up and Down Prozentzahl
    private static double upAndDownPerc = (upAndDownMadeCount * 1.0) / (upAndDownMoeglichCount * 1.0);
    //Bunker up and down Prozentzahl
    private static double bunkerPerc = (bunkerShotMadeCount * 1.0) / (bunkerShotMoeglichCount * 1.0);
    
    public static double getBunkerPerc() {
		return bunkerPerc;
	}

	public static void setBunkerPerc() {
		bunkerPerc = (bunkerShotMadeCount * 1.0) / (bunkerShotMoeglichCount * 1.0);
	}

	public void setHadBunkerShot(boolean hadBunkerShot) {
		this.hadBunkerShot = hadBunkerShot ;
	}
	
	public boolean getHadBunkerShot() {
		return hadBunkerShot;
	}
	
	public void hadBunkerShot(boolean bunkerShotMade){
		this.bunkerShotMade = bunkerShotMade;
		if(bunkerShotMade) {
			bunkerShotMoeglichCount++;
			bunkerShotMadeCount++;
			Bahn.setBunkerPerc();
		}else {
			bunkerShotMoeglichCount++;
			Bahn.setBunkerPerc();
		}
	}
	
	public static int getPuttCount() {
    	return puttCount;
    }
    
    public static void setPuttCount(int putts) {
    	puttCount += putts;
    }
    
    public void setPar(int par) {
    	this.par = par;
	}
    
    public int getPar() {
    	return par;
    }
    public void setBahnLaenge(int bahnLaenge) {
    	this.bahnLaenge = bahnLaenge;
    }
    public int getBahnLaenge() {
    	return bahnLaenge;
    }
    
    public static int getParUntilNow() {
    	return parUntilNow;
    }
    
    public int getErgebnisToPar() {
    	return ergebnisToPar;
    }

    public static int getErgebnisUntilNow() {
    	return ergebnisUntilNow;
    }
    
    public static void setErgebnisToPar(int bahnPar, int ergebnis) {
    	parUntilNow += bahnPar;
    	ergebnisUntilNow += ergebnis;
    	ergebnisToPar += ergebnis - bahnPar;
    }
    public boolean getUpAndDownMade() {
		return upAndDownMade;
	}
	public void setErgebnis(int ergebnis) {
		this.ergebnis = ergebnis;
	}
	public int getErgebnis() {
		return ergebnis;
	}
	
	public String getName() {
		return name;
	}

    public boolean getFiR() {
    	return FiR;
    }

	public void setFiR(boolean FiR) {
		this.FiR = FiR;
		if (FiR) {
			FiRMoeglichCount++;
			FiRCount++;
		} else {
			FiRMoeglichCount++;
		}
		updateFiRPerc();
	}
    
    public static double getFiRPerc() {
    	return FiRPerc;
    }

	private void updateFiRPerc() {
		if (FiRMoeglichCount > 0) {
			FiRPerc = (FiRCount * 100.0) / FiRMoeglichCount;
		}
	}
    
    public boolean getGiR() {
    	return GiR;
    }
    public static double getUpAndDownPerc() {
    	return upAndDownPerc;
    }
    public static void setUpAndDownPerc() {
    	upAndDownPerc = (upAndDownMadeCount * 100.0) / (upAndDownMoeglichCount * 1.0);
    }
    
    public static double getGiRPerc() {
    	return GiRPerc;
    }
    public static void setGiRPerc() {
    	GiRPerc = (GiRMadeCount * 100.0) / (GiRMoeglichCount * 1.0);
    }
    
	public Bahn(String name, int bahnLaenge, int par) {
		this.name= name;
		this.bahnLaenge = bahnLaenge;
		this.par = par;
	}
	
	public Bahn(String name, int bahnLaenge, int par, int ergebnis) {
		this.name= name;
		this.bahnLaenge = bahnLaenge;
		this.par = par;
		this.ergebnis = ergebnis;
	}
	
	public void hadGiR(boolean GiR) {
		this.GiR = GiR;
		if(GiR) {
			GiRMoeglichCount++;
			GiRMadeCount++;
			Bahn.setGiRPerc();
		}else {
			GiRMoeglichCount++;
			Bahn.setGiRPerc();
		}
	}

	private void updateGiRPerc() {
		if (GiRMoeglichCount > 0) {
			GiRPerc = (GiRMadeCount * 100.0) / GiRMoeglichCount;
		}
	}

	public void setHadUpAndDown(boolean hadUpAndDown) {
		this.hadUpAndDown = hadUpAndDown ;
	}
	
	public boolean getHadUpAndDown() {
		return hadUpAndDown;
	}
	
	public void hadUpAndDown(boolean upAndDownMade){
		this.upAndDownMade = upAndDownMade;
		if(upAndDownMade) {
			upAndDownMoeglichCount++;
			upAndDownMadeCount++;
			Bahn.setUpAndDownPerc();
		}else {
			upAndDownMoeglichCount++;
			Bahn.setUpAndDownPerc();
		}
	}

	public void setGiR(boolean GiR) {
		this.GiR = GiR;
		if (GiR) {
			GiRMoeglichCount++;
			GiRMadeCount++;
		} else {
			GiRMoeglichCount++;
		}
		updateGiRPerc();
	}

	public String toString() {
		return name + ", Bahnlänge: " + bahnLaenge + ", Par: " + par + ", Ergebnis: " + ergebnis + ", hatte Up and Down?: " + hadUpAndDown + ", Grüntreffer?: " + GiR
				+ ", Fairwaytreffer?: " + FiR + ", Bunkerschlag?: " + hadBunkerShot + ", Bunkerschlag gemacht?: " + bunkerShotMade;
	}
}
