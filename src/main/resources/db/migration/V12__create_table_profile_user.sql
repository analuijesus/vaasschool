create table Profile_User
(
    `user_id`    bigint,
    `profile_id` bigint,
    PRIMARY KEY (user_id, profile_id),
    FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
    FOREIGN KEY (`profile_id`) REFERENCES `Profile` (`id`)
);