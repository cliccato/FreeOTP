# FreeOTP

FreeOTP è un'applicazione Android sviluppata in Java per la gestione e generazione di codici TOTP (Time-Based One-Time Password), realizzata come parte di un progetto scolastico.

## Caratteristiche principali

L'app consente di generare codici TOTP per l'autenticazione a due fattori (2FA) in modo semplice e sicuro. Gli utenti possono aggiungere account associati ai loro servizi online, visualizzare i codici TOTP generati e gestire facilmente gli account salvati nell'app. L'interfaccia utente è progettata per essere intuitiva e facile da usare, garantendo una gestione rapida e sicura dei codici.

## Requisiti di sistema

- Android 5.0 (Lollipop) o versioni successive
- Java JDK 8 o superiore per lo sviluppo

## Installazione

1. **Clona il repository:**
   ```bash
   git clone https://github.com/cliccato/FreeOTP.git
   ```

2. **Apri il progetto in Android Studio:**
   - Seleziona "Open an existing Android Studio project".
   - Naviga fino alla cartella dove hai clonato il repository e seleziona la cartella del progetto.

3. **Compila ed esegui l'app:**
   - Configura correttamente il tuo ambiente di sviluppo con Android SDK e JDK.
   - Premi il tasto "Run" in Android Studio per compilare ed eseguire l'app su un emulatore o dispositivo fisico.

## Utilizzo

1. **Aggiungi un account:**
   - Premi il pulsante "+" nella schermata principale per aggiungere un nuovo account.
   - Inserisci il nome dell'account e la chiave segreta associata.
   - L'app genererà automaticamente i codici TOTP per l'account aggiunto.

2. **Visualizza i codici TOTP:**
   - I codici TOTP vengono visualizzati nella schermata principale e si aggiornano automaticamente ogni 30 secondi.

3. **Gestione degli account:**
   - Modifica o elimina gli account esistenti tramite le opzioni disponibili nella schermata principale.
