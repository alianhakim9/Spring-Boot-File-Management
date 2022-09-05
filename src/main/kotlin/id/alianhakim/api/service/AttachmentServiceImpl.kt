package id.alianhakim.api.service

import id.alianhakim.api.entity.Attachment
import id.alianhakim.api.repository.AttachmentRepository
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
class AttachmentServiceImpl constructor(
    private val attachmentRepository: AttachmentRepository
) : AttachmentService {
    override fun saveAttachment(file: MultipartFile): Attachment {
        val fileName = StringUtils.cleanPath(file.originalFilename ?: "empty file name")
        try {
            if (fileName.contains("..")) {
                throw Exception("Filename contains invalid path sequence")
            }
            if (file.contentType == null) {
                throw Exception("content type is null")
            }

            val attachment = Attachment(
                fileName = file.name,
                fileType = file.contentType!!,
                data = file.bytes
            )
            return attachmentRepository.save(attachment)
        } catch (e: Exception) {
            println(e.message)
            throw Exception("could not save file: $fileName")
        }
    }

    override fun getAttachment(fileId: UUID): Attachment {
        return attachmentRepository.findById(fileId)
            .orElseThrow { Exception("File not found with id : $fileId") }
    }
}