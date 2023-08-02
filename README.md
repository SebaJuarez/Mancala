# MANCALA

![Image](https://res.cloudinary.com/dwybyiio5/image/upload/v1690935370/wbju9bv7j5z4dqsmrmwi.png)

El proyecto consiste en la implementación del juego de mesa Mancala como parte del trabajo final de la asignatura "Programación Orientada a Objetos". El objetivo es desarrollar una aplicación que permita a los usuarios jugar Mancala en red, con múltiples jugadores conectados y siguiendo las reglas del juego. Se utilizarán patrones de diseño MVC y Observer, y se ofrecerán dos interfaces: gráfica y de consola. Además, se implementará la persistencia de datos por serialización para guardar el estado de los jugadores y asi obtener un ranking de los mejores de ellos. 
    
## Tabla de Contenidos

- [Introducción](#introducción)
- [Características](#características)
- [Requisitos previos](#requisitos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Agradecimientos](#agradecimientos)

## Introducción

El proyecto tiene como objetivo desarrollar una aplicación en línea para jugar al juego de mesa Mancala como parte del trabajo final de la asignatura "Programación Orientada a Objetos". Mancala es un antiguo juego de origen africano que se juega en un tablero con hoyos donde los jugadores deben mover piedras o semillas siguiendo [reglas específicas](https://brainking.com/es/GameRules?tp=103).

La aplicación permitirá a los usuarios conectarse y participar en partidas de Mancala en tiempo real, utilizando una implementación del juego en red con un servidor y múltiples clientes según se requiera. Se seguirán los patrones de diseño Modelo-Vista-Controlador (MVC) y Observer para mantener una estructura de código organizada y modular.

Se proporcionarán dos tipos de interfaces para jugar: una interfaz gráfica y una interfaz de consola. Ambas interfaces compartirán el mismo modelo del juego, asegurando que no se realicen cambios en el modelo para adaptarse a una u otra interfaz.

Además, la aplicación permitirá persistir los estados de los jugadores para implementar un sistema de ranking.

## Características

- MVC-Observer.
- Interfaz grafica y consola sin cambiar el modelo.
- Persistencia por serialización de jugadores.
- Sistema de Log-in.
- Sistema de Ranking.
- Juego en red.

## Requisitos

- El Juego está implementado en Java. Consulte https://www.java.com/en/download/help/download_options.xml. Los archivos deben compilarse, por lo que debe instalar un JDK (Java Development Kit). 
- Verifica que Git esté instalado en tu sistema. Puedes descargarlo e instalarlo desde https://git-scm.com/ si aún no lo tienes.
- Tener instalado en tu sistema un IDE como Eclipse ([recomendado](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2023-06/R/eclipse-inst-jre-win64.exe)), NetBeans, IntelliJ IDEA, etc..

## Instalación

- Abre una terminal o línea de comandos en tu computadora.
- Navega a la ubicación en la que deseas clonar el repositorio utilizando el comando cd.
- Clona el repositorio utilizando el siguiente comando:

```bash
git clone https://github.com/SebaJuarez/Mancala.git
```
- Importa el proyecto a tu IDE
- Agrega los archivos .jar al build path 
    1. Click derecho al archivo -> build path -> add to build path
- Agregar la carpeta resources como source folder
    1. Click derecho sobre la carpeta -> build path -> use as source folder

## Uso

1.  Seleccionar la interfaz grafica que desea ver. 
     1. Situarse en src/ar/edu/unlu/mancala/rmi/cliente/AppCliente.java 
     2. Si desea ver la interfaz grafica 
comentar la [linea](https://github.com/SebaJuarez/Mancala/blob/4e923770fafec2bad95edc7cd16a65386ddb31f9/src/ar/edu/unlu/mancala/rmi/cliente/AppCliente.java#L52) 52 y descomentar la [linea](https://github.com/SebaJuarez/Mancala/blob/4e923770fafec2bad95edc7cd16a65386ddb31f9/src/ar/edu/unlu/mancala/rmi/cliente/AppCliente.java#L53) 53. 
    3. Si desea ver la interfaz de consola, haga el proceso inverso.

2. Ejecutar el  servidor 
    1. Ejecutar la clase AppServidor.java situada en "src/ar/edu/unlu/mancala/rmi/servidor"
    2. Selecciona la ip donde se escucharan las peticiones

       ![Image](https://res.cloudinary.com/dwybyiio5/image/upload/v1690935864/imcyz62cvqyejhugfbcg.png)
       
    3. Presione aceptar, luego seleccione el numero de puerto
       
       ![Image](https://res.cloudinary.com/dwybyiio5/image/upload/v1690935905/jdunzlpnodidrybhoejp.png)
       
3. Ejecutar el cliente
    1. Ejecutar la clase AppCliente.java situada en "src/ar/edu/unlu/mancala/rmi/cliente"
    2. Seleccionar la ip donde se escucharan las peticiones el cliente.
       
    ![Image](https://res.cloudinary.com/dwybyiio5/image/upload/v1690935935/vjgmtuxujufilgxf6okw.png)

    3. Presione aceptar, luego seleccione el numero de puerto en que escuchará el cliente.
    
    ![Image](https://res.cloudinary.com/dwybyiio5/image/upload/v1690935533/cdpn8nhjs80kfptkzt2j.png)

    4. Presione aceptar, luego seleccione la Ip en la que corre el servidor.
    
    ![Image](https://res.cloudinary.com/dwybyiio5/image/upload/v1690935691/qnpxvcewjxe61hzrtxma.png)

    5. Presione aceptar, luego seleccione el numero de puerto que corre el servidor y presione aceptar.
    
    ![Image](https://res.cloudinary.com/dwybyiio5/image/upload/v1690935647/zqkmhzotkod629if2ubr.png)
        
4. Repita este ultimo proceso por cada cliente que desee tener en linea.
        
## Agradecimientos

Agradezco a [federico radeljak](https://github.com/federicoradeljak) por proporcionar la librería "librería-rmimvc", que ha agilizado la implemetacion de RMI en mi proyecto. Para más información sobre esta librería y su código  [pueden visitar el enlace](https://github.com/federicoradeljak/libreria-rmimvc).
        
