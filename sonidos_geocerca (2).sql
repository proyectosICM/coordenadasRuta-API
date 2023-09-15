-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-09-2023 a las 01:03:50
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
-- Base de datos: `coordenadas-rutadb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sonidos_geocerca`
--

CREATE TABLE `sonidos_geocerca` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `url_imagen` varchar(255) DEFAULT NULL,
  `url_sonido` varchar(255) DEFAULT NULL,
  `pais` bigint(20) NOT NULL,
  `tipos` bigint(20) NOT NULL,
  `codsonido` int(11) DEFAULT NULL,
  `detalle` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Truncar tablas antes de insertar `sonidos_geocerca`
--

TRUNCATE TABLE `sonidos_geocerca`;
--
-- Volcado de datos para la tabla `sonidos_geocerca`
--

INSERT INTO `sonidos_geocerca` (`id`, `nombre`, `url_imagen`, `url_sonido`, `pais`, `tipos`, `codsonido`, `detalle`) VALUES
(10, 'SR-01', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-01.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00010.mp3', 2, 1, 9, 'Pare'),
(11, 'SR-02', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-02.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00011.mp3', 2, 1, 10, 'Ceda el paso'),
(12, 'SR-03', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-03.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00012.mp3', 2, 1, 11, 'Siga de frente'),
(13, 'SR-04', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-04.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00013.mp3', 2, 1, 12, 'No pase'),
(14, 'SR-05', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-05.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00014.mp3', 2, 1, 13, 'Voltear a la izquierda'),
(15, 'SR-06', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-06.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00015.mp3', 2, 1, 14, 'No voltear a la izquierda'),
(16, 'SR-07', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-07.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00016.mp3', 2, 1, 15, 'Voltear a la derecha'),
(17, 'SR-08', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-08.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00017.mp3', 2, 1, 16, 'No voltear a la derecha'),
(18, 'SR-09', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-09.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00018.mp3', 2, 1, 17, 'Vuelta en U permitida'),
(19, 'SR-10', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-10.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00019.mp3', 2, 1, 18, 'Prohibido voltear en U'),
(21, 'SR-11', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-11.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00020.mp3', 2, 1, 20, 'Doble via'),
(22, 'SR-12', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-12.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00021.mp3', 2, 1, 21, 'Tres carriles (uno en contraflujo)'),
(23, 'SR-13', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-13.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00022.mp3', 2, 1, 22, 'Tres carriles (dos en contraflujo)'),
(24, 'SR-14', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-14.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00023.mp3', 2, 1, 23, 'Prohibido el cambio de calzada'),
(25, 'SR-15', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-15.jpg', NULL, 2, 1, 24, NULL),
(26, 'SR-16', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-16.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00024.mp3', 2, 1, 25, 'Circulacion prohibida de vehiculos automotores'),
(27, 'SR-17', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-17.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00025.mp3', 2, 1, 26, 'Vehiculos pesados a la derecha'),
(28, 'SR-18', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-18.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00026.mp3', 2, 1, 27, 'Circulacion prohibida de vehiculos de carga'),
(29, 'SR-19', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-19.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00027.mp3', 2, 1, 28, '\nPeatones a la izquierda'),
(30, 'SR-20', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-20.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00028.mp3', 2, 1, 29, 'Prohibido el paso de peatones'),
(31, 'SR-21', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-21.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00029.mp3', 2, 1, 30, 'Prohibido circulacion de cabalgaduras'),
(32, 'SR-22', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-22.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00030.mp3', 2, 1, 31, 'Prohibido circulación de bicicletas'),
(33, 'SR-23', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-23.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00031.mp3', 2, 1, 32, 'Prohibido circulacion de motocicleras'),
(34, 'SR-24', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-24.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00032.mp3', 2, 1, 33, 'Prohibido circulacion de maquina agricola'),
(35, 'SR-25', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-25.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00033.mp3', 2, 1, 34, 'Circulacion prohibida de vehiculo de traccion animal'),
(36, 'SR-26', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-26.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00034.mp3', 2, 1, 35, 'Prohibido adelantar'),
(37, 'SR-28', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-28.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00035.mp3', 2, 1, 36, 'Prohibido parquear'),
(38, 'SR-28A', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-28A.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00036.mp3', 2, 1, 37, 'No parquear ni detenerse'),
(39, 'SR-29', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-29.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00037.mp3', 2, 1, 38, 'Prohibido pitar'),
(40, 'SR-30', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-30.jpg', 'http://localhost/coordenadas-ruta-segura/audios/00038.mp3', 2, 1, 39, 'Velocidad maxima '),
(41, 'SR-31', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-31.jpg', NULL, 2, 1, 40, NULL),
(42, 'SR-32', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-32.jpg', NULL, 2, 1, 41, NULL),
(43, 'SR-33', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-33.jpg', NULL, 2, 1, 42, NULL),
(44, 'SR-34', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-34.jpg', NULL, 2, 1, 43, 'Zona de estacionamiento de taxis'),
(45, 'SR-35', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-35.jpg', NULL, 2, 1, 44, 'Circulacion con luces bajas'),
(46, 'SR-36', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-36.jpg', NULL, 2, 1, 45, 'Reten'),
(47, 'SR-37', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-37.jpg', NULL, 2, 1, 46, 'Ciclovia'),
(48, 'SR-38', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-38.jpg', NULL, 2, 1, 47, 'Sentido unico de circulacion'),
(49, 'SR-39', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-39.jpg', NULL, 2, 1, 48, 'Sentido de circulacion doble'),
(50, 'SR-40', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-40.jpg', NULL, 2, 1, 49, 'Paradero'),
(51, 'SR-41', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-41.jpg', NULL, 2, 1, 50, 'Prohibido dejar o recoger pasajeros'),
(52, 'SR-42', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-42.jpg', NULL, 2, 1, 51, 'Zona de carge y descargue'),
(53, 'SR-43', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-43.jpg', NULL, 2, 1, 52, '\nProhibido el cargue y descargue'),
(54, 'SR-44', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-44.jpg', NULL, 2, 1, 53, 'Espaciamiento'),
(55, 'SR-45', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-45.jpg', NULL, 2, 1, 54, 'Indicacion de separador transito'),
(56, 'SR-46', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SR-46.jpg', NULL, 2, 1, 55, 'Indicacion de separador transito'),
(64, 'SRO-01', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SRO-01.jpg', NULL, 2, 1, NULL, 'Via cerrada'),
(65, 'SRO-02', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SRO-02.jpg', NULL, 2, 1, NULL, 'Desvio'),
(66, 'SRO-03', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SRO-03.jpg', NULL, 2, 1, NULL, 'Paso uno a uno'),
(67, 'SP-01', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-01.jpg', NULL, 2, 2, NULL, 'Precaucion curva peligrosa '),
(68, 'SP-2', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-02.jpg', NULL, 2, 2, NULL, 'Precaucion curva peligrosa '),
(69, 'SP-03', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-03.jpg', NULL, 2, 2, NULL, 'Precaucion curva pronunciada'),
(70, 'SP-04', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-04.jpg', NULL, 2, 2, NULL, 'Precaucion curva pronunciada'),
(71, 'SP-05', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-05.jpg', NULL, 2, 2, NULL, 'Precaucion curva y contracurva peligrosa'),
(72, 'SP-06', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-06.jpg', NULL, 2, 2, NULL, 'Precaucion curva y contracurva peligrosa'),
(73, 'SP-07', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-07.jpg', NULL, 2, 2, NULL, 'Precaucion curvas sucesivas'),
(74, 'SP-08', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-08.jpg', NULL, 2, 2, NULL, 'Precaucion curvas sucesivas'),
(75, 'SP-09', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-09.jpg', NULL, 2, 2, NULL, 'Precaucion curva y contracurva pronunciada'),
(76, 'SP-10', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-10.jpg', NULL, 2, 2, NULL, 'Precaucion curva y contracurva pronunciada'),
(77, 'SP-11', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-11.jpg', NULL, 2, 2, NULL, 'Precaucion interseccion de vias'),
(78, 'SP-12', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-12.jpg', NULL, 2, 2, NULL, 'Precaucion Via lateral'),
(79, 'SP-13', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-13.jpg', NULL, 2, 2, NULL, 'Precaucion Via lateral'),
(80, 'SP-14', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-14.jpg', NULL, 2, 2, NULL, 'Precaucion Bifurcacion en T'),
(81, 'SP-15', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-15.jpg', NULL, 2, 2, NULL, 'Precaucion Bifucarcion en Y'),
(82, 'SP-16', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-16.jpg', NULL, 2, 2, NULL, 'Precaucion Bifucarcion'),
(83, 'SP-17', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-17.jpg', NULL, 2, 2, NULL, 'Precaucion Bifucarcion'),
(84, 'SP-18', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-18.jpg', NULL, 2, 2, NULL, 'Precaucion Bifurcacion Escalonada'),
(85, 'SP-19', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-19.jpg', NULL, 2, 2, NULL, 'Precaucion Bifurcacion Escalonada'),
(86, 'SP-20', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-20.jpg', NULL, 2, 2, NULL, 'Precaucion Glorieta'),
(87, 'SP-21', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-21.jpg', NULL, 2, 2, NULL, 'Precaucion Incorporacion de transito'),
(88, 'SP-22', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-22.jpg', NULL, 2, 2, NULL, 'Precaucion Incorporacion de transito'),
(89, 'SP-23', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-23.jpg', NULL, 2, 2, NULL, 'Precaucion Semaforo'),
(90, 'SP-24', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-24.jpg', NULL, 2, 2, NULL, 'Precaucion superficie rizada'),
(91, 'SP-25', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-25.jpg', NULL, 2, 2, NULL, 'Precaucion resalto'),
(92, 'SP-26', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-26.jpg', NULL, 2, 2, NULL, 'Precaucion depresion'),
(93, 'SP-27', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-27.jpg', NULL, 2, 2, NULL, 'Precaucion descenso peligroso'),
(94, 'SP-28', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-28.jpg', NULL, 2, 2, NULL, 'Precaucion reduccion simetrica de la calzada'),
(95, 'SP-29', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-29.jpg', NULL, 2, 2, NULL, 'Precaucion prevencion de pare'),
(96, 'SP-30', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-30.jpg', NULL, 2, 2, NULL, 'Precaucion asimetrica de la calzada'),
(97, 'SP-31', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-31.jpg', NULL, 2, 2, NULL, 'Precaucion asimetrica de la calzada'),
(98, 'SP-32', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-32.jpg', NULL, 2, 2, NULL, 'Precaucion ensanche simetrico de la calzada'),
(99, 'SP-33', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-33.jpg', NULL, 2, 2, NULL, 'Precaucion prevencion de ceda el paso'),
(100, 'SP-34', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-34.jpg', NULL, 2, 2, NULL, 'Precaucion ensanche asimetrico de la calzada'),
(101, 'SP-35', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-35.jpg', NULL, 2, 2, NULL, 'Precaucion ensanche asimetrico de la calzada'),
(102, 'SP-36', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-36.jpg', NULL, 2, 2, NULL, 'Precaucion puente angosto'),
(103, 'SP-37', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-37.jpg', NULL, 2, 2, NULL, 'Precaucion tunel'),
(104, 'SP-38', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-38.jpg', NULL, 2, 2, NULL, 'Precaucion peso maximo total permitido '),
(105, 'SP-39', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-39.jpg', NULL, 2, 2, NULL, 'Precaucion circulacion en dos sentidos'),
(106, 'SP-40', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-40.jpg', NULL, 2, 2, NULL, 'Precaucion flecha direccional'),
(107, 'SP-41', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-41.jpg', NULL, 2, 2, NULL, 'Precaucion tres carriles uno en contraflujo'),
(108, 'SP-42', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-42.jpg', NULL, 2, 2, NULL, 'Precaucion zona de derrumbe '),
(109, 'SP-43', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-43.jpg', NULL, 2, 2, NULL, 'Precaucion tres carriles dos en contraflujo'),
(110, 'SP-44', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-44.jpg', NULL, 2, 2, NULL, 'Precaucion superficie deslizante'),
(111, 'SP-45', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-45.jpg', NULL, 2, 2, NULL, 'Precaucion maquinaria agricola en la via'),
(112, 'SP-46', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-46.jpg', NULL, 2, 2, NULL, 'Precaucion peatones en la via'),
(113, 'SP-47', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-47.jpg', NULL, 2, 2, NULL, 'Precaucion zona escolar '),
(114, 'SP-48', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-48.jpg', NULL, 2, 2, NULL, 'Precaucion zona deportiva'),
(115, 'SP-49', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-49.jpg', NULL, 2, 2, NULL, 'Precaucion animales en la via'),
(116, 'SP-50', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-50.jpg', NULL, 2, 2, NULL, 'Precaucion altura libre'),
(117, 'SP-51', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-51.jpg', NULL, 2, 2, NULL, 'Precaucion ancho libre'),
(118, 'SP-52', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-52.jpg', NULL, 2, 2, NULL, 'Precaucion cruce a nivel con el ferrocarril'),
(119, 'SP-53', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-53.jpg', NULL, 2, 2, NULL, 'Precaucion barrera '),
(120, 'SP-54', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-54.jpg', NULL, 2, 2, NULL, 'Precaucion paso a nivel'),
(121, 'SP-55', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-55.jpg', NULL, 2, 2, NULL, 'Precaucion iniciacion de separador dos sentidos'),
(122, 'SP-55A', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-55A.jpg', NULL, 2, 2, NULL, 'Precaucion iniciacion de separador un sentido'),
(123, 'SP-56', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-56.jpg', NULL, 2, 2, NULL, 'Precaucion terminacion de via con separador (dos sentidos)'),
(124, 'SP-56A', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-56A.jpg', NULL, 2, 2, NULL, 'Precaucion terminacion de via con separador (un sentidos'),
(125, 'SP-57', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-57.jpg', NULL, 2, 2, NULL, 'Precaucion final del pavimento '),
(126, 'SP-59', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-59.jpg', NULL, 2, 2, NULL, 'Precaucion ciclistas en la via'),
(127, 'SP-67', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SP-67.jpg', NULL, 2, 2, NULL, 'Precaucion riesgos de accidente'),
(134, 'SI-01', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-01.jpg', NULL, 2, 3, NULL, 'Ruta Nacional'),
(135, 'SI-02', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-02.jpg', NULL, 2, 3, NULL, 'Ruta departamental'),
(136, 'SI-03', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-03.jpg', NULL, 2, 3, NULL, 'Ruta marginal de la selva'),
(137, 'SI-04', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-04.jpg', NULL, 2, 3, NULL, 'Poste de referencia'),
(138, 'SI-05', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-05.jpg', NULL, 2, 3, NULL, 'Informacion Previa de destino'),
(139, 'SI-05A', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-05A.jpg', NULL, 2, 3, NULL, 'Informativa de decision de destino'),
(140, 'SI-05B', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-05B.jpg', NULL, 2, 3, NULL, 'Croquis'),
(141, 'SI-05C', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-05C.jpg', NULL, 2, 3, NULL, 'Descripcion de giros'),
(142, 'SI-06', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-06.jpg', NULL, 2, 3, NULL, 'Confirmativa de destino'),
(143, 'SI-07', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-07.jpg', NULL, 2, 3, NULL, 'Sitio de parqueo'),
(144, 'SI-07A', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-07A.jpg', NULL, 2, 3, NULL, 'Zonas especiales de paqueo'),
(145, 'SI-08', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-08.jpg', NULL, 2, 3, NULL, 'Paradero de buses'),
(146, 'SI-09', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-09.jpg', NULL, 2, 3, NULL, 'Estacionamiento de Taxis'),
(147, 'SI-10', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-10.jpg', NULL, 2, 3, NULL, 'Transbordador'),
(148, 'SI-11', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-11.jpg', NULL, 2, 3, NULL, 'Vía para ciclistas'),
(149, 'SI-12', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-12.jpg', NULL, 2, 3, NULL, 'Monumento nacional'),
(150, 'SI-13', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-13.jpg', NULL, 2, 3, NULL, 'Zona militar'),
(151, 'SI-14', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-14.jpg', NULL, 2, 3, NULL, 'Aeropuerto'),
(152, 'SI-15', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-15.jpg', NULL, 2, 3, NULL, 'Hospedaje'),
(153, 'SI-16', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-16.jpg', NULL, 2, 3, NULL, 'Primeros auxilios'),
(154, 'SI-17', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-17.jpg', NULL, 2, 3, NULL, 'Servicios Sanitarios'),
(155, 'SI-18', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-18.jpg', NULL, 2, 3, NULL, 'Restaurante'),
(156, 'SI-19', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-19.jpg', NULL, 2, 3, NULL, 'Telefono'),
(157, 'SI-20', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-20.jpg', NULL, 2, 3, NULL, 'Iglesia'),
(158, 'SI-21', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-21.jpg', NULL, 2, 3, NULL, 'Taller'),
(159, 'SI-22', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-22.jpg', NULL, 2, 3, NULL, 'Estacion de servicio'),
(160, 'SI-23', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-23.jpg', NULL, 2, 3, NULL, 'Montallantas'),
(161, 'SI-24', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-24.jpg', NULL, 2, 3, NULL, 'Cruce peatonal'),
(162, 'SI-25', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-25.jpg', NULL, 2, 3, NULL, 'Discapacitados'),
(163, 'SI-26', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-26.jpg', NULL, 2, 3, NULL, 'Nomenclatura urbana'),
(164, 'SI-27', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-27.jpg', NULL, 2, 3, NULL, 'Seguro vial'),
(165, 'SI-28', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-28.jpg', NULL, 2, 3, NULL, 'Geográfica'),
(166, 'SI-29', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-29.jpg', NULL, 2, 3, NULL, 'Transporte Ferroviario'),
(167, 'SI-30', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-30.jpg', NULL, 2, 3, NULL, 'Transporte Masivo'),
(168, 'SI-31', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-31.jpg', NULL, 2, 3, NULL, 'Zona Recreativa'),
(169, 'SI-32', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-32.jpg', NULL, 2, 3, NULL, 'Cambio de moneda'),
(170, 'SI-33', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-33.jpg', NULL, 2, 3, NULL, 'Zona de camping'),
(171, 'SI-34', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-34.jpg', NULL, 2, 3, NULL, 'Playa'),
(172, 'SI-35', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-35.jpg', NULL, 2, 3, NULL, 'Museo'),
(173, 'SI-36', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-36.jpg', NULL, 2, 3, NULL, 'Muelle'),
(174, 'SI-37', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-37.jpg', NULL, 2, 3, NULL, 'Zoologico'),
(175, 'SI-38', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-38.jpg', NULL, 2, 3, NULL, 'Punto de Información Turística'),
(176, 'SI-39', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-39.jpg', NULL, 2, 3, NULL, 'Artesanías'),
(177, 'SI-40', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-40.jpg', NULL, 2, 3, NULL, 'Bienes Arqueológicos'),
(178, 'SI-41', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-41.jpg', NULL, 2, 3, NULL, 'Lago'),
(179, 'SI-42', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-42.jpg', NULL, 2, 3, NULL, 'Polideportivo'),
(180, 'SI-43', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-43.jpg', NULL, 2, 3, NULL, 'Mirador'),
(181, 'SI-44', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-44.jpg', NULL, 2, 3, NULL, 'Alquiler de autos'),
(182, 'SI-45', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-45.jpg', NULL, 2, 3, NULL, 'Atractivo natural'),
(183, 'SI-46', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-46.jpg', NULL, 2, 3, NULL, 'Volcán'),
(184, 'SI-47', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-47.jpg', NULL, 2, 3, NULL, 'Nevado'),
(185, 'SI-48', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-48.jpg', NULL, 2, 3, NULL, 'Termal'),
(186, 'SI-49', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-49.jpg', NULL, 2, 3, NULL, 'Cascada'),
(187, 'SI-50', 'http://localhost/coordenadas-ruta-segura/imagenes/Colombia/SI-50.jpg', NULL, 2, 3, NULL, 'Pesca');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `sonidos_geocerca`
--
ALTER TABLE `sonidos_geocerca`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhamdmcacf4m3xauitit06e51u` (`pais`),
  ADD KEY `FKogaaqfcocb2jqkbb2h8gbgs35` (`tipos`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `sonidos_geocerca`
--
ALTER TABLE `sonidos_geocerca`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=188;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `sonidos_geocerca`
--
ALTER TABLE `sonidos_geocerca`
  ADD CONSTRAINT `FKhamdmcacf4m3xauitit06e51u` FOREIGN KEY (`pais`) REFERENCES `paises` (`id`),
  ADD CONSTRAINT `FKogaaqfcocb2jqkbb2h8gbgs35` FOREIGN KEY (`tipos`) REFERENCES `tipo_senal` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
