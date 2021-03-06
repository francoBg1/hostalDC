-- Generado por Oracle SQL Developer Data Modeler 18.1.0.082.1035
--   en:        2020-05-27 11:25:45 CLT
--   sitio:      Oracle Database 11g
--   tipo:      Oracle Database 11g



CREATE TABLE cliente (
    rut_emp              VARCHAR2(20) NOT NULL,
    nom_emp              VARCHAR2(30) NOT NULL,
    tele_emp             VARCHAR2(30) NOT NULL,
    dir_emp              VARCHAR2(40) NOT NULL,
    usuario_id_usuario   INTEGER NOT NULL
);

CREATE UNIQUE INDEX cliente__idx ON
    cliente (
        usuario_id_usuario
    ASC );

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( rut_emp );

CREATE TABLE configuracion (
    iva   INTEGER NOT NULL
);

CREATE TABLE detalle_pedido (
    cantidad                 INTEGER NOT NULL,
    orden_pedido_id_pedido   INTEGER NOT NULL,
    productos_id_producto    INTEGER NOT NULL
);

CREATE TABLE empleado (
    rut                  VARCHAR2(20) NOT NULL,
    nombre               VARCHAR2(20) NOT NULL,
    apellido             VARCHAR2(20) NOT NULL,
    usuario_id_usuario   INTEGER NOT NULL
);

CREATE UNIQUE INDEX empleado__idx ON
    empleado (
        usuario_id_usuario
    ASC );

ALTER TABLE empleado ADD CONSTRAINT empleado_pk PRIMARY KEY ( rut );

CREATE TABLE factura (
    cod_factura                  INTEGER NOT NULL,
    descripcion                  VARCHAR2(50) NOT NULL,
    fecha_pago                   DATE,
    fecha_factura                DATE NOT NULL,
    orden_compra_codigo_compra   INTEGER NOT NULL
);

CREATE UNIQUE INDEX factura__idx ON
    factura (
        orden_compra_codigo_compra
    ASC );

ALTER TABLE factura ADD CONSTRAINT factura_pk PRIMARY KEY ( cod_factura );

CREATE TABLE habitacion (
    num_hab                      INTEGER NOT NULL,
    tipo_cama                    VARCHAR2(20) NOT NULL,
    precio                       INTEGER NOT NULL,
    accesorio                    VARCHAR2(50),
    disponibilidad               VARCHAR2(30) NOT NULL,
    orden_compra_codigo_compra   INTEGER
);

ALTER TABLE habitacion ADD CONSTRAINT habitacion_pk PRIMARY KEY ( num_hab );

CREATE TABLE huesped (
    rut                  VARCHAR2(20) NOT NULL,
    nombre               VARCHAR2(20) NOT NULL,
    apellido             VARCHAR2(20) NOT NULL,
    habitacion_num_hab   INTEGER NOT NULL
);

CREATE UNIQUE INDEX huesped__idx ON
    huesped (
        habitacion_num_hab
    ASC );

ALTER TABLE huesped ADD CONSTRAINT huesped_pk PRIMARY KEY ( rut );

CREATE TABLE minuta (
    id_minuta                    INTEGER NOT NULL,
    f_creado                     DATE NOT NULL,
    f_inicio                     DATE NOT NULL,
    f_fin                        DATE,
    titulo                       VARCHAR2(30) NOT NULL,
    desayuno                     VARCHAR2(100),
    almuerzo                     VARCHAR2(100),
    once                         VARCHAR2(100),
    cena                         VARCHAR2(100),
    valor                        INTEGER NOT NULL,
    orden_compra_codigo_compra   INTEGER
);

ALTER TABLE minuta ADD CONSTRAINT minuta_pk PRIMARY KEY ( id_minuta );

CREATE TABLE orden_compra (
    codigo_compra     INTEGER NOT NULL,
    f_inicio          DATE NOT NULL,
    f_fin             DATE NOT NULL,
    f_compra          DATE NOT NULL,
    cantidad          INTEGER NOT NULL,
    precio_total      INTEGER NOT NULL,
    cliente_rut_emp   VARCHAR2(20) NOT NULL
);

ALTER TABLE orden_compra ADD CONSTRAINT orden_compra_pk PRIMARY KEY ( codigo_compra );

CREATE TABLE orden_pedido (
    id_pedido       INTEGER NOT NULL,
    f_emicion       DATE NOT NULL,
    f_recepcion     DATE,
    plazo_limite    DATE,
    proveedor_rut   VARCHAR2(20) NOT NULL,
    empleado_rut    VARCHAR2(20) NOT NULL
);

ALTER TABLE orden_pedido ADD CONSTRAINT orden_pedido_pk PRIMARY KEY ( id_pedido );

CREATE TABLE productos (
    id_producto   INTEGER NOT NULL,
    nombre        VARCHAR2(15) NOT NULL,
    detalle       VARCHAR2(50) NOT NULL,
    valor         INTEGER NOT NULL
);

ALTER TABLE productos ADD CONSTRAINT productos_pk PRIMARY KEY ( id_producto );

CREATE TABLE proveedor (
    rut                  VARCHAR2(20) NOT NULL,
    nom_empresa          VARCHAR2(30) NOT NULL,
    rubro                VARCHAR2(30) NOT NULL,
    usuario_id_usuario   INTEGER NOT NULL
);

CREATE UNIQUE INDEX proveedor__idx ON
    proveedor (
        usuario_id_usuario
    ASC );

ALTER TABLE proveedor ADD CONSTRAINT proveedor_pk PRIMARY KEY ( rut );

CREATE TABLE tipo_usuario (
    permiso        INTEGER NOT NULL,
    tipo_usuario   VARCHAR2(15) NOT NULL
);

ALTER TABLE tipo_usuario ADD CONSTRAINT tipo_usuario_pk PRIMARY KEY ( permiso );

CREATE TABLE usuario (
    id_usuario             INTEGER NOT NULL,
    nom_usuario            VARCHAR2(40) NOT NULL,
    clave                  VARCHAR2(10) NOT NULL,
    correo                 VARCHAR2(40) NOT NULL,
    tipo_usuario_permiso   INTEGER NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pk PRIMARY KEY ( id_usuario );

ALTER TABLE cliente
    ADD CONSTRAINT cliente_usuario_fk FOREIGN KEY ( usuario_id_usuario )
        REFERENCES usuario ( id_usuario );

ALTER TABLE detalle_pedido
    ADD CONSTRAINT detalle_pedido_orden_pedido_fk FOREIGN KEY ( orden_pedido_id_pedido )
        REFERENCES orden_pedido ( id_pedido );

ALTER TABLE detalle_pedido
    ADD CONSTRAINT detalle_pedido_productos_fk FOREIGN KEY ( productos_id_producto )
        REFERENCES productos ( id_producto );

ALTER TABLE empleado
    ADD CONSTRAINT empleado_usuario_fk FOREIGN KEY ( usuario_id_usuario )
        REFERENCES usuario ( id_usuario );

ALTER TABLE factura
    ADD CONSTRAINT factura_orden_compra_fk FOREIGN KEY ( orden_compra_codigo_compra )
        REFERENCES orden_compra ( codigo_compra );

ALTER TABLE habitacion
    ADD CONSTRAINT habitacion_orden_compra_fk FOREIGN KEY ( orden_compra_codigo_compra )
        REFERENCES orden_compra ( codigo_compra );

ALTER TABLE huesped
    ADD CONSTRAINT huesped_habitacion_fk FOREIGN KEY ( habitacion_num_hab )
        REFERENCES habitacion ( num_hab );

ALTER TABLE minuta
    ADD CONSTRAINT minuta_orden_compra_fk FOREIGN KEY ( orden_compra_codigo_compra )
        REFERENCES orden_compra ( codigo_compra );

ALTER TABLE orden_compra
    ADD CONSTRAINT orden_compra_cliente_fk FOREIGN KEY ( cliente_rut_emp )
        REFERENCES cliente ( rut_emp );

ALTER TABLE orden_pedido
    ADD CONSTRAINT orden_pedido_empleado_fk FOREIGN KEY ( empleado_rut )
        REFERENCES empleado ( rut );

ALTER TABLE orden_pedido
    ADD CONSTRAINT orden_pedido_proveedor_fk FOREIGN KEY ( proveedor_rut )
        REFERENCES proveedor ( rut );

ALTER TABLE proveedor
    ADD CONSTRAINT proveedor_usuario_fk FOREIGN KEY ( usuario_id_usuario )
        REFERENCES usuario ( id_usuario );

ALTER TABLE usuario
    ADD CONSTRAINT usuario_tipo_usuario_fk FOREIGN KEY ( tipo_usuario_permiso )
        REFERENCES tipo_usuario ( permiso );



-- Informe de Resumen de Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            14
-- CREATE INDEX                             5
-- ALTER TABLE                             25
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           0
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          0
-- CREATE MATERIALIZED VIEW                 0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
