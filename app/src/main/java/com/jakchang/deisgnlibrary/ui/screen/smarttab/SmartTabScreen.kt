package com.jakchang.deisgnlibrary.ui.screen.smarttab

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.jakchang.deisgnlibrary.ui.theme.DeisgnlibraryTheme
import com.jakchang.designmodule.ui.tab.SmartTabLayout

@Composable
fun SmartTabScreen() {
    val pagerState: PagerState = rememberPagerState()

    Column {
        SmartTabLayout(
            pagerState = pagerState,
            tabSize = 5,
            tabTitleGetter = { _index ->
                var tabText = ""
                for (i in 0 until _index) tabText = "$tabText$i"
                "Tab $tabText"
            })

        HorizontalPager(count = 5, state = pagerState) {
            Text(
                modifier = Modifier.fillMaxSize(),
                text = "${pagerState.currentPage}",
                fontSize = 40.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    DeisgnlibraryTheme {
        SmartTabScreen()
    }
}