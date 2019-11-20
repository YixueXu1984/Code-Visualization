package TestProject;

import Java.io.BufferedWriter;
import Java.io.FileWriter;

public class TestMain {

    public static void main(String[] args) {
        try {
            BufferedWriter w = new BufferedWriter(new FileWriter("out/injected/out.txt"));
            w.write(Helper.giveMeAString());
            w.close()
        } catch (Exception e) {

        }
    }
}