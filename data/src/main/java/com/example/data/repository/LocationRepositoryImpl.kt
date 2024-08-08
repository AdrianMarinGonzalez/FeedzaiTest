package com.example.data.repository

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.example.domain.base.Error
import com.example.domain.base.Failure
import com.example.domain.base.OperationResult
import com.example.domain.base.Success
import com.example.domain.repository.LocationRepository
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import com.example.domain.model.Location as FeedzaiLocation

class LocationRepositoryImpl constructor(val context: Context) : LocationRepository {

    private inline fun locationPermissionEnabled(enabled: () -> Unit, disabled: () -> Unit) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION,
            ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            enabled()
        } else {
            disabled()
        }
    }

    override fun getUserLocation(): OperationResult<FeedzaiLocation, Error> {
        val deferred = CompletableDeferred<OperationResult<FeedzaiLocation, Error>>()

        locationPermissionEnabled(
            enabled = { getCurrentLocation(deferred) },
            disabled = { deferred.complete(Failure(Error.LocationUnavailable())) }
        )

        return runBlocking {
            deferred.await()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation(deferred: CompletableDeferred<OperationResult<FeedzaiLocation, Error>>) {
        LocationServices.getFusedLocationProviderClient(context).getCurrentLocation(
            Priority.PRIORITY_BALANCED_POWER_ACCURACY,
            object : CancellationToken() {
                override fun onCanceledRequested(p0: OnTokenCanceledListener) =
                    CancellationTokenSource().token

                override fun isCancellationRequested() = false
            },
        ).addOnSuccessListener { location: Location? ->
            location?.let {
                deferred.complete(Success(FeedzaiLocation(it.latitude, it.longitude)))
            } ?: run {
                getLastKnownLocation(deferred)
            }
        }
            .addOnFailureListener {
                getLastKnownLocation(deferred)
            }
            .addOnCanceledListener {
                getLastKnownLocation(deferred)
            }
    }

    @SuppressLint("MissingPermission")
    private fun getLastKnownLocation(deferred: CompletableDeferred<OperationResult<FeedzaiLocation, Error>>) {
        LocationServices.getFusedLocationProviderClient(context).lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let {
                    deferred.complete(Success(FeedzaiLocation(it.latitude, it.longitude)))
                } ?: run {
                    deferred.complete(Failure(Error.LocationUnavailable()))
                }
            }
            .addOnFailureListener {
                deferred.complete(Failure(Error.LocationUnavailable()))
            }
            .addOnCanceledListener {
                deferred.complete(Failure(Error.LocationUnavailable()))
            }
    }

}
