package sample_tests;

import helpers.DateTime;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DateTimeDemo {

    public static void main(String[] args) {

        final Logger log = LogManager.getLogger(DateTimeDemo.class.getName());
        DateTime dateTime = new DateTime();

        //Variables
        String today = dateTime.formatIfDateTime("today");

        String tomorrow = dateTime.formatIfDateTime("future");
        String twoDaysFromNow = dateTime.formatIfDateTime("future+2");
        String threeDaysFromNow = dateTime.formatIfDateTime("future+3");

        String yesterday = dateTime.formatIfDateTime("previous");
        String twoDaysAgo = dateTime.formatIfDateTime("previous+2");
        String threeDaysAgo = dateTime.formatIfDateTime("previous+3");
        String fourDaysAgo = dateTime.formatIfDateTime("previous+4");

        String todayYMD = dateTime.formatIfDateTime("today yyyy-MM-dd");
        String todayD = dateTime.formatIfDateTime("today d");

        String nowHMS = dateTime.formatIfDateTime("now HH:mm:ss");
        String nowDigital = dateTime.formatIfDateTime("now");

        String todayNow = dateTime.formatIfDateTime("today+now");

        String nowHHFutureTime = dateTime.formatIfDateTime("now HH+3");
        String nowHHPastTime = dateTime.formatIfDateTime("now HH-3");
        String nowMMFutureTime = dateTime.formatIfDateTime("now mm+3");
        String nowMMPastTime = dateTime.formatIfDateTime("now mm-3");

//        String test = dateTime.formatIfDateTime("now m0");
//        System.out.println(test);

        //Statementss
        log.debug("3 days from now  : " + threeDaysFromNow);
        log.debug("2 days from now  : " + twoDaysFromNow);
        log.debug("Tomorrow will be : " + tomorrow);
        log.debug("Today is         : " + today);
        log.debug("Yesterday was    : " + yesterday);
        log.debug("2 days ago was   : " + twoDaysAgo);
        log.debug("3 days ago was   : " + threeDaysAgo);
        log.debug("4 days ago was   : " + fourDaysAgo);

        log.debug("Today in yyyy-MM-dd : " + todayYMD);
        log.debug("Today in dd : " + todayD);

        log.debug("Current time in hh:mm:ss : " + nowHMS);
        log.debug("Current time in digital format : " + nowDigital);


        log.debug("Hour in 3 hours     : " + nowHHFutureTime);
        log.debug("Hour in 3 hours ago : " + nowHHPastTime);
        log.debug("Minute in 3 minutes     : " + nowMMFutureTime);
        log.debug("Minute in 3 minutes ago : " + nowMMPastTime);

    }


}
