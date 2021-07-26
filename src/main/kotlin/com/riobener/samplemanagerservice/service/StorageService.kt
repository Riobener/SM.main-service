package com.riobener.samplemanagerservice.service


import com.riobener.samplemanagerservice.entity.Sample
import org.springframework.core.io.Resource
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Path
import java.util.stream.Stream

interface StorageService {
    fun store(file: MultipartFile, sample: Sample)
    fun loadFile(id: Long): Resource?
    fun deleteAll()
    fun init()
    fun loadFiles(): Stream<Path>
    fun getFiles():Iterable<Sample>
}