# 🛍️ LUNIQ - APLICACIÓN DE TIENDA DE ROPA ONLINE
LUNIQ es una aplicación de escritorio desarrollada en Java que simula el funcionamiento de una tienda online de moda. Los usuarios pueden registrarse, iniciar sesión, explorar el catálogo, añadir productos al carrito y gestionar su perfil.
Este proyecto ha sido desarrollado como parte del módulo de integración de Desarrollo de Aplicaciones Web (DAW).

# 🚀 CARACTERÍSTICAS PRINCIPALES
✔️ Registro e inicio de sesión de usuarios ✔️ Catálogo de productos con imágenes ✔️ Vista detallada de cada producto ✔️ Añadir productos al carrito de compras ✔️ Gestión y edición del perfil de usuario ✔️ Persistencia de datos con operaciones CRUD mediante JDBC ✔️ Pruebas unitarias con JUnit 5

# 🛠 TECNOLOGÍAS UTILIZADAS
🔹 Lenguaje: Java SE 17 🔹 Interfaz Gráfica: Java Swing 🔹 Base de Datos: XAMP + PHPMyAdmin 🔹 Persistencia: JDBC 🔹 Gestión de Código: Git + GitHub 🔹 Pruebas: JUnit 5 🔹 IDE Utilizado: Eclipse

# 📁 ESTRUCTURA DEL PROYECTO

LUNIQ/
- │── src/
- │   ├── main/java/com/luniq/  
- │   │   ├── modelo/          **Clases del modelo (Usuario, Producto, Pedido)**
- │   │   ├── dao/             **Clases de acceso a datos (CRUD con JDBC)**
- │   │   ├── vista/           **Interfaces gráficas con Swing**
- │   │   ├── utils/           **Utilidades y configuraciones**
- │── resources/               **Imágenes, iconos y archivos de configuración**
- │── proyecto_bbdd.sql        **Script de creación de base de datos**
- │── module-info.java         **Archivo de configuración del módulo**

# 🔗 CONEXIÓN A LA BASE DE DATOS
    "localhost",                // Host
    "3306",                    // Puerto
    "usuario",                // Usuario MySQL
    "contraseña",            // Contraseña
    "mi_proyectoBBDD"       // Nombre de la base de datos

# ✅ PRUEBAS
Las pruebas unitarias con JUnit 5 garantizan la correcta funcionalidad de los DAOs y la lógica de negocio.
```bash
mvn test
```

# 🧠 BUENAS PRÁCTICAS APLICADAS
✔️ Programación Orientada a Objetos (POO) - Encapsulación y clases reutilizables ✔️ Gestión de Eventos - Modularización de la interfaz gráfica ✔️ Refactorización - Código optimizado y limpio ✔️ Control de Versiones - Uso de Git con commits frecuentes

# 📄 DOCUMENTACIÓN
📌 JavaDoc generado para todas las clases públicas. 📌 Este archivo README.md documenta el despliegue, configuración y estructura.

# 👨‍💻 AUTORES
Alejandro Hernández Murga
Diego Capellán Fernández

# 📅 FECHA DE ENTREGA
📌 26 mayo 2025.
