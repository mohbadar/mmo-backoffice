### Jobs Module of ePayFrame

#### Usage Guide

```
		<dependency>
	               <groupId>af.gov.anar.lib</groupId>
	                <artifactId>anar-lib-lobs</artifactId>
                        <version>${project.version}</version>
		</dependency>

```


Create a file in classpath by name of `quartz.properties` and following properties

```properties
org.quartz.scheduler.instanceName=ANAR_SCHEDULER
org.quartz.scheduler.instanceId=AUTO
org.quartz.threadPool.threadCount=5
org.quartz.jobStore.class=org.quartz.impl.jdbcjobstore.JobStoreTX
org.quartz.jobStore.driverDelegateClass=org.quartz.impl.jdbcjobstore.PostgreSQLDelegate
org.quartz.jobStore.useProperties=true
org.quartz.jobStore.misfireThreshold=60000
org.quartz.jobStore.tablePrefix=quartz_db.qrtz_
org.quartz.jobStore.isClustered=false
org.quartz.plugin.shutdownHook.class=org.quartz.plugins.management.ShutdownHookPlugin
org.quartz.plugin.shutdownHook.cleanShutdown=TRUE
spring.quartz.properties.org.quartz.scheduler.instanceIdGenerator.class=af.gov.anar.lib.jobs.schedular.CustomQuartzInstanceIdGenerator

```


This module save config in database create a new schema   
 `quartz_db` and copy `DDL SQL Queries` from **wiki** section of this repository. 


#### Documentation

Refer to this repository **Wiki** section.
https://github.com/Anar-Framework/anar-lib-jobs/wiki