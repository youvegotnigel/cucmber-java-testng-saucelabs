package com.youvegotnigel.automation.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.TimeZone;

public class DateTime extends DateTimeUtility{

    public static Properties config;
    private static String timeZone;
    public static final Logger log = LogManager.getLogger(DateTime.class.getName());


    /**
     * Get date time formatted value
     *
     * @param text String to check if it's a date time to get Date/Time
     * @return the Date/Time value
     */
    public static String formatIfDateTime(String text) {

        LoadConfigProperty();
        timeZone = config.getProperty("TIME_ZONE");
        log.info("The current TimeZone ID is: " + TimeZone.getTimeZone(timeZone).getID());
        log.debug("Setting datetime for " + text);

        switch (text) {
            case "year":
                return getCurrentYear();

            case "last year":
                return getPreviousYear(1);

            case "next year":
                return getFutureYear(1);

            case "today":
                return getCurrentDateTime(timeZone,"dd-MMM-yyyy");

            case "previous":
                return getPreviousDateTime(1,"dd-MMM-yyyy");

            case "future":
                return getFutureDateTime(1,"dd-MMM-yyyy");

            case "today yyyy-MM-dd":
                return getCurrentDateTime(timeZone,"yyyy-MM-dd");

            case "today dd/MM/yyyy HH:mm":
                return getCurrentDateTime(timeZone,"dd/MM/yyyy HH:mm");

            case "today d":
                return getCurrentDateTime(timeZone, "d");

            case "now HH:mm:ss":
                return getCurrentDateTime(timeZone, "HH:mm:ss");

            case "now":
                return getCurrentDateTime(timeZone,"hh:mm a");

            case "now h:mm":
                return getCurrentDateTime(timeZone,"h:mm");

            case "today+now":
                return getCurrentDateTime(timeZone,"dd-MMM-yyyy HH:mm");

            case "today+now+time":
                return getCurrentDateTime(timeZone,"dd-MMM-yyyy hh:mm:ss aa");

            case "now HH":
                return getCurrentDateTime(timeZone,"HH");

            case "now hh":
                return getCurrentDateTime(timeZone,"hh");

            case "now mm":
                return getCurrentDateTime(timeZone,"mm");

            case "now m0":
                return getCurrentDateTime(timeZone,"m0");

            default:

                if (text.matches("^previous\\+\\d$")) {
                    String[] date = text.split("\\+");
                    return getPreviousDateTime(Integer.parseInt(date[1]), "dd-MMM-yyyy");
                }

                else if (text.matches("^future\\+\\d$")) {
                    String[] date = text.split("\\+");
                    return getFutureDateTime(Integer.parseInt(date[1]), "dd-MMM-yyyy");
                }

                else if(text.matches("^now HH\\+\\d$")){
                    String[] change = text.split("now HH+");
                    return getFutureTime(Integer.parseInt(change[1]),0, timeZone,"HH");
                }

                else if(text.matches("^now HH\\-\\d$")){
                    String[] change = text.split("now HH-");
                    return getPastTime(Integer.parseInt(change[1]),0, timeZone,"HH");
                }

                else if(text.matches("^now mm\\+\\d$")){
                    String[] change = text.split("now mm+");
                    return getFutureTime(0, Integer.parseInt(change[1]), timeZone,"mm");
                }

                else if(text.matches("^now mm\\-\\d$")){
                    String[] change = text.split("now mm-");
                    return getPastTime(0, Integer.parseInt(change[1]), timeZone,"mm");
                }

                return text;
        }
    }

    public static void LoadConfigProperty(){
        try {
            config = new Properties();
            FileInputStream ip = new FileInputStream(
                    System.getProperty("user.dir") + "//src//test//resources//config//config.properties");
            config.load(ip);
            log.debug("Properties file loaded successfully");
        }catch (Exception e){
            log.error("Configuration Properties file not found." + Arrays.toString(e.getStackTrace()));
        }
    }
}
