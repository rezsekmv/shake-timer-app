package com.example.beerapp.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.beerapp.R
import com.example.beerapp.databinding.FragmentDetailBinding
import com.example.beerapp.databinding.FragmentListBinding
import com.example.beerapp.ui.list.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater)

        viewModel.beerLiveData.observe(viewLifecycleOwner) {
            binding.tvName.text = viewModel.beerLiveData.value?.name
            binding.tvYear.text = viewModel.beerLiveData.value?.formattedYear
            binding.tvDescription.text = viewModel.beerLiveData.value?.description
            Glide.with(binding.imageView.context)
                .load(viewModel.beerLiveData.value?.image)
                .into(binding.imageView)

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}