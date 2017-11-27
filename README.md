# **LeakyCauldron**
## **KeepCoding Startup Engineering Master V (2017)**

- - -

# **Práctica Fundamentos Android con Kotlin**
## **Sinopsis**
El objetivo de la práctica es generar una aplicación en Android Kotlin que permita almacenar platos agrupados en mesas siguiendo como criterio la selección desde una mesa del plato deseado.

## **Requisitos**
- Debe descargar la lista de posibles platos del restaurante de un servicio. Como no es el propósito principal de esta práctica hacer el servicio puedes valerte de Mocky: http://www.mocky.io/ o de tu propio servicio, lo que prefieras
- Una vez descargada la carta, desde la aplicación ha de ser posible acceder a un listado de las mesas de los clientes
- Cuando se accede a una mesa debe poder verse lo que han pedido hasta el momento. No es necesario guardar qué ha pedido cada cliente individual, confiaremos en que el camarero se acuerde qué plato iba para cada cliente en una mesa
- Desde esa vista de mesa se deben poder añadir platos. Para esto aparecerá otra pantalla donde se pueda elegir el plato de una lista. En esa lista aparece el nombre del plato, una pequeña imagen, unos iconos que indiquen los alérgenos que posee (si los tiene) y su precio
- Al pulsar sobre un plato aparece una pantalla con la información del plato (imagen, algún detalle más...), y una caja de texto donde poner las pequeñas variantes que pueda pedir un cliente
- Si guardamos dicho plato se añadirá a la lista de los platos que han pedido en una mesa
- Debe existir un pequeño menú para calcular la cuenta

## **Valoración**
En la aplicación se valorará:
- Buena aplicación de los conceptos vistos en el curso
- Diseño material allá donde sea posible y tenga sentido
- Organización y claridad del código
- Facilidad de uso para el usuario
- Distintas visualizaciones y formas de interactuar para distintos dispositivos como hemos visto en el curso (al menos distingue entre dispositivos tipo teléfono y tipo tableta)
- Soporte de varias versiones de Android, cubriendo un mínimo del 90% de
los dispositivos actuales

## Pantallas 
### Portrait
Listado mesas | Lista de platos de una mesa | Listado de platos completo | Detalle de un plato
------------ | ------------- | ------------- | -------------
<img src = "https://github.com/manuelcolmenero/LeakyCauldron/blob/develop/screenshots/port/Pantalla01.png" width="250px"> | <img src = "https://github.com/manuelcolmenero/LeakyCauldron/blob/develop/screenshots/port/Pantalla02.png" width="250px"> | <img src = "https://github.com/manuelcolmenero/LeakyCauldron/blob/develop/screenshots/port/Pantalla03.png" width="250px"> | <img src = "https://github.com/manuelcolmenero/LeakyCauldron/blob/develop/screenshots/port/Pantalla04.png" width="250px">

Menú del listado  | Factura 
------------ | ------------- 
<img src = "https://github.com/manuelcolmenero/LeakyCauldron/blob/develop/screenshots/port/Pantalla05.png" width="250px"> | <img src = "https://github.com/manuelcolmenero/LeakyCauldron/blob/develop/screenshots/port/Pantalla06.png" width="250px"> 

### Landscape
Listado mesas | Listado de platos completo 
------------ | ------------- 
<img src = "https://github.com/manuelcolmenero/LeakyCauldron/blob/develop/screenshots/land/pantalla01.png" width="350px"> | <img src = "https://github.com/manuelcolmenero/LeakyCauldron/blob/develop/screenshots/land/pantalla02.png" width="350px"> 


Detalle de un plato |  Factura 
------------ | -------------
<img src = "https://github.com/manuelcolmenero/LeakyCauldron/blob/develop/screenshots/land/pantalla03a.png" width="350px"> <img src = "https://github.com/manuelcolmenero/LeakyCauldron/blob/develop/screenshots/land/pantalla03b.png" width="350px"> | <img src = "https://github.com/manuelcolmenero/LeakyCauldron/blob/develop/screenshots/land/pantalla04.png" width="250px"> 