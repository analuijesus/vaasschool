create table `Question`
(
    `id`            bigint PRIMARY KEY AUTO_INCREMENT,
    `statement`     varchar(255),
    `question_type` ENUM ('SINGLE_ANSWER','MULTIPLE_ANSWERS','TRUE_FALSE') default 'SINGLE_ANSWER',
    `activity_id`   bigint not null,
    FOREIGN KEY (`activity_id`) REFERENCES `Activity` (`id`)
);