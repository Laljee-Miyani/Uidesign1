package com.example.uidesign3.ui.chat

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uidesign3.R
import com.example.uidesign3.databinding.ActivityMessagesBinding

class MessagesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMessagesBinding
    private val messages = mutableListOf<Message>()
    private lateinit var adapter: MessageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMessagesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val imeInsets = insets.getInsets(WindowInsetsCompat.Type.ime())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            binding.inputLayout.translationY = -imeInsets.bottom.toFloat()
            insets
        }
        messages.add(Message("Hey Lucas!", false, "Brooke", true))
        messages.add(Message("How's your project going?", false, "Brooke", false))
        messages.add(Message("Hi Brooke!", true, "Lucas", true))
        messages.add(Message("It's going well. Thanks for asking!", true, "Lucas", false))
        messages.add(
            Message(
                "No worries. Let me know if you need any help 😉", false, "Brooke", true
            )
        )
        messages.add(Message("You're the best!", true, "Lucas", true))

        adapter = MessageAdapter(messages)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.chat_margin_vertical)
        binding.recyclerView.addItemDecoration(MessageItemDecoration(spacingInPixels))

        binding.btnSend.setOnClickListener {
            val text = binding.msgInput.text.toString().trim()
            if (text.isNotEmpty()) {
                val isFirstMessage = messages.isEmpty() || !messages.last().isSent
                messages.add(Message(text, true, "Lucas", isFirstMessage))
                adapter.notifyItemInserted(messages.size - 1)
                binding.recyclerView.post {
                    binding.recyclerView.smoothScrollToPosition(messages.size - 1)
                }
                binding.msgInput.setText("")
            }
        }

        binding.btnSend.visibility = View.GONE

        binding.msgInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    binding.btnSend.visibility = View.GONE
                } else {
                    binding.btnSend.visibility = View.VISIBLE
                }
            }
        })
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}