# Taller de Manejo de Figuras Geometricas

Sistema de escritorio para crear, consultar y manipular figuras geometricas: circulo,
triangulo, cuadrado y pentagono regular. Cada figura es una subclase de `Figura`, que
centraliza el comportamiento comun a todas ellas, y guarda su posicion en el plano
mediante un `Punto`.

## Requisitos

- Java 25. El proyecto esta configurado en IntelliJ con JDK 25 (Corretto).
- No se utilizan librerias externas.

## Como ejecutar

### Desde IntelliJ IDEA

1. Abrir la carpeta raiz del proyecto.
2. Esperar a que el IDE detecte `src` como carpeta de fuentes.
3. Abrir `src/Main.java`.
4. Presionar el boton de ejecucion junto al metodo `main`.

El proyecto usa el GUI Designer de IntelliJ, que genera el codigo de los formularios
en tiempo de compilacion. Por eso **no se puede ejecutar desde la terminal**: compilar
con `javac` a mano deja los componentes de los formularios en `null` y la ventana sale
vacia. Hay que correr siempre desde el IDE, con **Build > Rebuild Project** antes de
ejecutar.

## Como usar

La ventana tiene tres zonas.

- **Selector de figuras.** Arriba, los paneles con triangulo, cuadrado, pentagono y
  circulo. Al hacer clic se abre el dialogo para crear esa figura.
- **Listado de figuras.** A la izquierda, la tabla con tipo, area, perimetro y posicion
  de cada figura. Se selecciona una fila para poder operarla.
- **Plano.** A la derecha, la grilla con el origen en el centro. Las figuras se dibujan
  escaladas, no a tamano real, para poder ver varias a la vez.

Con una fila seleccionada se habilitan las cuatro acciones:

| Accion | Que hace |
|---|---|
| Desplazar | Mueve la figura en el plano |
| Escalar | Agranda la figura sobre su propio punto, sin moverla |
| Comparar | Abre un dialogo con las figuras del mismo tipo y muestra si tienen el mismo dimensionamiento |
| Eliminar | Pide confirmacion y quita la figura |

### Limites de las figuras

El modelo valida lo que se ingresa, asi que casi ningun dato invalido llega a la
interfaz.

- La dimension debe ser un numero positivo, finito y de hasta 20 unidades. Con la
  escala del plano, 20 unidades entran completas en la vista.
- La posicion puede moverse hasta 40 unidades del origen en cada eje.
- El pentagono regular exige que el apotema corresponda al lado, segun
  `apotema = lado / (2 * tan 36 grados)`.

Cuando un dato no cumple, el dialogo muestra el motivo y no crea nada.

## Estructura del proyecto

```
src/
├── Main.java                       Punto de entrada
├── assets/                         Iconos del selector de figuras
├── controllers/
│   └── FiguraController.java       Almacena las figuras y la seleccion
├── domain/
│   ├── Figura.java                 Clase abstracta base
│   ├── Punto.java                  Posicion en el plano
│   ├── Circulo.java
│   ├── Cuadrado.java
│   ├── Triangulo.java
│   └── PentagonoRegular.java
├── exceptions/
│   └── DimensionInvalidaException.java
└── views/
    ├── MainForm.java               Ventana principal
    ├── Lienzo.java                 Plano con grilla y figuras
    ├── CompararFigura.java         Dialogo de comparacion
    ├── EscalarFigura.java          Dialogo de escalado
    ├── DesplazarFigura.java        Dialogo de desplazamiento
    ├── Crear_triangulo.java        Dialogos de creacion
    ├── Crear_circulo.java
    ├── Crear_cuadrilaterlo_form.java
    ├── Crear_pentagono.java
    └── *.form                      Diseno de los formularios
```

`FiguraController` mantiene la coleccion de figuras, guarda cual esta seleccionada y
expone las operaciones de agregar, eliminar, comparar, desplazar y escalar. Las clases de
`domain` no dependen del controlador ni de la vista.

### Como se dibuja cada figura

`Figura` declara `obtenerContorno()`, que devuelve los puntos del contorno de la figura
en coordenadas del plano. Cada subclase dice como se dibuja, y `Lienzo` solo convierte
esos puntos a pixeles. Agregar una figura nueva no obliga a modificar la vista.

El circulo es el unico caso aproximado: se dibuja como un poligono de 360 segmentos en
vez de un arco exacto, para que `domain` no dependa de las clases de dibujo de Java.

## Integrantes

| Integrante | Usuario de GitHub | Clases a cargo |
|---|---|---|
| Jhojan Andres Villada | `VilladaV` | `Figura`, `Punto`, `Main` |
| Juan Pablo Olave Munoz | `donpedromz` | `FiguraController`, `DimensionInvalidaException` |
| Maria Paz Bustamante | `mariapazbustamante` | `Circulo`, `Cuadrado`, `Triangulo`, `PentagonoRegular` |
