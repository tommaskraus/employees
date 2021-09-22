# Java Employees
Testovac� �kol

## Po�adavky
Java 8

## Struktura
`bin` - zkompilovan� soubory
`jars` - knihovny pot�ebn� pro testy
`src` - zdrojov� soubory aplikace
`test` - unit testy

## Popis
Po spu�t�n� aplikace je zobrazena n�pov�da a dle zadan�ch parametr� jsou spou�t�ny jednotliv� d�l�� �koly.

## Spu�t�n�
Lze ud�lat pomoc� p�ilo�en�ch soubor� compile.bat a run.bat, p��padn� p��kazy

`zkompilov�n�` - javac -d bin src/employees/*.java
`spu�t�n�` - java -cp bin employees.Main

## Testov�n�
Lze ud�lat pomoc� p�ilo�en�ch soubor� compileTests.bat a runTests.bat, p��padn� p��kazy

`zkompilov�n� test�` - javac -cp jars/junit-4.13.2.jar;org.hamcrest.core_1.3.0.v201303031735.jar;bin -d bin test/employees/*.java
`spu�t�n� test�` - java -cp jars/junit-4.13.2.jar;jars/org.hamcrest.core_1.3.0.v201303031735.jar;bin employees.MainForTests