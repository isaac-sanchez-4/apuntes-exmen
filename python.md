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
14. [Práctica 3 — soluciones](#14-práctica-3--soluciones)

---

## 1. Primer programa y sintaxis

Python ejecuta las instrucciones de arriba abajo. La indentación forma parte de la sintaxis y normalmente se escriben cuatro espacios por nivel.

```python
# Comentario: Python no ejecuta esta línea.
print("¡Hola, mundo!")

if True:
    print("Este bloque está indentado")
```

### Reglas básicas

- Las instrucciones del mismo bloque deben tener la misma indentación.
- No es necesario escribir `;` al final de cada línea.
- Python distingue mayúsculas y minúsculas: `nombre` y `Nombre` son diferentes.
- Usa nombres descriptivos en `snake_case`, como `nota_media`.
- Las constantes suelen escribirse en mayúsculas, como `PI = 3.1416`.

---

## 2. Variables y tipos de datos

Python infiere el tipo automáticamente.

```python
nombre = "Ana"       # str: texto
edad = 20             # int: entero
altura = 1.68         # float: decimal
aprobado = True       # bool: verdadero o falso
sin_dato = None       # ausencia de valor

print(type(edad))     # <class 'int'>
```

`=` asigna y `==` compara.

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

`input()` siempre devuelve un texto, aunque se escriba un número.

```python
nombre = input("Nombre: ")
edad = int(input("Edad: "))
print(f"{nombre} tiene {edad} años")
```

---

## 3. Operadores

| Operador | Significado | Ejemplo | Resultado |
|---|---|---:|---:|
| `+` | suma | `7 + 2` | `9` |
| `-` | resta | `7 - 2` | `5` |
| `*` | multiplicación | `7 * 2` | `14` |
| `/` | división decimal | `7 / 2` | `3.5` |
| `//` | división entera | `7 // 2` | `3` |
| `%` | resto | `7 % 2` | `1` |
| `**` | potencia | `2 ** 3` | `8` |

```python
numero = 14
es_par = numero % 2 == 0

puede_entrar = edad >= 18 and tiene_entrada
contador = 0
contador += 1
contador *= 2
```

`and` exige que se cumplan todas las condiciones, `or` solo una y `not` invierte el resultado.

---

## 4. Condicionales

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

Se ejecuta únicamente el primer bloque cuya condición sea verdadera.

```python
edad = 20
mensaje = "mayor de edad" if edad >= 18 else "menor de edad"
```

Se consideran falsos `0`, `""`, `[]`, `{}`, `None` y `False`.

---

## 5. Bucles

### `for`

```python
for numero in range(1, 6):
    print(numero)
```

`range(inicio, fin, paso)` incluye el inicio, pero no el fin.

### `while`

```python
i = 1
while i <= 5:
    print(i)
    i += 1
```

`break` termina el bucle y `continue` salta a la siguiente vuelta.

```python
for numero in range(1, 11):
    if numero == 7:
        break
    if numero % 2 == 0:
        continue
    print(numero)
```

El `else` de un bucle se ejecuta cuando termina sin `break`. `enumerate()` proporciona índice y elemento.

---

## 6. Colecciones

### Listas

Las listas son ordenadas y modificables.

```python
notas = [5, 8, 10, 6]
notas.append(9)          # añade al final
notas.insert(1, 7)       # inserta en una posición
notas.extend([4, 6])     # añade varios elementos
notas.remove(5)          # elimina la primera aparición
ultima = notas.pop()     # elimina y devuelve el último

print(notas[0])
print(notas[-1])
print(notas[1:4])
```

`len`, `sum`, `min`, `max`, `sorted` y `sort` son operaciones habituales. `sort()` modifica la lista; `sorted()` crea otra.

```python
numeros = range(1, 6)
cuadrados = [numero ** 2 for numero in numeros]
pares = [numero for numero in numeros if numero % 2 == 0]
```

### Tuplas

```python
coordenada = (10, 20)
x, y = coordenada
```

Son agrupaciones inmutables.

### Diccionarios

```python
alumno = {"nombre": "Ana", "edad": 20, "nota": 8.5}
print(alumno["nombre"])
alumno["curso"] = "Python"
alumno["nota"] = 9
ciudad = alumno.get("ciudad", "Desconocida")
```

`keys()`, `values()`, `items()`, `pop()` y `update()` son métodos importantes.

### Conjuntos

```python
colores = {"rojo", "azul", "rojo"}
colores.add("verde")
colores.discard("amarillo")

print({1, 2, 3} | {3, 4, 5})  # unión
print({1, 2, 3} & {3, 4, 5})  # intersección
```

---

## 7. Funciones

```python
def calcular_media(notas):
    """Devuelve la media de una lista de notas."""
    return sum(notas) / len(notas)

media = calcular_media([7, 8, 9])
```

Una función sin `return` devuelve `None`.

```python
def presentar(nombre, saludo="Hola"):
    return f"{saludo}, {nombre}"

presentar("Ana")
presentar(nombre="Luis", saludo="Buenos días")
```

`*args` agrupa argumentos posicionales en una tupla y `**kwargs` agrupa argumentos con nombre en un diccionario.

```python
def sumar_todos(*numeros):
    return sum(numeros)

cuadrado = lambda numero: numero ** 2
```

Las variables creadas dentro de una función son locales. Es preferible pasar datos como parámetros y devolver resultados antes que usar variables globales.

---

## 8. Strings

Los strings son secuencias inmutables de caracteres.

```python
texto = "  Python es útil  "
texto = texto.strip()
texto.lower()
texto.upper()
texto.replace("útil", "potente")
texto.startswith("Python")
texto.endswith("útil")

nombre = "Ana"
nota = 8.456
print(f"{nombre} ha sacado un {nota:.2f}")
```

`split()` divide y `join()` une.

```python
palabras = "Python es sencillo".split()
resultado = "-".join(palabras)
```

---

## 9. Excepciones

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

`try` contiene el código que puede fallar, `except` controla errores concretos, `else` se ejecuta sin errores y `finally` siempre se ejecuta.

```python
def validar_edad(edad):
    if edad < 0:
        raise ValueError("La edad no puede ser negativa")
```

---

## 10. Ficheros

`with open()` cierra el archivo automáticamente.

```python
with open("datos.txt", encoding="utf-8") as archivo:
    contenido = archivo.read()

with open("salida.txt", "w", encoding="utf-8") as archivo:
    archivo.write("Primera línea\n")

with open("salida.txt", "a", encoding="utf-8") as archivo:
    archivo.write("Línea añadida\n")
```

`r` lee, `w` sobrescribe y `a` añade. Para JSON se utilizan `json.dump()` y `json.load()`.

---

## 11. Programación orientada a objetos

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

`__init__` inicializa el objeto, `self` representa la instancia, los atributos guardan datos y los métodos definen acciones.

```python
class AlumnoBecado(Alumno):
    def __init__(self, nombre, nota, beca):
        super().__init__(nombre, nota)
        self.beca = beca
```

`super()` reutiliza el comportamiento de la clase padre.

---

## 12. Errores frecuentes de examen

1. `input()` devuelve `str`: conviértelo con `int()` o `float()`.
2. `=` asigna y `==` compara.
3. Escribe correctamente las condiciones con `and`, `or` y paréntesis.
4. No olvides `:` después de `if`, `for`, `while`, `def`, `class` y `try`.
5. Mantén la indentación del bloque.
6. `append` añade un elemento; `extend` añade varios.
7. Usa `get()` si una clave puede no existir.
8. `sort()` modifica; `sorted()` devuelve otra lista.
9. No modifiques una lista mientras la recorres.
10. Actualiza la condición de un `while` para evitar bucles infinitos.
11. Usa `==` para valores e `is` normalmente solo con `None`.
12. Captura excepciones concretas.
13. Imprimir no equivale a devolver con `return`.
14. `w` sobrescribe un fichero; `a` añade.

---

## 13. Chuleta final

```python
nombre = input("Nombre: ")
edad = int(input("Edad: "))

if edad >= 18:
    mensaje = "adulto"
else:
    mensaje = "menor"

for numero in range(5):
    print(numero)

numeros = [1, 2, 3, 4]
pares = [n for n in numeros if n % 2 == 0]

persona = {"nombre": "Ana", "edad": 20}
print(persona.get("ciudad", "sin ciudad"))

def doble(numero):
    return numero * 2

try:
    numero = int("10")
except ValueError:
    print("Valor no válido")

with open("datos.txt", encoding="utf-8") as archivo:
    texto = archivo.read()
```

> Método: entiende los datos de entrada, elige la estructura, separa la lógica en funciones, prueba casos normales y límite, y revisa tipos, indentación y salidas.

---

## 14. Práctica 3 — soluciones

Todos los ejercicios incluyen una descripción y comentarios explicativos.

### Ejercicio 1 — Catálogo de productos

La lista alterna nombres y precios. El slicing separa ambos tipos de datos.

```python
catalogo = [
    "Auriculares BT", 59.90, "Smartwatch S2", 149.00,
    "Tablet 10", 219.00, "Cargador USB-C", 18.50,
    "Cafetera Expres", 189.00, "Robot Aspirador", 279.00,
]

print("Total de elementos:", len(catalogo))  # Cantidad total de elementos.
print("Productos:", len(catalogo) // 2)       # Cada producto ocupa dos posiciones.
nombres = catalogo[0::2]                       # Posiciones 0, 2, 4...
precios = catalogo[1::2]                      # Posiciones 1, 3, 5...
idx_robot = catalogo.index("Robot Aspirador") # Busca el producto.
print("Precio robot:", catalogo[idx_robot + 1])
ultimos_tres_nombres = nombres[-3:]            # Obtiene los tres últimos nombres.
print("Últimos tres:", ultimos_tres_nombres)
print("Precio medio:", round(sum(precios) / len(precios), 2))
```

### Ejercicio 2 — Inventario y listas anidadas

Cada fila contiene almacén, producto, unidades y precio. Se calcula el valor económico del stock.

```python
inventario = [
    ["ALM-NORTE", "Robot Aspirador", 34, 201.50],
    ["ALM-NORTE", "Monitor 27", 58, 142.00],
    ["ALM-SUR", "Silla Ergonomica", 12, 178.90],
    ["ALM-SUR", "Robot Aspirador", 7, 201.50],
    ["ALM-ESTE", "Cafetera Expres", 41, 131.20],
    ["ALM-ESTE", "Monitor 27", 25, 142.00],
]

print(inventario[3][1])                         # Producto de la cuarta fila.
valor_stock = [
    [fila[1], round(fila[2] * fila[3], 2)]       # Producto y unidades por precio.
    for fila in inventario
]
print(valor_stock)
print("Total:", round(sum(valor for _, valor in valor_stock), 2))
alm_sur = inventario[2:4]                       # Filas contiguas de ALM-SUR.
print("ALM-SUR:", round(sum(fila[2] * fila[3] for fila in alm_sur), 2))
```

Si las filas no fueran contiguas, sería más seguro usar `fila for fila in inventario if fila[0] == "ALM-SUR"`.

### Ejercicio 3 — Copias y referencias

Se demuestra la diferencia entre compartir una lista y crear una copia independiente.

```python
precios_originales = [59.90, 149.00, 219.00, 18.50, 189.00, 279.00]
precios_rebajados = precios_originales       # Ambas variables apuntan al mismo objeto.
precios_rebajados[0] = 49.90
print(precios_originales, precios_rebajados) # Las dos listas cambian.

precios_originales[0] = 59.90
copia_list = list(precios_originales)        # Copia mediante list().
copia_slice = precios_originales[:]          # Copia mediante slicing.
copia_list[0] = 9.99

for i in range(len(copia_list)):              # Aplica un 10 % de descuento.
    copia_list[i] = round(copia_list[i] * 0.90, 2)

copia_list.extend([12.50, 45.00])             # Añade dos elementos.
copia_list.insert(2, 99.00)                   # Inserta en el índice 2.
del copia_list[4]                              # Borra el índice 4.
descartado = copia_list.pop()                  # Extrae el último elemento.
print(id(precios_originales), id(copia_list), id(copia_slice))
```

### Ejercicio 4 — Cola de pedidos

Se usan operaciones de listas para invertir, ordenar y atender pedidos.

```python
cola = ["PED-10021", "PED-10007", "PED-10044", "PED-10012", "PED-10033", "PED-10008"]
cola_invertida = cola[::-1]                    # Copia en orden inverso.
cola_ordenada = sorted(cola)                   # Copia ordenada.
cola.sort(reverse=True)                        # Ordena la lista original.
atendidos = [cola.pop(0), cola.pop(0)]         # Atiende los dos primeros.
cola.insert(0, "PED-99999")                   # Añade un pedido prioritario.
total_historico = len(atendidos) + len(cola)
avance_pct = round(len(atendidos) / total_historico * 100, 1)
print(len(atendidos), cola, f"{avance_pct}%")
```

### Ejercicio 5 — Funciones e importes

Las funciones reúnen cálculos reutilizables y devuelven resultados.

```python
def calcular_importe(unidades, precio_unitario, descuento_pct=0):
    """Devuelve el importe neto después del descuento."""
    bruto = unidades * precio_unitario
    return round(bruto * (1 - descuento_pct / 100), 2)


def resumen_pedido(unidades, precio_unitario, descuento_pct=0):
    """Devuelve una tupla con bruto, ahorro y neto."""
    bruto = unidades * precio_unitario
    ahorro = bruto * descuento_pct / 100
    return round(bruto, 2), round(ahorro, 2), round(bruto - ahorro, 2)

bruto, ahorro, neto = resumen_pedido(3, 59.90, 10) # Desempaqueta tres resultados.
help(calcular_importe)                             # Muestra la documentación.
pedidos = [(3, 59.90, 10), (1, 279, 0), (5, 18.50, 20), (2, 149, 5), (4, 89.90, 15)]
importes = [calcular_importe(*pedido) for pedido in pedidos] # * separa la tupla.
print(max(importes), min(importes), sorted(importes), len(importes))
```

### Ejercicio 6 — Normalización de SKU

Se limpian y uniforman códigos antes de buscarlos o compararlos.

```python
skus_crudos = ["  elc-0012-es ", "HOG-0045-ES", "dep-0003-pt  ", "ofi-0021-es", "  ELC-0012-ES", "hog-0099-fr "]
skus_limpios = [sku.strip().upper() for sku in skus_crudos] # Limpia y convierte.
print(skus_limpios.count("ELC-0012-ES"))                    # Cuenta coincidencias.
print(skus_limpios.index("OFI-0021-ES"))                    # Busca una posición.
partes_sku = [sku.split("-") for sku in skus_limpios]      # Divide cada código.
skus_es = [sku.replace("-", ".") for sku in skus_limpios if sku.endswith("-ES")]
print(" | ".join(skus_limpios))                             # Une los códigos.
```

### Ejercicio 7 — Importaciones y operaciones matemáticas

Se importan módulos y se utilizan funciones matemáticas.

```python
import math
from math import ceil

print(math.sqrt(2809))       # Calcula la raíz cuadrada.
print(ceil(1375 / 24))       # Redondea la división hacia arriba.
```

### Ejercicio 8 — Números pseudoaleatorios

Una semilla permite obtener siempre la misma secuencia y reproducir el ejercicio.

```python
import random

random.seed(42)                         # Fija el estado inicial del generador.
valores = [random.randint(1, 5) for _ in range(10)]
print(valores)                          # Genera diez enteros entre 1 y 5.
```

### Ejercicio 9 — Arrays, máscaras y operaciones vectorizadas

NumPy aplica operaciones a todos los elementos de un array sin escribir un bucle manual.

```python
import numpy as np

unidades = np.array([3, 1, 5, 2, 4, 6, 2, 8, 3, 1])
precios = np.array([59.90, 279, 18.50, 149, 89.90, 27.50, 219, 14.90, 64, 239])
importes = unidades * precios                   # Multiplica posición a posición.
caros = precios > 100                            # Máscara booleana.
print(importes.sum(), importes * 1.04)          # Total y aumento del 4 %.
print(precios[caros], importes[caros], np.where(caros))
seleccion = (precios > 100) & (unidades >= 2)   # Combina dos condiciones.
```

### Ejercicio 10 — Matrices y ejes

Se consulta la forma de una matriz y se suman sus filas y columnas.

```python
ventas_2d = np.array([
    [15200.50, 11800.25, 12950, 9870.40, 13400.10],
    [16840.75, 12310.60, 13480.90, 11020.35, 14100.80],
    [12535.72, 10663.06, 11221.59, 12673.08, 12334.38],
])

print(ventas_2d.shape, ventas_2d.ndim, ventas_2d.size) # Dimensiones y tamaño.
print(ventas_2d.sum(axis=1), ventas_2d.sum(axis=0))      # Filas y columnas.
ventas_2d = np.vstack((ventas_2d, ventas_2d[2] * 1.06))  # Añade una fila con +6 %.
```

### Ejercicio 11 — Estadística y generación reproducible

Se generan datos normales y se calculan medidas descriptivas, percentiles, correlación y valores extremos.

```python
rng = np.random.default_rng(2024)              # Generador reproducible.
temperatura = rng.normal(68, 6.5, 500)         # 500 temperaturas.
vibracion = np.abs(rng.normal(2.4, 0.8, 500))  # Vibraciones no negativas.
media = temperatura.mean()
mediana = np.median(temperatura)
desviacion = temperatura.std()
print(media, mediana, desviacion, temperatura.min(), temperatura.max())
print(np.percentile(vibracion, [25, 50, 75, 95]))
print(np.corrcoef(temperatura, vibracion))
umbral = media + 2 * desviacion
print((temperatura > umbral).sum())            # Cuenta valores por encima del umbral.
```

### Ejercicio 12 — Diccionarios de tarifas

Se crean, modifican, eliminan y recorren pares clave-valor.

```python
tarifas_envio = {"Norte": 4.95, "Sur": 5.50, "Este": 5.20, "Oeste": 6.10, "Centro": 3.90}
print(tarifas_envio["Este"])                  # Consulta una clave.
tarifas_envio["Insular"] = 9.80               # Añade una clave.
tarifas_envio["Oeste"] = 5.95                 # Modifica un valor.
del tarifas_envio["Sur"]                       # Elimina una entrada.
for region, tarifa in tarifas_envio.items():
    print(f"{region} -> {tarifa:.2f} €")
```

### Ejercicio 13 — DataFrames de almacenes

pandas permite representar datos tabulares mediante un `DataFrame`.

```python
import pandas as pd

almacenes = pd.DataFrame({
    "codigo": ["ALM-NORTE", "ALM-SUR", "ALM-ESTE", "ALM-OESTE", "ALM-CENTRO"],
    "ciudad": ["Bilbao", "Sevilla", "Valencia", "Vigo", "Madrid"],
    "m2": [4200, 3100, 3800, 2600, 6500],
    "operarios": [48, 31, 39, 22, 74],
}).set_index("codigo")                    # Usa codigo como índice.
```

### Ejercicio 14 — Lectura y transformación de ventas

Se carga un CSV y se crean columnas calculadas y agrupaciones.

```python
ventas = pd.read_csv("../data/ventas_retail.csv")
ventas["importe_bruto"] = ventas["unidades"] * ventas["precio_unitario"]
ventas["importe_neto"] = (ventas["importe_bruto"] * (1 - ventas["descuento_pct"] / 100)).round(2)
ventas["ticket_medio"] = (ventas["importe_neto"] / ventas["unidades"]).round(2)
agrup_region = ventas.groupby("region")["importe_neto"].sum().sort_values(ascending=False)
top_5 = ventas.groupby("producto")["importe_neto"].sum().nlargest(5)
ventas["satisfaccion"] = ventas["satisfaccion"].fillna(ventas["satisfaccion"].median())
ventas["canal"] = ventas["canal"].fillna("Desconocido")
```

### Ejercicio 15 — Limpieza de datos y valores ausentes

`fillna()` evita que los valores vacíos interfieran en los cálculos. La mediana resulta útil para completar una columna numérica y una etiqueta para una columna de texto.

```python
satisfaccion_mediana = ventas["satisfaccion"].median()
ventas["satisfaccion"] = ventas["satisfaccion"].fillna(satisfaccion_mediana)
ventas["canal"] = ventas["canal"].fillna("Desconocido")
print(ventas[["satisfaccion", "canal"]].head())
```

### Ejercicio 16 — Función de nivel de riesgo

La función devuelve una categoría según el intervalo de temperatura.

```python
def nivel_riesgo(temperatura):
    if temperatura > 85:
        return "CRÍTICO"
    if temperatura > 76:
        return "ALTO"
    if temperatura > 70:
        return "MEDIO"
    return "BAJO"
```

### Ejercicio 17 — Filtros booleanos

Los filtros seleccionan filas que cumplen una o varias condiciones.

```python
filtro = (ventas["canal"] == "Online") & (ventas["importe_neto"] > 500)
ventas_online = ventas[filtro]
ventas_region = ventas[ventas["region"].isin(["Norte", "Centro"])]
ventas_descuento = ventas[ventas["descuento_pct"].between(10, 20)]
```

### Ejercicio 18 — Filtros combinados y `query`

Se combinan condiciones con `&`, se niegan con `~` y también se puede usar `query()`.

```python
ventas_electronica = ventas[
    (ventas["categoria"] == "Electronica")
    & (ventas["unidades"] > 2)
    & (ventas["descuento_pct"] != 0)
]
ventas_no_oficina = ventas[~(ventas["categoria"] == "Oficina")]
ventas_online_query = ventas.query("canal == 'Online' and importe_neto > 500")
```

### Ejercicio 19 — Recorrido de filas

`head(5)` limita la muestra e `iterrows()` permite recorrerla fila a fila.

```python
for _, fila in ventas.head(5).iterrows():
    print(f"{fila['region']} | {fila['producto']} | {fila['importe_neto']} €")
```

El guion bajo representa el índice que no necesitamos.

### Ejercicio 20 — Nuevas columnas con texto y funciones

Se crean códigos de región y segmentos de ticket a partir de columnas existentes.

```python
ventas["codigo_region"] = ventas["region"].str[:3].str.upper()
ventas["segmento_ticket"] = ventas["importe_neto"].apply(
    lambda importe: "Bajo" if importe < 100 else "Medio" if importe < 400 else "Alto"
)
```

La operación `groupby("region")["importe_neto"].sum()` expresa directamente una agregación y suele ser más eficiente y legible que un bucle manual.

---

## ✅ Resumen en una frase

Usa **condicionales** para decidir, **bucles** para repetir, **listas/diccionarios** para organizar datos, **funciones** para reutilizar lógica y **excepciones** para controlar errores.
