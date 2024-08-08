package com.example.data.repository

import com.example.domain.model.Language
import com.example.domain.model.Timezone
import com.example.domain.repository.DeviceInfoRepository
import java.util.Locale
import java.util.TimeZone


class DeviceInfoRepositoryImpl : DeviceInfoRepository {
    override fun getLanguage() = Language(Locale.getDefault().toLanguageTag())
    override fun getTimezone() = Timezone(TimeZone.getDefault().displayName)
}