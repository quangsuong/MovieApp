package net.aedev.movieapp.base.api.datahandling

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import io.reactivex.observers.DisposableObserver
import retrofit2.HttpException
import net.aedev.movieapp.base.model.Error

abstract class ResultsObserver<T> : DisposableObserver<Result<T>>() {

    final override fun onComplete() {}

    final override fun onNext(t: Result<T>) {
        when {
            t.data != null -> onSuccess(t.data)
            t.error != null -> onError(t.error)
        }
    }

    final override fun onError(e: Throwable) {
        e.printStackTrace()
        onError(convertError(e))
    }

    /**
     * Callback for handling Http response success (statusCode == 200)
     */
    abstract fun onSuccess(success: T)
    abstract fun onError(error: Error)

    private fun convertError(error: Throwable?): Error {
        error?.printStackTrace()
        return when (error) {
            is HttpException -> parserErrorFromServer(error.response()?.errorBody()?.string().toString())
            else -> Error(Error.COMMON_ERROR_MESSAGE, Error.COMMON_ERROR_CODE)
        }
    }

    private fun parserErrorFromServer(errorBody: String): Error {
        val moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<Error> = moshi.adapter(Error::class.java)
        try {
            return adapter.fromJson(errorBody) ?: return Error(Error.COMMON_ERROR_MESSAGE, Error.COMMON_ERROR_CODE)
        } catch (e: Exception) {
            e.printStackTrace()
            return Error(Error.COMMON_ERROR_MESSAGE, Error.COMMON_ERROR_CODE)
        }
    }
}
