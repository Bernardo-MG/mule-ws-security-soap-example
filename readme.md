# Mule WS-Security Example

Small Mule application showing the use of WS-Security for SOAP web services.

## Features

The project offers various examples showing how to set up the [WSS4J][wss4j] and [XWSS][xwss] [WS-Security][ws-security] implementations for the [Spring-WS][spring-ws] framework.

The following authentication methods, along a web service without any kind of authentication, are used:

- Plain password.
- Signature.
- Encrypted message.

Each of them is applied to a different WS.

Additionally, several tests making sure that the web services work as intended.

## Usage

The application is coded in Java, using Maven to manage the project.

### Prerequisites

The project has been tested on the following Java versions:
* JDK 8
* JDK 7
* OpenJDK 7

All other dependencies are handled through Maven, and noted in the included POM file. This includes the plugins used for running the demo in an embedded web server.

### Running the demo

To run the demo just use the Anypoint Studio run option.

### URLs for the web services

By default the web services will be deployed to the following URL:

```
http://localhost:8080/mule-wss
```

#### SOAP web service

The following URLs allow using the various implementations of the SOAP service:

```
http://localhost:8080/mule-wss/unsecure/code_first?wsdl
http://localhost:8080/mule-wss/unsecure/wsdl_first?wsdl
http://localhost:8080/mule-wss/unsecure/simple?wsdl
http://localhost:8080/mule-wss/unsecure/proxy?wsdl
```

```
http://localhost:8080/mule-wss/password/code_first?wsdl
http://localhost:8080/mule-wss/password/wsdl_first?wsdl
http://localhost:8080/mule-wss/password/simple?wsdl
http://localhost:8080/mule-wss/password/proxy?wsdl
```

```
http://localhost:8080/mule-wss/signature/code_first?wsdl
http://localhost:8080/mule-wss/signature/wsdl_first?wsdl
http://localhost:8080/mule-wss/signature/simple?wsdl
http://localhost:8080/mule-wss/signature/proxy?wsdl
```

```
http://localhost:8080/mule-wss/encryption/code_first?wsdl
http://localhost:8080/mule-wss/encryption/wsdl_first?wsdl
http://localhost:8080/mule-wss/encryption/simple?wsdl
http://localhost:8080/mule-wss/encryption/proxy?wsdl
```

The endpoint consumer does not support querying for the WSDl.

#### Web service client

The following URLs allow making use of the HTTP client for the local web service:

```
http://localhost:8080/mule-wss/client/unsecure/code_first?id=1
http://localhost:8080/mule-wss/client/unsecure/wsdl_first?id=1
http://localhost:8080/mule-wss/client/unsecure/simple?id=1
http://localhost:8080/mule-wss/client/unsecure/proxy?id=1
http://localhost:8080/mule-wss/client/unsecure/consumer?id=1
```

```
http://localhost:8080/mule-wss/client/password/code_first?id=1
http://localhost:8080/mule-wss/client/password/wsdl_first?id=1
http://localhost:8080/mule-wss/client/password/simple?id=1
http://localhost:8080/mule-wss/client/password/proxy?id=1
http://localhost:8080/mule-wss/client/password/consumer?id=1
```

```
http://localhost:8080/mule-wss/client/signature/code_first?id=1
http://localhost:8080/mule-wss/client/signature/wsdl_first?id=1
http://localhost:8080/mule-wss/client/signature/simple?id=1
http://localhost:8080/mule-wss/client/signature/proxy?id=1
http://localhost:8080/mule-wss/client/signature/consumer?id=1
```

```
http://localhost:8080/mule-wss/client/encryption/code_first?id=1
http://localhost:8080/mule-wss/client/encryption/wsdl_first?id=1
http://localhost:8080/mule-wss/client/encryption/simple?id=1
http://localhost:8080/mule-wss/client/encryption/proxy?id=1
http://localhost:8080/mule-wss/client/encryption/consumer?id=1
```

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

[issues]: https://github.com/bernardo-mg/mule-wss-soap-example/issues
[javadoc-develop]: http://docs.wandrell.com/maven/mule-wss-soap-example/apidocs
[javadoc-release]: http://docs.wandrell.com/development/maven/mule-wss-soap-example/apidocs
[license]: http://www.opensource.org/licenses/mit-license.php
[scm]: https://github.com/bernardo-mg/spring-ws-security-soap-example
[site-develop]: http://docs.wandrell.com/development/maven/mule-wss-soap-example
[site-release]: http://docs.wandrell.com/maven/mule-wss-soap-example

[ws-security]: https://www.oasis-open.org/committees/wss/
[xwss]: https://docs.oracle.com/cd/E17802_01/webservices/webservices/docs/1.6/tutorial/doc/XWS-SecurityIntro4.html
[wss4j]: https://ws.apache.org/wss4j/

[spring-ws]: http://projects.spring.io/spring-ws/
