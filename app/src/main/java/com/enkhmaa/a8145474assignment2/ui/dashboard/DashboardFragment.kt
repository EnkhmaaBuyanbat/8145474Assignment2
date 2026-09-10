package com.enkhmaa.a8145474assignment2.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.enkhmaa.a8145474assignment2.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DashboardViewModel by viewModels()
    private val args: DashboardFragmentArgs by navArgs()

    private lateinit var adapter: TechnologyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        setupRetryButton()
        
        // Fetch dashboard data using the dynamic keypass passed from login
        viewModel.fetchDashboard(args.keypass)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    handleUiState(state)
                }
            }
        }
    }

    private fun setupRecyclerView() {
        adapter = TechnologyAdapter { technology ->
            // For now, just show a toast. Navigation to details will be implemented later.
            Toast.makeText(requireContext(), "Clicked: ${technology.deviceName}", Toast.LENGTH_SHORT).show()
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun setupRetryButton() {
        binding.retryButton.setOnClickListener {
            viewModel.fetchDashboard(args.keypass)
        }
    }

    private fun handleUiState(state: DashboardUiState) {
        binding.progressBar.isVisible = state is DashboardUiState.Loading
        binding.errorLayout.isVisible = state is DashboardUiState.Error
        binding.recyclerView.isVisible = state is DashboardUiState.Success
        binding.emptyText.isVisible = state is DashboardUiState.Success && state.entities.isEmpty()

        when (state) {
            is DashboardUiState.Success -> {
                adapter.submitList(state.entities)
                binding.toolbar.subtitle = "Total Entities: ${state.total}"
            }
            is DashboardUiState.Error -> {
                binding.errorText.text = state.message
            }
            else -> {}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
