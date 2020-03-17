### CORS (Cross Origin Resourcing Sharing) Library of ePayFrame

#### Usage Guide

```
		<dependency>
	               <groupId>af.gov.anar.lib</groupId>
	                <artifactId>anar-lib-cors</artifactId>
                        <version>${project.version}</version>
		</dependency>

```


Add following properies in your `application.properties`

```aidl
anar.cors.allowed-origin=
anar.cors.allowed-method=
anar.cors.allowed-header=
```

default value is `*` for all of them. 


### Adding new Resource Sharing Origin

```java



    @Autowired
    private CorsService corsService;


        CorsEntity corsEntity = CorsEntity.builder()
                .allowedHeader(allowedOrigin)
                .allowedMethod(allowedMethod)
                .allowedHeader(allowedHeader)
                .build();
        corsService.save(corsEntity);

  

```

#### Documentation

Refer to this repository **Wiki** section.
https://github.com/Anar-Framework/anar-lib-cors/wiki



