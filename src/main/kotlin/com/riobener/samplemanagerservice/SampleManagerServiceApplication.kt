package com.riobener.samplemanagerservice

import com.riobener.samplemanagerservice.service.StorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class SampleManagerServiceApplication

fun main(args: Array<String>) {
	runApplication<SampleManagerServiceApplication>(*args)
}
@Autowired
lateinit var fileStorage: StorageService;

@Bean
fun run() = CommandLineRunner {
	fileStorage.deleteAll()
	fileStorage.init()
}