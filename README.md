# **Ecommerce**

## Descripción: 
Esta aplicación Android, desarrollada en Kotlin con Compose, sigue los principios de Clean Architecture y utiliza la arquitectura MVVM, StateFlow, Retrofit, Room y Coroutines para brindar una experiencia robusta y eficiente. 
La aplicación está diseñada para realizar consumos a internet y mostrar los productos obtenidos como un portal ecommerce, en dicho portal se pueden realizar distintas operaciones, como filtrar, ordenar, seleccionar producto y/o buscar.


## Características Principales:
* Clean Architecture: La aplicación sigue los principios de Clean Architecture, lo que facilita la
  modularidad, el mantenimiento y la escalabilidad del código.
* MVVM: Utiliza el patrón de diseño MVVM (Model-View-ViewModel) para separar la lógica de
  presentación y el manejo de datos.
* Compose: La interfaz de usuario se construye utilizando Jetpack Compose, ofreciendo una forma
  declarativa y moderna de diseñar interfaces de usuario en Android.
* Retrofit: Se utiliza Retrofit para realizar solicitudes HTTP de manera sencilla y eficiente.
* LiveData: Es una clase observable de Android Jetpack que facilita la construcción de aplicaciones
  reactivas en Android. Permite que los componentes de la interfaz de usuario observen cambios en
  los datos y se actualicen automáticamente cuando estos cambian. Algunas ventajas clave incluyen su
  observabilidad consciente del ciclo de vida, prevención de fugas de memoria y fácil integración
  con la arquitectura MVVM.
* Room: La persistencia de datos se gestiona mediante la biblioteca Room, facilitando el
  almacenamiento y recuperación de datos locales.
* Coroutines: Se aprovechan las coroutines de Kotlin para realizar operaciones asíncronas de manera
  concisa y eficiente.
* Pruebas Unitarias: Son un componente esencial en el desarrollo de software que permite verificar
  el comportamiento correcto de unidades individuales de código, como funciones o métodos, de manera
  aislada. En el contexto de Android y Kotlin, JUnit es una biblioteca ampliamente utilizada para
  escribir y ejecutar pruebas unitarias.
  Para configurar el entorno de pruebas en tu proyecto, se utiliza la biblioteca JUnit junto con
  Mock y Mockito para crear y manejar objetos simulados (mocks) durante las pruebas.

## Requisitos del Sistema
Dispositivo con sistema operativo Android 7.0 (API nivel 24 | Android Nougat) o superior.

## Instalación
1. Clona el repositorio: git clone https://github.com/nicolasgil/Ecommerce.git
2. Abre el proyecto en Android Studio.
3. Ejecuta la aplicación en un dispositivo o emulador Android.

## IMPORTANTE
Tener en cuenta que versión de Gradle utilizada en la construcción de la aplicación fue la 8.2 y la versión de Android Studio Hedgehog | 2023.1.1 Patch 2



##### Hecho por Nicolas Gil Villa https://github.com/nicolasgil | https://www.linkedin.com/in/nicolasgilvilla/
