import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Analyzer {

    private static String program;
    private static Analyzer theAnalyzer;

    private Analyzer(String filename){
        try {
            program = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Didn't find file");
            System.exit(0);
        }
        analyze();
    }

    private void analyze (){
        System.out.println("Analyzing");
        Writer.writer.println("TEST");
    }

    public static void makeAnalyzer(String filename){
        if (theAnalyzer==null){
            theAnalyzer = new Analyzer(filename);
        }
    }
}
