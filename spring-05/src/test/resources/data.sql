insert into genre (name) values ('Fantasy');
insert into genre (name) values ('Novel');

insert into author (name) values ('J. R. R. Tolkien');
insert into author (name) values ('Agatha Christie');
insert into author (name) values ('Fedor Dostoevsky');

insert into book (name, author_id, genre_id) values ('Hobbit', 1, 1);
insert into book (name, author_id, genre_id) values ('The Lord of the Rings', 1, 1);
insert into book (name, author_id, genre_id) values ('Murder on the Orient Express', 2, 2);
insert into book (name, author_id, genre_id) values ('The Idiot', 3, 2);
