create database Ex

create table Sotrudnik(
	id_s int identity(1,1) primary key,
	fio nvarchar(50),
	dateN date not null,
	dol nvarchar(20),
	otd nvarchar(20),
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

create table Audit(
	id_s int identity(1,1) primary key,
	fio nvarchar(50),
	dateN date not null,
	dol nvarchar(20),
	otd nvarchar(20),
	zp int
);

insert into Sotrudnik values ('Гурина Кристина', '2019-04-05','директор','otd1', 1500)
insert into Sotrudnik values ('Авдеева Вера', '2019-05-05','зам директора','otd1', 1500),
							 ('Коктыш Евгения', '2019-05-05','секретарь','otd1', 1500),
							  ('fio1', '2019-05-05','работник','otd1', 1300), 
							  ('fio2', '2020-05-25','работник','otd1', 1300), 
							  ('fio3', '2021-05-05','работник','otd1', 1100),
							  ('fio4', '2021-05-05','работник','otd2', 1600),
							  ('fio5', '2022-05-05','работник','otd2', 1600),
							  ('fio6', '2021-05-05','работник','otd2', 1600),
							  ('fio7', '2022-05-05','работник','otd2', 1600),
							  ('fio8', '2021-05-05','работник','otd2', 1600)

insert into Otpusk values(1,'2020-05-04', '2020-06-04','оплачиваемый',5000),
						(2,'2019-05-25', '2019-06-25','оплачиваемый',450),
						(3,'2019-05-25', '2019-06-25','за свой счёт',300),
						(7,'2023-04-25', '2023-04-30','за свой счёт',400),
						(8,'2023-03-25', '2023-04-25','за свой счёт',200)

select * from Sotrudnik
select * from Otpusk

go
create procedure p1 
as begin 
	begin try
	--1
	 if exists (select * from Sotrudnik q inner join Otpusk w
		on q.id_s=w.id_s
		where q.zp<w.ozp)
	begin
		select * from Sotrudnik q inner join Otpusk w
			on q.id_s=w.id_s
			where q.zp<w.ozp
		raiserror('Отпускные не могут быть больше зарплаты',11,1)	
	end
	--2
	if exists (select * from Otpusk
		where dateF<dateS)
	begin
		select * from Otpusk
			where dateF<dateS
		raiserror('Отпуск не может закончится до того как начался',11,1)
	end

	--

		select * from Sotrudnik

		delete q from Sotrudnik q 
		where not exists (
		select * from Otpusk w 
		where q.id_s=w.id_s)
--3
if not exists (select * from Sotrudnik)
	begin
		raiserror('Усп а таких нет',11,1)
	end


		select * from Sotrudnik
		
	end try

	begin catch
		print 'Номер ошибки ' + cast(error_number() as nvarchar (6))
		print 'Сообщение ошибки ' + error_message()
		print 'Строка ошибки ' + cast(error_line() as nvarchar (6))
		if error_procedure() is not null
			print 'Процедура ошибки ' + error_procedure()
		print 'Метка ошибки ' + cast(error_state() as nvarchar (6))
	end catch

return 0;
end

--
insert into Sotrudnik values ('без отпуска', '2019-04-05','директор','otd1', 1500)
--
declare @i int
exec @i = p1

--тестим ошибочки
--1
insert into Otpusk values(1,'2020-05-04', '2020-06-04','оплачиваемый',5000)
delete from Otpusk where ozp=5000
--2
insert into Otpusk values(1,'2020-05-04', '2020-01-04','оплачиваемый',500)
delete from Otpusk where id_ot=7

drop procedure p1

-----------------------------
go
create trigger t1 on Sotrudnik after delete
as begin
	insert into Audit (fio,dateN,dol,otd,zp) select fio,dateN,dol,otd,zp from deleted
	print 'данные скопированы'
return;
end

select * from Audit
insert into Sotrudnik values ('todel', '2019-04-05','директор','otd1', 1500)
delete from Sotrudnik where fio='todel'
select * from Audit


drop trigger t1



--
select * from Sotrudnik q inner join Otpusk w
			on q.id_s=w.id_s
			where q.zp<w.ozp

			select * from Otpusk
	 where dateF<dateS
	 update Otpusk set dateF = '2023-05-25' where dateF = '2019-04-25' 



	 		--2
	if exists (select * from Otpusk
		where datediff(d,dateS,dateF)>60)
	begin
		select * from Otpusk
			where datediff(d,dateS,dateF)>60
		raiserror('Отпукс не может быть больше 60 дней',11,1)
	end
