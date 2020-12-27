use yogurt;

DROP DATABASE yogurt;

CREATE DATABASE yogurt DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

USE yogurt;

INSERT INTO studio values (
	1, now(), now(), '경기도 성남시', '종호로 2', '종호 스튜디오2', '2020-04-02', '031-123-1234'
);

SELECT * from verification v ;

UPDATE user SET role = 'ROLE_DEVELOPER' WHERE id = 1;

select * from lecture_item lli ;

update lecture_item set studio_id = 1;

select * from lecture_lecture_items lli ;

select * from lecture;
