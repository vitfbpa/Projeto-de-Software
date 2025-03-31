-- Criação do banco de dados
CREATE DATABASE Restaurante;
USE Restaurante;

-- Tabela de Produtos (comidas e bebidas juntas)
CREATE TABLE Produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    tipo ENUM('Comida', 'Bebida') NOT NULL
);

-- Tabela de Comandas (agora sem data/hora e status)
CREATE TABLE Comandas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    total DECIMAL(10,2) DEFAULT 0.00
);

-- Tabela de Itens da Comanda
CREATE TABLE ItensComanda (
    id INT AUTO_INCREMENT PRIMARY KEY,
    comanda_id INT NOT NULL,
    produto_id INT NOT NULL,
    quantidade INT NOT NULL DEFAULT 1,
    preco_unitario DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (comanda_id) REFERENCES Comandas(id),
    FOREIGN KEY (produto_id) REFERENCES Produtos(id)
);

-- Inserção de dados básicos de produtos e bebidas (3 de cada)
INSERT INTO Produtos (nome, preco, tipo) VALUES
-- Comidas
('Hambúrguer', 15.90, 'Comida'),
('Pizza', 35.00, 'Comida'),
('Salada', 12.50, 'Comida'),

-- Bebidas
('Refrigerante', 6.50, 'Bebida'),
('Suco Natural', 8.00, 'Bebida'),
('Água Mineral', 3.50, 'Bebida');
