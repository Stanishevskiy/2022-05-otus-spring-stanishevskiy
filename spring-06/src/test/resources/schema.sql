drop table comment if exists;
drop table author_book if exists;
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
    genre_id int not null,
    primary key (id),
    foreign key (genre_id) references genre(id)
);

create table author_book (
    author_id int not null,
    book_id int not null,
    foreign key (author_id) references author(id),
    foreign key (book_id) references book(id)
);

create table comment (
    id int not null auto_increment,
    description varchar(2550),
    book_id int not null,
    primary key (id),
    foreign key (book_id) references book(id)
)