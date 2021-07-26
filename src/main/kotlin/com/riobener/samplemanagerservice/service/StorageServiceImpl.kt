package com.riobener.samplemanagerservice.service

import antlr.StringUtils
import com.riobener.samplemanagerservice.entity.Sample

import com.riobener.samplemanagerservice.repository.SampleRepository

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils

import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.stream.Stream
import kotlin.io.path.pathString


@Service
class StorageServiceImpl : StorageService {

    @Autowired
    lateinit var sampleRepository: SampleRepository

    val log = LoggerFactory.getLogger(this::class.java)
    val rootLocation = Paths.get("filestorage")

    override fun store(file: MultipartFile, sample: Sample) {
        Files.copy(file.getInputStream(), this.rootLocation.resolve(file.originalFilename))
        val file1 = rootLocation.resolve(file.originalFilename)
        sample.localPath = file1.pathString
        sample.originalName = file.originalFilename.toString()
        sampleRepository.save(sample);
    }

    override fun loadFile(id: Long): Resource? {
        val dbFile:Sample = sampleRepository.findById(id).get()

        val file = rootLocation.resolve(dbFile.originalName)
        val resource = UrlResource(file.toUri())

        if (resource.exists() || resource.isReadable()) {
            return resource
        } else {
            throw RuntimeException("FAIL!")
        }
        return null
    }

    override fun deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile())
    }

    override fun init() {
        Files.createDirectory(rootLocation)
    }

    override fun loadFiles(): Stream<Path> {
        return Files.walk(this.rootLocation, 1)
            .filter { path -> !path.equals(this.rootLocation) }
            .map(this.rootLocation::relativize)
    }

    override fun getFiles(): Iterable<Sample> {
        return sampleRepository.findAll()
    }

}