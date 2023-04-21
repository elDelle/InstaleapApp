package com.app.instaleapapp.presentation.bottomnav

import com.app.instaleapapp.R

sealed class BottomNavItem(var icon: Int, var screenRoute: String) {

    object Home : BottomNavItem(R.drawable.ic_home, "home")
}