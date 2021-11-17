package com.cloudcomputer.kimychat.ui.home

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.cloudcomputer.kimychat.R
import com.cloudcomputer.kimychat.core.base.BaseActivity
import com.cloudcomputer.kimychat.databinding.ActivityHomeBinding
import com.cloudcomputer.kimychat.ui.home.fragment.adpater.ViewPagerAdapter
import com.cloudcomputer.kimychat.ui.splash.SplashViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : BaseActivity<SplashViewModel, ActivityHomeBinding>() {
    override val mViewModel: SplashViewModel by viewModel()

    override fun getLayoutResId(): Int = R.layout.activity_home

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewPagerAdapter = ViewPagerAdapter(this)
        mViewBinding.viewPager.adapter = viewPagerAdapter
        setUpBottomNavigation()
        setupViewPager()
    }

    private fun setupViewPager() {
        mViewBinding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                when (position) {
                    0 -> mViewBinding.bottomNavigation.menu.findItem(R.id.menu_message).isChecked =
                        true
                    1 -> mViewBinding.bottomNavigation.menu.findItem(R.id.menu_contacts).isChecked =
                        true
                    2 -> mViewBinding.bottomNavigation.menu.findItem(R.id.menu_more).isChecked =
                        true
                }
            }
        })
    }

    private fun setUpBottomNavigation() {
        mViewBinding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_message -> {
                    mViewBinding.viewPager.currentItem = 0
                    return@setOnItemSelectedListener true
                }
                R.id.menu_contacts -> {
                    mViewBinding.viewPager.currentItem = 1
                    return@setOnItemSelectedListener true
                }
                R.id.menu_more -> {
                    mViewBinding.viewPager.currentItem = 2
                    return@setOnItemSelectedListener true
                }
            }
            true
        }
    }
}

//    private fun printKeyHash(context: Activity): String? {
//        val packageInfo: PackageInfo
//        var key: String? = null
//        try {
//            //getting application package name, as defined in manifest
//            val packageName = context.applicationContext.packageName
//
//            //Retriving package info
//            packageInfo = context.packageManager.getPackageInfo(
//                packageName,
//                PackageManager.GET_SIGNATURES
//            )
//            Log.e("Package Name=", context.applicationContext.packageName)
//            for (signature in packageInfo.signatures) {
//                val md: MessageDigest = MessageDigest.getInstance("SHA")
//                md.update(signature.toByteArray())
//                key = String(Base64.encode(md.digest(), 0))
//
//                // String key = new String(Base64.encodeBytes(md.digest()));
//                Log.e("Key Hash=", key)
//            }
//        } catch (e1: PackageManager.NameNotFoundException) {
//            Log.e("Name not found", e1.toString())
//        } catch (e: NoSuchAlgorithmException) {
//            Log.e("No such an algorithm", e.toString())
//        } catch (e: Exception) {
//            Log.e("Exception", e.toString())
//        }
//        return key
//    }
//    }