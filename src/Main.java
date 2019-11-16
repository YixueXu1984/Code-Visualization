import java.io.FileWriter;
import java.lang.Runtime;
import java.io.BufferedWriter;

public class Main {

    public static void main(String[] args) {
        Writer.setWriter("output.txt");

        // Analyze and inject logs to obtain dynamic properties
        Analyzer.makeAnalyzer("README.md");

        Writer.closeWriter();

        // TODO: Refactor (or use existing writer class)

        System.out.println("Getting Runtime");
        Runtime rt = Runtime.getRuntime();
        System.out.println("Writing Child program");
        try {
            String program =
                    "public class Main {\n" +
                            "public static void main(String[] args) {\n" +
                            "   System.out.println(\"Hello from Injected\");" +
                            "}" +
                            "}";
            BufferedWriter writer = new BufferedWriter(new FileWriter("out/injected/Main"));
            writer.write(program);
            writer.close();
        } catch (Exception e) {
            System.out.println("Caught an exception while writing");
        }
        System.out.println("Child program written");
        try {
            Process p = rt.exec("java out/injected/Main.java");
            p.waitFor();
            System.out.println("Completed");
        } catch (Exception e) {
            System.out.println("Caught an exception while trying to run child");
        }
    }
}
