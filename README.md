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
<img width="1980" height="1449" alt="image" src="https://github.com/user-attachments/assets/11fe0e4c-f3d8-433a-9da8-dfc81fe4971f" />


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
<img width="2017" height="1464" alt="image" src="https://github.com/user-attachments/assets/904b4912-7310-49a6-9b2a-b7e493a3e2f3" />



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
<img width="1936" height="1043" alt="image" src="https://github.com/user-attachments/assets/7d32164b-3829-40ea-9304-0d8dd2c5a779" />

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
<img width="1986" height="1491" alt="image" src="https://github.com/user-attachments/assets/6beb497c-1a9e-40e6-ad6a-10ce0bba088c" />

DELETE /api/productos/{id}
Descripción: Elimina un producto por ID.
<img width="2052" height="1026" alt="image" src="https://github.com/user-attachments/assets/cfb224e4-832f-4a20-a325-0fd5c828dbd5" />


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

🔹 Pruebas con JMeter – Evidencias y Análisis

Durante las pruebas de rendimiento realizadas con JMeter, se configuró un Thread Group con una sola iteración inicial para validar el correcto funcionamiento de los endpoints del módulo Artesanos.

Resultados con IDs válidos
Cuando se ejecutan solicitudes usando IDs existentes en la base de datos, todos los endpoints responden exitosamente.
Esto demuestra que la API responde correctamente a operaciones CRUD bajo condiciones normales.
<img width="294" height="143" alt="Captura de pantalla 2025-11-24 121853" src="https://github.com/user-attachments/assets/3920cd94-bc2a-43fd-9774-7f4bae8122db" />

Resultados con IDs inexistentes (errores esperados)
Cuando se intenta realizar operaciones como:
GET /api/artesanos/{id}
DELETE /api/artesanos/{id}
…usando un ID que no existe, JMeter muestra las solicitudes en rojo.
Esto no indica un fallo del sistema, sino un comportamiento esperado:
GET retorna 404 Not Found
DELETE también retorna 404 cuando intenta eliminar un recurso inexistente
Este comportamiento demuestra que la API maneja correctamente errores de negocio y responde con los códigos HTTP apropiados.
<img width="2285" height="1425" alt="Captura de pantalla 2025-11-24 121618" src="https://github.com/user-attachments/assets/db7051d7-7215-4b65-8ba8-60e6046f69e1" />

Incluye métricas de:
Tiempo de respuesta
Throughput
Errores

## Configuración de Base de Datos (Docker + PostgreSQL)

Para el backend se utilizó una base de datos PostgreSQL levantada en un contenedor Docker. Esto permitió trabajar en un entorno limpio, reproducible y estable durante las pruebas.

Contenedor utilizado:
```
docker run --name postgres-artesanos \
  -e POSTGRES_USER=artesano \
  -e POSTGRES_PASSWORD=12345 \
  -e POSTGRES_DB=artesanos_db \
  -p 5432:5432 \
  -d postgres:latest
```

Acceso a la BD:
```
docker exec -it postgres-artesanos psql -U artesano -d artesanos_db
```
## Diagrama ER / Modelo de datos

```
Artesano
 ├── id (PK)
 ├── nombre
 ├── ubicacion
 ├── tipoArtesania
 └── descripcion

Producto
 ├── id (PK)
 ├── nombre
 ├── precio
 └── artesano_id (FK -> Artesano.id)
```


## 🧩 Patrones de Diseño Aplicados

MVC: separación clara entre controladores, servicios y repositorios.

Repository Pattern: acceso a datos abstraído mediante Spring Data JPA.

Inversión de Control / Dependency Injection: Spring gestiona dependencias automáticamente, reduciendo el acoplamiento y facilitando pruebas.

## 🧱 Decisiones de Arquitectura

Spring Boot: rápido, modular y perfecto para APIs REST.

PostgreSQL: base de datos robusta y confiable para datos estructurados.

Docker: permite correr PostgreSQL en un entorno limpio y reproducible.

Basic Auth: solución de seguridad simple y suficiente para proteger los endpoints sensibles.

📝 Conclusiones

- La arquitectura en capas (MVC + Servicios + Repositorios) permitió construir un sistema ordenado, modular y fácil de mantener.

- El uso de patrones como Repository, Inversión de Control y DI fortaleció la estructura y la escalabilidad del proyecto.

- PostgreSQL en Docker brindó un entorno estable, reproducible y aislado para las pruebas.

- Las pruebas funcionales confirmaron el correcto comportamiento del CRUD en escenarios reales.

- JMeter evidenció que los endpoints funcionan bien bajo carga y que la API responde correctamente ante errores (404, etc.).

- La seguridad con Basic Auth cubrió los requisitos mínimos sin agregar complejidad innecesaria.

- El proyecto queda como una base sólida para futuras ampliaciones: frontend, roles, autenticación JWT, panel administrativo, etc.

  
👥 Autores
Sara Nicol Zuluaga 
Axel Daniel Bedoya

📄 Licencia
Proyecto académico — uso educativo.
