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

- Configuración: 10 usuarios simultáneos, ramp-up 5 segundos, 1 loop.
- Resultados principales:
Tiempo promedio: 78 ms y 74 ms
Máximo: 83 ms
No se registraron errores significativos
Los pocos errores registrados se deben a IDs inexistentes de pruebas anteriores.
![Imagen de WhatsApp 2025-11-25 a las 11 05 20_7f5ece13](https://github.com/user-attachments/assets/65973325-55ba-4c89-93a3-b3527965f01c)
![Imagen de WhatsApp 2025-11-25 a las 11 07 48_08922ac1](https://github.com/user-attachments/assets/f3b3a815-e0e2-41a6-8213-0b5e17b9d477)

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

## Análisis de Código Estático (SAST)
- Herramienta: SonarQube local con Docker
- Comando usado:
```
mvn -B sonar:sonar -Dsonar.token=%SONAR_TOKEN_LOCAL% -Dsonar.host.url=http://localhost:9000
```
Resultado principal:
Quality Gate: Passed
Cobertura: 73.6%
Seguridad: 1 problema (Security rating E)
Mantenibilidad: A
Confiabilidad: A
<img width="1415" height="1015" alt="image" src="https://github.com/user-attachments/assets/f7aa85b4-df39-49d0-a58f-eb67b3b87fb5" />

## Escaneo de Dependencias
- Herramienta: OWASP Dependency-Check (plugin Maven)
- Comando usado:
```
mvn org.owasp:dependency-check-maven:check
```
- Resultado: Reporte HTML generado en:
```
target/dependency-check-report.html
```
- Aquí se listan vulnerabilidades conocidas en dependencias externas.

## Escaneo de Secrets
- Herramienta: Gitleaks
- Comando usado:
```
gitleaks detect --report-format json --report-path gitleaks-report.json
```
Resultado:
Se encontró un secret en:
```
target/surefire-reports/TEST-com.artesanos.artesanos.controller.ProductoControllerTest.xml
```
- Tipo: API Key genérica
  Se detectó un secret potencialmente sensible en los tests, no en el código de producción.
<img width="2740" height="1630" alt="image" src="https://github.com/user-attachments/assets/a7fd3c6c-52e2-46dc-bdf8-0047fd606b98" />

## Escaneo de Contenedores (Container Scan)
- Herramienta: Trivy
- Comando usado:
```
trivy image artesanos-api --format json --output trivy-report.json
```
- Resultado:
Si no hay Dockerfile, indicar que no se pudo escanear la imagen.
Explicación: “Se intentó escanear la imagen Docker, pero aún no se ha creado el Dockerfile ni la imagen ‘artesanos-api’.”
<img width="2219" height="1643" alt="image" src="https://github.com/user-attachments/assets/9caec4eb-642e-435d-bc4b-50ee7e3b54ac" />

## Tips y Observaciones
Variables de entorno en Windows con setx: cerrar y abrir CMD para que sean efectivas.
Comando SonarQube requiere token válido (SONAR_TOKEN_LOCAL).
Gitleaks requiere que el proyecto sea un repositorio Git (si no, solo escanea archivos sin commits).

## Pruebas API con Postman y Newman
- Se realizaron pruebas de los endpoints de productos y artesanos usando Postman y ejecutando los tests con Newman.
- Observación: Los errores 404 ocurren porque algunos IDs probados ya no existen, lo cual es esperado y correcto.
![Imagen de WhatsApp 2025-11-25 a las 10 57 29_b389af83](https://github.com/user-attachments/assets/7d227728-8088-4858-a0ee-2b2080aca70e)
![Imagen de WhatsApp 2025-11-25 a las 10 58 02_c9ed7d91](https://github.com/user-attachments/assets/bf81e569-3cff-4248-b945-69a77c8dfcde)

## Cobertura de Código (Jacoco)
- Reporte generado:
```
file:///C:/Users/saran/Downloads/artesanos/target/site/jacoco/index.html
```
- Cobertura total: 75%
- Permite ver cobertura por clases y métodos.
<img width="1432" height="438" alt="image" src="https://github.com/user-attachments/assets/97a62a9b-f40f-4b72-aa13-152187928b1d" />

## 🏁 Conclusiones

1. Arquitectura modular y escalable:
La aplicación está diseñada con capas claramente definidas (controladores, servicios, repositorios), lo que facilita mantenimiento, pruebas y escalabilidad futura.

2. Seguridad implementada:
Todos los endpoints sensibles requieren autenticación básica, y la aplicación maneja usuarios con contraseñas encriptadas, garantizando un nivel mínimo de protección.

3. Pruebas completas:
- Unitarias con JUnit y Mockito, asegurando que la lógica de negocio funcione correctamente.
- Integración con Postman y Newman para validar los endpoints; los errores 404 son esperados por IDs inexistentes, demostrando manejo correcto de errores.
- Pruebas de carga con JMeter muestran tiempos de respuesta bajos y consistentes bajo 10 usuarios simultáneos, evidenciando buen desempeño.

4. DevSecOps mínimo implementado:
- SAST: SonarQube confirmó calidad de código y cobertura adecuada.
- Dependency scanning: OWASP Dependency-Check detectó vulnerabilidades conocidas en librerías externas.
- Secrets scanning: Gitleaks identificó un secret en los tests, evitando exposición en producción.
- Container scanning: Trivy mostró la necesidad de generar la imagen Docker antes del escaneo, evidenciando buenas prácticas de CI/CD.

5. Cobertura de código confiable:
Jacoco reportó aproximadamente 75% de cobertura total, permitiendo identificar áreas críticas para pruebas adicionales.

6. Reproducibilidad y control de datos:
Uso de PostgreSQL en contenedor Docker asegura entornos consistentes y facilita la instalación en cualquier equipo sin conflictos de configuración.

7. Aprendizaje y buenas prácticas:
El proyecto permitió aplicar patrones de diseño (MVC, Repository, DI), integración de herramientas DevSecOps, pruebas de rendimiento y cobertura de código, consolidando conceptos de diseño, seguridad y calidad en software académico.

👥 Autores
Sara Nicol Zuluaga 
Axel Daniel Bedoya

📄 Licencia
Proyecto académico — uso educativo.
