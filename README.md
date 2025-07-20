1: hibernate serializable deserialization
2: @version - use (optimistic locking)

3: key clock

http://localhost:8080

docker run -d \
--name keycloak \
-e KEYCLOAK_ADMIN=admin \
-e KEYCLOAK_ADMIN_PASSWORD=admin \
-p 8080:8080 \
quay.io/keycloak/keycloak:24.0.1 \
start-dev

## still working

4: connection pooling 2 impl

basic spring security

curl --location 'localhost:8085/hello' \
--header 'Authorization: Basic dGVzdDE6MTIzNDU=' \
--header 'Cookie: JSESSIONID=5110A493121C75DAE5AA56A1FE192754'

security filter

1: [Filter chain](./Docs/security-filter.md)

https://www.youtube.com/watch?v=dFvbHZ8CuKM&list=PLEocw3gLFc8X_a8hGWGaBnSkPFJmbb8QP&index=2


https://docs.spring.io/spring-security/reference/servlet/architecture.html