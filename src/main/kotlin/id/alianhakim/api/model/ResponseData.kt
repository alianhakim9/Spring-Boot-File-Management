package id.alianhakim.api.model

data class ResponseData(
    val fileName: String,
    val downloadUrl: String,
    val fileType: String,
    val fileSize: Long
)
