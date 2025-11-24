# 🧵 Artesanos Platform – Gestión de Artesanos y Productos

## 📌 Descripción del Proyecto

Artesanos Platform es una aplicación web diseñada para apoyar a los artesanos colombianos en la gestión de sus productos, inventario y presencia digital.
La plataforma proporciona una API REST que permite registrar artesanos, administrar productos y consultar información relevante de forma sencilla.

Este proyecto hace parte del curso de Diseño y Arquitectura de Software.


## 🧱Arquitectura del Sistema

Backend: Spring Boot (Java)
Security: Spring Security + Basic Auth
Base de Datos: H2 / MySQL (según configuración)
Testing: JUnit, Mockito, Postman/Newman, JMeter
Front (opcional): No incluido aún
Build Tool: Maven


## 🚀 Características principales

✔ Gestión de Artesanos (CRUD)
Crear artesanos
Obtener todos los artesanos
Obtener artesano por ID
Actualizar artesano
Eliminar artesano

✔ Gestión de Productos 

✔ Seguridad con Spring Security
Endpoints /api/artesanos/** requieren autenticación
Usuario por defecto:
```
user: admin
password: admin123
```
## 📂 Estructura del Proyecto
```
src/
 ├── main/
 │   ├── java/com.artesanos.artesanos/
 │   │    ├── controller/
 │   │    ├── service/
 │   │    ├── repository/
 │   │    ├── model/
 │   │    └── config/
 │   └── resources/
 │        ├── application.properties
 │        └── data.sql (opcional)
 └── test/
      ├── unit/
      └── integration/
```

## Diagrama de Arquitectura 
```
                    ┌─────────────────────────────┐
                    │          CLIENTE            │
                    │  Navegador / Postman / App  │
                    └───────────────┬─────────────┘
                                    │ HTTP/JSON
                                    ▼
                    ┌─────────────────────────────┐
                    │        API REST (Spring)    │
                    │  Controladores:             │
                    │   - ArtesanoController      │
                    │   - ProductoController      │
                    └───────────────┬─────────────┘
                                    │ Llama métodos del servicio
                                    ▼
                    ┌─────────────────────────────┐
                    │          SERVICIOS           │
                    │  ArtesanoService             │
                    │  ProductoService             │
                    │  (Reglas de negocio)         │
                    └───────────────┬─────────────┘
                                    │ Interacción con repositorio
                                    ▼
                    ┌─────────────────────────────┐
                    │          REPOSITORIOS       │
                    │  ArtesanoRepository         │
                    │  ProductoRepository         │
                    │  (Spring Data JPA)          │
                    └───────────────┬─────────────┘
                                    │ ORM (JPA/Hibernate)
                                    ▼
                    ┌─────────────────────────────┐
                    │         BASE DE DATOS        │
                    │     H2 / MySQL (según uso)   │
                    └──────────────────────────────┘


                    ┌─────────────────────────────┐
                    │      Seguridad (Spring)     │
                    │    - Basic Auth             │
                    │    - InMemoryUserDetails    │
                    │ Filtra accesos a /artesanos │
                    └─────────────────────────────┘

```

## 🔧 Instalación y Ejecución

1️⃣ Clonar el repositorio
```
git clone https://github.com/usuario/tu-repo.git
cd tu-repo
```

2️⃣ Ejecutar la aplicación
```
mvn spring-boot:run
```

3️⃣ Acceder al servidor
```
http://localhost:8080
```

## 🔐 Autenticación

Este proyecto usa Basic Auth.

Credenciales por defecto:
```
username: admin
password: admin123
```

## 🛣 Endpoints principales

🔹 Artesanos
```
Método	Endpoint	Descripción
GET	/api/artesanos	Obtener todos
GET	/api/artesanos/{id}	Obtener por ID
POST	/api/artesanos	Crear artesano
PUT	/api/artesanos/{id}	Actualizar artesano
DELETE	/api/artesanos/{id}	Eliminar artesano
```

🔹 Productos
```
Método	Endpoint	Descripción
GET	/api/productos	Obtener todos
GET	/api/productos/{id}	Obtener por ID
POST	/api/productos	Crear artesano
PUT	/api/productos/{id}	Actualizar artesano
DELETE	/api/productos/{id}	Eliminar artesano
```

## 🧪 Pruebas

🔹 Pruebas unitarias
JUnit 5
Mockito

🔹 Pruebas con Postman
Incluye CRUD de artesanos.
Se puede ejecutar con:
```
newman run Artesanos.postman_collection.json --reporters cli,html
```

🔹 Pruebas de carga con JMeter

GET /api/artesanos
GET /api/artesanos/{id}
POST /api/artesanos
DELETE /api/artesanos/{id}

Incluye métricas de:
Tiempo de respuesta
Throughput
Errores

## 📊 Tecnologías usadas

Java 17
Spring Boot
Spring Security
Maven
Postman / JMeter
H2 / MySQL


👥 Autores
Sara Nicol Zuluaga 
Axel Daniel Bedoya

📄 Licencia
Proyecto académico — uso educativo.
