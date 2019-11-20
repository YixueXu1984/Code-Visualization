package analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Runtime;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

import static java.nio.file.FileVisitResult.CONTINUE;
import static java.nio.file.FileVisitResult.TERMINATE;


//TODO logger import stms / pakcage decl

/*
This class takes input files, calls the injection methods, then runs the injected code
Setup:
place all relevant project code into cpsc410-viz/in and
the runtime entry point path into cpsc410-viz/in/mainFile.txt
 */
public class Runner {

    private static String compileStr;

    public static void run() {
        //inject everything
        inject();
        copyLogger();
        //compile and run injected code
        File file;
        Process p;
        String mainPath = "";
        Runner.compileStr = "";
        Runtime rt = Runtime.getRuntime();
        //find out main path from mainFile.txt
        try {
            BufferedReader r = new BufferedReader(new FileReader("in/mainFile.txt"));
            mainPath = r.readLine();
            mainPath = mainPath.substring(0, mainPath.length() - 5).replace("/",".");
            r.close();
        } catch (IOException e) {
            System.out.println("Failed to read main file path");
            System.exit(0);
        }
        try {
            //compile all files
            file = new File("inject");
            Files.walkFileTree(file.toPath(), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Runner.addFileToCompile(file.toString());
                    return CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return TERMINATE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return CONTINUE;
                }
            });
            System.out.println("javac" + compileStr);
            p = rt.exec("javac" + compileStr);
            p.waitFor();
            //run main file
            System.out.println("java -cp inject " + mainPath);
            p = rt.exec("java -cp inject " + mainPath);
            p.waitFor();
        } catch (Exception e) {
            System.out.println("Failed to compile or run one or more injected files");
            cleanInjected();
            System.exit(0);
        }
    }

    //Note:The inject method call should put the injected file into the proper directory.
    private static void inject() {
        File file;
        try {
            file = new File("in");
            Files.walkFileTree(file.toPath(), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    File myFile;
                    if(dir.toString().equals("in"))
                        myFile = new File("inject");
                    else
                        myFile = new File("inject/" + dir.toString().substring(3));
                    myFile.mkdirs();
                    return CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    File myFile;
                    String name = file.getFileName().toString();
                    if(name.substring(name.lastIndexOf(".")).equals(".java")) {
                        name = "inject/" + file.toString().substring(3);
                        myFile = new File( name);
                        myFile.createNewFile();
                        System.out.println("To " + name + " from " + file.toString());
                        Writer.setWriter(name);
                        Analyzer.analyze(file.toString());
                    }
                    return CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return TERMINATE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    return CONTINUE;
                }
            });
        } catch (IOException e) {
            cleanInjected();
        }
    }

    private static void copyLogger() {
        try {
            Writer.setWriter("inject/Logger.java");
            Analyzer.packageLogger("src/viz/Logger.java");
            addFileToCompile("inject/Logger.java");
        } catch (Exception e) {
            System.out.println("Failed to copy over Logger.java");
            System.exit(0);
        }
    }

    private static void cleanInjected() {
        try {
            File file = new File("injected");
            System.out.println("Failed to create injected files");
            Files.walkFileTree(file.toPath(), new FileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    return CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.deleteIfExists(file);
                    return CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.deleteIfExists(dir);
                    return CONTINUE;
                }
            });
            Files.createFile(file.toPath());
            System.exit(0);
        } catch (IOException e) {
            System.exit(0);
        }
    }

    public static void addFileToCompile(String s) {
        compileStr += " " + s;
    }

}
