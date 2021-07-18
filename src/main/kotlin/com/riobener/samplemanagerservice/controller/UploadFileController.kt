package com.riobener.samplemanagerservice.controller

import com.riobener.samplemanagerservice.entity.Sample
import com.riobener.samplemanagerservice.service.StorageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api")
class UploadFileController {

    @Autowired
    lateinit var fileStorage: StorageService

    /*@GetMapping("/")
    fun index(): String {
        return "multipartfile/uploadform.html"
    }*/
    @GetMapping("/file/hello")
    fun hello(): String {

        return "all good"
    }

    @PostMapping("/file/upload")
    fun uploadMultipartFile(
        @RequestParam("uploadfile") file: MultipartFile, @RequestParam("name")sampleName:String,
        @RequestParam("author")author:String
    ): String {
        var sample:Sample = Sample(null,sampleName,author,"",null)
        fileStorage.store(file, sample)
        // model.addAttribute("message", "File uploaded successfully! -> filename = " + file.getOriginalFilename())
        return "success"
    }

    @GetMapping("/file/{id}")
    fun downloadFile(@PathVariable id:Long): ResponseEntity<Any> {
        val file = fileStorage.loadFile(id)
        if (file != null) {
            return ResponseEntity.ok()
                .body(file)
        }
        return ResponseEntity.badRequest().body("Такого файла нет")
    }
}