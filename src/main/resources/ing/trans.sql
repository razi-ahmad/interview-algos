create table transactions (
                              amount integer not null,
                              date date not null
);

insert into transactions values ('1000', '2020-01-06');
insert into transactions values ('-10', '2020-01-14');
insert into transactions values ('-75', '2020-01-20');
insert into transactions values ('-5', '2020-01-25');
insert into transactions values ('-4', '2020-01-29');
insert into transactions values ('2000', '2020-03-10');
insert into transactions values ('-75', '2020-03-12');
insert into transactions values ('-20', '2020-03-15');
insert into transactions values ('40', '2020-03-15');
insert into transactions values ('-50', '2020-03-17');
insert into transactions values ('200', '2020-10-10');
insert into transactions values ('-200', '2020-10-10');



select
        (select sum(amount) from transactions) -
        (select 5 *
                (select 12 -
                        (select count(*) from (
                                                  select sum(amount)from (select amount, date from transactions where amount< 0) as creditAmount
                                                  group by extract(MONTH FROM date) having count(amount)>2 and sum(amount)*-1>99) as freeMonth)
                            as months)
                    as fee)
        as balance
