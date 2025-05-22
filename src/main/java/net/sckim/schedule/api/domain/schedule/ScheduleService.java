package net.sckim.schedule.api.domain.schedule;

import net.sckim.schedule.api.domain.schedule.dto.ScheduleResponse;
import net.sckim.schedule.api.domain.schedule.entity.Schedule;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponse createSchedule(String name, String title, String contents) {
        final LocalDateTime now = LocalDateTime.now();

        final Schedule newSchedule = new Schedule(name, title, contents, now, now);
        final Schedule savedSchedule = scheduleRepository.save(newSchedule);

        return ScheduleResponse.of(savedSchedule);
    }
}
