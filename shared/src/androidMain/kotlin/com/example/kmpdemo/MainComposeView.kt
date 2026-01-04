package com.example.kmpdemo

import android.content.Context
import android.widget.FrameLayout
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.findViewTreeViewModelStoreOwner

class MainComposeView @JvmOverloads constructor(
    context: Context
) : FrameLayout(context) {

    private val composeView = ComposeView(context)

    init {
        composeView.setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed
        )

        addView(
            composeView,
            LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT
            )
        )
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        val lifecycleOwner = findViewTreeLifecycleOwner()
            ?: error("LifecycleOwner missing")

        val viewModelStoreOwner =
            findViewTreeViewModelStoreOwner()
                ?: error("ViewModelStoreOwner not found")

        val viewModel = ViewModelProvider(
            viewModelStoreOwner
        ).get(UserViewModel::class.java)

        composeView.setContent {
            UserScreen(viewModel)
        }
    }
}