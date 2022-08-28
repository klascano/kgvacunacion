# Kruger vacunacion api
Proyecto apis para sistemas de vacunación de empleados.

> Tildaciones y acentos son explicitamente excluidos para una mejor visualizacion de esta documentacion.

> No se incluye en esta documentacion instalacion y configuracion de herramientas e IDES para desarrollo.

## Software

 1. [Openjdk 11](https://openjdk.org/projects/jdk/11/)
 2. [Gradle 7](https://gradle.org/releases/)
 3. [Docker](https://www.docker.com/) ( Opcional para base de datos) 
	 3.1. [PostgresSQL 14](https://www.postgresql.org/download/)
	 3.2. [PgAdmin 4](https://www.pgadmin.org/download/)
4. [Eclipse 2021 o superior](https://www.eclipse.org/downloads/)
5. [Pagekite](https://pagekite.net/)

## Estructura de directorios

 - app: Proyecto api
 - db: Archivos para la base de datos
 - doc: Readme en formato pdf

## Repositorio de datos
Se utiliza PostgresSQL 14. Base de datos por defecto postgres. Usuario y contraseña por defecto postgres. Uso de docker opcional.

    docker run --name postgres14 -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres
Cargar la base de datos desde db/schema.sql con pgAdmin4 o desde consola.

    ## consola
	psql -U postgres -d postgres -h 127.0.0.1 -f db/schema.sql

Por defecto se tiene un registro en la tabla usuario con usuario/contraseña: 0103897955/0103897955 para las operaciones de admin.

## Apis
El proyecto api se encuentra en app/vacunaapi.

Para compliar

    cd app/vacunaapi
	gradle build

Para ejecutar con gradle

    cd app/vacunaapi
    gradle bootRun
	
Si todo esta correcto debemos tener una salida que se muestra a continuacion:

    INFO 93448 --- [  restartedMain] o.s.b.d.a.OptionalLiveReloadServer       : LiveReload server is running on port 35729
    INFO 93448 --- [  restartedMain] o.s.b.w.e.t.TomcatWebServer              : Tomcat started on port(s): 2090 (http) with context path ''
    INFO 93448 --- [  restartedMain] c.k.v.VacunaapiApplication               : Started VacunaapiApplication in 1.935 seconds (JVM running for 2.239)

**Publicacion a internet**

La api esta publicada mediante el servicio [pagekite](https://pagekite.net/) con la url base:

    https://klascano.pagekite.me/{{paths}}
    ##{{paths}} rutas de los servicios.


> Debido a que pagekite es un servicio gratuito y las apis estan en un ambiente de desarrollo pueden talvez no estar disponibles para pruebas. SOLICITAR SU HABILITACION EN CASO DE NO DISPONIBILIDAD.

## Documentacion apis
Las apis disponibles son:

 1. Autenticar https://klascano.pagekite.me/authenticate
	 * GET / No requiere rol ni token.
	 * Genera un token valido para las otras llamadas. Dentro del token se encuenta el claim "rol".
 2. Todos los empleados https://klascano.pagekite.me/emps
	 * GET / Requiere token con rol admin
 3. Registrar empleado https://klascano.pagekite.me/emps
	 * POST / Requiere token con rol admin
4. Actualizar datos empleado https://klascano.pagekite.me/emp/{{id_empleado}}
	* PUT / Requiere token con rol empleado
5. Filtra listado de empleados por estado de vacunacion https://klascano.pagekite.me/emps/ev/{{estado:0|1}}
	* GET / Requiere token con rol admin
6. Filtra listado de empleados por tipo de vacuna https://klascano.pagekite.me/emps/tv/{{tipo_vacuna: Sputnik| AstraZeneca| Pfizer | Jhonson&Jhonson}}
	* GET / Requiere token con rol admin
7. Filtra listado de empleados por fecha de vacuna https://klascano.pagekite.me/emps/fv/{{fi}}/{{ff}}
	* GET / Requiere token con rol admin
	* fi y ff Fecha en formato ddMMyyyy

Para mayor informacion y detalle consultar la documentacion swagger:

[Kruger vacunacion api](https://app.swaggerhub.com/apis/klascano/krugervacunacionapi/0.1)

## Soporte tecnico
kleverlascano@gmail.com / 0983739079