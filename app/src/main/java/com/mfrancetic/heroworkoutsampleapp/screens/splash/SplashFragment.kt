package com.mfrancetic.heroworkoutsampleapp.screens.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mfrancetic.heroworkoutsampleapp.R
import com.mfrancetic.heroworkoutsampleapp.databinding.SplashFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    companion object {
        private const val SPLASH_SCREEN_DELAY = 1500L
    }
    private lateinit var binding: SplashFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = SplashFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        GlobalScope.launch(context = Dispatchers.Main) {
            delay(SPLASH_SCREEN_DELAY)
            navigateToLoginFragment()
        }
    }

    private fun navigateToLoginFragment() {
        this.findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
    }
}