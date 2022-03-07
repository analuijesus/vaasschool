create table `Subcategory`(
    `id`                  bigint PRIMARY KEY AUTO_INCREMENT,
    `name`                varchar(75)  not null,
    `code`                varchar(255) not null unique,
    `description`         varchar(255) not null,
    `explanatory_guide`   varchar(255),
    `active`              bit(1) default 0,
    `order_visualization` int          not null,
    `category_id`         bigint       not null,
    FOREIGN KEY (`category_id`) REFERENCES `Category` (`id`)
);