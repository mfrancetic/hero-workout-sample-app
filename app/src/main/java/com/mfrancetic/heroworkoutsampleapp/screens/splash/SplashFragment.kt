package com.mfrancetic.heroworkoutsampleapp.screens.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mfrancetic.heroworkoutsampleapp.R
import com.mfrancetic.heroworkoutsampleapp.databinding.SplashFragmentBinding

class SplashFragment : Fragment() {

    private lateinit var binding: SplashFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = SplashFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        navigateToLoginFragment()
    }

    private fun navigateToLoginFragment() {
        findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
    }
}