create table `Explanation`
(
    `id`          bigint PRIMARY KEY AUTO_INCREMENT,
    `text`        varchar(255),
    `activity_id` bigint not null,
    FOREIGN KEY (`activity_id`) REFERENCES `Activity` (`id`)
);