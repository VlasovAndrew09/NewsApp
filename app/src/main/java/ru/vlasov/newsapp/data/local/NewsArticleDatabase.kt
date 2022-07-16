package ru.vlasov.newsapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NewsArticleDbModel::class],
    version = 1
)
abstract class NewsArticleDatabase : RoomDatabase() {

    abstract fun newsArticleDao(): NewsArticleDao
}