package com.example.airlineapp.presentation.FlightList.Components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.airlineapp.domain.model.FlightItemModel

@Composable
fun FlightListItem(
    flightdata: FlightItemModel,
) {

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = flightdata.name,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = flightdata.country,
            overflow = TextOverflow.Ellipsis
        )
    }

}