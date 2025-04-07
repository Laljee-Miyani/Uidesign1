package com.example.uidesign3.ui.search

import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.uidesign3.R
import com.example.uidesign3.databinding.ActivitySearchBinding

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val searchEditText = binding.root.findViewById<EditText>(R.id.etsearch)

        searchEditText.setOnEditorActionListener { _, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                (event?.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
            ) {
                val query = searchEditText.text.toString()
                if (query.isNotEmpty()) {
                    showSearchResultFragment(query)
                    addSearchToHistory(query)
                }
                return@setOnEditorActionListener true
            }
            false
        }

        if (savedInstanceState == null) {
            showSearchHistoryFragment()
        }
    }

    private fun showSearchHistoryFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, SearchHistoryFragment())
            .commit()
    }

    private fun showSearchResultFragment(query: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, SearchResultFragment.newInstance(query))
            .addToBackStack(null)
            .commit()
    }

    private fun addSearchToHistory(query: String) {
        val fragment = supportFragmentManager.findFragmentById(R.id.frameLayout)
        if (fragment is SearchHistoryFragment) {
            fragment.addSearch(query)
        }
    }
}
