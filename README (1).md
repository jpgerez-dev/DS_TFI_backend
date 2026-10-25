# JFA Distribuciones: Sistema web de gestión de usuarios

Trabajo Final Integrador de **Diseño de Sistemas** (Ingeniería en Sistemas de Información, UTN Facultad Regional Tucumán, comisión 3K3, grupo 17, 2023).

El proyecto parte del análisis y diseño de un sistema de ventas online para **JFA Distribuciones**, una distribuidora de materiales eléctricos, y lo lleva a una implementación funcional: una **API REST** en Spring Boot con **autenticación mediante JWT**, contraseñas protegidas con **Argon2** y un frontend web para registrarse, iniciar sesión y administrar usuarios.

![Java](https://img.shields.io/badge/Java-21-007396?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1.6-6DB33F?logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.x-4479A1?logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-wrapper-C71A36?logo=apachemaven&logoColor=white)
![JWT](https://img.shields.io/badge/Auth-JWT-000000?logo=jsonwebtokens&logoColor=white)

## Funcionalidades implementadas

- **Registro de usuarios** con nombre, apellido, email y contraseña.
- **Inicio de sesión** con emisión de un token JWT firmado (HS256, vigencia de 4 días por defecto).
- **Listado de usuarios** en una tabla con búsqueda y paginación (DataTables), protegido por token.
- **Eliminación de usuarios**, protegida por token.
- **Contraseñas almacenadas como hash Argon2**, nunca en texto plano.
- **Persistencia con JPA/Hibernate** sobre MySQL.

## Estado del proyecto

El informe del TFI documenta el sistema completo de ventas (catálogo, carrito y compra). La implementación entregada cubre el **módulo de usuarios y autenticación**.

| Área | Estado |
|---|---|
| Registro de usuarios | Implementado |
| Inicio de sesión con JWT | Implementado |
| Listado y eliminación de usuarios | Implementado |
| Modificación de usuarios | En desarrollo (endpoint y pantalla creados, falta la lógica) |
| Búsqueda y selección de productos | Documentado en el informe, pendiente |
| Carrito de compras y finalización de compra | Documentado en el informe, pendiente |

## Tecnologías

| Capa | Tecnologías |
|---|---|
| Backend | Java 21, Spring Boot 3.1.6, Spring Web, Spring Data JPA (Hibernate), Lombok |
| Seguridad | JWT (jjwt), Argon2 (argon2-jvm) |
| Base de datos | MySQL 8 (Connector/J 8.0.33) |
| Frontend | HTML, JavaScript, jQuery, Bootstrap 4 (plantilla SB Admin 2), DataTables |
| Construcción | Maven (wrapper incluido) |

## Estructura del repositorio

```
DS2023-TFI-G17/
├── README.md
├── schema.sql                      # Script de la base de datos
├── DS2023_TFI_G17.pdf              # Informe completo del TFI
└── DS2023_TFI_G17/
    └── backendTFI/
        ├── pom.xml
        └── src/main/
            ├── java/com/BackendTFI/backendTFI/
            │   ├── controllers/    # Endpoints REST (usuarios y login)
            │   ├── Dao/            # Acceso a datos (JPA / EntityManager)
            │   ├── Models/         # Entidad Usuario
            │   └── utils/          # JWT, CORS y configuración
            └── resources/
                ├── application.properties
                └── static/         # Frontend: login, registro, usuarios, js/
```

## Requisitos

- JDK 21
- MySQL 8.x
- No hace falta instalar Maven: el proyecto incluye el wrapper (`mvnw`).

## Puesta en marcha

**1. Crear la base de datos**

```bash
mysql -u root -p < schema.sql
```

El script crea la base `cursojava` y la tabla `usuarios`.

**2. Configurar credenciales**

El proyecto no guarda secretos en el código: la contraseña de MySQL y el secreto del JWT se leen de variables de entorno.

| Variable | Obligatoria | Descripción |
|---|---|---|
| `DB_PASSWORD` | Sí | Contraseña del usuario de MySQL |
| `JWT_SECRET` | Sí | Secreto para firmar los tokens, en Base64 |
| `DB_USER` | No | Usuario de MySQL (por defecto `root`) |

Linux / macOS:

```bash
export DB_PASSWORD=tu_password
export JWT_SECRET=$(openssl rand -base64 32)
```

Windows (PowerShell):

```powershell
$env:DB_PASSWORD="tu_password"
$env:JWT_SECRET="pegá_acá_un_valor_en_base64"
```

**3. Ejecutar la aplicación**

```bash
cd DS2023_TFI_G17/backendTFI
./mvnw spring-boot:run          # En Windows: mvnw.cmd spring-boot:run
```

**4. Abrir el sistema**

La aplicación queda disponible en el puerto `8083`:

| Pantalla | URL |
|---|---|
| Registro | http://localhost:8083/registar.html |
| Inicio de sesión | http://localhost:8083/login.html |
| Usuarios (requiere sesión) | http://localhost:8083/usuarios.html |

## API REST

Las rutas protegidas esperan el token JWT en el encabezado `Authorization` (el token solo, sin el prefijo `Bearer`) y responden `401` si falta o está vencido.

| Método | Ruta | Descripción | Auth |
|---|---|---|---|
| `POST` | `/api/usuarios` | Registra un usuario (responde `409` si el email ya existe) | No |
| `POST` | `/api/login` | Inicia sesión y devuelve el token JWT (responde `401` si las credenciales son incorrectas) | No |
| `GET` | `/api/usuarios` | Lista los usuarios | Sí |
| `PUT` | `/api/usuarios/modificar/{id}` | Modifica un usuario (en desarrollo) | Sí |
| `DELETE` | `/api/usuarios/eliminar/{id}` | Elimina un usuario | Sí |

Ejemplo de uso:

```bash
# Registrar
curl -X POST http://localhost:8083/api/usuarios \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Ana","apellido":"Pérez","email":"ana@example.com","password":"MiClave123"}'

# Iniciar sesión (devuelve el token)
curl -X POST http://localhost:8083/api/login \
  -H "Content-Type: application/json" \
  -d '{"email":"ana@example.com","password":"MiClave123"}'

# Listar usuarios con el token
curl http://localhost:8083/api/usuarios -H "Authorization: <token>"
```

## Documentación de análisis y diseño

El informe [`DS2023_TFI_G17.pdf`](DS2023_TFI_G17.pdf) (36 páginas) incluye:

- Modelado de la empresa y delimitación del proyecto
- Diagramas de actividades y de bloques de la arquitectura
- Casos de uso (registrar usuario, iniciar sesión, buscar y seleccionar producto, agregar al carrito, finalizar compra), glosario y especificación complementaria
- Modelo de dominio, diagramas de secuencia y contratos
- Diseño: diagramas de despliegue, de paquetes, de colaboración, de clases y de transición de estados
- Diseño de interfaz y de salida impresa
- Modelo entidad-relación y diagrama relacional de la base de datos

## Autores

Grupo 17, comisión 3K3, año 2023:

- Gallardo, Maximiliano
- García Piovesán, Matías
- Gerez, Juan Pablo
- Jaluf, Rocío Daniela

**Docentes:** Bustos Thames, Juan Pablo (teoría) y Vicente, Francisco (práctica)
**Cátedra de Diseño de Sistemas, Universidad Tecnológica Nacional, Facultad Regional Tucumán**

## Créditos

El frontend se basa en la plantilla [SB Admin 2](https://startbootstrap.com/theme/sb-admin-2/) de Start Bootstrap, distribuida bajo licencia MIT.

---

*Proyecto académico desarrollado como Trabajo Final Integrador.*
