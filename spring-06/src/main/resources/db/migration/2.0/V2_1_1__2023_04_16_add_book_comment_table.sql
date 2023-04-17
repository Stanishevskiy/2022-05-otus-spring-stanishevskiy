-- date: 2023-04-16
-- author: stanishevskiy-aa

create table comment (
    id int not null auto_increment,
    description varchar(2550) not null,
    book_id int not null,
    primary key (id),
    foreign key (book_id) references book(id)
);
