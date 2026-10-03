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

En Windows con PowerShell:

```powershell
javac -d out (Get-ChildItem -Path src -Recurse -Filter *.java).FullName
java -cp out Main
```

Al iniciar, el programa ofrece un menu con seis opciones: crear una figura, ver todas
las figuras, desplazar una figura, escalarla, comparar dos figuras y salir del sistema.

Para desplazar, escalar o comparar, el menu muestra las figuras registradas con un
numero y pide el indice de la que se quiere operar. Si no hay ninguna figura registrada,
lo indica y vuelve al menu.

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

## Integrantes

| Integrante | Usuario de GitHub | Clases a cargo |
|---|---|---|
| Jhojan Andres Villada | `VilladaV` | `Figura`, `Punto`, `Main` |
| Juan Pablo Olave Munoz | `donpedromz` | `FiguraController`, `DimensionInvalidaException` |
| Maria Paz Bustamante | `mariapazbustamante` | `Circulo`, `Cuadrado`, `Triangulo`, `PentagonoRegular` |