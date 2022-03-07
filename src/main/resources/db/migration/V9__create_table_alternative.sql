create table `Alternative`
(
    `id`                  bigint PRIMARY KEY AUTO_INCREMENT,
    `text`                varchar(255) not null,
    `order_visualization` int,
    `correct`             bit(1),
    `justification`       varchar(255),
    `question_id`         bigint       not null,
    FOREIGN KEY (`question_id`) REFERENCES `Question` (`id`)
);