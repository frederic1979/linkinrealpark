


insert into creator (id, nick_name, email) values (creator_seq_id.nextval, 'Georges', 'georges.lebon@gmail.com');
insert into creator (id, nick_name, email) values (creator_seq_id.nextval, 'Serge', 'serge.simplon@gmail.com');
insert into creator (id, nick_name, email) values (creator_seq_id.nextval, 'David', 'david.simplon@gmail.com');

/*insert into participant (id, nick_name) values (participant_seq_id.nextval, 'David');*/




insert into event_category (id, category) values (event_category_seq_id.nextval, 'Culture');
insert into event_category (id, category) values (event_category_seq_id.nextval, 'Sport');
insert into event_category (id, category) values (event_category_seq_id.nextval, 'Musique');

insert into place (id, label, street_number, street, postal_code) values (place_seq_id.nextval, 'Le Louvre', 140, 'Rue de Rivoli', 75001);
insert into place (id, label, street_number, street, postal_code) values (place_seq_id.nextval, 'Arena Bercy', 8, 'Rue de Bercy', 75011);
insert into place (id, label, street_number, street, postal_code) values (place_seq_id.nextval, 'Opera', 1, 'Rue de l''Opera', 75009);

insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-02-25T12:45:30', 'viendez !', 5, 1, 1 ,1);
insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-03-03T12:45:30', 'viendez !', 2, 2, 2 ,2);
insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-04-25T12:45:30', 'dfgdfg !', 5, 1, 1 ,1);
insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-05-03T12:45:30', 'dbbgfj !', 2, 2, 2 ,2);
insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-06-25T12:45:30', 'k;jjk; !', 5, 1, 1 ,1);
insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-07-03T12:45:30', 'zefeze !', 2, 2, 2 ,2);
insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-08-25T12:45:30', 'hjkyu !', 5, 1, 1 ,1);
insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-09-03T12:45:30', 'ztre !', 2, 2, 2 ,2);
insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-10-25T12:45:30', 'jmk !', 5, 1, 1 ,1);
insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-11-03T12:45:30', 'sdfsdf !', 2, 2, 2 ,2);
insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-12-25T12:45:30', 'hrth !', 5, 1, 1 ,1);
insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-03-01T12:45:30', 'yurtru !', 2, 2, 2 ,2);
insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-02-02T12:45:30', 'etere !', 5, 1, 1 ,1);
insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-03-03T12:45:30', 'dfgdf !', 2, 2, 2 ,2);
insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-02-05T12:45:30', 'dfdgr !', 5, 1, 1 ,1);
insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-03-06T12:45:30', 'luilu !', 2, 2, 2 ,2);
insert into event (id, date, description, participant_nb, creator_id, event_category_id, place_id) values (event_seq_id.nextval, '2020-02-07T12:45:30', 'rtyrh !', 5, 1, 1 ,1);


insert into participant (id, nick_name,event_id) values (participant_seq_id.nextval, 'kiki',1);
insert into participant (id, nick_name,event_id) values (participant_seq_id.nextval, 'lolo',1);
insert into participant (id, nick_name,event_id) values (participant_seq_id.nextval, 'juju',2);
insert into participant (id, nick_name,event_id) values (participant_seq_id.nextval, 'tyty',1);