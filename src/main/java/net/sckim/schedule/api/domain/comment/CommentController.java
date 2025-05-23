package net.sckim.schedule.api.domain.comment;

import jakarta.validation.Valid;
import net.sckim.schedule.api.domain.comment.dto.CreateCommentRequest;
import net.sckim.schedule.api.domain.comment.dto.EditCommentRequest;
import net.sckim.schedule.api.domain.comment.dto.CommentResponse;
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
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    public CommentResponse createComment(@RequestBody @Valid CreateCommentRequest request) {
        return commentService.createComment(request.getScheduleId(), request.getUserId(), request.getContents());
    }

    @GetMapping("/comments/{commentId}")
    public CommentResponse getComment(@PathVariable Long commentId) {
        return commentService.getComment(commentId);
    }

    @GetMapping("/comments")
    public List<CommentResponse> getAllComments() {
        return commentService.getAllComment();
    }

    @PutMapping("/comments/{commentId}")
    public CommentResponse updateComment(@PathVariable Long commentId, @RequestBody @Valid EditCommentRequest request) {
        return commentService.editComment(commentId, request.getContents());
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Object> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok()
                .build();
    }
}
