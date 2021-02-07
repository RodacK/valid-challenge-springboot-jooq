------- Para tener en cuenta ---------

Para generar fuentes

./gradlew generateJooq

para eliminar fuentes generadas

./gradlew cleanGenerateJooq

------------Copia de archivos base de la BD---------------

Estos archivos se necesitan para levantar el server de H2

1- Copiar el contenido de basebd.rar en el home de su usuario

2- navegar en su consola hasta la ubicacion del archivo h2-1.4.200.jar

3- Escribir el siguiente comando en la consola $ java -cp h2-1.4.200.jar org.h2.tools.Server -web -webAllowOthers -tcp -tcpAllowOthers -baseDir ~/tmp/h2dbs

4- La consola mostrara algo parecido a esto: TCP server running at tcp://172.25.80.1:9092 (others can connect)
tenga presente esto para configurar el build.gradle, el application.properties y su visualizador de bases de datos (si tiene uno, por ejemplo yo uso DB Viewer Plugin para STS )

Lo anterior permite levantar el server de BD en modo tcp para multiples conexiones (aunque solo se tiene creado un usuario por el momento)


----------------Extra y solo para ver definicion de la tabla y hacer consultas directamente---------------------------------------

Para correr un cliente H2 en local escribir en la terminal $java -jar h2-1.4.200.jar -baseDir C:\Users\miUser\tmp\h2dbs (tener en cuenta mi home y la ubicacion de lo que se extrajo del rar)

Usar diferentes querys que estan en el archivo h2.sql


NOTAS:
- La Carpeta frontend es donde estan las pagina que interactuaran con el back
- Si algo, esta es la collection https://www.getpostman.com/collections/3d07f19f003c899547e7
- Debido a que tenia la version 4.9.0 de STS, tuve que usar jdk 11 
- Los paquetes estan relacionados con MVC
- Se usa jooq como orm(Aunque tambien se hubiese podido utilizar Hibernate o consultas directas al jdbc)
- El contenido de com.valid.challenge.jooq.* son autogenerados por medio de un task configurado que se tiene comentareado en el buil.gradle
- com.valid.challenge.model.ProcessRequest es la clase a mapear para el request de procesar usuarios
- com.valid.challenge.model.convert.ListIntegerToListQuery es la clase deserializadora que toma la lista de ids a procesar y las convierte en una lista de querys para correrlas en un batch de jooq
- com.valid.challenge.init.InitialConfiguration Es donde se generan los beans a usar en la app y tambien par adefinir las configuraciones del cors y la conexion de jooq con h2
- com.valid.challenge.controller.UserController Es donde esta los mapeos de los recursos rest
- com.valid.challenge.controller.UserRepository Es donde se aprovecha el DSL (https://www.jooq.org/doc/3.14/manual/sql-building/sql-statements/dsl-and-non-dsl/) ya configurado para hacer las diferentes interacciones con la BD


