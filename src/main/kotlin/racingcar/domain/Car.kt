package racingcar.domain

private const val INITIAL_POSITION = 0
private const val MOVEMENT_CONDITION_STANDARD = 4
private const val NEXT_POSITION = 1

class Car(
    val position: Int = INITIAL_POSITION,
) {
    init {
        check(position >= INITIAL_POSITION) { "자동차의 처음 position은 0이상의 숫자만 가능합니다. position = $position" }
    }

    fun moveOrNot(condition: Int): Car {
        if (condition >= MOVEMENT_CONDITION_STANDARD) {
            return Car(position = position + NEXT_POSITION)
        }

        return this
    }
}