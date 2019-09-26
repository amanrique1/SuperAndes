-- Creaaci�n de la tabla PROMOCION y especificaci�n de sus restricciones
---CHECK (tipoPromocion IN ('pagueXlleveYunidades','xPorcentajeDescuento','pagueXlleveYcantidad','pagueXlleveSiguienteConYPorcentajeDescuento','pagueXporProductosPromocion')),

INSERT INTO PROMOCION(idpromocion,x,y,fechaInicial,fechaFinal,tipoPromocion,descripcion)
        VALUES(1,1,2,'31-10-2018','31-12-2018','pagueXlleveYunidades','Promoci�n 2x1');
 
SELECT * FROM PROMOCION;

            
-- Creaaci�n de la tabla COMPRADORES y especificaci�n de sus restricciones
INSERT INTO COMPRADORES (identificador,nombre,correoElectronico,puntos)
        VALUES('10203552014','Juan Manrique','jmanrique@uniandes.edu.co',0.0);

SELECT * FROM COMPRADORES;
 
       
-- Creaaci�n de la tabla EMPRESAS y especificaci�n de sus restricciones
INSERT INTO EMPRESAS (NIT,direccion)
        VALUES('123-343-3535','Cra. 5 #122-65');

SELECT * FROM EMPRESAS;


-- Creaaci�n de la tabla PERSONAS y especificaci�n de sus restricciones
INSERT INTO PERSONAS (cedula)
        VALUES('10203552014');

SELECT * FROM PERSONAS;



-- Creaaci�n de la tabla CATEGORIA y especificaci�n de sus restricciones
INSERT INTO CATEGORIA (nombreCategoria,perecedero)
        VALUES('Panaderia','true');

SELECT * FROM CATEGORIA;
        
-- Creaaci�n de la tabla TIPO PRODUCTOS y especificaci�n de sus restricciones
INSERT INTO TIPOPRODUCTO (nombreTipo,metodoAlmacenamiento,nombreCategoria)
        VALUES('Croissant','Seco','Panaderia');

SELECT * FROM TIPOPRODUCTO;
        

-- Creaaci�n de la tabla PRODUCTOS  y especificaci�n de sus restricciones
---   (unidadVolumen IN ('ml','l')),(unidadPeso IN ('g','kg','ton'))

INSERT INTO PRODUCTOS(codigoBarras,nombre,marca,presentacion,unidadPeso,unidadVolumen,cantidadPeso,cantidadVolumen,tipoProducto)
        VALUES('f2f2f2','Croissant Relleno De Chocolate','Bimbo','Paquete x10','g','ml','500','1','Croissant');

SELECT * FROM PRODUCTOS;
    
-- Creaaci�n de la tabla SUCURSAL y especificaci�n de sus restricciones
INSERT INTO SUCURSAL (idSucursal,nombre,ciudad,direccion)
        VALUES(5,'Mazuren','Bogota','Calle 155#80-55');

SELECT * FROM SUCURSAL;
   


-- Creaaci�n de la tabla PRODUCTOSSUCURSAL y especificaci�n de sus restricciones
INSERT INTO PRODUCTOSSUCURSAL (idProductoSucursal,idSucursal,precio,codigoProducto)
        VALUES('5-f0f0f0','5','32500','f0f0f0');
INSERT INTO PRODUCTOSSUCURSAL (idProductoSucursal,idSucursal,precio,codigoProducto)
        VALUES('5-f1f1f1','5','1900','f1f1f1');
INSERT INTO PRODUCTOSSUCURSAL (idProductoSucursal,idSucursal,precio,codigoProducto)
        VALUES('5-f2f2f2','5','11000','f2f2f2');
INSERT INTO PRODUCTOSSUCURSAL (idProductoSucursal,idSucursal,precio,codigoProducto)
        VALUES('5-f2f2f3','5','2100','f2f2f3');
INSERT INTO PRODUCTOSSUCURSAL (idProductoSucursal,idSucursal,precio,codigoProducto)
        VALUES('5-f1f1f2','5','53000','f1f1f2');
INSERT INTO PRODUCTOSSUCURSAL (idProductoSucursal,idSucursal,precio,codigoProducto)
        VALUES('5-f1f1f3','5','47000','f1f1f3');
INSERT INTO PRODUCTOSSUCURSAL (idProductoSucursal,idSucursal,precio,codigoProducto)
        VALUES('5-f1f1f4','5','5800','f1f1f4');
INSERT INTO PRODUCTOSSUCURSAL (idProductoSucursal,idSucursal,precio,codigoProducto)
        VALUES('5-f1f1f5','5','12000','f1f1f5');
INSERT INTO PRODUCTOSSUCURSAL (idProductoSucursal,idSucursal,precio,codigoProducto)
        VALUES('5-f0f0f1','5','32500','f0f0f1');
INSERT INTO PRODUCTOSSUCURSAL (idProductoSucursal,idSucursal,precio,codigoProducto)
        VALUES('5-f0f0f2','5','32200','f0f0f2');
INSERT INTO PRODUCTOSSUCURSAL (idProductoSucursal,idSucursal,precio,codigoProducto)
        VALUES('5-f0f0f3','5','29200','f0f0f3');
INSERT INTO PRODUCTOSSUCURSAL (idProductoSucursal,idSucursal,precio,codigoProducto)
        VALUES('5-f0f0f4','5','26500','f0f0f4'); 
INSERT INTO PRODUCTOSSUCURSAL (idProductoSucursal,idSucursal,precio,codigoProducto)
        VALUES('5-f0f0f5','5','27800','f0f0f5');
    
    
SELECT * FROM PRODUCTOSSUCURSAL; 

-- Creaaci�n de la tabla LOTE y especificaci�n de sus restricciones
INSERT INTO LOTE (codigoProducto,fechaVencimiento,cantidadProductos)
        VALUES('f1f1f1','31-12-2500',100);

SELECT * FROM LOTE;

-- Creaaci�n de la tabla FACTURAS y especificaci�n de sus restricciones
INSERT INTO FACTURASCOMPRADOR (numero,fecha,idComprador,idSucursal)
        VALUES(5,'28-10-2018','512-238-1547','5');
        
   
SELECT * FROM FACTURASCOMPRADOR;

-- Creaaci�n de la tabla ESTANTE y especificaci�n de sus restricciones
INSERT INTO ESTANTE (idEstante,idSucursal,unidadPeso,unidadVolumen,capacidadPeso,capacidadVolumen,nivelReOrden,tipoProducto)
        VALUES(1,1,'kg','l',200,200,30,'Aguardiente');

INSERT INTO ESTANTE (idEstante,idSucursal,unidadPeso,unidadVolumen,capacidadPeso,capacidadVolumen,nivelReOrden,tipoProducto)
        VALUES(2,1,'g','ml',2000,2000,500,'Cerveza');

INSERT INTO ESTANTE (idEstante,idSucursal,unidadPeso,unidadVolumen,capacidadPeso,capacidadVolumen,nivelReOrden,tipoProducto)
        VALUES(3,1,'g','ml',1500,1500,600,'Croissant');

SELECT * FROM ESTANTE;


-- Creaaci�n de la tabla PRODUCTOSESTANTE y especificaci�n de sus restricciones
INSERT INTO PRODUCTOSESTANTE (idEstante,cantidad,idProductoSucursal)
        VALUES(1,30,'1-f0f0f0');
INSERT INTO PRODUCTOSESTANTE (idEstante,cantidad,idProductoSucursal)
        VALUES(1,30,'1-f0f0f1');
INSERT INTO PRODUCTOSESTANTE (idEstante,cantidad,idProductoSucursal)
        VALUES(1,30,'1-f0f0f2');
INSERT INTO PRODUCTOSESTANTE (idEstante,cantidad,idProductoSucursal)
        VALUES(1,30,'1-f0f0f3');
INSERT INTO PRODUCTOSESTANTE (idEstante,cantidad,idProductoSucursal)
        VALUES(1,30,'1-f0f0f4');
INSERT INTO PRODUCTOSESTANTE (idEstante,cantidad,idProductoSucursal)
        VALUES(1,30,'1-f0f0f5');

INSERT INTO PRODUCTOSESTANTE (idEstante,cantidad,idProductoSucursal)
        VALUES(14,150,'5-f1f1f1');
INSERT INTO PRODUCTOSESTANTE (idEstante,cantidad,idProductoSucursal)
        VALUES(14,50,'5-f1f1f2');
INSERT INTO PRODUCTOSESTANTE (idEstante,cantidad,idProductoSucursal)
        VALUES(14,50,'5-f1f1f3');
INSERT INTO PRODUCTOSESTANTE (idEstante,cantidad,idProductoSucursal)
        VALUES(14,100,'5-f1f1f4');
INSERT INTO PRODUCTOSESTANTE (idEstante,cantidad,idProductoSucursal)
        VALUES(14,100,'5-f1f1f5');
        
INSERT INTO PRODUCTOSESTANTE (idEstante,cantidad,idProductoSucursal)
        VALUES(15,20,'5-f2f2f2');
INSERT INTO PRODUCTOSESTANTE (idEstante,cantidad,idProductoSucursal)
        VALUES(15,40,'5-f2f2f3');

SELECT * FROM PRODUCTOSESTANTE;
    
-- Creaaci�n de la tabla PRODUCTOSVENDIDOS y especificaci�n de sus restricciones
INSERT INTO PRODUCTOSVENDIDOS (noFactura,cantidadVendida,idProductoSucursal)
        VALUES(3,10,'5-f0f0f5');
SELECT * FROM  PRODUCTOSVENDIDOS;

        
-- Creaaci�n de la tabla BODEGAS y especificaci�n de sus restricciones
CREATE TABLE BODEGAS
  INSERT INTO BODEGAS (idBodega,idSucursal,unidadPeso,unidadVolumen,capacidadPeso,capacidadVolumen,nivelReOrden,tipoProducto)
        VALUES(1,1,'kg','l',1000,1000,100,'Aguardiente');
INSERT INTO BODEGAS (idBodega,idSucursal,unidadPeso,unidadVolumen,capacidadPeso,capacidadVolumen,nivelReOrden,tipoProducto)
        VALUES(2,1,'g','ml',1000000,1000000,200000,'Cerveza');
INSERT INTO BODEGAS (idBodega,idSucursal,unidadPeso,unidadVolumen,capacidadPeso,capacidadVolumen,nivelReOrden,tipoProducto)
        VALUES(3,1,'g','ml',150000,150000,10000,'Croissant');

SELECT * FROM BODEGAS;


    

-- Creaaci�n de la tabla PRODUCTOSBODEGA y especificaci�n de sus restricciones
INSERT INTO productosbodega (idbodega,cantidad,idProductoSucursal)
        VALUES(1,150,'1-f0f0f0');
INSERT INTO productosbodega (idbodega,cantidad,idProductoSucursal)
        VALUES(1,150,'1-f0f0f1');
INSERT INTO productosbodega (idbodega,cantidad,idProductoSucursal)
        VALUES(1,150,'1-f0f0f2');
INSERT INTO productosbodega (idbodega,cantidad,idProductoSucursal)
        VALUES(1,150,'1-f0f0f3');
INSERT INTO productosbodega (idbodega,cantidad,idProductoSucursal)
        VALUES(1,150,'1-f0f0f4');
INSERT INTO productosbodega (idbodega,cantidad,idProductoSucursal)
        VALUES(1,150,'1-f0f0f5');

INSERT INTO PRODUCTOSBODEGA  (idbodega,cantidad,idProductoSucursal)
        VALUES(14,700,'5-f1f1f1');
INSERT INTO PRODUCTOSBODEGA  (idbodega,cantidad,idProductoSucursal)
        VALUES(14,70,'5-f1f1f2');
INSERT INTO PRODUCTOSBODEGA  (idbodega,cantidad,idProductoSucursal)
        VALUES(14,100,'5-f1f1f3');
INSERT INTO PRODUCTOSBODEGA  (idbodega,cantidad,idProductoSucursal)
        VALUES(14,400,'5-f1f1f4');
INSERT INTO PRODUCTOSBODEGA  (idbodega,cantidad,idProductoSucursal)
        VALUES(14,200,'5-f1f1f5');
        
INSERT INTO PRODUCTOSBODEGA  (idbodega,cantidad,idProductoSucursal)
        VALUES(15,100,'5-f2f2f2');
INSERT INTO PRODUCTOSBODEGA  (idbodega,cantidad,idProductoSucursal)
        VALUES(15,120,'5-f2f2f3');

SELECT * FROM productosbodega;


-- Creaaci�n de la tabla PROMOCIONESVENDIDAS y especificaci�n de sus restricciones
CREATE TABLE PROMOCIONESVENDIDAS
    (noFactura NUMBER, 
	cantidadVendida NUMBER NOT NULL,
    idPromocion NUMBER,
    CONSTRAINT CK_CANTPOS_F_PROMOSVEND 
        CHECK (cantidadVendida >0), 
    CONSTRAINT A_PROMOCIONESVENDIDAS_PK 
        PRIMARY KEY (noFactura,idPromocion));
    
ALTER TABLE PROMOCIONESVENDIDAS
ADD  CONSTRAINT FK_FACT_F_PROMOSVEND
         FOREIGN KEY (noFactura)
         REFERENCES FACTURASCOMPRADOR(numero)
ENABLE; 

ALTER TABLE PROMOCIONESVENDIDAS
ADD CONSTRAINT FK_PROMO_F_PROMOSVEND
         FOREIGN KEY (idPromocion)
         REFERENCES PROMOCION(idPromocion)
ENABLE;
-- Creaaci�n de la tabla PROVEEDOR y especificaci�n de sus restricciones
CREATE TABLE PROVEEDOR
    (nit VARCHAR2(255 BYTE),
    nombre VARCHAR2(255 BYTE)NOT NULL,
    correoElectronico VARCHAR2(255 BYTE)NOT NULL, 
	telefono VARCHAR2(255 BYTE) NOT NULL,
    CONSTRAINT CK_FORMCORREO_F_PROVED
        CHECK (correoElectronico LIKE '%@%'),
    CONSTRAINT A_PROVEEDOR_PK 
        PRIMARY KEY (nit));
    
-- Creaaci�n de la tabla PEDIDO y especificaci�n de sus restricciones
CREATE TABLE PEDIDO
   (idPedido NUMBER, 
    fechaEntregaAcordada DATE NOT NULL,
    fechaEntrega DATE,
    estado VARCHAR2(255 BYTE) NOT NULL,
    idSucursal NUMBER NOT NULL,
    idProveedor VARCHAR2(255 BYTE)NOT NULL,
    CONSTRAINT CK_ESTADO_FROM_PEDIDO
        CHECK (estado IN ('HECHO','ACEPTADO','TARDE','ENTREGADO')),
    CONSTRAINT A_PEDIDO_PK 
        PRIMARY KEY (idPedido));
        
ALTER TABLE PEDIDO
ADD  CONSTRAINT FK_SUCUR_F_PEDIDO
         FOREIGN KEY (idSucursal)
         REFERENCES SUCURSAL(idSucursal)
ENABLE;

ALTER TABLE PEDIDO
ADD  CONSTRAINT FK_PRROVEED_F_PEDID
         FOREIGN KEY (idProveedor)
         REFERENCES PROVEEDOR(nit)
ENABLE;
    

-- Creaaci�n de la tabla PRODUCTOSPROVEEDOR y especificaci�n de sus restricciones
CREATE TABLE PRODUCTOSPROVEEDOR
   (codigoProducto VARCHAR2(255 BYTE),
    idPedido NUMBER, 
	precioProveedor NUMBER NOT NULL,
    idProveedor VARCHAR2(255 BYTE)NOT NULL,
    fechaVencimiento DATE,
    calificacionCalidad DOUBLE PRECISION,
    cantidadUnidades NUMBER NOT NULL,
    CONSTRAINT CK_CALPOS_F_PRODSPROVEED
        CHECK ( calificacionCalidad>=0 AND calificacionCalidad<=5),
    CONSTRAINT CK_CANTPOS_F_PRODSPROVEED
        CHECK (cantidadUnidades >=0),
    CONSTRAINT CK_PRECIOPOS_F_PRODSPROVEED 
        CHECK (precioProveedor >=0),
    CONSTRAINT A_PRODUCTOSPROVEEDOR_PK 
        PRIMARY KEY (codigoProducto,idProveedor));

ALTER TABLE PRODUCTOSPROVEEDOR
ADD CONSTRAINT FK_PROVEED_F_PRODSPROVEED
    FOREIGN KEY (idProveedor)
    REFERENCES PROVEEDOR(nit)
ENABLE;

ALTER TABLE PRODUCTOSPROVEEDOR
ADD CONSTRAINT FK_CODPROD_F_PRODSPROVEED
    FOREIGN KEY (codigoProducto)
    REFERENCES PRODUCTOS(codigoBarras)
ENABLE;

ALTER TABLE PRODUCTOSPROVEEDOR
ADD CONSTRAINT FK_PEDIDO_F_PRODSPROVEED
    FOREIGN KEY (idPedido)
    REFERENCES PEDIDO(idPedido)
ENABLE;
  

-- Creaaci�n de la tabla CARRITOPERSONASy especificaci�n de sus restricciones
CREATE TABLE CARRITOPERSONAS
   (idComprador  VARCHAR2(255 BYTE), 
    idCarrito NUMBER,
    CONSTRAINT A_CARRITOPERSONAS_PK 
        PRIMARY KEY (idCarrito));
        
ALTER TABLE CARRITOPERSONAS
ADD CONSTRAINT FK_COMPRADOR_F_CARRITO
         FOREIGN KEY (idComprador)
         REFERENCES COMPRADORES(identificador )
ENABLE;   
   

-- Creaaci�n de la tabla CARRITOPRODUCTOS y especificaci�n de sus restricciones
CREATE TABLE CARRITOPRODUCTOS
   (idCarrito NUMBER, 
	cantidadProducto NUMBER NOT NULL,
    idProductoSucursal VARCHAR2(255 BYTE),
    CONSTRAINT CK_CANTPROD_F_CARRITO
        CHECK (cantidadProducto >0),
    CONSTRAINT A_CARRITOPROD_PK 
        PRIMARY KEY (idCarrito,idProductoSucursal));
        
ALTER TABLE CARRITOPRODUCTOS
ADD CONSTRAINT FK_PRODSUCUR_F_CARRITOPROD
     FOREIGN KEY (idProductoSucursal)
     REFERENCES PRODUCTOSSUCURSAL(idProductoSucursal)
ENABLE;

ALTER TABLE CARRITOPRODUCTOS
ADD CONSTRAINT FK_CARRITO_F_CARRITOPROD
         FOREIGN KEY (idCarrito)
         REFERENCES CARRITOPERSONAS(idCarrito )
ENABLE;   

COMMIT;