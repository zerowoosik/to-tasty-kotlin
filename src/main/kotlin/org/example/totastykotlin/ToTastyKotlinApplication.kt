package org.example.totastykotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.runApplication

//@SpringBootApplication
@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
class ToTastyKotlinApplication

fun main(args: Array<String>) {
    runApplication<ToTastyKotlinApplication>(*args)
}
