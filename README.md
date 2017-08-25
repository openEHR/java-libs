# java-libs
Java openEHR Implementation project (ADL 1.4)

## Getting Started

### Requirements

* Java 8 or higher
* Maven 3.0.4 or higher

### Usage

The java-libs project is available at [Maven Central](http://search.maven.org/).

For example, if you need to use the adl-parser, add into your _pom.xml_:

```xml
<dependency>
    <groupId>org.openehr.java-libs</groupId>
    <artifactId>adl-parser</artifactId>
    <version>1.0.71</version>
</dependency>
```

### Installation

To build the whole project, first clone it, and once inside the project's folder (by default _java-libs_), run:
```bash
mvn clean install
```
This will create binary files in the _target_ directories of each submodule. 

