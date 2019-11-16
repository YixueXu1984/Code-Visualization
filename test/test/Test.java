package test;

public class Test {

    private int counter = 10;

    private void first() {

        System.out.println("First!");
        counter--;

        if(counter != 0) {
            second();
        }
    }

    private void second() {

        System.out.println("Second!");
        counter--;

        if(counter != 0) {
            first();
        }
    }

    public static void main(String[] args) {
        System.out.println("In Main!");
        first();
    }
}
