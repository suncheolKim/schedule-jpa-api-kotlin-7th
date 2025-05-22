CREATE TABLE `schedule`
(
    `id`         BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT COMMENT '일정 ID',
    `name`       VARCHAR(4)         NOT NULL COMMENT '작성자명',
    `title`      VARCHAR(10)        NOT NULL COMMENT '제목',
    `contents`   VARCHAR(500)       NOT NULL COMMENT '내용',
    `created_at` DATETIME           NOT NULL COMMENT '생성일시',
    `updated_at` DATETIME           NOT NULL COMMENT '수정일시'
);