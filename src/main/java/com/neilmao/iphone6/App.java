package com.neilmao.iphone6;

/**
 * Created with IntelliJ IDEA.
 * User: neil
 * Date: 29/09/14
 * Time: 11:48 AM
 */
public class App {

    public static void main(String[] args) throws Exception {

        String logFile = "log.txt";

        boolean importantOnly = false;
        boolean sydneyOnly = false;

        if (args.length > 0) {
            logFile = args[0];

            for (int i=1; i<args.length; ++i) {
                if (args[i].equals("i")) {
                    importantOnly = true;
                    continue;
                }
                if (args[i].equals("s")) {
                    sydneyOnly = true;
                }
            }
        }

        new Spider(logFile).getAvailability(importantOnly, sydneyOnly);
    }
}
