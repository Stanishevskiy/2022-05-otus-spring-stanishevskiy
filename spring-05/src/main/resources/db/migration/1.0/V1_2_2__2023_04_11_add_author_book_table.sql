-- date: 2023-04-11
-- author: stanishevskiy-aa

create table author_book (
    author_id int not null,
    book_id int not null,
    foreign key (author_id) references author(id),
    foreign key (book_id) references book(id)
);
