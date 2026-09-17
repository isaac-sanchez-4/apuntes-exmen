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

## 14. Práctica 3 — soluciones

Esta práctica combina listas, slicing, copias, estructuras de control, funciones y análisis con pandas/NumPy. Cada apartado incluye una breve descripción y comentarios en el código para explicar qué hace cada instrucción.

### Ejercicio 1 — Catálogo de productos

El catálogo guarda nombres y precios en posiciones alternas. Se separan ambos datos para poder consultar productos y calcular estadísticas.

```python
catalogo = [
    "Auriculares BT", 59.90, "Smartwatch S2", 149.00,
    "Tablet 10", 219.00, "Cargador USB-C", 18.50,
    "Cafetera Expres", 189.00, "Robot Aspirador", 279.00,
]

# Cuenta todos los elementos de la lista y después divide entre dos para obtener productos.
print("Total de elementos:", len(catalogo))
print("Productos:", len(catalogo) // 2)

# El slicing con paso 2 separa nombres y precios según sus posiciones.
nombres = catalogo[0::2]
precios = catalogo[1::2]

# Busca el producto y suma uno a su índice para localizar el precio siguiente.
idx_robot = catalogo.index("Robot Aspirador")
print("Precio robot:", catalogo[idx_robot + 1])

# Selecciona los tres últimos nombres y calcula la media de los precios.
ultimos_tres_nombres = nombres[-3:]
print("Últimos tres:", ultimos_tres_nombres)
print("Precio medio:", round(sum(precios) / len(precios), 2))
```

### Ejercicio 2 — Inventario y listas anidadas

Cada fila contiene almacén, producto, unidades y precio. Se calcula el valor de stock de cada producto y el valor total.

```python
inventario = [
    ["ALM-NORTE", "Robot Aspirador", 34, 201.50],
    ["ALM-NORTE", "Monitor 27", 58, 142.00],
    ["ALM-SUR", "Silla Ergonomica", 12, 178.90],
    ["ALM-SUR", "Robot Aspirador", 7, 201.50],
    ["ALM-ESTE", "Cafetera Expres", 41, 131.20],
    ["ALM-ESTE", "Monitor 27", 25, 142.00],
]

# Índice 3 selecciona la cuarta fila; índice 1 selecciona el nombre del producto.
print(inventario[3][1])

# Para cada fila, multiplica unidades por precio y guarda producto y valor calculado.
valor_stock = [[fila[1], round(fila[2] * fila[3], 2)] for fila in inventario]
print(valor_stock)

# El desempaquetado _, valor ignora el nombre y permite sumar solo los importes.
print("Total:", round(sum(valor for _, valor in valor_stock), 2))

# Como las dos filas de ALM-SUR son contiguas, el slicing obtiene ambas.
alm_sur = inventario[2:4]
print("ALM-SUR:", round(sum(fila[2] * fila[3] for fila in alm_sur), 2))
```

> Si las filas dejan de estar contiguas, el slicing puede seleccionar datos incorrectos. En ese caso hay que filtrar comprobando `fila[0] == "ALM-SUR"`.

### Ejercicio 3 — Copias y referencias

Asignar una lista a otra variable crea una referencia al mismo objeto. `list()` y `[:]`, en cambio, crean copias independientes de la lista.

```python
# Ambas variables apuntan inicialmente a la misma lista.
precios_originales = [59.90, 149.00, 219.00, 18.50, 189.00, 279.00]
precios_rebajados = precios_originales
precios_rebajados[0] = 49.90
print(precios_originales, precios_rebajados)  # Las dos muestran el cambio.

# Restauramos el precio y creamos dos copias independientes.
precios_originales[0] = 59.90
copia_list = list(precios_originales)
copia_slice = precios_originales[:]

# Cambiamos la copia y aplicamos un descuento del 10 % a todos sus elementos.
copia_list[0] = 9.99
for i in range(len(copia_list)):
    copia_list[i] = round(copia_list[i] * 0.90, 2)

# Añadimos, insertamos y eliminamos elementos para practicar métodos de listas.
copia_list.extend([12.50, 45.00])
copia_list.insert(2, 99.00)
del copia_list[4]
descartado = copia_list.pop()

# id() permite comprobar que las tres listas son objetos diferentes.
print(id(precios_originales), id(copia_list), id(copia_slice))
```

### Ejercicio 4 — Cola de pedidos

Se practica el uso de listas como cola: se invierte y ordena la información, se atienden pedidos y se calcula el porcentaje de avance.

```python
# Lista inicial de pedidos pendientes.
cola = ["PED-10021", "PED-10007", "PED-10044", "PED-10012", "PED-10033", "PED-10008"]

# [::-1] crea una copia invertida y sorted() crea una copia ordenada.
cola_invertida = cola[::-1]
cola_ordenada = sorted(cola)

# sort() modifica la lista original y reverse=True la ordena de mayor a menor.
cola.sort(reverse=True)

# pop(0) extrae y elimina el primer pedido; aquí se atienden dos.
atendidos = [cola.pop(0), cola.pop(0)]

# insert(0, ...) coloca un nuevo pedido al principio.
cola.insert(0, "PED-99999")

# Calcula cuántos pedidos se han atendido respecto al total histórico.
total_historico = len(atendidos) + len(cola)
avance_pct = round(len(atendidos) / total_historico * 100, 1)
print(len(atendidos), cola, f"{avance_pct}%")
```

### Ejercicio 5 — Funciones e importes

Las funciones encapsulan cálculos reutilizables. Una devuelve el importe neto y la otra devuelve bruto, ahorro y neto.

```python
def calcular_importe(unidades, precio_unitario, descuento_pct=0):
    """Calcula el importe neto después de aplicar un descuento."""
    bruto = unidades * precio_unitario
    return round(bruto * (1 - descuento_pct / 100), 2)


def resumen_pedido(unidades, precio_unitario, descuento_pct=0):
    """Devuelve una tupla con importe bruto, ahorro e importe neto."""
    bruto = unidades * precio_unitario
    ahorro = bruto * descuento_pct / 100
    return round(bruto, 2), round(ahorro, 2), round(bruto - ahorro, 2)

# Desempaqueta los tres valores que devuelve resumen_pedido().
bruto, ahorro, neto = resumen_pedido(
    unidades=3, precio_unitario=59.90, descuento_pct=10
)

# help() muestra la documentación de la función.
help(calcular_importe)

# Cada tupla contiene unidades, precio y descuento de un pedido.
pedidos = [(3, 59.90, 10), (1, 279, 0), (5, 18.50, 20), (2, 149, 5), (4, 89.90, 15)]

# *pedido desempaqueta cada tupla como argumentos de la función.
importes = [calcular_importe(*pedido) for pedido in pedidos]
print(max(importes), min(importes), sorted(importes), len(importes))
```

### Ejercicio 6 — Normalización de SKU

Se limpian códigos de producto para que todos tengan el mismo formato antes de buscarlos o analizarlos.

```python
# Los datos originales contienen espacios y combinaciones de mayúsculas diferentes.
skus_crudos = ["  elc-0012-es ", "HOG-0045-ES", "dep-0003-pt  ", "ofi-0021-es", "  ELC-0012-ES", "hog-0099-fr "]

# strip() elimina espacios exteriores y upper() convierte a mayúsculas.
skus_limpios = [sku.strip().upper() for sku in skus_crudos]
print(skus_limpios.count("ELC-0012-ES"))
print(skus_limpios.index("OFI-0021-ES"))

# split() divide cada SKU en sus partes usando el guion como separador.
partes_sku = [sku.split("-") for sku in skus_limpios]

# Filtra los SKU españoles y cambia los guiones por puntos para mostrarlos.
skus_es = [sku.replace("-", ".") for sku in skus_limpios if sku.endswith("-ES")]
print(" | ".join(skus_limpios))
```

### Ejercicios 7–11 — NumPy y aleatoriedad

Se practican cálculos matemáticos, números pseudoaleatorios, operaciones vectorizadas de NumPy y medidas estadísticas.

```python
import math
from math import ceil
import random
import numpy as np

# sqrt() calcula una raíz cuadrada y ceil() redondea hacia arriba.
print(math.sqrt(2809), ceil(1375 / 24))

# La semilla permite repetir la misma secuencia pseudoaleatoria.
random.seed(42)
print([random.randint(1, 5) for _ in range(10)])

# NumPy multiplica arrays elemento a elemento.
unidades = np.array([3, 1, 5, 2, 4, 6, 2, 8, 3, 1])
precios = np.array([59.90, 279, 18.50, 149, 89.90, 27.50, 219, 14.90, 64, 239])
importes = unidades * precios
caros = precios > 100

# sum() calcula el total; la máscara caros filtra precios e importes superiores a 100.
print(importes.sum(), importes * 1.04)
print(precios[caros], importes[caros], np.where(caros))
seleccion = (precios > 100) & (unidades >= 2)

# Una matriz 2D permite analizar ventas por filas y columnas.
ventas_2d = np.array([
    [15200.50, 11800.25, 12950, 9870.40, 13400.10],
    [16840.75, 12310.60, 13480.90, 11020.35, 14100.80],
    [12535.72, 10663.06, 11221.59, 12673.08, 12334.38],
])
print(ventas_2d.shape, ventas_2d.ndim, ventas_2d.size)
print(ventas_2d.sum(axis=1), ventas_2d.sum(axis=0))

# Añade una nueva fila aplicando un crecimiento del 6 % a la tercera fila.
ventas_2d = np.vstack((ventas_2d, ventas_2d[2] * 1.06))

# Genera 500 temperaturas y vibraciones siguiendo distribuciones normales.
rng = np.random.default_rng(2024)
temperatura = rng.normal(68, 6.5, 500)
vibracion = np.abs(rng.normal(2.4, 0.8, 500))

# Calcula medidas descriptivas de temperatura.
media, mediana, desviacion = temperatura.mean(), np.median(temperatura), temperatura.std()
print(media, mediana, desviacion, temperatura.min(), temperatura.max())
print(np.percentile(vibracion, [25, 50, 75, 95]))
print(np.corrcoef(temperatura, vibracion))

# Cuenta cuántas temperaturas superan la media en más de dos desviaciones típicas.
umbral = media + 2 * desviacion
print((temperatura > umbral).sum())
```

Fijar una semilla inicializa el generador pseudoaleatorio siempre en el mismo estado. Así se pueden reproducir los datos y comprobar los resultados.

### Ejercicios 12–15 — Diccionarios y pandas

Se usan diccionarios para tarifas y pandas para cargar, transformar, agrupar y limpiar datos tabulares.

```python
import pandas as pd

# Diccionario que relaciona cada región con su tarifa de envío.
tarifas_envio = {"Norte": 4.95, "Sur": 5.50, "Este": 5.20, "Oeste": 6.10, "Centro": 3.90}
print(tarifas_envio["Este"])

# Añade una región, modifica otra y elimina la región Sur.
tarifas_envio["Insular"] = 9.80
tarifas_envio["Oeste"] = 5.95
del tarifas_envio["Sur"]
for region, tarifa in tarifas_envio.items():
    print(f"{region} -> {tarifa:.2f} €")

# Crea un DataFrame y utiliza el código del almacén como índice.
almacenes = pd.DataFrame({
    "codigo": ["ALM-NORTE", "ALM-SUR", "ALM-ESTE", "ALM-OESTE", "ALM-CENTRO"],
    "ciudad": ["Bilbao", "Sevilla", "Valencia", "Vigo", "Madrid"],
    "m2": [4200, 3100, 3800, 2600, 6500],
    "operarios": [48, 31, 39, 22, 74],
}).set_index("codigo")

# Lee el CSV y calcula importe bruto, neto y ticket medio.
ventas = pd.read_csv("../data/ventas_retail.csv")
ventas["importe_bruto"] = ventas["unidades"] * ventas["precio_unitario"]
ventas["importe_neto"] = (ventas["importe_bruto"] * (1 - ventas["descuento_pct"] / 100)).round(2)
ventas["ticket_medio"] = (ventas["importe_neto"] / ventas["unidades"]).round(2)

# Agrupa por región y producto para obtener totales y los cinco productos principales.
agrup_region = ventas.groupby("region")["importe_neto"].sum().sort_values(ascending=False)
top_5 = ventas.groupby("producto")["importe_neto"].sum().nlargest(5)

# Sustituye valores ausentes por la mediana o por una etiqueta descriptiva.
ventas["satisfaccion"] = ventas["satisfaccion"].fillna(ventas["satisfaccion"].median())
ventas["canal"] = ventas["canal"].fillna("Desconocido")
```

### Ejercicios 16–20 — Booleanos, funciones, filtros y bucles

Se combinan condiciones para filtrar ventas, una función para clasificar riesgos y operaciones de texto para crear nuevas columnas.

```python
def nivel_riesgo(temperatura):
    # Devuelve una categoría según el intervalo en el que está la temperatura.
    if temperatura > 85:
        return "CRÍTICO"
    if temperatura > 76:
        return "ALTO"
    if temperatura > 70:
        return "MEDIO"
    return "BAJO"

# Cada filtro conserva solo las filas que cumplen sus condiciones.
filtro = (ventas["canal"] == "Online") & (ventas["importe_neto"] > 500)
ventas_online = ventas[filtro]
ventas_region = ventas[ventas["region"].isin(["Norte", "Centro"])]
ventas_descuento = ventas[ventas["descuento_pct"].between(10, 20)]
ventas_electronica = ventas[
    (ventas["categoria"] == "Electronica")
    & (ventas["unidades"] > 2)
    & (ventas["descuento_pct"] != 0)
]
ventas_no_oficina = ventas[~(ventas["categoria"] == "Oficina")]
ventas_online_query = ventas.query("canal == 'Online' and importe_neto > 500")

# Recorre las cinco primeras filas e imprime una selección de sus columnas.
for _, fila in ventas.head(5).iterrows():
    print(f"{fila['region']} | {fila['producto']} | {fila['importe_neto']} €")

# Crea un código de región y clasifica cada venta por importe.
ventas["codigo_region"] = ventas["region"].str[:3].str.upper()
ventas["segmento_ticket"] = ventas["importe_neto"].apply(
    lambda importe: "Bajo" if importe < 100 else "Medio" if importe < 400 else "Alto"
)
```

La operación `groupby("region")["importe_neto"].sum()` expresa directamente la agregación por región. Suele ser más eficiente y legible que recorrer manualmente todas las filas.

---

## ✅ Resumen en una frase

Usa **condicionales** para decidir, **bucles** para repetir, **listas/diccionarios** para organizar datos, **funciones** para reutilizar lógica y **excepciones** para controlar errores.
