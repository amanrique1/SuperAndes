
--Buenos clientes que compran cada mes
SELECT * FROM COMPRADORES 
	WHERE identificador IN 
		(SELECT idComprador FROM(SELECT COUNT(idComprador)as mesesComprados, idComprador 
			FROM(SELECT EXTRACT(month FROM FECHA)as mes,EXTRACT(year FROM FECHA)as Anio, idComprador 
				FROM FACTURASCOMPRADOR GROUP BY EXTRACT(year FROM FECHA),EXTRACT(month FROM FECHA),idComprador) 
		GROUP BY idComprador)
		WHERE mesesComprados>5);

--Buenos clientes que compran siempre productos costosos
SELECT * FROM COMPRADORES 
    WHERE identificador IN(
        SELECT idComprador FROM FACTURASCOMPRADOR 
            WHERE numero IN 
                (SELECT NoFactura FROM PRODUCTOSVENDIDOS 
                    WHERE idProductoSucursal IN 
                      (SELECT idproductosucursal FROM PRODUCTOSSUCURSAL WHERE PRECIO >= 100000)) 
            AND numero NOT IN
                (SELECT NoFactura FROM PRODUCTOSVENDIDOS 
                    WHERE idProductoSucursal IN 
                        (SELECT idproductosucursal FROM PRODUCTOSSUCURSAL WHERE PRECIO < 100000)
                    AND idProductoSucursal NOT IN
                        (SELECT idproductosucursal FROM PRODUCTOSSUCURSAL WHERE PRECIO >= 100000)))

--Buenos clientes que compran siempre productos de categoria tecnologia o herramientas
SELECT * FROM COMPRADORES 
    WHERE identificador IN(
        SELECT idComprador FROM FACTURASCOMPRADOR 
            WHERE numero IN 
                (SELECT NoFactura FROM PRODUCTOSVENDIDOS 
                    WHERE idProductoSucursal IN 
                      (SELECT idproductosucursal FROM PRODUCTOSSUCURSAL 
                            WHERE codigoProducto in
                                (SELECT codigoBarras FROM PRODUCTOS 
                                    WHERE tipoProducto IN 
                                        (SELECT nombreTipo FROM TIPOPRODUCTO 
                                            WHERE nombreCategoria='Tools' OR nombreCategoria='Electronics')))) 
            AND numero NOT IN
                (SELECT NoFactura FROM PRODUCTOSVENDIDOS 
                    WHERE idProductoSucursal IN 
                        (SELECT idproductosucursal FROM PRODUCTOSSUCURSAL 
                            WHERE codigoProducto in
                                (SELECT codigoBarras FROM PRODUCTOS 
                                    WHERE tipoProducto IN (SELECT nombreTipo 
                                        FROM TIPOPRODUCTO 
                                            WHERE nombreCategoria<>'Tools' AND nombreCategoria<>'Electronics')))
                    AND idProductoSucursal NOT IN
                        (SELECT idproductosucursal FROM PRODUCTOSSUCURSAL 
                            WHERE codigoProducto in
                                (SELECT codigoBarras FROM PRODUCTOS 
                                    WHERE tipoProducto IN 
                                        (SELECT nombreTipo FROM TIPOPRODUCTO 
                                            WHERE nombreCategoria='Tools' OR nombreCategoria='Electronics')))))


