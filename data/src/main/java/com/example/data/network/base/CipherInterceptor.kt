package com.example.data.network.base

import android.util.Base64
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.Response
import okio.Buffer
import java.nio.charset.Charset
import javax.crypto.Cipher


class CipherInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()

        val oldBody = request.body
        val buffer = Buffer()
        oldBody?.writeTo(buffer)
        val strOldBody = buffer.readUtf8()
        buffer.clear()
        buffer.close()

        val mediaType = "application/json; charset=UTF-8".toMediaType()
        val body = RequestBody.create(
            mediaType,
            encrypt(strOldBody)
        )
        request = request.newBuilder()
            .header("Content-Type", "application/json")
            .header("Content-Length", body.contentLength().toString())
            .method(request.method, body).build();

        return chain.proceed(request)
    }

    private fun encrypt(content: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key)

        val encryted_bytes = cipher.doFinal(content.toByteArray())
        val encode_bytes: ByteArray = Base64.encode(encryted_bytes, Base64.DEFAULT)

        return String(encode_bytes, Charset.forName("UTF-8"))
    }
}
