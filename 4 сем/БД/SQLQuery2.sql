drop database exTest
create database exTest
/*
 Создайте таблицы Сотрудники (КодСотрудника, ФИО, ДатаНайма, Должность, Отдел, Зарплата) и Отпуска (КодОтпуска, КодСотрудника, ДатаНачала, ДатаОкончания, Тип, ОплатаРуб). 
  Задайте необходимые ограничения целостности. 
  Создайте процедуру, которая выводит список всех сотрудников отдела с указанием суммы, состоящей из зарплаты и отпускных. 
  Параметры – наименование отдела, год. Обработайте возможные ошибки. 
  Создайте триггер, который не дает отправить сотрудника в отпуск, пока он не проработал полгода.
*/
use exTest

create table Sotrudnik(
	id_s int identity(1,1) primary key,
	fio nvarchar(50),
	dateN date not null,
	otd nvarchar(50),
	zp int
);
create table Otpusk(
	id_ot int identity(1,1) primary key,
	id_s int foreign key references Sotrudnik(id_s),
	dateS date not null,
	dateF date not null,
	tip nvarchar(50),
	ozp int
);

insert into Sotrudnik values('fio','23/02/2021','otd1',1440),
('fio2','20/05/2022','otd2',1000),
('fio18','28/07/2019','otd2',1800),
('fio2','20/05/2022','otd2',1000),
('fio17','20/05/2022','otd1',1700),
('fio3','20/05/2023','otd2',1000),
('fio19','20/05/2020','otd1',1900),
('fio9','20/05/2023','otd2',900)

insert into Otpusk values(11,'20/04/2023', '20/06/2023','type',180),
(10,'20/05/2023', '20/06/2023','type',10),
(11,'28/05/2023', '20/06/2023','type',10),
(12,'20/03/2023', '20/05/2023','type',190),
(13,'20/05/2023', '20/06/2023','type',100),
(14,'20/05/2023', '20/06/2023','type',110),
(15,'20/05/2023', '20/06/2023','type',200),
(16,'20/04/2022', '20/06/2022','type',200),
(10,'20/04/2023', '20/06/2023','type',200)

go
create procedure p1 @o nvarchar(20),@g int
as
begin
	begin try

	if @g<2000
	raiserror('hbfjk',11,1)
		select s.id_s,fio, sum(zp+ozp) from Sotrudnik s inner join Otpusk o
		on s.id_s=o.id_s
		where year(s.dateN)<=@g and YEAR(o.dateS) = @g and 
		s.otd=isnull(@o,s.otd)
		group by  s.id_s,fio
	end try

	begin catch
		print error_number();
		print error_message();
		print error_line();
		print error_procedure();
	end catch
return 0;
end

declare @e int 
exec @e = p1 @o='otd2', @g=2023
drop procedure p1

create trigger t1 on Otpusk after insert
as
begin
	declare @m int, @d date
	set @d = (select dateN from Sotrudnik s inner join inserted i
	on s.id_s=i.id_s)

	set @m=datediff(m,@d, getdate())
	if @m<6
		begin
			print 'лох'
			delete q
			from Otpusk q inner join inserted i
			on q.id_ot=i.id_ot
		end
return;
end

select * from Sotrudnik

insert into Otpusk values(8,'20/05/2023', '20/06/2023','type',120)
drop trigger t1

/*
Таблицы сотрудники и отпуска, сделать ограничения, написать процедуру которая удаляет сотрудников, которые не ходили в отпуск. 
Сделать обработку ошибок. Написать триггер, который при удалении сотрудника копирует удалённые данные в таблицу аудит
*/
select * from Sotrudnik
go
create procedure p2 @otd nvarchar(20), @y int
as 
begin
	select * from Sotrudnik
	delete q
	from Sotrudnik q
	where not exists(select * from Otpusk o
	where q.id_s=o.id_s)
	and q.otd=@otd
	select * from Sotrudnik
	
return 0;
end

declare @w int 
exec @w=p2 @otd='otd2', @y =2023
drop procedure p2



--триггер 2
create table Audit(
	id_s int identity(1,1) primary key,
	fio nvarchar(50),
	dateN date not null,
	otd nvarchar(50),
	zp int
);
go
create trigger t2 on Sotrudnik after delete 
as begin
	insert into Audit(Audit.fio,Audit.dateN) select deleted.fio,deleted.dateN from deleted
return;
end

-- тестики процедур
/*1.Написать процедуру для выбора всех сотрудников с определенной должностью и отделом, чьи отпуска начинаются в ближайшие 2 месяца. 
Параметры: должность, отдел.
*/
create procedure p3 @o nvarchar(20)
as 
begin
	begin try
	select * from 
	Sotrudnik q inner join Otpusk w
	on q.id_s=w.id_s
	where q.otd=@o
	and datediff(m, getdate(), w.dateS)<2
	end try

	begin catch
		print error_number()
		print error_message()
		print error_line()
	end catch
return 0;
end

declare @q int 
exec @q = p3 @o='otd2'
drop procedure p3



/*2. Написать процедуру для выбора количества дней, которые сотрудник провел на отпуске в течение года. 
Параметры: код сотрудника, год.*/

create procedure p4 @y int, @id int
as 
begin
	begin try
		select q.id_s, q.fio as ФИО, datediff(d, w.dateS, w.dateF) as Дни from 
		Sotrudnik q inner join Otpusk w
		on q.id_s=w.id_s
		and year(w.dateS) = @y

		select q.id_s, q.fio as ФИО, datediff(d, w.dateS, w.dateF) as Дни from 
		Sotrudnik q inner join Otpusk w
		on q.id_s=w.id_s
		and year(w.dateS) = @y
		and q.id_s=@id
	end try

	begin catch
		print error_number()
		print error_message()
		print error_line()
		print error_procedure()
		print error_state()
	end catch
return 0;
end

declare @e int
exec @e=p4 @y=2023,@id=3
drop procedure p4

/*
3. Написать процедуру для расчета средней зарплаты по отделам. 
Параметры: год. 
Процедура должна вычислять сумму зарплат по каждому отделу за указанный год, делить ее на количество сотрудников в отделе и выводить результат на экран.
*/

create procedure p5 @y int
as begin
	begin try
		select q.otd, avg(q.zp) from Sotrudnik q
		where year(q.dateN)<=@y
		group by q.otd
	end try

	begin catch
		print error_number()
		print error_message()
		print error_line()
	end catch

return 1;
end

declare @i int
exec @i=p5 @y=2023
drop procedure p5

/*
2. Написать процедуру для удаления всех отпусков, которые закончились больше 3 месяцев назад. 
Параметры: отдел. Процедура должна удалить все отпуска сотрудников данного отдела, которые закончились более 3 месяцев назад.
*/
create procedure p6 @o nvarchar(20)
as
begin
	begin try
		delete q
		from Otpusk q inner join Sotrudnik w
		on q.id_s=w.id_s
		where datediff(m,q.dateF,getdate())>3
		and q.id_ot=@o
	end try

	begin catch
		print error_number()
		print error_message()
	end catch

return 0;
end

select * from Otpusk
go
declare @e int
exec @e=p6 @o='otd2'

/*
Написать процедуру для выбора сотрудников, чьи отпуска пересекаются с указанным диапазоном дат. 
Параметры: дата начала, дата окончания. 
Процедура должна выбрать все отпуска, которые пересекаются с указанным диапазоном дат, выбрать уникальных сотрудников из этих отпусков и вывести результат на экран.
*/
create procedure p7 @s date,@f date
as begin
	begin try

	if @f<@s
		raiserror('неверный диапазон', 11,1)

		select q.fio, w.dateS, w.dateF from
		Sotrudnik q inner join Otpusk w
		on q.id_s=w.id_s
		where w.dateS>@s and w.dateF<@f
	end try

	begin catch
		print error_number()
		print error_message()
		print error_line()
	end catch

return 0;
end

declare @i int 
exec @i = p7 @s='01/01/2020', @f='01/01/2024'
drop procedure p7

/*
Написать процедуру для выбора всех сотрудников, которые не брали отпуск в течение последних 6 месяцев и имеют зарплату выше средней зарплаты по всем отделам. 
Параметры: год. 
*/
create procedure p8 @t int
as begin 
	select q.fio, q.zp from 
	Sotrudnik q 
	where 
	--q.zp>avg(zp)
	year(q.dateN)<@t and
	not exists(select * from Otpusk w 
	where w.id_s=q.id_s or datediff(m,w.dateF, getdate())<6)
	group by q.fio, q.zp
	having q.zp>avg(zp)

return 
end
drop procedure p8

declare @i int
exec @i=p8 @t=2023

--тестим триггеры
/*
1. Создайте триггер, который при изменении зарплаты сотрудника на 30% и более, записывает данные о изменении в таблицу 
ИзмененияЗарплаты (КодИзменения, КодСотрудника, ДатаИзменения, СтараяЗарплата, НоваяЗарплата).
*/
go
create trigger t1 on Sotrudnik after update
as begin
	declare @old int, @new int
	set @old = (select zp from deleted)
	set @new = (select zp from inserted)
	if ((@new-@old)/@old)>0.3
	begin
		insert into Audit(dateN, fio, zp) select dateN, fio, zp from inserted
		select * from Audit
	end
return;
end



insert into Sotrudnik values('test','23/02/2021','otd1',1000)
update Sotrudnik set zp=2000 where fio='test'

drop trigger t1

/*
2. Создайте триггер, который при добавлении нового отпуска, проверяет, не пересекается ли он по времени с другими отпусками этого же сотрудника,
и если пересекается, отменяет добавление нового отпуска.
*/
go
create trigger t1 on Otpusk instead of insert
as begin
	declare @ds date, @df date, @c int, @id int
	set @ds = (select dateS from inserted)
	set @df = (select dateF from inserted)
	set @id = (select id_s from inserted)
if exists(select *
	from Otpusk
	where dateS>=@ds 
	and dateF<=@df)
	begin 
		raiserror('Низя',11,1)
		rollback tran
	end
	else
		begin
		insert into Otpusk (id_s,dateS,dateF,ozp) select id_s,dateS,dateF,ozp from inserted
		end
return;
end


insert into Otpusk values(10,'2010-04-22', '2010-05-21','trigg',180)	
select * from Otpusk

--тоже самое но другим способом
go
create trigger t1 on Otpusk after insert, update
as begin
	declare @ds date, @df date, @id int
	set @ds = (select dateS from inserted)
	set @df = (select dateF from inserted)
	set @id = (select id_ot from inserted)

	if exists(select * from Otpusk
	where @ds=dates and @df=dateF)
	begin
		print 'Вам низя в отпуск'
		delete from Otpusk where id_ot=@id
		raiserror ('НИЗЯ', 11,1)
	end
return;
end
insert into Otpusk values(9,'2010-04-22', '2010-05-21','ter',111)	
drop trigger t1

-----------------------------------------------------------------------------
-----------------------------------------------------------------------------
-- функции
-----------------------------------------------------------------------------
-----------------------------------------------------------------------------

--1) Написать функцию, которая принимает на вход дату и отдел, а возвращает количество сотрудников, 
--которые работают в этом отделе в указанный период времени, и количество дней, которые они провели в отпуске за этот период.
go
drop function f1
go
create function f1(@d date, @o nvarchar(20)) returns int
as begin
	declare @rc int, @rc2 int
	set @rc = (select count(*) from Sotrudnik where dateN<@d and otd=@o)
return @rc;
end

declare @f int
set @f = dbo.f1('2023-05-23', 'otd2')
print cast(@f as varchar(20))


select distinct otd, dbo.f1('2023-05-23', otd) as qqqqq from Sotrudnik

--такая же но табличная функция
create function f1 (@d date, @o nvarchar(20)) returns table
as return
	select q.id_s, q.fio, q.otd, q.dateN, sum(datediff(d, w.dateS,w.dateF)) [дней отпуска]
	from Sotrudnik q inner join Otpusk w
	on q.id_s=w.id_s
	where q.dateN<@d and q.otd = @o
	group by q.id_s, q.fio, q.otd, q.dateN

	select * from dbo.f1('2023-05-25','otd2')

	select * from Sotrudnik

	--4) Написать функцию, которая принимает на вход год и отдел, а возвращает среднюю зарплату по этому отделу за указанный год, 
	--а также максимальную и минимальную зарплаты в этом отделе за этот же период.
	go
	create function f2 (@y int,@o nvarchar(20)) returns table
	as return
		select otd, avg(zp)[средняя за], min(zp)[min],max(zp)[max] from Sotrudnik
		where 
		year(dateN)<@y and otd=isnull(@o,otd)
		group by otd

drop function f2

select * from dbo.f2(2023, 'otd2')
select * from dbo.f2(2023, null)

select * from Sotrudnik

--курсоры в процедуре
create procedure pk @o nvarchar(20)
as begin
	declare @f nvarchar(20), @r nvarchar(300)
	set @r = 'Список сотредников: '

	declare cur cursor local for select fio from Sotrudnik where otd=@o
	open cur
	fetch cur into @f
	while @@FETCH_STATUS=0
	begin
		set @r+=@f + ', '
		fetch cur into @f
	end
	print  @r
	close cur
return 0;
end

declare @i int 
exec @i=pk @o='otd2'
drop procedure pk
