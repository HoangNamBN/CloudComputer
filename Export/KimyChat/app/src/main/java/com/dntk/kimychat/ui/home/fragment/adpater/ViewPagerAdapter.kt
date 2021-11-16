package com.dntk.kimychat.ui.home.fragment.adpater

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dntk.kimychat.ui.home.fragment.ChatFragment
import com.dntk.kimychat.ui.home.fragment.ContactsFragment
import com.dntk.kimychat.ui.home.fragment.MoreInfoFragment


class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {
//    private val mListFragment =
//        mutableListOf(ChatFragment(), ContactsFragment(), MoreInfoFragment())

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> ChatFragment()
            1 -> ContactsFragment()
            2 -> MoreInfoFragment()
            else -> ChatFragment()
        }
    }
}