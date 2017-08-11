-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 11-08-2017 a las 13:41:50
-- Versión del servidor: 10.1.16-MariaDB
-- Versión de PHP: 5.6.24

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `data_spc`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE `categoria` (
  `id` bigint(20) NOT NULL,
  `date_created` datetime NOT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `last_updated` datetime NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `date_created`, `enabled`, `last_updated`, `nombre`) VALUES
(1, '2017-08-09 12:44:44', b'1', '2017-08-09 12:44:44', 'categoria1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria_componente`
--

DROP TABLE IF EXISTS `categoria_componente`;
CREATE TABLE `categoria_componente` (
  `categoria_componente_id` bigint(20) NOT NULL,
  `componente_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria_proveedor`
--

DROP TABLE IF EXISTS `categoria_proveedor`;
CREATE TABLE `categoria_proveedor` (
  `categoria_proveedor_id` bigint(20) NOT NULL,
  `proveedor_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `id` int(11) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `codigo` varchar(255) DEFAULT NULL,
  `codigo_postal` int(5) NOT NULL DEFAULT '0',
  `date_created` datetime NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `dni` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `last_updated` datetime NOT NULL,
  `movil` int(9) NOT NULL DEFAULT '0',
  `municipio` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `observaciones` varchar(4000) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `telefono` int(9) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `apellidos`, `codigo`, `codigo_postal`, `date_created`, `direccion`, `dni`, `email`, `enabled`, `last_updated`, `movil`, `municipio`, `nombre`, `observaciones`, `provincia`, `telefono`) VALUES
(1, NULL, '1701', 0, '2017-08-03 08:33:47', NULL, NULL, NULL, b'0', '2017-08-03 08:33:47', 0, NULL, 'ROCIO', NULL, NULL, 0),
(2, NULL, NULL, 0, '2017-08-03 08:37:02', NULL, NULL, NULL, b'0', '2017-08-03 08:37:02', 0, NULL, 'ROCIO2', NULL, NULL, 0),
(3, '', NULL, 0, '2017-08-08 00:00:00', '', '', '', b'1', '2017-08-08 00:00:00', 0, '', 'martin', '', '', 0),
(4, '', NULL, 0, '2017-08-08 00:00:00', '', '', '', b'0', '2017-08-08 00:00:00', 0, '', 'maria', '', '', 0),
(5, '', NULL, 0, '2017-08-08 00:00:00', '', '', '', b'1', '2017-08-08 00:00:00', 0, '', 'marina', '', '', 0),
(6, '', '176', 0, '2017-08-08 00:00:00', '', '', '', b'1', '2017-08-08 00:00:00', 0, '', 'ROCIO', '', '', 0),
(7, '', '177', 0, '2017-08-09 00:00:00', '', '', '', b'1', '2017-08-09 00:00:00', 0, '', 'Pepe', '', '', 0),
(8, '', '178', 0, '2017-08-09 00:00:00', '', '', '', b'1', '2017-08-09 00:00:00', 0, '', 'Miguel 2', '', '', 0),
(9, '', '1709', 0, '2017-08-09 00:00:00', '', '', '', b'1', '2017-08-09 00:00:00', 0, '', 'Miguel 3', '', '', 0),
(10, '', '1710', 0, '2017-08-09 00:00:00', '', '', '', b'1', '2017-08-09 00:00:00', 0, '', 'Miguel 4', '', '', 0),
(11, 'lopez garcia', '1711', 41530, '2017-08-09 00:00:00', 'calle del rio, 2', '11222333a', 'adas@asddas.com', b'1', '2017-08-09 00:00:00', 600010203, 'moron de la fra', 'Luis ', '', 'sevilla', 955851122);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente_seguimiento`
--

DROP TABLE IF EXISTS `cliente_seguimiento`;
CREATE TABLE `cliente_seguimiento` (
  `cliente_seguimientos_id` int(11) NOT NULL,
  `seguimiento_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `componente`
--

DROP TABLE IF EXISTS `componente`;
CREATE TABLE `componente` (
  `id` bigint(20) NOT NULL,
  `coste` double DEFAULT NULL,
  `date_created` datetime NOT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `last_updated` datetime NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `defecto` bit(1) NOT NULL DEFAULT b'0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `componente`
--

INSERT INTO `componente` (`id`, `coste`, `date_created`, `enabled`, `last_updated`, `nombre`, `defecto`) VALUES
(1, 12, '2017-08-09 12:50:31', b'1', '2017-08-09 12:50:31', 'componente1', b'0'),
(2, 1, '0000-00-00 00:00:00', b'1', '0000-00-00 00:00:00', 'componente2', b'0'),
(3, 2, '0000-00-00 00:00:00', b'1', '0000-00-00 00:00:00', 'componente3', b'0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `componente_proveedor`
--

DROP TABLE IF EXISTS `componente_proveedor`;
CREATE TABLE `componente_proveedor` (
  `id` bigint(20) NOT NULL,
  `componente_id` bigint(20) NOT NULL,
  `proveedor_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago`
--

DROP TABLE IF EXISTS `pago`;
CREATE TABLE `pago` (
  `id` bigint(20) NOT NULL,
  `date_created` datetime NOT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `last_updated` datetime NOT NULL,
  `pago` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pago`
--

INSERT INTO `pago` (`id`, `date_created`, `enabled`, `last_updated`, `pago`) VALUES
(1, '2017-08-09 12:45:33', b'1', '2017-08-09 12:45:33', 'Efectivo'),
(2, '2017-08-09 12:45:59', b'1', '2017-08-09 12:45:59', 'Cheque');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE `proveedor` (
  `id` bigint(20) NOT NULL,
  `cif` varchar(255) DEFAULT NULL,
  `codigo_postal` int(5) DEFAULT '0',
  `date_created` datetime NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `last_updated` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `movil` int(9) NOT NULL DEFAULT '0',
  `municipio` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `observaciones` varchar(4000) DEFAULT NULL,
  `persona_contacto` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `telefono` int(9) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id`, `cif`, `codigo_postal`, `date_created`, `direccion`, `email`, `enabled`, `last_updated`, `movil`, `municipio`, `nombre`, `observaciones`, `persona_contacto`, `provincia`, `telefono`) VALUES
(1, '11111111-X', 0, '2017-08-09 09:03:39', '', '', b'1', '2017-08-09 09:20:13', 0, '', 'Proveedor1', '', NULL, '', 0),
(2, '22222222C', 0, '2017-08-09 09:02:56', '', 'a', b'1', '2017-08-09 09:43:42', 0, '', 'Proveedor2', '', NULL, '', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor_componente`
--

DROP TABLE IF EXISTS `proveedor_componente`;
CREATE TABLE `proveedor_componente` (
  `proveedor_componente_id` bigint(20) NOT NULL,
  `componente_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `authority` varchar(255) NOT NULL,
  `rolname` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `role`
--

INSERT INTO `role` (`id`, `authority`, `rolname`) VALUES
(1, 'ROLE_ADMIN', 'Administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguimiento`
--

DROP TABLE IF EXISTS `seguimiento`;
CREATE TABLE `seguimiento` (
  `id` bigint(20) NOT NULL,
  `color` varchar(255) DEFAULT NULL,
  `date_created` datetime NOT NULL,
  `enabled` bit(1) NOT NULL DEFAULT b'1',
  `last_updated` datetime NOT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `observacion` varchar(4000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguimiento_componente`
--

DROP TABLE IF EXISTS `seguimiento_componente`;
CREATE TABLE `seguimiento_componente` (
  `id` bigint(20) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `componente_id` bigint(20) NOT NULL,
  `fecha_entrega` datetime NOT NULL,
  `fecha_pedido` datetime NOT NULL,
  `numero_factura` varchar(255) NOT NULL,
  `observaciones` varchar(255) NOT NULL,
  `seguimiento_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguimiento_pago`
--

DROP TABLE IF EXISTS `seguimiento_pago`;
CREATE TABLE `seguimiento_pago` (
  `id` bigint(20) NOT NULL,
  `version` bigint(20) NOT NULL,
  `banco` varchar(255) NOT NULL,
  `date_created` datetime NOT NULL,
  `fecha` datetime NOT NULL,
  `last_updated` datetime NOT NULL,
  `observaciones` varchar(255) NOT NULL,
  `pago_id` bigint(20) NOT NULL,
  `seguimiento_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `user`
--

INSERT INTO `user` (`id`, `enabled`, `password`, `username`) VALUES
(1, b'1', 'd033e22ae348aeb5660fc2140aec35850c4da997', 'admin'),
(2, b'1', 'b49a5780a99ea81284fc0746a78f84a30e4d5c73', 'juan');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `user_role`
--

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `rol_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `user_role`
--

INSERT INTO `user_role` (`user_id`, `rol_id`) VALUES
(1, 1),
(2, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `categoria_componente`
--
ALTER TABLE `categoria_componente`
  ADD KEY `FKqp3j3ug0xju7q3lro8ct2u84` (`componente_id`),
  ADD KEY `FKf5grojhn9s0sla9dklq79tvs3` (`categoria_componente_id`);

--
-- Indices de la tabla `categoria_proveedor`
--
ALTER TABLE `categoria_proveedor`
  ADD KEY `FK8awvkbrk0d1v268qx47f9ty04` (`proveedor_id`),
  ADD KEY `FKi4lgf1q7wa1dg3meykei25cm6` (`categoria_proveedor_id`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cliente_seguimiento`
--
ALTER TABLE `cliente_seguimiento`
  ADD KEY `FKpx7hhbe6468d21ekkbrc5h8sn` (`seguimiento_id`),
  ADD KEY `FKpmk4h8hpy858mxe983l28kj3u` (`cliente_seguimientos_id`);

--
-- Indices de la tabla `componente`
--
ALTER TABLE `componente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `componente_proveedor`
--
ALTER TABLE `componente_proveedor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKj20bae6pyyiyaj99cxrbky79u` (`componente_id`),
  ADD KEY `FKjdi7jvy03a90gyuwnymv5jwij` (`proveedor_id`);

--
-- Indices de la tabla `pago`
--
ALTER TABLE `pago`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `proveedor_componente`
--
ALTER TABLE `proveedor_componente`
  ADD KEY `FKq99pam7v6bammbqws29mh2ndm` (`componente_id`),
  ADD KEY `FKc8hh3cukmmwdbl6p7l038uh81` (`proveedor_componente_id`);

--
-- Indices de la tabla `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_irsamgnera6angm0prq1kemt2` (`authority`);

--
-- Indices de la tabla `seguimiento`
--
ALTER TABLE `seguimiento`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `seguimiento_componente`
--
ALTER TABLE `seguimiento_componente`
  ADD PRIMARY KEY (`seguimiento_id`,`componente_id`),
  ADD KEY `FKksfpcufw5i3wykhs2m1906bvl` (`componente_id`);

--
-- Indices de la tabla `seguimiento_pago`
--
ALTER TABLE `seguimiento_pago`
  ADD PRIMARY KEY (`seguimiento_id`,`pago_id`),
  ADD KEY `FKa8nhklmtjq44t2dad3wlent1l` (`pago_id`);

--
-- Indices de la tabla `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`);

--
-- Indices de la tabla `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`rol_id`),
  ADD KEY `FKa68196081fvovjhkek5m97n3y` (`rol_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT de la tabla `componente`
--
ALTER TABLE `componente`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `componente_proveedor`
--
ALTER TABLE `componente_proveedor`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `pago`
--
ALTER TABLE `pago`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `seguimiento`
--
ALTER TABLE `seguimiento`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `seguimiento_componente`
--
ALTER TABLE `seguimiento_componente`
  MODIFY `seguimiento_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `seguimiento_pago`
--
ALTER TABLE `seguimiento_pago`
  MODIFY `seguimiento_id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `categoria_componente`
--
ALTER TABLE `categoria_componente`
  ADD CONSTRAINT `FKf5grojhn9s0sla9dklq79tvs3` FOREIGN KEY (`categoria_componente_id`) REFERENCES `categoria` (`id`),
  ADD CONSTRAINT `FKqp3j3ug0xju7q3lro8ct2u84` FOREIGN KEY (`componente_id`) REFERENCES `componente` (`id`);

--
-- Filtros para la tabla `categoria_proveedor`
--
ALTER TABLE `categoria_proveedor`
  ADD CONSTRAINT `FK8awvkbrk0d1v268qx47f9ty04` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedor` (`id`),
  ADD CONSTRAINT `FKi4lgf1q7wa1dg3meykei25cm6` FOREIGN KEY (`categoria_proveedor_id`) REFERENCES `categoria` (`id`);

--
-- Filtros para la tabla `cliente_seguimiento`
--
ALTER TABLE `cliente_seguimiento`
  ADD CONSTRAINT `FKpmk4h8hpy858mxe983l28kj3u` FOREIGN KEY (`cliente_seguimientos_id`) REFERENCES `cliente` (`id`),
  ADD CONSTRAINT `FKpx7hhbe6468d21ekkbrc5h8sn` FOREIGN KEY (`seguimiento_id`) REFERENCES `seguimiento` (`id`);

--
-- Filtros para la tabla `componente_proveedor`
--
ALTER TABLE `componente_proveedor`
  ADD CONSTRAINT `FKj20bae6pyyiyaj99cxrbky79u` FOREIGN KEY (`componente_id`) REFERENCES `componente` (`id`),
  ADD CONSTRAINT `FKjdi7jvy03a90gyuwnymv5jwij` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedor` (`id`);

--
-- Filtros para la tabla `proveedor_componente`
--
ALTER TABLE `proveedor_componente`
  ADD CONSTRAINT `FKc8hh3cukmmwdbl6p7l038uh81` FOREIGN KEY (`proveedor_componente_id`) REFERENCES `proveedor` (`id`),
  ADD CONSTRAINT `FKq99pam7v6bammbqws29mh2ndm` FOREIGN KEY (`componente_id`) REFERENCES `componente` (`id`);

--
-- Filtros para la tabla `seguimiento_componente`
--
ALTER TABLE `seguimiento_componente`
  ADD CONSTRAINT `FKcrc34ct6d0jk7m91ou407nn7k` FOREIGN KEY (`seguimiento_id`) REFERENCES `seguimiento` (`id`),
  ADD CONSTRAINT `FKksfpcufw5i3wykhs2m1906bvl` FOREIGN KEY (`componente_id`) REFERENCES `componente` (`id`);

--
-- Filtros para la tabla `seguimiento_pago`
--
ALTER TABLE `seguimiento_pago`
  ADD CONSTRAINT `FKa8nhklmtjq44t2dad3wlent1l` FOREIGN KEY (`pago_id`) REFERENCES `pago` (`id`),
  ADD CONSTRAINT `FKbntm8y7dvcuuwhdp2x5occq99` FOREIGN KEY (`seguimiento_id`) REFERENCES `seguimiento` (`id`);

--
-- Filtros para la tabla `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`rol_id`) REFERENCES `role` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
