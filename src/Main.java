import analysis.Analyzer;
import analysis.Writer;

import java.io.FileWriter;
import java.lang.Runtime;
import java.io.BufferedWriter;

public class Main {

    public static void main(String[] args) {
        Writer.setWriter("injected/output_injected.java");

        // Analyze and inject logs to obtain dynamic properties
        Analyzer.makeAnalyzer("test/Test1.java");
        Writer.closeWriter();

        System.out.println("Getting Runtime");
        Runtime rt = Runtime.getRuntime();

        // Run injected file
        try {
            Process p = rt.exec("javac injected/output_injected.java injected/Logger.java");
            p.waitFor();
            p = rt.exec("java -cp injected Test1");
            p.waitFor();
            System.out.println("Completed: check injected/output");
        } catch (Exception e) {
            System.out.println("Caught an exception while trying to run child");
        }
    }
}
