# CHETSHETTSCALAOFICIAL

## Variables

```scala
var x = 5
```

**Bien**

```scala
x = 6
```

Variable.

```scala
val x = 5
```

**Malo**

```scala
x = 6
```

Constante.

```scala
var x: Double = 5
```

Tipo explícito.

## Funciones

**Bien**

```scala
def f(x: Int) = { x * x }
```

**Malo**

```scala
def f(x: Int)   { x * x }
```

Definir función.

**Error oculto:** sin `=` se convierte en un procedimiento que devuelve `Unit`; causa estragos. Obsoleto en Scala 2.13.

**Bien**

```scala
def f(x: Any) = println(x)
```

**Malo**

```scala
def f(x) = println(x)
```

Definir función. Error de sintaxis: se necesitan tipos para cada argumento.

```scala
type R = Double
```

Tipo alias.

```scala
def f(x: R)
```

vs.

```scala
def f(x: => R)
```

Llamada por valor vs. llamada por nombre (parámetros diferidos).

```scala
(x: R) => x * x
```

Función anónima.

```scala
(1 to 5).map(_ * 2)
```

vs.

```scala
(1 to 5).reduceLeft(_ + _)
```

Función anónima: el guion bajo coincide posicionalmente con el argumento.

```scala
(1 to 5).map(x => x * x)
```

Función anónima: para usar un argumento dos veces, hay que nombrarlo.

```scala
(1 to 5).map { x =>
  val y = x * 2
  println(y)
  y
}
```

Función anónima: el estilo de bloque devuelve la última expresión.

```scala
(1 to 5) filter {
  _ % 2 == 0
} map {
  _ * 2
}
```

Funciones anónimas: estilo de tubería, o también entre paréntesis.

```scala
def compose(g: R => R, h: R => R) =
  (x: R) => g(h(x))

val f = compose(_ * 2, _ - 1)
```

Funciones anónimas: para pasar varios bloques, se necesitan paréntesis exteriores.

```scala
val zscore =
  (mean: R, sd: R) =>
    (x: R) =>
      (x - mean) / sd
```

Currificación, sintaxis obvia.

```scala
def zscore(mean: R, sd: R) =
  (x: R) =>
    (x - mean) / sd
```

Currificación, sintaxis obvia.

```scala
def zscore(mean: R, sd: R)(x: R) =
  (x - mean) / sd
```

Currying, sintaxis del azúcar. Pero entonces:

```scala
val normer =
  zscore(7, 0.4) _
```

Necesitas un guion bajo al final para obtener la versión parcial, solo para la versión de azúcar.

```scala
def mapmake[T](g: T => T)(seq: List[T]) =
  seq.map(g)
```

Tipo genérico.

```scala
5.+(3)
5 + 3

(1 to 5) map (_ * 2)
```

Azúcar de inserción.

```scala
def sum(args: Int*) =
  args.reduceLeft(_ + _)
```

Varargs.

## Paquetes

```scala
import scala.collection._
```

Importación comodín.

```scala
import scala.collection.Vector

import scala.collection.{Vector, Sequence}
```

Importación selectiva.

```scala
import scala.collection.{Vector => Vec28}
```

Cambiar el nombre de la importación.

```scala
import java.util.{Date => _, _}
```

Importar todo `java.util` excepto `Date`.

Al inicio del archivo:

```scala
package pkg
```

Embalaje por alcance:

```scala
package pkg {
  ...
}
```

Paquete singleton:

```scala
package object pkg {
  ...
}
```

Declara un paquete.

## Estructuras de datos

```scala
(1, 2, 3)
```

Literal de tupla (`Tuple3`).

```scala
var (x, y, z) = (1, 2, 3)
```

Desestructuración de la vinculación: desempaquetado de tuplas mediante coincidencia de patrones.

**Malo**

```scala
var x, y, z = (1, 2, 3)
```

Error oculto: cada variable queda asignada a la tupla completa.

```scala
var xs = List(1, 2, 3)
```

Lista inmutable.

```scala
xs(2)
```

Indexación entre paréntesis.

```scala
1 :: List(2, 3)
```

Desventajas.

```scala
1 to 5
```

Igual que:

```scala
1 until 6
```

```scala
1 to 10 by 2
```

Azúcar de la huerta.

```scala
()
```

Los paréntesis vacíos son valores singleton del tipo `Unit`, equivalente a `void` en C y Java.

## Construcciones de control

```scala
if (check) happy else sad
```

Condicional.

```scala
if (check) happy
```

Igual que:

```scala
if (check) happy else ()
```

Azúcar condicional.

```scala
while (x < 5) {
  println(x)
  x += 1
}
```

Bucle `while`.

```scala
do {
  println(x)
  x += 1
} while (x < 5)
```

Bucle `do-while`.

```scala
import scala.util.control.Breaks._

breakable {
  for (x <- xs) {
    if (Math.random < 0.1)
      break
  }
}
```

Descanso.

```scala
for (x <- xs if x % 2 == 0)
  yield x * 10
```

Igual que:

```scala
xs.filter(_ % 2 == 0).map(_ * 10)
```

Para comprensión: filtro y mapa.

```scala
for ((x, y) <- xs zip ys)
  yield x * y
```

Igual que:

```scala
(xs zip ys) map {
  case (x, y) => x * y
}
```

Para la comprensión: desestructuración de la unión.

```scala
for (x <- xs; y <- ys)
  yield x * y
```

Igual que:

```scala
xs flatMap { x =>
  ys map { y =>
    x * y
  }
}
```

Para comprender: producto cruzado.

```scala
for (x <- xs; y <- ys) {
  val div = x / y.toFloat
  println("%d/%d = %.1f".format(x, y, div))
}
```

Para comprensión: estilo casi imperativo con `sprintf`.

```scala
for (i <- 1 to 5) {
  println(i)
}
```

Para comprensión: iterar incluyendo el límite superior.

```scala
for (i <- 1 until 5) {
  println(i)
}
```

Para comprensión: iterar omitiendo el límite superior.

## Coincidencia de patrones

**Bien**

```scala
(xs zip ys) map {
  case (x, y) => x * y
}
```

**Malo**

```scala
(xs zip ys) map {
  (x, y) => x * y
}
```

Utiliza `case` en los argumentos de la función para la coincidencia de patrones.

**Malo**

```scala
val v42 = 42

3 match {
  case v42 => println("42")
  case _   => println("Not 42")
}
```

`v42` se interpreta como un nombre que coincide con cualquier valor `Int`, por lo que se imprime `42`.

**Bien**

```scala
val v42 = 42

3 match {
  case `v42` => println("42")
  case _     => println("Not 42")
}
```

``v42`` con comillas invertidas se interpreta como el valor existente `v42`, por lo que se imprime `Not 42`.

**Bien**

```scala
val UppercaseVal = 42

3 match {
  case UppercaseVal => println("42")
  case _            => println("Not 42")
}
```

`UppercaseVal` se trata como un valor existente, en lugar de una nueva variable de patrón, porque comienza con una letra mayúscula. Por lo tanto, el valor contenido en `UppercaseVal` se compara con `3`, y se imprime `Not 42`.

## Orientación a objetos

```scala
class C(x: R)
```

Los parámetros del constructor `x` solo están disponibles en el cuerpo de la clase.

```scala
class C(val x: R)

var c = new C(4)

c.x
```

Parámetro del constructor: miembro público automático definido.

```scala
class C(var x: R) {
  assert(x > 0, "positive please")
  var y = x
  val readonly = 5
  private var secret = 1
  def this() = this(42)
}
```

El constructor es el cuerpo de la clase.

- Declara un miembro público.
- Declara un miembro que se puede obtener, pero no modificar.
- Declara un miembro privado.
- Declara un constructor alternativo.

```scala
new {
  ...
}
```

Clase anónima.

```scala
abstract class D { ... }
```

Define una clase abstracta que no se puede crear directamente.

```scala
class C extends D { ... }
```

Define una clase heredada.

```scala
class D(var x: R)

class C(x: R) extends D(x)
```

Herencia y parámetros del constructor.

```scala
object O extends D { ... }
```

Define un singleton, similar a un módulo.

```scala
trait T { ... }

class C extends T { ... }

class C extends D with T { ... }
```

Rasgos: interfaces con implementación, sin parámetros de constructor y combinables mediante mixins.

```scala
trait T1
trait T2

class C extends T1 with T2

class C extends D with T1 with T2
```

Múltiples rasgos.

```scala
class C extends D {
  override def f = ...
}
```

Debe declarar las anulaciones de métodos.

```scala
new java.io.File("f")
```

Crear objeto.

**Malo**

```scala
new List[Int]
```

**Bien**

```scala
List(1, 2, 3)
```

Error de tipo: `List` es un tipo abstracto. En su lugar, se utiliza una fábrica invocable que oculta el tipo concreto.

```scala
classOf[String]
```

Literal de clase.

```scala
x.isInstanceOf[String]
```

Verificación de tipo en tiempo de ejecución.

```scala
x.asInstanceOf[String]
```

Conversión de tipo en tiempo de ejecución.

```scala
x: String
```

Atribución de tipo en tiempo de compilación.

## Opciones

```scala
Some(42)
```

Construye un valor opcional no vacío.

```scala
None
```

El valor opcional vacío del singleton.

```scala
Option(null) == None
Option(obj.unsafeMethod)
```

Pero:

```scala
Some(null) != None
```

Fábrica de valores opcionales a prueba de valores nulos.

```scala
val optStr: Option[String] = None
```

Igual que:

```scala
val optStr = Option.empty[String]
```

Tipo explícito para valor opcional vacío y fábrica para valor opcional vacío.

### Estilo de tubería

```scala
val name: Option[String] =
  request.getParameter("name")

val upper = name.map {
  _.trim
} filter {
  _.length != 0
} map {
  _.toUpperCase
}

println(upper.getOrElse(""))
```

### Sintaxis de comprensión

```scala
val upper = for {
  name <- request.getParameter("name")
  trimmed <- Some(name.trim)
    if trimmed.length != 0
  upper <- Some(trimmed.toUpperCase)
} yield upper

println(upper.getOrElse(""))
```

### Operaciones comunes de `Option`

```scala
option.map(f(_))
```

Igual que:

```scala
option match {
  case Some(x) => Some(f(x))
  case None    => None
}
```

Aplica una función al valor opcional.

```scala
option.flatMap(f(_))
```

Igual que:

```scala
option match {
  case Some(x) => f(x)
  case None    => None
}
```

Igual que `map`, pero la función debe devolver un valor opcional.

```scala
optionOfOption.flatten
```

Igual que:

```scala
optionOfOption match {
  case Some(Some(x)) => Some(x)
  case _             => None
}
```

Extrae una opción anidada.

```scala
option.foreach(f(_))
```

Igual que:

```scala
option match {
  case Some(x) => f(x)
  case None    => ()
}
```

Aplica un procedimiento al valor opcional.

```scala
option.fold(y)(f(_))
```

Igual que:

```scala
option match {
  case Some(x) => f(x)
  case None    => y
}
```

Aplica la función al valor opcional; si está vacío, devuelve el valor predeterminado.

```scala
option.collect {
  case x => ...
}
```

Igual que:

```scala
option match {
  case Some(x) if f.isDefinedAt(x) => ...
  case Some(_)                     => None
  case None                        => None
}
```

Aplica una coincidencia de patrón parcial en el valor opcional.

```scala
option.isDefined
```

Igual que:

```scala
option match {
  case Some(_) => true
  case None    => false
}
```

Devuelve `true` si no está vacío.

```scala
option.isEmpty
```

Igual que:

```scala
option match {
  case Some(_) => false
  case None    => true
}
```

Devuelve `true` si está vacío.

```scala
option.nonEmpty
```

Igual que:

```scala
option match {
  case Some(_) => true
  case None    => false
}
```

Devuelve `true` si no está vacío.

```scala
option.size
```

Igual que:

```scala
option match {
  case Some(_) => 1
  case None    => 0
}
```

Devuelve `0` si está vacío; de lo contrario, `1`.

```scala
option.orElse(Some(y))
```

Igual que:

```scala
option match {
  case Some(x) => Some(x)
  case None    => Some(y)
}
```

Evalúa y devuelve un valor opcional alternativo si está vacío.

```scala
option.getOrElse(y)
```

Igual que:

```scala
option match {
  case Some(x) => x
  case None    => y
}
```

Evalúa y devuelve el valor predeterminado si está vacío.

```scala
option.get
```

Igual que:

```scala
option match {
  case Some(x) => x
  case None    => throw new Exception
}
```

Devuelve el valor; si está vacío, lanza una excepción.

```scala
option.orNull
```

Igual que:

```scala
option match {
  case Some(x) => x
  case None    => null
}
```

Devuelve el valor o `null` si está vacío.

```scala
option.filter(f)
```

Igual que:

```scala
option match {
  case Some(x) if f(x) => Some(x)
  case _               => None
}
```

El valor opcional satisface el predicado.

```scala
option.filterNot(f(_))
```

Igual que:

```scala
option match {
  case Some(x) if !f(x) => Some(x)
  case _                => None
}
```

El valor opcional no satisface el predicado.

```scala
option.exists(f(_))
```

Igual que:

```scala
option match {
  case Some(x) if f(x) => true
  case Some(_)         => false
  case None             => false
}
```

Aplica el predicado al valor opcional o devuelve `false` si está vacío.

```scala
option.forall(f(_))
```

Igual que:

```scala
option match {
  case Some(x) if f(x) => true
  case Some(_)         => false
  case None             => true
}
```

Aplica el predicado al valor opcional o devuelve `true` si está vacío.

```scala
option.contains(y)
```

Igual que:

```scala
option match {
  case Some(x) => x == y
  case None    => false
}
```
