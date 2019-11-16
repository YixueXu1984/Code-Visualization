public class Main {
    public static void main(String[] args) {
        Writer.setWriter("output.txt");

        // Analyze and inject logs to obtain dynamic properties
        Analyzer.makeAnalyzer("README.md");

        Writer.closeWriter();

    }

}
