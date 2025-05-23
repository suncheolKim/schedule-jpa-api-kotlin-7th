package net.sckim.schedule.api.domain.schedule;

import net.sckim.schedule.api.domain.schedule.dto.ScheduleResponse;
import net.sckim.schedule.api.domain.schedule.entity.Schedule;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponse createSchedule(String name, String title, String contents) {
        final Schedule newSchedule = new Schedule(name, title, contents);
        final Schedule savedSchedule = scheduleRepository.save(newSchedule);

        return ScheduleResponse.of(savedSchedule);
    }

    public ScheduleResponse getSchedule(Long scheduleId) {
        final Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found. id = " + scheduleId));

        return ScheduleResponse.of(schedule);
    }
}
