package com.example.uidesign3.ui.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uidesign3.databinding.FragmentChatBinding

class ChatFragment : Fragment() {
    private var _binding: FragmentChatBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val window = requireActivity().window
        WindowCompat.setDecorFitsSystemWindows(window, false)

        ViewCompat.setOnApplyWindowInsetsListener(view) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val chatList = listOf(
            ChatItem(1, "Haley James", "Stand up for what you believe in", "9"),
            ChatItem(
                2,
                "Nathan Scott",
                "One day you’re seventeen and planning for someday. And then quietly and without...",
                ""
            ),
            ChatItem(3, "Brooke Davis", "I am who I am. No excuses.", "2"),
            ChatItem(
                4,
                "Jamie Scott",
                "Some people are a little different. I think that’s cool.",
                ""
            ),
            ChatItem(
                5,
                "Marvin McFadden",
                "Last night in the NBA the Charlotte Bobcats quietly made a move that most sports fans...",
                ""
            ),
            ChatItem(6, "Antwon Taylor", "Meet me at the Rivercourt", ""),
            ChatItem(
                7,
                "Jake Jagielski",
                "In your life, you’re gonna go to some great places, and do some wonderful things.",
                ""
            ),
            ChatItem(
                8,
                "Peyton Sawyer",
                "Every song ends, is that any reason not to enjoy the music?",
                ""
            )
        )

        val chatAdapter = ChatAdapter(chatList)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}