package net.sckim.schedule.api.domain.schedule;

import net.sckim.schedule.api.domain.schedule.dto.CreateScheduleRequest;
import net.sckim.schedule.api.domain.schedule.dto.ScheduleResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedules")
    public ScheduleResponse createSchedule(@RequestBody CreateScheduleRequest request) {
        return scheduleService.createSchedule(request.getName(), request.getTitle(), request.getContents());
    }
}
