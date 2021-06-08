# masivian
Tecnical test

### Test API REST - CURL
_Este servicio dispone de cinco operaciones GET y POST(new, allRoulettes, openRoulette, bet, closeRoulette) y para su correcto funcionamiento el servicio debe iniciar una instancia o sesión, es por ellos que debe lanzarse primero la operación "new". Estas operaciones se describen a continucación:_

* **new:** Obtiene nueva ruleta o ambiente con instancias reseteadas.
```
CURL: http://localhost:8080/roulette/new
```
* **allRoulettes:** Permite consultar todas las ruletas existentes.
```
CURL: http://localhost:8080/roulette/allRoulettes
```
* **openRoulette:** Permite abrir una ruleta para crear apuestas. Parametros: _{rouletteId}_ ._
```
CURL: http://localhost:8080/roulette/openRoulette/{rouletteId}
```
* **bet:** Permite apostar a una ruleta. Parametros: RequestBody  _{BetRequest}_ ._
```
CURL: http://localhost:8080/roulette/bet
```
* **closeRoulette:** Permite cerrar una ruleta para finalizar apuestas. Parametros: _{rouletteId}_ ._
```
CURL: http://localhost:8080/roulette/closeRoulette/{rouletteId}
```
