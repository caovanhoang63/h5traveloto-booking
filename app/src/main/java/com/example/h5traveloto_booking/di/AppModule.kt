package com.example.h5traveloto_booking.di

import android.content.Context
import com.example.h5traveloto_booking.auth.data.remote.api.AuthenticateApi
import com.example.h5traveloto_booking.auth.data.remote.api.CheckExistedApi
import com.example.h5traveloto_booking.auth.data.remote.api.RegisterApi
import com.example.h5traveloto_booking.auth.data.remote.repository.AuthenticateRepositoryImpl
import com.example.h5traveloto_booking.auth.data.remote.repository.CheckExistedRepositoryImpl
import com.example.h5traveloto_booking.auth.data.remote.repository.RegisterRepositoryImpl
import com.example.h5traveloto_booking.auth.domain.repository.AuthenticateRepository
import com.example.h5traveloto_booking.auth.domain.repository.CheckExistedRepository
import com.example.h5traveloto_booking.auth.domain.repository.RegisterRepository
import com.example.h5traveloto_booking.auth.domain.use_case.*
import com.example.h5traveloto_booking.details.presentation.data.api.hotelDetails.HotelDetailsApi
import com.example.h5traveloto_booking.details.presentation.data.api.listRooms.ListRoomsApi
import com.example.h5traveloto_booking.details.presentation.data.api.repository.HotelDetailsRepositoryImpl
import com.example.h5traveloto_booking.details.presentation.data.api.repository.ListRoomsRepositoryImpl
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.domain.repository.HotelDetailsRepository
import com.example.h5traveloto_booking.details.presentation.domain.repository.ListRoomsRepository
import com.example.h5traveloto_booking.details.presentation.domain.usecases.HotelDetailsUseCase
import com.example.h5traveloto_booking.details.presentation.domain.usecases.HotelDetailsUseCases
import com.example.h5traveloto_booking.details.presentation.domain.usecases.ListRoomsUseCase
import com.example.h5traveloto_booking.details.presentation.domain.usecases.ListRoomsUseCases
import com.example.h5traveloto_booking.main.presentation.data.api.Account.ChangePasswordApi
import com.example.h5traveloto_booking.main.presentation.data.api.Account.ProfileApi
import com.example.h5traveloto_booking.main.presentation.data.api.AuthInterceptor.AuthInterceptor
import com.example.h5traveloto_booking.main.presentation.data.api.Hotel.HotelApi
import com.example.h5traveloto_booking.main.presentation.data.api.Hotel.SearchApi
import com.example.h5traveloto_booking.main.presentation.data.api.repository.ChangePasswordRepositoryImpl
import com.example.h5traveloto_booking.main.presentation.data.api.repository.HotelRepositoryImpl
import com.example.h5traveloto_booking.main.presentation.data.api.repository.ProfileRepositoryImpl
import com.example.h5traveloto_booking.main.presentation.data.api.repository.SearchRepositoryImpl
import com.example.h5traveloto_booking.main.presentation.domain.repository.ChangePasswordRepository
import com.example.h5traveloto_booking.main.presentation.domain.repository.HotelRepository
import com.example.h5traveloto_booking.main.presentation.domain.repository.ProfileRepository
import com.example.h5traveloto_booking.main.presentation.domain.repository.SearchRepository
import com.example.h5traveloto_booking.main.presentation.domain.usecases.*
import com.example.h5traveloto_booking.util.Constants
import com.example.h5traveloto_booking.util.SharedPrefManager
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideRegisterApi(moshi: Moshi,okHttpClient: OkHttpClient) : RegisterApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient)
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

    @Provides
    @Singleton
    fun provideCheckExistedApi(moshi: Moshi) : CheckExistedApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(CheckExistedApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCheckExistedRepository(api : CheckExistedApi) : CheckExistedRepository {
        return CheckExistedRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideCheckExistedUsesCases(repository: CheckExistedRepository) : CheckExistedUseCases {
        return CheckExistedUseCases(
            checkExistedUseCase =  CheckExistedUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideSearchApi(moshi: Moshi) : SearchApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(SearchApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSearchRepository(api : SearchApi) : SearchRepository {
        return SearchRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideSearchUsesCases(repository: SearchRepository) : SearchUseCases {
        return SearchUseCases(
            listDistrictsUseCase = ListDistrictsUseCase(repository),
            searchSuggestionUseCase = SearchSuggestionUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideProfileApi(moshi: Moshi) : ProfileApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ProfileApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(api : ProfileApi) : ProfileRepository {
        return ProfileRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideAccountUseCases(repository: ProfileRepository) : AccountUseCases {
        return AccountUseCases(
            getProfileUseCase = ProfileUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideChangePasswordApi(moshi: Moshi) : ChangePasswordApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ChangePasswordApi::class.java)
    }

    @Provides
    @Singleton
    fun provideChangePasswordRepository(api : ChangePasswordApi) : ChangePasswordRepository {
        return ChangePasswordRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun providePasswordUseCases(repository: ChangePasswordRepository) : PasswordUseCases {
        return PasswordUseCases(
            changePasswordUseCases = ChangePasswordUseCase(repository)
        )
    }

    //
    @Provides
    @Singleton
    fun provideHotelDetailsApi(moshi: Moshi,okHttpClient: OkHttpClient) : HotelDetailsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient)
            .build()
            .create(HotelDetailsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHotelDetailsRepository(api : HotelDetailsApi) : HotelDetailsRepository {
        return HotelDetailsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideHotelDetailsUseCases(repository: HotelDetailsRepository) : HotelDetailsUseCases {
        return HotelDetailsUseCases(
            getHotelDetailsUseCase = HotelDetailsUseCase(repository)
        )
    }
    //
    @Provides
    @Singleton
    fun provideListRoomsApi(moshi: Moshi) : ListRoomsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ListRoomsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideListRoomsRepository(api : ListRoomsApi) : ListRoomsRepository {
        return ListRoomsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideListRoomsUseCases(repository: ListRoomsRepository) : ListRoomsUseCases {
        return ListRoomsUseCases(
            getListRoomsUseCase = ListRoomsUseCase(repository)
        )
    }




    /*
    * 
    * 
    * 
    * 
    * 
    * 
    * 
    * 
    * 
    * */

}