import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Writer {

    static protected PrintWriter writer;

    public static void setWriter(String name) {
        try {
            writer = new PrintWriter(name, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            System.out.println("Didn't find file to write to");
            System.exit(0);
        }
    }

    public static void closeWriter(){
        writer.close();
    }

}

