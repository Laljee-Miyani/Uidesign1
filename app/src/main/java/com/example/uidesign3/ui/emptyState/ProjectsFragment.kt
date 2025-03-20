package com.example.uidesign3.ui.emptyState

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.uidesign3.R
import com.example.uidesign3.databinding.FragmentProjectsBinding
import com.google.android.material.tabs.TabLayout

class ProjectsFragment : Fragment() {
    private var _binding: FragmentProjectsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTabs()
    }

    private fun setupTabs() {
        val tabTitles = listOf("To do", "In progress", "Finished")

        binding.tabLayout.removeAllTabs()

        tabTitles.forEachIndexed { index, title ->
            val tab = binding.tabLayout.newTab().setText(title)
            binding.tabLayout.addTab(tab, index == 0)
        }

        applyTabBackground(binding.tabLayout.getTabAt(binding.tabLayout.selectedTabPosition))

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.view?.setBackgroundResource(R.drawable.vector_tab_selected_bg)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                tab?.view?.setBackgroundResource(android.R.color.transparent)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })

        addTabDividers()
    }

    private fun applyTabBackground(tab: TabLayout.Tab?) {
        tab?.view?.setBackgroundResource(R.drawable.vector_tab_selected_bg)
    }

    private fun addTabDividers() {
        val root = binding.tabLayout.getChildAt(0)
        if (root is LinearLayout) {
            root.showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            root.dividerPadding = 10
            val drawable = GradientDrawable().apply {
                setColor(ContextCompat.getColor(requireContext(), R.color.tab_divider))
                setSize(1, 1)
            }
            root.dividerDrawable = drawable
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
