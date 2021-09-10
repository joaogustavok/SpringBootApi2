-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           8.0.26 - MySQL Community Server - GPL
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              11.3.0.6295
-- --------------------------------------------------------

SET FOREIGN_KEY_CHECKS=0;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Copiando dados para a tabela desafioapi2.autopsia: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `autopsia` DISABLE KEYS */;
INSERT INTO `autopsia` (`id`, `data`, `laudo`, `legista_id`, `vitima_id`) VALUES
	(1, '2021-09-09 13:21:33.745131', 'Morte', 1, 1),
	(2, '2021-09-09 13:22:03.771426', 'Assasinato', 2, 3),
	(3, '2021-09-09 13:22:29.878492', 'Desaparecimento', 2, 2);
/*!40000 ALTER TABLE `autopsia` ENABLE KEYS */;

-- Copiando dados para a tabela desafioapi2.crime: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `crime` DISABLE KEYS */;
INSERT INTO `crime` (`id`, `data`, `descricao`, `criminoso_id`, `vitima_id`) VALUES
	(1, '2021-09-09 13:30:12.649675', 'Assalto Relampago', 1, 2),
	(2, '2021-09-09 13:30:27.894640', 'Furto', 2, 1),
	(3, '2021-09-09 13:30:51.235699', 'Sequestro', 3, 3);
/*!40000 ALTER TABLE `crime` ENABLE KEYS */;

-- Copiando dados para a tabela desafioapi2.criminoso: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `criminoso` DISABLE KEYS */;
INSERT INTO `criminoso` (`id`, `cpf`, `nome`) VALUES
	(1, '67212693090', 'Eden Novais Leiria'),
	(2, '97417963072', 'Ludmila Couto Borba'),
	(3, '11044466006', 'Alyssa Moreira Medeiros'),
	(4, '94953884060', 'Raul Mata Caldeira');
/*!40000 ALTER TABLE `criminoso` ENABLE KEYS */;

-- Copiando dados para a tabela desafioapi2.delegacia: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `delegacia` DISABLE KEYS */;
INSERT INTO `delegacia` (`id`, `batalhao`, `bairro`, `cep`, `cidade`, `complemento`, `estado`, `logradouro`, `numero`, `telefone`) VALUES
	(1, 'Comando de Operações Especiais', 'Centro', '83840-000', 'Quitandinha', 'Predio Azul', 'Paraná', 'Av. Fernandes de Andrade', '1830', '41-35231242'),
	(2, 'Força Nacional de Segurança Pública', 'Vista Alegre', '83840-001', 'Quitandinha', '1 Andar', 'Paraná', 'Rua da America', '1203', '41-38582354');
/*!40000 ALTER TABLE `delegacia` ENABLE KEYS */;

-- Copiando dados para a tabela desafioapi2.delegado: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `delegado` DISABLE KEYS */;
INSERT INTO `delegado` (`id`, `funcional`, `nome`, `turno`, `delegacia_id`) VALUES
	(1, '98592256', 'Ricardo Gusto', 'Manha', 1),
	(2, '85832924', 'Arlindo Cruz', 'Tarde', 2),
	(3, '98295952', 'Merlin Rodrigo', 'Tarde', 1);
/*!40000 ALTER TABLE `delegado` ENABLE KEYS */;

-- Copiando dados para a tabela desafioapi2.legista: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `legista` DISABLE KEYS */;
INSERT INTO `legista` (`id`, `crm`, `nome`) VALUES
	(1, '920482', 'Kauã Sarmento Gadelha'),
	(2, '23232123', 'Ari Montenegro Dâmaso'),
	(3, '854452', 'Jacinto Castilho Canela');
/*!40000 ALTER TABLE `legista` ENABLE KEYS */;

-- Copiando dados para a tabela desafioapi2.perfil: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `perfil` DISABLE KEYS */;
INSERT INTO `perfil` (`id`, `nome`) VALUES
	(1, 'JUIZ'),
	(2, 'ADVOGADO'),
	(3, 'DELEGADO');
/*!40000 ALTER TABLE `perfil` ENABLE KEYS */;

-- Copiando dados para a tabela desafioapi2.policial: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `policial` DISABLE KEYS */;
INSERT INTO `policial` (`id`, `funcional`, `nome`, `patente`) VALUES
	(1, '23212323', 'Pedro Americo', 'Capitão'),
	(2, '28938422', 'Amauri Junior', 'Soldado'),
	(3, '23642682', 'Arcondes Filho', 'Cabo');
/*!40000 ALTER TABLE `policial` ENABLE KEYS */;

-- Copiando dados para a tabela desafioapi2.prisao: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `prisao` DISABLE KEYS */;
INSERT INTO `prisao` (`id`, `data`, `criminoso_id`, `delegacia_id`, `delegado_id`, `policial_id`, `vitima_id`) VALUES
	(1, '2021-09-09 13:33:50.437681', 1, 2, 2, 3, 2),
	(2, '2021-09-09 13:34:17.966723', 2, 1, 3, 1, 1),
	(3, '2021-09-09 13:34:41.582732', 3, 1, 1, 3, 3);
/*!40000 ALTER TABLE `prisao` ENABLE KEYS */;

-- Copiando dados para a tabela desafioapi2.usuario: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `email`, `senha`, `perfil_id`) VALUES
	(1, 'Lulay', '$2a$10$uRGMALzVYDzZSrl9u67NUuCePCJe5rOAbPtBTjuYqyf84t3A1F3Q2', 1),
	(2, 'bijip64665@rerunway.com', '$2a$10$uRGMALzVYDzZSrl9u67NUuCePCJe5rOAbPtBTjuYqyf84t3A1F3Q2', 2),
	(3, 'Delegado', '$2a$10$uRGMALzVYDzZSrl9u67NUuCePCJe5rOAbPtBTjuYqyf84t3A1F3Q2', 3);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;

-- Copiando dados para a tabela desafioapi2.vitima: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `vitima` DISABLE KEYS */;
INSERT INTO `vitima` (`id`, `cpf`, `nome`) VALUES
	(1, '52032891077', 'Carlos Andrade'),
	(2, '70822215098', 'Hugo Hape'),
	(3, '68472740005', 'Noemi Monte Onofre'),
	(4, '05416177020', 'Eliza Miguéis Lobo');
/*!40000 ALTER TABLE `vitima` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

SET FOREIGN_KEY_CHECKS=1;
