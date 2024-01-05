package com.example.diceroller

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DiceRollerApp()
                }
            }
        }
    }
}


@Composable
fun DiceRollerApp() {
    DiceWithButtonAndImage()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiceWithButtonAndImage(modifier: Modifier = Modifier
    .fillMaxSize()
    .wrapContentSize(Alignment.Center)
) {
    var selectedDice by remember { mutableStateOf(Dice.D4) }
    var numberOfDice by remember { mutableStateOf(2) }
    var result1 by remember { mutableStateOf(1) }
    var result2 by remember { mutableStateOf(1) }

    val imageResource1 = getImageResource(result1)
    val imageResource2 = getImageResource(result2)

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (numberOfDice == 2) {
            Image(painter = painterResource(id = imageResource1), contentDescription = result1.toString())
            Spacer(modifier = Modifier.height(10.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))

        Image(painter = painterResource(id = imageResource2), contentDescription = result2.toString())

        Spacer(modifier = Modifier.height(50.dp))

        Button(onClick = {
            result1 = (1..selectedDice.sides).random()
            result2 = (1..selectedDice.sides).random()
        }) {
            Text(stringResource(id = R.string.roll))
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Dropdown menu for selecting dice type
        val diceOptions = Dice.values().map { it.type }
        var expandedDice by remember { mutableStateOf(false) }
        var selectedOptionTextDice by remember { mutableStateOf(diceOptions[0]) }

        ExposedDropdownMenuBox(
            expanded = expandedDice,
            onExpandedChange = {
                expandedDice = !expandedDice
            }
        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .size(200.dp, 50.dp),
                readOnly = true,
                value = selectedOptionTextDice,
                onValueChange = { },
                label = { Text("Dice") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expandedDice
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expandedDice,
                onDismissRequest = {
                    expandedDice = false
                }
            ) {
                diceOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(text = selectionOption) },
                        onClick = {
                            selectedOptionTextDice = selectionOption
                            expandedDice = false
                            selectedDice = Dice.values().first { it.type == selectionOption }
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Dropdown menu for selecting number of dice
        val numberOfDiceOptions = listOf("1", "2")
        var expandedNumberOfDice by remember { mutableStateOf(false) }
        var selectedOptionTextNumberOfDice by remember { mutableStateOf(numberOfDiceOptions[0]) }

        ExposedDropdownMenuBox(
            expanded = expandedNumberOfDice,
            onExpandedChange = {
                expandedNumberOfDice = !expandedNumberOfDice
            }
        ) {
            TextField(
                modifier = Modifier
                    .menuAnchor()
                    .size(200.dp, 50.dp),
                readOnly = true,
                value = selectedOptionTextNumberOfDice,
                onValueChange = { },
                label = { Text("Number of Dice") },
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expandedNumberOfDice
                    )
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors()
            )
            ExposedDropdownMenu(
                expanded = expandedNumberOfDice,
                onDismissRequest = {
                    expandedNumberOfDice = false
                }
            ) {
                numberOfDiceOptions.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(text = selectionOption) },
                        onClick = {
                            selectedOptionTextNumberOfDice = selectionOption
                            expandedNumberOfDice = false
                            numberOfDice = selectionOption.toInt()
                        }
                    )
                }
            }
        }
    }
}

private fun getImageResource(result: Int): Int {
    return when (result) {
        1 -> R.drawable.dice1
        2 -> R.drawable.dice2
        3 -> R.drawable.dice3
        4 -> R.drawable.dice4
        5 -> R.drawable.dice5
        6 -> R.drawable.dice6
        7 -> R.drawable.dice7
        8 -> R.drawable.dice8
        9 -> R.drawable.dice9
        10 -> R.drawable.dice10
        11 -> R.drawable.dice11
        12 -> R.drawable.dice12
        13 -> R.drawable.dice13
        14 -> R.drawable.dice14
        15 -> R.drawable.dice15
        16 -> R.drawable.dice16
        17 -> R.drawable.dice17
        18 -> R.drawable.dice18
        19 -> R.drawable.dice19
        else -> R.drawable.dice20
    }
}


@Preview
@Composable
fun DiceAppPreview() {
    DiceRollerApp()
}

enum class Dice(val sides: Int, val type: String) {
    D4(4, "4-sided"),
    D6(6, "6-sided"),
    D8(8, "8-sided"),
    D10(10, "10-sided"),
    D12(12, "12-sided"),
    D20(20, "20-sided")
}