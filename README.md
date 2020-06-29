# Mutant API


## Diseño de la API

Al diseñar la solución pensé en una arquitectura basada en microservicios, en donde para llegar a cubrir el objetivo de un millón de request por segundo sería necesario escalar horizontalmente hasta poder satisfacer dicha demanda.
Cabe aclarar que el servicio es stateless, ya que solo se preocupa de ejecutar un algoritmo para reconocer un patrón y devolver un resultado. El estado se encuentra en la base de datos elegida, Cassandra.
Se eligió Cassandra ya que permite el almacenamiento de grandes cantidades de datos de una manera distribuida, con alta disponibilidad y un gran rendimiento. Por lo cual también sería necesario contar con un cluster de estos nodos.
Cada microservicio cuenta con una cache interna limitada por tamaño, que almacena de manera temporal (cache efímera) los ADN verificados. El objetivo de ésta cache es evitar ataques de denegación de servicio sencillos, como ser el de enviar reiteradas veces a analizar el mismo ADN.


## Componentes

1. El código fuente
2. [JavaDocs](/apidocs) de la API
3. Se incluye un excel explicando la lógica del algoritmo. ([Lógica-Algoritmo](./Logica-Algoritmo.xlsx))
4. [Test unitarios](/src/test) Los tests que necesitan levantar el contexto de Spring utilizan una base cassandra en memoria. ([cassandra-unit](https://github.com/jsevellec/cassandra-unit))
5. Reporte de coverage mediante *jacoco-maven-plugin* ([Rerporte](/jacoco))


## API hosteada en Amazon AWS

- [Mutan API](http://54.159.111.93/swagger-ui.html).
- Comandos CURL para ejecutar ambos endpoints:

```
curl -X POST "http://54.159.111.93/mutant" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"dna\": [ \"ATGCGA\",\"CAGTGC\",\"TTATGT\",\"AGAAGG\",\"CCCCTA\",\"TCACTG\" ]}"

curl -X GET "http://54.159.111.93/stats" -H "accept: application/json"
```


## ¿Cómo ejecutar la API?

Tener en cuenta que la API requiere una base cassandra ([Cassandra 3.11.6](https://www.apache.org/dyn/closer.lua/cassandra/3.11.6/apache-cassandra-3.11.6-bin.tar.gz))

- Se modificó dentro del archivo *cassandra.yaml* el valor de la property *"authenticator"* a *"PasswordAuthenticator"*
- Debe existir el keyspace *magneto*

```
CREATE KEYSPACE IF NOT EXISTS magneto WITH REPLICATION = {'class':'SimpleStrategy', 'replication_factor':1};
```

Archivo de properties default:

```
server.port=80

spring.cache.cache-names=mutant_cache
spring.cache.caffeine.spec=initialCapacity=1000,maximumSize=10000

spring.data.cassandra.contact-points=localhost
spring.data.cassandra.port=9042
spring.data.cassandra.username=cassandra
spring.data.cassandra.password=cassandra
spring.data.cassandra.local-datacenter=datacenter1
spring.data.cassandra.keyspace-name=magneto
spring.data.cassandra.schema-action=CREATE_IF_NOT_EXISTS
```

Para levantar el servicio con los valores predeterminados:

```
java -jar challenge-1.0.0.jar
```

Si se quiere proveer un archivo properties customizado:

```
java -jar challenge-1.0.0.jar --spring.config.location=application.properties
```

Para deshabilitar la cache:

```
java -jar challenge-1.0.0.jar --spring.cache.type=none
```

Una vez que se haya ejecutado alguno de los comandos anteriores, la API estará disponible en el puerto 80 por default.
