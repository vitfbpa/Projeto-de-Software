-- Criação do banco de dados
CREATE DATABASE Restaurante;
USE Restaurante;

-- Tabela de Produtos (comidas e bebidas juntas)
CREATE TABLE Produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    tipo ENUM('Comida', 'Bebida') NOT NULL,
    estoque INT NOT NULL DEFAULT 0
);

-- Tabela de Comandas
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

-- Inserção de dados iniciais com estoque
INSERT INTO Produtos (nome, preco, tipo, estoque) VALUES
-- Comidas
('Hambúrguer', 15.90, 'Comida', 50),
('Pizza', 35.00, 'Comida', 30),
('Salada', 12.50, 'Comida', 20),

-- Bebidas
('Refrigerante', 6.50, 'Bebida', 60),
('Suco Natural', 8.00, 'Bebida', 40),
('Água Mineral', 3.50, 'Bebida', 100);
