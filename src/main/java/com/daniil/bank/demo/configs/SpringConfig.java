package com.daniil.bank.demo.configs;


import org.springframework.scheduling.annotation.Scheduled;

//@Configuration
//@EnableScheduling
public class SpringConfig {


    @Scheduled(fixedDelay = 1000)
    public void scheduleFixedDelayTask() {
        System.out.println(
                "Fixed delay task - " + System.currentTimeMillis() / 1000);
    }


}
