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

### Ejercicio 1 — Catálogo de productos

```python
catalogo = [
    "Auriculares BT", 59.90, "Smartwatch S2", 149.00,
    "Tablet 10", 219.00, "Cargador USB-C", 18.50,
    "Cafetera Expres", 189.00, "Robot Aspirador", 279.00,
]

print("Total de elementos:", len(catalogo))
print("Productos:", len(catalogo) // 2)
nombres = catalogo[0::2]
precios = catalogo[1::2]
idx_robot = catalogo.index("Robot Aspirador")
print("Precio robot:", catalogo[idx_robot + 1])
ultimos_tres_nombres = nombres[-3:]
print("Últimos tres:", ultimos_tres_nombres)
print("Precio medio:", round(sum(precios) / len(precios), 2))
```

### Ejercicio 2 — Inventario y listas anidadas

```python
inventario = [
    ["ALM-NORTE", "Robot Aspirador", 34, 201.50],
    ["ALM-NORTE", "Monitor 27", 58, 142.00],
    ["ALM-SUR", "Silla Ergonomica", 12, 178.90],
    ["ALM-SUR", "Robot Aspirador", 7, 201.50],
    ["ALM-ESTE", "Cafetera Expres", 41, 131.20],
    ["ALM-ESTE", "Monitor 27", 25, 142.00],
]

print(inventario[3][1])
valor_stock = [[fila[1], round(fila[2] * fila[3], 2)] for fila in inventario]
print(valor_stock)
print("Total:", round(sum(valor for _, valor in valor_stock), 2))
alm_sur = inventario[2:4]
print("ALM-SUR:", round(sum(fila[2] * fila[3] for fila in alm_sur), 2))
```

> Si las filas de los almacenes dejan de estar contiguas, un slicing basado en posiciones seleccionará datos incorrectos. Para filtrar de forma robusta hay que comprobar el contenido de la columna, por ejemplo mediante una condición.

### Ejercicio 3 — Copias y referencias

```python
precios_originales = [59.90, 149.00, 219.00, 18.50, 189.00, 279.00]
precios_rebajados = precios_originales
precios_rebajados[0] = 49.90
print(precios_originales, precios_rebajados)  # Es la misma lista.

precios_originales[0] = 59.90
copia_list = list(precios_originales)
copia_slice = precios_originales[:]
copia_list[0] = 9.99
for i in range(len(copia_list)):
    copia_list[i] = round(copia_list[i] * 0.90, 2)
copia_list.extend([12.50, 45.00])
copia_list.insert(2, 99.00)
del copia_list[4]
descartado = copia_list.pop()
print(id(precios_originales), id(copia_list), id(copia_slice))
```

### Ejercicio 4 — Cola de pedidos

```python
cola = ["PED-10021", "PED-10007", "PED-10044", "PED-10012", "PED-10033", "PED-10008"]
cola_invertida = cola[::-1]
cola_ordenada = sorted(cola)
cola.sort(reverse=True)
atendidos = [cola.pop(0), cola.pop(0)]
cola.insert(0, "PED-99999")
total_historico = len(atendidos) + len(cola)
avance_pct = round(len(atendidos) / total_historico * 100, 1)
print(len(atendidos), cola, f"{avance_pct}%")
```

### Ejercicio 5 — Funciones e importes

```python
def calcular_importe(unidades, precio_unitario, descuento_pct=0):
    """Devuelve el importe neto redondeado a dos decimales."""
    bruto = unidades * precio_unitario
    return round(bruto * (1 - descuento_pct / 100), 2)


def resumen_pedido(unidades, precio_unitario, descuento_pct=0):
    """Devuelve importe bruto, ahorro e importe neto."""
    bruto = unidades * precio_unitario
    ahorro = bruto * descuento_pct / 100
    return round(bruto, 2), round(ahorro, 2), round(bruto - ahorro, 2)

bruto, ahorro, neto = resumen_pedido(
    unidades=3, precio_unitario=59.90, descuento_pct=10
)
help(calcular_importe)
pedidos = [(3, 59.90, 10), (1, 279, 0), (5, 18.50, 20), (2, 149, 5), (4, 89.90, 15)]
importes = [calcular_importe(*pedido) for pedido in pedidos]
print(max(importes), min(importes), sorted(importes), len(importes))
```

### Ejercicio 6 — Normalización de SKU

```python
skus_crudos = ["  elc-0012-es ", "HOG-0045-ES", "dep-0003-pt  ", "ofi-0021-es", "  ELC-0012-ES", "hog-0099-fr "]
skus_limpios = [sku.strip().upper() for sku in skus_crudos]
print(skus_limpios.count("ELC-0012-ES"))
print(skus_limpios.index("OFI-0021-ES"))
partes_sku = [sku.split("-") for sku in skus_limpios]
skus_es = [sku.replace("-", ".") for sku in skus_limpios if sku.endswith("-ES")]
print(" | ".join(skus_limpios))
```

### Ejercicios 7–11 — NumPy y aleatoriedad

```python
import math
from math import ceil
import random
import numpy as np

print(math.sqrt(2809), ceil(1375 / 24))
random.seed(42)
print([random.randint(1, 5) for _ in range(10)])

unidades = np.array([3, 1, 5, 2, 4, 6, 2, 8, 3, 1])
precios = np.array([59.90, 279, 18.50, 149, 89.90, 27.50, 219, 14.90, 64, 239])
importes = unidades * precios
caros = precios > 100
print(importes.sum(), importes * 1.04)
print(precios[caros], importes[caros], np.where(caros))
seleccion = (precios > 100) & (unidades >= 2)

ventas_2d = np.array([
    [15200.50, 11800.25, 12950, 9870.40, 13400.10],
    [16840.75, 12310.60, 13480.90, 11020.35, 14100.80],
    [12535.72, 10663.06, 11221.59, 12673.08, 12334.38],
])
print(ventas_2d.shape, ventas_2d.ndim, ventas_2d.size)
print(ventas_2d.sum(axis=1), ventas_2d.sum(axis=0))
ventas_2d = np.vstack((ventas_2d, ventas_2d[2] * 1.06))

rng = np.random.default_rng(2024)
temperatura = rng.normal(68, 6.5, 500)
vibracion = np.abs(rng.normal(2.4, 0.8, 500))
media, mediana, desviacion = temperatura.mean(), np.median(temperatura), temperatura.std()
print(media, mediana, desviacion, temperatura.min(), temperatura.max())
print(np.percentile(vibracion, [25, 50, 75, 95]))
print(np.corrcoef(temperatura, vibracion))
umbral = media + 2 * desviacion
print((temperatura > umbral).sum())
```

Fijar la semilla inicializa el generador pseudoaleatorio en el mismo estado. Así, cualquier persona puede reproducir los mismos datos y verificar las métricas obtenidas.

### Ejercicios 12–15 — Diccionarios y pandas

```python
import pandas as pd

tarifas_envio = {"Norte": 4.95, "Sur": 5.50, "Este": 5.20, "Oeste": 6.10, "Centro": 3.90}
print(tarifas_envio["Este"])
tarifas_envio["Insular"] = 9.80
tarifas_envio["Oeste"] = 5.95
del tarifas_envio["Sur"]
for region, tarifa in tarifas_envio.items():
    print(f"{region} -> {tarifa:.2f} €")

almacenes = pd.DataFrame({
    "codigo": ["ALM-NORTE", "ALM-SUR", "ALM-ESTE", "ALM-OESTE", "ALM-CENTRO"],
    "ciudad": ["Bilbao", "Sevilla", "Valencia", "Vigo", "Madrid"],
    "m2": [4200, 3100, 3800, 2600, 6500],
    "operarios": [48, 31, 39, 22, 74],
}).set_index("codigo")

ventas = pd.read_csv("../data/ventas_retail.csv")
ventas["importe_bruto"] = ventas["unidades"] * ventas["precio_unitario"]
ventas["importe_neto"] = (ventas["importe_bruto"] * (1 - ventas["descuento_pct"] / 100)).round(2)
ventas["ticket_medio"] = (ventas["importe_neto"] / ventas["unidades"]).round(2)
agrup_region = ventas.groupby("region")["importe_neto"].sum().sort_values(ascending=False)
top_5 = ventas.groupby("producto")["importe_neto"].sum().nlargest(5)
ventas["satisfaccion"] = ventas["satisfaccion"].fillna(ventas["satisfaccion"].median())
ventas["canal"] = ventas["canal"].fillna("Desconocido")
```

### Ejercicios 16–20 — Booleanos, funciones, filtros y bucles

```python
def nivel_riesgo(temperatura):
    if temperatura > 85:
        return "CRÍTICO"
    if temperatura > 76:
        return "ALTO"
    if temperatura > 70:
        return "MEDIO"
    return "BAJO"

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

for _, fila in ventas.head(5).iterrows():
    print(f"{fila['region']} | {fila['producto']} | {fila['importe_neto']} €")
ventas["codigo_region"] = ventas["region"].str[:3].str.upper()
ventas["segmento_ticket"] = ventas["importe_neto"].apply(
    lambda importe: "Bajo" if importe < 100 else "Medio" if importe < 400 else "Alto"
)
```

La técnica `groupby("region")["importe_neto"].sum()` es preferible al bucle manual: expresa directamente la operación de agregación y suele ser más eficiente y legible para trabajar con DataFrames.
