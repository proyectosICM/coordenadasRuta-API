

INSERT INTO `empresas` (`id`, `nombre`, `password`, `usuario`) VALUES
(1, 'ICM', '1234', 'ICM');

INSERT INTO `paises` (`id`, `nombre`) VALUES
(1, 'Peru'),
(2, 'Colombia');

INSERT INTO `tipo_senal` (`id`, `nombre`) VALUES
(1, 'Reglamentarias'),
(2, 'Preventivas'),
(3, 'Informativas');

INSERT INTO `sonidos_velocidad` (`id`, `nombre`, `sonido_velocidad`, `codvel`) VALUES
(1, '20', 'http://localhost/coordenadas-ruta-segura/audios/00002.mp3', 2),
(2, '30', 'http://localhost/coordenadas-ruta-segura/audios/00003.mp3', 3),
(3, '40', 'http://localhost/coordenadas-ruta-segura/audios/00004.mp3', 4),
(4, '50', 'http://localhost/coordenadas-ruta-segura/audios/00005.mp3', 5),
(5, '60', 'http://localhost/coordenadas-ruta-segura/audios/00006.mp3', 6),
(6, '70', 'http://localhost/coordenadas-ruta-segura/audios/00007.mp3', 7),
(7, '80', 'http://localhost/coordenadas-ruta-segura/audios/00008.mp3', 8),
(8, 'Sin audio de velocidad', 'http://localhost/coordenadas-ruta-segura/audios/00000.mp3', 0);

