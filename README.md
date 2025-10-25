# Rick & Morty App

Esta es una aplicación de Android de ejemplo que muestra información sobre los personajes y episodios de la popular serie de televisión Rick & Morty, construida con un enfoque moderno y modular.

## ✨ Características

*   Ver una lista de personajes.
*   Buscar personajes por nombre.
*   Ver detalles de un personaje específico.
*   Ver una lista de episodios.
*   Navegación entre pantallas.

## 🚀 Tecnologías utilizadas

*   **Lenguaje:** [Kotlin](https://kotlinlang.org/)
*   **Interfaz de usuario:** [Jetpack Compose](https://developer.android.com/jetpack/compose)
*   **Arquitectura:** MVVM (Model-View-ViewModel) con Clean Architecture y modular por capas y features.
*   **Inyección de dependencias:** [Hilt](https://dagger.dev/hilt/)
*   **Networking:** [Retrofit](https://square.github.io/retrofit/) & [OkHttp](https://square.github.io/okhttp/)
*   **Asincronía:** Coroutines & Flows
*   **Navegación:** [Jetpack Navigation Compose](https://developer.android.com/jetpack/compose/navigation)

## 🏛️ Arquitectura Modular

El proyecto está organizado en una arquitectura multi-módulo, siguiendo los principios de **Clean Architecture**. Esto promueve la separación de preocupaciones, la escalabilidad y el mantenimiento del código. La dirección de las dependencias es siempre hacia el centro: `Feature -> Domain <- Data`.

```
┌──────────────────┐
│     Features     │
│ (home, detail, …)│
└────────┬─────────┘
         │
         ▼
┌──────────────────┐
│      Domain      │
└────────┬─────────┘
         │
         ▼
┌──────────────────┐
│       Data       │
└────────┬─────────┘
         │
┌────────┴────────┬──────────┐
│                 │          │
▼                 ▼          ▼
┌─────────┐   ┌─────────┐   ┌───────────┐
│ Network │   │Database │   │Preferences│
└─────────┘   └─────────┘   └───────────┘
```

### Capas Principales

*   **`:feature:*` (Capa de Presentación)**
    *   Módulos: `:feature:home`, `:feature:detailCharacter`, `:feature:view_episodes`.
    *   Responsabilidad: Contiene la UI (implementada con Jetpack Compose), los `ViewModel` que gestionan el estado y la lógica de la pantalla. Cada módulo de feature representa una pantalla o un flujo de usuario completo. Dependen del módulo `:domain`.

*   **`:domain` (Capa de Dominio)**
    *   Responsabilidad: Contiene la lógica de negocio central de la aplicación. Define los modelos de dominio y los casos de uso (`UseCases`) que orquestan el flujo de datos desde la capa de datos. Es un módulo de Kotlin puro, sin dependencias de Android, lo que lo hace completamente independiente y testeable.

*   **`:data` (Capa de Datos)**
    *   Responsabilidad: Implementa los repositorios definidos en la capa de dominio. Actúa como una única fuente de verdad (`Single Source of Truth`), decidiendo si obtener los datos de una fuente remota (`:network`) o local (`:database`).

### Módulos Core

Estos módulos proporcionan funcionalidades específicas y compartidas a través de las diferentes capas.

*   **`:core:network`**: Gestiona toda la comunicación de red con la API de Rick & Morty usando Retrofit y OkHttp.
*   **`:core:database`**: Administra la base de datos local (usando Room) para persistir datos y permitir el acceso sin conexión.
*   **`:core:preferences`**: Maneja el almacenamiento de datos simples en formato clave-valor, como configuraciones de usuario.
*   **`:core:model`**: Define las estructuras de datos (DTOs, entidades de base de datos, modelos de dominio) que se utilizan en toda la aplicación.
*   **`:core:design`**: Contiene el sistema de diseño de la aplicación: temas, colores, tipografía y componentes de Jetpack Compose reutilizables.
*   **`:core:common`**: Incluye clases de utilidad, extensiones y lógica compartida que pueden ser utilizadas por cualquier otro módulo.

## 💡 Decisiones de Diseño y Estrategias

### Estrategia Offline-First

Para cumplir con el requisito de funcionamiento sin conexión, la aplicación implementa una estrategia de caché a nivel de red, gestionada por **OkHttp**. Esto se logra a través de un `Interceptor` que modifica las cabeceras `Cache-Control` de las peticiones:

1.  **Con conexión a Internet:** Las respuestas se cachean por un breve período (60 segundos). Esto mejora el rendimiento y reduce el consumo de datos ante peticiones repetidas en poco tiempo.
2.  **Sin conexión a Internet:** Se instruye a OkHttp para que utilice una respuesta de la caché, incluso si está "vencida" (`stale`), por un período de hasta 4 horas (`max-stale=14400`).

Esta solución permite que la aplicación muestre datos previamente cargados de forma transparente para el usuario cuando el dispositivo está offline, sin necesidad de implementar una lógica de sincronización compleja en la capa de datos para este caso de uso.

### Manejo de Estado de la UI

La arquitectura reactiva con **MVVM** y **Flow** es fundamental para una gestión de estados robusta y declarativa en la UI de Compose.

*   **ViewModel**: Expone un `StateFlow` que representa el estado de la pantalla (ej. `CharactersUiState`).
*   **UI (Compose)**: Recolecta el `StateFlow` como estado (`collectAsStateWithLifecycle`) y reacciona automáticamente a los cambios, redibujando únicamente los componentes necesarios.
*   **Estados Modelados**: El `StateFlow` emite objetos que representan los diferentes estados posibles de la pantalla: `Loading` (mostrando un indicador de progreso), `Success` (mostrando los datos) y `Error` (mostrando un mensaje de error o una pantalla de reintento). Este patrón asegura que la UI siempre refleje el estado actual del sistema de forma predecible.

## 🏗️ Cómo compilar

1.  Clona este repositorio:
    ```bash
    git clone https://github.com/jcpg1982/Rick-Morty.git
    ```
2.  Abre el proyecto en Android Studio.
3.  Sincroniza las dependencias de Gradle.
4.  Ejecuta la aplicación en un emulador o dispositivo físico.
