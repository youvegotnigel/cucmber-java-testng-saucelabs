package com.youvegotnigel.automation.utils;

public class DateTime extends DateTimeUtility{

    /**
     * Get date time formatted value
     *
     * @param text String to check if it's a date time to get Date/Time
     * @return the Date/Time value
     */
    public static String formatIfDateTime(String text) {

        switch (text) {
            case "year":
                return getCurrentYear();

            case "last year":
                return getPreviousYear(1);

            case "next year":
                return getFutureYear(1);

            case "today":
                return getCurrentDateTime("dd-MMM-yyyy");

            case "previous":
                return getPreviousDateTime(1,  "dd-MMM-yyyy");

            case "future":
                return getFutureDateTime(1,  "dd-MMM-yyyy");

            case "today yyyy-MM-dd":
                return getCurrentDateTime("yyyy-MM-dd");

            case "today dd/MM/yyyy HH:mm":
                return getCurrentDateTime("dd/MM/yyyy HH:mm");

            case "today d":
                return getCurrentDateTime("d");

            case "now HH:mm:ss":
                return getCurrentDateTime("HH:mm:ss");

            case "now":
                return getCurrentDateTime("hh:mm aa");

            case "now h:mm":
                return getCurrentDateTime("h:mm");

            case "today+now":
                return getCurrentDateTime("dd-MMM-yyyy HH:mm");

            case "today+now+time":
                return getCurrentDateTime("dd-MMM-yyyy hh:mm:ss aa");

            case "now HH":
                return getCurrentDateTime("HH");

            case "now hh":
                return getCurrentDateTime("hh");

            case "now mm":
                return getCurrentDateTime("mm");

            case "now m0":
                return getCurrentDateTime("m0");

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
                    return getFutureTime(Integer.parseInt(change[1]),0,"HH");
                }

                else if(text.matches("^now HH\\-\\d$")){
                    String[] change = text.split("now HH-");
                    return getPastTime(Integer.parseInt(change[1]),0,"HH");
                }

                else if(text.matches("^now mm\\+\\d$")){
                    String[] change = text.split("now mm+");
                    return getFutureTime(0, Integer.parseInt(change[1]),"mm");
                }

                else if(text.matches("^now mm\\-\\d$")){
                    String[] change = text.split("now mm-");
                    return getPastTime(0, Integer.parseInt(change[1]),"mm");
                }

                return text;
        }
    }
}
