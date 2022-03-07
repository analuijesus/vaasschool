create table `Course`
(
    `id`                       bigint PRIMARY KEY AUTO_INCREMENT,
    `name`                     varchar(75)  not null,
    `code`                     varchar(255) not null unique,
    `estimated_time_to_finish` smallint     not null,
    `visibility`               ENUM ('PUBLIC','PRIVATE') default 'PRIVATE',
    `target_audience`          varchar(255),
    `instructor_name`          varchar(75)  not null,
    `summary`                  text,
    `learned_skills`           varchar(255),
    `subcategory_id`           bigint       not null,
    FOREIGN KEY (`subcategory_id`) REFERENCES `Subcategory` (`id`)
);