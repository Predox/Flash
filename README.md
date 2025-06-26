# **_FLASH_** - Editor de Imagens - Projeto Java + Spring Boot

Este projeto é uma aplicação web para **edição de imagens** com funcionalidades como brilho, contraste, saturação, renomear imagens, e aplicação de presets automáticos. É composto por um backend Java (Spring Boot) e um frontend HTML+JavaScript, conectados a um banco de dados MySQL na nuvem.

## 🌐 Hospedagem

- **Frontend e Backend**: [Render](https://flash-ebm9.onrender.com)
- **Banco de Dados (MySQL)**: [Railway](https://railway.app)

---

## ✨ Funcionalidades

- Login com sessão ativa
- Cadastro, listagem e exclusão de Presets
- Upload de imagens e gerenciamento por usuário
- Aplicação de efeitos em imagens:
  - Brilho
  - Contraste
  - Saturação
  - Presets automáticos pré-configurados
- Visualização em tempo real
- Edição de nome da imagem diretamente pela interface
- Download e exclusão de imagens individuais
- Interface moderna e responsiva
- Sistema MVC com conexão JDBC ao banco de dados
- Comunicação dinâmica entre frontend e backend via Fetch API

---

## 🧱 Estrutura do Projeto

### Backend (Spring Boot)

- **Controllers**: Responsáveis por receber e processar requisições HTTP
- **JDBC Template**: Executa consultas diretamente ao MySQL
- **Sem camada de serviço**: comunicação direta Controller ↔ Banco
- **PresetsController**: Processamento e aplicação de efeitos
- **ImagensController**: Upload, listagem, renomeação, exclusão
- **UsuariosController**: Login e autenticação

### Frontend (HTML + JS)

- Interface responsiva com as seguintes páginas:
  - Página de **Login e Cadastro**
  - Página de **Upload de Imagem**
  - Página de **Edição e Visualização**
  - Página de **Listagem com botões (Renomear, Download, Deletar)**
  - Página "Sobre"
- Integração dinâmica via JavaScript e Fetch

---

## 🔌 Endpoints de API

- `POST /usuarios/login` → Login do usuário
- `POST /usuarios/cadastrar` → Cadastro de novo usuário
- `GET /usuarios/perfil/dados` → Dados do usuário logado
- `POST /presets/adicionar` → Criar novo Preset
- `GET /presets/todos` → Listar Presets disponíveis
- `GET /presets/{id}` → Buscar Preset específico
- `DELETE /presets/delete/{id}` → Deletar Preset
- `POST /imagens/upload` → Upload de imagem
- `GET /imagens/listar` → Listar imagens do usuário
- `GET /imagens/imagem/{id}` → Obter imagem por ID
- `DELETE /imagens/deletar/{id}` → Remover imagem
- `PUT /imagens/renomear/{id}` → Renomear imagem

---

## 📁 Organização das Classes

- `Edicoes.java`: Contém lógica para manipular brilho, contraste, saturação.
- `Imagens.java`: Gerencia estrutura e atributos das imagens.
- `Presets.java`: Representa os efeitos salvos como presets.
- `Usuarios.java`: Define estrutura e autenticação de usuários.

---

## 🛠️ Como Rodar

### Requisitos

- Java 17+
- Maven
- MySQL
- Navegador Web

### Backend (local)

No terminal da pasta raiz, execute:

```bash
mvn clean install
mvn spring-boot:run
```

---  

## 📧 Contato

Desenvolvido por Pedro Haupt

Email: pedro.henrique.haupt@gmail.com

Curso: Tecnólogo em Análise e Desenvolvimento de Sistemas
