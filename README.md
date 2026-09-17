# 📘 Guía definitiva de Scala: desde cero hasta nivel examen

Guía práctica para aprender Scala paso a paso. Los ejemplos están explicados con detalle para poder copiarlos, probarlos y repasarlos antes del examen.

> **Nota:** Los ejemplos funcionan en Scala 2 y, salvo pequeños detalles de sintaxis, también en Scala 3.

---

## 1. Variables: ¿cómo guardamos datos?

Una variable es como una caja donde guardamos un dato. En Scala podemos crear cajas que no cambian (`val`) y cajas cuyo contenido podemos reasignar (`var`).

### `val`: valor inmutable

Un `val` no puede apuntar a otro valor después de inicializarse. Es la opción recomendada siempre que sea posible.

```scala
// Scala deduce automáticamente los tipos
val nombre = "Alex"
val edad = 20

// También podemos escribir el tipo explícitamente
val ciudad: String = "Madrid"
val nota: Double = 8.5

// edad = 21 // ERROR: un val no se puede reasignar
```

Que un `val` sea inmutable significa que no podemos reasignar la variable. Por eso se recomienda usarlo por defecto.

### `var`: variable mutable

Un `var` sí puede reasignarse. Se usa cuando el valor tiene que cambiar, por ejemplo, en un contador o acumulador.

```scala
var puntuacion: Int = 0
puntuacion = puntuacion + 10
puntuacion += 5

println(puntuacion) // 15
```

**Regla importante:** usa `val` por defecto y `var` solo cuando necesites reasignar.

---

## 2. Tipos de datos más comunes

| Tipo | Ejemplo | Uso |
|---|---|---|
| `Int` | `10`, `-5` | Números enteros |
| `Double` | `3.14`, `9.99` | Números decimales |
| `String` | `"Hola"` | Texto |
| `Boolean` | `true`, `false` | Verdadero o falso |
| `Char` | `'A'` | Un único carácter |
| `Long` | `10000000000L` | Números enteros grandes |
| `Unit` | `()` | Funciones que no devuelven un resultado útil |

```scala
val numero: Int = 10
val precio: Double = 3.99
val mensaje: String = "Hola"
val aprobado: Boolean = true
val inicial: Char = 'A'
```

### Conversión entre tipos

```scala
val texto = "25"
val numero = texto.toInt
val decimal = numero.toDouble
val textoOtraVez = numero.toString

println(s"Número: $numero, decimal: $decimal")
```

Si el texto no representa un número válido, `toInt` puede producir un error.

### Interpolación de strings

La letra `s` delante de una cadena permite insertar variables con `$`.

```scala
val nombre = "Ana"
val edad = 20

println(s"Me llamo $nombre y tengo $edad años")
println(s"El año que viene tendré ${edad + 1} años")
```

---

## 3. Operadores básicos

```scala
val a = 10
val b = 3

println(a + b)  // 13: suma
println(a - b)  // 7: resta
println(a * b)  // 30: multiplicación
println(a / b)  // 3: división entera
println(a % b)  // 1: resto

println(a == b) // igualdad
println(a != b) // desigualdad
println(a > b)  // mayor que
println(a >= b) // mayor o igual
println(a < b)  // menor que
println(a <= b) // menor o igual
```

Operadores lógicos:

- `&&`: y; las dos condiciones deben ser verdaderas.
- `||`: o; al menos una condición debe ser verdadera.
- `!`: negación; cambia `true` por `false` y al revés.

```scala
val tieneEntrada = true
val esMayorDeEdad = true

if (tieneEntrada && esMayorDeEdad) {
  println("Puede entrar")
}
```

Recuerda: `=` asigna un valor y `==` compara dos valores.

---

## 4. Condicionales: `if`, `else if` y `else`

Los condicionales permiten que el programa tome decisiones.

```scala
val puntos = 25

if (puntos == 21) {
  println("¡Has hecho un Blackjack!")
} else if (puntos < 21) {
  println("Mano válida, puedes seguir pidiendo.")
} else {
  println("Te has pasado (Bust).")
}
```

En Scala, un `if` también puede devolver un valor:

```scala
val resultado = if (puntos <= 21) "válido" else "no válido"
println(resultado)
```

---

## 5. Funciones (`def`)

Una función recibe datos, realiza un trabajo y devuelve un resultado. La última expresión del cuerpo se devuelve automáticamente.

```scala
def comprobarMano(puntuacion: Int): Boolean = {
  if (puntuacion <= 21) {
    true
  } else {
    false
  }
}

println(comprobarMano(20)) // true
println(comprobarMano(25)) // false
```

La función anterior se puede escribir de forma más corta:

```scala
def comprobarManoCorta(puntuacion: Int): Boolean = puntuacion <= 21
```

Una función con varios parámetros:

```scala
def sumar(a: Int, b: Int): Int = a + b

val resultado = sumar(4, 6)
println(resultado) // 10
```

Una función que solo imprime devuelve `Unit`:

```scala
def saludar(nombre: String): Unit = {
  println(s"Hola, $nombre")
}
```

---

## 6. Colecciones: `Array` y `List`

Las colecciones sirven para guardar varios datos juntos.

### `Array`: tamaño fijo

El tamaño de un `Array` no cambia, pero podemos modificar sus posiciones. Los índices empiezan en `0`.

```scala
val notas = Array(5, 7, 9)

notas(0) = 6

println(notas(0))     // 6
println(notas.length) // 3
```

Un array de tamaño 3 tiene las posiciones `0`, `1` y `2`. Acceder a otra posición produce un error.

### `List`: colección inmutable

Una `List` normal no se modifica. Cuando añadimos un elemento, se crea otra lista.

```scala
val numeros = List(10, 20, 30)

println(numeros.head)   // 10: primer elemento
println(numeros.tail)   // List(20, 30): resto
println(numeros.length) // 3
```

Para construir una lista progresivamente podemos usar `var`:

```scala
var misNumeros = List[Int]()

misNumeros = misNumeros :+ 10
misNumeros = misNumeros :+ 20

println(misNumeros) // List(10, 20)
```

`:+` añade al final. `::` añade al principio y suele ser más eficiente:

```scala
val lista = 30 :: 20 :: 10 :: Nil
println(lista) // List(30, 20, 10)
```

Para unir listas usamos `++`:

```scala
val primera = List(1, 2)
val segunda = List(3, 4)
val completa = primera ++ segunda

println(completa) // List(1, 2, 3, 4)
```

### `map`, `filter` y `foreach`

```scala
val numeros = List(1, 2, 3, 4, 5)

val duplicados = numeros.map(numero => numero * 2)
val pares = numeros.filter(numero => numero % 2 == 0)

println(duplicados) // List(2, 4, 6, 8, 10)
println(pares)      // List(2, 4)
```

- `map` transforma todos los elementos y devuelve una colección nueva.
- `filter` conserva solo los elementos que cumplen una condición.
- `foreach` recorre los elementos para ejecutar una acción y no devuelve una colección nueva.

---

## 7. Bucles: repitiendo acciones

### Bucle `while`: control manual

Necesitamos un índice y debemos aumentarlo en cada vuelta. Si olvidamos `i += 1`, el bucle puede ser infinito.

```scala
val arrayNumeros = Array(10, 20, 30)
var i = 0

while (i < arrayNumeros.length) {
  println(arrayNumeros(i))
  i += 1
}
```

### Bucle `foreach`: recorrer sin índice

`foreach` coge cada elemento de la colección, uno por uno, y lo guarda temporalmente en la variable que escribimos antes de `=>`. No hay que crear un contador.

```scala
val listaNombres = List("Ana", "Luis", "Marta")

listaNombres.foreach { nombre =>
  println(s"Hola, $nombre")
}
```

En cada vuelta ocurre lo siguiente:

1. Primera vuelta: `nombre` vale `"Ana"`.
2. Segunda vuelta: `nombre` vale `"Luis"`.
3. Tercera vuelta: `nombre` vale `"Marta"`.

También se puede escribir en una sola línea:

```scala
listaNombres.foreach(nombre => println(s"Hola, $nombre"))
```

Con una función ya existente podemos usar la sintaxis abreviada `_`:

```scala
List(1, 2, 3).foreach(numero => println(numero))
```

La forma anterior es más clara para principiantes. En un `foreach` no podemos usar `break` de la misma manera que en algunos lenguajes; normalmente filtramos antes o usamos otro tipo de bucle.

### `for`: recorrer una colección

`for` también recorre los elementos, pero suele ser más cómodo cuando necesitamos condiciones o crear una colección nueva.

```scala
for (nombre <- listaNombres) {
  println(s"Hola, $nombre")
}
```

La variable `nombre` toma el valor de cada elemento, igual que en `foreach`.

Podemos añadir una condición, llamada guardia:

```scala
for (numero <- 1 to 10 if numero % 2 == 0) {
  println(numero)
}
```

- `1 to 10` incluye el `10`.
- `1 until 10` llega hasta `9` y no incluye el `10`.

### `for` con dos listas sin usar `zip`

Si tenemos dos listas relacionadas, por ejemplo nombres y alturas, podemos usar el índice. Las dos listas deben tener el mismo tamaño.

```scala
val nombres = List("Ana", "Luis", "Marta")
val alturas = List(1.65, 1.80, 1.70)

for (i <- nombres.indices) {
  println(s"${nombres(i)} mide ${alturas(i)} metros")
}
```

`nombres.indices` genera los índices `0`, `1` y `2`. En cada vuelta usamos el mismo índice `i` para obtener el nombre y la altura que están en la misma posición.

Otra forma equivalente es:

```scala
for (i <- 0 until nombres.length) {
  println(s"Nombre: ${nombres(i)}, altura: ${alturas(i)}")
}
```

> Es importante que las dos listas tengan la misma longitud. Si una es más corta, intentar acceder a una posición inexistente produce un error.

### `for` con dos listas usando `zip`

`zip` une los elementos de dos listas en parejas. Así el `for` recibe directamente el nombre y la altura.

```scala
for ((nombre, altura) <- nombres.zip(alturas)) {
  println(s"$nombre mide $altura metros")
}
```

`nombres.zip(alturas)` produce parejas como estas:

```scala
List(("Ana", 1.65), ("Luis", 1.80), ("Marta", 1.70))
```

Si las listas tienen diferente longitud, `zip` solo utiliza las parejas que existen en las dos listas.

### `for` con `yield`

`yield` crea una colección nueva con el resultado de cada vuelta:

```scala
val cuadrados = for (numero <- 1 to 5) yield numero * numero
println(cuadrados) // Vector(1, 4, 9, 16, 25)
```

---

## 8. `match`: alternativa a muchos `if`

`match` compara un valor con varios casos.

```scala
val nota = 8

val calificacion = nota match {
  case 10      => "Matrícula"
  case 9        => "Sobresaliente"
  case 7 | 8   => "Notable"
  case _        => "Necesita mejorar"
}

println(calificacion)
```

`case _` significa “cualquier otro caso” y funciona como caso por defecto.

---

## 9. Ejercicio final de examen

**Objetivo:** recorrer un `Array` con `while`, guardar en una lista las puntuaciones menores o iguales que `21` y sumarlas.

```scala
object RepasoExamen extends App {

  val puntuaciones = Array(15, 25, 19, 22, 21, 14)

  var i = 0
  var puntuacionesValidas = List[Int]()
  var sumaTotal = 0

  while (i < puntuaciones.length) {
    val puntoActual = puntuaciones(i)

    if (puntoActual <= 21) {
      puntuacionesValidas = puntuacionesValidas :+ puntoActual
      sumaTotal += puntoActual
    }

    i += 1
  }

  println(s"Lista de puntuaciones válidas: $puntuacionesValidas")
  println(s"Suma total de las válidas: $sumaTotal")
}
```

**Salida esperada:**

```text
Lista de puntuaciones válidas: List(15, 19, 21, 14)
Suma total de las válidas: 69
```

---

## 10. Errores típicos de examen

1. Los índices empiezan en `0`, no en `1`.
2. En un `while`, no olvides aumentar el contador.
3. Un `val` no se puede reasignar.
4. `=` asigna y `==` compara.
5. Una lista vacía puede necesitar tipo: `List[Int]()`.
6. Un `Array` permite cambiar posiciones; una `List` normal no.
7. `&&` significa “y”; `||` significa “o”.
8. Revisa que cada `{` tenga su `}` y cada `(` tenga su `)`.
9. En una declaración, el tipo va después de `:`: `val edad: Int = 20`.
10. Scala devuelve automáticamente la última expresión de una función.
11. Si relacionas dos listas por índice, comprueba que tengan el mismo tamaño.
12. Con `zip`, recuerda que se trabaja con parejas: `(nombre, altura)`.
13. En `foreach`, la variable antes de `=>` representa el elemento actual.
14. `foreach` sirve para ejecutar una acción; `map` sirve para transformar y guardar resultados.

---

## 11. Chuleta rápida

```scala
val fijo = 10
var cambia = 10

if (condicion) { ... } else { ... }

def doble(numero: Int): Int = numero * 2

val lista = List(1, 2, 3)
val array = Array(1, 2, 3)

lista.foreach(elemento => println(elemento))
val pares = lista.filter(elemento => elemento % 2 == 0)
val dobles = lista.map(elemento => elemento * 2)

for (elemento <- lista) {
  println(elemento)
}

for (i <- nombres.indices) {
  println(nombres(i))
}

for ((nombre, altura) <- nombres.zip(alturas)) {
  println(s"$nombre mide $altura")
}

while (condicion) {
  // código
}
```

**Resumen:** usa `val` siempre que puedas; usa `if` para decidir; `foreach` y `for` para recorrer; `map` para transformar; `filter` para seleccionar; y `while` cuando necesites controlar manualmente el índice.
