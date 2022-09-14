package com.dkb.pocketutil

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableJpaRepositories
class PocketUtilApplication

fun main(args: Array<String>) {
    runApplication<PocketUtilApplication>(*args)
}
