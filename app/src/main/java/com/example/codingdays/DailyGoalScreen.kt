package com.example.codingdays

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarToday
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.codingdays.data.DailyGoalRepository
import com.example.codingdays.model.DailyGoal
import com.example.codingdays.ui.theme.CodingDaysTheme
import com.example.codingdays.ui.theme.DailyGaolShapes
import com.example.codingdays.ui.theme.DailyGoalTypography

@Composable
fun DailyGoalCard(
    dailyGoal: DailyGoal,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_small))
    ) {
        DailyGoalInformation(
            dayOfMonthId = dailyGoal.dayOfMonthId,
            dayGoalSummaryId = dailyGoal.dayGoalSummaryId,
            dayGoalImageId = dailyGoal.dayGoalImageId,
            dayGoalDescriptionId = dailyGoal.dayGoalDescriptionId,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))
        )
    }
}


@Composable
fun DailyGoalInformation(
    @StringRes dayOfMonthId: Int,
    @StringRes dayGoalSummaryId: Int,
    @DrawableRes dayGoalImageId: Int,
    @StringRes dayGoalDescriptionId: Int,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.space_small))
        ) {
            Icon(
                Icons.Outlined.CalendarToday,
                contentDescription = stringResource(R.string.day_1),
                modifier = Modifier.size(dimensionResource(R.dimen.day_title_icon))
            )
            Text(
                text = stringResource(dayOfMonthId),
                style = DailyGoalTypography.titleSmall
            )
        }
        Text(
            text = stringResource(dayGoalSummaryId),
            style = DailyGoalTypography.displayMedium
        )
        Image(
            painter = painterResource(dayGoalImageId),
            contentDescription = stringResource(dayGoalSummaryId),
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.padding_small),
                    bottom = dimensionResource(R.dimen.padding_small)
                )
                .clip(DailyGaolShapes.small)

        )
        Text(
            text = stringResource(dayGoalDescriptionId),
            style = DailyGoalTypography.bodyMedium
        )
    }
}

@Composable
fun DailyGoalList(
    dailyGoals: List<DailyGoal>
) {
    LazyColumn {
        items(dailyGoals) {dailyGoal ->
            DailyGoalCard(dailyGoal)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DailyGoalCardPreview() {
    CodingDaysTheme {
        DailyGoalList(DailyGoalRepository.dailyGoals)
    }
}