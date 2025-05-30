package net.sckim.schedule.api.domain.comment;

import net.sckim.schedule.api.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByScheduleIdIn(List<Long> scheduleIds);

    List<Comment> findAllByScheduleId(Long scheduleId);
}
