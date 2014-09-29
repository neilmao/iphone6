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

        if (args.length > 0)
            logFile = args[0];

        new Spider(logFile).getAvailability(args.length > 1);
    }
}
