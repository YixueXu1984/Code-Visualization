/*

You have to improve this poorly implemented code to be more concise in terms of function calls and more time efficient

*/

import java.util.Random;
import java.lang.Thread;

public class Problem {

    private static boolean CheckIfOdd(int number) {

        return odd(number);
    }

    private static boolean odd(int number) {

        if(number == 0) {

            return false;
        } else {

            return even(number-1);
        }
    }

    private static boolean even(int number) {

        if(number == 0) {

            return true;
        } else {

            try {

                Thread.sleep(100 * number);
            } catch (Exception e) {

                System.out.println(e.getMessage());
            }

            return odd(number-1);
        }
    }

    public static void main(String[] args) {

        Random rand = new Random();

        int number = rand.nextInt(20) + 1;

        System.out.println("Is " + number + " odd or even?");

        if(CheckIfOdd(number)) {

            System.out.println(number + " is odd!");
        }

        if(!CheckIfOdd(number)) {

            System.out.println(number + " is even!");
        }
    }
}
