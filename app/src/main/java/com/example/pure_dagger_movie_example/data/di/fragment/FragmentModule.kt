package com.example.pure_dagger_movie_example.data.di.fragment


import com.bumptech.glide.RequestManager
import com.example.pure_dagger_movie_example.data.di.scopes.PerFragment
import com.example.pure_dagger_movie_example.data.network.DetailsApi
import com.example.pure_dagger_movie_example.ui.main.fragment.cast_details.CastRepository
import com.example.pure_dagger_movie_example.ui.main.fragment.details.CastAdapter
import com.example.pure_dagger_movie_example.ui.main.fragment.details.CastClicked
import com.example.pure_dagger_movie_example.ui.main.fragment.details.DetailsRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class FragmentModule/* constructor(
    private val id: Int
    , private val item: CastClicked
    , private val creditId: String
)*/{

 /*   @Provides
    @PerFragment
    fun providesMovieId(): Int {
        return id
    }

    @Provides
    @PerFragment
    fun providesCastClicked(): CastClicked {
        return item

    }

    @Provides
    @PerFragment
    fun providesCreditId(): String {
        return creditId
    }*/

    @Provides
    @PerFragment
    fun providesMovieDetailsApi(retrofit: Retrofit): DetailsApi {
        return retrofit.create(DetailsApi::class.java)
    }

    @Provides
    @PerFragment
    fun providesDetailsRepository(api: DetailsApi, id: Int): DetailsRepository {
        return DetailsRepository(api, id)
    }

    @Provides
    @PerFragment
    fun providesCastCreditsRepository(api: DetailsApi, creditId: String): CastRepository {
        return CastRepository(api, creditId)
    }

    @Provides
    @PerFragment
    fun providesCastAdapter(requestManager: RequestManager, item: CastClicked): CastAdapter {
        return CastAdapter(requestManager, item)
    }
}