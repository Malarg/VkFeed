package com.malarg.vkfeed.core.platform

import androidx.fragment.app.Fragment
import com.malarg.vkfeed.features.feed.FeedFragment
import com.malarg.vkfeed.features.login.LoginFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    object Login: SupportAppScreen() {
        override fun getFragment(): Fragment {
            return LoginFragment.newInstance()
        }
    }

    object Feed: SupportAppScreen() {
        override fun getFragment(): Fragment {
            return FeedFragment.newInstance()
        }
    }
}