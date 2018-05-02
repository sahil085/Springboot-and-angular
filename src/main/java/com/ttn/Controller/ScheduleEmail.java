package com.ttn.Controller;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleEmail {

    @Scheduled(cron = "0 0 9-17 * * MON-FRI" )
    public void sendEmailSchedule()
    {


    }


}
