package fr.o80.twitckbot.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import kotlin.math.max


@Composable
@Suppress("FunctionName")
fun FlowLayout(
    modifier: Modifier = Modifier,
    children: @Composable () -> Unit
) {
    Layout(
        content = children,
        modifier = modifier
    ) { measurables, constraints ->

        val placer = FlowPlacer(constraints)

        val constraintsForOne = Constraints(
            minWidth = 0,
            maxWidth = constraints.maxWidth,
            minHeight = 0,
            maxHeight = Constraints.Infinity
        )

        measurables.forEach { measurable ->
            val placeable = measurable.measure(constraintsForOne)
            placer.record(placeable)
        }

        layout(
            placer.width,
            placer.height
        ) {
            placer.placements.forEach {
                it.placeable.place(it.x, it.y)
            }
        }
    }
}

class FlowPlacer(
    private val constraints: Constraints
) {

    val width: Int
        get() = constraints.maxWidth

    private var _height = 0
    val height: Int
        get() = _height.coerceAtLeast(constraints.minHeight)

    private var currentRowHeight = 0
    private var currentRowX = 0
    private var currentRowY = 0

    private val _placements = mutableListOf<FlowPlacement>()
    val placements: List<FlowPlacement>
        get() = _placements

    fun record(placeable: Placeable) {
        val last = _placements.lastOrNull()

        if (last == null) {
            currentRowHeight = placeable.height
            currentRowX = 0
            addToPlacements(currentRowX, currentRowY, placeable)
            return
        }

        if (currentRowX + last.placeable.width + placeable.width <= width) {
            currentRowHeight = max(currentRowHeight, placeable.height)
            currentRowX += placeable.width
            addToPlacements(currentRowX, currentRowY, placeable)
            return
        }

        addToNextRow(placeable)
    }

    private fun addToNextRow(placeable: Placeable) {
        _height += currentRowHeight
        currentRowX = 0
        currentRowY = _height
        currentRowHeight = placeable.height

        addToPlacements(currentRowX, currentRowY, placeable)
    }

    private fun addToPlacements(x: Int, y: Int, placeable: Placeable) {
        _placements.add(FlowPlacement(placeable, x, y))
    }

}

class FlowPlacement(
    val placeable: Placeable,
    val x: Int,
    val y: Int
)
