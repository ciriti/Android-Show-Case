# AndroidShowCase

The app uses a free music API. It has two main modules:

- App: view layer
- Datasource: data layer

It is based on:

- SOLID principles
- common android best practices from the official android documentation

I've chosen the following [Code Styles](https://github.com/square/java-code-styles/).

##### Offline Capabilities

I implemented a simple mechanism to support offline Capabilities.
The logic is inside the implementation of DBDelegate.kt.
As the name suggests, to add this feature, I used a simple implementation
of the [Decorator Pattern](https://en.wikipedia.org/wiki/Decorator_pattern)
using the [class delegation](https://kotlinlang.org/docs/reference/delegation.html)
feature from Kotlin.

This version of DBDelegate.kt stores the json of the tracks list
inside Shared Preferences.

Of course it is possible to create with the same technique
([class delegation](https://kotlinlang.org/docs/reference/delegation.html))
different implementation using different way to store the data.

The Database architecture follows also the
[Open-Close principle](https://en.wikipedia.org/wiki/Open%E2%80%93closed_principle).
It provides an interface and a default implementation. The default class is **final**
but you can create your own implementation and using it in all your application
without causing crash.


##### Architecture:

- MVVM

##### Language:

- kotlin

##### Used libraries:

- RxJava
- Retrofit 2
- Glide
- Dagger 2
- canary leaks
- Android Architecture Components

##### Test libraries:

- Espresso
- Mockito
- JUnit


