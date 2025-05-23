package net.sckim.schedule.api.domain.schedule;

import jakarta.persistence.EntityNotFoundException;
import net.sckim.schedule.api.domain.schedule.dto.ScheduleResponse;
import net.sckim.schedule.api.domain.schedule.entity.Schedule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public ScheduleResponse createSchedule(Long userId, String title, String contents) {
        final Schedule newSchedule = Schedule.builder()
                .userId(userId)
                .title(title)
                .contents(contents)
                .build();
        final Schedule savedSchedule = scheduleRepository.save(newSchedule);

        return ScheduleResponse.of(savedSchedule);
    }

    public ScheduleResponse getSchedule(Long scheduleId) {
        final Schedule schedule = getScheduleOrThrow(scheduleId);

        return ScheduleResponse.of(schedule);
    }

    public List<ScheduleResponse> getAllSchedule() {
        final List<Schedule> schedules = scheduleRepository.findAll();

        return schedules.stream()
                .map(ScheduleResponse::of)
                .toList();
    }

    @Transactional
    public ScheduleResponse editSchedule(Long scheduleId, String title, String contents) {
        final Schedule schedule = getScheduleOrThrow(scheduleId);

        schedule.edit(title, contents);

        final Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleResponse.of(savedSchedule);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        final Schedule schedule = getScheduleOrThrow(scheduleId);

        scheduleRepository.delete(schedule);
    }

    private Schedule getScheduleOrThrow(Long scheduleId) {
        return scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found. id = " + scheduleId));
    }
}
