package com.example.codingdays

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.codingdays.data.DailyGoalRepository.dailyGoals
import com.example.codingdays.ui.DailyGoalList
import com.example.codingdays.ui.theme.CodingDaysTheme
import com.example.codingdays.ui.theme.DailyGoalTypography

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodingDaysTheme {
                    Scaffold(
                        topBar = {
                            DailyGoalTopAppBar()
                        }
                    ) {
                        Box(
                            modifier = Modifier.padding(it)
                        ) {
                            DailyGoalApp()
                        }
                    }
                }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    modifier: Modifier = Modifier,
    textState: String,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = textState,
        onValueChange = onValueChange,
        singleLine = true,
        modifier = modifier,
        leadingIcon = {
            Icon(
                Icons.Outlined.Search,
                contentDescription = "Search Icon"
            )
        }
    )
}

@Composable
fun DailyGoalApp() {
    var textState by rememberSaveable {
        mutableStateOf("")
    }
    Column {
        SearchView(
            textState = textState,
            onValueChange = { newTextState ->
                textState = newTextState
            },
            modifier = Modifier
                .fillMaxWidth()
        )
        DailyGoalList(
            dailyGoals = dailyGoals,
            modifier = Modifier
                .padding(
                    top = dimensionResource(R.dimen.padding_small),
                    bottom = dimensionResource(R.dimen.padding_small),
                    start = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium)

                )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DailyGoalTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small))
            ) {
                Icon(
                    Icons.Filled.Code,
                    contentDescription = null,
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.top_app_icon))
                )
                Text(
                    text = "30 Days Of Coding",
                    style = DailyGoalTypography.displayLarge
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DailyGoalAppPreview() {

}