package com.cloudcomputer.kimychat.di

import com.cloudcomputer.kimychat.ui.home.viewmodel.ChatViewModel
import com.cloudcomputer.kimychat.ui.home.viewmodel.ContactsViewModel
import com.cloudcomputer.kimychat.ui.home.viewmodel.MoreInfoViewModel
import com.cloudcomputer.kimychat.ui.modulelogin.LoginViewModel
import com.cloudcomputer.kimychat.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SplashViewModel() }
    viewModel { LoginViewModel() }
    viewModel { ChatViewModel() }
    viewModel { ContactsViewModel() }
    viewModel { MoreInfoViewModel() }
}
