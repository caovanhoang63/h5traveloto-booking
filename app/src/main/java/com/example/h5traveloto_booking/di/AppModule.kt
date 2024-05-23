package com.example.h5traveloto_booking.di

import android.content.Context
import android.util.Log
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
import com.example.h5traveloto_booking.chat.presentation.data.api.AllChatApi
import com.example.h5traveloto_booking.chat.presentation.data.api.ChatListApi
import com.example.h5traveloto_booking.chat.presentation.data.api.ChatRoomApi
import com.example.h5traveloto_booking.chat.presentation.data.dto.ChatListDTO
import com.example.h5traveloto_booking.chat.presentation.data.repository.AllChatRepositoryImpl
import com.example.h5traveloto_booking.chat.presentation.data.repository.ChatListRepositoryImpl
import com.example.h5traveloto_booking.chat.presentation.data.repository.ChatRoomRepositoryImpl
import com.example.h5traveloto_booking.chat.presentation.domain.repository.AllChatRepository
import com.example.h5traveloto_booking.chat.presentation.domain.repository.ChatListRepository
import com.example.h5traveloto_booking.chat.presentation.domain.repository.ChatRoomRepository
import com.example.h5traveloto_booking.chat.presentation.domain.usecases.*
import com.example.h5traveloto_booking.details.presentation.data.api.HotelFacilitiesDetails.HotelFacilitiesDetailsApi
import com.example.h5traveloto_booking.details.presentation.data.api.RoomFacilitiesDetails.RoomFacilitiesDetailsApi
import com.example.h5traveloto_booking.details.presentation.data.api.hotelDetails.HotelDetailsApi
import com.example.h5traveloto_booking.details.presentation.data.api.listRooms.ListRoomsApi
import com.example.h5traveloto_booking.details.presentation.data.api.repository.*
import com.example.h5traveloto_booking.details.presentation.data.api.reviews.ListReviewsApi
import com.example.h5traveloto_booking.details.presentation.data.dto.hotelDetails.HotelDetailsDTO
import com.example.h5traveloto_booking.details.presentation.domain.repository.*
import com.example.h5traveloto_booking.details.presentation.domain.usecases.*
import com.example.h5traveloto_booking.main.presentation.data.api.Account.ChangePasswordApi
import com.example.h5traveloto_booking.main.presentation.data.api.Account.ProfileApi
import com.example.h5traveloto_booking.main.presentation.data.api.AuthInterceptor.AuthInterceptor
import com.example.h5traveloto_booking.main.presentation.data.api.Account.UploadApi
import com.example.h5traveloto_booking.main.presentation.data.api.Booking.BookingApi
import com.example.h5traveloto_booking.main.presentation.data.api.Favorite.FavoriteApi
import com.example.h5traveloto_booking.main.presentation.data.api.Hotel.HotelApi
import com.example.h5traveloto_booking.main.presentation.data.api.Hotel.SearchApi
import com.example.h5traveloto_booking.main.presentation.data.api.repository.*
import com.example.h5traveloto_booking.main.presentation.domain.repository.*
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
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return try {
            OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build()
        } catch (e: Exception) {
            // Handle the exception here
            // You can log the exception, show an error message, or take appropriate actions
            Log.d("OkHttpClient error",e.printStackTrace().toString())
            // Return a default OkHttpClient instance or throw a custom exception
            OkHttpClient.Builder().build()
        }
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun provideRegisterApi(moshi: Moshi, okHttpClient: OkHttpClient): RegisterApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient)
            .build()
            .create(RegisterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRegisterRepository(api: RegisterApi): RegisterRepository {
        return RegisterRepositoryImpl(api)
    }


    @Provides
    @Singleton
    fun provideRegisterUsesCases(repository: RegisterRepository): RegisterUseCases {
        return RegisterUseCases(
            registerUseCase = RegisterUsesCase(repository)
        )
    }


    @Provides
    @Singleton
    fun provideAuthenticateApi(moshi: Moshi): AuthenticateApi {
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
            renewTokenUseCase = RenewTokenUseCase(repository),
            refreshTokenUseCase = RefreshTokenUseCase(repository)
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
    fun provideHotelUsesCases(repository: HotelRepository): HotelUseCases {
        return HotelUseCases(
            listHotelUseCase = ListHotelUseCase(repository),
            clickHotelUseCase = ClickHotelUseCase(repository)
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
            searchSuggestionUseCase = SearchSuggestionUseCase(repository),
            searchHotelUseCase = SearchHotelUseCase(repository),
            searchRoomTypeUseCase = SearchRoomTypeUseCase(repository),
            getProminentHotelUseCase = GetProminentHotelUseCase(repository),
            searchViewedHotelsUseCase = SearchViewedHotelsUseCase(repository)
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
            getProfileUseCase = ProfileUseCase(repository),
            updateProfileUseCase = UpdateProfileUseCase(repository)
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


    @Provides
    @Singleton
    fun provideUploadApi(moshi: Moshi) : UploadApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(UploadApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUploadRepository(api : UploadApi) : UploadRepository {
        return UploadRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideUploadUseCases(repository: UploadRepository) : UploadUseCases {
        return UploadUseCases(
            uploadFileUseCase = UploadFileUseCase(repository)
        )
    }

    //
    @Provides
    @Singleton
    fun provideChatList(moshi: Moshi,okHttpClient: OkHttpClient) : ChatListApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient)
            .build()
            .create(ChatListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideChatListRepository(api : ChatListApi) : ChatListRepository {
        return ChatListRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideListChatUseCases(repository: ChatListRepository) :ChatListUseCases {
        return ChatListUseCases(
            getChatListUseCase = ChatListUseCase(repository)
        )
    }
    @Provides
    @Singleton
    fun provideSocketHandler() : websocket.SocketHandler {
        return websocket.SocketHandler()
    }


    //
    @Provides
    @Singleton
    fun provideBookingApi(moshi: Moshi,okHttpClient: OkHttpClient) : BookingApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient)
            .build()
            .create(BookingApi::class.java)
    }

    @Provides
    @Singleton
    fun provideBookingRepository(api : BookingApi) : BookingRepository {
        return BookingRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideListUserBookingUseCases(repository: BookingRepository) : ListUserBookingUseCases {
        return ListUserBookingUseCases(
            getListUserBookingUseCase = ListUserBookingUseCase(repository)
        )
    }
    //
    @Provides
    @Singleton
    fun provideListReviews(moshi: Moshi,okHttpClient: OkHttpClient) : ListReviewsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient)
            .build()
            .create(ListReviewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideListReviewsRepository(api : ListReviewsApi) : ListReviewsRepository {
        return ListReviewsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideListReviewsUseCases(repository: ListReviewsRepository) :ListReviewsUseCases {
        return ListReviewsUseCases(
            geListReviewsUseCases = ListReviewsUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideFavoriteApi(moshi: Moshi,okHttpClient: OkHttpClient) : FavoriteApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient)
            .build()
            .create(FavoriteApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFavoriteRepository(api : FavoriteApi) : FavoriteRepository {
        return FavoriteRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideFavoriteUseCase(repository: FavoriteRepository) : FavoriteUseCases {
        return FavoriteUseCases(
            getCollectionUseCase = CollectionUseCase(repository),
            getAllSavedHotelsUseCase = AllSavedHotelsUseCase(repository),
            unsaveHotelUseCase = UnSaveHotelUseCase(repository),
            getHotelsByCollectionIdUseCase = GetHotelsByCollectionIdUseCase(repository),
            deleteHotelUseCase = DeleteHotelUseCase(repository),
            addHotelUseCase = AddHotelUseCase(repository),
            createCollectionUseCase = CreateCollectionUseCase(repository),
            deleteCollectionUseCase = DeleteCollectionUseCase(repository),
            updateCollectionUseCase = UpdateCollectionUseCase(repository),
            getCollectionByCollectionIdUseCase = GetCollectionByCollectionIdUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideBookingUseCases(repository: BookingRepository): BookingUseCases {
        return BookingUseCases(
            bookingUseCase = BookingUseCase(repository),
            getBookingUseCase = GetBookingUseCase(repository)
        )
    }

    //
    @Provides
    @Singleton

    fun provideRoomFacilitiesDetails(moshi: Moshi): RoomFacilitiesDetailsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RoomFacilitiesDetailsApi::class.java)
    }

    @Provides
    @Singleton

    fun provideRoomFacilitiesDetailsRepository(api: RoomFacilitiesDetailsApi): RoomFacilitiesDetailsRepository {
        return RoomFacilitiesDetailsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideRoomFacilitiesDetailsUseCases(repository: RoomFacilitiesDetailsRepository): RoomFacilitiesDetailsUseCases {
        return RoomFacilitiesDetailsUseCases(
             getRoomFacilitiesDetailsUseCase = RoomFacilitiesDetailsUseCase(repository)
        )
    }
    //
    //
    @Provides
    @Singleton
    fun provideChatRoom(moshi: Moshi,okHttpClient: OkHttpClient): ChatRoomApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient)
            .build()
            .create(ChatRoomApi::class.java)
    }

    @Provides
    @Singleton
    fun provideChatRoomRepository(api: ChatRoomApi): ChatRoomRepository {
        return ChatRoomRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideChatRoomUseCases(repository: ChatRoomRepository): ChatRoomUseCases {
        return ChatRoomUseCases(
            getChatRoomUseCase = ChatRoomUseCase(repository)
        )
    }
    //
    @Provides
    @Singleton
    fun provideAllChat(moshi: Moshi,okHttpClient: OkHttpClient): AllChatApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient)
            .build()
            .create(AllChatApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAllChatRepository(api: AllChatApi): AllChatRepository {
        return AllChatRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideAllChatUseCases(repository: AllChatRepository): AllChatUseCases {
        return AllChatUseCases(
            getAllChatUseCase = AllChatUseCase(repository)
        )
    }
    //
    @Provides
    @Singleton
    fun provideHotelFacilitiesDetails(moshi: Moshi,okHttpClient: OkHttpClient): HotelFacilitiesDetailsApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi)).client(okHttpClient)
            .build()
            .create(HotelFacilitiesDetailsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHotelFacilitiesDetailsRepository(api: HotelFacilitiesDetailsApi): HotelFacilitiesDetailsRepository {
        return HotelFacilitiesDetailsRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideHotelFacilitiesDetailsUseCases(repository: HotelFacilitiesDetailsRepository): HotelFacilitiesDetailsUseCases {
        return HotelFacilitiesDetailsUseCases(
            getHotelFacilitiesDetailsUseCase = HotelFacilitiesDetailsUseCase(repository)
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