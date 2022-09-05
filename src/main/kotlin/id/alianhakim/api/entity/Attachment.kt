package id.alianhakim.api.entity

import org.hibernate.annotations.Type
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Lob

@Entity
data class Attachment(
    @Id
    @Type(type = "uuid-char")
    var id: UUID = UUID.randomUUID(),
    @Column(name = "file_name")
    val fileName: String,
    @Column(name = "file_type")
    val fileType: String,
    @Lob
    val data: ByteArray
)
