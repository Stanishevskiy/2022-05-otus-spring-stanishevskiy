drop table book if exists;
drop table author if exists;
drop table genre if exists;

create table genre (
    id int not null auto_increment,
    name varchar(255) not null,
    primary key (id)
);
create table author (
    id int not null auto_increment,
    name varchar(255) not null,
    primary key (id)
);
create table book (
    id int not null auto_increment,
    name varchar(255) not null,
    author_id int not null,
    genre_id int not null,
    primary key (id),
    foreign key (author_id) references author(id),
    foreign key (genre_id) references genre(id),
);