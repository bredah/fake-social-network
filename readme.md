# Social Network

## Command

- Run app

```sh
make start-api
```

## Test

Run single test class

```sh
# Test Class
mvn test -Dtest=ClassName
# Test Method
mvn test -Dtest=ClassName#MethodName
# Test TAG
mvn test -Dgroups=TAG
# Maven Module
mvn test -pl :web
```

> This command defaults to the `dev` profile in Spring Boot

## More details

- [Data Base](./extra/doc/data-base.md)
- [Debug](./extra/doc/debug.md)

