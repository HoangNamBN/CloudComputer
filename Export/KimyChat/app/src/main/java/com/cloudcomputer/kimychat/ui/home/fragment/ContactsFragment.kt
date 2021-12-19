package com.cloudcomputer.kimychat.ui.home.fragment

import com.cloudcomputer.kimychat.R
import com.cloudcomputer.kimychat.core.base.BaseFragment
import com.cloudcomputer.kimychat.databinding.FragmentContactsBinding
import com.cloudcomputer.kimychat.ui.home.viewmodel.ContactsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactsFragment : BaseFragment<ContactsViewModel, FragmentContactsBinding>() {
    override fun getLayoutResId(): Int = R.layout.fragment_contacts

    override val mViewModel: ContactsViewModel by viewModel()

}