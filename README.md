# BloggingWebAPI
Una Web API di blogging + feed rss
# Progetto di Gestione Utenti e Autenticazione

Questo è un progetto di gestione utenti e autenticazione sviluppato con **React**, **Redux** e un backend in **Java** (Spring Boot). Il progetto include funzionalità di login, registrazione, dashboard per gli amministratori e gestione dei dati utente.

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
    - H2 Database (per il database di sviluppo)
    - JWT (JSON Web Tokens) per la gestione dei token di autenticazione

## Funzionalità

### Frontend
- **Login/Registrazione**: Gli utenti possono registrarsi e fare login. In caso di successo, ricevono un token di accesso salvato nei cookie.
- **Dashboard**: Gli utenti amministratori possono accedere a una dashboard protetta.
- **Routing Condizionale**: Solo gli utenti con il ruolo "ADMIN" possono accedere a determinate aree. Altrimenti vengono reindirizzati alla pagina di accesso negato.
- **Toast Notifications**: Notifiche per eventi come il login, la registrazione o errori.

### Backend
- **Autenticazione**: Gli utenti possono registrarsi e fare login. I JWT vengono utilizzati per la protezione delle API.
- **Ruoli Utente**: Gli utenti possono avere ruoli diversi (e.g., "ADMIN").
- **Protezione delle Rotte**: Alcune rotte sono protette da Spring Security e richiedono un token di accesso valido.

## Struttura del Progetto


## Prerequisiti

- **Node.js** (>=12.x)
- **npm** (>=6.x)
- **Java 8+**
- **Maven** (per il backend)
- **H2 Database** (o altro database configurato per il backend)

## Configurazione del Backend

1. Clona il repository nel tuo ambiente locale.

    ```bash
    git clone https://github.com/tuo-username/tuo-progetto.git
    cd tuo-progetto/backend
    ```

2. Configura il file `application.properties` (se necessario) per la connessione al database e alle altre impostazioni di configurazione.

3. Costruisci il progetto backend utilizzando Maven:

    ```bash
    mvn clean install
    ```

4. Avvia l'applicazione Spring Boot:

    ```bash
    mvn spring-boot:run
    ```

   Il backend sarà disponibile su `http://localhost:8080`.

## Configurazione del Frontend

1. Naviga nella cartella `frontend`:

    ```bash
    cd ../frontend
    ```

2. Installa le dipendenze:

    ```bash
    npm install
    ```

3. Avvia il progetto React:

    ```bash
    npm start
    ```

   Il frontend sarà disponibile su `http://localhost:3000`.

## API del Backend

### **POST** `/api/auth/login`
- **Descrizione**: Permette agli utenti di effettuare il login.
- **Parametri**:
    - `email` (string)
    - `password` (string)
- **Risposta**: Restituisce un token JWT se la login è riuscita.

### **POST** `/api/auth/register`
- **Descrizione**: Permette agli utenti di registrarsi.
- **Parametri**:
    - `email` (string)
    - `password` (string)
    - `name` (string)
    - `surname` (string)
    - `birthday` (string)
- **Risposta**: Restituisce i dettagli dell'utente e un token JWT.

### **GET** `/api/utenti/me`
- **Descrizione**: Ottiene i dettagli dell'utente autenticato.
- **Headers**: `Authorization: Bearer <token>`
- **Risposta**: Restituisce i dati dell'utente.

### **GET** `/api/utenti`
- **Descrizione**: Ottiene tutti gli utenti (accessibile solo da amministratori).
- **Headers**: `Authorization: Bearer <token>`
- **Risposta**: Restituisce una lista di utenti.

## Funzioni di Sicurezza

1. **JWT**: Utilizza JSON Web Token (JWT) per la gestione dell'autenticazione e delle sessioni utente. Ogni richiesta protetta deve includere un token JWT nel campo `Authorization` dell'header HTTP.
2. **Autorizzazione per Ruoli**: Solo gli utenti con il ruolo "ADMIN" possono accedere a determinate risorse, come la dashboard amministrativa.

## Come Contribuire

1. Fork il repository
2. Crea un nuovo branch (`git checkout -b feature/nome-funzione`)
3. Fai le modifiche necessarie e committa (`git commit -am 'Aggiungi nuova funzionalità'`)
4. Push le modifiche (`git push origin feature/nome-funzione`)
5. Crea una pull request descrivendo le modifiche fatte.

## Licenza

Distribuito sotto la licenza MIT. Vedi `LICENSE` per maggiori dettagli.

## Contatti

- Email: `tuo-email@esempio.com`
- Sito web: `https://www.tuosito.com`
- GitHub: `https://github.com/tuo-username`

---

Spero che questo `README.md` ti aiuti a documentare il tuo progetto in modo chiaro e utile per gli altri sviluppatori! Se hai bisogno di ulteriori modifiche, fammelo sapere.
