#!/usr/bin/env kotlin

// @file:Import("functions/toString.main.kts") // works when executed
@file:Import("functions/toString.kts") // works
// @file:Import("toString.kts") // works


val ab = ""
val a = ""

// Run: kotlin hello.main.kts world
if (args.isNotEmpty()){
	println("Hello, ${args[0]}")
} else {
	println("Hello, Silent One")
}

val test1 = "Hello World"
println("Test Quoted string accessed via common imported function: " + test1.toStringQuoted())