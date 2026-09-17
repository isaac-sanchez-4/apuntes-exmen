# 🐍 Guía de Python — de cero a nivel examen

> Apuntes prácticos en español para entender Python, no solo memorizar su sintaxis.
>
> **Idea principal:** un programa recibe datos, los transforma y produce un resultado. En cada ejercicio intenta identificar esas tres partes.

---

## 📚 Índice

1. [Primer programa y sintaxis](#1-primer-programa-y-sintaxis)
2. [Variables y tipos de datos](#2-variables-y-tipos-de-datos)
3. [Operadores](#3-operadores)
4. [Condicionales](#4-condicionales)
5. [Bucles](#5-bucles)
6. [Colecciones](#6-colecciones)
7. [Funciones](#7-funciones)
8. [Strings](#8-strings)
9. [Excepciones](#9-excepciones)
10. [Ficheros](#10-ficheros)
11. [Programación orientada a objetos](#11-programación-orientada-a-objetos)
12. [Errores frecuentes de examen](#12-errores-frecuentes-de-examen)
13. [Chuleta final](#13-chuleta-final)

---

## 1. Primer programa y sintaxis

Python ejecuta las instrucciones de arriba abajo. A diferencia de otros lenguajes, **la indentación forma parte de la sintaxis**: normalmente se utilizan cuatro espacios.

```python
# Esto es un comentario: Python no lo ejecuta.
print("¡Hola, mundo!")

if True:
    print("Este bloque está indentado")
```

### Reglas básicas

- Las instrucciones dentro del mismo bloque deben tener la misma indentación.
- No es necesario escribir `;` al final de cada línea.
- Los nombres distinguen mayúsculas y minúsculas: `nombre` y `Nombre` son diferentes.
- Usa nombres descriptivos en `snake_case`: `nota_media`, `numero_alumnos`.
- Las constantes se suelen escribir en mayúsculas: `PI = 3.1416`.

---

## 2. Variables y tipos de datos

Una variable es un nombre que referencia un valor. Python **infiere el tipo automáticamente**, por lo que no hay que declararlo antes.

```python
nombre = "Ana"       # str: texto
edad = 20             # int: entero
altura = 1.68         # float: decimal
aprobado = True       # bool: verdadero o falso
sin_dato = None       # ausencia de valor

print(type(edad))     # <class 'int'>
```

> `=` asigna un valor. `==` compara dos valores.

### Conversión de tipos

```python
texto = "25"
numero = int(texto)
decimal = float(numero)
otro_texto = str(decimal)

print(numero + 5)     # 30
```

Si el texto no representa un número válido, `int()` o `float()` producen `ValueError`.

### Entrada por teclado

`input()` **siempre devuelve un texto**, aunque el usuario escriba un número.

```python
nombre = input("Nombre: ")
edad = int(input("Edad: "))

print(f"{nombre} tiene {edad} años")
```

---

## 3. Operadores

### Aritméticos

| Operador | Significado | Ejemplo | Resultado |
|---|---|---:|---:|
| `+` | suma | `7 + 2` | `9` |
| `-` | resta | `7 - 2` | `5` |
| `*` | multiplicación | `7 * 2` | `14` |
| `/` | división decimal | `7 / 2` | `3.5` |
| `//` | división entera | `7 // 2` | `3` |
| `%` | resto | `7 % 2` | `1` |
| `**` | potencia | `2 ** 3` | `8` |

El operador `%` es especialmente útil para saber si un número es par:

```python
numero = 14
es_par = numero % 2 == 0
```

### Comparación y lógica

```python
edad >= 18       # mayor o igual
nota == 10       # igualdad
nombre != ""     # distinto

# and: todas las condiciones deben cumplirse
# or: basta con que se cumpla una
# not: invierte el resultado
puede_entrar = edad >= 18 and tiene_entrada
```

### Asignación abreviada

```python
contador = 0
contador += 1    # contador = contador + 1
contador *= 2    # contador = contador * 2
```

---

## 4. Condicionales

Los condicionales permiten ejecutar un bloque solo cuando se cumple una condición.

```python
nota = 7.5

if nota >= 9:
    resultado = "Sobresaliente"
elif nota >= 5:
    resultado = "Aprobado"
else:
    resultado = "Suspenso"

print(resultado)
```

Python evalúa las condiciones en orden y ejecuta **solo el primer bloque verdadero**.

### Condición en una sola línea

```python
edad = 20
mensaje = "mayor de edad" if edad >= 18 else "menor de edad"
```

Úsala solo para expresiones sencillas; si la decisión es compleja, es más legible usar `if` normal.

### Valores considerados falsos

Python interpreta como falso (`False`) valores como `0`, `""`, `[]`, `{}`, `None` y `False`.

```python
nombre = input("Nombre: ")
if nombre:
    print(f"Hola, {nombre}")
else:
    print("No has escrito ningún nombre")
```

---

## 5. Bucles

### `for`: recorrer una colección o un rango

```python
for numero in range(1, 6):
    print(numero)
```

`range(inicio, fin, paso)` incluye `inicio`, pero **no incluye `fin`**.

```python
range(5)          # 0, 1, 2, 3, 4
range(2, 10, 2)   # 2, 4, 6, 8
range(5, 0, -1)   # 5, 4, 3, 2, 1
```

### `while`: repetir mientras se cumpla una condición

```python
i = 1
while i <= 5:
    print(i)
    i += 1  # imprescindible para evitar un bucle infinito
```

Usa `for` cuando conozcas o puedas recorrer una secuencia; usa `while` cuando la repetición dependa de una condición que cambia durante el programa.

### `break` y `continue`

```python
for numero in range(1, 11):
    if numero == 7:
        break       # termina el bucle completo
    if numero % 2 == 0:
        continue    # salta a la siguiente vuelta
    print(numero)   # 1, 3, 5
```

### `else` en bucles

El `else` se ejecuta si el bucle termina normalmente, es decir, si no se ha usado `break`.

```python
for numero in range(2, 10):
    if numero == 5:
        print("Encontrado")
        break
else:
    print("No encontrado")
```

### `enumerate`: elemento e índice

```python
nombres = ["Ana", "Luis", "Marta"]

for indice, nombre in enumerate(nombres, start=1):
    print(f"{indice}. {nombre}")
```

Es más claro y seguro que mantener un contador manual.

---

## 6. Colecciones

### 6.1 Listas: ordenadas y modificables

Una lista puede contener valores repetidos y sus elementos se pueden cambiar.

```python
notas = [5, 8, 10, 6]

notas.append(9)          # añade al final
notas.insert(1, 7)       # inserta en una posición
notas.extend([4, 6])     # añade varios elementos
notas.remove(5)          # elimina la primera aparición del valor
ultima = notas.pop()     # elimina y devuelve el último elemento

print(notas[0])          # primer elemento
print(notas[-1])         # último elemento
print(notas[1:4])        # desde 1 incluido hasta 4 excluido
```

> Los índices empiezan en `0`. Acceder a una posición inexistente produce `IndexError`.

Operaciones útiles:

```python
len(notas)       # número de elementos
sum(notas)       # suma
min(notas)       # mínimo
max(notas)       # máximo
sorted(notas)    # nueva lista ordenada
notas.sort()     # ordena la lista original
```

### Comprensión de listas

Permite crear una lista aplicando una expresión a cada elemento, opcionalmente con una condición.

```python
numeros = range(1, 6)
cuadrados = [numero ** 2 for numero in numeros]
pares = [numero for numero in numeros if numero % 2 == 0]

# Equivale a:
pares_largos = []
for numero in numeros:
    if numero % 2 == 0:
        pares_largos.append(numero)
```

### 6.2 Tuplas: agrupaciones inmutables

Una tupla se parece a una lista, pero no puede modificarse después de crearla.

```python
coordenada = (10, 20)
x, y = coordenada  # desempaquetado

# coordenada[0] = 5  # TypeError
```

Úsala para datos que representan una unidad fija o cuando quieres impedir modificaciones accidentales.

### 6.3 Diccionarios: clave y valor

Un diccionario relaciona cada clave con un valor. Las claves deben ser únicas.

```python
alumno = {
    "nombre": "Ana",
    "edad": 20,
    "nota": 8.5,
}

print(alumno["nombre"])
alumno["curso"] = "Python"  # crear una clave
alumno["nota"] = 9           # modificar un valor
```

Para evitar un error si la clave no existe, usa `get()`:

```python
ciudad = alumno.get("ciudad", "Desconocida")

for clave, valor in alumno.items():
    print(f"{clave}: {valor}")
```

Otros métodos importantes: `keys()`, `values()`, `items()`, `pop()` y `update()`.

Comprensión de diccionarios:

```python
cuadrados = {numero: numero ** 2 for numero in range(1, 5)}
```

### 6.4 Conjuntos (`set`): valores sin duplicados

```python
colores = {"rojo", "azul", "rojo"}
print(colores)  # {'rojo', 'azul'}

colores.add("verde")
colores.discard("amarillo")  # no falla si no existe
```

Operaciones de conjuntos:

```python
a = {1, 2, 3}
b = {3, 4, 5}

print(a | b)  # unión
print(a & b)  # intersección
print(a - b)  # elementos de a que no están en b
```

---

## 7. Funciones

Una función agrupa instrucciones reutilizables. Recibe parámetros y puede devolver un resultado con `return`.

```python
def calcular_media(notas):
    """Devuelve la media de una lista de notas."""
    return sum(notas) / len(notas)

media = calcular_media([7, 8, 9])
print(media)  # 8.0
```

Si una función no tiene `return`, devuelve `None` automáticamente.

### Parámetros por posición y por nombre

```python
def presentar(nombre, saludo="Hola"):
    return f"{saludo}, {nombre}"

presentar("Ana")
presentar(nombre="Luis", saludo="Buenos días")
```

Los parámetros con valor por defecto deben aparecer después de los obligatorios.

### `*args` y `**kwargs`

```python
def sumar_todos(*numeros):
    return sum(numeros)

sumar_todos(1, 2, 3, 4)


def mostrar_datos(**datos):
    for clave, valor in datos.items():
        print(clave, valor)

mostrar_datos(nombre="Ana", edad=20)
```

- `*args` recibe varios argumentos posicionales en una tupla.
- `**kwargs` recibe varios argumentos con nombre en un diccionario.

### Ámbito de las variables

Una variable creada dentro de una función es local. No depende de una variable externa con el mismo nombre.

```python
def duplicar(numero):
    resultado = numero * 2  # solo existe dentro de la función
    return resultado
```

Como regla general, es preferible pasar datos como parámetros y devolver resultados antes que usar variables globales.

### Funciones lambda

Una `lambda` es una función pequeña de una sola expresión:

```python
cuadrado = lambda numero: numero ** 2
print(cuadrado(4))  # 16

personas = [("Ana", 30), ("Luis", 20)]
ordenadas = sorted(personas, key=lambda persona: persona[1])
```

Para funciones con varias instrucciones, usa `def`, que resulta más legible.

---

## 8. Strings

Los strings son secuencias de caracteres y no se pueden modificar directamente: son **inmutables**.

```python
texto = "  Python es útil  "

texto = texto.strip()           # quita espacios laterales
texto.lower()                   # minúsculas
texto.upper()                   # mayúsculas
texto.replace("útil", "potente")
texto.startswith("Python")
texto.endswith("útil")
```

### Formatear texto con f-strings

```python
nombre = "Ana"
nota = 8.456
print(f"{nombre} ha sacado un {nota:.2f}")
```

`:.2f` muestra un número decimal con dos posiciones.

### Dividir y unir

```python
frase = "Python es sencillo"
palabras = frase.split()              # ["Python", "es", "sencillo"]
resultado = "-".join(palabras)        # "Python-es-sencillo"
```

### Recorrer un string

```python
vocales = 0
for caracter in "programacion":
    if caracter in "aeiou":
        vocales += 1
```

---

## 9. Excepciones

Una excepción es un error que ocurre durante la ejecución. Con `try` y `except` podemos controlarlo y mostrar un mensaje útil.

```python
try:
    edad = int(input("Edad: "))
    resultado = 100 / edad
except ValueError:
    print("Debes introducir un número entero")
except ZeroDivisionError:
    print("La edad no puede ser cero")
else:
    print(f"Resultado: {resultado}")
finally:
    print("Fin del programa")
```

- `try`: código que puede fallar.
- `except`: respuesta a un tipo de error concreto.
- `else`: se ejecuta si no hubo errores.
- `finally`: se ejecuta siempre.

Evita `except Exception` o un `except` vacío si no es necesario: puede ocultar errores reales.

También puedes provocar una excepción de forma intencionada:

```python
def validar_edad(edad):
    if edad < 0:
        raise ValueError("La edad no puede ser negativa")
```

---

## 10. Ficheros

La forma recomendada de trabajar con archivos es `with open(...)`, porque cierra el archivo automáticamente aunque ocurra un error.

### Leer un archivo

```python
with open("datos.txt", "r", encoding="utf-8") as archivo:
    contenido = archivo.read()

# También se puede leer línea a línea:
with open("datos.txt", encoding="utf-8") as archivo:
    for linea in archivo:
        print(linea.strip())
```

### Escribir y añadir

```python
with open("salida.txt", "w", encoding="utf-8") as archivo:
    archivo.write("Primera línea\n")

with open("salida.txt", "a", encoding="utf-8") as archivo:
    archivo.write("Línea añadida\n")
```

- `r`: lectura.
- `w`: escritura; sobrescribe el archivo si ya existe.
- `a`: añade al final.

### JSON

```python
import json

persona = {"nombre": "Ana", "edad": 20}

with open("persona.json", "w", encoding="utf-8") as archivo:
    json.dump(persona, archivo, ensure_ascii=False, indent=2)

with open("persona.json", encoding="utf-8") as archivo:
    persona_leida = json.load(archivo)
```

---

## 11. Programación orientada a objetos

Una **clase** define la estructura y el comportamiento de un tipo de objeto. Un **objeto** es una instancia concreta de esa clase.

```python
class Alumno:
    def __init__(self, nombre, nota):
        self.nombre = nombre
        self.nota = nota

    def esta_aprobado(self):
        return self.nota >= 5

    def __str__(self):
        return f"{self.nombre}: {self.nota}"

alumno = Alumno("Ana", 8.5)
print(alumno)
print(alumno.esta_aprobado())
```

- `__init__` se ejecuta al crear el objeto.
- `self` representa al objeto actual y permite acceder a sus atributos y métodos.
- Un atributo guarda datos; un método define una acción.

### Herencia

```python
class AlumnoBecado(Alumno):
    def __init__(self, nombre, nota, beca):
        super().__init__(nombre, nota)
        self.beca = beca
```

`super()` permite reutilizar el comportamiento de la clase padre.

---

## 12. Errores frecuentes de examen

1. **Olvidar que `input()` devuelve `str`:** convierte con `int()` o `float()`.
2. **Confundir `=` y `==`:** el primero asigna; el segundo compara.
3. **Usar `and`/`or` de forma incorrecta:** escribe las condiciones completas y usa paréntesis si hace falta.
4. **Olvidar los dos puntos `:`** después de `if`, `for`, `while`, `def`, `class`, `try`, etc.
5. **Indentación incorrecta:** todo el bloque debe tener la misma sangría.
6. **Confundir `append` y `extend`:** `append([3, 4])` añade una lista como un único elemento; `extend([3, 4])` añade sus elementos.
7. **Acceder a una clave inexistente:** usa `diccionario.get("clave")` si no estás seguro de que exista.
8. **Confundir `sort()` y `sorted()`:** `sort()` modifica la lista; `sorted()` devuelve una nueva.
9. **Modificar una lista mientras la recorres:** crea otra lista o recorre una copia.
10. **Usar `while` sin actualizar la condición:** puede crear un bucle infinito.
11. **Usar `is` para comparar valores:** utiliza `==`; `is` comprueba identidad y se reserva normalmente para `None`.
12. **Usar un `except` demasiado general:** captura errores concretos para no ocultar fallos.
13. **Olvidar `return`:** imprimir un resultado no es lo mismo que devolverlo.
14. **Sobrescribir un fichero sin querer:** `"w"` borra el contenido anterior; usa `"a"` para añadir.

---

## 13. Chuleta final

```python
# Variables y entrada
nombre = input("Nombre: ")
edad = int(input("Edad: "))

# Condición
if edad >= 18:
    mensaje = "adulto"
else:
    mensaje = "menor"

# Bucle
for numero in range(5):
    print(numero)

# Lista y comprensión
numeros = [1, 2, 3, 4]
pares = [n for n in numeros if n % 2 == 0]

# Diccionario
persona = {"nombre": "Ana", "edad": 20}
print(persona.get("ciudad", "sin ciudad"))

# Función
def doble(numero):
    return numero * 2

# Excepción
try:
    numero = int("10")
except ValueError:
    print("Valor no válido")

# Fichero
with open("datos.txt", encoding="utf-8") as archivo:
    texto = archivo.read()
```

> **Método para resolver ejercicios:** 1) entiende qué datos entran; 2) elige la estructura adecuada; 3) separa la lógica en funciones; 4) prueba casos normales y casos límite; 5) revisa tipos, indentación y valores devueltos.

---

## ✅ Resumen en una frase

Usa **condicionales** para decidir, **bucles** para repetir, **listas/diccionarios** para organizar datos, **funciones** para reutilizar lógica y **excepciones** para controlar errores.
