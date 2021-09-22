# Java Employees
Testovací úkol

## Požadavky
Java 8

## Struktura
`bin` - zkompilované soubory

`jars` - knihovny potřebné pro testy

`src` - zdrojové soubory aplikace

`test` - unit testy

## Popis
Po spuštění aplikace je zobrazena nápověda a dle zadaných parametrů jsou spouštěny jednotlivé dílčí úkoly.

## Spuštění
Lze udělat pomocí přiložených souborů compile.bat a run.bat, případně příkazy

`zkompilování` - javac -d bin src/employees/*.java

`spuštění` - java -cp bin employees.Main

## Testování
Lze udělat pomocí přiložených souborů compileTests.bat a runTests.bat, případně příkazy

`zkompilování testů` - javac -cp jars/junit-4.13.2.jar;org.hamcrest.core_1.3.0.v201303031735.jar;bin -d bin test/employees/*.java

`spuštění testů` - java -cp jars/junit-4.13.2.jar;jars/org.hamcrest.core_1.3.0.v201303031735.jar;bin employees.MainForTests
