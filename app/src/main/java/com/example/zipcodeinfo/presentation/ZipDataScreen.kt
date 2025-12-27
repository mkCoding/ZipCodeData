package com.example.zipcodeinfo.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly
import com.example.zipcodeinfo.data.model2.ModelPlace
import com.example.zipcodeinfo.data.model2.ZipDataModel

@Composable
fun ZipDataScreen(
    state: ZipDataState,
    onZipCheck: (Int) -> Unit
) {
    var zipCode by remember { mutableStateOf("") }
    val enableButtonCondition =
        zipCode.isNotEmpty() && zipCode.isDigitsOnly() && zipCode.length == 5


    Column(
        modifier = Modifier
            .background(Color(0xFF32292F))
            .statusBarsPadding()
            .navigationBarsPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        Text(
            color = Color(0xFFF0F7F4),
            modifier = Modifier
                .padding(16.dp),
            text = "Zip Code Report",
            fontSize = 40.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Bold
        )


        OutlinedTextField(
            textStyle = TextStyle(
                color = Color.White,
                fontStyle = FontStyle.Italic,
                textAlign = TextAlign.Center,
                fontSize = 30.sp),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            value = zipCode,
            onValueChange = { it ->
                // this field will only take 5 chars and restricted to digits only
                if (it.length <= 5 && it.all { it.isDigit() }) {
                    zipCode = it
                }
            },
            placeholder = {
                Text(
                    color = Color.Gray,
                    fontSize = 30.sp,
                    text = "Enter in a Zip",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
            }
        )

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xffF2E5DB), // Normal color
                disabledContainerColor = Color.Gray // Disabled color
            ),
            enabled = enableButtonCondition,
            onClick = { onZipCheck(zipCode.toInt()) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .shadow(
                    elevation = 20.dp,
                    shape = RoundedCornerShape(8.dp),
                    ambientColor = Color.Black.copy(alpha = 0.5f)
                ),
            shape = RoundedCornerShape(8.dp),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 100.dp,
            ),
        ) {
            Text(text = "Check Zip", fontSize = 20.sp,
                color = if(enableButtonCondition) Color.Black else Color.LightGray)
        }

        when (state) {
            is ZipDataState.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is ZipDataState.Success -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    // verticalArrangement = Arrangement.Center
                ) {
                    val data = state.data
                    Card(
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.elevatedCardElevation(8.dp),
                        shape = RoundedCornerShape(20.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {

                            // Zip
                            Text(
                                text = "Zip",
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                            )
                            Text("${data.postCode}", fontSize = 25.sp)

                            Spacer(Modifier.padding(bottom = 16.dp))

                            // Country
                            Text(
                                text = "Country",
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                            )
                            Text("${data.country}", fontSize = 25.sp)

                            // City
                            Spacer(Modifier.padding(bottom = 16.dp))
                            Text(
                                text = "City",
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                            )
                            Text(
                                "${data.places?.firstOrNull()?.placeName}",
                                fontSize = 25.sp
                            ) // city


                            // State
                            Spacer(Modifier.padding(bottom = 16.dp))
                            Text(
                                text = "State",
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                            )
                            Text("${data.places?.firstOrNull()?.state}", fontSize = 25.sp) // state


                            // State Abbreviation.
                            Spacer(Modifier.padding(bottom = 16.dp))
                            Text(
                                text = "State Abbreviation",
                                color = Color.Gray,
                                fontWeight = FontWeight.Bold,
                                fontStyle = FontStyle.Italic,
                            )
                            Text(
                                "${data.places?.firstOrNull()?.stateAbbreviation}",
                                fontSize = 25.sp
                            )
                        }
                    }
                }
            }

            is ZipDataState.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(state.message, color = Color.White, textAlign = TextAlign.Center)
                }
            }
        }
    }

}


@Composable
@Preview(showBackground = true)
fun ZipDataScreenPreview() {
    ZipDataScreen(
        ZipDataState.Success(
            ZipDataModel(
                country = "United States",
                countryAbbreviation = "US",
                postCode = "33162",
                places = listOf(
                    ModelPlace(
                        latitude = "25.9286",
                        longitude = "-80.183",
                        placeName = "Miami",
                        state = "Florida",
                        stateAbbreviation = "FL"
                    )
                )
            )
        ), onZipCheck = {}
    )
}