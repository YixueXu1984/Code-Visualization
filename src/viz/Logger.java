package viz;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Logger {


    public static Stack<String> callStack;
    private static Map<String, Integer> callFreqs;
    private static Map<String, Integer> funcTimes;

    public static void LogInit() {
        callStack = new Stack<>();
        callFreqs = new HashMap<>();
        funcTimes = new HashMap<>();
    }

    public static void timeIn() {

    }

    public static void timeOut() {

    }

    public static void callUp() {

    }

    public static void callDown() {

    }

    public static void log() {

    }

    public static void writeLogs() {
        //GraphMaker.visualize(callFreqs, funcTimes);
    }

}
