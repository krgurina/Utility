--�������� ������� ���������� (�������������, ���, ���������, ���������, �����, ��������) �
--������� (����������, �������������, ����������, �������������, ���, ���������). 
--������� ����������� ����������� �����������. 
--�������� ���������, ������� ������� ������ ���� ����������� ������ � ��������� �����, 
--��������� �� �������� � ���������. ��������� � ������������ ������, ���. ����������� ��������� ������.
--�������� �������, ������� �� ���� ��������� ���������� � ������, ���� �� �� ���������� �������.

create table Sotrudniki
(
IdS int primary key,
FullName nvarchar(50) not null,
FromD date,
Kto nvarchar(20),
Otdel nvarchar(20),
Zp money 
)
go 
create table Otpusk
(
IdO int primary key,
IdS int foreign key references Sotrudniki(IdS),
Beg date not null,
En date not null,
Typ nvarchar(10),
ZpO money 
)
go

Create proc Ex 
@fn nvarchar(50), @g date
as declare @k int = 1
begin try
select FullName, Sum(Zp  + ZpO)[������+��] from Sotrudniki inner join Otpusk on Sotrudniki.IdS = Otpusk.IdS where Sotrudniki.Otdel = @fn and Sotrudniki.FromD = @g group by FullName -- ��� ������ � �����, ���� ������ �� ������ ��� �����������, �� �����, ���� ���� ����������� ����� �������� ����, �� >=
return @k
end try
begin catch
		print '����� ������: ' + cast(error_number() as varchar(6));
		print '���������: ' + error_message();
        print '����� ������: ' + cast(error_line() as varchar(8));
		if error_procedure() is not null   
		print '��� ���������: ' + error_procedure();
		return -1;
end catch;
go
	
insert into Sotrudniki(IdS, FullName, FromD, Kto, Otdel, Zp) values
(1, 'Avdeeva V D', '23/09/2003', 'Junior', '������', 400),
(2, 'Avdeeva A D', '23/09/2003', 'Junior', '������', 400),
(3, 'Trotskaya N A', '28/03/2003', 'Junior+', '������', 450),
(4, 'Fffffff F F', '28/03/2013', 'Middle', '������', 700),
(5, 'TTTTTTT T T', '28/03/2007', 'Senior', '������', 680)

insert into Otpusk(IdO, IdS, Beg, En, Typ, ZpO) values
(1, 1, '23/09/2004', '23/10/2004', '��������', 600),
(2, 2, '23/09/2004', '23/10/2004', '��������', 400),
(3, 3, '23/09/2004', '23/10/2004', '��������', 100),
(4, 4, '01/01/2014', '01/03/2014', '���������', 1500),
(5, 5, '14/03/2008', '14/05/2008', '���������', 1100)

declare @m int
exec @m = Ex @fn = '������', @g = '2003'

drop proc ex 
go
Create trigger Otp
on Otpusk
INSTEAD of Insert
as begin
 declare @a int = (select IdO from inserted);
 declare @b int = (select IdS from inserted);
 declare @c date = (select Beg from inserted);
 declare @d date = (select En from inserted);
 declare @e nvarchar(10) = (select Typ from inserted);
 declare @f money = (select ZpO from inserted); 

 declare @datef date;
 declare @datet date;
 declare @kolvo date;

 select @datef = s.FromD  from Sotrudniki s inner join inserted i on s.IdS = i.IdS;
 select @datet = inserted.Beg from inserted;
 set @kolvo = DATEADD(MONTH, 6 , @datef);

 if @datet >= @kolvo
   insert into Otpusk(IdO, IdS, Beg, En, Typ, ZpO) values (@a, @b, @c, @d, @e, @f)
else 
 print '���� ���������, ���� � ������ ����'
end

insert into Sotrudniki(IdS, FullName, FromD, Kto, Otdel, Zp) values
(0, 'Proverochka Triggera', '23/09/2009', 'Junior', '������', 400)

insert into Otpusk(IdO, IdS, Beg, En, Typ, ZpO) values
(7, 7, '14/03/2010', '19/03/2010', '��������', 600)
delete from Otpusk where IdS = 7
select * from Otpusk
