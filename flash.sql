-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 06/05/2025 às 03:35
-- Versão do servidor: 10.4.32-MariaDB
-- Versão do PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `flash`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `edicoes`
--

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

--
-- Despejando dados para a tabela `edicoes`
--

INSERT INTO `edicoes` (`id`, `imagem_id`, `preset_id`, `saturacao`, `exposicao`, `contraste`, `realce`, `sombras`, `brancos`, `pretos`, `textura`, `data_edicao`) VALUES
(1, 1, 1, 0, 10, 0, 0, 0, 5, 5, 0, '2025-04-29 19:47:53'),
(3, 1, 1, 3, 3, 3, 3, 3, 3, 5, 5, '2025-05-05 22:01:34');

-- --------------------------------------------------------

--
-- Estrutura para tabela `imagens`
--

CREATE TABLE `imagens` (
  `id` int(11) NOT NULL,
  `usuario_id` int(11) NOT NULL,
  `caminho_arquivo` varchar(255) NOT NULL,
  `data_upload` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `imagens`
--

INSERT INTO `imagens` (`id`, `usuario_id`, `caminho_arquivo`, `data_upload`) VALUES
(1, 1, 'uploads/pedro_foto1.png', '2025-04-29 19:47:53');

-- --------------------------------------------------------

--
-- Estrutura para tabela `presets`
--

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

--
-- Despejando dados para a tabela `presets`
--

INSERT INTO `presets` (`id`, `nome`, `usuario_id`, `saturacao`, `exposicao`, `contraste`, `realce`, `sombras`, `brancos`, `pretos`, `textura`) VALUES
(1, 'Look Suave', 1, 20, 0, 15, 0, 0, 0, 0, 10),
(7, 'Silhueta', NULL, -100, -2, -17, -45, -27, 32, 0, 100);

-- --------------------------------------------------------

--
-- Estrutura para tabela `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Despejando dados para a tabela `usuarios`
--

INSERT INTO `usuarios` (`id`, `nome`, `email`, `senha`) VALUES
(1, 'Pedro Haupt', 'pedro@example.com', '1234');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `edicoes`
--
ALTER TABLE `edicoes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `imagem_id` (`imagem_id`),
  ADD KEY `preset_id` (`preset_id`);

--
-- Índices de tabela `imagens`
--
ALTER TABLE `imagens`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Índices de tabela `presets`
--
ALTER TABLE `presets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `usuario_id` (`usuario_id`);

--
-- Índices de tabela `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT para tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `edicoes`
--
ALTER TABLE `edicoes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de tabela `imagens`
--
ALTER TABLE `imagens`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de tabela `presets`
--
ALTER TABLE `presets`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de tabela `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `edicoes`
--
ALTER TABLE `edicoes`
  ADD CONSTRAINT `edicoes_ibfk_1` FOREIGN KEY (`imagem_id`) REFERENCES `imagens` (`id`),
  ADD CONSTRAINT `edicoes_ibfk_2` FOREIGN KEY (`preset_id`) REFERENCES `presets` (`id`);

--
-- Restrições para tabelas `imagens`
--
ALTER TABLE `imagens`
  ADD CONSTRAINT `imagens_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);

--
-- Restrições para tabelas `presets`
--
ALTER TABLE `presets`
  ADD CONSTRAINT `presets_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
