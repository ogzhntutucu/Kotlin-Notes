const val DEFINITELY_NOT_A_MAGIC_NUMBER = 6

const val O = 'O'
const val X = 'X'

fun main() {
    @Suppress("VariableNaming", "MagicNumber")
    val `good name` = 14
    println(`good name`)

    @Suppress("MagicNumber")
    val fraction: Float = 1.51f
    println(fraction)

    //////

    print("#\n##\n###\n####\n#####\n######\n")

    for (i in 1..DEFINITELY_NOT_A_MAGIC_NUMBER) {
        println("#".repeat(i))
    }

    //////

    println(
        """
        O X X
        O X O
        X O X
        """.trimIndent()
    )

    oxoGame(O, X, X)
    oxoGame(O, X, O)
    oxoGame(X, O, X)
}

fun oxoGame(a: Char, b: Char, c: Char) {
    println("$a $b $c")
}