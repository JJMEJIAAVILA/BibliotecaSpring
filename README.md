
# 📚 BibliotecaSpring - API REST para Gestión de Libros

Este repositorio es un **clon adaptado y extendido** del proyecto original desarrollado por el instructor **Julián Loaiza** y el aprendiz **José**, como parte de un proceso formativo en el SENA.  
> ⚠️ **Este proyecto se ha replicado con fines educativos y de aprendizaje, respetando los derechos de autor de sus creadores originales.**  
> Repositorio original: [julian-loaiza/BibliotecaSpring](https://github.com/julian-loaiza/BibliotecaSpring)

---

## 🔍 Descripción del Proyecto

`BibliotecaSpring` es una API REST creada con **Java**, **Spring Boot** y **JPA**, que permite realizar operaciones CRUD (crear, leer, actualizar y eliminar) sobre una base de datos de libros.

Esta API es ideal como ejemplo práctico de arquitectura de software backend con buenas prácticas y estructura limpia.

---

## 🏗️ Tecnologías Usadas

- ☕ Java 17
- 🌱 Spring Boot 3
- 🗃️ Spring Data JPA
- 🐘 PostgreSQL / H2 (en pruebas)
- 🌐 RESTful API
- 🧪 Insomnia / Postman (para pruebas)

---

## 🚀 Cómo Clonar y Ejecutar el Proyecto en IntelliJ IDEA

### 1. Clonar el repositorio original

```bash
git clone https://github.com/JJMEJIAAVILA/BibliotecaSpring.git
cd BibliotecaSpring
```

### 2. Importar en IntelliJ IDEA

1. Abre IntelliJ IDEA.
2. Haz clic en `File > Open`, selecciona la carpeta del proyecto y haz clic en `OK`.
3. Espera a que se descarguen las dependencias automáticamente (Maven o Gradle).

### 3. Configurar base de datos (H2 por defecto o PostgreSQL)

Revisa el archivo:  
`src/main/resources/application.properties`  
y ajusta si usas PostgreSQL:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
```

Para pruebas rápidas, puedes dejar configurado H2 (memoria).

---

## 🧪 Pruebas con Insomnia

Puedes probar el endpoint principal:

```
GET http://localhost:8080/api/libros
```

Esto retornará una lista de libros con el siguiente formato:

```json
[
  {
    "id": 1,
    "titulo": "Cien años de soledad",
    "fechaPublicacion": "1967-05-30",
    "autor": "Gabriel García Márquez",
    "isbn": "978-0307267149",
    "numeroPaginas": 0
  }
]
```

También puedes realizar:

- **POST**: Crear un libro
- **PUT**: Editar un libro
- **DELETE**: Eliminar por ID

---

## ✍️ Mejoras Realizadas (hasta la fecha)

- ✅ Pruebas funcionales en Insomnia
- ✅ Documentación actualizada
- 🔄 Pendiente: Validaciones con DTOs, control de errores, manejo de excepciones globales, Swagger/OpenAPI

---

## 💡 Próximas Mejoras Propuestas

- Agregar autenticación con Spring Security
- Validaciones con anotaciones `@Valid`
- Documentación interactiva con Swagger
- Frontend básico con HTML + JS o Angular

---

## 🧑‍💻 Autor del Fork

Este fork ha sido creado por **Jhon Jairo Mejía Ávila**, aprendiz del programa **Análisis y Desarrollo de Software del SENA**.

📫 Contacto: [jhon_jmejia@soy.sena.edu.co](mailto:jhon_jmejia@soy.sena.edu.co)

---

## ⚖️ Licencia

Este repositorio adaptado mantiene los créditos del proyecto original. Si los autores del repositorio original establecen una licencia específica, esta será respetada en su totalidad.
