package com.example.data.network.base

import android.provider.Settings
import okhttp3.Interceptor
import okhttp3.Response

class CipherInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        /*
        val textToEncrypt = "This is a sample text"

        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key)

        val iv: ByteArray = cipher.iv
        val ivSpec = IvParameterSpec(iv)

        val encryted_bytes = cipher.doFinal(textToEncrypt.toByteArray())
        val encode_bytes: ByteArray = Base64.encode(encryted_bytes, Base64.DEFAULT)

        val encryptedTxt = String(encode_bytes, Charset.forName("UTF-8"))
*/
        val request = chain.request().newBuilder()
            .build()

        return chain.proceed(request)
    }
}
