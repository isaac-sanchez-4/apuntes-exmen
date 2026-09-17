# 📘 Guía definitiva de Scala: desde cero hasta nivel examen

Guía práctica para aprender los fundamentos de Scala paso a paso. Los ejemplos están pensados para copiar, pegar y repasar directamente.

> **Nota:** Los ejemplos son compatibles con Scala 2 y, salvo pequeños detalles de sintaxis, también con Scala 3.

---

## 1. Variables: ¿cómo guardamos datos?

En Scala hay dos formas principales de crear una variable: `val`, que es inmutable, y `var`, que es mutable.

### `val`: valor inmutable

Un `val` no puede apuntar a otro valor después de inicializarse. Es la opción recomendada siempre que sea posible.

```scala
// Sin especificar el tipo: Scala lo infiere automáticamente
val nombre = "Alex"
val edad = 20

// Con tipo explícito: puede hacer el código más claro
val ciudad: String = "Madrid"
val nota: Double = 8.5

// edad = 21 // ERROR: un val no se puede reasignar
```

> Que un `val` sea inmutable significa que no se puede reasignar la variable. Si contiene una colección mutable, el contenido puede tener un comportamiento distinto; por eso conviene preferir colecciones inmutables.

### `var`: variable mutable

Un `var` sí puede reasignarse. Se utiliza cuando el valor tiene que cambiar, por ejemplo, en un contador o acumulador.

```scala
var puntuacion: Int = 0
puntuacion = puntuacion + 10
puntuacion += 5

println(puntuacion) // 15
```

**Regla rápida:** usa `val` por defecto y `var` solo cuando necesites reasignar el valor.

---

## 2. Tipos de datos más comunes

| Tipo | Ejemplo | Uso |
|---|---|---|
| `Int` | `10`, `-5` | Números enteros |
| `Double` | `3.14`, `9.99` | Números decimales |
| `String` | `"Hola"` | Texto |
| `Boolean` | `true`, `false` | Verdadero o falso |
| `Char` | `'A'` | Un único carácter |
| `Long` | `10000000000L` | Enteros grandes |
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

Si el texto no representa un número válido, `toInt` puede producir un error. En ejercicios sencillos se suele asumir que el texto es correcto.

### Interpolación de strings

La `s` delante de una cadena permite insertar variables usando `$`.

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

println(a + b)  // 13
println(a - b)  // 7
println(a * b)  // 30
println(a / b)  // 3, división entera
println(a % b)  // 1, resto

println(a == b) // igualdad
println(a != b) // desigualdad
println(a > b)
println(a >= b)
```

Los operadores lógicos más importantes son:

- `&&`: y
- `||`: o
- `!`: negación

```scala
val tieneEntrada = true
val esMayorDeEdad = true

if (tieneEntrada && esMayorDeEdad) {
  println("Puede entrar")
}
```

---

## 4. Condicionales: tomando decisiones

`if` y `else` permiten ejecutar código dependiendo de una condición. En Scala, un `if` también puede devolver un valor.

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

Como expresión:

```scala
val resultado = if (puntos <= 21) "válido" else "no válido"
println(resultado)
```

> En Scala se suele evitar el operador ternario de otros lenguajes (`condicion ? a : b`), porque `if (...) ... else ...` cumple esa función.

---

## 5. Funciones (`def`): pequeñas fábricas de código

Una función recibe parámetros, realiza un trabajo y devuelve un resultado.

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

La última expresión del cuerpo se devuelve automáticamente. No hace falta escribir `return`.

La función anterior puede escribirse de forma más corta:

```scala
def comprobarManoCorta(puntuacion: Int): Boolean = puntuacion <= 21
```

Una función con varios parámetros:

```scala
def sumar(a: Int, b: Int): Int = a + b

val resultado = sumar(4, 6)
println(resultado) // 10
```

Una función que solo imprime tiene como resultado `Unit`:

```scala
def saludar(nombre: String): Unit = {
  println(s"Hola, $nombre")
}
```

---

## 6. Colecciones: `Array` y `List`

Las colecciones sirven para guardar varios datos juntos.

### `Array`: tamaño fijo

Un `Array` mantiene su tamaño, pero sus posiciones se pueden modificar.

```scala
val notas = Array(5, 7, 9)

// Los índices empiezan en 0
notas(0) = 6

println(notas(0)) // 6
println(notas.length) // 3
```

Un `Array` de tamaño 3 tiene las posiciones `0`, `1` y `2`. Intentar acceder a una posición inexistente produce un error.

### `List`: colección inmutable

Las listas normales son inmutables. Al “añadir” un elemento, se crea una lista nueva.

```scala
val numeros = List(10, 20, 30)

println(numeros.head) // 10, primer elemento
println(numeros.tail) // List(20, 30), resto de elementos
println(numeros.length) // 3
```

#### Crear una lista progresivamente

Para actualizar una referencia durante un ejercicio, se puede usar `var`:

```scala
var misNumeros = List[Int]()

misNumeros = misNumeros :+ 10
misNumeros = misNumeros :+ 20

println(misNumeros) // List(10, 20)
```

> `:+` añade al final, pero en listas grandes puede ser menos eficiente. El operador `::` añade al principio y es la forma habitual en Scala:

```scala
val lista = 30 :: 20 :: 10 :: Nil
println(lista) // List(30, 20, 10)
```

También se pueden concatenar listas:

```scala
val primera = List(1, 2)
val segunda = List(3, 4)
val completa = primera ++ segunda

println(completa) // List(1, 2, 3, 4)
```

### `map`, `filter` y `foreach`

Estas operaciones son muy habituales porque evitan modificar variables manualmente.

```scala
val numeros = List(1, 2, 3, 4, 5)

val duplicados = numeros.map(numero => numero * 2)
val pares = numeros.filter(numero => numero % 2 == 0)

println(duplicados) // List(2, 4, 6, 8, 10)
println(pares)      // List(2, 4)
```

- `map` transforma todos los elementos.
- `filter` conserva solo los elementos que cumplen una condición.
- `foreach` recorre los elementos para ejecutar una acción y no crea una nueva colección.

---

## 7. Bucles: repitiendo acciones

### Bucle `while`: control manual

Hay que crear un contador que empiece en `0` y aumentarlo en cada vuelta.

```scala
val arrayNumeros = Array(10, 20, 30)
var i = 0

while (i < arrayNumeros.length) {
  println(arrayNumeros(i))
  i += 1 // Si se olvida, el bucle será infinito
}
```

### Bucle `foreach`: control automático

Recorre cada elemento sin necesitar un índice.

```scala
val listaNombres = List("Ana", "Luis", "Marta")

listaNombres.foreach { nombre =>
  println(s"Hola, $nombre")
}
```

También puede escribirse con una expresión corta:

```scala
listaNombres.foreach(nombre => println(s"Hola, $nombre"))
```

### Bucle `for`

Es útil cuando queremos recorrer colecciones de forma clara:

```scala
for (nombre <- listaNombres) {
  println(s"Hola, $nombre")
}
```

Se puede añadir una condición, llamada guardia:

```scala
for (numero <- 1 to 10 if numero % 2 == 0) {
  println(numero)
}
```

- `1 to 10` incluye el `10`.
- `1 until 10` llega hasta `9` y no incluye el `10`.

Un `for` también puede crear una colección con `yield`:

```scala
val cuadrados = for (numero <- 1 to 5) yield numero * numero
println(cuadrados) // Vector(1, 4, 9, 16, 25)
```

---

## 8. `match`: alternativa clara a muchos `if`

`match` permite comparar un valor con varios casos.

```scala
val nota = 8

val calificacion = nota match {
  case 10 => "Matrícula"
  case 9   => "Sobresaliente"
  case 7 | 8 => "Notable"
  case _   => "Necesita mejorar"
}

println(calificacion)
```

El caso `_` significa “cualquier otro valor” y funciona como caso por defecto.

---

## 9. Ejercicio final de examen: todo junto

**Objetivo:** tenemos un `Array` con los puntos de varios jugadores. Hay que recorrerlo con un `while`. Si los puntos son válidos (menores o iguales que `21`), se guardan en una lista nueva y se suman a un total general.

```scala
object RepasoExamen extends App {

  // 1. Datos iniciales
  val puntuaciones = Array(15, 25, 19, 22, 21, 14)

  // 2. Variables de trabajo
  var i = 0
  var puntuacionesValidas = List[Int]()
  var sumaTotal = 0

  // 3. Recorremos el Array
  while (i < puntuaciones.length) {

    // Obtenemos el elemento de la posición actual
    val puntoActual = puntuaciones(i)

    // 4. Comprobamos si es válido
    if (puntoActual <= 21) {
      puntuacionesValidas = puntuacionesValidas :+ puntoActual
      sumaTotal += puntoActual
    }

    // 5. Avanzamos a la siguiente posición
    i += 1
  }

  // 6. Mostramos los resultados
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

1. **Olvidar que los índices empiezan en `0`.** En un `Array` de longitud `3`, la última posición es `2`.
2. **Olvidar `i += 1` en un `while`.** Esto puede crear un bucle infinito.
3. **Intentar reasignar un `val`.** Si un dato cambia, hay que usar `var` o, mejor aún, crear una nueva colección.
4. **Confundir `=` con `==`.** `=` se usa para asignar; `==` para comparar.
5. **Olvidar el tipo de una lista vacía.** `List[Int]()` deja claro que guardará enteros.
6. **Confundir `Array` y `List`.** El contenido de un `Array` puede cambiar; una `List` normal es inmutable.
7. **Usar `&&` cuando se quiere decir “o”.** Para “o” se usa `||`.
8. **No poner llaves o paréntesis correctamente.** Revisa siempre que cada `{` tenga su `}` y cada `(` su `)`. 
9. **Confundir `:` con `=` en una declaración.** Por ejemplo: `val edad: Int = 20`.
10. **Usar `return` innecesariamente.** Normalmente Scala devuelve automáticamente la última expresión de una función.

---

## 11. Chuleta rápida

```scala
val fijo = 10                    // no se puede reasignar
var cambia = 10                  // sí se puede reasignar

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

while (condicion) {
  // código
}
```

La idea fundamental es: **preferir `val`, usar colecciones inmutables cuando sea posible y dejar `var` y `while` para los ejercicios en los que el control manual sea necesario.**
