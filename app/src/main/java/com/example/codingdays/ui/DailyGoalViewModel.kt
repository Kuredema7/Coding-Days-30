package com.example.codingdays.ui

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingdays.data.DailyGoalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class DailyGoalViewModel : ViewModel() {
    private val _searchTextUiState = MutableStateFlow("")
    val searchTextUiState = _searchTextUiState.asStateFlow()
    private val _isSearchedUiState = MutableStateFlow(false)
    val isSearchedUiState = _isSearchedUiState.asStateFlow()
    private val _dailyGoalsStateList = MutableStateFlow(DailyGoalRepository.dailyGoals)
    val dailyGoalStateList = searchTextUiState
        .combine(_dailyGoalsStateList) { text, dailyGoaStateList ->
            if (text.isBlank()) {
                dailyGoaStateList
            } else {
                dailyGoaStateList.filter { dailyGoal ->
                    dailyGoal.dayGoalSummaryId.toString().contentEquals(text, ignoreCase = true) ||
                            dailyGoal.dayOfMonthId.toString().contentEquals(text, ignoreCase = true)
                }
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            initialValue = _dailyGoalsStateList
        )

    fun onSearchTextChange(text: String) {
        _searchTextUiState.value = text
    }
}