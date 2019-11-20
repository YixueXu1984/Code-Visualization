package analysis;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Analyzer {

    public static void analyze (String filename){
        String program;
        try {
            program = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
            String injectedProgram = program.replaceAll("((?:(?:public|private|protected|static|final|native|synchronized|abstract|transient)+\\s+)+[$_\\w<>\\[\\]\\s]*\\s+[\\$_\\w]+\\([^\\)]*\\)?\\s*\\{)","$1 \n Logger.log(); \n");
            injectedProgram = injectedProgram.replaceAll("package\\s+([a-zA-Z\\_]+[\\.\\w]*);", "package injected;");
            Writer.writer.println(injectedProgram);
            Writer.closeWriter();
        } catch (IOException e) {
            System.out.println("Didn't find file");
            System.exit(0);
        }
    }


    //Same as above without Log() inserts
    public static void packageLogger(String filename) {
        String program, injectedProgram;
        try {
            program = new String(Files.readAllBytes(Paths.get(filename)), StandardCharsets.UTF_8);
            injectedProgram = program.replaceAll("package\\s+([a-zA-Z\\_]+[\\.\\w]*);", "package injected;");
            Writer.writer.println(injectedProgram);
            Writer.closeWriter();
        } catch (IOException e) {
            System.out.println("Didn't find file");
            System.exit(0);
        }
    }
}
