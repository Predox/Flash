USE railway;

-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
-- Host: 127.0.0.1
-- Tempo de geração: 23/06/2025 às 18:54
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;




CREATE TABLE `edicoes` (
  `id` int(11) NOT NULL,
  `imagem_id` int(11) NOT NULL,
  `preset_id` int(11) DEFAULT NULL,
  `saturacao` int(11) DEFAULT 0,
  `exposicao` int(11) DEFAULT 0,
  `contraste` int(11) DEFAULT 0,
  `realce` int(11) DEFAULT 0,
  `sombras` int(11) DEFAULT 0,
  `brancos` int(11) DEFAULT 0,
  `pretos` int(11) DEFAULT 0,
  `textura` int(11) DEFAULT 0,
  `data_edicao` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `imagens` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `imagem` longblob DEFAULT NULL,
  `data_upload` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



CREATE TABLE `presets` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `usuario_id` int(11) DEFAULT NULL,
  `saturacao` int(11) DEFAULT 0,
  `exposicao` int(11) DEFAULT 0,
  `contraste` int(11) DEFAULT 0,
  `realce` int(11) DEFAULT 0,
  `sombras` int(11) DEFAULT 0,
  `brancos` int(11) DEFAULT 0,
  `pretos` int(11) DEFAULT 0,
  `textura` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `presets` (`id`, `nome`, `usuario_id`, `saturacao`, `exposicao`, `contraste`, `realce`, `sombras`, `brancos`, `pretos`, `textura`) VALUES
(1, 'Look Suave', 1, 20, 0, 15, 0, 0, 0, 0, 10),
(7, 'Silhueta', NULL, -100, -2, -17, -45, -27, 32, 0, 100),
(13, '++', NULL, 20, 0, 15, 0, 37, 0, 0, 10),
(48, 'teste', NULL, 25, 25, 25, 25, 25, 67, 67, 25),
(53, 'exposição', NULL, 0, -54, 0, 0, 0, 0, 0, 0),
(54, 'oi', NULL, 84, 0, -71, -61, 52, 72, 27, -56),
(55, 'a', NULL, 0, 0, 0, 0, 0, 0, 0, 50),
(56, 'a', NULL, 0, 0, 71, 0, 27, 0, 0, 0),
(57, 'bb', NULL, 0, 0, 0, 0, 0, 0, 0, 0);



CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `usuarios` (`id`, `nome`, `email`, `senha`) VALUES
(1, 'Pedro Haupt', 'pedro@example.com', '1234'),
(2, 'Afonso', 'afonso@gmail.com', 'a12');


ALTER TABLE `edicoes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `imagem_id` (`imagem_id`),
  ADD KEY `preset_id` (`preset_id`);

ALTER TABLE `imagens`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

ALTER TABLE `presets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);


ALTER TABLE `edicoes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

ALTER TABLE `imagens`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

ALTER TABLE `presets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;


ALTER TABLE `edicoes`
  ADD CONSTRAINT `edicoes_ibfk_1` FOREIGN KEY (`imagem_id`) REFERENCES `imagens` (`id`),
  ADD CONSTRAINT `edicoes_ibfk_2` FOREIGN KEY (`preset_id`) REFERENCES `presets` (`id`);

ALTER TABLE `imagens`
  ADD CONSTRAINT `imagens_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

ALTER TABLE `presets`
  ADD CONSTRAINT `presets_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
