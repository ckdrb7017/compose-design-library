package com.jakchang.deisgnlibrary.ui.screen.chiptextfield

import android.widget.EditText
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jakchang.deisgnlibrary.ui.theme.*
import com.jakchang.designmodule.ui.chiptextfield.ChipTextField
import com.jakchang.designmodule.ui.chiptextfield.TagData


@Composable
fun ChipTextFieldScreen() {
    val tagList =
        listOf<TagData>(
            TagData(
                tagText = "Red",
                tagTextColor = Red100,
                tagBackgroundColor = Red10
            ),
            TagData(
                tagText = "Blue",
                tagTextColor = Blue100,
                tagBackgroundColor = Blue10
            ),
            TagData(
                tagText = "Gray",
                tagTextColor = Gray100,
                tagBackgroundColor = Gray10
            )
        )

    Column {
        var editText: EditText = EditText(LocalContext.current)

        LazyColumn {
            for (i in 0 until 1) {
                item {
                    ChipTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(96.dp)
                            .padding(horizontal = 16.dp)
                            .border(
                                width = 1.5.dp,
                                color = Color(0xFFB7BBBF),
                                shape = RoundedCornerShape(10.dp)
                            ),
                        editText = editText,
                        tagList = tagList
                    )
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(
                                vertical = 16.dp
                            )
                            .background(color = Color(0xFFD6D9DF))
                    ) {
                        Row(
                            modifier = Modifier.padding(
                                vertical = 8.dp,
                                horizontal = 16.dp
                            )
                        ) {
                            Text(
                                text = "Color",
                                color = Gray70,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            ChipTag(
                                tagData = tagList[0],
                                editText = editText
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            ChipTag(
                                tagData = tagList[1],
                                editText = editText
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            ChipTag(
                                tagData = tagList[2],
                                editText = editText
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ChipTag(
    tagData: TagData,
    editText: EditText
) {
    Text(
        modifier = Modifier
            .height(24.dp)
            .background(
                color = tagData.tagBackgroundColor,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(vertical = 2.dp, horizontal = 8.dp)
            .clickable {
                editText.text.insert(editText.selectionStart, tagData.tagText + " ")
            },
        text = tagData.tagText,
        fontWeight = FontWeight.Bold,
        color = tagData.tagTextColor
    )
}