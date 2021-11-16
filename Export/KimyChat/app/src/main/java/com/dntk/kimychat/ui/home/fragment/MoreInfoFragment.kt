package com.dntk.kimychat.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dntk.kimychat.R
import com.dntk.kimychat.core.base.BaseFragment
import com.dntk.kimychat.databinding.FragmentChatBinding
import com.dntk.kimychat.databinding.FragmentMoreInfoBinding
import com.dntk.kimychat.ui.home.viewmodel.ChatViewModel
import com.dntk.kimychat.ui.home.viewmodel.MoreInfoViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class MoreInfoFragment : BaseFragment<MoreInfoViewModel, FragmentMoreInfoBinding>() {
    private lateinit var auth : FirebaseAuth
    override fun getLayoutResId(): Int = R.layout.fragment_more_info

    override val mViewModel: MoreInfoViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        mViewBinding.tvNameUser.text = user?.displayName
        mViewBinding.tvEmailUser.text = user?.email
        Glide
            .with(requireContext())
            .load(user?.photoUrl)
            .centerCrop()
            .into(mViewBinding.civAvatarUser)
    }

}