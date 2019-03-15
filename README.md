# Minder_Client
Client de Minder

TODO LIST:

  - Un cop llegit el fitxer, obrir GUI amb les següents opcions:
    - Registrar-se i/o iniciar sessió.
    - Editar perfil.
    - Acceptar o descartar usuaris.
    - Xatejar amb els usuaris amb els quals s'ha fet match.
    - Tancar sessió.
    
  - Registrar-se:
    - Nom d'usuari (únic).
    - Edat (major d'edat).
    - Tipus de compte (Normal o Premium).
    - Correu (únic i format correcte).
    - Password:
      - Mínim 8 caràcters.
      - Mínim 1 majúscula.
      - Mínim 1 minúscula.
      - Mínim 1 número.
    - Password confirm:
      - Ha de ser igual al camp Password.
      
    - Comprovar que les dades compleixin requisits (menys si user i mail són únics, que ho fa el server).
      - Si no compleix els requisits, mostrar missatge d'error específic.
      - Si els compleix, enviar al server, el server fa les comprovacions i si falla algo, mostrar missatge d'error específic.
    
  - Inici de sessió:
    - Username / mail.
    - Password.
    - S'envien les dades al servidor i, si el servidor retorna error, es mostra missatge genèric.
    - En el primer inici de sessió s'ha d'obligar a l'usuari a introduïr les següents dades i comprovar si són correctes:
      - Escollir foto de perfil.
      - Escriure una descripció.
      - Indicar si està interessat en Java o C++.
   
  - Editar el perfil:
    - Després d'iniciar sessió, a la pantalla principal l'usuari ha de poder accedir al seu perfil i editar-lo en qualsevol moment.
    - L'usuari ha de poder com a mínim:
      - Canviar foto de perfil.
      - Canviar descripció.
      - Indicar si està interessat en Java o C++.
     
  - Acceptar o descartar usuaris:
    - De cada usuari s'ha de mostrar:
      - Foto.
      - Nom.
      - Edat.
      - Botó per poder accedir a la resta de dades.
    - Si has acceptat a un usuari que prèviament t'ha aceptat a tu (i per tant heu fet match), ha d'aparèixer un missatge informatiu i desbloquejar l'opció de xatejar.
    - Els usuaris han d'aparèixer en ordre aleatori, però només han d'aparèixer els que han sel·leccionat el mateix llenguatge de programació que tu.
    - No han d'aparèixer ni els usuaris acceptats ni els usuaris amb els qual s'ha fet match.
    - Tipus d'usuaris:
      - Usuaris Normals:
        - Usuaris ordenats de manera aleatòria.
      - Usuaris Premium:
        - Primer usuaris que l'han acceptat prèviament, després la resta de manera aleatòria.
        
  - Xatejar:
    - L'usuari ha de poder accedir a una llista amb tots els usuaris amb els que té match actiu.
    - Ha de poder xatejar en temps real.
    - Des del xat, un usuari ha de poder fer unmatch en qualsevol moment.
    
  - Tancar sessió:
    - Des de qualsevol pantalla i en qualsevol moment s'ha de poder tancar la sessió.
  
  
  
OPCIONALS:
  - Afegir camps extra al perfil: cançó preferida, llista de hobbies i opció de poder escollir Java i C++.
  - Afegir un filtre per edat.
  - Acceptar o rebutjar usuaris fent drag&drop.
  - Eliminar missatges quan es fa unmatch.
  - Guardar il·limitats missatges en comptes dels últims 20.
