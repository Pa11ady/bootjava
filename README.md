# Lunch Voting System (TopJava Graduation Project)

## Technical Assignment

Design and implement a REST API using Spring Boot / Spring Data JPA (without frontend).

The task is to build a voting system for deciding where to have lunch.

---

## Business Requirements

### Users

There are 2 types of users:

- **ADMIN**
- **USER**

### Admin capabilities

- Create, update and delete restaurants
- Create, update and delete menu items for restaurants
- Each restaurant provides a **daily menu**
- Menu consists of **2–5 items** (dish name and price)
- Menu can be updated every day
- Previous menus are stored and not deleted

### User capabilities

- View restaurants with today’s menu
- Vote for a restaurant for today

### Voting rules

- Only **one vote per user per day**
- If user votes again **before 11:00** — vote is updated
- If user votes again **after 11:00** — vote cannot be changed
- Voting history is stored

---

## REST API

API is designed according to REST principles and TopJava conventions.

### User API


- Stack: [JDK 21](http://jdk.java.net/20/), Spring Boot 3.x, Lombok, H2, SpringDoc OpenApi 2.x
- Run: `mvn spring-boot:run` in root directory.
-----------------------------------------------------
[REST API documentation](http://localhost:8080/)

```
User:  user@yandex.ru / password
Admin: admin@gmail.com / admin
Guest: guest@gmail.com / guest
```
https://github.com/JavaOPs/topjava/blob/master/graduation.md/