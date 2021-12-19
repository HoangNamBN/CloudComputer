package com.cloudcomputer.kimychat.ui.home.fragment

import android.os.Bundle
import android.view.View
import com.cloudcomputer.kimychat.R
import com.cloudcomputer.kimychat.core.base.BaseFragment
import com.cloudcomputer.kimychat.databinding.FragmentChatBinding
import com.cloudcomputer.kimychat.ui.home.viewmodel.ChatViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : BaseFragment<ChatViewModel, FragmentChatBinding>() {
    override fun getLayoutResId(): Int = R.layout.fragment_chat

    override val mViewModel: ChatViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}