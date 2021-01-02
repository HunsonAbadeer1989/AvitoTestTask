insert into room (description, price, creation_date)
    values ("room for forever alone", 100.0, ADDDATE(NOW(), INTERVAL -10 DAY)),
            ("room for Edward&Vivian", 200.0, ADDDATE(NOW(), INTERVAL -30 DAY)),
            ("room for just married", 300.0, ADDDATE(NOW(), INTERVAL -50 DAY));

insert into reservation (beginning, ending, room_id)
    values (NOW(), ADDDATE(NOW(), INTERVAL 3 DAY),
               (SELECT id FROM room WHERE room.description LIKE 'room for forever alone')),
          (ADDDATE(NOW(), INTERVAL 5 DAY), ADDDATE(NOW(), INTERVAL 6 DAY),
               (SELECT id FROM room WHERE room.description LIKE 'room for forever alone')),
          (NOW(), ADDDATE(NOW(), INTERVAL 7 DAY),
               (SELECT id FROM room WHERE room.description LIKE 'room for Edward&Vivian')),
          (NOW(), ADDDATE(NOW(), INTERVAL 3 DAY),
               (SELECT id FROM room WHERE room.description LIKE 'room for just married'));