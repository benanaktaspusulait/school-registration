
create table school_registration.courses
(
    id          bigint       not null
        primary key,
    created_at  datetime     null,
    create_user bigint       null,
    update_user bigint       null,
    updated_at  datetime     null,
    name        varchar(255) null,
    constraint UK_5o6x4fpafbywj4v2g0owhh11r
        unique (name)
);

create table school_registration.hibernate_sequence
(
    next_val bigint null
);



create table school_registration.students
(
    id          bigint       not null
        primary key,
    created_at  datetime     null,
    create_user bigint       null,
    update_user bigint       null,
    updated_at  datetime     null,
    firstname   varchar(255) not null,
    lastname    varchar(255) not null,
    constraint UKqir8bthxl6i6dp4nxh5j2sjox
        unique (firstname, lastname)
);

create table school_registration.course_registration
(
    id            bigint   not null
        primary key,
    created_at    datetime null,
    create_user   bigint   null,
    update_user   bigint   null,
    updated_at    datetime null,
    course_id     bigint   not null,
    grade         int      null,
    registered_at datetime null,
    student_id    bigint   not null,
    constraint COURSE_REGISTRATION_COURSE
        foreign key (course_id) references school_registration.courses (id),
    constraint COURSE_REGISTRATION_STUDENT
        foreign key (student_id) references school_registration.students (id)
);

create view student_without_courses
as
select s.firstname, s.lastname, s.id
from students s
where id in (
    select id
    from school_registration.students
    where id not in (
        select distinct student_id
        from school_registration.course_registration));

create view course_without_students
as
select s.name, s.id
from courses s
where id in (
    select id
    from school_registration.courses
    where id not in (
        select distinct course_id
        from school_registration.course_registration));