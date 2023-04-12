insert into genre (name)
    values ('Fantasy'),
           ('Novel'),
           ('Old Genre');

insert into author (name)
    values ('J. R. R. Tolkien'),
           ('Agatha Christie'),
           ('Fedor Dostoevsky'),
           ('Old Author');

insert into book (name, genre_id)
    values ('Hobbit', 1),
           ('The Lord of the Rings', 1),
           ('Murder on the Orient Express', 2),
           ('The Idiot', 2);

insert into author_book (author_id, book_id)
    values (1, 1),
           (1, 2),
           (2, 3),
           (3, 4);
