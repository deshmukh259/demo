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
