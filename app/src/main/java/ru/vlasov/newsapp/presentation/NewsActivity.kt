package ru.vlasov.newsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.vlasov.newsapp.R
import ru.vlasov.newsapp.databinding.ActivityNewsBinding
import ru.vlasov.newsapp.domain.NewsArticle

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navController = findNavController(R.id.newsNavHostFragment)
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}