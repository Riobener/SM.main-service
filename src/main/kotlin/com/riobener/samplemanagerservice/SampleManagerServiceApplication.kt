package com.riobener.samplemanagerservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SampleManagerServiceApplication

fun main(args: Array<String>) {
	runApplication<SampleManagerServiceApplication>(*args)
}
