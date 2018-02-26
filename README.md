# krizic_kruzic

Ovaj projekat je imao za zadatak da kreira krizic kruzic igricu u obliku Rest servisa.

Opis aplikacije:
 - Igra započinje kada se na endpoint: game/new?first={player1}&second={player2} pošalje GET request.

U slučaju kada je parametar player1 jednak computer prvi igrač je računalo, a parametar player2 određuje ime igrača.
Obratno kada je parametar player1 neki string različit od computer tada taj string predstavlja ime igrača.
Parametar computer se može ispustiti. Dakle validan request je i game/new?first=Amer  i game/new?second=Amer


Kao response servis vraća json sa id-em igre:

{
"gameId":156
}

Na endpoint: game/status?gameId={id} slanjem GET requesta igrač može provjeriti trenutno stanje igre, npr. u slučaju da igra traje dobije se response:

{
"gameId":156,
"status":"inProgress"
"game":[
{
"row":1,
"column":1
"value":"X"
},
{
"row":1,
"column":2
"value":"O"
}, ...
]
}

ili u slučaju da je igra gotova dobije se response:

{
"gameId":156,
"status":"finished",
"winner":"computer",
"game":[...]
}

Igrač može napraviti potez tako da pošalje GET request na endpoint: game/play?gameId={id}&row={row_number}&column={column_number}
U slučaju da igrač pokuša napraviti nedopušten potez servis vraća http status 412.

Osim navedenih endpointa namijenjenih za igru moguće je vidjeti statistiku pobjeda/gubitaka na endpointu stats sa metodom GET

Primjer responsea:
{
"stats":[
{
"name":"Amer",
"wins":12,
"losses":3,
"draws":5
}, ...
]
}
.
