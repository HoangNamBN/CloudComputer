package com.dntk.kimychat.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dntk.kimychat.R
import com.dntk.kimychat.core.base.BaseFragment
import com.dntk.kimychat.databinding.FragmentChatBinding
import com.dntk.kimychat.ui.home.viewmodel.ChatViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : BaseFragment<ChatViewModel, FragmentChatBinding>() {
    override fun getLayoutResId(): Int = R.layout.fragment_chat

    override val mViewModel: ChatViewModel by viewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}