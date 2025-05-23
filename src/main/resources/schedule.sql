CREATE TABLE `user`
(
    `id`         BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '사용자 식별자',
    `name`       VARCHAR(4)         NOT NULL        COMMENT '사용자명',
    `email`      VARCHAR(100)       NOT NULL UNIQUE COMMENT '이메일',
    `password`   VARCHAR(255)       NOT NULL        COMMENT '비밀번호',
    `created_at` DATETIME           NOT NULL        COMMENT '생성일시',
    `updated_at` DATETIME           NOT NULL        COMMENT '수정일시'
);

CREATE TABLE `schedule`
(
    `id`         BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id`    BIGINT             NOT NULL COMMENT '작성자 ID',
    `title`      VARCHAR(10)        NOT NULL COMMENT '제목',
    `contents`   VARCHAR(500)       NOT NULL COMMENT '내용',
    `created_at` DATETIME           NOT NULL COMMENT '생성일시',
    `updated_at` DATETIME           NOT NULL COMMENT '수정일시',

    CONSTRAINT `fk_schedule_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);

CREATE TABLE `comment`
(
    `id`          BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id`     BIGINT             NOT NULL COMMENT '작성자 ID',
    `schedule_id` BIGINT             NOT NULL COMMENT '일정 ID',
    `contents`    VARCHAR(500)       NOT NULL COMMENT '내용',
    `created_at`  DATETIME           NOT NULL COMMENT '생성일시',
    `updated_at`  DATETIME           NOT NULL COMMENT '수정일시',

    CONSTRAINT `fk_comment_schedule` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`),
    CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
);