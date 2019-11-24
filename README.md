
Our foundation goal is to form a robust but flexible data model which is ready-made for extensions and customization. An API provides access to all basic functions grouped in modules.

## Main Advantages 

- Flexibility in Using Technologies (Programming language independence) <br/>
- Autonomous, Cross-functional Teams<br/>
- Easier to Build and Maintain Apps ( Save Resources)<br/>
- Easier system integration<br/>
- Improved Productivity and Speed<br/>
- Highly loosely coupled services, modules and systems<br/>
- Real-time analysis and reports<br/>
- and more<br/>

## Keywords
  - Tenant:  is a separate and distinct set of users within a multi-tenant database.  <br/>
  - Event: is an action or occurrence recognized by software. <br/>
  - Message:  is a discrete unit of data and metadata intended by the source for consumption by some recipient or group of recipients. <br/>

## Design Principles and Best Practices 

## Multi-tenancy in mind
There are many advantages to implementing a multi-tenant application environment. A multi-tenant application can provide savings by reducing development and deployment costs to companies that develop applications. These savings can be passed on to customers – increasing competitive advantages for all parties involved.
Savings created by multi-tenancy come from sharing the same resources with multiple tenants. Sharing resources provides a way for an application vendor to create and maintain resources once for all customers, which can result in significant savings.

In some special cases of tenant data access can be  as follows:
   -  Super-tenant users (e.g. Central) — Super-tenants users (or simply super tenants) can access all of the data in the database, typically for maintenance purposes.
   -  Tenant groups — Groups allow more than one tenant to access the same data for an instance of a table.

## Message Broker  for all internal and external data exchange 
A message broker (also known as an integration broker or interface engine) is an intermediary computer program module that translates a message from the formal messaging protocol of the sender to the formal messaging protocol of the receiver. We can achieve following benefits by using a message broker. 

  - Route messages to one or more destinations <br/>
  - Transform messages to an alternative representation <br/>
  - Perform message aggregation, decomposing messages into multiple messages and sending them to their destination, then recomposing the responses into one message to return to the user <br/>
  - Interact with an external repository to augment a message or store it <br/>
  - Invoke web services to retrieve data <br/>
  - Respond to events or errors <br/>
  - Provide content and topic-based message routing using the publish–subscribe pattern <br/>

Message brokers are generally based on one of two fundamental architectures: hub-and-spoke and message bus. In the first, a central server acts as the mechanism that provides integration services, whereas with the latter, the message broker is a communication backbone or distributed service that acts on the bus. Additionally, a more scalable multi-hub approach can be used to integrate multiple brokers

## NoSQL database at Storage Level 
We prefer NoSQL database for following reasons

- To store large volumes of data that might have little to no structure:  NoSQL databases do not limit the types of data that you can store together. NoSQL databases also enable you to add new data types as your needs change. With document-oriented databases, you can store data in one place without having to define the data type in advance. <br/>
- To speed development: When we are developing in rapid iterations or making frequent updates to the data structure, a relational database slows you down. However, because NoSQL data doesn’t need to be prepped ahead of time, you can make frequent updates to the data structure with minimal downtime. <br/>
- To boost horizontal scalability: The CAP (consistency, availability, and partition tolerance) theorem states that in any distributed system, only two of the three CAP properties can be used simultaneously. Adjusting these properties in favor of strong partition tolerance enables NoSQL users to boost horizontal scalability. <br/>

Note: we use SQL databases in case we have to or we need ACID (Atomicity, Consistency, Isolation, Durability)  for transaction processing. 

## Event Driven Design (EDD) at heart of architectural design
It  is a design pattern built around the production, detection, and reaction to events that take place in time. It is a design paradigm normalized for dynamic, asynchronous, process-oriented contexts. By using this pattern we can have following benefits:

   - Highly loosely coupled services, modules and systems <br/>
   - Autonomous components <br/>
   - Easy Integration  <br/>
   - Greater responsiveness because event-driven systems are, by design, normalized to unpredictable, nonlinear, and asynchronous environments.  <br/>
   - Real-time and streaming of events <br/>
   - Real-time analysis and reports <br/>

## Domain Driven Design (DDD) 
we will take a close look at the solutions we have built, decomposed the functionality, and rearranged them based on bounded contexts we have identified. Simply, we do not develop systems based on developers assumptions. 

Data Locality instead of loading huge data for computation 
Data locality is the process of moving the computation close to where the actual data resides on the node, instead of moving large data to computation. This minimizes network congestion and increases the overall throughput of the system.

Event Sourcing instead of having only the current application state. 
With event sourcing, instead of storing the “current” state of the entities that are used in our system, we store a stream of events that relate to these entities. Each event is a fact, it describes a state change that occurred to the entity (past tense!). As we all know, facts are indisputable and immutable.

Having a stream of such events it’s possible to find out what’s the current state of an entity by folding all events relating to that entity; note, however, that it’s not possible the other way round  when storing the “current” state only, we discard a lot of valuable historical information.

##  CQRS instead of direct read and write from single database
Every service employs the Command and Query Responsibility Segregation (CQRS) pattern and Event Sourcing to enable fast processing and data enhancement with a clean separation of concerns. CQRS segregates operations that read data from operations that update data by using separate interfaces.

This pattern can maximize performance, scalability, and security; support evolution of the system over time through higher flexibility; and prevent update commands from causing merge conflicts at the domain level. To allow scalability and ephemeral behavior every services is stateless, not storing any data or holding context related information, and supports multi-tenancy by default.

Authorization Server instead of implementation of Auth Layer in each application
An authorization server defines the security boundaries. Within each authorization server we can define our own  scopes, claims, and access policies.  This allows our apps and our APIs to anchor to a central authorization point and leverage the rich identity features of our identity management platform. 

## Resource Server for resource protection 
The resource server handles authenticated requests after the application has obtained an access token. The resource server will be getting requests from applications with an HTTP Authorization header containing an access token. The resource server needs to be able to verify the access token to determine whether to process the request or not. 

## Stateless authentication 
To maintain statelessness, and secure access at the same time, stateless authentication needs to  performed via JWT bearer tokens which need to be regularly refreshed.  The tokens should be signed via a tenant-specific private key and long-lived tokens are transmitted as secure cookies, thus limiting the possibilities for interception.

## Use Host Security Modules instead of manual crypto-management 
A host security module is a hardware/software that safeguards and manages digital keys for strong authentication and provides cryptoprocessing. 

## Service Architectural Style instead of monolithic architecture
The service architectural style is an approach to developing a single application as a suite of small services, each running in its own process and communicating with lightweight mechanisms, often an HTTP resource API. These services are built around business capabilities and independently deployable by fully automated deployment machinery.
