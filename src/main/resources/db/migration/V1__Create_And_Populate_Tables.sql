CREATE TABLE IF NOT EXISTS `pessoa`
(
	`id` BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL UNIQUE,
	`nome` VARCHAR(50) NOT NULL,
	`nascimento` DATE NOT NULL,
	`end_principal` BIGINT,
	PRIMARY KEY (`id`)
);

CREATE TABLE IF NOT EXISTS `endereco`
(
	`id` BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL UNIQUE,
	`logradouro` VARCHAR(255) NOT NULL,
	`cidade` VARCHAR(50) NOT NULL,
	`numero` INT,
	`principal` BIT,
	`cep` VARCHAR(10),
	`pessoa_id` BIGINT,
	PRIMARY KEY (`id`),
	CONSTRAINT `fk_pessoa` FOREIGN KEY (`pessoa_id`) REFERENCES `pessoa` (`id`)
);

INSERT INTO `pessoa` (`id`, `nome`, `nascimento`) VALUES
	(1, 'Bruce Wayne', '1970-01-01'),
	(2, 'Barry Allen', '1994-03-25'),
	(3, 'Scoty Summers', '1988-12-31'),
	(4, 'Jean Gray', '1987-09-25'),
	(5, 'Klark Kent', '1966-02-23');
	

INSERT INTO `endereco` (`id` , `logradouro`, `cidade`, `numero`, `cep`, `principal`, `pessoa_id`) VALUES
	(1, 'BatCaverna', 'Gotam',15, '10100000',1, 1),
	(2, 'Fazenda dos Kent', 'Smalville',2699, '10100000',1, 5),
	(3, 'Torre Wayne', 'Gotam', 515, '10100000',0, 1),
	(4, 'Mansão Xavier', 'Mutante',1020, '10100000',1, 3),
	(5, 'Mansão Xavier', 'Mutante',01, '10100000',1, 4),
	(6, 'Laboratório', 'Central City',393393, '10100000',1, 2);
	
