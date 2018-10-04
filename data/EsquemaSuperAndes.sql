-- Creaación de la tabla PROMOCION y especificación de sus restricciones
CREATE TABLE PROMOCION
   (idPromocion NUMBER, 
	x DOUBLE PRECISION NOT NULL,
    y DOUBLE PRECISION NOT NULL,
    fechaInicial DATE NOT NULL,
    fechaFinal DATE NOT NULL,
    tipoPromocion VARCHAR2(255 BYTE) NOT NULL,
    descripcion VARCHAR2(255 BYTE),
    CONSTRAINT CK_TIPOPROMO_F_PROMO
        CHECK (tipoPromocion IN ('pagueXlleveYunidades','xPorcentajeDescuento','pagueXlleveYcantidad','pagueXlleveSiguienteConYPorcentajeDescuento','pagueXporProductosPromocion')),
    CONSTRAINT CK_X_FROM_PROMOCION
        CHECK (x >0),
    CONSTRAINT CK_Y_FROM_PROMOCION 
        CHECK (y >0),
    CONSTRAINT A_PROMOCION_PK 
        PRIMARY KEY (idPromocion));
        
-- Creaación de la tabla COMPRADORES y especificación de sus restricciones
CREATE TABLE COMPRADORES
   (identificador VARCHAR2(255 BYTE), 
	nombre VARCHAR2(255 BYTE) NOT NULL,
	correoElectronico VARCHAR2(255 BYTE) NOT NULL,
	puntos DOUBLE PRECISION NOT NULL,
    CONSTRAINT CK_PUNTOSPOS_F_COMPRAD 
        CHECK (puntos >=0),
    CONSTRAINT CK_FORMATOCORREO_F_COMPRAD
        CHECK (correoElectronico LIKE '%@%'),
	CONSTRAINT A_COMPRADORES_PK 
        PRIMARY KEY (identificador));
        
-- Creaación de la tabla EMPRESAS y especificación de sus restricciones
CREATE TABLE EMPRESAS
   (NIT VARCHAR2(255 BYTE), 
	direccion VARCHAR2(255 BYTE),
	CONSTRAINT A_EMPRESAS_PK 
        PRIMARY KEY (NIT));
        
ALTER TABLE EMPRESAS
ADD CONSTRAINT FK_COMPRADOR_F_EMPRESAS
    FOREIGN KEY (NIT)
    REFERENCES COMPRADORES(identificador)
ENABLE;
        
-- Creaación de la tabla CLIENTES y especificación de sus restricciones
CREATE TABLE PERSONAS
   (cedula VARCHAR2(255 BYTE),
	CONSTRAINT A_PERSONAS_PK 
        PRIMARY KEY (cedula));

ALTER TABLE PERSONAS
ADD CONSTRAINT FK_COMPRADOR_F_PERSONAS
    FOREIGN KEY (cedula)
    REFERENCES COMPRADORES(identificador)
ENABLE;



-- Creaación de la tabla CATEGORIA y especificación de sus restricciones
CREATE TABLE CATEGORIA
   (nombreCategoria VARCHAR2(255 BYTE), 
    perecedero VARCHAR2(255 BYTE) NOT NULL,
    CONSTRAINT CK_PERECEDERO_F_CATEGORIA
        CHECK (perecedero IN ('true','false')),
    CONSTRAINT A_CATEGORIA_PK 
        PRIMARY KEY (nombreCategoria));
        
-- Creaación de la tabla TIPO PRODUCTOS y especificación de sus restricciones
CREATE TABLE TIPOPRODUCTO
   (nombreTipo VARCHAR2(255 BYTE), 
    metodoAlmacenamiento VARCHAR2(255 BYTE) NOT NULL,
    nombreCategoria VARCHAR2(255 BYTE) NOT NULL,
    CONSTRAINT CK_METODALMAC_F_TPRODUCTO
        CHECK (metodoAlmacenamiento IN ('Refrigeramiento')),
    CONSTRAINT A_TIPOPRODUCTO_PK 
        PRIMARY KEY (nombreTipo));
        
ALTER TABLE TIPOPRODUCTO
ADD CONSTRAINT FK_CATEGORIA_F_TPRODUCTO
        FOREIGN KEY (nombreCategoria)
        REFERENCES CATEGORIA(nombreCategoria)
ENABLE;    

-- Creaación de la tabla PRODUCTOS  y especificación de sus restricciones
CREATE TABLE PRODUCTOS
   (codigoBarras VARCHAR2(255 BYTE), 
	nombre VARCHAR2(255 BYTE) NOT NULL,
    marca VARCHAR2(255 BYTE) NOT NULL,
    presentacion VARCHAR2(255 BYTE) NOT NULL,
    unidadPeso VARCHAR2(255 BYTE) NOT NULL,
    unidadVolumen VARCHAR2(255 BYTE) NOT NULL,
    cantidadPeso DOUBLE PRECISION NOT NULL,
    cantidadVolumen DOUBLE PRECISION NOT NULL,
    tipoProducto VARCHAR2(255 BYTE) NOT NULL,
    CONSTRAINT CK_PESOPOS_F_PRODUCTOS
        CHECK (cantidadPeso >0),
    CONSTRAINT CK_VOLUMENPOS_F_PRODUCTOS
        CHECK (cantidadVolumen >0),
    CONSTRAINT CK_UNIDVOL_F_PRODUCTOS
        CHECK (unidadVolumen IN ('ml','l')),
    CONSTRAINT CK_UNIDPESO_F_PRODUCTOS
        CHECK (unidadPeso IN ('g','kg','ton')),
	CONSTRAINT A_PRODUCTOS_PK 
        PRIMARY KEY (codigoBarras));

ALTER TABLE PRODUCTOS
ADD CONSTRAINT FK_TIPOPRODUCTO_FROM_PRODUCTOS
    FOREIGN KEY (tipoProducto)
    REFERENCES  TIPOPRODUCTO(nombreTipo)
ENABLE;


    
-- Creaación de la tabla SUCURSAL y especificación de sus restricciones
CREATE TABLE SUCURSAL
   (idSucursal NUMBER, 
    nombre VARCHAR2(255 BYTE) NOT NULL,
    ciudad VARCHAR2(255 BYTE) NOT NULL,
    direccion VARCHAR2(255 BYTE) NOT NULL,
    CONSTRAINT ND_NOMBRE_F_SUCURSAL
        UNIQUE (nombre),
    CONSTRAINT A_SUCURSAL_PK 
        PRIMARY KEY (idSucursal));
   


-- Creaación de la tabla PRODUCTOS VENDIDOS y especificación de sus restricciones
CREATE TABLE PRODUCTOSSUCURSAL
   (idProductoSucursal VARCHAR2(255 BYTE),
    idSucursal NUMBER NOT NULL, 
	precio NUMBER NOT NULL,
    codigoProducto VARCHAR2(255 BYTE) NOT NULL,
    idPromocion NUMBER,
    CONSTRAINT CK_PRECIOPOS_F_PRODSSUCUR
        CHECK (precio >=0),
    CONSTRAINT A_PRODUCTOSSUCURSAL_PK 
        PRIMARY KEY (idProductoSucursal));
    
ALTER TABLE PRODUCTOSSUCURSAL
ADD CONSTRAINT FK_CODPROD_F_PRODSSUCUR
            FOREIGN KEY (codigoProducto)   
            REFERENCES PRODUCTOS(codigoBarras)
ENABLE;

ALTER TABLE PRODUCTOSSUCURSAL
ADD CONSTRAINT FK_SUCUR_F_PRODSSUCUR
            FOREIGN KEY (idSucursal)
            REFERENCES SUCURSAL(idSucursal)
ENABLE;

ALTER TABLE PRODUCTOSSUCURSAL
ADD CONSTRAINT FK_PROMO_F_PRODSSUCUR
            FOREIGN KEY (idPromocion)
            REFERENCES PROMOCION(idPromocion)
ENABLE;
    
    
    
-- Creaación de la tabla LOTE y especificación de sus restricciones
CREATE TABLE LOTE
   (codigoProducto VARCHAR2(255 BYTE), 
    fechaVencimiento DATE,
    cantidadProductos NUMBER NOT NULL,
    CONSTRAINT CK_CANTPRODSPOS_F_LOTE
        CHECK (cantidadProductos >=0),
    CONSTRAINT A_LOTE_PK 
        PRIMARY KEY (codigoProducto,fechaVencimiento));

ALTER TABLE LOTE
ADD CONSTRAINT FK_CODPROD_F_LOTE
         FOREIGN KEY (codigoProducto)
         REFERENCES PRODUCTOS(codigoBarras)
ENABLE;

-- Creaación de la tabla FACTURAS y especificación de sus restricciones
CREATE TABLE FACTURASCOMPRADOR
   (numero NUMBER, 
	fecha DATE NOT NULL,
	idComprador VARCHAR2(255 BYTE) NOT NULL,
    idSucursal NUMBER NOT NULL,   
	CONSTRAINT A_FACTURASCOMPRADOR_PK 
        PRIMARY KEY (numero));
    
    ALTER TABLE FACTURASCOMPRADOR
ADD  CONSTRAINT FK_SUCURSAL_F_FACTSCOMPR
         FOREIGN KEY (idSucursal)
         REFERENCES SUCURSAL(idSucursal)
ENABLE;

ALTER TABLE FACTURASCOMPRADOR
ADD CONSTRAINT FK_CLIENT_F_FACTSCOMPR
    FOREIGN KEY (idComprador)
    REFERENCES COMPRADORES(identificador)
ENABLE;

-- Creaación de la tabla ESTANTE y especificación de sus restricciones
CREATE TABLE ESTANTE
   (idEstante NUMBER, 
    idSucursal NUMBER NOT NULL,
    unidadPeso VARCHAR2(255 BYTE) NOT NULL,
    unidadVolumen VARCHAR2(255 BYTE) NOT NULL,
    capacidadPeso DOUBLE PRECISION NOT NULL,
    capacidadVolumen DOUBLE PRECISION NOT NULL,
    nivelReOrden DOUBLE PRECISION NOT NULL,
    tipoProducto VARCHAR2(255 BYTE) NOT NULL,
    CONSTRAINT CK_PESOPO_F_ESTANTE
        CHECK (capacidadPeso >=0),
    CONSTRAINT CK_VOLUMENPOS_F_ESTANTE 
        CHECK (capacidadVolumen >=0),
    CONSTRAINT CK_UNIDVOL_F_ESTANTE 
        CHECK (unidadVolumen IN ('ml','l')),
    CONSTRAINT CK_UNIDPESO_F_ESTANTE
        CHECK (unidadPeso IN ('g','kg','ton')),
    CONSTRAINT A_ESTANTE_PK 
        PRIMARY KEY (idEstante));
    
ALTER TABLE ESTANTE
ADD CONSTRAINT FK_TIPOPROD_F_ESTANTE
         FOREIGN KEY (tipoProducto)
         REFERENCES TIPOPRODUCTO(nombreTipo)
ENABLE;

ALTER TABLE ESTANTE
ADD CONSTRAINT FK_SUCURSAL_F_ESTANTE
         FOREIGN KEY (idSucursal)
         REFERENCES SUCURSAL(idSucursal)
ENABLE;

-- Creaación de la tabla PRODUCTOSESTANTE y especificación de sus restricciones
CREATE TABLE PRODUCTOSESTANTE
   (idEstante NUMBER, 
	cantidad NUMBER NOT NULL,
    idProductoSucursal VARCHAR2(255 BYTE),
    CONSTRAINT CK_CANTPOS_F_PRODSESTANTE 
        CHECK (cantidad >0), 
    CONSTRAINT A_PRODUCTOSESTANTE_PK 
        PRIMARY KEY (idEstante,idProductoSucursal));
        
ALTER TABLE PRODUCTOSESTANTE
ADD CONSTRAINT FK_ESTANTE_F_PRODSESTANTE
         FOREIGN KEY (idEstante)
         REFERENCES ESTANTE(idEstante)
ENABLE;

ALTER TABLE PRODUCTOSESTANTE
ADD CONSTRAINT FK_PRODSUCUR_F_PRODSESTANTE
         FOREIGN KEY (idProductoSucursal)
         REFERENCES PRODUCTOSSUCURSAL(idProductoSucursal)
ENABLE;
    
-- Creaación de la tabla PRODUCTOSVENDIDOS y especificación de sus restricciones
CREATE TABLE PRODUCTOSVENDIDOS
   (noFactura NUMBER, 
	cantidadVendida NUMBER NOT NULL,
    idProductoSucursal VARCHAR2(255 BYTE),
    CONSTRAINT CK_CANTPOS_F_PRODSVEND
        CHECK (cantidadVendida >0), 
    CONSTRAINT A_PRODUCTOSVENDIDOS_PK
        PRIMARY KEY (noFactura,idProductoSucursal));
        
ALTER TABLE PRODUCTOSVENDIDOS
ADD  CONSTRAINT FK_FACT_F_PRODSVEND
         FOREIGN KEY (noFactura)
         REFERENCES FACTURASCOMPRADOR(numero)
ENABLE;

ALTER TABLE PRODUCTOSVENDIDOS
ADD CONSTRAINT FK_PRODSSUCUR_F_PRODSVEND
         FOREIGN KEY (idProductoSucursal)
         REFERENCES PRODUCTOSSUCURSAL(idProductoSucursal)
ENABLE;
        
-- Creaación de la tabla BODEGAS y especificación de sus restricciones
CREATE TABLE BODEGAS
   (idBodega NUMBER, 
    idSucursal NUMBER NOT NULL,
    unidadPeso VARCHAR2(255 BYTE) NOT NULL,
    unidadVolumen VARCHAR2(255 BYTE) NOT NULL,
    capacidadPeso DOUBLE PRECISION NOT NULL,
    capacidadVolumen DOUBLE PRECISION NOT NULL,
    nivelReOrden DOUBLE PRECISION NOT NULL,
    tipoProducto VARCHAR2(255 BYTE) NOT NULL,
    CONSTRAINT CK_PESOPOS_F_BODEGA
        CHECK (capacidadPeso >=0),
    CONSTRAINT CK_VOLPOS_F_BODEGA
        CHECK (capacidadVolumen >=0),
    CONSTRAINT CK_UNIDVOL_F_BODEGA 
        CHECK (unidadVolumen IN ('ml','l')),
    CONSTRAINT CK_UNIDPESO_F_BODEGA 
        CHECK (unidadPeso IN ('g','kg','ton')),
    CONSTRAINT A_BODEGAS_PK 
        PRIMARY KEY (idBodega));
        
ALTER TABLE BODEGAS
ADD CONSTRAINT FK_TIPOPROD_F_BODEGA
         FOREIGN KEY (tipoProducto)
         REFERENCES TIPOPRODUCTO(nombreTipo)
ENABLE;

ALTER TABLE BODEGAS
ADD CONSTRAINT FK_SUCUR_F_BODEGA
         FOREIGN KEY (idSucursal)
         REFERENCES SUCURSAL(idSucursal)
ENABLE;
    

-- Creaación de la tabla PRODUCTOSBODEGA y especificación de sus restricciones
CREATE TABLE PRODUCTOSBODEGA
   (idBodega NUMBER, 
	cantidad NUMBER NOT NULL,
    idProductoSucursal VARCHAR2(255 BYTE),
    CONSTRAINT CK_CANTPOS_F_PRODSBODEGA 
        CHECK (cantidad >0),
    CONSTRAINT A_PRODUCTOSBODEGA_PK 
        PRIMARY KEY (idBodega,idProductoSucursal));
        
ALTER TABLE PRODUCTOSBODEGA
ADD CONSTRAINT FK_PRODSUCUR_F_PRODSBODEGA
     FOREIGN KEY (idProductoSucursal)
     REFERENCES PRODUCTOSSUCURSAL(idProductoSucursal)
ENABLE;

ALTER TABLE PRODUCTOSBODEGA
ADD CONSTRAINT FK_BODEGA_F_PRODSBODEGA
         FOREIGN KEY (idBodega)
         REFERENCES BODEGAS(idBodega)
ENABLE;   


    

-- Creaación de la tabla PROMOCIONESVENDIDAS y especificación de sus restricciones
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
-- Creaación de la tabla PROVEEDOR y especificación de sus restricciones
CREATE TABLE PROVEEDOR
    (nit VARCHAR2(255 BYTE),
    nombre VARCHAR2(255 BYTE)NOT NULL,
    correoElectronico VARCHAR2(255 BYTE)NOT NULL, 
	telefono VARCHAR2(255 BYTE) NOT NULL,
    CONSTRAINT CK_FORMCORREO_F_PROVED
        CHECK (correoElectronico LIKE '%@%'),
    CONSTRAINT A_PROVEEDOR_PK 
        PRIMARY KEY (nit));
    
-- Creaación de la tabla PEDIDO y especificación de sus restricciones
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
    

-- Creaación de la tabla PRODUCTOSPROVEEDOR y especificación de sus restricciones
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
  

COMMIT;