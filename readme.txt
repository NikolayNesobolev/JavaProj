Miklai Chabatarevich, 146847, I4.2, pon13:30

Wziąłem ikony portów, transportu oraz mapy z Internetu i zmieniłem je nieco w Photoshopie.

1.1	W głównej klasie "Main" zmienne mają przypisane ikony, tworzone są listy z danych znajdujących się w plikach(nazwy i lokalizacje portów, trasy transportu), tworzone są również okna mapy i okna kontrolowego. Dodają się przyciski z funkcją dodawania nowego transportu[Każdy rodzaj transportu ma swoje minimalne i maksymalne generowanie prędkości <<statek pasażerski nie może mieć prędkości samolotu pasażerskiego>>].

1.2	W klasie "Map" tworzone jest okno mapy, ustawiane są parametry okna i funkcja zakończenia programu po zamknięciu okna na krzyżyku.

1.3	W klasie "AppPane" tak samo tylko dla okna kontrolowego.

1.4	Klasa abstrakcyjna "SceneObject" zawiera gettery oraz settery.

1.5	Klasa "Port" określa położenie portów na mapie.

1.6	Klasa abstrakcyjna "Vehicle" zawiera gettery oraz settery oraz ogólne funkcje dla różnych środków transportu(wyświetlanie w oknie transportu kierunku i przystanków, funkcja ruchu, wyszukiwanie ścieżki do portu...). Dodaje się przycisk z funkcją usuwania transportu z mapy.

1.7	Klasa abstrakcyjna "Ship" zawiera klasę "AnimationTimer" i funkcję tej klasy "handle" (znalazłem przykład użycia na jakimś rosyjskojęzycznym forum / na YouTube). To samo w klasie abstrakcyjnej "Plane". Pokazuje ogólne informacje o 'Ship'. Zawiera funkcję lotu do następnego portu. Ship wypływa z portu i pływa w morzu na wyznaczonej trasie, nie kierując się do następnego portu i bez powrotu do pierwotnego portu.

1.8	Klasa "PassengerShip" dodaje w oknie informacji o transporcie dane, które posiada tylko passenger ship.

1.9	Klasa "Carrier" dodaje w oknie informacji o transporcie dane, które posiada tylko carrier oraz dodaje się przycisk, który uwalnia samolot wojskowy ze statku.

1.10	Klasa abstrakcyjna "Plane" pokazuje ogólne informacje o 'Plane'. Zawiera funkcje i przyciski zmiany trasy do jakiejkolwiek innej wybranej losowo i lądowania awaryjnego (po lądowaniu awaryjnym samolot znika. Już nie ma siły się z tym uporać :3). Zawiera funkcję lotu do następnego portu i utraty benzyny.

1.11	Klasa "PassengerAircraft" dodaje w oknie informacji o transporcie dane, które posiada tylko passenger aircraft oraz funkcję odbierania losowej liczby pasażerów na airporcie.

1.12	Klasa "MilitaryAircraft" dodaje w oknie informacji o transporcie dane, które posiada tylko military aircraft 


2.	Nie realizowałem pojemność airportów. Samoloty lecą z airportu do airportu bez żadnych dróg lotniczych. Statki płyną po określonym trasam. 
W moim projekcie samoloty i statki mogą znajdować się na tej samej ścieżce i przecinać się między sobą. Nie udało się zrealizować semaforów... One mogą przechodzić przez siebie ;)
Użyłem w projekcie nazw rosyjskich firm wycieczkowych oraz nazw samolotów wojskowych ZSRR i Rosji.