### Kafka Integration Library for ePayFrame

#### Usage Guide

```
		<dependency>
	               <groupId>af.gov.anar.lib</groupId>
	                <artifactId>anar-lib-kafka</artifactId>
                        <version>${project.version}</version>
		</dependency>

```

add following in your `application.properties`

```properties
# Kafka Connect Host
kafka.connect.host=http://localhost:8083
#Kafka Rest Host
kafka.rest.host=http://localhost:9092
#Schema URL
kafka.schema.url=http://localhost:8081

tpd.topic-name=default-topic
```


#### Documentation

Refer to this repository **Wiki** section.
https://github.com/Anar-Framework/anar-lib-kafka/wiki