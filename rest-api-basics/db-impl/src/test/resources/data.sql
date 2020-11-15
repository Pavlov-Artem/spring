insert into gift_certificate (name,description,price,create_date,last_update_date,duration)
values('vip_certificate','very important certificate', 10, current_timestamp(), current_timestamp(), 10);
insert into gift_certificate (name,description,price,create_date,last_update_date,duration)
values('simple certificate','give some bonus somewhere', 5, current_timestamp(), current_timestamp(), 20);
insert into gift_certificate (name,description,price,create_date,last_update_date,duration)
values('journey certificate','to move somewhere ', 100, current_timestamp(), current_timestamp(), 30);
insert into gift_certificate (name,description,price,create_date,last_update_date,duration)
values('fuel certificate','gives free 100 litres of any fuel in any gas station ', 90, current_timestamp(), current_timestamp(), 30);
insert into gift_certificate (name,description,price,create_date,last_update_date,duration)
values('hair cut certificate','hair cut 50% discount on hair cut ', 100, current_timestamp(), current_timestamp(), 30);

insert into tag (name) values ('cheap');
insert into tag (name) values ('vip');
insert into tag (name) values ('discount');
insert into tag (name) values ('gift');

insert into gift_certificate_has_tag (gift_certificate_id,tag_id) values (1,2);
insert into gift_certificate_has_tag (gift_certificate_id,tag_id) values (2,2);
insert into gift_certificate_has_tag (gift_certificate_id,tag_id) values (2,3);
insert into gift_certificate_has_tag (gift_certificate_id,tag_id) values (3,1);


