-- Producto mas vendido para cada semana del año
SELECT tabla1.cantidadVendida,tabla1.numeroSemana,tabla2.idProductoSucursal as producto FROM 
    (SELECT numeroSemana, MAX(cantidadVendida)as cantidadVendida FROM 
        (SELECT idProductoSucursal ,SUM(cantidadVendida)as cantidadVendida , to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww') as numeroSemana 
            FROM FACTURASCOMPRADOR INNER JOIN PRODUCTOSVENDIDOS
                ON FACTURASCOMPRADOR.numero=NoFactura 
                GROUP BY idProductoSucursal,to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww')) 
        GROUP BY numeroSemana) tabla1 
    INNER JOIN (SELECT idProductoSucursal ,SUM(cantidadVendida)as cantidadVendida , to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww') as numeroSemana
        FROM FACTURASCOMPRADOR INNER JOIN PRODUCTOSVENDIDOS
            ON FACTURASCOMPRADOR.numero=NoFactura 
            GROUP BY idProductoSucursal,to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww')) tabla2 ON tabla1.cantidadVendida=tabla2.cantidadVendida
            AND tabla1.numeroSemana=tabla2.numeroSemana  
            ORDER BY tabla1.numeroSemana ;

-- Producto menos vendido para cada semana del año
SELECT tabla1.cantidadVendida,tabla1.numeroSemana,tabla2.idProductoSucursal as producto FROM 
    (SELECT numeroSemana, MAX(cantidadVendida)as cantidadVendida FROM 
        (SELECT idProductoSucursal ,SUM(cantidadVendida)as cantidadVendida , to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww') as numeroSemana 
            FROM FACTURASCOMPRADOR INNER JOIN PRODUCTOSVENDIDOS
                ON FACTURASCOMPRADOR.numero=NoFactura 
                GROUP BY idProductoSucursal,to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww')) 
        GROUP BY numeroSemana) tabla1 
    INNER JOIN (SELECT idProductoSucursal ,SUM(cantidadVendida)as cantidadVendida , to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww') as numeroSemana
        FROM FACTURASCOMPRADOR INNER JOIN PRODUCTOSVENDIDOS
            ON FACTURASCOMPRADOR.numero=NoFactura 
            GROUP BY idProductoSucursal,to_char(to_date(fecha, 'dd-mm-yyyy'), 'ww')) tabla2 ON tabla1.cantidadVendida=tabla2.cantidadVendida
            AND tabla1.numeroSemana=tabla2.numeroSemana  
            ORDER BY tabla1.numeroSemana ;

-- Proveedor mas solicitado para cada semana del año
WITH tabla1 as 
(SELECT numeroSemana, MAX(cantidadVendida)as cantidadVendida FROM 
(SELECT idProveedor ,COUNT (idProveedor) AS cantidadVendida, to_char(to_date(fechaEntrega, 'dd-mm-yyyy'), 'ww') as numeroSemana FROM 
PROVEEDOR INNER JOIN PEDIDO ON nit=idProveedor GROUP BY idProveedor,to_char(to_date(fechaEntrega, 'dd-mm-yyyy'), 'ww')) 
GROUP BY numeroSemana),
 tabla2 as  
(SELECT idProveedor,COUNT(idProveedor)as cantidadVendida ,nombre, to_char(to_date(fechaEntrega, 'dd-mm-yyyy'), 'ww') as numeroSemana 
	FROM PROVEEDOR INNER JOIN PEDIDO ON nit=idProveedor GROUP BY idProveedor,to_char(to_date(fechaEntrega, 'dd-mm-yyyy'), 'ww'),nombre) 
SELECT tabla1.cantidadVendida,tabla1.numeroSemana,tabla2.idProveedor, tabla2.nombre as proveedor 
	FROM tabla1 LEFT JOIN tabla2 ON tabla1.cantidadVendida=tabla2.cantidadVendida AND tabla1.numeroSemana=tabla2.numeroSemana 
	ORDER BY tabla1.numeroSemana

-- Proveedor menos solicitado para cada semana del año
WITH tabla1 as 
(SELECT numeroSemana, MIN(cantidadVendida)as cantidadVendida FROM 
(SELECT idProveedor ,COUNT (idProveedor) AS cantidadVendida, to_char(to_date(fechaEntrega, 'dd-mm-yyyy'), 'ww') as numeroSemana FROM 
PROVEEDOR INNER JOIN PEDIDO ON nit=idProveedor GROUP BY idProveedor,to_char(to_date(fechaEntrega, 'dd-mm-yyyy'), 'ww')) 
GROUP BY numeroSemana),
 tabla2 as  
(SELECT idProveedor,COUNT(idProveedor)as cantidadVendida ,nombre, to_char(to_date(fechaEntrega, 'dd-mm-yyyy'), 'ww') as numeroSemana 
	FROM PROVEEDOR INNER JOIN PEDIDO ON nit=idProveedor GROUP BY idProveedor,to_char(to_date(fechaEntrega, 'dd-mm-yyyy'), 'ww'),nombre) 
SELECT tabla1.cantidadVendida,tabla1.numeroSemana,tabla2.idProveedor, tabla2.nombre as proveedor 
	FROM tabla1 LEFT JOIN tabla2 ON tabla1.cantidadVendida=tabla2.cantidadVendida AND tabla1.numeroSemana=tabla2.numeroSemana 
	ORDER BY tabla1.numeroSemana



