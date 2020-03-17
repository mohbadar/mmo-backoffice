### Monitoring and Metrics Library of ePayFrame with Grafana and Prometheus


#### Usage Guide: 

add the following dependency to pom.xml 


```
		<dependency>
	               <groupId>af.gov.anar.lib</groupId>
	               <artifactId>anar-lib-metrics</artifactId>
			<version>${project.version}</version>
		</dependency>
```

#### Features with Actuator 

- Flexible Config with properties
- Prometheus based matrics data generator
- Production Ready health data 
- Exposes audit events information for the current application. Requires an AuditEventRepository bean.
- Displays a complete list of all the Spring beans in your application.
- Exposes available caches.
- Shows the conditions that were evaluated on configuration and auto-configuration classes and the reasons why they did or did not match.
- Displays a collated list of all @ConfigurationProperties.
- Exposes properties from Spring’s ConfigurableEnvironment.
- Shows any Flyway database migrations that have been applied. Requires one or more Flyway beans.
- Shows application health information.
- Displays HTTP trace information (by default, the last 100 HTTP request-response exchanges). Requires an HttpTraceRepository bean.
- Displays arbitrary application info.
- Shows the Spring Integration graph. Requires a dependency on spring-integration-core.
- Shows and modifies the configuration of loggers in the application.
- Shows any Liquibase database migrations that have been applied. Requires one or more Liquibase beans.
- Shows ‘metrics’ information for the current application.
- Displays a collated list of all @RequestMapping paths.
- Displays the scheduled tasks in your application.
- Allows retrieval and deletion of user sessions from a Spring Session-backed session store. Requires a Servlet-based web application using Spring Session.
- Lets the application be gracefully shutdown. Disabled by default.



add following properties in `application.properties`

```properties

#Metrics related configurations
management.endpoint.metrics.enabled=true
management.endpoints.web.exposure.include=*
management.endpoint.prometheus.enabled=true
management.metrics.export.prometheus.enabled=true
management.metrics.export.graphite.step=1s
```

### Documentation 

For more information refer to wiki section of this repository
