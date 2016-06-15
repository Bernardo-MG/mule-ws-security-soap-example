# Mule SOAP Web Services WS-Security Example

Maven-based [Mule][mule] application showcasing the configuration of secured SOAP web services.

Mule is an Enterprise Service Bus, meant to connect together online applications. This example just touches an specific part of the web services support it offers, to be precise the security layer, and is prepared for the Community Edition.

It includes an endpoint for each type of endpoint Mule supports, which are four ways of implementing a CXF endpoint and an endpoint consumer. To these all the possible security protocols (unsecured, password, signed and encrypted) are applied.

It is meant to be just an example, and for that reason is kept as simple as possible, avoiding the use of external web services. Still it tries to show how a real application would be built, and is divided into layers, including services and persistence, created with the help of Spring.

## Features

The project offers various examples showing how to set up the various security options offered by Mule:

- Unsecured
- Password protected
- Password protected with SAML
- Signed
- Encrypted

These are applied to the different web services implementations which Mule supports:

- CXF with a code-first JAX-WS frontend, created from custom JAXB classes
- CXF with a WSDL-first JAX-WS frontend, created from generated JAXB classes
- CXF with a plain frontend, created from POJOs
- CXF with a proxy frontend, which handles plain SOAP messages instead of beans
- Consumer, which takes the body of a SOAP message and transforms it for the other services

All the endpoints receive a request, try to acquire an object from the persistence layer and then return a response with it.

For each of them a HTTP client is included.

There are also several tests ensuring that the flows and endpoints work as expected.

## Usage

The application is coded in Java, using Maven to manage the project.

### Prerequisites

The project has been tested on the following Java versions:
* JDK 8
* JDK 7
* OpenJDK 7

As it is a Mule project, it also requires the following Mule version:
* Mule 3.7.0CE

All other dependencies are handled through Maven, and noted in the included POM file. This includes the plugins used for running the demo in an embedded web server.

### Running the demo

The easiest way to run the demo is using the Anypoint Studio run option.

### URLs for the web services

By default the web services will be deployed to the following URL:

```
http://localhost:8080/mule-wss
```

#### SOAP web service

The following URLs allow using the various implementations of the SOAP service.

The endpoint consumer does not support querying for the WSDL, and only accepts the body of a SOAP message.

|Authentication method|Endpoint type|URL|
|:-:|:-:|:-:|
|Unsecure|Code-First|[http://localhost:8080/mule-wss/cxf/unsecure/code_first?wsdl](http://localhost:8080/mule-wss/cxf/unsecure/code_first?wsdl)|
|Unsecure|WSDL-First|[http://localhost:8080/mule-wss/cxf/unsecure/wsdl_first?wsdl](http://localhost:8080/mule-wss/cxf/unsecure/wsdl_first?wsdl)|
|Unsecure|Simple|[http://localhost:8080/mule-wss/cxf/unsecure/simple?wsdl](http://localhost:8080/mule-wss/cxf/unsecure/simple?wsdl)|
|Unsecure|Proxy|[http://localhost:8080/mule-wss/cxf/unsecure/proxy?wsdl](http://localhost:8080/mule-wss/cxf/unsecure/proxy?wsdl)|
|Unsecure|Consumer|[http://localhost:8080/mule-wss/consumer/unsecure](http://localhost:8080/mule-wss/consumer/unsecure)|
|Password|Code-First|[http://localhost:8080/mule-wss/cxf/password/code_first?wsdl](http://localhost:8080/mule-wss/cxf/password/code_first?wsdl)|
|Password|WSDL-First|[http://localhost:8080/mule-wss/cxf/password/wsdl_first?wsdl](http://localhost:8080/mule-wss/cxf/password/wsdl_first?wsdl)|
|Password|Simple|[http://localhost:8080/mule-wss/cxf/password/simple?wsdl](http://localhost:8080/mule-wss/cxf/password/simple?wsdl)|
|Password|Proxy|[http://localhost:8080/mule-wss/cxf/password/proxy?wsdl](http://localhost:8080/mule-wss/cxf/password/proxy?wsdl)|
|Password|Consumer|[http://localhost:8080/mule-wss/consumer/password](http://localhost:8080/mule-wss/consumer/password)|
|Password with SAML|Code-First|[http://localhost:8080/mule-wss/cxf/password/saml/code_first?wsdl](http://localhost:8080/mule-wss/cxf/password/saml/code_first?wsdl)|
|Password with SAML|WSDL-First|[http://localhost:8080/mule-wss/cxf/password/saml/wsdl_first?wsdl](http://localhost:8080/mule-wss/cxf/password/saml/wsdl_first?wsdl)|
|Password with SAML|Simple|[http://localhost:8080/mule-wss/cxf/password/saml/simple?wsdl](http://localhost:8080/mule-wss/cxf/password/saml/simple?wsdl)|
|Password with SAML|Proxy|[http://localhost:8080/mule-wss/cxf/password/saml/proxy?wsdl](http://localhost:8080/mule-wss/cxf/password/saml/proxy?wsdl)|
|Password with SAML|Consumer|[http://localhost:8080/mule-wss/consumer/password/saml](http://localhost:8080/mule-wss/consumer/password/saml)|
|Signature|Code-First|[http://localhost:8080/mule-wss/cxf/signature/code_first?wsdl](http://localhost:8080/mule-wss/cxf/signature/code_first?wsdl)|
|Signature|WSDL-First|[http://localhost:8080/mule-wss/cxf/signature/wsdl_first?wsdl](http://localhost:8080/mule-wss/cxf/signature/wsdl_first?wsdl)|
|Signature|Simple|[http://localhost:8080/mule-wss/cxf/signature/simple?wsdl](http://localhost:8080/mule-wss/cxf/signature/simple?wsdl)|
|Signature|Proxy|[http://localhost:8080/mule-wss/cxf/signature/proxy?wsdl](http://localhost:8080/mule-wss/cxf/signature/proxy?wsdl)|
|Signature|Consumer|[http://localhost:8080/mule-wss/consumer/signature](http://localhost:8080/mule-wss/consumer/signature)|
|Encryption|Code-First|[http://localhost:8080/mule-wss/cxf/encryption/code_first?wsdl](http://localhost:8080/mule-wss/cxf/encryption/code_first?wsdl)|
|Encryption|WSDL-First|[http://localhost:8080/mule-wss/cxf/encryption/wsdl_first?wsdl](http://localhost:8080/mule-wss/cxf/encryption/wsdl_first?wsdl)|
|Encryption|Simple|[http://localhost:8080/mule-wss/cxf/encryption/simple?wsdl](http://localhost:8080/mule-wss/cxf/encryption/simple?wsdl)|
|Encryption|Proxy|[http://localhost:8080/mule-wss/cxf/encryption/proxy?wsdl](http://localhost:8080/mule-wss/cxf/encryption/proxy?wsdl)|
|Encryption|Consumer|[http://localhost:8080/mule-wss/consumer/encryption](http://localhost:8080/mule-wss/consumer/encryption)|

#### Web service client

The following URLs allow making use of the HTTP client for the local web service.

To change the entity queried just change the id value on the URL.

|Authentication method|Endpoint type|URL|
|:-:|:-:|:-:|
|Unsecure|Code-First|[http://localhost:8080/mule-wss/client/cxf/unsecure/code_first?id=1](http://localhost:8080/mule-wss/client/cxf/unsecure/code_first?id=1)|
|Unsecure|WSDL-First|[http://localhost:8080/mule-wss/client/cxf/unsecure/wsdl_first?id=1](http://localhost:8080/mule-wss/client/cxf/unsecure/wsdl_first?id=1)|
|Unsecure|Simple|[http://localhost:8080/mule-wss/client/cxf/unsecure/simple?id=1](http://localhost:8080/mule-wss/client/cxf/unsecure/simple?id=1)|
|Unsecure|Proxy|[http://localhost:8080/mule-wss/client/cxf/unsecure/proxy?id=1](http://localhost:8080/mule-wss/client/cxf/unsecure/proxy?id=1)|
|Unsecure|Consumer|[http://localhost:8080/mule-wss/client/consumer/unsecure?id=1](http://localhost:8080/mule-wss/client/consumer/unsecure?id=1)|
|Password|Code-First|[http://localhost:8080/mule-wss/client/cxf/password/code_first?id=1](http://localhost:8080/mule-wss/client/cxf/password/code_first?id=1)|
|Password|WSDL-First|[http://localhost:8080/mule-wss/client/cxf/password/wsdl_first?id=1](http://localhost:8080/mule-wss/client/cxf/password/wsdl_first?id=1)|
|Password|Simple|[http://localhost:8080/mule-wss/client/cxf/password/simple?id=1](http://localhost:8080/mule-wss/client/cxf/password/simple?id=1)|
|Password|Proxy|[http://localhost:8080/mule-wss/client/cxf/password/proxy?id=1](http://localhost:8080/mule-wss/client/cxf/password/proxy?id=1)|
|Password|Consumer|[http://localhost:8080/mule-wss/client/consumer/password?id=1](http://localhost:8080/mule-wss/client/consumer/password?id=1)|
|Password with SAML|Code-First|[http://localhost:8080/mule-wss/client/cxf/password/saml/code_first?id=1](http://localhost:8080/mule-wss/client/cxf/password/saml/code_first?id=1)|
|Password with SAML|WSDL-First|[http://localhost:8080/mule-wss/client/cxf/password/saml/wsdl_first?id=1](http://localhost:8080/mule-wss/client/cxf/password/saml/wsdl_first?id=1)|
|Password with SAML|Simple|[http://localhost:8080/mule-wss/client/cxf/password/saml/simple?id=1](http://localhost:8080/mule-wss/client/cxf/password/saml/simple?id=1)|
|Password with SAML|Proxy|[http://localhost:8080/mule-wss/client/cxf/password/saml/proxy?id=1](http://localhost:8080/mule-wss/client/cxf/password/saml/proxy?id=1)|
|Password with SAML|Consumer|[http://localhost:8080/mule-wss/client/consumer/password/saml?id=1](http://localhost:8080/mule-wss/client/consumer/password/saml?id=1)|
|Signature|Code-First|[http://localhost:8080/mule-wss/client/signature/cxf/code_first?id=1](http://localhost:8080/mule-wss/client/cxf/signature/code_first?id=1)|
|Signature|WSDL-First|[http://localhost:8080/mule-wss/client/signature/cxf/wsdl_first?id=1](http://localhost:8080/mule-wss/client/cxf/signature/wsdl_first?id=1)|
|Signature|Simple|[http://localhost:8080/mule-wss/client/signature/cxf/simple?id=1](http://localhost:8080/mule-wss/client/cxf/signature/simple?id=1)|
|Signature|Proxy|[http://localhost:8080/mule-wss/client/signature/cxf/proxy?id=1](http://localhost:8080/mule-wss/client/cxf/signature/proxy?id=1)|
|Signature|Consumer|[http://localhost:8080/mule-wss/client/consumer/signature?id=1](http://localhost:8080/mule-wss/client/consumer/signature?id=1)|
|Encryption|Code-First|[http://localhost:8080/mule-wss/client/cxf/encryption/code_first?id=1](http://localhost:8080/mule-wss/client/cxf/encryption/code_first?id=1)|
|Encryption|WSDL-First|[http://localhost:8080/mule-wss/client/cxf/encryption/wsdl_first?id=1](http://localhost:8080/mule-wss/client/cxf/encryption/wsdl_first?id=1)|
|Encryption|Simple|[http://localhost:8080/mule-wss/client/cxf/encryption/simple?id=1](http://localhost:8080/mule-wss/client/cxf/encryption/simple?id=1)|
|Encryption|Proxy|[http://localhost:8080/mule-wss/client/cxf/encryption/proxy?id=1](http://localhost:8080/mule-wss/client/cxf/encryption/proxy?id=1)|
|Encryption|Consumer|[http://localhost:8080/mule-wss/client/consumer/encryption?id=1](http://localhost:8080/mule-wss/client/consumer/encryption?id=1)|

## Collaborate

Any kind of help with the project will be well received, and there are two main ways to give such help:

- Reporting errors and asking for extensions through the issues management
- or forking the repository and extending the project

### Issues management

Issues are managed at the GitHub [project issues tracker][issues], where any Github user may report bugs or ask for new features.

### Getting the code

If you wish to fork or modify the code, visit the [GitHub project page][scm], where the latest versions are always kept. Check the 'master' branch for the latest release, and the 'develop' for the current, and stable, development version.

## License

The project has been released under the [MIT License][license].

[issues]: https://github.com/bernardo-mg/mule-ws-security-soap-example/issues
[license]: http://www.opensource.org/licenses/mit-license.php
[scm]: https://github.com/bernardo-mg/spring-ws-security-soap-example

[mule]: https://www.mulesoft.com
