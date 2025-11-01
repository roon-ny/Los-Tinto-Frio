# 🌾 Proyecto AgroSmart  
### Optimización de rutas y tiempos de recolección de cultivos 🚜

---

## 🌱 ¿Qué es este proyecto?

**AgroSmart** es un programa hecho en **Java** que busca ayudar a los **agricultores y recolectores** a trabajar de una forma **más eficiente, rápida y con menos desgaste físico**.

La idea principal es **asignar a cada recolector las parcelas o zonas del cultivo más cercanas**, equilibrando la **cantidad de trabajo (en kilos o toneladas)** y calculando **el tiempo óptimo de recolección**, según el tipo de cultivo y su estado de maduración.

De esta forma, se logra una **mejor distribución del trabajo**, se **minimizan los recorridos innecesarios** y se **aprovecha mejor el tiempo de cosecha**.

---

## 🍃 ¿Por qué hicimos esto?

En muchos cultivos (café, caña, maíz, cacao, plátano, etc.), la organización de los recolectores se hace “a ojo”, sin una planificación basada en datos.  
Esto genera varios problemas:

- Recolectores caminando más de lo necesario.  
- Zonas que se recolectan tarde o se desperdician.  
- Tiempos de trabajo mal distribuidos.  
- Mayor cansancio y menor rendimiento.

El objetivo del proyecto es **usar programación para mejorar el rendimiento del campo** sin requerir tecnología costosa.  
Solo se necesitan los datos de las parcelas y los recolectores para generar una **planificación inteligente y justa**.

---

## 💡 ¿Cómo funciona?

El sistema trabaja con **datos básicos del cultivo y del terreno**, y a partir de eso toma decisiones.

### 1. Datos de entrada:
- **Tipo de cultivo:** café, maíz, cacao, etc.  
- **Parcelas:** posición (x, y) y cantidad estimada de producto (kg o toneladas).  
- **Recolectores:** posición inicial y capacidad de trabajo.  
- **Fecha de siembra o floración** (para calcular cuándo el cultivo estará listo).

### 2. Cálculos que realiza:
- **Tiempo estimado de maduración del cultivo** (según tipo y clima).  
- **Asignación óptima de parcelas a recolectores**, equilibrando distancia y carga.  
- **Tiempos de recolección** (en horas o días).  
- **Rutas óptimas** para que cada recolector camine lo menos posible.

### 3. Fórmulas base:
- Distancia entre puntos (x, y):  
  \[
  d = \sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2}
  \]
- Tiempo total ≈ tiempo caminando + tiempo recolectando.  
  Ejemplo:  
  - 10 kg = 1 hora de recolección.  
  - 1 km = 1 hora de caminata.

---
El sistema sugiere **qué recolector va a cada zona**, **cuánto recogerá** y **cuánto tiempo tardará**, ayudando a planificar toda la jornada.

---

## 🌤️ Tiempo óptimo de recolección

AgroSmart también puede analizar **cuándo el cultivo estará listo para cosechar**, dependiendo del tipo y del clima.

Por ejemplo:
- **Café:** entre 210 y 230 días después de la floración.  
- **Maíz:** alrededor de 120 días desde la siembra.  
- **Cacao:** entre 150 y 180 días desde la floración.

El programa puede estimar la **fecha de cosecha óptima** a partir de la fecha de siembra o floración ingresada.

🧮 Ejemplo:
> Si el maíz se sembró el 10 de junio,  
> el sistema sugiere la cosecha entre el **5 y el 10 de octubre**.

Esto ayuda a los agricultores a **organizar la mano de obra y los recursos** antes del momento ideal.

---

## 🧩 Estructura del código

| Clase | Función |
|-------|----------|
| `Parcela` | Guarda la posición (x, y), tipo de cultivo y estimación de peso. |
| `Recolector` | Representa un trabajador con su ubicación y carga de trabajo. |
| `Cultivo` | Contiene información general del cultivo (nombre, tiempo de maduración, etc.). |
| `AsignacionCultivos` | Clase principal que calcula asignaciones, tiempos y rutas óptimas. |

---

## 🧠 Lógica del algoritmo

1. Lee los datos de las parcelas, cultivos y recolectores.  
2. Calcula la **distancia** entre cada recolector y las parcelas disponibles.  
3. Asigna la parcela al recolector más cercano con menos carga.  
4. Suma el tiempo estimado (recolección + caminata).  
5. Muestra el plan final de trabajo y los tiempos totales.

---

## 🛠️ Cómo usar el programa

1. Clona o descarga el repositorio:  

## 🧭 Ejemplo de salida del programa

## 📊 Resultado de asignación óptima

```text
Recolector 1 - Total: 60kg (Cultivo: Café)
   ➜ Parcela 1 (2.0,3.0) - 25kg
   ➜ Parcela 4 (1.0,8.0) - 35kg
Tiempo estimado: 7.8 horas

Recolector 2 - Total: 55kg (Cultivo: Maíz)
   ➜ Parcela 2 (5.0,1.0) - 15kg
   ➜ Parcela 3 (7.0,4.0) - 20kg
   ➜ Parcela 5 (4.0,7.0) - 20kg
Tiempo estimado: 6.5 horas
