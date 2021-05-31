package com.example.androidcase.ui.fragment.splash

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidcase.R
import kotlinx.android.synthetic.main.fragment_splash.*


class SplashFragment() : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)

    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {

        events()
    }

    private fun events() {
        splashAnimation.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                try {
                    findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
                } catch (ex: Exception) {
                    ex.toString()
                }
            }

            override fun onAnimationCancel(animation: Animator?) {

            }

            override fun onAnimationRepeat(animation: Animator?) {

            }
        })
    }
}