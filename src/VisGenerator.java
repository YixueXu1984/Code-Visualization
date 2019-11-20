

public class VisGenerator {


    private static void generatesGraph(String filename) {

        Process p;
        Runtime rt = Runtime.getRuntime();

        try{
            p = rt.exec("dot  -Tpng " + filename + " -o " + filename + ".png");
            p.waitFor();
            System.out.println("Complete generating graph.");
        } catch (Exception e) {
            System.out.println("Error: failed when generating the graph.");
        }
    }

}