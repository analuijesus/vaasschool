create table `User`
(
    `id`         bigint PRIMARY KEY AUTO_INCREMENT,
    `email`      varchar(255) not null,
    `password`   varchar(255) not null
);