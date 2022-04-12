#!/usr/bin/env kotlin

@file:Repository("https://jcenter.bintray.com")
@file:DependsOn("org.jetbrains.kotlinx:kotlinx-html-jvm:0.6.11")
@file:Import("toString.kts")
import kotlinx.html.*; import kotlinx.html.stream.*; import kotlinx.html.attributes.*

fun String?.toStringQuoted() = this?.let { "'$this'" } ?: "null"

val addressee: String = args.firstOrNull() ?: "World"
val aString = ""

print(createHTML().html {
    body {
        h1 { +"Hello, $addressee!" }
    }
})

aString.toStringQuoted()