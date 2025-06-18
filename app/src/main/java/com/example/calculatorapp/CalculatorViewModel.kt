package com.example.calculatorapp

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder

class CalculatorViewModel: ViewModel() {

    var state by mutableStateOf(CalculatorState())
        private set

    fun onAction(action: CalculatorAction) {
        when(action) {
            is CalculatorAction.Number -> appendToExpression(action.number.toString())
            is CalculatorAction.Operation -> appendToExpression(action.symbol.symbol)
            is CalculatorAction.Decimal -> appendToExpression(".")
            is CalculatorAction.Delete -> deleteLast()
            is CalculatorAction.Clear -> clear()
            is CalculatorAction.Calculate -> calculate()
        }
    }

    private fun appendToExpression(value: String) {
        val expr = state.expression

        if (value == ".") {
            val lastNumber = expr.takeLastWhile { it.isDigit() || it == '.' }
            if (lastNumber.contains(".") || lastNumber.isEmpty())
                return
        }
        if (value in "+-*/" && (expr.isEmpty() || expr.last() in "+-*/"))
            return
        if (expr.length >= MAX_LENGTH)
            return

        state = state.copy(expression = expr + value)
    }

    private fun deleteLast() {
        if (state.expression.isNotEmpty()) {
            state = state.copy(expression = state.expression.dropLast(1))
        }
    }

    private fun clear() {
        state = CalculatorState()
    }

    @SuppressLint("DefaultLocale")
    private fun calculate() {
        try {
            val rawResult = ExpressionBuilder(state.expression).build().evaluate()
            val formattedResult = String.format("%.4f", rawResult).trimEnd('0').trimEnd('.')
            state = state.copy(result = formattedResult.take(15))
        } catch (e: Exception) {
            state = state.copy(result = "Error")
        }
    }

    companion object {
        private const val MAX_LENGTH = 50
    }
}