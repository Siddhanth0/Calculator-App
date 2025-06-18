package com.example.calculatorapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculatorapp.ui.theme.CalculatorAppTheme
import com.example.calculatorapp.ui.theme.MediumGray
import com.example.calculatorapp.ui.theme.Orange

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorAppTheme {
                val viewModel = viewModel<CalculatorViewModel>()
                val state = viewModel.state
                val context = LocalContext.current
                LaunchedEffect(key1 = true) {
                    viewModel.event.collect { event ->
                        when (event) {
                            is UIEvent.ShowToast -> {
                                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
                Calculator(state, viewModel)
            }
        }
    }
}

@Composable
fun Calculator(
    state: CalculatorState,
    viewModel: CalculatorViewModel,
    buttonSpacing: Dp = 8.dp,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing),
        ) {
            Text(
                text = state.expression,
                fontSize = 50.sp,
                lineHeight = 52.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                text = state.result,
                fontSize = 50.sp,
                lineHeight = 52.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth().padding(top = 12.dp, bottom = 32.dp),
                color = MaterialTheme.colorScheme.primary
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "AC",
                    color = MediumGray,
                    modifier = Modifier
                        .aspectRatio(2f)
                        .weight(2f)
                ) {
                    viewModel.onAction(CalculatorAction.Clear)
                }
                CalculatorButton(
                    symbol = "Del",
                    color = MediumGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.onAction(CalculatorAction.Delete)
                }
                CalculatorButton(
                    symbol = "/",
                    color = Orange,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Divide))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "7",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.onAction(CalculatorAction.Number(7))
                }
                CalculatorButton(
                    symbol = "8",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.onAction(CalculatorAction.Number(8))
                }
                CalculatorButton(
                    symbol = "9",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.onAction(CalculatorAction.Number(9))
                }
                CalculatorButton(
                    symbol = "x",
                    color = Orange,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Multiply))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "4",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.onAction(CalculatorAction.Number(4))
                }
                CalculatorButton(
                    symbol = "5",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.onAction(CalculatorAction.Number(5))
                }
                CalculatorButton(
                    symbol = "6",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.onAction(CalculatorAction.Number(6))
                }
                CalculatorButton(
                    symbol = "-",
                    color = Orange,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Subtract))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "1",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.onAction(CalculatorAction.Number(1))
                }
                CalculatorButton(
                    symbol = "2",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.onAction(CalculatorAction.Number(2))
                }
                CalculatorButton(
                    symbol = "3",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.onAction(CalculatorAction.Number(3))
                }
                CalculatorButton(
                    symbol = "+",
                    color = Orange,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.onAction(CalculatorAction.Operation(CalculatorOperation.Add))
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "0",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(2f)
                        .weight(2f)
                ) {
                    viewModel.onAction(CalculatorAction.Number(0))
                }
                CalculatorButton(
                    symbol = ".",
                    color = Color.DarkGray,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.onAction(CalculatorAction.Decimal)
                }
                CalculatorButton(
                    symbol = "=",
                    color = Orange,
                    modifier = Modifier
                        .aspectRatio(1f)
                        .weight(1f)
                ) {
                    viewModel.onAction(CalculatorAction.Calculate)
                }
            }
        }
    }
}