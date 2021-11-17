package com.cloudcomputer.kimychat.ui.home.fragment

import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.cloudcomputer.kimychat.R
import com.cloudcomputer.kimychat.core.base.BaseFragment
import com.cloudcomputer.kimychat.databinding.FragmentMoreInfoBinding
import com.cloudcomputer.kimychat.ui.home.viewmodel.MoreInfoViewModel
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