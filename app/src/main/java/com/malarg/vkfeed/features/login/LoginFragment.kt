package com.malarg.vkfeed.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.malarg.vkfeed.R
import com.malarg.vkfeed.core.platform.BaseFragment
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKScope
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginFragment: BaseFragment() {

    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = getViewModel()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        view.loginButton.setOnClickListener {
            navigateToLoginFragment()
        }
        return view
    }

    private fun navigateToLoginFragment() {
        VK.login(activity ?: return, arrayListOf(VKScope.WALL, VKScope.PHOTOS))
    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment().apply {}
    }
}
