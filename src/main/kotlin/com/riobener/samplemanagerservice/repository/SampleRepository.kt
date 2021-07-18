package com.riobener.samplemanagerservice.repository

import com.riobener.samplemanagerservice.entity.Sample
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SampleRepository:JpaRepository<Sample,Long> {

}