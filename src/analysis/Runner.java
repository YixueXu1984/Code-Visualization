package analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Runtime;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;


//TODO, injected files should have the log package import, but compiler may
//TODO have trouble finding it, if so, add a copy of the file to injected.
/*
This class takes input files, calls the injection methods, then runs the injected code
Setup:
place all relevant project code into cpsc410-viz/in and
the runtime entry point path into cpsc410-viz/in/mainFile.txt
 */
public class Runner {

    public static void run() {
        //inject everything
        inject();
        //compile and run injected code
        File file;
        projectStack.push(new File("injected"));
        Process p;
        String mainPath, compileStr = "";
        Runtime rt = Runtime.getRuntime();
        //find out main path from mainFile.txt
        try {
            BufferedReader r = new BufferedReader(new FileReader("in/mainFile.txt"));
            mainPath = r.readLine();
            mainPath = mainPath.substring(0, mainPath.length() - 5).replace("/",".");
            r.close();
        } catch (IOException e) {
            System.out.println("Failed to read main file path");
            throw new RuntimeException(); //mainFile should exist and hav path in it.
        }
        try {
            //compile all files
            file = getNext();
            while(file != null) {
                compileStr += " " + file.getPath();
                file = getNext();
            }
            System.out.println("javac" + compileStr);
            p = rt.exec("javac" + compileStr);
            p.waitFor();
            //run main file
            System.out.println("java -cp injected " + mainPath);
            p = rt.exec("java -cp injected " + mainPath);
            p.waitFor();
        } catch (Exception e) {
            System.out.println("Failed to compile or run one or more injected files");
            throw new RuntimeException(); //code should compile
        }
    }

    private static Stack<File> projectStack = new Stack<>();
    private static Iterator<File> dirContents;

    //iterator method recurses through code directory
    private static File getNext() {
        //return next .java file to compile
        File f;
        String s;
        int i;
        if(dirContents != null && dirContents.hasNext()) {
            f = dirContents.next();
            if (f.isFile()) {
                s = f.getName();
                i = s.lastIndexOf(".");
                if (i>0 && s.substring(1 +i).equals("java"))
                    return f;
                else
                    return getNext();
            } else if (f.isDirectory()){
                projectStack.push(f);
                return getNext();
            }
            return getNext();
        } else {
            if(projectStack.size() > 0) {
                dirContents = Arrays.asList(projectStack.pop().listFiles()).iterator();
                return getNext();
            } else
                return null;
        }
    }

    //Note:The inject method call should put the injected file into the proper directory.
    private static void inject() {
        projectStack.push(new File("in"));
        File file = null;
        file = getNext();
        while (file != null) {
            file = getNext();
            Analyzer.makeAnalyzer(file.getName());
        }
    }

}
