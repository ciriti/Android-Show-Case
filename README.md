# AndroidShowCase

The app uses a free music API. It has two main modules:

- App: view layer
- Datasource: data layer

It is based on:

- SOLID principles
- common android best practices from the official android documentation

I've chosen the following [Code Styles](https://github.com/square/java-code-styles/).

### Offline Capabilities

I implemented a simple mechanism to support offline Capabilities.
The logic is [DBDelegate.kt](app/src/main/java/ciriti/androidshowcase/core/components/DBDelegate.kt).
As the name suggests, to add this feature, I used [class delegation](https://kotlinlang.org/docs/reference/delegation.html) feature from Kotlin
to implement the [Decorator Pattern](https://en.wikipedia.org/wiki/Decorator_pattern).

[DBDelegate.kt](app/src/main/java/ciriti/androidshowcase/core/components/DBDelegate.kt)
stores the json of the tracks list inside Shared Preferences.

Of course with the same technique ([class delegation](https://kotlinlang.org/docs/reference/delegation.html)),
it is possible to create different implementations using others mechanisms
to store the data.

Additionally, the database structure follows the
[Open-Close principle](https://en.wikipedia.org/wiki/Open%E2%80%93closed_principle),
which provides an interface and a default implementation. The class I provide is
**final** but it leaves you the option of creating your own implementation to
use it in the application - without causing crash.


### Architecture:

- MVVM

### Language:

- kotlin

### Used libraries:

- RxJava
- Retrofit 2
- Glide
- Dagger 2
- canary leaks
- Android Architecture Components

### Test libraries:

- Espresso
- Mockito
- JUnit


