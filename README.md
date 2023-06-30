# Login
Este projeto consiste em um aplicativo de cadastro e login, onde os usuários podem se cadastrar com nome de usuário, senha, email e nome completo. Eles também podem fazer login usando suas credenciais registradas. O objetivo principal deste projeto foi aprender mais sobre a utilização do SharedPreferences para salvar o status do usuário logado, mesmo após o encerramento do aplicativo.

<h3>Funcionalidades</h3>
O aplicativo possui as seguintes funcionalidades:

- Tela de Cadastro: Permite aos usuários preencher os campos de cadastro, como nome, nome de usuário, senha e email. Ao clicar no botão "Cadastrar", os dados são validados e um novo usuário é criado e armazenado no banco de dados. Se o usuário já existir, uma mensagem de erro é exibida.

- Tela de Login: Os usuários podem fazer login usando seu nome de usuário e senha. As credenciais são verificadas no banco de dados. Se as credenciais estiverem corretas, o login é efetuado e os usuários são redirecionados para a tela de detalhes. Caso contrário, uma mensagem de erro é exibida.

- Tela de Detalhes: Após fazer login com sucesso, os usuários são redirecionados para essa tela, onde suas informações de cadastro são exibidas, como nome, nome de usuário, email e senha. Eles também têm a opção de fazer logoff, que os redirecionará de volta para a tela de login.

<h3>Tecnologias Utilizadas</h3>
O projeto utiliza as seguintes tecnologias e conceitos:

- Kotlin: Linguagem de programação utilizada para desenvolver o aplicativo Android.

- Android: Plataforma utilizada para desenvolver o aplicativo.

- Android Jetpack: Conjunto de bibliotecas, ferramentas e diretrizes do Android para ajudar no desenvolvimento de aplicativos robustos e de alta qualidade. Algumas das bibliotecas utilizadas incluem:

  - Room: Biblioteca de persistência do Android que fornece uma camada de abstração sobre o SQLite, permitindo o acesso e a manipulação do banco de dados de maneira mais fácil.

  - Data Binding: Biblioteca que permite vincular os elementos da interface do usuário diretamente aos dados do aplicativo de forma declarativa.

  - Coroutines: Biblioteca para programação assíncrona baseada em Kotlin, facilitando o tratamento de operações de E/S e tarefas assíncronas.

  - SharedPreferences: Interface de armazenamento de pares de chave-valor simples que permite salvar e recuperar dados primitivos em um arquivo XML. Foi utilizada para salvar e recuperar o status do usuário logado, mesmo após o encerramento do aplicativo.

<h3>Como Executar o Projeto</h3>

Para executar o projeto, siga as etapas abaixo:

1. Clone o repositório para o seu ambiente local.

2. Abra o projeto no Android Studio.

3. Conecte um dispositivo Android ou inicie um emulador.

4. Execute o aplicativo pressionando o botão "Run" (Executar) no Android Studio.

O aplicativo será instalado e executado no dispositivo/emulador, onde você poderá interagir com as funcionalidades de cadastro e login.

| :placard: Vitrine.Dev |     |
| -------------  | --- |
| :sparkles: Nome        | **Login**
| :label: Tecnologias | Kotlin, Android, AndroidJetPack, coroutines, room, Data Binding, SharedPreferences
| :rocket: URL         | https://github.com/phtrebil/Login
| :fire: Desafio     | https://github.com/phtrebil/Login
