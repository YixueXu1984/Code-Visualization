// Mutual Recursion
class Test5 {

    private static void first(int selfCall) {

        if(selfCall == 0) {
            return;
        }

        System.out.println("First!");
        second(--selfCall);
    }

    private static void second(int selfCall) {

        if(selfCall == 0) {
            return;
        }

        System.out.println("Second!");
        first(--selfCall);
    }

    public static void main(String[] args) {

        System.out.println("In Main!");
        first(20);
    }
}
