# Documentation

## First Succeed Test should return "from WEB"
Tested: `SELECT * FROM DB;`
![ok](./imgs/ok.png)

## Second Test with same Query should return "from DB"
Tested: `SELECT * FROM DB;`
![db](./imgs/db.png)

## Third Test with same Query but different casing return "from DB"
Tested: `seLecT * fRoM db;`
![perfect](./imgs/perfect.png)

## Fourth Test with errors in query
Tested: `FROM something SELET`
![error](./imgs/error.png)

## Fith Test with more than 1 error
Tested: `SL FO blabla ; bla`
![more errors](./imgs/more.png)

## Sixth Test with more than 1 error again
Tested: `SL FO blabla ; blabla`
![more errors with db](./imgs/morewithoutEntry.png)

