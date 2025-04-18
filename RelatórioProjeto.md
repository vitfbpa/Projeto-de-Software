# 🍽️ Projeto-de-Software: Projeto Restaurante

## 📋 Relatório do Projeto Restaurante - Sistema de Gerenciamento para Restaurante

---

## 1. 🧠 Introdução

O objetivo principal é automatizar processos como **pedidos**, **controle de estoque** e **atendimento ao cliente**, melhorando a eficiência operacional do estabelecimento.

---

### 1.1 📊 Diagramas

#### 🎭 Caso de Uso - Descrição
![image](https://github.com/user-attachments/assets/bbfc0915-0879-4045-aedf-87d63f9f1ecc)

##### 📝 Modelo Descritivo do Caso de Uso
1. Sumário
caso de interação entre os atores Cliente e Funcionário no processo de pedidos e gerenciamento de comandas de uma lanchonete.

2. Atores
Cliente: Usuário que realiza pedidos no sistema.

Funcionário: Responsável pelo gerenciamento do estoque e pela verificação das comandas.

3. Pré-condição
cliente deve fazer o pedido pelo tablet vinculado a uma mesa, gerando uma comanda quando feito.

restaurante deve ter um cardápio disponível.

4. Fluxo Principal
 o cliente consulta o cardápio.

O cliente faz um pedido.

O sistema gera uma comanda.

O Funcionário verifica a comanda.

O Funcionário confirma a entrega do pedido.

5. Fluxo Secundário
O cliente pode adicionar mais produtos a comanda.

o funcionário pode ajustar a comanda antes da confirmação da entrega e após caso confirmação, relatando erro.

6. Fluxo de Exceção
se um item do pedido não estiver disponível no estoque, o funcionário informa ao cliente e solicita uma alteração no pedido.

se houver erro na comanda, o funcionário pode modificá-la se não estiver fechada.

7. Pós condição
O pedido é finalizado e entregue ao cliente.

comanda só pode ser fechada se todos pedidos com entrega confirmada nela estiverem pagos.

a comanda é fechada.

8. Regras de Negócio
todo pedido deve estar vinculado a uma comanda.

a confirmação da entrega deve ser feita em todos pedidos feitos e adicionados antes de dar ok para fechar a comanda.

O estoque deve ser atualizado após a entrega do pedido.

##### 📝 Diagrama de classes
![image](https://github.com/user-attachments/assets/4bb8c2d6-1c12-46b5-801f-fda96fe33769)


![image](https://github.com/user-attachments/assets/06435083-da1e-41b9-8bc6-c4b9c9f03d00)


![image](https://github.com/user-attachments/assets/1ffc657a-1b2e-4d6c-8ae4-2372485b0875)


#####  Diagrama de sequência

![image](https://github.com/user-attachments/assets/391d4436-6f2a-4d00-bc14-00f2a12691c8)

![image](https://github.com/user-attachments/assets/1d919f1c-d117-48e8-a1b3-68e0df0175a1)

![image](https://github.com/user-attachments/assets/7e6ad26c-10b7-4ddb-b243-cee2bdb85fbe)

**1. 📌 Sumário**  
Caso de interação entre os atores *Cliente* e *Funcionário* no processo de pedidos e gerenciamento de comandas de uma lanchonete.

**2. 👥 Atores**  
- **👤 Cliente**: Usuário que realiza pedidos no sistema.  
- **👨‍🍳 Funcionário**: Responsável pelo gerenciamento do estoque e pela verificação das comandas.

**3. ⚙️ Pré-condição**  
- O cliente deve fazer o pedido pelo tablet vinculado à mesa.  
- O restaurante deve ter um cardápio disponível no sistema.

**4. 🔁 Fluxo Principal**  
1. O cliente consulta o cardápio. 📱  
2. O cliente faz um pedido. 🍔  
3. O sistema gera uma comanda. 🧾  
4. O funcionário verifica a comanda. 👀  
5. O funcionário confirma a entrega do pedido, gerando um recibo do mesmo. ✅

**🔄 Fluxo Secundário**  
- O cliente pode adicionar mais produtos à comanda. ➕  
- O funcionário pode ajustar a comanda antes ou depois da entrega.

**⚠️ Fluxo de Exceção**  
- Se um item não estiver disponível no estoque, o funcionário informa o cliente.  
- Caso haja erro na comanda, o funcionário pode corrigi-la (se não estiver fechada).

**📦 Pós-condição**  
- O pedido é entregue.  
- A comanda é fechada apenas se todos os pedidos forem entregues e pagos. 💰

**📐 Regras de Negócio**  
- Todo pedido deve estar vinculado a uma comanda.  
- A entrega de todos os pedidos deve ser confirmada antes do fechamento.  
- O estoque é atualizado após cada entrega. 📉

---

## 2. ⚙️ Funcionalidades Principais

### 2.1 💡 Ideia de Funcionalidade

O cliente realiza seu pedido diretamente no sistema por meio de um tablet localizado na mesa.  
- Criação automática de uma **comanda** com os itens pedidos. 🧾  
- Geração de **recibo em `.txt`** com ID, produtos e valor total. 🧾  
- Controle de **estoque** com quantidade e disponibilidade dos produtos. 📦  
- Adição de itens em comandas já existentes. 🔄

### 2.2 📦 Cadastro e Gerenciamento de Produtos

#### 🍽️ Cardápio Digital
- Cadastro de pratos e bebidas.  
- Adição de produtos a comandas existentes.  
- Atualização de preços e disponibilidade.

#### 🏷️ Controle de Estoque
- Registro de produtos no banco de dados.  
- Adição de novos itens ao estoque.

#### 🚀 Fluxo de Atendimento
- Pedidos enviados à **cozinha** automaticamente. 🍳  
- Visualização das **comandas** com pedidos abertos. 🧾  
- Atualização do **status do pedido**. 🔄  
- Geração de **recibo** após pagamento. 💸

---

## 3. 🛢️ Banco de Dados SQL (Estrutura Principal)

### 🧱 Tabelas do banco `restaurante`:

| 🗂️ Tabela        | 📄 Descrição                                                                 |
|------------------|------------------------------------------------------------------------------|
| `comandas`       | Armazena as comandas criadas com seus respectivos IDs.                      |
| `itenscomanda`   | Contém os produtos adicionados nas comandas pelos clientes.                 |
| `produtos`       | Contém os produtos disponíveis no restaurante (comidas e bebidas).           |

---

## 4. 📁 Estrutura de Arquivos
```
├───build  
│   └───classes  
│       ├───dao  
│       ├───images  
│       ├───model  
│       │   └───dao  
│       └───view  
├───drivers  
├───nbproject  
│   └───private  
├───src  
│   ├───dao  
│   ├───images  
│   ├───model  
│   │   └───dao  
│   └───view
└───test
```
---

## 5. 👥 Autores

**Vicenzo de Souza**  
Curso de Ciência da Computação – Universidade Franciscana (UFN)  

E-mail: vicenzo.milanesi@ufn.edu.br 

GitHub: [@vitfbpa](https://github.com/vitfbpa)

---

**Anthony Guedes**  
Curso de Ciência da Computação – Universidade Franciscana (UFN)  

E-mail: anthony.guedes@ufn.edu.br

GitHub: [@AnthyGuedes](https://github.com/AnthyGuedes)

---
