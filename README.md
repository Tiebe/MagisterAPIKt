
# MagisterAPIKt

This is an wrapper for the Magister API in Kotlin. It supports Kotlin Multiplatform.




## Installation

Add this project as a dependency to your Gradle or Maven project.

In Maven:
```
<dependency>
  <groupId>dev.tiebe</groupId>
  <artifactId>magisterapi</artifactId>
  <version>1.1.9</version>
</dependency>
```

In Gradle:
```
implementation("dev.tiebe:magisterapi:1.1.9")
```


## Usage/Examples

To use this project you will first need to handle the logging in process yourself. This cannot be done automatically in code.

You can use `LoginFlow#createAuthURL()` to create an URL to sign in.
After signing in, you will be redirected to a page that starts with `m6loapp://`.
You can now put this url into the `String#getCodeFromUrl()` function, which will return a code that you can exchange for the access tokens by using `LoginFlow#exchangeTokens()`.


For example:
```kotlin
val authURL = LoginFlow.createAuthURL()

println(authURL.url) // prints url you can use to sign in (ie with a webview or just in the browser)

val code = readln().getCodeFromUrl() // the user then enters the redirect uri after login, and extracts the code

val tokens = LoginFlow.exchangeTokens(code, authURL.codeVerifier) // exchange code for tokens
```

You can then use `TokenResponse#accessToken` to authenticate any future requests, and `TokenResponse#refreshToken` by using `LoginFlow#refreshToken()` to refresh the access tokens.

After retrieving those tokens, you can retrieve information using the flows in `nl.tiebe.magisterapi.api.*`

If any errors occur, the library will throw a `MagisterException` with information on what went wrong.
