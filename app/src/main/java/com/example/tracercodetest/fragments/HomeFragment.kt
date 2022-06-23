package com.example.tracercodetest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.tracercodetest.R
import com.example.tracercodetest.databinding.FragmentHomeBinding
import com.example.tracercodetest.repository.ServiceRepository
import com.example.tracercodetest.viewmodel.HomeViewModel
import com.example.tracercodetest.viewmodel.SharedViewModel

class HomeFragment : Fragment() {

    private val sharedViewModel : SharedViewModel by activityViewModels()
    private lateinit var homeViewModel : HomeViewModel

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        sharedViewModel.username.observe(this) {
            binding.tvUsername.text = "Welcome $it!"
        }
        homeViewModel = HomeViewModel(ServiceRepository())
        homeViewModel.response.observe(viewLifecycleOwner) { giphy ->
            Glide.with(requireContext())
                .asGif()
                .load(giphy.data.images.original.url)
                .into(binding.giphyGif)
        }
        homeViewModel.imageResponse.observe(viewLifecycleOwner) { photoResponse ->
            val photo = photoResponse.photos.photo[0]
            val serverId = photo.server
            val photoId = photo.id
            val secret = photo.secret
            val underscore = "_"
            val size = "w"
            val baseUrl = "https://live.staticflickr.com"
            val imgUri = "$baseUrl/$serverId/$photoId$underscore$secret$underscore$size.jpg"
            Glide.with(requireContext())
                .load(imgUri)
                .into(binding.giphyGif)
        }
        homeViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            when (isLoading) {
                true -> {
                    binding.pbGiphy.visibility = View.VISIBLE
                    binding.giphyGif.visibility = View.GONE
                }
                false -> {
                    binding.pbGiphy.visibility = View.GONE
                    binding.giphyGif.visibility = View.VISIBLE
                }
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogout.setOnClickListener {
            findNavController().navigate(R.id.LoginFragment)
        }

        //homeViewModel.getImage(sharedViewModel.username.value)
        homeViewModel.getGiphy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}