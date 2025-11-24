# 🧵 Artesanos Platform – Gestión de Artesanos y Productos

## 📌 Descripción del Proyecto

Artesanos Platform es una aplicación web diseñada para apoyar a los artesanos colombianos en la gestión de sus productos, inventario y presencia digital.
La plataforma proporciona una API REST que permite registrar artesanos, administrar productos y consultar información relevante de forma sencilla.

Este proyecto hace parte del curso de Diseño y Arquitectura de Software.


## 🧱Arquitectura del Sistema

El sistema Artesanos está construido con una arquitectura en capas, organizada de la siguiente forma:

Capa de Presentación (API REST)
Expone los endpoints para gestionar artesanos y productos. Los controladores reciben solicitudes HTTP, validan datos y devuelven respuestas en formato JSON.

Capa de Servicios (Lógica de negocio)
Contiene las reglas del sistema, coordinando acciones entre controladores y repositorios. Evita duplicar lógica y asegura consistencia de datos.

Capa de Acceso a Datos (Repositorios)
Utiliza Spring Data JPA para realizar operaciones CRUD en la base de datos sin escribir SQL manual.

Persistencia (Base de Datos)
La aplicación puede trabajar con H2 en desarrollo o MySQL en producción. Se manejan entidades como Artesano y Producto con una relación 1 a N.

Seguridad
Implementada mediante Spring Security con Basic Authentication.
• /api/productos/** → público
• /api/artesanos/** → requiere autenticación
La autenticación maneja usuarios en memoria con contraseñas encriptadas.

Esta estructura permite modularidad, facilidad de mantenimiento, pruebas unitarias aisladas y escalabilidad futura.

### Diagrama de Arquitectura 
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

GET /api/artesanos
Descripción: Obtiene la lista completa de artesanos.
Ejemplo de Response (200):
```
[
  {
    "id": 1,
    "nombre": "Juan Pérez",
    "ubicacion": "Bogotá",
    "tipoArtesania": "Cerámica",
    "descripcion": "Artesano de cerámica tradicional"
  }
]
```
Códigos de estado:
200 OK — Lista obtenida correctamente
401 Unauthorized — Falta autenticación
500 Internal Server Error

GET /api/artesanos/{id}
Descripción: Obtiene un artesano por su ID.
Ejemplo de Response (200):
```
{
  "id": 1,
  "nombre": "Juan Pérez",
  "ubicacion": "Bogotá",
  "tipoArtesania": "Cerámica",
  "descripcion": "Artesano de cerámica tradicional"
}
```
POST /api/artesanos
Descripción: Crea un nuevo artesano.
Body requerido:
```
{
  "nombre": "María Rojas",
  "ubicacion": "Medellín",
  "tipoArtesania": "Tejidos",
  "descripcion": "Tejedora de artesanías ancestrales"
}
```
Response (200/201):
```
{
  "id": 5,
  "nombre": "María Rojas",
  "ubicacion": "Medellín",
  "tipoArtesania": "Tejidos",
  "descripcion": "Tejedora de artesanías ancestrales"
}
```
Códigos de estado:
201 Created
400 Bad Request — Body inválido
401 Unauthorized


DELETE /api/artesanos/{id}
Descripción: Elimina un artesano y automáticamente sus productos asociados (cascade delete).
Response (200):
```
"Artesano eliminado correctamente"
```
Códigos de estado:
200 OK
404 Not Found
401 Unauthorized

🔹 Productos

GET /api/productos
Descripción: Obtiene todos los productos disponibles.
Response (200):
```
[
  {
    "id": 1,
    "nombre": "Jarrón artesanal",
    "precio": 50000,
    "artesanoId": 1
  }
]
```

GET /api/productos/{id}
Descripción: Obtiene un producto específico.

POST /api/productos
Body ejemplo:
```
{
  "nombre": "Sombrero vueltiao",
  "precio": 120000,
  "artesanoId": 3
}
```

DELETE /api/productos/{id}
Descripción: Elimina un producto por ID.


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
