create table `Video`
(
    `id`            bigint PRIMARY KEY AUTO_INCREMENT,
    `url`           varchar(255) not null,
    `minutes`       int,
    `transcription` varchar(255),
    `activity_id`   bigint       not null,
    FOREIGN KEY (`activity_id`) REFERENCES `Activity` (`id`)
);