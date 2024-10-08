-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-10-2024 a las 20:57:40
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `materia-prima`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materia-prima`
--

CREATE TABLE `materia-prima` (
  `id` int(3) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `cantidad` float NOT NULL,
  `unidad` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `materia-prima`
--

INSERT INTO `materia-prima` (`id`, `nombre`, `cantidad`, `unidad`) VALUES
(1, 'azúcar', 5, 'kg'),
(5, 'azúcar negra', 3, 'kg'),
(6, 'manteca fortificada', 400, 'g');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `materia-prima`
--
ALTER TABLE `materia-prima`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `materia-prima`
--
ALTER TABLE `materia-prima`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
