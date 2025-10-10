

select * from employee;
-- select * from hibernate_sequence;

-- ALTER TABLE employee add(bonous float(5,2));

-- delete from employee where bonous is null;
-- delete from employee where ename is null;
-- delete  from employee;

commit;

SELECT * FROM nifty200 WHERE TRADE_DATE='2023-09-13';
-- delete from nifty200 WHERE TRADE_DATE='2023-09-13';

select * from nifty200 limit 1;
select * from nifty200 where symbol='TECHM' AND trade_date='2023-08-21' LIMIT 1;
select distinct(SYMBOL) FROM nifty200;

select id,symbol,trade_date,open,high,low,ltp as close FROM nifty200 
where symbol='NAUKRI' AND trade_date between '2023-07-17' and '2023-07-21' 
order by trade_date desc;




select count(*) from nifty200;

SET SQL_SAFE_UPDATES = 0;

SELECT * FROM app_db.user;
insert into user values(1,'Suraj');
insert into user values(2,'Suresh');
insert into user values(3,'Sujit');

delete from user where username='Suresh';

SELECT * FROM user where id=1;



select * from world.city;
select * from world.country;





