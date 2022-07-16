package ru.vlasov.newsapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.vlasov.newsapp.data.local.NewsArticleDao
import ru.vlasov.newsapp.data.local.NewsArticleDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideNewsArticleDatabase(app: Application): NewsArticleDatabase {
        return Room.databaseBuilder(
            app,
            NewsArticleDatabase::class.java,
            "newsdb.db"
        ).build()
    }

    @Provides
    fun provideMovieDao(newsArticleDatabase: NewsArticleDatabase): NewsArticleDao {
        return newsArticleDatabase.newsArticleDao()
    }
}