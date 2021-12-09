create database vaasschool;

create table `Category` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(75) not null,
  `code` varchar(255) not null unique,
  `description` varchar(255) not null,
  `explanatory_guide` varchar(255),
  `active` bit(1),
  `order` int not null,
  `imagem_path` varchar(255) not null,
  `color_code` varchar(7) not null
);

create table `Subcategory` (
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(75) not null,
  `code` varchar(255) not null unique,
  `description` varchar(255) not null,
  `explanatory_guide` varchar(255),
  `active` bit(1),
  `order` int not null,
  `category_id` int not null,
  FOREIGN KEY(`category_id`) REFERENCES `Category`(`id`)
);

create table `Course`(
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(75) not null,
  `code` varchar (255) not null unique,
  `estimated_time_to_finish` smallint not null,
  `visibility` ENUM('PUBLIC','PRIVATE') default 'PRIVATE',
  `target_audience` varchar(255),
  `instructor_name` varchar(75) not null,
  `summary` text,
  `learned_skills` varchar(255),
  `subcategory_id` int not null,
  FOREIGN KEY(`subcategory_id`) REFERENCES `Subcategory`(`id`)
);

create table `Section`(
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(75) not null,
  `code` varchar(255) not null unique,
  `active` bit(1),
  `test` bit(1),
  `order` int,
  `course_id` int not null,
  FOREIGN KEY(`course_id`) REFERENCES `Course`(`id`)
);

create table `Activity`(
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `title` varchar(255) not null,
  `code` varchar(255) not null unique,
  `order` int,
  `active` bit(1),
  `type` ENUM('Explanation', 'Video', 'Question'),
  `section_id` int not null,
  FOREIGN KEY(`section_id`) REFERENCES `Section`(`id`)
);

create table `Explanation`(
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `text` varchar(255),
  `activity_id` int not null,
  FOREIGN KEY(`activity_id`) REFERENCES `Activity`(`id`)
);

create table `Video`(
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `url` varchar(255) not null,
  `minutes` smallint,
  `transcription` varchar(255),
  `activity_id` int not null,
  FOREIGN KEY(`activity_id`) REFERENCES `Activity`(`id`)
);
 
create table `Question`(
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `statiment` varchar(255),
  `question_type` ENUM('SINGLE_ANSWER','MULTIPLE_ANSWERS','TRUE_FALSE') default 'SINGLE_ANSWER',
  `activity_id` int not null,
  FOREIGN KEY(`activity_id`) REFERENCES `Activity`(`id`)
);

create table `Alternative`(
  `id` int PRIMARY KEY AUTO_INCREMENT,
  `text` varchar(255) not null,
  `order` int,
  `correct` bit(1),
  `justification` varchar(255),
  `question_id` int not null,
  FOREIGN KEY(`question_id`) REFERENCES `Question`(`id`)
);
