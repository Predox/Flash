
# **_FLASH_** - Editor de Imagens - Projeto Java + Spring Boot

  

Este projeto é uma aplicação web para edição de imagens com funcionalidades como brilho, contraste, saturação, e aplicação de presets automáticos. Ele é composto por um backend Java (Spring Boot) e um frontend HTML+JavaScript.

  

## ✨ Funcionalidades

 - Login funcional

- Cadastro e Exclusão de Presets

- Listagem de Presets

- Upload de imagens

- Aplicação de efeitos: brilho, contraste, saturação, entre outros

- Presets automáticos pré-configurados

- Sistema MVC com banco de dados (JDBC)

- Comunicação dinâmica entre frontend e backend

  

## 🧱 Estrutura do Projeto

  

### Backend (Spring Boot)

  

-  **Controllers**: Recebem e processam requisições HTTP.

-  **JDBC Template**: Utilizado diretamente para consultas SQL.

-  **Sem camada de serviço**: Comunicação direta entre controller e banco de dados.

-  **PresetsController**: Responsável por listar e aplicar presets de edição.

  

### Frontend (HTML + JS)

- Interface intuitiva com:

  - Página de **upload**

  - Página de **edição**

  - Página de **login/cadastro**

- Botões para:

  - Aplicar efeitos

  - Escolher presets

  - Salvar alterações

  - Adicionar nova imagem

  

## 🔌 Endpoints de API

  
-  `POST /usuarios/login` → Fazer a busca e verificação do login

-  `POST /presets/adicionar` → Faz a criação de um novo Preset

-  `POST /presets/todos` → Lista todos os Presets disponíveis

-  `GET  /presets/{id}` → Busca um Preset específico

-  `DELETE  /presets/delete/{id}` → Deleta um Preset específico

  

## 📁 Organização das Classes

  

-  `Edicoes.java`: Define os métodos de edição de imagem.

-  `Imagens.java`: Gerencia imagens salvas e carregadas (Futuro Update).

-  `Presets.java`: Estrutura dos presets e comunicação com frontend.

-  `Usuarios.java`: Cadastro e autenticação de usuários.
  

## 🛠️ Como Rodar

  

### Requisitos

  

- Java 17+

- Maven

- MySQL

- Navegador Web

  

### Backend

  

```bash

cd  backend

./mvnw  spring-boot:run

```

### SQL

  

Pelo XAMPP incialize o Apache e o MySQL

- Clique em Admin no MySQL

Irá abrir o phpMyAdmin

Crie um Banco com o nome `flash`

Importe o arquivo flash.sql que está no repositório do Github
  



### Frontend

  

Após inicializar o Maeven, em seu navegador utilize o endereço `http://localhost:8080/`.

  




## 📸 Exemplos de Uso

  

1. Faça upload de uma imagem

2. Selecione um preset ou ajuste manualmente brilho/contraste/saturação...

3. Visualize a prévia em tempo real

4. Clique em “Salvar”

5. Para lista o Preset salvo basta abrir a opção de Presets 

  

## 📌 Status

  

🚧 Em desenvolvimento — novas funcionalidades e melhorias estão sendo adicionadas.

  

## 📧 Contato

  

Desenvolvido por Pedro Haupt

Email: pedro.henrique.haupt@gmail.com

Curso: Tecnólogo em Análise e Desenvolvimento de Sistemas
