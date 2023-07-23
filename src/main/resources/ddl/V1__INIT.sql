create table course
(
    id            bigint  not null auto_increment,
    course_code   varchar(255),
    course_credit integer not null,
    course_name   varchar(255),
    prerequisite  longtext,
    description   longtext
        primary key (id)
)

create table course_tag
(
    id              bigint not null auto_increment,
    course_tag_type varchar(255),
    course_id       bigint,
    primary key (id)
)

create table raw_course
(
    id               bigint  not null auto_increment,
    course_code      varchar(255),
    course_credit    integer not null,
    course_name      varchar(255),
    course_professor varchar(255),
    course_room      varchar(255),
    course_time      varchar(255),
    course_type      varchar(255),
    prerequisite     longtext,
    semester         varchar(255),
    year             integer not null,
    primary key (id)
)

create table users
(
    id                 bigint       not null auto_increment,
    created_at         datetime(6),
    updated_at         datetime(6),
    email              varchar(255) not null,
    family_name        varchar(255),
    given_name         varchar(255),
    locale             varchar(255),
    google_picture_url varchar(255),
    major_type         varchar(255) not null,
    name               varchar(255) not null,
    student_id         varchar(255) not null,
    primary key (id)
)

create index idx_user_email on users (email)



create table user_taken_course
(
    id          bigint not null auto_increment,
    created_at  datetime(6),
    updated_at  datetime(6),
    course_code varchar(255),
    course_name varchar(255),
    course_type integer,
    credit      integer,
    grade       varchar(255),
    semester    varchar(255),
    year        integer,
    user_id     bigint,
    primary key (id)
)

-- alter table users
--     add constraint UK_qh3otyipv2k9hqte4a1abcyhq unique (student_id)

alter table course_tag
    add constraint FK3tta6lkm8fr0rgfyr4y3xrr3u
        foreign key (course_id)
            references course (id)

alter table user_taken_course
    add constraint FKpkvqpreqjnf3ku9ptf4c10gc0
        foreign key (user_id)
            references users (id)