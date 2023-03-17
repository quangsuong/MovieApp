package net.aedev.movieapp.base.model

data class Error(
    /**
     * Confirm with server to define errors response
     */
    val errorMessage: String?,
    val errorCode: Int?
) {
    companion object {
        const val HTTP_CUSTOM_ERROR_CODE = 400
        const val AUTHORIZE_ERROR_CODE = 401
        const val MAINTENANCE_ERROR_CODE = 503
        const val FORCE_UPDATE_ERROR_CODE = 526
        const val COMMON_ERROR_CODE = 999

        const val COMMON_ERROR_MESSAGE = "Network or Server have some error.\nPlease retry later!"
    }
}
