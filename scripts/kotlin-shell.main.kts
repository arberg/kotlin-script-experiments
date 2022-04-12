#!/usr/bin/env kotlin

// Auto-complete 'typically' works in this class, but
@file:DependsOn("eu.jrie.jetbrains:kotlin-shell-core:0.2.1")
@file:DependsOn("org.slf4j:slf4j-simple:1.7.28")
@file:CompilerOptions("-opt-in=kotlin.RequiresOptIn")
@file:OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)

import eu.jrie.jetbrains.kotlinshell.shell.*
import java.util.*

val a = ""

enum class OS {
    WINDOWS, LINUX, MAC, SOLARIS
}

// https://www.techiedelight.com/determine-current-operating-system-kotlin/
fun getOS(): OS? {
    val os = System.getProperty("os.name").lowercase()
    return when {
        os.contains("win") -> {
            OS.WINDOWS
        }
        os.contains("nix") || os.contains("nux") || os.contains("aix") -> {
            OS.LINUX
        }
        os.contains("mac") -> {
            OS.MAC
        }
        os.contains("sunos") -> {
            OS.SOLARIS
        }
        else -> null
    }
}

shell {
    if (args.isEmpty()) {
        when (getOS()) {
            OS.WINDOWS -> "cmd /c dir"()
            else -> "ls -l"()
        }
    } else {
        var lines = 0
        var words = 0
        var chars = 0

        var wasSpace = false

        pipeline {
            "cat ${args[0]}".process() pipe
                    streamLambda { strm, _, _ ->
                        while (true) {
                            val byte = strm.read()
                            if (byte < 0) break
                            val ch = byte.toChar()
                            chars++
                            if (ch == '\n') lines++
                            val isSpace = ch == '\n' || ch == '\t' || ch == ' '
                            if (!wasSpace && isSpace) {
                                wasSpace = true
                            } else if (wasSpace && !isSpace) {
                                words++
                                wasSpace = false
                            }
                        }
                    }
        }

        println("My wc:")
        println("$lines $words $chars")
        println("System wc:")
        "wc ${args[0]}"()
    }
}
