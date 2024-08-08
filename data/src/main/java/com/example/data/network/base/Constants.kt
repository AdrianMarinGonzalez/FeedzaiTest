package com.example.data.network.base

import java.net.URLEncoder
import javax.crypto.spec.SecretKeySpec


val feedzaiEndpoint = "whateverEndpoint"

val keyTxt= "SampleEncryptionKey"
val encKey  = URLEncoder.encode(keyTxt, "UTF-8")
val key = SecretKeySpec(encKey.toByteArray(), "AES")