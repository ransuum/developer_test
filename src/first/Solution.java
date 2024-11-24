package first;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class Solution {

    public void read() {
        int N;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(resolve(checkForNegative(N)));
    }

    private List<String> resolve(int N) {
        List<String> result = new LinkedList<>();
        brackets(N, N, "", result);
        System.out.println("Count of variants: " + result.size());
        return result;
    }

    private int checkForNegative(int N) {
        if (N < 0) {
            throw new IllegalArgumentException("Negative numbers are not allowed");
        } else {
            return N;
        }
    }

    /**
     * At each step we have a choice:
     * Add an opening bracket ( if openBracket > 0
     * Add closing bracket ) if closeBracket > openBracket
     **/
    private void brackets(int openBracket, int closeBracket, String currentBrackets, List<String> result) {

        if (openBracket == 0 && closeBracket == 0) {  //All brackets are used
            result.add(currentBrackets);  //The currentBrackets string is added to the result
            return;
        }

        if (openBracket > 0) {  // If we add (: reduce openBracket by 1
            // The current brackets string is accumulated in the @currentBrackets parameter
            brackets(openBracket - 1, closeBracket, currentBrackets + "(", result);
        }

        // You cannot add ')' if there are not enough '('
        // At any time, the number of '(' >= the number of ')'
        if (closeBracket > openBracket) {
            // If we add ')': reduce closeBracket by 1
            // The current brackets string is accumulated in the @currentBrackets parameter
            brackets(openBracket, closeBracket - 1, currentBrackets + ")", result);
        }
    }

    /*
      N = 2

      Call brackets(openBracket=2, closeBracket=2, currentBrackets='')
      → Adding opening bracket
        Call brackets(openBracket=1, closeBracket=2, currentBrackets='(')
         → Adding opening bracket
         Call brackets(openBracket=0, closeBracket=2, currentBrackets='((')
           → Adding closing bracket
           Call brackets(openBracket=0, closeBracket=1, currentBrackets='(()')
            → Adding closing bracket
            Call brackets(openBracket=0, closeBracket=0, currentBrackets='(())')
              → Adding result: (())

      → Adding closing bracket
      Call brackets(openBracket=1, closeBracket=1, currentBrackets='()')
        → Adding opening bracket
        Call brackets(openBracket=0, closeBracket=1, currentBrackets='()(')
          → Adding closing bracket
          Call brackets(openBracket=0, closeBracket=0, currentBrackets='()()')
            → Adding result: ()()

      result: [(()) , ()()]
     */

}
