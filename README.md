# LiterAlura - Catálogo de Libros

Bienvenido a **LiterAlura**, un desafío de programación que te invita a crear un catálogo de libros interactivo utilizando Java. Este proyecto te permite realizar consultas a una API de libros, procesar datos JSON, almacenarlos en una base de datos y ofrecer diversas opciones de interacción textual a través de la consola.

---

## ⚡ Características principales

- **Búsqueda de libros**: Consume datos de una API para obtener información detallada sobre libros y autores.
- **Almacenamiento persistente**: Utiliza una base de datos para guardar libros, autores y otros datos relevantes.
- **Interacción con el usuario**: Menú de opciones en consola que permite buscar, filtrar y consultar información.
- **Manejo de datos JSON**: Procesa y analiza datos JSON provenientes de la API.
- **Estadísticas**: Genera informes sobre libros y autores almacenados.

---

## 🔄 Flujo del proyecto

1. **Configuración del ambiente de desarrollo**

   - Java 17 o superior.
   - Spring Boot.
   - Base de datos relacional (PostgreSQL o similar).

2. **Consumo de la API de libros**

   - API utilizada: [Gutendex Books API](https://gutendex.com/books).
   - Los datos incluyen títulos, autores, idiomas, descargas y más.

3. **Procesamiento y almacenamiento**

   - Análisis de datos JSON para extraer información relevante.
   - Almacenamiento de libros y autores en la base de datos.

4. **Interacción textual**

   - Menú interactivo en consola con al menos cinco opciones de consulta y filtrado.

5. **Consulta y estadísticas**

   - Filtrado de libros por idioma, autor o descargas.
   - Información sobre autores vivos en un año específico.

---

## 📑 Estructura del proyecto

El proyecto está organizado siguiendo las mejores prácticas de Spring Boot:

```
LiterAlura/
├── src/main/java/valverde/com/mx/literalura/
│   ├── models/          # Entidades del dominio (Autor, Libro, Idioma)
│   ├── repositories/    # Repositorios JPA
│   ├── services/        # Lógica de negocio (Consumo de API, persistencia, estadísticas)
│   ├── util/            # Utilidades (Colores para la consola, validaciones)
│   └── LiterAluraApplication.java  # Clase principal
└── src/main/resources/
    └── application.properties  # Configuración de la base de datos y otras propiedades
```

---

## 🛠️ Configuración del proyecto

### Prerrequisitos

- **Java**: Versión 17 o superior.
- **Maven**: Para la gestión de dependencias.
- **Base de datos**: PostgreSQL.

### Configuración

1. Clona este repositorio:

   ```bash
   git clone https://github.com/tu-usuario/LiterAlura.git
   cd LiterAlura
   ```

2. Configura la base de datos en el archivo `application.properties`:

