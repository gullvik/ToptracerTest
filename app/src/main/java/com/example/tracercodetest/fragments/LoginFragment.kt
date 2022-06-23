package com.example.tracercodetest.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.tracercodetest.R
import com.example.tracercodetest.databinding.FragmentLoginBinding
import com.example.tracercodetest.viewmodel.SharedViewModel

class LoginFragment : Fragment() {

    private var _binding : FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel : SharedViewModel by activityViewModels()

    private var username : String? = null
    private var password : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin.setOnClickListener {
            username = binding.etUsername.text.toString()
            password = binding.etPassword.text.toString()

            if (username.isNullBlankOrEmpty()) {
                binding.etUsername.error = "Username is required to sign in"
                return@setOnClickListener
            }
            if (!(password == "password")) {
                binding.etPassword.error = "Password is incorrect"
                return@setOnClickListener
            }
            sharedViewModel.loginUser(username!!)
            findNavController().navigate(R.id.HomeFragment)
        }

    }

    private fun String?.isNullBlankOrEmpty(): Boolean {
        return this == null || this.isBlank() || this.isEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}