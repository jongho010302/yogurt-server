use yogurt_dev;

DROP DATABASE yogurt_dev;

CREATE DATABASE yogurt_dev DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE yogurt_dev;

SELECT * from verification v ;

UPDATE user SET role = 'ROLE_DEVELOPER' WHERE id = 1;

select * from lecture_item lli ;

update lecture_item set studio_id = 1;

select * from lecture_lecture_items lli ;

select * from user;