// Circular Graph

public class Test3 {

    private static void first(int selfCall) {

        if(selfCall > 0) {

            System.out.println("First!");
            first(--selfCall);
        } else if (selfCall == 0) {

            second(3);
        } else {

            System.out.println("First!");
        }
    }

    private static void second(int selfCall) {

        if(selfCall > 0) {

            System.out.println("Second!");
            second(--selfCall);
        } else {

            third(2);
        }
    }

    private static void third(int selfCall) {

        if(selfCall > 0) {

            System.out.println("Third!");
            third(--selfCall);
        } else {

            first(-1);
        }
    }

    public static void main(String[] args) {

        System.out.println("In Main!");
        first(4);
    }
}
