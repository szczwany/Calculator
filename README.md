# Spring Boot "Calculator" Project

Projekt serwisu REST, która pozwala planować oraz wykonywać obliczenia matematyczne.
Napisany w języku Java z użyciem Spring Boot 1.5.9.RELEASE i Maven w IntelliJ IDEA.

## Uruchomienie aplikacji 

Aplikacja jest dostarczona jako jar z wbudowaną bazą danych H2. 

* Wymagania: JDK 1.8+ oraz Maven 3.x
* Sklonuj repozytorium
* Zbuduj i uruchom testy ```mvn clean package```
* Po pomyślnym zbudowaniu projektu, można uruchomić poprzez uruchomienie jar w katalogu:
```
        target/calculator-0.0.1-SNAPSHOT.jar
```
* Lub zaimportować projekt do IntelliJ IDEA/Eclipse i uruchomić z poziomu środowiska

## Opis aplikacji

Projekt serwisu REST, który pozwala planować oraz wykonywać obliczenia matematyczne. Używa wbudowanej bazy danych H2 działającej na porcie 9212.
Wszystkie funkcje dostępne są w endpointach serwisu, które są opisane poniżej. 
Przykładowy endpoint wyświetlający kalkulację ```id = 5``` w projekcie ```id = 13```
```    
http://localhost:9212/v1/projects/13/calculations/5
```

Założenia dotyczące obliczeń matematycznych:

* Do wykonywania obliczeń używamy liczb wymiernych w postaci dzięsiętnej z separatorem kropki (maksymalnie dwa miejsca po kropce) np: ``` 1, 19.67, 8323.12, 0.23, -333.1 ```
* Można używać czterech operatorów: ``` '+' '-' '*' '/' ```
* Nie można używać nawiasów
* Wyrażenie matematyczne musi zostać zapisane w formacie bez spacji: ``` 1.23*544+5+6/7.54 ```

Lista funkcjonalności aplikacji:

* Tworzenie projektów, które mają nazwę i listę kalkulacji.
* Tworzenie kalkulacji, które mają opis, działanie matematyczne, wynik oraz czas ostatniej aktualizacji wyniku.
* Wyświetlanie, aktualizowanie oraz usuwanie projektów i kalkulacji.
* Uruchamianie obliczeń dla jednej lub wielu kalkulacji.
* Zawiera skrypt sql inicjalizujący dane w bazie danych wymagane w testach.
* Zawiera testy większości klas.
* Dodatkowo zawiera kolekcję testów w programie Postman, która znajduje się w katalogu:
```    
 src/test/resources/PostmanTests.json
```

## Endpointy aplikacji

### Projekt:

#### Wyświetl wszystkie projekty

```
GET /v1/projects

Response: HTTP 200
Content-Type: application/json
Content: lista projektów
```

#### Stwórz projekt

```
POST /v1/projects
Accept: application/json
Content-Type: application/json

{
"name" : "Nazwa projektu"
}

RESPONSE: HTTP 201 (Created)
Content: id stworzonego projektu
Location header: http://localhost:9212/v1/projects/1
```

#### Wyświetl projekt

```
GET /v1/projects/1

Response: HTTP 200
Content-Type: application/json
Content: projekt
```

#### Aktualizuj projekt

```
PUT /v1/projects/1
Accept: application/json
Content-Type: application/json

{
"name" : "Nowa nazwa projektu"
}

RESPONSE: HTTP 204 (No Content)
```

#### Usuń projekt

```
DELETE /v1/projects/1
Content-Type: application/json

RESPONSE: HTTP 204 (No Content)
```

### Kalkucja:

#### Wyświetl wszystkie kalkulacje dla projektu

```
GET /v1/projects/1/calculations

Response: HTTP 200
Content-Type: application/json
Content: lista kalkulacji
```

#### Stwórz kalkulację

```
POST /v1/projects/1/calculations
Accept: application/json
Content-Type: application/json

{
"description" : "Opis kalkulacji",
"expression" : "2+2"
}

RESPONSE: HTTP 201 (Created)
Content: id stworzonej kalkulacji
Location header: http://localhost:9212/v1/projects/1/calculations/1
```

#### Wyświetl kalkulację

```
GET /v1/projects/1/calculations/1

Response: HTTP 200
Content-Type: application/json
Content: kalkulacja
```

#### Aktualizuj kalkulację

```
PUT /v1/projects/1/calculations/1
Accept: application/json
Content-Type: application/json

{
"description" : "Nowy opis kalkulacji",
"expression" : "2+2-4"
}

RESPONSE: HTTP 204 (No Content)
```

#### Usuń kalkulację

```
DELETE /v1/projects/1/calculations/1
Content-Type: application/json

RESPONSE: HTTP 204 (No Content)
```

### Wyniki:

#### Wyświetl wszystkie kalkulacje

```
GET /v1/calculations

Response: HTTP 200
Content-Type: application/json
Content: lista kalkulacji
```

#### Stwórz wyniki w każdej kalkulacji

```
GET /v1/calculations/results

Content-Type: application/json
RESPONSE: HTTP 204 (No Content)
```

#### Stwórz wyniki w każdej kalkulacji w projekcie

```
GET /v1/projects/1/results

Content-Type: application/json
RESPONSE: HTTP 204 (No Content)
```

#### Stwórz wyniki w jednej kalkulacji w projekcie

```
GET /v1/projects/1/calculations/1/results

Content-Type: application/json
RESPONSE: HTTP 204 (No Content)
```

#### Pytania: tkrutel@hotmail.com





