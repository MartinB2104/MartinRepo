package golfCard;

import java.util.Scanner;

public class Tools {
	private static Scanner scan = new Scanner(System.in);

    public static boolean getBooleanFromUser(String frage) {
        Boolean error = true;
        Boolean answer = false;
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

    public static Integer getIntegerFromUser(String frage) {
        Boolean error = true;
        Integer answer = -1;
        do {
            try {
                System.out.println(frage);
                answer = scan.nextInt();
                error = false;
            }
            catch (Exception e) {
                //handle input mismatch exception, falsche Eingabe
                System.out.println("Geb ne Zahl ein du kek");
                error = true;
                scan.next();
            }
        } while (error);
        return answer;
    }
	
}