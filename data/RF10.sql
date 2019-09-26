--RFC10 Administrador, devuelve la info de todas las sucursales
SELECT nombre, identificador, correoelectronico, fecha, cantidadVendida, idProductoSucursal as CodigoProducto FROM COMPRADORES 
    INNER JOIN (SELECT * FROM FACTURASCOMPRADOR 
        INNER JOIN (SELECT * FROM PRODUCTOSVENDIDOS 
            WHERE idProductoSucursal LIKE '%f1f1f1') 
        ON FACTURASCOMPRADOR.numero=NoFactura 
        WHERE FECHA between '26-10-2000' AND '31-10-3000') 
    ON COMPRADORES.identificador=idComprador;

--RFC10 Gerente, devuelve unicamente la info de una sucursal.
SELECT nombre, identificador, correoelectronico, fecha, cantidadVendida, idProductoSucursal as CodigoProducto FROM COMPRADORES 
    INNER JOIN (SELECT * FROM FACTURASCOMPRADOR 
        INNER JOIN (SELECT * FROM PRODUCTOSVENDIDOS 
            WHERE idProductoSucursal LIKE '%f1f1f1') 
        ON FACTURASCOMPRADOR.numero=NoFactura 
        WHERE FECHA between '26-10-2000' AND '31-10-3000' AND idSucursal=3) 
    ON COMPRADORES.identificador=idComprador;
