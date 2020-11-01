insert into gift_certificate (name,description,price,create_date,last_update_date,duration)
values('vip_certificate','very important certificate', 10, current_timestamp(), current_timestamp(), 5);
insert into gift_certificate (name,description,price,create_date,last_update_date,duration)
values('simple certificate','give some bonus somewhere', 5, current_timestamp(), current_timestamp(), 5);
insert into gift_certificate (name,description,price,create_date,last_update_date,duration)
values('journey certificate','to move somewhere ', 100, current_timestamp(), current_timestamp(), 5);


insert into tag (name) value ('cheap');
insert into tag (name) value ('vip');
insert into tag (name) value ('trip');

insert into gift_certificate_has_tag (gift_certificate_id,tag_id) values (1,2);
insert into gift_certificate_has_tag (gift_certificate_id,tag_id) values (2,2);
insert into gift_certificate_has_tag (gift_certificate_id,tag_id) values (2,3);
insert into gift_certificate_has_tag (gift_certificate_id,tag_id) values (3,1);


