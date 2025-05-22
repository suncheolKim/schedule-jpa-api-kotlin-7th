package net.sckim.schedule.api.domain.schedule;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }
}
