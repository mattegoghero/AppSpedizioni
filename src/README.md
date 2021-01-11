Progetto di Programmazione a Oggetti 2020/2021
Goghero Matteo Federico - 143143

Il progetto è stato costruito utilizzando come punto principale d'avvio la classe MainWindow la quale estende la class JFrame.
La classe MainWindow, attraverso un CardLayout, gestisce la visualizzazione delle varie schermate del programma (dei JPanel).
Le schermate per le spedizioni dal punto di vista del cliente e dell'amministratore estendono la superclasse atratta Spedizioni che gestisce l'interfaccia comune a entrambe
le visualizzazioni evitando un inutile duplicazione di codice.

Per quanto riguarda la gestione della persistenza dei dati ({@link data.Cliente Clienti} e {@link data.Spedizione Spedizioni}) sono state realizzate due classi ({@link dao.DaoClienti DaoClienti} e {@link dao.DaoSpedizioni DaoSpedizioni}) che permettono di gestire in modo trasparente
il salvataggio e la lettura dei dati. 
Entrambe le classi estendono la superclasse {@link dao.Dao Dao} (Data Access Object) che è di tipo Generics, realizzata in modo da poter essere flessibile con diversi tipi di dato da leggere e salvare.
Al suo interno è stata usata la struttura dati Vector perché Thread-Safe in quanto all'interno della schermata delle spedizioni amministratore vi è un Thread che aggiorna ogni 5 secondi lo stato
di una spedizione scelta casualmente.
I dati vengono salvati su file in formato binario usando ObjectOutputStream e viene usato il suo duale per leggere i dati da file.

Per la gestione delle spedizioni è stata creata una classe ({@link data.Spedizione Spedizione}) la quale rappresenta una spedizione normale. Le spedizioni assicurate vengono rappresentate dalla classe {@link data.SpedizioneAssicurata SpedizioneAssicurata} la quale estende la prima
sempre per sfruttare il meccanismo di ereditarietà.

Per gestire lo stato delle spedizioni è stata fatta la scelta di usare un unico enum ({@link data.StatoSpedizione StatoSpedizione}) che implementa l'interfaccia {@link data.StatoSpedizioneInterface StatoSpedizioneInteface} la quale mette a disposizione diversi metodi utili a rappresentare
i vari stati delle spedizioni quali: nome, colore, idoneità a rappresentare una spedizione normale.
Di conseguenza una spedizione normale accetta valori dall'enum sono se soddisfano quest'ultima condizione, le spedizioni assicurate accettano invece tutti i possibili stati.
La scelta di usare un unico enum è stata fatta per semplificare il suo utilizzo nel codice che gestisce la visualizzazione e modifica delle spedizioni stesse.

Per la visualizzazione delle spedizioni all'interno della tabella delle spedizioni cliente si è scelto di usare un TableModel ({@link tablemodels.SpedizioniTableModel SpedizioniTableModel} che estende {@link tablemodels.ColoredRowTableModel ColoredRowTableModel}) che implementa tutte le caratterische necessarie a fornire un output formattato
per tutte le righe e colonne della tabella.

Per quanto riguarda la schermata delle spedizioni amministratore si utilizza sempre un TableModel ({@link tablemodels.SpedizioniAmministratoreTableModel SpedizioniAmministratoreTableModel}), che estende {@link tablemodels.SpedizioniTableModel SpedizioniTableModel}, aggiungendo soltanto una colonna per la visualizzazione del nome del cliente a cui appartiene
la spedizione elencata dato che in questa schermata vengono visualizzate le spedizioni di tutti i clienti.
Quando si entra in questa schermata viene avviato automaticamente un thread ({@link threads.UpdateSpedizioniThread UpdateSpedizioniThread}) il quale ogni 5 secondi sceglie una spedizione in modo casuale e tenta di farne avanazare lo stato (seguendo le logiche opportune) oppure la fa fallire (con un probabilità di circa il 20%).
Quando si esce dalla schermata il thread viene fermato.

All'interno del progetto sono stati utilizzati anche dei componenti personalizzati per poter limitare al minimo la duplicazione di codice e implementare delle logiche di formattazione particolari come per il caso della classe {@link customcomponents.ColoredRowRender ColoredRowRender} che si occupa di colorare
in modo opportuno le righe della tabella in base allo stato della spedizione che rapprensentano.

Alcuni pannelli usati nel progetto estendono la superclasse {@link customcomponents.CustomPanel CustomPanel} che implementa due metodi per visualizzare comodamente dei Dialog di avviso ed errore. 
