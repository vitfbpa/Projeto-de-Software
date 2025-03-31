-- Criação do banco de dados

CREATE DATABASE Restaurante;
USE Restaurante;

-- Tabela de Produtos (Lanches)
CREATE TABLE Produtos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    preco DECIMAL(10,2) NOT NULL
);

-- Tabela de Ingredientes
CREATE TABLE Ingredientes (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
);

-- Tabela de Bebidas
CREATE TABLE Bebidas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    preco DECIMAL(10,2) NOT NULL
);

-- Tabela de relação Produto-Ingrediente
CREATE TABLE ProdutoIngredientes (
    produto_id INT,
    ingrediente_id INT,
    PRIMARY KEY (produto_id, ingrediente_id),
    FOREIGN KEY (produto_id) REFERENCES Produtos(id),
    FOREIGN KEY (ingrediente_id) REFERENCES Ingredientes(id)
);

-- Inserindo produtos (lanches)
INSERT INTO Produtos (nome, preco) VALUES 
('Hambúrguer Simples', 10.00),
('Hambúrguer com Queijo', 12.50),
('X-Burger', 15.00),
('X-Tudo', 18.00);

-- Inserindo ingredientes básicos
INSERT INTO Ingredientes (nome) VALUES 
('Pão'),
('Hambúrguer de Carne'),
('Queijo'),
('Alface'),
('Tomate'),
('Molho Especial'),
('Bacon');

-- Inserindo bebidas simples
INSERT INTO Bebidas (nome, preco) VALUES 
('Água', 3.00),
('Refrigerante', 5.00),
('Suco', 6.00);

-- Definindo os ingredientes de cada produto
INSERT INTO ProdutoIngredientes (produto_id, ingrediente_id) VALUES 
(1, 1), -- Hambúrguer Simples: Pão
(1, 2), -- Hambúrguer Simples: Hambúrguer de Carne

(2, 1), -- Hambúrguer com Queijo: Pão
(2, 2), -- Hambúrguer com Queijo: Hambúrguer de Carne
(2, 3), -- Hambúrguer com Queijo: Queijo

(3, 1), -- X-Burger: Pão
(3, 2), -- X-Burger: Hambúrguer de Carne
(3, 3), -- X-Burger: Queijo

(4, 1), -- X-Tudo: Pão
(4, 2), -- X-Tudo: Hambúrguer de Carne
(4, 3), -- X-Tudo: Queijo

-- DAOs

-- Listar todos os lanches e seus preços:

SELECT nome, preco FROM Produtos;

-- Mostrar os ingredientes de um lanche específico:

SELECT i.nome 
FROM ProdutoIngredientes pi
JOIN Ingredientes i ON pi.ingrediente_id = i.id
WHERE pi.produto_id = 3; -- Ingredientes do X-Burger

-- Listar todas as bebidas disponíveis:

SELECT nome, preco FROM Bebidas;
