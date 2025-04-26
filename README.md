# Progetto di Gestione Utenti, Autenticazione e Blogpost

Questo è un progetto fullstack sviluppato con **React**, **Redux** e **Spring Boot**, che include funzionalità di login, registrazione, gestione degli utenti e creazione/gestione di blog post.

## Tecnologie

- **Frontend**:
    - React.js
    - Redux Toolkit
    - Axios per le chiamate API
    - React Router per la gestione delle rotte
    - TailwindCSS per il design
    - Js-Cookie per la gestione dei cookie di autenticazione
    - Toastify per le notifiche

- **Backend**:
    - Spring Boot (Java)
    - Spring Security per l'autenticazione
    - JWT (JSON Web Tokens) per la gestione delle sessioni
    - Postgres SQL Database (o altro database configurato)
    - JPA/Hibernate per l'accesso al database

## Funzionalità

### Frontend
- **Login/Registrazione**: Sistema completo di autenticazione con gestione dei cookie.
- **Dashboard Amministrativa**: Accesso protetto ai dati e alle funzionalità di amministrazione.
- **Gestione Blogposts**:
    - Creazione di nuovi articoli da parte degli utenti autenticati.
    - Modifica dei propri articoli.
    - Gli utenti amministratori possono visualizzare, modificare e cancellare **qualsiasi** articolo.
- **Routing Condizionale**: Accesso ai contenuti controllato in base ai permessi utente.
- **Toast Notifications**: Notifiche utente integrate.

### Backend
- **Autenticazione e Autorizzazione**:
    - Login, Registrazione, Logout.
    - Protezione delle rotte tramite JWT.
- **Gestione Utenti**:
    - Creazione e recupero informazioni utente.
    - Ruolo "ADMIN" per i privilegi di amministrazione.
- **Gestione Blogposts**:
    - Creazione, aggiornamento e cancellazione di articoli.
    - Gli amministratori possono gestire tutti i post, anche quelli creati da altri utenti.
- **Protezione delle API**:
    - Endpoint protetti accessibili solo con token JWT valido.

## Struttura del Progetto


## Prerequisiti

- **Node.js** (>=12.x)
- **npm** (>=6.x)
- **Java 8+**
- **Maven**
- **H2 Database** (o altro database)

## Avvio del Progetto

### Backend

```bash
git clone https://github.com/tuo-username/tuo-progetto.git
cd tuo-progetto/backend
mvn clean install
mvn spring-boot:run

cd ../frontend
npm install
npm start
