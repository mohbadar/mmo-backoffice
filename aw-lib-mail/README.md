### Mail Utility Library for ePayFrame

#### Usage Guide

```
		<dependency>
	               <groupId>af.gov.anar.lib</groupId>
	                <artifactId>anar-lib-mail</artifactId>
                        <version>${project.version}</version>
		</dependency>

```

To use MailBuilder Add mail.properties in resources file and add following properties

```$xslt
mail.protocol = smtp
mail.host = smtp.gmail.com
mail.port = 587
mail.debug = true
mail.smtp.auth = false
mail.smtp.starttls.enable = false
mail.username =
mail.password =
```

To use spring-mail add following properties in application.properties 

```$xslt
# Gmail config
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=xxx@gmail.com
spring.mail.password=xxxx
spring.mail.properties.mail.smtp.auth = true
spring.mail.properties.mail.smtp.ssl.trust=smtp.gmail.com
spring.mail.properties.mail.smtp.connectiontimeout = 60000
spring.mail.properties.mail.smtp.timeout = 60000
spring.mail.properties.mail.smtp.writetimeout=5000

```
#### Documentation

Refer to this repository **Wiki** section.
https://github.com/Anar-Framework/anar-lib-mail/wiki