package net.sckim.schedule.api.domain.schedule;

import jakarta.validation.Valid;
import net.sckim.schedule.api.domain.schedule.dto.CreateScheduleRequest;
import net.sckim.schedule.api.domain.schedule.dto.EditScheduleRequest;
import net.sckim.schedule.api.domain.schedule.dto.ScheduleResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedules")
    public ScheduleResponse createSchedule(@RequestBody @Valid CreateScheduleRequest request) {
        return scheduleService.createSchedule(request.getUserId(), request.getTitle(), request.getContents());
    }

    @GetMapping("/schedules/{scheduleId}")
    public ScheduleResponse getSchedule(@PathVariable Long scheduleId) {
        return scheduleService.getSchedule(scheduleId);
    }

    @GetMapping("/schedules")
    public List<ScheduleResponse> getAllSchedules() {
        return scheduleService.getAllSchedule();
    }

    @PutMapping("/schedules/{scheduleId}")
    public ScheduleResponse updateSchedule(@PathVariable Long scheduleId, @RequestBody @Valid EditScheduleRequest request) {
        return scheduleService.editSchedule(scheduleId, request.getTitle(), request.getContents());
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Object> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.ok()
                .build();
    }
}
