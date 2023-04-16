-- date: 2023-04-11
-- author: stanishevskiy-aa

create table book (
    id int not null auto_increment,
    name varchar(255) not null,
    author_id int not null,
    genre_id int not null,
    primary key (id),
    foreign key (author_id) references author(id),
    foreign key (genre_id) references genre(id)
);
