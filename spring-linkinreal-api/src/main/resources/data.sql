


insert into creator (id, nick_name, email) values (creator_seq_id.nextval, 'Georges', 'georges.lebon@gmail.com');
insert into creator (id, nick_name, email) values (creator_seq_id.nextval, 'Serge', 'serge.simplon@gmail.com');
insert into creator (id, nick_name, email) values (creator_seq_id.nextval, 'David', 'david.simplon@gmail.com');


insert into event_category (id, category) values (event_category_seq_id.nextval, 'Culture');
insert into event_category (id, category) values (event_category_seq_id.nextval, 'Sport');
insert into event_category (id, category) values (event_category_seq_id.nextval, 'Musique');

insert into place (id, label, street_number, street, postal_code) values (place_seq_id.nextval, 'Le Louvre', 140, 'Rue de Rivoli', 75001);
insert into place (id, label, street_number, street, postal_code) values (place_seq_id.nextval, 'Arena Bercy', 8, 'Rue de Bercy', 75011);
insert into place (id, label, street_number, street, postal_code) values (place_seq_id.nextval, 'Opera', 1, 'Rue de l''Opera', 75009);

insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-02-25T12:45:30', 'viendez !', 5, 1, 1 ,1);
insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-03-03T12:45:30', 'viendez !', 2, 2, 2 ,2);