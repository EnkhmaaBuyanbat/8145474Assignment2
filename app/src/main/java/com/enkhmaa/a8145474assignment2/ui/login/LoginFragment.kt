package com.enkhmaa.a8145474assignment2.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.enkhmaa.a8145474assignment2.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            viewModel.login(username, password)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { state ->
                    handleUiState(state)
                }
            }
        }
    }

    private fun handleUiState(state: LoginUiState) {
        binding.progressBar.isVisible = state is LoginUiState.Loading
        binding.errorText.isVisible = state is LoginUiState.Error
        binding.loginButton.isEnabled = state !is LoginUiState.Loading

        when (state) {
            is LoginUiState.Error -> {
                binding.errorText.text = state.message
                // Also show error on specific fields if it's a validation error
                if (state.message.contains("Username", ignoreCase = true)) {
                    binding.usernameLayout.error = state.message
                } else if (state.message.contains("Password", ignoreCase = true)) {
                    binding.passwordLayout.error = state.message
                }
            }
            is LoginUiState.Success -> {
                binding.usernameLayout.error = null
                binding.passwordLayout.error = null
                val action = LoginFragmentDirections.actionLoginFragmentToDashboardFragment(state.keypass)
                findNavController().navigate(action)
            }
            is LoginUiState.Idle -> {
                binding.usernameLayout.error = null
                binding.passwordLayout.error = null
            }
            is LoginUiState.Loading -> {
                binding.usernameLayout.error = null
                binding.passwordLayout.error = null
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
