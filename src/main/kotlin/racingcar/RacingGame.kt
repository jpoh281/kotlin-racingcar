package racingcar

fun main() {
    RacingGame().run(victoryCondition = VictoryCondition.Longest)
}

class RacingGame(
    private val inputView: InputView = InputView(),
    private val resultView: ResultView = ResultView(),
) {

    fun run(
        setting: Setting = inputView.receiveSetting(),
        victoryCondition: VictoryCondition = VictoryCondition.Longest
    ): List<Car> {
        val cars = generateCars(setting.nameOfCars)

        repeat(setting.numberOfLab) {
            playTurn(cars)
        }

        val winner = victoryCondition.announceWinner(cars)
        resultView.displayWinner(winner)

        return cars
    }

    private fun playTurn(cars: List<Car>) {
        cars.forEach { car ->
            car.move()
        }
        resultView.displayResult(cars)
    }

    private fun generateCars(nameOfCars: List<String>): List<Car> {
        return List(nameOfCars.size) { Car(name = nameOfCars[it]) }
    }
}
