package net.sckim.schedule.api.domain.comment;

import jakarta.persistence.EntityNotFoundException;
import net.sckim.schedule.api.domain.comment.dto.CommentResponse;
import net.sckim.schedule.api.domain.comment.entity.Comment;
import net.sckim.schedule.api.domain.schedule.ScheduleRepository;
import net.sckim.schedule.api.domain.schedule.entity.Schedule;
import net.sckim.schedule.api.domain.user.UserRepository;
import net.sckim.schedule.api.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, ScheduleRepository scheduleRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public CommentResponse createComment(Long scheduleId, Long userId, String contents) {
        final Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("Schedule not found. id = " + scheduleId));

        final User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found. id = " + userId));

        final Comment newComment = Comment.builder()
                .schedule(schedule)
                .user(user)
                .contents(contents)
                .build();
        final Comment savedComment = commentRepository.save(newComment);

        return CommentResponse.of(savedComment, scheduleId, userId, user.getName());
    }

    public CommentResponse getComment(Long commentId) {
        final Comment comment = getCommentOrThrow(commentId);

        return CommentResponse.of(
                comment,
                comment.getSchedule().getId(),
                comment.getUser().getId(),
                comment.getUser().getName()
        );
    }

    public List<CommentResponse> getAllComment() {
        final List<Comment> comments = commentRepository.findAll();

        return comments.stream()
                .map((Comment comment) -> CommentResponse.of(
                        comment,
                        comment.getSchedule().getId(),
                        comment.getUser().getId(),
                        comment.getUser().getName()
                ))
                .toList();
    }

    @Transactional
    public CommentResponse editComment(Long commentId, String contents) {
        final Comment comment = getCommentOrThrow(commentId);

        comment.edit(contents);

        final Comment savedComment = commentRepository.save(comment);

        return CommentResponse.of(
                savedComment,
                savedComment.getSchedule().getId(),
                savedComment.getUser().getId(),
                savedComment.getUser().getName()
        );
    }

    @Transactional
    public void deleteComment(Long commentId) {
        final Comment comment = getCommentOrThrow(commentId);

        commentRepository.delete(comment);
    }

    private Comment getCommentOrThrow(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found. id = " + commentId));
    }
}
