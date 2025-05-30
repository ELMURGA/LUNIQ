-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 23-05-2025 a las 22:36:09
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `proyecto_bbdd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id_producto` int(11) NOT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` text DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `stock` int(11) DEFAULT 0,
  `categoria` varchar(50) DEFAULT NULL,
  `ImagenURL` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id_producto`, `nombre`, `descripcion`, `precio`, `stock`, `categoria`, `ImagenURL`) VALUES
(6, 'Camiseta Under Amour', 'Camiseta azul Under Amour de buenísima calidad', 30.00, 40, 'Camisetas', 'CamisetaUnderAmour.jpg'),
(7, 'Camiseta de Solo Leveling', 'Camiseta con el diseño de la serie Solo Leveling, de gran calidad.', 20.00, 20, 'Camisetas', 'CamisetaSoloLeveling.jpg'),
(10, 'Camiseta Pokemon', 'Camiseta Pokemon que incluye okemon de varias generaciones', 30.00, 40, 'Camisetas', 'CamisetaPokemon.jpg'),
(11, 'Pantalon vaquero', 'Pantalón vaquero basico, cumple para ir a cualquier situación', 20.00, 20, 'Pantalones', 'PantalonVaquero.jpg'),
(12, 'Pantalon Nike', 'Pantalón nike básico de color rosa', 35.00, 25, 'Pantalones', 'PantalonNike.jpg'),
(13, 'Deportes Rojos', 'Zapatilas de deporte, muy cómodas y reconfortables', 60.00, 60, 'Zapatillas', 'ZapatillasRojas.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `nombre_usuario` varchar(10) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellido` varchar(15) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `contraseña` varchar(255) NOT NULL,
  `pais` varchar(100) DEFAULT NULL,
  `fecha_registro` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `nombre_usuario`, `nombre`, `apellido`, `email`, `contraseña`, `pais`, `fecha_registro`) VALUES
(1, 'dcf', 'Diego', 'Capellán', 'dcf0012@alu.medac.es', '1234', 'España', '2025-05-20 14:39:11'),
(2, 'murga', NULL, NULL, 'alejandohernandezmurga@alu.medac.es', '12345', NULL, '2025-05-21 08:26:18'),
(3, '', NULL, NULL, '', '', NULL, '2025-05-21 10:35:18'),
(5, 'pedro', NULL, NULL, 'jfaksldj@gmail.com', 'jasfjaskldfj', NULL, '2025-05-21 10:59:12'),
(6, 'Juan', NULL, NULL, 'jfkaldsjkl@gmail.com', 'fjaskldjfkalsd', NULL, '2025-05-21 11:02:18'),
(8, 'ana', NULL, NULL, 'anr@gmail.com', '1234', NULL, '2025-05-21 19:30:57'),
(9, 'tuesta', NULL, NULL, 'tuesta@gmail.com', '123', NULL, '2025-05-22 15:16:22'),
(10, 'diego', NULL, NULL, 'diego@gmail.com', '1234', NULL, '2025-05-22 15:28:36'),
(11, 'sergio', NULL, NULL, 'sergio@gmail.com', '123', NULL, '2025-05-22 17:45:51'),
(13, 'tuesta', NULL, NULL, 'tuestaa@gmail.com', '1234', NULL, '2025-05-22 17:53:31'),
(14, 'usuario', 'usuario', 'usuario', 'usuario', 'usuario', 'españa', '2025-05-22 17:54:22'),
(15, 'sergi', NULL, NULL, 'sergi@gmail.com', 'sergi', NULL, '2025-05-22 19:42:03'),
(16, 'guille', NULL, NULL, 'guille@gmail.com', 'guille', NULL, '2025-05-23 14:02:39'),
(17, 'd', 'd', 'd', 'd@gmail', '1', 'esp', '2025-05-23 22:26:58');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id_producto`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id_producto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
