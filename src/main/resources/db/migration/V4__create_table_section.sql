create table `Section`
(
    `id`                  bigint PRIMARY KEY AUTO_INCREMENT,
    `name`                varchar(75)  not null,
    `code`                varchar(255) not null unique,
    `active`              bit(1) default 0,
    `test`                bit(1) default 0,
    `order_visualization` int,
    `course_id`           bigint       not null,
    FOREIGN KEY (`course_id`) REFERENCES `Course` (`id`)
);