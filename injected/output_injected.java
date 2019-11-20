// Linear Tree

class Test1 {

    private static void first(int selfCall) { 
 Logger.log(); 


        if(selfCall > 0) {

            System.out.println("First!");
            first(--selfCall);
        } else {

            second(3);
        }
    }

    private static void second(int selfCall) { 
 Logger.log(); 


        if(selfCall > 0) {

            System.out.println("Second!");
            second(--selfCall);
        } else {

            third(2);
        }
    }

    private static void third(int selfCall) { 
 Logger.log(); 


        if(selfCall > 0) {

            System.out.println("Third!");
            third(--selfCall);
        } else {

            fourth(1);
        }
    }

    private static void fourth(int selfCall) { 
 Logger.log(); 


        if(selfCall > 0) {

            System.out.println("Fourth!");
            fourth(--selfCall);
        }
    }

    public static void main(String[] args) { 
 Logger.log(); 


        System.out.println("In Main!");
        first(4);
    }
}

