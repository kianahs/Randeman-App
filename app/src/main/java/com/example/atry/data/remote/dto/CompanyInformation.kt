package com.example.atry.data.remote.dto

data class CompanyInformation(
    val ContributorCount: Int,
    val announcementCount: Int,
    val contributors: List<String>,
    val resourceCount: Int,
    val taskCount: Int,
    val announcements: List<String>
)