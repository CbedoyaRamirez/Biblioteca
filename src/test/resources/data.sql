INSERT INTO libro(ID, TITULO, ISBN, ANIO)
VALUES (10, 'MI PRIMER AMOR', 'PD1023', 2020);

INSERT INTO libro(ID, TITULO, ISBN, ANIO)
VALUES (11, 'PRINCIPIOS SOLID', 'PD1000', 2020);

INSERT INTO libro(ID, TITULO, ISBN, ANIO)
VALUES (12, 'LAS LOCURAS DEL POTRO', 'PD1001', 2020);

INSERT INTO libro(ID, TITULO, ISBN, ANIO)
VALUES (13, 'NO TE METAS CON LA VECINA', 'PD9999', 2020);

INSERT INTO libro(ID, TITULO, ISBN, ANIO)
VALUES (14, 'PAÑO VILLA', '12421', 2020);


INSERT INTO prestamo(ID, ID_LIBRO, FECHA_SOLICITUD, FECHA_ENTREGA_MAXIMA, NOMBRE_USUARIO)
VALUES (100, 10, NULL, NULL, 'PEDRO');