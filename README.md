# Taller de Manejo de Figuras Geometricas

Sistema de consola para crear, consultar y manipular figuras geometricas: circulo,
triangulo, cuadrado y pentagono regular. Cada figura es una subclase de `Figura`, que
centraliza el comportamiento comun a todas ellas, y guarda su posicion en el plano
mediante un `Punto`.

## Requisitos

- Java 8 o superior. El proyecto esta configurado en IntelliJ con JDK 25 (Corretto).
- No se utilizan librerias externas.

## Como ejecutar

### Desde IntelliJ IDEA

1. Abrir la carpeta raiz del proyecto.
2. Esperar a que el IDE detecte `src` como carpeta de fuentes.
3. Abrir `src/Main.java`.
4. Presionar el boton de ejecucion junto al metodo `main`.

### Desde la linea de comandos

En Linux o macOS:

```bash
javac -d out $(find src -name "*.java")
java -cp out Main
```

En Windows con PowerShell:

```powershell
javac -d out (Get-ChildItem -Path src -Recurse -Filter *.java).FullName
java -cp out Main
```

Al iniciar, el programa muestra el detalle de las figuras de ejemplo y luego ofrece un
menu con tres opciones: crear una figura, ver todas las figuras y salir del sistema.
Las opciones `3` terminan la ejecucion.

Si se escribe una dimension incorrecta, como texto o un valor menor o igual a cero, el
programa informa el motivo y vuelve a solicitar el dato.

## Estructura del proyecto

```
src/
├── Main.java                       Punto de entrada
├── controllers/
│   └── FiguraController.java       Almacena y opera las figuras
├── domain/
│   ├── Figura.java                 Clase abstracta base
│   ├── Punto.java                  Posicion en el plano
│   ├── Circulo.java
│   ├── Cuadrado.java
│   ├── Triangulo.java
│   └── PentagonoRegular.java
└── exceptions/
    └── DimensionInvalidaException.java
```

`FiguraController` mantiene la coleccion de figuras y expone las operaciones de
agregar, listar, comparar, desplazar y escalar. Las clases de `domain` no dependen del
controlador.

## Diagrama de clases

El diagrama UML se entrega en la version impresa de la entrega, de acuerdo con la
Parte 1 del enunciado.

## Integrantes

| Integrante | Clases a cargo |
|---|---|
| Juan Pablo Olave Munoz (`donpedromz`) | `Figura`, `Punto`, `FiguraController`, `DimensionInvalidaException`, `Main` |
| Maria Paz Bustamante (`mariapazbustamante`) | `Circulo`, `Cuadrado`, `Triangulo`, `PentagonoRegular` |

La asignacion de clases por integrante se realizo en el diagrama, en azul el primer
integrante y en amarillo el segundo.

## Ramas

El proyecto sigue GitFlow:

| Rama | Contenido |
|---|---|
| `main` | version estable |
| `develop` | integracion del trabajo en curso |
| `feature/` | una funcionalidad o clase por rama |
| `release/` | preparacion de una version |
| `hotfix/` | correccion de errores criticos |

Cada rama `feature/` se integra en `develop` mediante un pull request.