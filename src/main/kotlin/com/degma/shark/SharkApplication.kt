package com.degma.shark

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SharkApplication

fun main(args: Array<String>) {
    runApplication<SharkApplication>(*args)
}
