package com.dntk.kimychat.di

import com.dntk.kimychat.ui.home.viewmodel.ChatViewModel
import com.dntk.kimychat.ui.home.viewmodel.ContactsViewModel
import com.dntk.kimychat.ui.home.viewmodel.MoreInfoViewModel
import com.dntk.kimychat.ui.modulelogin.LoginViewModel
import com.dntk.kimychat.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { LoginViewModel() }
    viewModel { ChatViewModel() }
    viewModel { ContactsViewModel() }
    viewModel { MoreInfoViewModel() }
}
