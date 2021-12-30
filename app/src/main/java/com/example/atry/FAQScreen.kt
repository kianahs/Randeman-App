package com.example.atry

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.atry.data.remote.dto.FAQItem


@ExperimentalMaterialApi
@OptIn(ExperimentalMaterialApi::class)
@ExperimentalAnimationApi
@Composable

fun FAQScreen(navController: NavController){


    val items = listOf<FAQItem>(
        FAQItem("راندمان چیست؟", "اپلیکیشن بستری را برای کارفرما فراهم می کند تا بتواند منابع خود را مدیریت کند.برای مثال با افزودن هر منبع و تسک های مربوط به آن منبع و تعیین اهمیت آن تسک و تسک های پیش نیاز(در صورت وجود) از جدول زمانی کار آن منبع که توسط اپلیکیشن ساخته می شود،مطلع می شوند و زمان خالی آن منبع را پیدا کرده و با تسک دیگری پر می کنند در نتیجه راندمان منبع را افزایش می دهند."),
        FAQItem("چرا از راندمان استفاده کنم؟", "امروزه در صنعت کارفرمایان متعددی به دنبال مدیریت منابع خود هستند تا بتوانند راندمان منابع خود را افزایش دهند. در این حیطه نرم افزار هایی مانند Microsoft project وجود دارند اما به علت پیچیدگی کار با این نرم افزارها و نبود اپلیکیشن های مناسب با کاربری آسان و کمبود افراد متخصص در این نرم افزارها، در صنعت ایران به صورت عمده استفاده نمی شوند. اغلب کارفرمایان تنها به دنبال مدیریت منابع خود و تسک های همان منبع می باشند و نیازی به سایر امکانات نرم افزار های پیچیده ندارند. "),
        FAQItem("تفاوت راندمان با برنامه های مشابه چیست؟", "مدیریت آسان منابع یک کارفرما بدون نیاز به فرد متخصص یا یادگیری نرم افزار های پیچیده و از میان برداشتن برنامه ریزی های نوشتاری.در این حیطه نرم افزار هایی مانند Microsoft project وجود دارند اما به علت پیچیدگی کار با این نرم افزارها و نبود اپلیکیشن های مناسب با کاربری آسان و کمبود افراد متخصص در این نرم افزارها، در صنعت ایران به صورت عمده استفاده نمی شوند. اغلب کارفرمایان تنها به دنبال مدیریت منابع خود و تسک های همان منبع می باشند و نیازی به سایر امکانات نرم افزار های پیچیده ندارند."),
        FAQItem("هدف و کارابی نهایی راندمان چیست؟", "ایجاد یک بستر مناسب برای کارفرمایان در صنعت به گونه ای که بدون آموزش خاص و استخدام نیروی متخصص به سادگی منابع خود را مدیریت کنند و راندمان منابع را از طریق یافتن و پر کردن زمان های خالی آن منبع افزایش دهند."),
    )

    Column(modifier = Modifier.fillMaxSize()){
        Text(
            buildAnnotatedString {
//                    append("welcome to ")
            withStyle(
                style = SpanStyle(
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF4552B8),
                    fontSize = 40.sp
                )
            ) {
                append("FAQ")
            }
        }, textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(4.dp))
        Spacer(modifier = Modifier.padding(10.dp))
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 40.dp), horizontalAlignment = Alignment.CenterHorizontally){

            itemsIndexed(
                items
            ){index, item ->
                MyExpandedList(title = item.getTitle(), content = item.getContent())

            }
        }

    }



}