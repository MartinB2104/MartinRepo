package cryptoTrading;

	import java.util.Scanner;

	public class Tools {
		private static Scanner scan = new Scanner(System.in);

	    public static boolean getBooleanFromUser(String frage) {
	        boolean error = true;
	        boolean answer = false;
	        do {
	            try {
	                System.out.println(frage);
	                answer = scan.nextBoolean();
	                error = false;
	            }
	            catch (Exception e) {
	                //handle input mismatch exception, falsche Eingabe
	                System.out.println("Gebe true oder false ein!");
	                error = true;
	                scan.next();
	            }
	        } while (error);
	        return answer;
	    }

	    public static int getIntegerFromUser(String frage) {
	        boolean error = true;
	        int answer = -1;
	        do {
	            try {
	                System.out.println(frage);
	                answer = scan.nextInt();
	                error = false;
	            }
	            catch (Exception e) {
	                //handle input mismatch exception, falsche Eingabe
	                System.out.println("Gebe eine Zahl ein!");
	                error = true;
	                scan.next();
	            }
	        } while (error);
	        return answer;
	    }
	    
	    public static String getStringFromUser(String frage) {
	        boolean error = true;
	        String answer = "";
	        do {
	            try {
	                System.out.println(frage);
	                answer = scan.next();
	                error = false;
	            }
	            catch (Exception e) {
	                //handle input mismatch exception, falsche Eingabe
	                System.out.println("Geb ein String ein!");
	                error = true;
	                scan.nextLine();
	            }
	        } while (error);
	        return answer;
	    }
	    
	    public static double getDoubleFromUser(String frage) {
	        boolean error = true;
	        double answer = -1.0;
	        do {
	            try {
	                System.out.println(frage);
	                answer = scan.nextDouble();
	                error = false;
	            }
	            catch (Exception e) {
	                //handle input mismatch exception, falsche Eingabe
	                System.out.println("Gebe eine Double ein!");
	                error = true;
	                scan.next();
	            }
	        } while (error);
	        return answer;
	    }
	    
}
