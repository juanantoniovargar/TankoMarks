-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-05-2023 a las 13:52:06
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tankomarks`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `capitulo`
--

CREATE TABLE `capitulo` (
  `id_capitulo` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `tomo_id_tomo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `capitulo`
--

INSERT INTO `capitulo` (`id_capitulo`, `nombre`, `tomo_id_tomo`) VALUES
(1, 'El guerrero negro', 3),
(2, 'El estigma', 3),
(3, 'Guardianes de la codicia (1)', 3),
(4, 'Guardianes de la codicia (2)', 4),
(5, 'Guardianes de la codicia (3)', 4),
(6, 'Guardianes de la codicia (4)', 5),
(7, 'Guardianes de la codicia (5)', 5),
(8, 'Guardianes de la codicia (6)', 5),
(9, 'La edad de oro (1)', 5),
(10, 'La edad de oro (2)', 6),
(11, 'La edad de oro (3)', 6),
(12, 'La edad de oro (4)', 6),
(13, 'La edad de oro (5)', 6),
(14, 'La edad de oro (6)', 6),
(15, 'La edad de oro (7)', 7),
(16, 'La edad de oro (8)', 7),
(17, 'Ráfaga de espada', 7),
(18, 'Nosferatu Zodd (1)', 7),
(19, 'Nosferatu Zodd (2)', 7),
(20, 'Nosferatu Zodd (3)', 7),
(21, 'Nosferatu Zodd (4)', 7),
(22, 'El dueño de la espada (1)', 7),
(23, 'El dueño de la espada (2)', 8),
(24, 'El asesino (1)', 8),
(25, 'El asesino (2)', 8),
(26, 'El asesino (3)', 8),
(27, 'El asesino (4)', 8),
(28, 'Aquello que nos importa', 8),
(29, 'A la guerra', 8),
(30, 'Batalla', 8),
(31, 'Casca (1)', 8),
(32, 'Casca (2)', 8),
(33, 'Casca (3)', 9),
(34, 'Dispuestos a morir (1)', 9),
(35, 'Dispuestos a morir (2)', 9),
(36, 'Dispuestos a morir (3)', 9),
(37, 'El regreso', 9),
(38, 'Hogueras de sueños', 9),
(39, 'La batalla de Doldrey (1)', 9),
(40, 'La batalla de Doldrey (2)', 9),
(41, 'La batalla de Doldrey (3)', 9),
(42, 'La batalla de Doldrey (4)', 9),
(43, 'La batalla de Doldrey (5)', 10),
(44, 'La batalla de Doldrey (6)', 10),
(45, 'Regreso triunfante', 10),
(46, 'Instante glorioso', 10),
(47, 'Lápida de fuego (1)', 10),
(48, 'Lápida de fuego (2)', 10),
(49, 'Una noche de nieve...', 10),
(50, 'La mañana de la partida (1)', 10),
(51, 'La mañana de la partida (2)', 10),
(52, 'La mañana de la partida (3)', 10),
(53, 'El Caballero de la Calavera', 11),
(54, 'El principio de una noche interminable', 11),
(55, 'Halcón derribado', 11),
(56, 'El fin del sueño', 11),
(57, 'El torneo', 11),
(58, 'Fugitivos', 11),
(59, 'El luchador', 11),
(60, 'Compañeros de armas', 11),
(61, 'Confesión', 11),
(62, 'Cicatrices (1)', 11),
(63, 'Cicatrices (2)', 11),
(64, 'Chispas en el filo', 12),
(65, 'Entrando en Wyndham (1)', 12),
(66, 'Entrando en Wyndham (2)', 12),
(67, 'Víspera festiva (1)', 12),
(68, 'Víspera festiva (2)', 12),
(69, 'Mil años de encierro', 12),
(70, 'Reencuentro en el abismo', 12),
(71, 'Senda de sangre', 12),
(72, 'Los Bakiraka (1)', 12),
(73, 'Los Bakiraka (2)', 12),
(74, 'Flores en el castillo de piedra', 12),
(75, 'El perro demoníaco (1)', 13),
(76, 'El perro demoníaco (2)', 13),
(77, 'El perro demoníaco (3)', 13),
(78, 'El perro demoníaco (4)', 13),
(79, 'El rugido de la bestia enloquecida', 13),
(80, 'El bosque de las atrocidades', 13),
(81, 'Lucha a muerte (1)', 13),
(82, 'Lucha a muerte (2)', 13),
(83, 'Con la armadura en el corazón', 13),
(84, 'Desde los cielos', 13),
(85, 'El regreso del Inmortal', 13),
(86, 'El réquiem del viento', 14),
(87, 'El ocaso de los guerreros', 14),
(88, 'El chico del callejón', 14),
(89, 'Eclipse', 14),
(90, 'El momento prometido', 14),
(91, 'Advenimiento', 14),
(92, 'Entes sobrenaturales', 14),
(93, 'El castillo', 14),
(94, 'Despedida', 14),
(95, 'El banquete', 14),
(96, 'Tempestad de muerte (1)', 15),
(97, 'Tempestad de muerte (2)', 15),
(98, 'El dios del abismo', 15),
(99, 'Sangre fresca', 15),
(100, 'Movimientos fetales', 15),
(101, 'Eclosión', 15),
(102, 'Grabado en la retina del ojo derecho', 15),
(103, 'Escape', 15),
(104, 'Despertar en una pesadilla', 15),
(105, 'A la carrera', 15),
(106, 'Declaración de intenciones', 15),
(107, 'Engendro maléfico', 16),
(108, 'Armamento', 16),
(109, 'Aquel que caza dragones', 16),
(110, 'El Guerrero Negro, de nuevo', 16),
(111, 'Los elfos del Valle de la Niebla', 16),
(112, 'Jill', 16),
(113, 'Llegan volando', 16),
(114, 'Larvas', 16),
(115, 'La reina', 17),
(116, 'Fuegos fatuos', 17),
(117, 'Pirkaf de los ojos rojos', 17),
(118, 'La niña de los recuerdos', 17),
(119, 'El mundo de los alados', 17),
(120, 'Guardianes (1)', 17),
(121, 'Guardianes (2)', 17),
(122, 'Los perseguidores', 17),
(123, 'El Valle de la Niebla (1)', 17),
(124, 'El Valle de la Niebla (2)', 17),
(125, 'Las crisálidas', 17),
(126, 'Monstruo', 18),
(127, 'El demonio volador', 18),
(128, 'Sangrienta noche estrellada', 18),
(129, 'La fina línea entre demonio y humano', 18),
(130, 'Luciérnaga', 18),
(131, 'Camino a casa', 18),
(132, 'El elfo en el cielo', 18),
(133, 'La Bestia Negra', 18),
(134, 'Los Caballeros de la Santa Cadena de Hierro (1)', 18),
(135, 'Los Caballeros de la Santa Cadena de Hierro (2)', 18),
(136, 'Ídolos vacíos', 18),
(137, 'Ojos que no ven', 19),
(138, 'Noche de milagros', 19),
(139, 'Idas y venidas', 19),
(140, 'La mañana de la verdad', 19),
(141, 'Revelación (1)', 19),
(142, 'Revelación (2)', 19),
(143, 'Revelación (3)', 19),
(144, 'Filo resquebrajado', 19),
(145, 'Rescoldos', 19),
(146, 'Hacia tierra sagrada (1)', 19),
(147, 'Hacia tierra sagrada (2)', 19),
(148, 'Patrulla kushana (1)', 20),
(149, 'Patrulla kushana (2)', 20),
(150, 'La sombra de la torre (1)', 20),
(151, 'La sombra de la torre (2)', 20),
(152, 'Hijos de las sombras', 20),
(153, 'Fanático', 20),
(154, 'Las entrañas de la tierra sagrada', 20),
(155, 'La bruja', 20),
(156, 'Camino de monstruos (1)', 20),
(157, 'Camino de monstruos (2)', 20),
(158, 'Columnas de fuego', 20),
(159, 'El guerrero negro en tierra sagrada', 21),
(160, 'Sin rumbo', 21),
(161, 'Joven ambicioso', 21),
(162, 'Gruta demoníaca', 21),
(163, 'Reencuentro', 21),
(164, 'Emboscada', 21),
(165, 'El precipicio', 21),
(166, 'Prisioneras', 21),
(167, 'Doncella de hierro', 21),
(168, 'La sangre de los muertos (1)', 21),
(169, 'La sangre de los muertos (2)', 21),
(170, 'Hilo de araña', 22),
(171, 'Aquellos que danzan en las alturas y aquellos que reptan por el suelo', 22),
(172, 'Hells Angels', 22),
(173, 'Aquel del fondo que carece de fondo', 22),
(174, 'Temerosos', 22),
(175, 'Presagios', 22),
(176, 'Mártires', 22),
(177, 'Derrumbe', 22),
(178, 'Siluetas de ideas (1)', 22),
(179, 'Siluetas de ideas (2)', 22),
(180, 'Siluetas de ideas (3)', 22),
(181, 'Pez volador', 23),
(182, 'Sacerdote monstruoso (1)', 23),
(183, 'Sacerdote monstruoso (2)', 23),
(184, 'El que se aferra y el que pugna', 23),
(185, 'Tsunami tenebroso (1)', 23),
(186, 'Tsunami tenebroso (2)', 23),
(187, 'Reverberación', 23),
(188, 'El descenso', 23),
(189, 'Amanecer', 23),
(190, 'Aparición', 23),
(191, 'Decisiones y partidas', 23),
(192, 'El mundo se desmorona', 24),
(193, 'Reencuentro en la colina de las espadas', 24),
(194, 'Guerrero bestial contra guerrero negro', 24),
(195, 'Invariable', 24),
(196, 'El prólogo de la crónica de guerra', 24),
(197, 'Kushan ataca', 24),
(198, 'Grito de guerra del viento (1)', 24),
(199, 'Grito de guerra del viento (2)', 24),
(200, 'Nieve y llamas/Primera parte', 24),
(201, 'Nieve y llamas/Segunda parte', 24),
(202, 'Travesía invernal (1)', 25),
(203, 'Travesía invernal (2)', 25),
(204, 'Tiempo desperdiciado', 25),
(205, 'Colmillos', 25),
(206, 'Reencuentro en la estepa', 25),
(207, 'Bestias de guerra', 25),
(208, 'El estandarte de la espada alada', 25),
(209, 'Alas de luz y tinieblas', 25),
(210, 'Noche de estrellas fugaces', 25),
(211, 'Como un bebé', 25),
(212, 'El trol', 26),
(213, 'La bruja', 26),
(214, 'La mansión del bosque encantado (1)', 26),
(215, 'La mansión del bosque encantado (2)', 26),
(216, 'El mundo fantasmagórico', 26),
(217, 'Piedra demoníaca', 26),
(218, 'Los elementales', 26),
(219, 'La aldea de Enoch', 26),
(220, 'Ambición y recuerdos', 26),
(221, 'Ataque trol', 26),
(222, 'La espada mágica', 27),
(223, 'El espejo de la culpa', 27),
(224, 'Magia', 27),
(225, 'El secreto del rezo', 27),
(226, 'Horda diabólica (1)', 27),
(227, 'Horda diabólica (2)', 27),
(228, 'Fuerte riada', 27),
(229, 'Chamán', 27),
(230, 'Enclave tenebroso (Qlifot)', 27),
(231, 'Contaminación', 27),
(232, 'Merecido', 28),
(233, 'Redención', 28),
(234, 'La ribera del averno', 28),
(235, 'La princesa ramera del mar de entrañas', 28),
(236, 'Compañeros', 28),
(237, 'Arañazos', 28),
(238, 'En llamas (1)', 28),
(239, 'En llamas (2)', 28),
(240, 'La armadura de Berserker (1)', 28),
(241, 'La armadura de Berserker (2)', 28),
(242, 'Dragón de fuego', 29),
(243, 'Al fondo de las llamas del averno', 29),
(244, 'Las llamas parten de viaje', 29),
(245, 'Ciudad maléfica', 29),
(246, 'Emperador del terror', 29),
(247, 'Los soldados demonio daka', 29),
(248, 'El caballero del diablo', 29),
(249, 'Dios demoníaco', 29),
(250, 'El despertar de la princesa durmiente', 29),
(251, 'El fragor de la marea', 29),
(252, 'La señal revelada', 30),
(253, 'Un niño bajo la luz de la luna', 30),
(254, 'Familiares', 30),
(255, 'Niebla inquietante', 30),
(256, 'Bestia marina (Makara)', 30),
(257, 'Rumor marino', 30),
(258, 'Superhombre', 30),
(259, 'Base naval', 30),
(260, 'Ciudad humana', 30),
(261, 'El milano y la lechuza en el muelle', 30),
(262, 'Estocadas', 31),
(263, 'Guerreros', 31),
(264, 'Una cena frugal', 31),
(265, 'Regreso al nido', 31),
(266, 'Vandimion', 31),
(267, 'En el jardín', 31),
(268, 'Un blanco lirio silvestre', 31),
(269, 'Madre', 31),
(270, 'El baile', 31),
(271, 'La sala de los pilares', 31),
(272, 'Duelo', 32),
(273, 'Paladín del área doctrinal', 32),
(274, 'El tigre sobrenatural', 32),
(275, 'Irrupción', 32),
(276, 'La jaula oxidada', 32),
(277, 'Declaración de guerra', 32),
(278, 'El ataque de las bestias sobrenaturales', 32),
(279, 'Revelación', 32),
(280, 'Ciudad de bestias sobrenaturales (1)', 32),
(281, 'Ciudad de bestias sobrenaturales (2)', 32),
(282, 'El puerto ardiente', 33),
(283, 'Rueda flameante', 33),
(284, 'La espada bestial', 33),
(285, 'El general ermitaño', 33),
(286, 'El hechicero oriental', 33),
(287, 'Creador de vórtices', 33),
(288, 'Estallido', 33),
(289, 'El emperador del relámpago', 33),
(290, 'Ataca el ejército demoníaco', 33),
(291, 'Nubarrones', 33),
(292, 'La bala en la recámara', 34),
(293, 'Levando anclas', 34),
(294, 'La gran invasión (1)', 34),
(295, 'La gran invasión (2)', 34),
(296, 'El vuelo', 34),
(297, 'Desgarro en el campo de batalla', 34),
(298, 'Vendaval', 34),
(299, 'El ejército regular de Midland', 34),
(300, 'El héroe', 34),
(301, 'A bordo', 34),
(302, 'En vano', 35),
(303, 'Batalla naval (1)', 35),
(304, 'Batalla naval (2)', 35),
(305, 'Rugidos desde las tinieblas', 35),
(306, 'Sueño premonitorio', 35),
(307, 'Niebla mortal', 35),
(308, 'Tinieblas mudas', 35),
(309, 'Éxodo', 35),
(310, 'El dios del fin del mundo', 35),
(311, 'Rugidos en el cielo', 35),
(312, 'El dios ciego', 36),
(313, 'Demonios fuera', 36),
(314, 'Batalla inhumana', 36),
(315, 'La sacerdotisa del Halcón', 36),
(316, 'Caos', 36),
(317, 'Vuelo', 36),
(318, 'Deslumbrado', 36),
(319, 'Grieta', 36),
(320, 'El inicio del mundo', 36),
(321, 'Fantasía', 36),
(322, 'Falconia', 37),
(323, 'El barco fantasma (1)', 37),
(324, 'El barco fantasma (2)', 37),
(325, 'El barco fantasma (3)', 37),
(326, 'La isla solitaria', 37),
(327, 'La chica del acantilado', 37),
(328, 'Moradores de un mar aciago', 37),
(329, 'Tentáculos humanos', 37),
(330, 'Barco tentáculo', 37),
(331, 'Luna llena (1)', 38),
(332, 'Luna llena (2)', 38),
(333, 'Guerrero bestial', 38),
(334, 'El dios marino (1)', 38),
(335, 'El dios marino (2)', 38),
(336, 'El dios marino (3)', 38),
(337, 'Corazón desbocado', 38),
(338, 'La voz que llama desde lo más profundo', 38),
(339, 'Merrow (Sirena)', 38),
(340, 'Merrow (Sirena) (2)', 39),
(341, 'Voces encadenadas', 39),
(342, 'Emersión', 39),
(343, 'Estrella fugaz', 39),
(344, 'La flor primaveral de días lejanos (1)', 39),
(345, 'La flor primaveral de días lejanos (2)', 39),
(346, 'La flor primaveral de días lejanos (3)', 39),
(347, 'Carruajes cubiertos', 39),
(348, 'El edén', 39),
(349, 'La capital de las personas', 40),
(350, 'El derecho divino de los reyes', 40),
(351, 'Pandemonio', 40),
(352, 'El puente de la partida', 40),
(353, 'El asesino en las tinieblas', 40),
(354, 'La capital real bajo la luz de la luna', 40),
(355, 'Batalla en la oscuridad', 40),
(356, 'Huida aérea', 40),
(357, 'Desembarco', 40),
(358, 'El muñeco en llamas', 41),
(359, 'El pueblo de las brujas', 41),
(360, 'Los Grandes Magos', 41),
(361, 'Elfhelm', 41),
(362, 'El Rey de la Tormenta de Pétalos', 41),
(363, 'El páramo tenebroso', 41),
(364, 'El corredor de los sueños', 41),
(365, 'Fragmentos de memoria', 41),
(366, 'El bosque de los cadáveres y los cedros de espino', 42),
(367, 'El origen del mal', 42),
(368, 'El último fragmento', 42),
(369, 'Despertar', 42),
(370, 'Bajo la luz del sol que se filtra entre la vegetación', 42),
(371, 'Gigantes', 42),
(372, 'Entrada triunfal al amanecer', 42),
(373, 'Los albores del imperio', 43),
(374, 'Obstáculo', 43),
(375, 'El jardín de los cerezos', 43),
(376, 'El desfiladero', 43),
(377, 'Visión mortal', 43),
(378, 'Mono saltarín', 43),
(379, 'Lágrima de rocío', 43);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `demografia`
--

CREATE TABLE `demografia` (
  `id_demografia` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `demografia`
--

INSERT INTO `demografia` (`id_demografia`, `nombre`) VALUES
(1, 'Seinen'),
(2, 'Shonen'),
(3, 'Shojo'),
(4, 'Josei');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `manga`
--

CREATE TABLE `manga` (
  `id_manga` int(11) NOT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `enlacefoto` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `demografia_id_demografia` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `manga`
--

INSERT INTO `manga` (`id_manga`, `descripcion`, `enlacefoto`, `nombre`, `demografia_id_demografia`) VALUES
(1, 'Perteneciente al género épico fantástico, está ambientada en una tierra inspirada y con amplias similitudes a la Europa de la Alta Edad Media y Edad Moderna. Cuenta la historia de Guts, un mercenario huérfano. La misma está dividida en dos partes: la primera cuenta su juventud y cómo conoce a Griffith, líder de un grupo mercenario llamado como la \"Banda del Halcón\". La segunda parte es su historia tras el fatídico Eclipse.', '../../../imagesDB/NewVolume_1.webp', 'Berserk', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `manga_propio`
--

CREATE TABLE `manga_propio` (
  `id_manga_propio` int(11) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `enlacefoto` varchar(255) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `demografia_id_demografia` int(11) NOT NULL,
  `usuario_id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `rol`
--

INSERT INTO `rol` (`id_rol`, `nombre`) VALUES
(1, 'USER'),
(2, 'ADMIN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tomo`
--

CREATE TABLE `tomo` (
  `id_tomo` int(11) NOT NULL,
  `enlacefoto` varchar(255) NOT NULL,
  `numero` int(11) NOT NULL,
  `manga_id_manga` int(11) DEFAULT NULL,
  `manga_propio_id_manga_propio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tomo`
--

INSERT INTO `tomo` (`id_tomo`, `enlacefoto`, `numero`, `manga_id_manga`, `manga_propio_id_manga_propio`) VALUES
(3, '../../../imagesDB/NewVolume_1.webp', 1, 1, NULL),
(4, '../../../imagesDB/NewVolume_2.webp', 2, 1, NULL),
(5, '../../../imagesDB/NewVolume_3.webp', 3, 1, NULL),
(6, '../../../imagesDB/NewVolume_4.webp', 4, 1, NULL),
(7, '../../../imagesDB/NewVolume_5.webp', 5, 1, NULL),
(8, '../../../imagesDB/NewVolume_6.webp', 6, 1, NULL),
(9, '../../../imagesDB/NewVolume_7.webp', 7, 1, NULL),
(10, '../../../imagesDB/NewVolume_8.webp', 8, 1, NULL),
(11, '../../../imagesDB/NewVolume_9.webp', 9, 1, NULL),
(12, '../../../imagesDB/NewVolume_10.webp', 10, 1, NULL),
(13, '../../../imagesDB/NewVolume_11.webp', 11, 1, NULL),
(14, '../../../imagesDB/NewVolume_12.webp', 12, 1, NULL),
(15, '../../../imagesDB/NewVolume_13.webp', 13, 1, NULL),
(16, '../../../imagesDB/NewVolume_14.webp', 14, 1, NULL),
(17, '../../../imagesDB/NewVolume_15.webp', 15, 1, NULL),
(18, '../../../imagesDB/NewVolume_16.webp', 16, 1, NULL),
(19, '../../../imagesDB/Volume_17.webp', 17, 1, NULL),
(20, '../../../imagesDB/Volume_18.webp', 18, 1, NULL),
(21, '../../../imagesDB/Volume_19.webp', 19, 1, NULL),
(22, '../../../imagesDB/Volume_20.webp', 20, 1, NULL),
(23, '../../../imagesDB/Volume_21.webp', 21, 1, NULL),
(24, '../../../imagesDB/Volume_22.webp', 22, 1, NULL),
(25, '../../../imagesDB/Volume_23.webp', 23, 1, NULL),
(26, '../../../imagesDB/Volume_24.webp', 24, 1, NULL),
(27, '../../../imagesDB/Volume_25.webp', 25, 1, NULL),
(28, '../../../imagesDB/Volume_26.webp', 26, 1, NULL),
(29, '../../../imagesDB/Volume_27.webp', 27, 1, NULL),
(30, '../../../imagesDB/Volume_28.webp', 28, 1, NULL),
(31, '../../../imagesDB/Volume_29.webp', 29, 1, NULL),
(32, '../../../imagesDB/Volume_30.webp', 30, 1, NULL),
(33, '../../../imagesDB/Volume_31.webp', 31, 1, NULL),
(34, '../../../imagesDB/Volume_32.webp', 32, 1, NULL),
(35, '../../../imagesDB/Volume_33.webp', 33, 1, NULL),
(36, '../../../imagesDB/Volume_34.webp', 34, 1, NULL),
(37, '../../../imagesDB/Volume_35.webp', 35, 1, NULL),
(38, '../../../imagesDB/Volume_36.webp', 36, 1, NULL),
(39, '../../../imagesDB/Volumen_37.webp', 37, 1, NULL),
(40, '../../../imagesDB/Volumen_38.webp', 38, 1, NULL),
(41, '../../../imagesDB/Volumen_39.webp', 39, 1, NULL),
(42, '../../../imagesDB/Volumen_40.webp', 40, 1, NULL),
(43, '../../../imagesDB/Volumen_41.webp', 41, 1, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `email`, `nombre`, `password`) VALUES
(1, 'juan@gmail.com', 'juan', '$2a$10$lH9BC7rQhF9G1D1micyXVuAO/nfQHRkomarvBgXUQ6RyrWkTH6Dbm'),
(2, 'juan2@gmail.com', 'a', '$2a$10$xY7Y0A2borUyFRxqzKDDjObHwJHb7keukUJS4.m61fPG8g/EPeAq2'),
(3, 'juan3@gmail.com', 'antoino', '$2a$10$qWVzPotH186wW7YZ3iTTr..r2NZuAkmTAMFMbJJcaDoYEeWP4Oypu'),
(4, 'juan1@gmail.com', 'juanfcxh', '$2a$10$ktyZUwCO4mfBMs5e3XB7Ze1VUzV6njMWBAd8TiqrmQz4CjCOKOGLu'),
(5, 'juan10@gmail.com', 'juan', '$2a$10$Mf0frgDiWj/6ZBC.4D6bUeCjeLp2sIdsfe1z.ay0Xa54cmkUlM1Tq'),
(7, 'juan11@gmail.com', 'juan', '$2a$10$/QqoZWR.EJ6ZpbKGA3pBlOrekt.QJBpCYnyFPyWX5RQfMHbYXt7DC'),
(9, '989', '8', '$2a$10$D2MBm0tx10BGn9gmAW43AuGU1fGzkPq.gMbwI3N98IxBXjYh5f46i');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios_roles`
--

CREATE TABLE `usuarios_roles` (
  `usuario_id_usuario` int(11) NOT NULL,
  `rol_id_rol` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios_roles`
--

INSERT INTO `usuarios_roles` (`usuario_id_usuario`, `rol_id_rol`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(7, 1),
(9, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valoracion`
--

CREATE TABLE `valoracion` (
  `id_valoracion` int(11) NOT NULL,
  `comentario` varchar(255) DEFAULT NULL,
  `favorito` bit(1) DEFAULT NULL,
  `capitulo_id_capitulo` int(11) NOT NULL,
  `usuario_id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `capitulo`
--
ALTER TABLE `capitulo`
  ADD PRIMARY KEY (`id_capitulo`),
  ADD KEY `FKggpcktv7469fnwvdo1fw2gqkw` (`tomo_id_tomo`);

--
-- Indices de la tabla `demografia`
--
ALTER TABLE `demografia`
  ADD PRIMARY KEY (`id_demografia`);

--
-- Indices de la tabla `manga`
--
ALTER TABLE `manga`
  ADD PRIMARY KEY (`id_manga`),
  ADD KEY `FK7x9a1p2dn2na64pebvdn7vpqu` (`demografia_id_demografia`);

--
-- Indices de la tabla `manga_propio`
--
ALTER TABLE `manga_propio`
  ADD PRIMARY KEY (`id_manga_propio`),
  ADD KEY `FKeh9raourf41aw4kswu06qxnaw` (`demografia_id_demografia`),
  ADD KEY `FKeyhyptqutby8kx37qhwbe8776` (`usuario_id_usuario`);

--
-- Indices de la tabla `rol`
--
ALTER TABLE `rol`
  ADD PRIMARY KEY (`id_rol`);

--
-- Indices de la tabla `tomo`
--
ALTER TABLE `tomo`
  ADD PRIMARY KEY (`id_tomo`),
  ADD KEY `FK64645tatrxascla2r6t9s67ug` (`manga_id_manga`),
  ADD KEY `FK35rnpnojpf5lj6cif2cglj9wi` (`manga_propio_id_manga_propio`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `UK5171l57faosmj8myawaucatdw` (`email`);

--
-- Indices de la tabla `usuarios_roles`
--
ALTER TABLE `usuarios_roles`
  ADD KEY `FKnq37geovk6b7j58f6q0d55cq5` (`rol_id_rol`),
  ADD KEY `FKqcw8pep8pjpgmpv8dfk0jik1i` (`usuario_id_usuario`);

--
-- Indices de la tabla `valoracion`
--
ALTER TABLE `valoracion`
  ADD PRIMARY KEY (`id_valoracion`),
  ADD KEY `FKomn74yjvxiwmyv06hh7nfkr9w` (`capitulo_id_capitulo`),
  ADD KEY `FKotemi0bl7p9kony0kqnt3qrrv` (`usuario_id_usuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `capitulo`
--
ALTER TABLE `capitulo`
  MODIFY `id_capitulo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=380;

--
-- AUTO_INCREMENT de la tabla `demografia`
--
ALTER TABLE `demografia`
  MODIFY `id_demografia` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `manga`
--
ALTER TABLE `manga`
  MODIFY `id_manga` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `manga_propio`
--
ALTER TABLE `manga_propio`
  MODIFY `id_manga_propio` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `rol`
--
ALTER TABLE `rol`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `tomo`
--
ALTER TABLE `tomo`
  MODIFY `id_tomo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `valoracion`
--
ALTER TABLE `valoracion`
  MODIFY `id_valoracion` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `capitulo`
--
ALTER TABLE `capitulo`
  ADD CONSTRAINT `FKggpcktv7469fnwvdo1fw2gqkw` FOREIGN KEY (`tomo_id_tomo`) REFERENCES `tomo` (`id_tomo`);

--
-- Filtros para la tabla `manga`
--
ALTER TABLE `manga`
  ADD CONSTRAINT `FK7x9a1p2dn2na64pebvdn7vpqu` FOREIGN KEY (`demografia_id_demografia`) REFERENCES `demografia` (`id_demografia`);

--
-- Filtros para la tabla `manga_propio`
--
ALTER TABLE `manga_propio`
  ADD CONSTRAINT `FKeh9raourf41aw4kswu06qxnaw` FOREIGN KEY (`demografia_id_demografia`) REFERENCES `demografia` (`id_demografia`),
  ADD CONSTRAINT `FKeyhyptqutby8kx37qhwbe8776` FOREIGN KEY (`usuario_id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `tomo`
--
ALTER TABLE `tomo`
  ADD CONSTRAINT `FK35rnpnojpf5lj6cif2cglj9wi` FOREIGN KEY (`manga_propio_id_manga_propio`) REFERENCES `manga_propio` (`id_manga_propio`),
  ADD CONSTRAINT `FK64645tatrxascla2r6t9s67ug` FOREIGN KEY (`manga_id_manga`) REFERENCES `manga` (`id_manga`);

--
-- Filtros para la tabla `usuarios_roles`
--
ALTER TABLE `usuarios_roles`
  ADD CONSTRAINT `FKnq37geovk6b7j58f6q0d55cq5` FOREIGN KEY (`rol_id_rol`) REFERENCES `rol` (`id_rol`),
  ADD CONSTRAINT `FKqcw8pep8pjpgmpv8dfk0jik1i` FOREIGN KEY (`usuario_id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `valoracion`
--
ALTER TABLE `valoracion`
  ADD CONSTRAINT `FKomn74yjvxiwmyv06hh7nfkr9w` FOREIGN KEY (`capitulo_id_capitulo`) REFERENCES `capitulo` (`id_capitulo`),
  ADD CONSTRAINT `FKotemi0bl7p9kony0kqnt3qrrv` FOREIGN KEY (`usuario_id_usuario`) REFERENCES `usuario` (`id_usuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
