# Secured Soap Web Services in Mule Example

Small Mule application showing the use of Spring WS Security for a SOAP web service.

[![Release docs](https://img.shields.io/badge/docs-release-blue.svg)][site-release]
[![Development docs](https://img.shields.io/badge/docs-develop-blue.svg)][site-develop]

[![Release javadocs](https://img.shields.io/badge/javadocs-release-blue.svg)][javadoc-release]
[![Development javadocs](https://img.shields.io/badge/javadocs-develop-blue.svg)][javadoc-develop]

## Features

The project offers various examples showing how to set up the [WSS4J][wss4j] and [XWSS][xwss] [WS-Security][ws-security] implementations for the [Spring-WS][spring-ws] framework.

The following authentication methods, along a web service without any kind of authentication, are used:

- Plain password.
- Digested password.
- Signature.
- Encryption.

Each of them is applied to a different WS.

Additionally, several tests making sure that the web services work as intended.

## Documentation

Documentation is always generated for the latest release, kept in the 'master' branch:

- The [latest release documentation page][site-release].
- The [the latest release Javadoc site][javadoc-release].

Documentation is also generated from the latest snapshot, taken from the 'develop' branch:

- The [the latest snapshot documentation page][site-develop].
- The [the latest snapshot Javadoc site][javadoc-develop].

The documentation site sources come along the source code (as it is a Maven site), so it is always possible to generate them using the following Maven command:

```
$ mvn verify site
```

The verify phase is required, as otherwise some of the reports won't be created.

## Usage

The application is coded in Java, using Maven to manage the project.

### Prerequisites

The project has been tested on the following Java versions:
* JDK 7
* OpenJDK 7

All other dependencies are handled through Maven, and noted in the included POM file. This includes the plugins used for running the demo in an embedded web server.

### Running the demo

To run the demo just use the Anypoint Studio run option.

### URLs for the web services

By default the web services will be deployed to the following URL:

```
http://localhost:8080/swss
```

#### Local SOAP web service

The following URLs allow using the various implementations of the SOAP service:

```
http://localhost:8080/swss/unsecure/code_first?wsdl
http://localhost:8080/swss/unsecure/wsdl_first?wsdl
http://localhost:8080/swss/unsecure/simple?wsdl
http://localhost:8080/swss/unsecure/proxy?wsdl
http://localhost:8080/swss/unsecure/consumer?wsdl
```

#### Local web service client

The following URLs allow making use of the HTTP client for the local web service:

```
http://localhost:8080/swss/client/unsecure/code_first?cod1=1&cod2=2.1
http://localhost:8080/swss/client/unsecure/wsdl_first?cod1=1&cod2=2.1
http://localhost:8080/swss/client/unsecure/simple?cod1=1&cod2=2.1
http://localhost:8080/swss/client/unsecure/consumer?cod1=1&cod2=2.1
```

#### Local secure SOAP web service

The following URLs allow using the various implementations of the SOAP service:

```
http://localhost:8080/swss/secure/username?wsdl
http://localhost:8080/swss/secure/signed?wsdl
http://localhost:8080/swss/secure/encrypted?wsdl
```

#### Local secure web service client

The following URLs allow making use of the HTTP client for the local web service:

```
http://localhost:8080/swss/client/secure/username?cod1=1&cod2=2.1
http://localhost:8080/swss/client/secure/signed?cod1=1&cod2=2.1
http://localhost:8080/swss/client/secure/encrypted?cod1=1&cod2=2.1

http://localhost:8080/swss/client/secure/consumer/username?cod1=1&cod2=2.1
http://localhost:8080/swss/client/secure/consumer/signed?cod1=1&cod2=2.1
http://localhost:8080/swss/client/secure/consumer/encrypted?cod1=1&cod2=2.1
```

#### Remote web service proxy

The following URL allows making use of the remote web service:

```
http://localhost:8080/swss/remote/proxy?wsdl
http://localhost:8080/swss/remote/consumer?wsdl
```

#### Remote web service client

The following URLs allow making use of the HTTP client for a remote web service:

```
http://localhost:8080/swss/client/remote?zip=11222
http://localhost:8080/swss/client/remote/consumer?zip=11222
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
