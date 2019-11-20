// Branched Tree With Loop Back

class Test4 {

    private static void first(int selfCall) {

        if(selfCall > 0) {

            System.out.println("First!");
            first(--selfCall);
        } else if (selfCall == 0) {

            second(3);
            third(3);
        } else {

            System.out.println("First!");
        }
    }

    private static void second(int selfCall) {

        if(selfCall > 0) {

            System.out.println("Second!");
            second(--selfCall);
        } else {

            fourth(2);
            fifth(2);
        }
    }

    private static void third(int selfCall) {

        if(selfCall > 0) {

            System.out.println("Third!");
            third(--selfCall);
        } else {

            sixth(2);
            seventh(2);
        }
    }

    private static void fourth(int selfCall) {

        if(selfCall > 0) {

            System.out.println("Fourth!");
            fourth(--selfCall);
        } else {

            first(-1);
        }
    }

    private static void fifth(int selfCall) {

        if(selfCall > 0) {

            System.out.println("Fifth!");
            fifth(--selfCall);
        } else {

            first(-1);
        }
    }

    private static void sixth(int selfCall) {

        if(selfCall > 0) {

            System.out.println("Sixth!");
            sixth(--selfCall);
        } else {

            first(-1);
        }
    }

    private static void seventh(int selfCall) {

        if(selfCall > 0) {

            System.out.println("Seventh!");
            seventh(--selfCall);
        } else {

            first(-1);
        }
    }

    public static void main(String[] args) {

        System.out.println("In Main!");
        first(4);
    }
}
