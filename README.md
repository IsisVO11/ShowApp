# 🎬 ShowApp - Gerenciador de Séries e Filmes

[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-7F52FF?style=for-the-badge&logo=kotlin)](https://kotlinlang.org)
[![Android Studio](https://img.shields.io/badge/Android_Studio-2023.1-3DDC84?style=for-the-badge&logo=android-studio)](https://developer.android.com)
[![Room Database](https://img.shields.io/badge/Room_Database-2.5.2-4285F4?style=for-the-badge&logo=android)](https://developer.android.com/training/data-storage/room)
[![MVVM](https://img.shields.io/badge/Architecture-MVVM-FF6D00?style=for-the-badge)](https://developer.android.com/topic/architecture)

Aplicativo Android nativo para gerenciamento e acompanhamento de séries e filmes. Desenvolvido com Kotlin seguindo as melhores práticas de arquitetura Android.

<p align="center">
  <img src="https://github.com/IsisVO11/ShowApp/raw/main/screenshots/app_icon.png" width="200" alt="ShowApp Icon">
</p>

## ✨ Funcionalidades

- **📺 Gerenciamento de Conteúdo**: Adicione séries e filmes à sua lista
- **📊 Acompanhamento de Progresso**: Marque episódios assistidos e temporadas completas
- **⭐ Sistema de Avaliação**: Avalie conteúdos de 1 a 5 estrelas
- **🔍 Busca Inteligente**: Encontre rapidamente seus conteúdos favoritos
- **📱 Interface Responsiva**: Design adaptado para diferentes tamanhos de tela
- **💾 Armazenamento Local**: Dados persistidos localmente com Room Database
- **🌐 Modo Offline**: Funcionalidade completa sem necessidade de conexão internet

## 🛠️ Tecnologias Utilizadas

### Linguagens & Frameworks
- **Kotlin 1.9.0**: Linguagem principal de desenvolvimento
- **Android SDK**: API level 28+
- **Jetpack Components**: Conjunto de bibliotecas modernas para Android

### Arquitetura & Componentes
- **MVVM (Model-View-ViewModel)**: Padrão arquitetural
- **Room Database 2.5.2**: Persistência de dados local
- **LiveData**: Observação de dados para atualização da UI
- **ViewModel**: Gerenciamento de dados relacionado à UI
- **Coroutines**: Programação assíncrona e não-bloqueante

### Interface do Usuário
- **Material Design 3**: Design system moderno
- **XML Layouts**: Interface declarativa
- **RecyclerView**: Listas eficientes e scrolláveis
- **ViewBinding**: Vinculação segura de views

## 📁 Estrutura do Projeto
ShowApp/

├── app/

│ ├── src/

│ │ ├── main/

│ │ │ ├── java/com/isisvo/showapp/

│ │ │ │ ├── data/

│ │ │ │ │ ├── dao/ # Data Access Objects

│ │ │ │ │ ├── database/ # Configuração do Room

│ │ │ │ │ ├── entities/ # Entidades de dados

│ │ │ │ │ └── repository/ # Repositórios de dados

│ │ │ │ ├── ui/

│ │ │ │ │ ├── main/ # Activity principal

│ │ │ │ │ ├── addshow/ # Adicionar conteúdo

│ │ │ │ │ ├── detail/ # Detalhes do conteúdo

│ │ │ │ │ └── viewmodels/ # ViewModels

│ │ │ │ ├── utils/ # Utilidades e extensões

│ │ │ │ └── ShowApplication.kt # Classe Application

│ │ │ ├── res/

│ │ │ │ ├── layout/ # XML layouts

│ │ │ │ ├── drawable/ # Ícones e imagens

│ │ │ │ ├── values/ # Cores, strings, styles

│ │ │ │ └── navigation/ # Navegação (se usar Navigation Component)

│ │ │ └── AndroidManifest.xml

│ │ └── test/ # Testes unitários

│ │ └── java/com/isisvo/showapp/

├── build.gradle # Configuração do módulo app

├── proguard-rules.pro # Regras de ofuscação

└── README.md


## 🚀 Como Executar o Projeto

### Pré-requisitos
- Android Studio Giraffe (2023.1) ou superior
- SDK Android API 28 (Android 9.0) ou superior
- Dispositivo Android ou emulador com API 28+

### Instalação e Execução
1. **Clone o repositório**

   git clone https://github.com/IsisVO11/ShowApp.git
   cd ShowApp
Abra no Android Studio

File → Open → Selecione a pasta do projeto

Aguarde a sincronização do Gradle

Execute o aplicativo

Conecte um dispositivo Android ou inicie um emulador

Clique em Run → Run 'app' (Shift+F10)

**Build do APK**

### Gerar APK de debug
./gradlew assembleDebug

### O APK será gerado em: app/build/outputs/apk/debug/

## 💡 Funcionalidades Técnicas Detalhadas
### Persistência de Dados
Room Database: Armazenamento local com SQLite

Entities: ShowEntity com campos para título, tipo, temporadas, etc.

DAO: Operações CRUD com queries otimizadas

Migrations: Suporte a atualizações do schema

### Arquitetura MVVM
View: Activities e Fragments com ViewBinding

ViewModel: Mantém dados durante mudanças de configuração

Model: Entidades e repositórios de dados

LiveData: Atualização automática da UI quando dados mudam

### Interface do Usuário
Material Design 3: Temas claros e escuros

RecyclerView com Adapter: Listas eficientes

Custom Dialogs: Para adicionar/editar conteúdos

Progress Trackers: Visualização de progresso por temporada

## 🤝 Como Contribuir
Contribuições são bem-vindas! Siga estos passos:

Faça um Fork do projeto

Crie uma Branch para sua Feature (git checkout -b feature/AmazingFeature)

Commit suas Mudanças (git commit -m 'Add some AmazingFeature')

Push para a Branch (git push origin feature/AmazingFeature)

Abra um Pull Request

## 📝 Licença
Este projeto está sob a licença MIT. Veja o arquivo LICENSE para detalhes.

## 👩💻 Autora
Isis Venturin - LinkedIn: https://www.linkedin.com/in/isis-venturin-b72443296/ | GitHub: https://github.com/IsisVO11

## 🔮 Próximas Funcionalidades (Roadmap)
Sincronização com API TMDB (The Movie Database)

Notificações para novos episódios

Backup em nuvem (Firebase ou Drive)

Widgets da homescreen

Modo assistir com amigos

Recomendações personalizadas

### Desenvolvido com ❤️ usando Kotlin e Android Jetpack
