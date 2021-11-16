package com.dntk.kimychat.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dntk.kimychat.R
import com.dntk.kimychat.core.base.BaseFragment
import com.dntk.kimychat.databinding.FragmentChatBinding
import com.dntk.kimychat.databinding.FragmentContactsBinding
import com.dntk.kimychat.ui.home.viewmodel.ChatViewModel
import com.dntk.kimychat.ui.home.viewmodel.ContactsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsFragment : BaseFragment<ContactsViewModel, FragmentContactsBinding>() {
    override fun getLayoutResId(): Int = R.layout.fragment_contacts

    override val mViewModel: ContactsViewModel by viewModel()

}