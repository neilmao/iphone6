package com.neilmao.iphone6;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: neil
 * Date: 29/09/14
 * Time: 11:48 AM
 */
public class App {

    private static final String LOG_FOLDER = "log/";

    // time window between two checks in ms
    private static long PERIOD = 100;

    public static void main(String[] args) throws Exception {

        boolean importantOnly = false;
        boolean sydneyOnly = false;

        SimpleDateFormat smf = new SimpleDateFormat("MMdd-HHmmss-SSS");

        File logFolder = new File(LOG_FOLDER);
        if (!logFolder.exists() || !logFolder.isDirectory()) {
            logFolder.mkdir();
        }

        String logFile = LOG_FOLDER + smf.format(new Date()) + ".log";

        for (int i=0; i<args.length; ++i) {
            if (args[i].equals("-i")) {
                importantOnly = true;
                continue;
            }
            if (args[i].equals("-s")) {
                sydneyOnly = true;
                continue;
            }
            if (args[i].equals("-t") && i + 1 < args.length) {
               PERIOD = extractTime(args[++i]);
            }
        }

        new Spider(logFile, PERIOD).getAvailability(importantOnly, sydneyOnly);
    }

    private static long extractTime(String time) {

        String number = "";
        String unit = "";

        long value;

        for (int i=0; i<time.length(); ++i) {
            if (time.charAt(i) >= '0' && time.charAt(i) <= '9') {
               number += time.charAt(i);
            } else {
               unit = time.substring(i);
            }
        }

        try {
            value = Long.parseLong(number);

            if (unit.equals("H") || unit.equals("h")) {
                PERIOD = value * 60 * 60 * 1000;
            }

            if (unit.equals("M") || unit.equals("m")) {
                PERIOD = value * 60 * 1000;
            }

            if (unit.equals("S") || unit.equals("s")) {
                PERIOD = value * 1000;
            }

        } catch (Exception e) {}

        return PERIOD;
    }
}
