# TP2-PIL
Trabajo practico 2 obligatorio para Pil -Globant

## Objetivo general

La creación e implementación de la clase EpicDoubleHashMap, la cual será una clase que nos permitirá guardar para cada key, hasta dos valores distintos pertenecientes a dos clases distintas, más diversas operaciones. También se pedirá la prueba de distintos escenarios en la función main().

## Objetivos específicos
- Crear la clase genérica EpicDoubleHashMap<K extends Number, V, T>, en la cual las llaves serán valores numéricos.
- Crear clases para poder instanciar la clase genérica principal, y ejecutar todas las funciones de la clase al menos una vez.
### Metodos
- Contener funciones que permitan agregar para dada una key, un valor o dos dependiendo de lo que se quiera, por ejemplo, es posible añadir un item conteniendo un entero y un objeto de clase V, o un float y un objeto de clase T, o un short y dos objetos V y T respectivamente. Dos funciones para devolver el valor V o T del item dada una key. 
- También deberá tener una función para remover el item que contenga un key en específico. (item se le dice a la combinación key+value, o key+values dependiendo). 
- Un método que printee si existen más valores de tipo T, V o si tienen la misma cantidad (recordar que es posible añadir items con una key y un solo valor, y no necesariamente con dos).
- Un método que dado una key, devuelva cuantos valores existen iguales al valor asociado a la key. (De vuelta, es necesario la implementación de equals en las clases que generalicen V y T).
- Un método booleano que devuelva true si existen values repetidos, y false en caso contrario.

### Excepciones
Se deberán crear excepciones (subclases de Exception definidas por el usuario) para las siguientes situaciones particulares:
- Añadir un item con una key ya existente
- Remover un item con una key inexistente
- Añadir un item con un valor/valores que se repita/repitan por lo menos 3 veces (Para lograr esto, es necesaria la implementación del equals en las clases que generalicen V y T).
- Tratar de obtener el valor V o T de una key, sin que este exista. (Esto se da en caso de que se haya insertado por ejemplo, una key y un valor V, y se esté pidiendo un valor T).
- Se deben capturarlas e informar la situación debidamente al usuario.

## Integrantes del equipo:
Agustina Mastricola, Lucas Agustin Heredia, Gabriel Moncerrat.
