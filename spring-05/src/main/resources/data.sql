insert into genre (name) values ('Science fiction');
insert into genre (name) values ('Fantasy');
insert into genre (name) values ('Novel');

insert into author (name) values ('Isaac Asimov');
insert into author (name) values ('Arthur C. Clarke');
insert into author (name) values ('J. R. R. Tolkien');
insert into author (name) values ('George R. R. Martin');
insert into author (name) values ('Agatha Christie');
insert into author (name) values ('Fedor Dostoevsky');

insert into book (name, author_id, genre_id) values ('Foundation', 1, 1);
insert into book (name, author_id, genre_id) values ('I, robot', 1, 1);
insert into book (name, author_id, genre_id) values ('2001: A Space Odyssey', 2, 1);
insert into book (name, author_id, genre_id) values ('Childhood''s End', 2, 1);
insert into book (name, author_id, genre_id) values ('Hobbit', 3, 2);
insert into book (name, author_id, genre_id) values ('The Lord of the Rings', 3, 2);
insert into book (name, author_id, genre_id) values ('A Game of Thrones', 4, 2);
insert into book (name, author_id, genre_id) values ('A Clash of Kings', 4, 2);
insert into book (name, author_id, genre_id) values ('And Then There Were None', 5, 3);
insert into book (name, author_id, genre_id) values ('Murder on the Orient Express', 5, 3);
insert into book (name, author_id, genre_id) values ('Crime and Punishment', 6, 3);
insert into book (name, author_id, genre_id) values ('The Idiot', 6, 3);

