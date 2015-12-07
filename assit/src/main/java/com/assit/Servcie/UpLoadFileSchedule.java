package com.assit.Servcie;

import java.util.Date;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class UpLoadFileSchedule {
	@Scheduled(cron = "0 */1 *  * * * ")
    public void uploadFile(){
        System.out.println ("Scheduling Tasks Examples By Cron: The time is now " + new Date());
    }
}
