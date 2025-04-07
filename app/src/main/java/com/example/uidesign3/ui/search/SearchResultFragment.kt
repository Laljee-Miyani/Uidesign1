package com.example.uidesign3.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.uidesign3.R
import com.example.uidesign3.databinding.FragmentSearchResultBinding

class SearchResultFragment : Fragment() {
    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!
    private var searchQuery: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            searchQuery = it.getString(ARG_SEARCH_QUERY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.rvSearchResults)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        val products = listOf(
            Product("Amazing Shoes", 12.00, "https://picsum.photos/200?random=1"),
            Product("Fabulous Shoes", 15.00, "https://picsum.photos/200?random=2"),
            Product("Fantastic Shoes", 15.00, "https://picsum.photos/200?random=3"),
            Product("Spectacular Shoes", 12.00, "https://picsum.photos/200?random=4"),
            Product("Stunning Shoes", 12.00, "https://picsum.photos/200?random=5"),
            Product("Wonderful Shoes", 15.00, "https://picsum.photos/200?random=6")
        )
        recyclerView.adapter = ProductAdapter(products)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_SEARCH_QUERY = "search_query"

        @JvmStatic
        fun newInstance(query: String) = SearchResultFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_SEARCH_QUERY, query)
            }
        }
    }
}