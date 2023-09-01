create database pildoras;
show databases;
use pildoras;
create table if not exists producto(
CODIGOPRODUCTO  varchar(50) not null,
SECCION  varchar(50) not null,
NOMBREARTICULO varchar(50) not null,
PRECIO float (2) not null,
FECHA date not null,
IMPORTADO tinyint (1) not null,
PAISDEORIGEN varchar(50)
)engine=innodb;

insert into producto(CODIGOPRODUCTO,SECCION,NOMBREARTICULO,PRECIO,FECHA,IMPORTADO,PAISDEORIGEN)
                  values('AR01','FERRERTERIA','DESTORNILLADOR',6.63,'20230325',0,'ESPAÑA'),
						('AR02','CONFECCION','TRAJE CABALLERO',284.58,'20230325',1,'ITALIA'),
                        ('AR03','JUGUETERIA','COCHE TELEDRIGIDO',154.58,'20230325',1,'MARRUECOS');
                        
         alter table producto modify  CODIGOPRODUCTO VARCHAR(50) null,modify SECCION varchar(50) null ; 
         alter table producto modify NOMBREARTICULO VARCHAR(50) NULL, modify PRECIO float(2) ;
		 alter table producto modify FECHA  date null, modify IMPORTADO TINYINT (1) NULL;
		describe producto;


    
    
    
              
           