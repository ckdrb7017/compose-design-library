package com.jakchang.deisgnlibrary.ui.screen.fadingedgescroll

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jakchang.deisgnlibrary.ui.theme.*
import com.jakchang.deisgnlibrary.utils.horizontalFadingEdge
import com.jakchang.designmodule.ui.chiptextfield.TagData

@Composable
fun FadingEdgeScrollScreen(){
    val listState = rememberLazyListState()
    val tagList = arrayListOf<TagData>()

    for(i in 0 until 10){
        tagList.add(TagData(
            tagText = "Tag$i",
            tagTextColor = Blue100,
            tagBackgroundColor = Blue10
        ))
    }

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalFadingEdge(
                listState = listState,
                length = 28.dp,
                edgeColor = Color(0x77000000),
                firstIndexKey = "Index0",
                lastIndexKey = "Index${tagList.size - 1}"
            ),
        state = listState
    ){
        tagList.forEachIndexed {index, tagData ->
            item(key = "Index$index") {
                FadingEdgeTag(tagData = tagData)
            }
        }
    }
}

@Composable
fun FadingEdgeTag(
    tagData: TagData
){
    Text(
        modifier = Modifier
            .height(40.dp)
            .background(
                color = tagData.tagBackgroundColor,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(vertical = 2.dp, horizontal = 8.dp),
        text = tagData.tagText,
        fontWeight = FontWeight.Bold,
        color = tagData.tagTextColor
    )
}