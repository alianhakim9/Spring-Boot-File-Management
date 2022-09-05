package id.alianhakim.api.repository

import id.alianhakim.api.entity.Attachment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AttachmentRepository : JpaRepository<Attachment, UUID> {
}