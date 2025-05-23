package net.sckim.schedule.api.domain.schedule;

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

    public List<ScheduleResponse> getAllSchedule() {
        final List<Schedule> schedules = scheduleRepository.findAll();

        return schedules.stream()
                .map(ScheduleResponse::of)
                .toList();
    }

    @Transactional
    public ScheduleResponse editSchedule(Long scheduleId, String name, String title, String contents) {
        final Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found. id = " + scheduleId));

        schedule.edit(name, title, contents);

        final Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleResponse.of(savedSchedule);
    }

    @Transactional
    public void deleteSchedule(Long scheduleId) {
        final Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found. id = " + scheduleId));

        scheduleRepository.delete(schedule);
    }
}
