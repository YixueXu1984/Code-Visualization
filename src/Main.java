import analysis.Runner;

public class Main {


    public static void main(String[] args) {
        Runner.run();
    }























    /*public static void main(String[] args) {
        Writer.setWriter("output.txt");

        // Analyze and inject logs to obtain dynamic properties
        Analyzer.makeAnalyzer("test/Test1.java");
        Writer.closeWriter();

        System.out.println("Getting Runtime");
        Runtime rt = Runtime.getRuntime();

        // Run injected file
        try {
            String program =
                    "import java.io.BufferedWriter;\n" +
                            "import java.io.FileWriter;\n"+
                            "\n" +
                            "public class Main {\n" +
                            "public static void main(String[] args) {\n" +
                            "   try {\n" +
                            "   BufferedWriter writer = new BufferedWriter(new FileWriter(\"out/injected/out.txt\"));\n" +
                            "   writer.write(\"Hello from Injected\");\n" +
                            "   writer.close();\n" +
                            "   } catch(Exception e) {}\n" +
                            "}\n" +
                            "}";
            BufferedWriter writer = new BufferedWriter(new FileWriter("out/injected/Main.java"));
            writer.write(program);
            writer.close();
        } catch (Exception e) {
            System.out.println("Caught an exception while writing");
        }
        System.out.println("Child program written");
        try {
            Process p = rt.exec("javac out/injected/Main.java");
            p.waitFor();
            p = rt.exec("java -cp out/injected Main");
            p.waitFor();
            System.out.println("Completed: check out/injected/out");
        } catch (Exception e) {
            System.out.println("Caught an exception while trying to run child");
        }
    }*/
}
