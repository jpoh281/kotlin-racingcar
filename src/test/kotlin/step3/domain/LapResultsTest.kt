package step3.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import step3.domain.Car
import step3.domain.Cars
import step3.domain.LapResult
import step3.domain.LapResults

internal class LapResultsTest {
    @ParameterizedTest
    @MethodSource("findWinnerTestSourceProvider")
    fun `getWinners() 는 가장 최종 position 값이 큰 record 들을 알려준다`(lapResults: LapResults, winnersSize: Int, winnersPosition: Int) {
        val result = lapResults.getWinners()

        assertThat(result.winners.size).isEqualTo(winnersSize)
        assertThat(result.winners[0].position).isEqualTo(winnersPosition)
    }

    companion object {
        @JvmStatic
        fun findWinnerTestSourceProvider() = listOf(
            Arguments {
                arrayOf(
                    LapResults(
                        listOf(
                            LapResult(cars = Cars(listOf(Car(name = "a"), Car(name = "b"), Car(name = "c"))))
                        )
                    ),
                    3,
                    0
                )
            },
            Arguments {
                arrayOf(
                    LapResults(
                        listOf(
                            LapResult(cars = Cars(listOf(Car(name = "a", position = 1), Car(name = "b"), Car(name = "c", position = 1))))
                        )
                    ),
                    2,
                    1
                )
            },
            Arguments {
                arrayOf(
                    LapResults(
                        listOf(
                            LapResult(cars = Cars(listOf(Car(name = "a", position = 1), Car(name = "b", position = 1), Car(name = "c")))),
                            LapResult(cars = Cars(listOf(Car(name = "a", position = 1), Car(name = "b", position = 2), Car(name = "c", position = 1))))
                        )
                    ),
                    1,
                    2
                )
            }
        )
    }
}