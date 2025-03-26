# **API Employee - Employee Management System**

La **API Employee** es una solución RESTful construida con Spring Boot para gestionar empleados. Incluye funcionalidades CRUD, registro de eventos, y soporte para pruebas automatizadas con JUnit y Mockito.

## **Tecnologías Utilizadas**
- **Java 17**: Lenguaje de programación principal.
- **Spring Boot 3.x**: Framework para simplificar el desarrollo de aplicaciones.
- **Spring Data JPA**: Persistencia y consultas de bases de datos.
- **H2 Database**: Base de datos en memoria para pruebas.
- **Logback/SLF4J**: Registro de eventos para auditoría.
- **Swagger/OpenAPI**: Documentación interactiva de los endpoints.
- **Postman**: Herramienta para probar y documentar las API.
- **JUnit 5**: Framework para pruebas unitarias.
- **Mockito**: Librería para simulación (mocking) en pruebas unitarias.

## **Endpoints**
| **Método** | **Ruta**                | **Descripción**                         |
|------------|-------------------------|-----------------------------------------|
| GET        | `/api/employees/{id}`   | Obtiene un empleado por su ID.          |
| GET        | `/api/employees`        | Lista todos los empleados registrados.  |
| POST       | `/api/employees/single` | Crea un nuevo empleado.                 |
| POST       | `/api/employees/multiple` | Crea múltiples empleados a la vez.      |
| PUT        | `/api/employees`        | Actualiza los datos de un empleado.     |
| DELETE     | `/api/employees/{id}`   | Elimina un empleado por su ID.          |

**Nota**: La colección de Postman con ejemplos para probar estos endpoints está disponible en la sección [Colección Postman](#colección-postman).

## **Cómo Ejecutar**
1. Clona el repositorio:
   ```bash
   git clone https://github.com/sudhunter/api-employee.git

## **Pruebas Unitarias**
El proyecto incluye pruebas con **JUnit** y **Mockito**:
- **Cobertura**: Se prueban los endpoints, servicios, y lógica principal de la aplicación.
- **Frameworks Utilizados**:
  - **JUnit**: Para estructurar y ejecutar las pruebas.
  - **Mockito**: Para simular dependencias como repositorios y servicios durante las pruebas unitarias.

Para ejecutar las pruebas, usa el siguiente comando:
```bash
mvn test

### **Instrucciones para Importar en Postman**

1. Copia el contenido del archivo `EmployeeController API.postman_collection.json` en tu equipo.
2. Abre Postman y sigue estos pasos:
   - Ve a la pestaña **File > Import**.
   - Selecciona la opción **Importar archivo** y carga el archivo `.json` que descargaste o guardaste.
3. Una vez importada, la colección aparecerá en la lista de tus colecciones de Postman.
4. Usa las solicitudes preconfiguradas para interactuar y probar los endpoints de la API.
