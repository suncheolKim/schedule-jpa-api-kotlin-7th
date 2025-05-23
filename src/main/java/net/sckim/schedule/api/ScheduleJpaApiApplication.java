package net.sckim.schedule.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ScheduleJpaApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleJpaApiApplication.class, args);
    }

}
