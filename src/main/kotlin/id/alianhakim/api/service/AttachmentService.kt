package id.alianhakim.api.service

import id.alianhakim.api.entity.Attachment
import org.springframework.web.multipart.MultipartFile
import java.util.*


interface AttachmentService {
    fun saveAttachment(file: MultipartFile): Attachment
    fun getAttachment(fileId: UUID): Attachment
}