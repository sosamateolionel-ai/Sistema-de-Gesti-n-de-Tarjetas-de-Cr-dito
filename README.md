# Sistema de Gestión de Tarjetas de Crédito

## Descripción
Este proyecto desarrolla un sistema de gestión de tarjetas de crédito utilizando Java y Programación Orientada a Objetos.

Permite administrar diferentes categorías de tarjetas (Estándar, Gold y Black), realizar compras nacionales e internacionales, calcular comisiones, controlar límites de crédito y otorgar beneficios como millas acumuladas y seguro de viaje.

## Funcionamiento del programa

El programa crea tres tarjetas de crédito:

- **Tarjeta Estándar:** pertenece a Laura Gómez, tiene un límite de $200.000 y aplica una comisión del 5% en compras internacionales.
- **Tarjeta Gold:** pertenece a Carlos Pérez, tiene un límite de $500.000, permite superar el límite hasta un 10%, aplica una comisión internacional del 3% y acumula 1 milla por cada $1.000 consumidos.
- **Tarjeta Black:** pertenece a Marta Díaz, tiene un límite de $1.000.000, permite superar el límite hasta un 20%, no cobra comisión internacional, acumula 3 millas por cada $1.000 consumidos e incluye seguro de viaje.

### 1. Operaciones de la tarjeta Estándar

1. Compra nacional de $150.000: aprobada.
2. Compra internacional de $40.000: aprobada, con una comisión de $2.000.
3. Compra nacional de $20.000: rechazada por superar el límite disponible.

**Consumo acumulado: $192.000.**

### 2. Operaciones de la tarjeta Gold

1. Compra nacional de $300.000: aprobada.
2. Compra internacional de $200.000: aprobada, con una comisión de $6.000. Utiliza parte del margen de tolerancia.
3. Compra nacional de $50.000: rechazada porque supera el límite permitido, incluyendo la tolerancia.

**Consumo acumulado: $506.000.**

**Millas acumuladas: 500.**

### 3. Operaciones de la tarjeta Black

1. Compra internacional de $800.000: aprobada, sin comisión.
2. Compra internacional de $350.000: aprobada, sin comisión. Utiliza parte del margen de tolerancia.
3. Compra nacional de $100.000: rechazada porque supera el límite permitido.

**Consumo acumulado: $1.150.000.**

**Millas acumuladas: 3.450.**

Finalmente, el programa almacena las tres tarjetas en un arreglo de tipo `TarjetaCredito[]` y recorre sus elementos para mostrar sus respectivos estados de cuenta.

## Clases principales

| Clase | Responsabilidad |
|---|---|
| `TarjetaCredito` | Clase base que administra los datos generales, las compras, las comisiones y la validación del límite de crédito. |
| `TarjetaGold` | Hereda de `TarjetaCredito`, permite una tolerancia del 10%, aplica una comisión internacional preferencial y acumula millas. |
| `TarjetaBlack` | Hereda de `TarjetaCredito`, permite una tolerancia del 20%, elimina la comisión internacional y ofrece millas y seguro de viaje. |
| `Main` | Crea las tarjetas, ejecuta las operaciones y muestra los estados de cuenta. |

## Conceptos de programación aplicados

- **Herencia:** `TarjetaGold` y `TarjetaBlack` heredan los atributos y métodos de `TarjetaCredito`.
- **Polimorfismo:** se utiliza un arreglo `TarjetaCredito[]` para almacenar diferentes categorías de tarjetas.
- **Sobrescritura de métodos:** las subclases redefinen métodos como `getCategoria()`, `validarCompra()`, `getTasaComisionInternacional()` y `mostrarEstadoCuenta()`.
- **Encapsulamiento:** se utilizan atributos `private` y `protected` para controlar el acceso a los datos.
- **Constructores con `super()`:** permiten inicializar los atributos heredados.
- **Métodos protegidos:** permiten que las subclases especialicen el cálculo de comisiones y la acreditación de beneficios.
- **Constantes (`static final`):** representan las comisiones internacionales y los porcentajes de tolerancia.
- **Validaciones:** se controla que los montos sean positivos y que las compras no superen el límite autorizado.
- **Condicionales:** permiten determinar si una compra es nacional o internacional y si corresponde aplicar una comisión.
- **Acumulación de beneficios:** las tarjetas Gold y Black calculan y registran las millas obtenidas por las compras aprobadas.

## Reglas de cálculo

| Categoría | Comisión internacional | Tolerancia del límite |
|---|---:|---:|
| Estándar | 5% | 0% |
| Gold | 3% | 10% |
| Black | 0% | 20% |

Las compras se validan considerando el importe original más la comisión correspondiente. Las tarjetas Gold y Black pueden superar su límite base dentro del margen de tolerancia establecido.

## Tecnologías utilizadas

- Lenguaje: Java.
- Entorno de desarrollo: IntelliJ IDEA.
- Paradigma: Programación Orientada a Objetos.

## Ejecución

<img width="846" height="740" alt="image" src="https://github.com/user-attachments/assets/ca5a9a87-6e26-4fba-b490-33ddba6147ff" />
<img width="344" height="167" alt="image" src="https://github.com/user-attachments/assets/3e7bd255-25be-4d50-ac2e-24746be22d5c" />

## Resultado esperado

Al finalizar la ejecución, los estados de cuenta muestran:

| Tarjeta | Consumo acumulado | Disponible según límite base |
|---|---:|---:|
| Estándar | $192.000,00 | $8.000,00 |
| Gold | $506.000,00 | -$6.000,00 |
| Black | $1.150.000,00 | -$150.000,00 |

Además:

- Gold acumula 500 millas.
- Black acumula 3.450 millas.
- Black tiene seguro de viaje incluido.
- Las compras que superan los límites autorizados son rechazadas.

## Objetivo del ejercicio

Aplicar los conceptos de herencia, polimorfismo, encapsulamiento y sobrescritura de métodos mediante un sistema que represente distintas categorías de tarjetas de crédito, cada una con sus propias reglas de comisiones, límites y beneficios.

## Estructura del proyecto

```text
SistemaTarjetasCredito/
├── TarjetaCredito.java
├── TarjetaGold.java
├── TarjetaBlack.java
├── Main.java
└── README.md
```

**Nombre sugerido para el repositorio:** `sistema-tarjetas-credito`

## Observación

El programa simula las operaciones y reglas de tarjetas de crédito mediante la consola. No se conecta con entidades bancarias ni procesa pagos reales. Los importes disponibles se muestran respecto del límite base, por lo que pueden ser negativos cuando se utiliza la tolerancia autorizada para las tarjetas Gold y Black.
