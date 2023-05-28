# Elevator

# Wymagania
```
git
java 19.0.2
nodejs
```
# Instalacja i uruchomienie:
```
git clone https://github.com/Kosiyyu/elevator.git
cd elevator
```


# Opis projektu
Backend został napisany w Spring-Boocie, a frontend w Reacie

## Opis algorytmu
Główny projekt znajduje się na branchu main, a sam algorytm znajduje się w metodzie addFloors w klasie ElevatorManager.

Głównym problemem algorytmu było rozróżnienie, kilku stanów w jakich może znaleźć się winda. Wzięto pod uwagę czy winda jedzie do góry czy w dół i czy użytkownik jedzie w górę czy też w dół. Otrzymujemy wtedy elementarne stany:
- 1 1 
Winda jedzie do góry, użytkownik chce jechać do góry

- 1 -1 
Winda jedzie do góry, użytkownik chce jechać na dół

- -1 -1 
Winda jedzie na góry, użytkownik chce jechać na dół

- -1 1
Winda jedzie na góry, użytkownik chce jechać do góry

Sprowadzenie problemu do takich stanów elementarnych umożliwia efektywne zarządzanie w którą stronę i po jakie wezwanie winda powinna ruszyć. 

W klasie ElevatorManager metody elevatorMove użyto do automatycznego poruszania windy, metody calculatePath użyto by obliczyć drogę jaką pokona winda, a metody findOptimal użyto do znalezienia indeksu widny która pokona najkrótszą trasę.

Niestety w projekcie występują pomniejsze bugi ;cc
