# 🐼 Guía rápida de Pandas — de cero a nivel examen

> **Chuleta / cheat sheet en español** para limpiar, explorar, transformar y analizar datos con Pandas.
>
> ```bash
> pip install pandas openpyxl
> ```

## 0. Importar Pandas y crear datos

```python
import pandas as pd
import numpy as np  # Opcional: útil para representar valores nulos y hacer operaciones numéricas

# Crear un DataFrame desde un diccionario
datos = {
    "Nombre": ["Ana", "Luis", "Marta", "Alex"],
    "Edad": [25, 30, 22, 28],
    "Ciudad": ["Madrid", "Barcelona", "Madrid", "Sevilla"],
    "Puntuacion": [8.5, 7.0, 9.2, 6.5],
}

df = pd.DataFrame(datos)

# Crear una Serie
edades = pd.Series([25, 30, 22], name="Edad")

# Ver las primeras filas
print(df.head())
```

### Convenciones recomendadas

- Usa `df` para un DataFrame principal y nombres descriptivos para los resultados.
- No uses `inplace=True` por defecto: guardar el resultado en una variable hace el código más claro y fácil de depurar.
- Para comprobar si una operación modifica el DataFrame original, revisa la documentación de la función y asigna explícitamente el resultado.

---

## 1. Leer y guardar archivos

### CSV

```python
# Leer un CSV
df = pd.read_csv("mis_datos.csv")

# Leer indicando separador, codificación y valores que representan nulos
df = pd.read_csv(
    "mis_datos.csv",
    sep=";",
    encoding="utf-8",
    na_values=["", "NA", "N/A", "null", "-"],
)

# Guardar sin el índice numérico como columna
df.to_csv("datos_limpios.csv", index=False)
```

### Excel y otros formatos

```python
# Excel (requiere openpyxl)
df = pd.read_excel("datos.xlsx", sheet_name="Hoja1")
df.to_excel("resultado.xlsx", index=False)

# JSON
df = pd.read_json("datos.json")
df.to_json("resultado.json", orient="records", indent=2)

# Parquet (eficiente para grandes volúmenes)
df = pd.read_parquet("datos.parquet")
df.to_parquet("resultado.parquet", index=False)
```

### Importar fechas correctamente

```python
df = pd.read_csv("ventas.csv", parse_dates=["fecha"])
# Si ya se cargó como texto:
df["fecha"] = pd.to_datetime(df["fecha"], errors="coerce")
```

---

## 2. Exploración básica

Antes de transformar los datos, comprueba su estructura, tipos y calidad.

```python
# Muestras
df.head()       # Primeras 5 filas
df.head(10)     # Primeras 10
df.tail(3)      # Últimas 3 filas
df.sample(5, random_state=42)  # Muestra aleatoria reproducible

# Estructura
df.shape        # Tupla: (filas, columnas)
df.columns      # Nombres de las columnas
df.index        # Índice
df.dtypes       # Tipo de cada columna
df.info()       # Resumen, memoria y valores no nulos

# Estadística descriptiva
df.describe()                    # Columnas numéricas
df.describe(include="all")      # Numéricas y categóricas
df["Ciudad"].value_counts()     # Frecuencia de cada categoría

# Valores únicos
df["Ciudad"].unique()
df["Ciudad"].nunique()
```

### Comprobaciones rápidas

```python
# ¿Hay duplicados?
df.duplicated().sum()

# ¿Hay nulos por columna?
df.isna().sum()
(df.isna().mean() * 100).round(2)  # Porcentaje de nulos

# Tipos concretos
pd.api.types.is_numeric_dtype(df["Edad"])
pd.api.types.is_datetime64_any_dtype(df["fecha"])
```

---

## 3. Seleccionar columnas y filas

### Columnas

```python
# Una columna: devuelve una Serie
edades = df["Edad"]

# Varias columnas: devuelve un DataFrame
sub_tabla = df[["Nombre", "Ciudad", "Puntuacion"]]

# Acceso por atributo: solo si el nombre es válido y no coincide con un método
edades = df.Edad  # Preferible df["Edad"] por ser más explícito
```

### `.loc` frente a `.iloc`

| Método | Selecciona por | Ejemplo |
|---|---|---|
| `.loc` | Etiquetas/nombres | `df.loc[filas, columnas]` |
| `.iloc` | Posiciones enteras | `df.iloc[filas, columnas]` |

```python
# Primera fila por posición
primera_fila = df.iloc[0]

# Filas 0 a 2 y columnas 0 a 1 (el final no se incluye)
bloque = df.iloc[0:3, 0:2]

# Filas con índice 0 y 2, y columnas concretas
seleccion = df.loc[[0, 2], ["Nombre", "Edad"]]

# Todas las filas de una columna
ciudades = df.loc[:, "Ciudad"]
```

> **Nota:** `.loc["a":"c"]` incluye el extremo final cuando el índice usa etiquetas; `.iloc[0:3]` no incluye la posición 3.

---

## 4. Filtrar datos

```python
# Una condición
mayores_25 = df[df["Edad"] > 25]

# Varias condiciones: usa &, | y ~; cada condición debe ir entre paréntesis
madrid_y_aprobados = df[
    (df["Ciudad"] == "Madrid") & (df["Puntuacion"] >= 5)
]

# OR: Madrid o Sevilla
madrid_o_sevilla = df[df["Ciudad"].isin(["Madrid", "Sevilla"])]

# NOT: excluir Madrid
no_madrid = df[~df["Ciudad"].eq("Madrid")]

# Texto que contiene un patrón; na=False evita problemas con nulos
nombres_con_a = df[df["Nombre"].str.contains("a", case=False, na=False)]

# Filtrar un rango inclusivo
edades_entre_25_y_30 = df[df["Edad"].between(25, 30)]

# Query: sintaxis cómoda para expresiones sencillas
resultado = df.query("Edad >= 25 and Puntuacion >= 7")
```

> En Pandas no se usan `and` / `or` directamente con Series; utiliza `&` / `|` y paréntesis.

---

## 5. Ordenar, renombrar y cambiar el índice

```python
# Orden ascendente
df_ordenado = df.sort_values("Puntuacion")

# Varias columnas y diferentes sentidos
df_ordenado = df.sort_values(
    ["Ciudad", "Puntuacion"], ascending=[True, False]
)

# Renombrar columnas
df = df.rename(columns={"Puntuacion": "puntuacion"})

# Convertir todos los nombres a minúsculas y sustituir espacios
df.columns = df.columns.str.strip().str.lower().str.replace(" ", "_", regex=False)

# Índice basado en una columna (devuelve una copia)
df_indexado = df.set_index("Nombre")

# Volver a usar un índice numérico
df = df_indexado.reset_index()
```

---

## 6. Crear y modificar columnas

```python
# Operación vectorizada (más recomendable que un bucle)
df["Puntuacion_Doble"] = df["Puntuacion"] * 2

# Condición binaria
df["Aprobado"] = df["Puntuacion"] >= 5

# Varias condiciones con np.select
condiciones = [
    df["Puntuacion"] >= 9,
    df["Puntuacion"] >= 7,
]
valores = ["Sobresaliente", "Notable"]
df["Categoria"] = np.select(condiciones, valores, default="Aprobado")

# Renombrar valores de una columna
df["Ciudad"] = df["Ciudad"].replace({"Madrid": "MAD", "Barcelona": "BCN"})

# Eliminar columnas
# axis=1 equivale a columns=[...]
df = df.drop(columns=["Puntuacion_Doble"])

# Eliminar filas por índice
df = df.drop(index=[0, 1])
```

### `.assign()` para encadenar transformaciones

```python
resultado = (
    df.assign(
        puntuacion_doble=lambda x: x["Puntuacion"] * 2,
        aprobado=lambda x: x["Puntuacion"] >= 5,
    )
    .query("aprobado")
    .sort_values("puntuacion_doble", ascending=False)
)
```

---

## 7. Valores nulos y limpieza

```python
# Detectar nulos (isnull() es un alias de isna())
df.isna().sum()

# Eliminar filas o columnas con nulos
df_sin_nulos = df.dropna()                  # Fila con al menos un nulo
df_sin_nulos = df.dropna(subset=["Edad"])  # Solo exige Edad
df_columnas = df.dropna(axis=1)             # Elimina columnas con algún nulo

# Rellenar con un valor fijo
df["Ciudad"] = df["Ciudad"].fillna("Desconocida")

# Rellenar numéricos con media o mediana
mediana = df["Edad"].median()
df["Edad"] = df["Edad"].fillna(mediana)

# Rellenar usando el valor anterior o posterior
df["Precio"] = df["Precio"].ffill()
df["Precio"] = df["Precio"].bfill()

# Convertir valores problemáticos a NaN
df["Edad"] = pd.to_numeric(df["Edad"], errors="coerce")
```

> Elige la estrategia de nulos según el significado de los datos: `fillna(0)` no siempre es correcto, porque cero puede tener un significado real.

---

## 8. Tipos de datos y texto

```python
# Conversión de tipos
df["Edad"] = df["Edad"].astype("Int64")      # Enteros que permiten NA
df["Ciudad"] = df["Ciudad"].astype("string")
df["fecha"] = pd.to_datetime(df["fecha"], errors="coerce")

# Operaciones de texto (vectorizadas)
df["Nombre"] = df["Nombre"].str.strip()
df["nombre_minusculas"] = df["Nombre"].str.lower()
df["inicial"] = df["Nombre"].str[0]
df["contiene_m"] = df["Nombre"].str.contains("m", case=False, na=False)

# Fechas
df["año"] = df["fecha"].dt.year
df["mes"] = df["fecha"].dt.month
df["dia_semana"] = df["fecha"].dt.day_name()
df["mes_inicio"] = df["fecha"].dt.to_period("M")
```

---

## 9. Agregación y `groupby`

```python
# Media de puntuación por ciudad
media_por_ciudad = df.groupby("Ciudad")["Puntuacion"].mean()

# Varias métricas por grupo
resumen = (
    df.groupby("Ciudad", as_index=False)
      .agg(
          personas=("Nombre", "count"),
          edad_media=("Edad", "mean"),
          puntuacion_media=("Puntuacion", "mean"),
          puntuacion_maxima=("Puntuacion", "max"),
      )
      .sort_values("puntuacion_media", ascending=False)
)

# Agrupar por más de una columna
resumen = df.groupby(["Ciudad", "Aprobado"], as_index=False).size()

# Transform: devuelve una serie con el mismo tamaño que df
media_ciudad = df.groupby("Ciudad")["Puntuacion"].transform("mean")
df["diferencia_media_ciudad"] = df["Puntuacion"] - media_ciudad
```

### Diferencia entre `agg`, `transform` y `filter`

- `agg`: reduce cada grupo a una o varias filas de resumen.
- `transform`: conserva el mismo número de filas que el DataFrame original.
- `filter`: conserva o elimina grupos completos según una condición.

---

## 10. Tablas dinámicas y frecuencias

```python
# Frecuencia absoluta
df["Ciudad"].value_counts(dropna=False)

# Frecuencia relativa
df["Ciudad"].value_counts(normalize=True) * 100

# Tabla dinámica: filas, columnas, valores y función de agregación
tabla = pd.pivot_table(
    df,
    index="Ciudad",
    columns="Aprobado",
    values="Puntuacion",
    aggfunc="mean",
    fill_value=0,
)
```

---

## 11. Combinar DataFrames: `concat`, `merge` y `join`

```python
clientes = pd.DataFrame({"cliente_id": [1, 2], "Nombre": ["Ana", "Luis"]})
compras = pd.DataFrame({"cliente_id": [1, 1, 3], "total": [100, 50, 80]})

# Apilar filas con las mismas columnas
historico = pd.concat([df_enero, df_febrero], ignore_index=True)

# Combinar por una clave (similar a un JOIN de SQL)
resultado = clientes.merge(
    compras,
    on="cliente_id",
    how="left",       # inner, left, right o outer
    validate="one_to_many",
)

# Detectar filas sin correspondencia con indicator=True
resultado = clientes.merge(compras, on="cliente_id", how="outer", indicator=True)

# Unir por índice
resultado = df1.join(df2, how="left", lsuffix="_izq", rsuffix="_der")
```

**Tipos de `merge`:**

- `inner`: solo claves presentes en ambos DataFrames.
- `left`: conserva todas las filas del DataFrame izquierdo.
- `right`: conserva todas las filas del derecho.
- `outer`: conserva todas las claves de ambos.

---

## 12. Duplicados

```python
# Ver duplicados completos
df[df.duplicated()]

# Duplicados según unas columnas
df[df.duplicated(subset=["Nombre", "Ciudad"], keep=False)]

# Eliminar duplicados, conservando el primero
df = df.drop_duplicates(subset=["Nombre", "Ciudad"], keep="first")

# Conservar el último
df = df.drop_duplicates(subset=["Nombre", "Ciudad"], keep="last")
```

---

## 13. Estadística y operaciones útiles

```python
# Operaciones numéricas
df["Puntuacion"].mean()
df["Puntuacion"].median()
df["Puntuacion"].sum()
df["Puntuacion"].min()
df["Puntuacion"].max()
df["Puntuacion"].std()

# Correlación entre columnas numéricas
df[["Edad", "Puntuacion"]].corr()

# Limitar valores extremos
df["Puntuacion_limitada"] = df["Puntuacion"].clip(lower=0, upper=10)
```

---

## 14. Gráficos rápidos

```python
import matplotlib.pyplot as plt

# Histograma
df["Edad"].plot(kind="hist", bins=10, title="Distribución de edades")
plt.show()

# Barras con la media por ciudad
df.groupby("Ciudad")["Puntuacion"].mean().plot(kind="bar")
plt.ylabel("Puntuación media")
plt.show()
```

Para gráficos más complejos, suele ser útil combinar Pandas con `matplotlib` o `seaborn`.

---

## 15. Encadenamiento de operaciones (`method chaining`)

```python
resultado = (
    pd.read_csv("ventas.csv", parse_dates=["fecha"])
      .drop_duplicates()
      .assign(
          total=lambda x: x["unidades"] * x["precio"],
          mes=lambda x: x["fecha"].dt.to_period("M"),
      )
      .query("total > 100")
      .groupby("mes", as_index=False)
      .agg(ventas=("total", "sum"), pedidos=("total", "size"))
      .sort_values("ventas", ascending=False)
)
```

---

## 16. Errores frecuentes en exámenes

1. **Usar `and` / `or` con Series:** usa `&` / `|` y paréntesis.
2. **Olvidar los dobles corchetes:** `df[["A", "B"]]` selecciona varias columnas; `df["A", "B"]` da error.
3. **Confundir `.loc` e `.iloc`:** `.loc` usa etiquetas; `.iloc`, posiciones.
4. **Asignar a una vista:** para evitar `SettingWithCopyWarning`, crea una copia explícita:
   ```python
   filtrado = df.loc[df["Edad"] > 25].copy()
   filtrado["grupo"] = "adulto"
   ```
5. **Modificar sin querer el índice al exportar:** usa `index=False` en `to_csv()` y `to_excel()`.
6. **Comparar con `NaN`:** `df["columna"] == np.nan` no funciona; usa `isna()` / `notna()`.
7. **Hacer bucles fila a fila:** primero intenta operaciones vectorizadas, `map`, `where`, `assign` o `np.select`.
8. **Perder filas en un `merge`:** revisa `how=`, la clave de unión y usa `validate=` para detectar relaciones inesperadas.

---

## 17. Resumen de funciones imprescindibles

| Necesidad | Función / patrón |
|---|---|
| Leer CSV | `pd.read_csv()` |
| Guardar CSV | `df.to_csv(..., index=False)` |
| Inspeccionar | `head()`, `info()`, `describe()`, `shape` |
| Seleccionar | `[]`, `.loc[]`, `.iloc[]` |
| Filtrar | `df[condicion]`, `.query()` |
| Ordenar | `sort_values()` |
| Nulos | `isna()`, `dropna()`, `fillna()` |
| Duplicados | `duplicated()`, `drop_duplicates()` |
| Agrupar | `groupby().agg()` |
| Tabla dinámica | `pivot_table()` |
| Combinar | `concat()`, `merge()`, `join()` |
| Fechas | `pd.to_datetime()`, `.dt` |
| Texto | `.str` |
| Crear columnas | asignación, `assign()`, `np.select()` |

> **Regla de oro:** explora primero (`head`, `info`, `describe`, nulos y duplicados), transforma después y valida el resultado al final.
