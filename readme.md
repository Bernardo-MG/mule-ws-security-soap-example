# Mule SOAP Web Services WS-Security Example

Maven-based [Mule][mule] application showcasing the configuration of secured SOAP web services.

Mule is an Enterprise Service Bus, meant to connect together online applications. This example just touches an specific part of the web services support it offers, to be precise the security layer.

It includes an endpoint for each type of endpoint Mule supports (code-first, WSDL-first, simple, proxy and consumer), and to these all the possible security protocols (unsecured, password, signed and encrypted) are applied.

It is meant to be just an example, and for that reason is kept as simple as possible, avoiding the use of external web services. Still it tries to show how a real application would be built.

## Features

The project offers various examples showing how to set up the various security options offered by Mule:

- Unsecured
- Password protected
- Password protected with SAML
- Signed
- Encrypted

These are applied to the different web services implementations which Mule supports:

- Code-first
- WSDL-first
- Plain
- Proxy
- Consumer

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

It also requires a Mule runtime environment. For development this can be taken care by the [Anypoint Studio][anypoint-studio].

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
|Unsecure|Code-First|[http://localhost:8080/mule-wss/unsecure/code_first?wsdl](http://localhost:8080/mule-wss/unsecure/code_first?wsdl)|
|Unsecure|WSDL-First|[http://localhost:8080/mule-wss/unsecure/wsdl_first?wsdl](http://localhost:8080/mule-wss/unsecure/wsdl_first?wsdl)|
|Unsecure|Simple|[http://localhost:8080/mule-wss/unsecure/simple?wsdl](http://localhost:8080/mule-wss/unsecure/simple?wsdl)|
|Unsecure|Proxy|[http://localhost:8080/mule-wss/unsecure/proxy?wsdl](http://localhost:8080/mule-wss/unsecure/proxy?wsdl)|
|Unsecure|Consumer|[http://localhost:8080/mule-wss/unsecure/consumer](http://localhost:8080/mule-wss/unsecure/consumer)|
|Password|Code-First|[http://localhost:8080/mule-wss/password/code_first?wsdl](http://localhost:8080/mule-wss/password/code_first?wsdl)|
|Password|WSDL-First|[http://localhost:8080/mule-wss/password/wsdl_first?wsdl](http://localhost:8080/mule-wss/password/wsdl_first?wsdl)|
|Password|Simple|[http://localhost:8080/mule-wss/password/simple?wsdl](http://localhost:8080/mule-wss/password/simple?wsdl)|
|Password|Proxy|[http://localhost:8080/mule-wss/password/proxy?wsdl](http://localhost:8080/mule-wss/password/proxy?wsdl)|
|Password|Consumer|[http://localhost:8080/mule-wss/password/consumer](http://localhost:8080/mule-wss/password/consumer)|
|Password with SAML|Code-First|[http://localhost:8080/mule-wss/password/saml/code_first?wsdl](http://localhost:8080/mule-wss/password/saml/code_first?wsdl)|
|Password with SAML|WSDL-First|[http://localhost:8080/mule-wss/password/saml/wsdl_first?wsdl](http://localhost:8080/mule-wss/password/saml/wsdl_first?wsdl)|
|Password with SAML|Simple|[http://localhost:8080/mule-wss/password/saml/simple?wsdl](http://localhost:8080/mule-wss/password/saml/simple?wsdl)|
|Password with SAML|Proxy|[http://localhost:8080/mule-wss/password/saml/proxy?wsdl](http://localhost:8080/mule-wss/password/saml/proxy?wsdl)|
|Password with SAML|Consumer|[http://localhost:8080/mule-wss/password/saml/consumer](http://localhost:8080/mule-wss/password/saml/consumer)|
|Signature|Code-First|[http://localhost:8080/mule-wss/signature/code_first?wsdl](http://localhost:8080/mule-wss/signature/code_first?wsdl)|
|Signature|WSDL-First|[http://localhost:8080/mule-wss/signature/wsdl_first?wsdl](http://localhost:8080/mule-wss/signature/wsdl_first?wsdl)|
|Signature|Simple|[http://localhost:8080/mule-wss/signature/simple?wsdl](http://localhost:8080/mule-wss/signature/simple?wsdl)|
|Signature|Proxy|[http://localhost:8080/mule-wss/signature/proxy?wsdl](http://localhost:8080/mule-wss/signature/proxy?wsdl)|
|Signature|Consumer|[http://localhost:8080/mule-wss/signature/consumer](http://localhost:8080/mule-wss/signature/consumer)|
|Encryption|Code-First|[http://localhost:8080/mule-wss/encryption/code_first?wsdl](http://localhost:8080/mule-wss/encryption/code_first?wsdl)|
|Encryption|WSDL-First|[http://localhost:8080/mule-wss/encryption/wsdl_first?wsdl](http://localhost:8080/mule-wss/encryption/wsdl_first?wsdl)|
|Encryption|Simple|[http://localhost:8080/mule-wss/encryption/simple?wsdl](http://localhost:8080/mule-wss/encryption/simple?wsdl)|
|Encryption|Proxy|[http://localhost:8080/mule-wss/encryption/proxy?wsdl](http://localhost:8080/mule-wss/encryption/proxy?wsdl)|
|Encryption|Consumer|[http://localhost:8080/mule-wss/encryption/consumer](http://localhost:8080/mule-wss/encryption/consumer)|

#### Web service client

The following URLs allow making use of the HTTP client for the local web service.

To change the entity queried just change the id value on the URL.

|Authentication method|Endpoint type|URL|
|:-:|:-:|:-:|
|Unsecure|Code-First|[http://localhost:8080/mule-wss/client/unsecure/code_first?id=1](http://localhost:8080/mule-wss/client/unsecure/code_first?id=1)|
|Unsecure|WSDL-First|[http://localhost:8080/mule-wss/client/unsecure/wsdl_first?id=1](http://localhost:8080/mule-wss/client/unsecure/wsdl_first?id=1)|
|Unsecure|Simple|[http://localhost:8080/mule-wss/client/unsecure/simple?id=1](http://localhost:8080/mule-wss/client/unsecure/simple?id=1)|
|Unsecure|Proxy|[http://localhost:8080/mule-wss/client/unsecure/proxy?id=1](http://localhost:8080/mule-wss/client/unsecure/proxy?id=1)|
|Unsecure|Consumer|[http://localhost:8080/mule-wss/client/unsecure/consumer?id=1](http://localhost:8080/mule-wss/client/unsecure/consumer?id=1)|
|Password|Code-First|[http://localhost:8080/mule-wss/client/password/code_first?id=1](http://localhost:8080/mule-wss/client/password/code_first?id=1)|
|Password|WSDL-First|[http://localhost:8080/mule-wss/client/password/wsdl_first?id=1](http://localhost:8080/mule-wss/client/password/wsdl_first?id=1)|
|Password|Simple|[http://localhost:8080/mule-wss/client/password/simple?id=1](http://localhost:8080/mule-wss/client/password/simple?id=1)|
|Password|Proxy|[http://localhost:8080/mule-wss/client/password/proxy?id=1](http://localhost:8080/mule-wss/client/password/proxy?id=1)|
|Password|Consumer|[http://localhost:8080/mule-wss/client/password/consumer?id=1](http://localhost:8080/mule-wss/client/password/consumer?id=1)|
|Password with SAML|Code-First|[http://localhost:8080/mule-wss/client/password/saml/code_first?id=1](http://localhost:8080/mule-wss/client/password/saml/code_first?id=1)|
|Password with SAML|WSDL-First|[http://localhost:8080/mule-wss/client/password/saml/wsdl_first?id=1](http://localhost:8080/mule-wss/client/password/saml/wsdl_first?id=1)|
|Password with SAML|Simple|[http://localhost:8080/mule-wss/client/password/saml/simple?id=1](http://localhost:8080/mule-wss/client/password/saml/simple?id=1)|
|Password with SAML|Proxy|[http://localhost:8080/mule-wss/client/password/saml/proxy?id=1](http://localhost:8080/mule-wss/client/password/saml/proxy?id=1)|
|Password with SAML|Consumer|[http://localhost:8080/mule-wss/client/password/saml/consumer?id=1](http://localhost:8080/mule-wss/client/password/saml/consumer?id=1)|
|Signature|Code-First|[http://localhost:8080/mule-wss/client/signature/code_first?id=1](http://localhost:8080/mule-wss/client/signature/code_first?id=1)|
|Signature|WSDL-First|[http://localhost:8080/mule-wss/client/signature/wsdl_first?id=1](http://localhost:8080/mule-wss/client/signature/wsdl_first?id=1)|
|Signature|Simple|[http://localhost:8080/mule-wss/client/signature/simple?id=1](http://localhost:8080/mule-wss/client/signature/simple?id=1)|
|Signature|Proxy|[http://localhost:8080/mule-wss/client/signature/proxy?id=1](http://localhost:8080/mule-wss/client/signature/proxy?id=1)|
|Signature|Consumer|[http://localhost:8080/mule-wss/client/signature/consumer?id=1](http://localhost:8080/mule-wss/client/signature/consumer?id=1)|
|Encryption|Code-First|[http://localhost:8080/mule-wss/client/encryption/code_first?id=1](http://localhost:8080/mule-wss/client/encryption/code_first?id=1)|
|Encryption|WSDL-First|[http://localhost:8080/mule-wss/client/encryption/wsdl_first?id=1](http://localhost:8080/mule-wss/client/encryption/wsdl_first?id=1)|
|Encryption|Simple|[http://localhost:8080/mule-wss/client/encryption/simple?id=1](http://localhost:8080/mule-wss/client/encryption/simple?id=1)|
|Encryption|Proxy|[http://localhost:8080/mule-wss/client/encryption/proxy?id=1](http://localhost:8080/mule-wss/client/encryption/proxy?id=1)|
|Encryption|Consumer|[http://localhost:8080/mule-wss/client/encryption/consumer?id=1](http://localhost:8080/mule-wss/client/encryption/consumer?id=1)|

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

[anypoint-studio]: https://www.mulesoft.com/platform/studio
[mule]: https://www.mulesoft.com
