package id.alianhakim.api.controller

import id.alianhakim.api.entity.Attachment
import id.alianhakim.api.model.ResponseData
import id.alianhakim.api.service.AttachmentService
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.*

@RestController
class AttachmentController constructor(
    private val attachmentService: AttachmentService
) {

    private lateinit var attachment: Attachment

    @PostMapping("/upload")
    fun uploadFile(
        @RequestParam("file") file: MultipartFile
    ): ResponseData {
        var downloadUrl = ""
        attachment = attachmentService.saveAttachment(file)
        downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
            .path("/download/")
            .path(attachment.id.toString())
            .toUriString()
        return ResponseData(
            fileName = attachment.fileName,
            downloadUrl = downloadUrl,
            fileType = file.contentType!!,
            fileSize = file.size
        )
    }

    @GetMapping("/download/{fileId}")
    fun downloadFile(
        @PathVariable fileId: UUID
    ): ResponseEntity<Resource> {
        attachment = attachmentService.getAttachment(fileId)
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(attachment.fileType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=/${attachment.fileName}/")
            .body(ByteArrayResource(attachment.data))
    }
}