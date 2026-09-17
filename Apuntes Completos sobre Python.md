# Apuntes Completos sobre Python: Fundamentos, Tuplas, Diccionarios y Herencia

## Introducción a Python

Python es un lenguaje de programación de alto nivel, interpretado y de propósito general. Es conocido por su legibilidad y simplicidad, lo que lo convierte en una excelente opción tanto para principiantes como para desarrolladores experimentados.

### Características clave

- **Sintaxis simple y clara**: facilita la lectura y escritura del código.
- **Tipado dinámico**: no es necesario declarar el tipo de variable, lo que permite una mayor flexibilidad.
- **Multiparadigma**: soporta programación orientada a objetos, programación funcional y programación imperativa.
- **Gran comunidad y bibliotecas**: existe una amplia cantidad de módulos y paquetes disponibles que extienden las funcionalidades del lenguaje.

## Fundamentos de Python

### Variables y tipos de datos

Las variables en Python se declaran sin necesidad de especificar su tipo. Python infiere el tipo de datos automáticamente.

#### Tipos de datos comunes

- `int`: números enteros.
- `float`: números decimales.
- `str`: cadenas de texto.
- `bool`: valores booleanos (`True` o `False`).

#### Ejemplo de declaración de variables

```python
nombre = "Juan"          # Tipo str
edad = 25                # Tipo int
altura = 1.75            # Tipo float
es_estudiante = True     # Tipo bool
```

### Estructuras de control

#### Condicionales

Permiten ejecutar diferentes bloques de código en función de condiciones específicas.

```python
if edad >= 18:
    print("Eres mayor de edad.")
else:
    print("Eres menor de edad.")
```

#### Bucles

Los bucles permiten ejecutar un bloque de código repetidamente.

**For**: se utiliza para iterar sobre una secuencia, como una lista, tupla, diccionario o conjunto.

```python
for i in range(5):
    print(i)  # Imprime los números del 0 al 4
```

**While**: ejecuta un bloque de código mientras la condición sea verdadera.

```python
contador = 0

while contador < 5:
    print(contador)
    contador += 1  # Incrementa el contador
```

### Funciones

Las funciones permiten encapsular código y reutilizarlo. Se definen con la palabra clave `def`.

#### Definición y llamada a funciones

```python
def saludar(nombre):
    return f"Hola, {nombre}!"


print(saludar("Ana"))  # Salida: Hola, Ana!
```

#### Parámetros y argumentos

```python
def sumar(a, b):
    return a + b


resultado = sumar(5, 3)  # resultado es 8
```

## Estructuras de datos

### Tuplas

Las tuplas son colecciones ordenadas e inmutables. Esto significa que, una vez creada una tupla, no se pueden modificar sus elementos.

#### Creación de tuplas

```python
tupla = (1, 2, 3, "cuatro", 5.0)
print(tupla)  # Salida: (1, 2, 3, 'cuatro', 5.0)
```

#### Acceso a elementos

```python
print(tupla[0])  # Salida: 1
print(tupla[3])  # Salida: cuatro
```

#### Slicing

```python
subtupla = tupla[1:4]  # Obtiene elementos desde el índice 1 hasta el 3
print(subtupla)        # Salida: (2, 3, 'cuatro')
```

#### Métodos de tuplas

Las tuplas tienen algunos métodos incorporados, aunque son limitados debido a su inmutabilidad.

- `count()`: devuelve el número de veces que un elemento aparece en la tupla.
- `index()`: devuelve el índice de la primera aparición de un elemento.

```python
tupla_repetida = (1, 2, 2, 3, 4)

print(tupla_repetida.count(2))  # Salida: 2
print(tupla_repetida.index(3))  # Salida: 3
```

#### Conversión a tupla

```python
lista = [1, 2, 3]
tupla_convertida = tuple(lista)

print(tupla_convertida)  # Salida: (1, 2, 3)
```

### Diccionarios

Los diccionarios son colecciones de pares clave-valor. Son mutables, lo que significa que puedes agregar, modificar o eliminar elementos después de su creación.

#### Creación de diccionarios

```python
diccionario = {
    "nombre": "Carlos",
    "edad": 30,
    "ciudad": "Madrid",
}

print(diccionario)  # Salida: {'nombre': 'Carlos', 'edad': 30, 'ciudad': 'Madrid'}
```

#### Acceso a elementos

```python
print(diccionario["nombre"])  # Salida: Carlos
print(diccionario["edad"])    # Salida: 30
```

#### Modificación de elementos

```python
diccionario["edad"] = 31  # Modifica la edad

print(diccionario)  # Salida: {'nombre': 'Carlos', 'edad': 31, 'ciudad': 'Madrid'}
```

#### Agregar y eliminar elementos

```python
# Agregar un nuevo elemento
diccionario["profesión"] = "Ingeniero"
print(diccionario)
# Salida: {'nombre': 'Carlos', 'edad': 31, 'ciudad': 'Madrid', 'profesión': 'Ingeniero'}

# Eliminar un elemento
del diccionario["ciudad"]
print(diccionario)
# Salida: {'nombre': 'Carlos', 'edad': 31, 'profesión': 'Ingeniero'}
```

#### Métodos comunes de diccionarios

- `keys()`: devuelve una vista de las claves del diccionario.
- `values()`: devuelve una vista de los valores del diccionario.
- `items()`: devuelve una vista de los pares clave-valor.

```python
print(diccionario.keys())
# Salida: dict_keys(['nombre', 'edad', 'profesión'])

print(diccionario.values())
# Salida: dict_values(['Carlos', 31, 'Ingeniero'])

print(diccionario.items())
# Salida: dict_items([('nombre', 'Carlos'), ('edad', 31), ('profesión', 'Ingeniero')])
```

#### Conversión a diccionario

```python
lista_tuplas = [("nombre", "Ana"), ("edad", 25)]
diccionario_convertido = dict(lista_tuplas)

print(diccionario_convertido)  # Salida: {'nombre': 'Ana', 'edad': 25}
```

## Programación orientada a objetos (POO)

### Clases y objetos

- **Clase**: plantilla para crear objetos.
- **Objeto**: instancia de una clase.

```python
class Persona:
    def __init__(self, nombre, edad):
        self.nombre = nombre  # Atributo de instancia
        self.edad = edad

    def saludar(self):
        return f"Hola, soy {self.nombre} y tengo {self.edad} años."


persona1 = Persona("María", 28)
print(persona1.saludar())  # Salida: Hola, soy María y tengo 28 años.
```

### Encapsulamiento

El encapsulamiento se refiere a ocultar los atributos y métodos de una clase para proteger el estado interno del objeto. En Python, se puede usar un doble guion bajo para indicar que un atributo debe tratarse como privado.

```python
class CuentaBancaria:
    def __init__(self):
        self.__saldo = 0  # Atributo privado

    def depositar(self, cantidad):
        self.__saldo += cantidad

    def obtener_saldo(self):
        return self.__saldo


cuenta = CuentaBancaria()
cuenta.depositar(100)
print(cuenta.obtener_saldo())  # Salida: 100
# print(cuenta.__saldo)  # Esto generaría un error
```

### Herencia

La herencia permite que una clase, llamada subclase, herede atributos y métodos de otra clase, llamada superclase. Esto facilita la reutilización del código.

```python
class Estudiante(Persona):
    def __init__(self, nombre, edad, carrera):
        super().__init__(nombre, edad)  # Llama al constructor de la superclase
        self.carrera = carrera

    def presentar(self):
        return f"Hola, soy {self.nombre}, estudio {self.carrera}."


estudiante1 = Estudiante("Luis", 22, "Ingeniería")
print(estudiante1.presentar())
# Salida: Hola, soy Luis, estudio Ingeniería.
```

### Polimorfismo

El polimorfismo permite que diferentes clases sean tratadas mediante una interfaz común. Esto se consigue implementando métodos con el mismo nombre en distintas clases.

```python
class Perro:
    def hacer_sonido(self):
        return "Guau!"


class Gato:
    def hacer_sonido(self):
        return "Miau!"


def hacer_sonido_animal(animal):
    print(animal.hacer_sonido())


hacer_sonido_animal(Perro())  # Salida: Guau!
hacer_sonido_animal(Gato())   # Salida: Miau!
```

## Conclusiones

Python es un lenguaje versátil y poderoso que ofrece una amplia gama de características, desde programación básica hasta programación orientada a objetos. La comprensión de estos conceptos proporciona una base sólida para continuar aprendiendo y desarrollando aplicaciones con Python.

### Recomendaciones

- **Práctica regular**: la mejor manera de dominar Python es mediante la práctica constante. Realiza ejercicios y proyectos que te desafíen.
- **Explora bibliotecas**: familiarízate con bibliotecas populares como NumPy, Pandas y Matplotlib para ampliar tus habilidades en análisis de datos y visualización.
- **Participa en la comunidad**: únete a foros y grupos de discusión para compartir conocimientos y resolver dudas.

¡Feliz programación!
