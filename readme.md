# Java Employees
Testovací úkol

## Požadavky
Java 8

## Struktura
`bin` - zkompilované soubory
`jars` - knihovny potøebné pro testy
`src` - zdrojové soubory aplikace
`test` - unit testy

## Popis
Po spuštìní aplikace je zobrazena nápovìda a dle zadaných parametrù jsou spouštìny jednotlivé dílèí úkoly.

## Spuštìní
Lze udìlat pomocí pøiložených souborù compile.bat a run.bat, pøípadnì pøíkazy

`zkompilování` - javac -d bin src/employees/*.java
`spuštìní` - java -cp bin employees.Main

## Testování
Lze udìlat pomocí pøiložených souborù compileTests.bat a runTests.bat, pøípadnì pøíkazy

`zkompilování testù` - javac -cp jars/junit-4.13.2.jar;org.hamcrest.core_1.3.0.v201303031735.jar;bin -d bin test/employees/*.java
`spuštìní testù` - java -cp jars/junit-4.13.2.jar;jars/org.hamcrest.core_1.3.0.v201303031735.jar;bin employees.MainForTests