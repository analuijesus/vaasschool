create table `Activity`
(
    `id`                  bigint PRIMARY KEY AUTO_INCREMENT,
    `title`               varchar(255) not null,
    `code`                varchar(255) not null unique,
    `order_visualization` int,
    `active`              bit(1) default 0,
    `type`                ENUM ('EXPLANATION', 'VIDEO', 'QUESTION'),
    `section_id`          bigint       not null,
    FOREIGN KEY (`section_id`) REFERENCES `Section` (`id`)
);