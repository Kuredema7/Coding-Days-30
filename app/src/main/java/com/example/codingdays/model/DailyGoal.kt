package com.example.codingdays.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DailyGoal(
    @StringRes val dayOfMonthId: Int,
    @StringRes val dayGoalSummaryId: Int,
    @DrawableRes val dayGoalImageId: Int,
    @StringRes val dayGoalDescriptionId: Int
)
