package com.riobener.samplemanagerservice.entity

import javax.persistence.*

@Entity
@Table(name = "sample_storage")
data class Sample(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    var id: Long?,

    @Column(name = "sample_name")
    var sampleName: String,

    @Column(name = "author")
    var author: String,

    @Column(name = "original_name")
    var originalName: String,

    @Column(name = "local_path")
    var localPath: String?
)


