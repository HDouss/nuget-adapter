<img src="https://www.artipie.com/logo.svg" width="64px" height="64px"/>

[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/com.artipie/nuget-adapter/blob/master/LICENSE.txt)

This Java library turns your binary [ASTO](https://github.com/artipie/asto) 
storage into a NuGet repository.

Similar solutions:

  * [Artifactory](https://www.jfrog.com/confluence/display/RTF/NuGet+Repositories)
  * [NuGet](https://www.nuget.org/)

Some valuable references:

  * [NuGet Documentation](https://docs.microsoft.com/en-us/nuget/)
  * [NuGet Sources](https://github.com/NuGet)

## Getting started

Add dependency to your `pom.xml`:

```xml
<dependency>
  <groupId>com.artipie</groupId>
  <artifactId>nuget-adapter</artifactId>
  <version>[...]</version>
</dependency>
```

Save package file in `.nupkg` format to storage. 
Then, you make an instance of `Repository` class with your storage
as an argument.
Finally, you instruct `Repository` to add the package to repository:

```java
import com.artpie.nuget;
Repository repo = new Repository(storage);
repo.add(new Key.From("package.nupkg"));
```

## How to contribute

Fork repository, make changes, send us a pull request. We will review
your changes and apply them to the `master` branch shortly, provided
they don't violate our quality standards. To avoid frustration, before
sending us your pull request please run full Maven build:

```
$ mvn clean install -Pqulice
```

To avoid build errors use Maven 3.2+.
