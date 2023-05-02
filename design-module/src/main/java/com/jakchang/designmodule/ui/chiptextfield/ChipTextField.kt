package com.jakchang.designmodule.ui.chiptextfield

import android.text.Editable
import android.text.Spannable
import android.text.TextWatcher
import android.view.Gravity
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.viewinterop.AndroidView
import com.jakchang.deisgnlibrary.utils.getDpToPx
import java.util.regex.Pattern


@Composable
fun ChipTextField(
    modifier: Modifier = Modifier,
    editText: EditText,
    tagList: List<TagData>
) {
    val density = LocalDensity.current.density

    Column(
        modifier = modifier
    ) {
        AndroidView(
            factory = {
                editText.apply {
                    layoutParams = LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    gravity = Gravity.TOP
                    background = null
                    textSize = 16f

                    setLineSpacing(density * 6, 1f)

                    val editTextPadding = context.getDpToPx(12f).toInt()
                    setPadding(
                        editTextPadding,
                        editTextPadding,
                        editTextPadding,
                        editTextPadding
                    )

                    setOnTouchListener { view, motionEvent ->
                        if (view.id == editText.id) {
                            view.parent.requestDisallowInterceptTouchEvent(true)
                            when (motionEvent.action and MotionEvent.ACTION_MASK) {
                                MotionEvent.ACTION_UP -> {
                                    view.parent.requestDisallowInterceptTouchEvent(false)
                                }
                            }
                        }
                        false
                    }

                    addTextChangedListener(object :
                        TextWatcher {
                        override fun beforeTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {
                        }

                        override fun onTextChanged(
                            p0: CharSequence?,
                            p1: Int,
                            p2: Int,
                            p3: Int
                        ) {
                        }

                        override fun afterTextChanged(
                            editableText: Editable
                        ) {
                            highlightTagText(
                                editText = this@apply,
                                editableText = editableText,
                                tagList = tagList,
                                density = density
                            )
                        }
                    })
                }
            })
    }
}


data class TagData(val tagText: String, val tagTextColor: Color, val tagBackgroundColor: Color)

private fun highlightTagText(
    editText: EditText,
    editableText: Editable,
    tagList: List<TagData>,
    density: Float
) {
    // 기존에 설정해놓은 span 제거
    editableText.let { editable ->
        val spans = editable.getSpans(
            0,
            editable.length,
            RoundedBackgroundSpan::class.java
        )
        spans.forEach { span ->
            editable.removeSpan(span)
        }
    }

    val tagPatternRegex = makeTagPattern(tagList.map { it.tagText })
    val inputText = editableText.toString()
    val tagPattern = Pattern.compile(tagPatternRegex)
    val tagMatcher = tagPattern.matcher(inputText)

    while (tagMatcher.find()) {
        val startIndex = tagMatcher.start()
        val endIndex = tagMatcher.end()
        val filteredText = inputText.substring(startIndex, endIndex)
        val filteredTag = tagList.filter { it.tagText == filteredText }.getOrNull(0)

        filteredTag?.let { _filteredTag ->
            val span = com.jakchang.designmodule.ui.chiptextfield.RoundedBackgroundSpan(
                textColor = _filteredTag.tagTextColor.toArgb(),
                backgroundColor = _filteredTag.tagBackgroundColor.toArgb(),
                density = density
            )

            editableText.setSpan(
                span,
                startIndex,
                endIndex,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            // 태그가 아닌 글자로 만든 후 글자를 지워 태그로 만들면
            // 커서 위치가 가운데로 유지되는 이슈가 있어 맨 마지막으로 커서 위치이동
            if (editText.selectionStart in startIndex..endIndex) {
                editText.setSelection(endIndex)
            }
        }
    }
}

private fun makeTagPattern(tagList: List<String>): String {
    return tagList
        .map { "($it)|" }
        .reduce { str1, str2 -> str1 + str2 }
        .removeSuffix("|")
}