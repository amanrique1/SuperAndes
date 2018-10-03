-- Creaación de la tabla EMPRESAS y especificación de sus restricciones
CREATE TABLE A_EMPRESAS
   (NIT VARCHAR2(255 BYTE), 
	direccion VARCHAR2(255 BYTE),
    CONSTRAINT FK_COMPRADOR_FROM_EMPRESAS 
        FOREIGN KEY (NIT) 
        REFERENCES A_COMPRADORES(identificador),
	CONSTRAINT A_EMPRESAS_PK 
        PRIMARY KEY (NIT));
        
-- Creaación de la tabla CLIENTES y especificación de sus restricciones
CREATE TABLE A_PERSONAS
   (cedula VARCHAR2(255 BYTE),
    CONSTRAINT FK_COMPRADOR_FROM_PERSONAS
        FOREIGN KEY (cedula)
        REFERENCES A_COMPRADORES(identificador),
	CONSTRAINT A_PERSONAS_PK 
        PRIMARY KEY (cedula));


-- Creaación de la tabla COMPRADORES y especificación de sus restricciones
CREATE TABLE A_COMPRADORES
   (identificador VARCHAR2(255 BYTE), 
	nombre VARCHAR2(255 BYTE) NOT NULL,
	correoElectronico VARCHAR2(255 BYTE) NOT NULL,
	puntos DOUBLE NOT NULL,
    CONSTRAINT CK_PUNTOSPOSITIVOS_FROM_COMPRADORES 
        CHECK (puntos >=0),
    CONSTRAINT CK_FORMATOCORREO_FROM_COMPRADORES
        CHECK (correoElectronico LIKE '%@%'),
	CONSTRAINT A_COMPRADORES_PK 
        PRIMARY KEY (identificador));
    
 
-- Creaación de la tabla PRODUCTOS  y especificación de sus restricciones
CREATE TABLE A_PRODUCTOS
   (codigoBarras NUMBER, 
	nombre VARCHAR2(255 BYTE) NOT NULL,
    marca VARCHAR2(255 BYTE) NOT NULL,
    presentacion VARCHAR2(255 BYTE) NOT NULL,
    unidadPeso VARCHAR2(255 BYTE) NOT NULL,
    unidadVolumen VARCHAR2(255 BYTE) NOT NULL,
    cantidadPeso DOUBLE NOT NULL,
    cantidadVolumen DOUBLE NOT NULL,
    tipoProducto VARCHAR2(255 BYTE) NOT NULL,
    CONSTRAINT CK_PESO_POSITIVO_FROM_PRODUCTOS
        CHECK (cantidadPeso >0),
    CONSTRAINT CK_VOLUMEN_POSITIVO_FROM_PRODUCTOS
        CHECK (cantidadVolumen >0),
    CONSTRAINT CK_UNIDADES_VOLUMEN_FROM_PRODUCTOS
        CHECK (unidadVolumen IN ('ml','l')),
    CONSTRAINT CK_UNIDADES_PESO_FROM_PRODUCTOS
        CHECK (unidadPeso IN ('g','kg','ton')),
    CONSTRAINT FK_TIPOPRODUCTO_FROM_PRODUCTOS
        FOREIGN KEY (tipoProducto)
        REFERENCES  A_TIPOPRODUCTO(nombreTipo),
	CONSTRAINT A_PRODUCTOS_PK 
        PRIMARY KEY (codigoBarras));

    
-- Creaación de la tabla TIPO PRODUCTOS y especificación de sus restricciones
CREATE TABLE A_TIPOPRODUCTO
   (nombreTipo VARCHAR2(255 BYTE), 
    metodoAlmacenamiento VARCHAR2(255 BYTE) NOT NULL,
    nombreCategoria VARCHAR2(255 BYTE) NOT NULL,
    CONSTRAINT CK_METODO_ALMACENAMIENTO_FROM_TIPOPRODUCTO
        CHECK (metodoAlmacenamiento IN ('Refrigeramiento')),
    CONSTRAINT FK_CATEGORIA_FROM_TIPOPRODUCTO
        FOREIGN KEY (nombreCategoria)
        REFERENCES A_CATEGORIA(nombreCategoria),
    CONSTRAINT A_TIPOPRODUCTO_PK 
        PRIMARY KEY (nombreTipo));
   

    
-- Creaación de la tabla CATEGORIA y especificación de sus restricciones
CREATE TABLE A_CATEGORIA
   (nombreCategoria VARCHAR2(255 BYTE), 
    perecedero VARCHAR2(255 BYTE) NOT NULL,
    CONSTRAINT CK_PERECEDERO_FROM_CATEGORIA
        CHECK (perecedero IN ('true','false')),
    CONSTRAINT A_CATEGORIA_PK 
        PRIMARY KEY (nombreCategoria));
     
    
    
-- Creaación de la tabla SUCURSAL y especificación de sus restricciones
CREATE TABLE A_SUCURSAL
   (idSucursal LONG, 
    nombre VARCHAR2(255 BYTE) NOT NULL,
    ciudad VARCHAR2(255 BYTE) NOT NULL,
    direccion VARCHAR2(255 BYTE) NOT NULL,
    CONSTRAINT ND_NOMBRE_FROM_SUCURSAL
        UNIQUE (nombre),
    CONSTRAINT A_SUCURSAL_PK 
        PRIMARY KEY (idSucursal));
   


-- Creaación de la tabla PRODUCTOS VENDIDOS y especificación de sus restricciones
CREATE TABLE A_PRODUCTOSSUCURSAL
   (idProductoSucursal LONG,
    idSucursal LONG NOT NULL, 
	precio NUMBER NOT NULL,
    cantidad NUMBER NOT NULL,
    codigoProducto VARCHAR2(255 BYTE) NOT NULL,
    idPromocion LONG,
    CONSTRAINT CK_CANTIDADPOSITIVA_FROM_PRODUCTOSSUCURSAL
        CHECK (cantidad >=0),
    CONSTRAINT CK_PRECIOPOSITIVO_FROM_PRODUCTOSSUCURSAL 
        CHECK (precio >=0),
    CONSTRAINT FK_CODIGOPRODUCTO_FROM_PRODUCTOSSUCURSAL 
            FOREIGN KEY (codigoProducto)   
            REFERENCES A_PRODUCTOS(codigoBarras),
    CONSTRAINT FK_SUCURSAL_FROM_PRODUCTOSSUCURSAL 
            FOREIGN KEY (idSucursal)
            REFERENCES A_SUCURSAL(idSucursal),
    CONSTRAINT FK_PROMOCION_FROM_PRODUCTOSSUCURSAL 
            FOREIGN KEY (idPromocion)
            REFERENCES A_PROMOCION(idPromocion),
    CONSTRAINT A_PRODUCTOSSUCURSAL_PK 
        PRIMARY KEY (idProductoSucursal));
    
-- Creaación de la tabla LOTE y especificación de sus restricciones
CREATE TABLE A_LOTE
   (codigoProducto VARCHAR2(255 BYTE), 
    fechaVencimiento DATE,
    cantidadProductos NUMBER NOT NULL,
    CONSTRAINT CK_CANTIDADPRODUCTOSPOSITIVA_FROM_LOTE
        CHECK (cantidadProductos >=0),
    CONSTRAINT FK_CODIGOPRODUCTO_FROM_LOTE
         FOREIGN KEY (codigoProducto)
         REFERENCES A_PRODUCTOS(codigoBarras),
    CONSTRAINT A_LOTE_PK 
        PRIMARY KEY (codigoProducto,fechaVencimiento));


-- Creaación de la tabla FACTURAS y especificación de sus restricciones
CREATE TABLE A_FACTURASCOMPRADOR
   (numero LONG, 
	fecha DATE NOT NULL,
	idComprador VARCHAR2(255 BYTE) NOT NULL,
    idSucursal LONG NOT NULL,
    CONSTRAINT FK_CLIENTE_FROM_FACTURASCOMPRADOR
         FOREIGN KEY (idComprador)
         REFERENCES A_COMPRADORES(identificador),
    CONSTRAINT FK_SUCURSAL_FROM_FACTURASCOMPRADOR
         FOREIGN KEY (idSucursal)
         REFERENCES A_SUCURSAL(idSucursal),
	CONSTRAINT A_FACTURASCOMPRADOR_PK 
        PRIMARY KEY (numero));
    
-- Creaación de la tabla ESTANTE y especificación de sus restricciones
CREATE TABLE A_ESTANTE
   (idEstante LONG, 
    idSucursal LONG NOT NULL,
    unidadPeso VARCHAR2(255 BYTE) NOT NULL,
    unidadVolumen VARCHAR2(255 BYTE) NOT NULL,
    capacidadPeso DOUBLE NOT NULL,
    capacidadVolumen DOUBLE NOT NULL,
    nivelReOrden DOUBLE NOT NULL,
    tipoProducto VARCHAR2(255 BYTE) NOT NULL,
    CONSTRAINT FK_SUCURSAL_FROM_ESTANTE
         FOREIGN KEY (idSucursal)
         REFERENCES A_SUCURSAL(idSucursal),
    CONSTRAINT FK_TIPOPRODUCTO_FROM_ESTANTE
         FOREIGN KEY (tipoProducto)
         REFERENCES A_TIPOPRODUCTO(nombreTipo),
    CONSTRAINT CK_PESO_POSITIVO_FROM_ESTANTE
        CHECK (capacidadPeso >=0),
    CONSTRAINT CK_VOLUMEN_POSITIVO_FROM_ESTANTE 
        CHECK (capacidadVolumen >=0),
    CONSTRAINT CK_UNIDADES_VOLUMEN_FROM_ESTANTE 
        CHECK (unidadVolumen IN ('ml','l')),
    CONSTRAINT CK_UNIDADES_PESO_FROM_ESTANTE
        CHECK (unidadPeso IN ('g','kg','ton')),
    CONSTRAINT A_ESTANTE_PK 
        PRIMARY KEY (idEstante));
    

-- Creaación de la tabla PRODUCTOSESTANTE y especificación de sus restricciones
CREATE TABLE A_PRODUCTOSESTANTE
   (idEstante LONG, 
	cantidad NUMBER NOT NULL,
    idProductoSucursal LONG,
    CONSTRAINT CK_CANTIDAD_POSITIVA_FROM_PRODUCTOSESTANTE 
        CHECK (cantidad >0),
    CONSTRAINT FK_ESTANTE_FROM_PRODUCTOSESTANTE
         FOREIGN KEY (idEstante)
         REFERENCES A_ESTANTE(idEstante),
    CONSTRAINT FK_PRODUCTOSUCURSAL_FROM_PRODUCTOSESTANTE
         FOREIGN KEY (idProductoSucursal)
         REFERENCES A_PRODUCTOSSUCURSAL(idProductoSucursal), 
    CONSTRAINT A_PRODUCTOSESTANTE_PK 
        PRIMARY KEY (idEstante,idProductoSucursal));
    
-- Creaación de la tabla PRODUCTOSVENDIDOS y especificación de sus restricciones
CREATE TABLE A_PRODUCTOSVENDIDOS
   (noFactura LONG, 
	cantidadVendida NUMBER NOT NULL,
    idProductoSucursal LONG,
    CONSTRAINT CK_CANTIDAD_POSITIVA_FROM_PRODUCTOSVENDIDOS
        CHECK (cantidadVendida >0),
    CONSTRAINT FK_FACTURA_FROM_PRODUCTOSVENDIDOS
         FOREIGN KEY (noFactura)
         REFERENCES A_FACTURASCOMPRADOR(numero),
    CONSTRAINT FK_PRODUCTOSUCURSAL_FROM_PRODUCTOSVENDIDOS
         FOREIGN KEY (idProductoSucursal)
         REFERENCES A_PRODUCTOSSUCURSAL(idProductoSucursal), 
    CONSTRAINT A_PRODUCTOSVENDIDOS_PK
        PRIMARY KEY (noFactura,idProductoSucursal));
    
-- Creaación de la tabla BODEGAS y especificación de sus restricciones
CREATE TABLE A_BODEGAS
   (idBodega LONG, 
    idSucursal LONG NOT NULL,
    unidadPeso VARCHAR2(255 BYTE) NOT NULL,
    unidadVolumen VARCHAR2(255 BYTE) NOT NULL,
    capacidadPeso DOUBLE NOT NULL,
    capacidadVolumen DOUBLE NOT NULL,
    nivelReOrden DOUBLE NOT NULL,
    tipoProducto VARCHAR2(255 BYTE) NOT NULL,
    CONSTRAINT FK_SUCURSAL_FROM_BODEGA
         FOREIGN KEY (idSucursal)
         REFERENCES A_SUCURSAL(idSucursal),
    CONSTRAINT FK_TIPOPRODUCTO_FROM_BODEGA
         FOREIGN KEY (tipoProducto)
         REFERENCES A_TIPOPRODUCTO(nombreTipo),
    CONSTRAINT CK_PESO_POSITIVO_FROM_BODEGA
        CHECK (capacidadPeso >=0),
    CONSTRAINT CK_VOLUMEN_POSITIVO_FROM_BODEGA
        CHECK (capacidadVolumen >=0),
    CONSTRAINT CK_UNIDADES_VOLUMEN_FROM_BODEGA 
        CHECK (unidadVolumen IN ('ml','l')),
    CONSTRAINT CK_UNIDADES_PESO_FROM_BODEGA 
        CHECK (unidadPeso IN ('g','kg','ton')),
    CONSTRAINT A_BODEGAS_PK 
        PRIMARY KEY (idBodega));
    

-- Creaación de la tabla PRODUCTOSBODEGA y especificación de sus restricciones
CREATE TABLE A_PRODUCTOSBODEGA
   (idBodega LONG, 
	cantidad NUMBER NOT NULL,
    idProductoSucursal LONG,
    CONSTRAINT CK_CANTIDAD_POSITIVA_FROM_PRODUCTOSBODEGA 
        CHECK (cantidad >0),
    CONSTRAINT FK_BODEGA_FROM_PRODUCTOSBODEGA
         FOREIGN KEY (idBodega)
         REFERENCES A_BODEGAS(idBodega),
    CONSTRAINT FK_PRODUCTOSUCURSAL_FROM_PRODUCTOSBODEGA
         FOREIGN KEY (idProductoSucursal)
         REFERENCES A_PRODUCTOSSUCURSAL(idProductoSucursal), 
    CONSTRAINT A_PRODUCTOSBODEGA_PK 
        PRIMARY KEY (idBodega,idProductoSucursal));
    

-- Creaación de la tabla PROMOCION y especificación de sus restricciones
CREATE TABLE A_PROMOCION
   (idPromocion LONG, 
	x DOUBLE NOT NULL,
    y DOUBLE NOT NULL,
    fechaInicial DATE NOT NULL,
    fechaFinal DATE NOT NULL,
    tipoPromocion VARCHAR2(255 BYTE) NOT NULL,
    descripcion VARCHAR2(255 BYTE),
    CONSTRAINT CK_TIPOPROMO_FROM_PROMOCION
        CHECK (tipoPromocion IN ('pagueXlleveYunidades','xPorcentajeDescuento','pagueXlleveYcantidad','pagueXlleveSiguienteConYPorcentajeDescuento','pagueXporProductosPromocion')),
    CONSTRAINT CK_X_FROM_PROMOCION 
        CHECK (x >0),
    CONSTRAINT CK_Y_FROM_PROMOCION 
        CHECK (y >0),
    CONSTRAINT A_PROMOCION_PK 
        PRIMARY KEY (idPromocion));
    

-- Creaación de la tabla PROMOCIONESVENDIDAS y especificación de sus restricciones
CREATE TABLE A_PROMOCIONESVENDIDAS
    (noFactura LONG, 
	cantidadVendida NUMBER NOT NULL,
    idPromocion LONG,
    CONSTRAINT CK_CANTIDAD_POSITIVA_FROM_PROMOCIONESVENDIDAS 
        CHECK (cantidadVendida >0),
    CONSTRAINT FK_FACTURA_FROM_PROMOCIONESVENDIDAS
         FOREIGN KEY (noFactura)
         REFERENCES A_FACTURASCOMPRADOR(numero),
    CONSTRAINT FK_PROMOCION_FROM_PROMOCIONESVENDIDAS
         FOREIGN KEY (idPromocion)
         REFERENCES A_PROMOCION(idPromocion), 
    CONSTRAINT A_PROMOCIONESVENDIDAS_PK 
        PRIMARY KEY (noFactura,idPromocion));
    
-- Creaación de la tabla PROVEEDOR y especificación de sus restricciones
CREATE TABLE A_PROVEEDOR
    (nit VARCHAR2(255 BYTE),
    nombre VARCHAR2(255 BYTE)NOT NULL,
    correoElectronico VARCHAR2(255 BYTE)NOT NULL, 
	telefono VARCHAR2(255 BYTE) NOT NULL,
    CONSTRAINT CK_FORMATOCORREO_FROM_PROVEEDOR
        CHECK (correoElectronico LIKE '%@%'),
    CONSTRAINT A_PROVEEDOR_PK 
        PRIMARY KEY (nit));
    
-- Creaación de la tabla PEDIDO y especificación de sus restricciones
CREATE TABLE A_PEDIDO
   (idPedido LONG, 
    fechaEntregaAcordada DATE NOT NULL,
    fechaEntrega DATE,
    estado VARCHAR2(255 BYTE) NOT NULL,
    idSucursal LONG NOT NULL,
    idProveedor VARCHAR2(255 BYTE)NOT NULL,
    CONSTRAINT CK_ESTADO_FROM_PEDIDO
        CHECK (estado IN ('HECHO','ACEPTADO','TARDE','ENTREGADO')),
    CONSTRAINT FK_PRROVEEDOR_FROM_PEDIDO
         FOREIGN KEY (idProveedor)
         REFERENCES A_PROVEEDOR(nit),
    CONSTRAINT FK_SUCURSAL_FROM_PEDIDO
         FOREIGN KEY (idSucursal)
         REFERENCES A_SUCURSAL(idSucursal),
    CONSTRAINT A_PEDIDO_PK 
        PRIMARY KEY (idPedido));
    

-- Creaación de la tabla PRODUCTOSPROVEEDOR y especificación de sus restricciones
CREATE TABLE A_PRODUCTOSPROVEEDOR
   (codigoProducto VARCHAR2(255 BYTE),
    idPedido LONG, 
	precioProveedor NUMBER NOT NULL,
    idProveedor VARCHAR2(255 BYTE)NOT NULL,
    fechaVencimiento DATE,
    calificacionCalidad DOUBLE,
    cantidadUnidades NUMBER NOT NULL,
    CONSTRAINT CK_CANTIDADPOSITIVA_FROM_PRODUCTOSPROVEEDOR
        CHECK ( calificacionCalidad>=0 AND calificacionCalidad<=5),
    CONSTRAINT CK_CANTIDADPOSITIVA_FROM_PRODUCTOSPROVEEDOR
        CHECK (cantidadUnidades >=0),
    CONSTRAINT CK_PRECIOPOSITIVO_FROM_PRODUCTOSPROVEEDOR 
        CHECK (precioProveedor >=0),
    CONSTRAINT FK_CODIGOPRODUCTO_FROM_PRODUCTOSPROVEEDOR
        FOREIGN KEY (codigoProducto)
        REFERENCES A_PRODUCTOS(codigoBarras),
    CONSTRAINT FK_PROVEEDOR_FROM_PRODUCTOSPROVEEDOR
         FOREIGN KEY (idProveedor)
         REFERENCES A_PROVEEDOR(nit),
    CONSTRAINT FK_PEDIDO_FROM_PRODUCTOSPROVEEDOR
         FOREIGN KEY (idPedido)
         REFERENCES A_PEDIDO(idPedido),
    CONSTRAINT A_PRODUCTOSPROVEEDOR_PK 
        PRIMARY KEY (codigoProducto,idProveedor));

  

COMMIT;
