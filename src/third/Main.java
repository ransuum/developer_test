package third;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) {
        String number;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            number = br.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BigInteger fact = BigInteger.ONE;

        for (int i = 1; i <= Integer.parseInt(number); i++) {
            fact = fact.multiply(BigInteger.valueOf(i));  // multiplication
        }

        int sum = 0;

        String allDigits = fact.toString();  // multiplication to string for using Character

        for (int i = 0; i < allDigits.length(); i++) {
            sum += Character.getNumericValue(allDigits.charAt(i));  // get digits and + them
        }

        System.out.println(sum);
    }
}
