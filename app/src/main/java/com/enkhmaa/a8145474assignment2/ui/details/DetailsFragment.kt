package com.enkhmaa.a8145474assignment2.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.enkhmaa.a8145474assignment2.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val technology = args.technology

        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.deviceNameText.text = technology.deviceName
        binding.manufacturerText.text = technology.manufacturer
        binding.osText.text = technology.operatingSystem
        binding.releaseYearText.text = technology.releaseYear.toString()
        binding.descriptionText.text = technology.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
