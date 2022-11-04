package calculator

class Calculator(
    private val cpu: CPU = CPU(),
    private val keypad: Keypad = Keypad(),
    private val monitor: Monitor = Monitor(),
    private var memory: Memory = Memory()
) {

    init {
        monitor.display("----on----")
    }

    fun pressButton(input: String?) {
        val expression: String = if (input == null) {
            keypad.pressed()
        } else {
            keypad.pressed(input)
        }
        val terms = covertExpressionToTerm(expression)
        memory.store(terms)
    }

    fun calculate(): Int {
        for (index: Int in 1 until memory.size() step 2) {
            val accumulator = memory.accumulator
            val operator = Operator.findByValue(memory.fetch(index))
            val operand = memory.fetch(index + 1).toInt()

            val result = parse(operator, accumulator, operand)
            memory.accumulate(result)
        }

        monitor.display(memory.accumulator.toString())
        return memory.accumulator
    }

    private fun covertExpressionToTerm(expression: String): List<String> {
        return expression.split(" ")
    }

    private fun parse(operator: Operator, operand1: Int, operand2: Int): Int {
        return when (operator) {
            Operator.ADD -> {
                cpu.add(operand1, operand2)
            }

            Operator.SUBTRACT -> {
                cpu.subtract(operand1, operand2)
            }

            Operator.MULTIPLY -> {
                cpu.multiply(operand1, operand2)
            }

            Operator.DIVIDE -> {
                cpu.divide(operand1, operand2)
            }
        }
    }
}
