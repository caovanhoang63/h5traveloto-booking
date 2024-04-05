package com.example.h5traveloto_booking.di

import android.content.Context
import com.example.h5traveloto_booking.auth.data.remote.api.AuthenticateApi
import com.example.h5traveloto_booking.auth.data.remote.api.RegisterApi
import com.example.h5traveloto_booking.auth.data.remote.repository.AuthenticateRepositoryImpl
import com.example.h5traveloto_booking.auth.data.remote.repository.RegisterRepositoryImpl
import com.example.h5traveloto_booking.auth.domain.repository.AuthenticateRepository
import com.example.h5traveloto_booking.auth.domain.repository.RegisterRepository
import com.example.h5traveloto_booking.auth.domain.use_case.*
import com.example.h5traveloto_booking.main.presentation.data.api.Hotel.HotelApi
import com.example.h5traveloto_booking.main.presentation.data.api.repository.HotelRepositoryImpl
import com.example.h5traveloto_booking.main.presentation.domain.repository.HotelRepository
import com.example.h5traveloto_booking.main.presentation.domain.usecases.HotelUseCases
import com.example.h5traveloto_booking.main.presentation.domain.usecases.ListHotelUseCase
import com.example.h5traveloto_booking.util.Constants
import com.example.h5traveloto_booking.util.SharedPrefManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideRegisterApi(moshi: Moshi) : RegisterApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RegisterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRegisterRepository(api : RegisterApi) : RegisterRepository {
        return RegisterRepositoryImpl(api)
    }


    @Provides
    @Singleton
    fun provideRegisterUsesCases(repository: RegisterRepository) : RegisterUseCases {
        return RegisterUseCases(
            registerUseCase =  RegisterUsesCase(repository)
        )
    }


    @Provides
    @Singleton
    fun provideAuthenticateApi(moshi: Moshi) : AuthenticateApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(AuthenticateApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthenticateRepository(api : AuthenticateApi) : AuthenticateRepository {
        return AuthenticateRepositoryImpl(api)
    }


    @Provides
    @Singleton
    fun provideAuthenticateUsesCases(repository: AuthenticateRepository) : AuthenticateUseCases {
        return AuthenticateUseCases(
            authenticateUseCase =  AuthenticateUseCase(repository),
            renewTokenUseCase = RenewTokenUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideHotelApi(moshi: Moshi) : HotelApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(HotelApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHotelRepository(api : HotelApi) : HotelRepository {
        return HotelRepositoryImpl(api)
    }


    @Provides
    @Singleton
    fun provideHotelUsesCases(repository: HotelRepository) : HotelUseCases {
        return HotelUseCases(
            listHotelUseCase = ListHotelUseCase(repository)
        )
    }





    @Provides
    @Singleton
    fun provideSharedPrefManager(@ApplicationContext context: Context): SharedPrefManager {
        return SharedPrefManager(context)
    }



}