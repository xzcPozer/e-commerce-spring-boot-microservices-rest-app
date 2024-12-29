# E-commerce-spring-boot-microservices-rest-app

Spring Boot e-commerce application with the ability CRUD operations for products, customers, orders and send notifications to email from Kafka-server topics.

## Technologies

- **Spring Boot 3**

- **Spring Cloud**

- **Spring Data JPA**

- **Spring Data MongoDB**

- **Spring Security**

- **Thymeleaf**

- **PostgreSQL**

- **MongoDB**

- **Eureka server**

- **Kafka**

- **Mailhog**

- **Zipkin**

- **Keycloak**

## Launch

In the root of the project, you need to run `docker-compose.yml`:
```bash
docker compose up -d
```
Then you can run microservices. The first should be `ConfigServerApplication`, then `DiscoveryApplication`, then the rest of the services and at the end `GatewayApplication`.

## Keycloak set up

create realm micro-services and create client with the following parameters:
![изображение](https://github.com/user-attachments/assets/5fd353a6-2095-4ccc-86b7-d9d117760009)

## Zipkin distributed tracing

Client not found error:
![изображение](https://github.com/user-attachments/assets/3038e253-c3ca-45e4-9909-d7b8d57531e3)

Client found and product insufficient stock quantity:
![изображение](https://github.com/user-attachments/assets/48739323-f3b4-4ac1-94e8-5c5e40bbad67)


Client found and products quantity is available:
![изображение](https://github.com/user-attachments/assets/69317625-e6c6-42eb-ae7c-8d1e0f642017)

The order is not created accordingly

## Order-service request

create order:

![изображение](https://github.com/user-attachments/assets/d6e40409-d2fc-4897-a20d-f7ac1808ecd2)

results:

![изображение](https://github.com/user-attachments/assets/8b21aab2-67e8-4549-9e43-7d8d0f64c9c7)

![изображение](https://github.com/user-attachments/assets/95c49093-f9c9-4d9d-af4a-ec009ef372e1)

get all orders:

![изображение](https://github.com/user-attachments/assets/9c919b81-4698-412a-90ed-555ce8192c6f)

get order by id:

![изображение](https://github.com/user-attachments/assets/0f174eab-537e-47ac-9629-cf2aeea860f5)

## Customer-service request

add customer:

![изображение](https://github.com/user-attachments/assets/45e588d6-fd34-482f-947b-7fdd56b86875)

get customer by id:

![изображение](https://github.com/user-attachments/assets/29f8eb56-df2f-4b37-bda0-74581d8ba789)

update customer by id:

![изображение](https://github.com/user-attachments/assets/744f3457-ee7a-487b-bd4e-e43143c4b39c)

delete customer by id:

![изображение](https://github.com/user-attachments/assets/2b2bbd6e-aa5a-418f-9fe5-428552b3c924)

## Product-service request

get all products by id:

![изображение](https://github.com/user-attachments/assets/b1cbe199-2df2-4307-8cd7-68ba249231e8)

get product by id:

![изображение](https://github.com/user-attachments/assets/da7f4686-3ac1-4a38-8fc7-e873e810c5fd)

create product:

![изображение](https://github.com/user-attachments/assets/5d7e164b-b127-4fa9-bc82-4dc4af6fa5e5)

## Sending messages

After create order request on customer email will send messages about order and payment products:

![изображение](https://github.com/user-attachments/assets/d6880800-bf9e-4ec5-b5e4-8d69b0d882c7)
![изображение](https://github.com/user-attachments/assets/7d9ee036-3de5-460f-8aa0-b4a9375f2e9e)
![изображение](https://github.com/user-attachments/assets/b3c8d9a6-e96c-4bf2-a300-0419668e81e0)




