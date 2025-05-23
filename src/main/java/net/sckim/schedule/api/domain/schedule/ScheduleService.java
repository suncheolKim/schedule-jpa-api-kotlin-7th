package net.sckim.schedule.api.domain.schedule;

import jakarta.persistence.EntityNotFoundException;
import net.sckim.schedule.api.domain.comment.CommentRepository;
import net.sckim.schedule.api.domain.comment.dto.CommentResponse;
import net.sckim.schedule.api.domain.comment.entity.Comment;
import net.sckim.schedule.api.domain.schedule.dto.ScheduleAndCommentsResponse;
import net.sckim.schedule.api.domain.schedule.dto.SchedulePageResponse;
import net.sckim.schedule.api.domain.schedule.dto.ScheduleResponse;
import net.sckim.schedule.api.domain.schedule.entity.Schedule;
import net.sckim.schedule.api.domain.user.UserRepository;
import net.sckim.schedule.api.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public ScheduleService(ScheduleRepository scheduleRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public ScheduleResponse createSchedule(Long userId, String title, String contents) {
        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found. id = " + userId));

        final Schedule newSchedule = Schedule.builder()
                .user(user)
                .title(title)
                .contents(contents)
                .build();

        final Schedule savedSchedule = scheduleRepository.save(newSchedule);

        return ScheduleResponse.of(savedSchedule, user.getId(), user.getName());
    }

    public ScheduleResponse getSchedule(Long scheduleId) {
        final Schedule schedule = getScheduleOrThrow(scheduleId);

        return ScheduleResponse.of(
                schedule,
                schedule.getUser().getId(),
                schedule.getUser().getName()
        );
    }

    public List<ScheduleResponse> getAllSchedule() {
        final List<Schedule> schedules = scheduleRepository.findAll();

        return schedules.stream()
                .map((Schedule schedule) -> ScheduleResponse.of(
                        schedule,
                        schedule.getUser().getId(),
                        schedule.getUser().getName()
                ))
                .toList();
    }

    @Transactional
    public ScheduleResponse editSchedule(Long scheduleId, String title, String contents) {
        final Schedule schedule = getScheduleOrThrow(scheduleId);

        schedule.edit(title, contents);

        final Schedule savedSchedule = scheduleRepository.save(schedule);

        return ScheduleResponse.of(
                savedSchedule,
                savedSchedule.getUser().getId(),
                savedSchedule.getUser().getName()
        );
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

    public Page<SchedulePageResponse> getSchedulePages(Pageable pageable) {
        // 페이징 대상 조회
        final Page<Schedule> schedulePage = scheduleRepository.findAll(pageable);

        // 일정 ID 리스트 추출
        final List<Long> scheduleIdList = schedulePage.stream()
                .map(Schedule::getId)
                .toList();

        // 일정 ID에 해당하는 모든 댓글을 조회한다.
        final List<Comment> commentList = commentRepository.findAllByScheduleIdIn(scheduleIdList);

        // 댓글에서 일정ID별 댓글수를 만든다.
        final Map<Long, Long> commentCountMap = commentList.stream()
                .collect(Collectors.groupingBy(comment -> comment.getSchedule().getId(), Collectors.counting()));

        // SchedulePageResponse를 조합하여 리턴
        return schedulePage.map(schedule -> SchedulePageResponse.of(schedule, commentCountMap.getOrDefault(schedule.getId(), 0L)));
    }

    public ScheduleAndCommentsResponse getScheduleWithComments(Long scheduleId) {
        final ScheduleResponse schedule = getSchedule(scheduleId);

        final List<Comment> commentList = commentRepository.findAllByScheduleId(scheduleId);
        final List<CommentResponse> commentResponseList = commentList.stream()
                .map(comment -> CommentResponse.of(comment, scheduleId, comment.getUser().getId(), comment.getUser().getName()))
                .toList();

        return ScheduleAndCommentsResponse.of(schedule, commentResponseList);
    }
}


