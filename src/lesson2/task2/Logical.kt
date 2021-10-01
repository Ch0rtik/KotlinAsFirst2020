@file:Suppress("UNUSED_PARAMETER")

package lesson2.task2

import lesson1.task1.getNumberList
import lesson1.task1.sqr
import lesson2.task1.TypeFigure
import lesson2.task1.inControlCells
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
    sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая (2 балла)
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    val ints = getNumberList(number)
    val sumStart: Int = ints[0] + ints[1]
    val sumEnd: Int = ints[2] + ints[3]

    return sumStart == sumEnd
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    return inControlCells(TypeFigure.QUEEN, x1, y1, x2, y2)
}


/**
 * Простая (2 балла)
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {
    // Сперва определить: год высокстный или нет

    val isLeapYear: Boolean = if (year % 400 == 0) {
        true
    } else {
        year % 100 != 0 && year % 4 == 0
    }

    return when (month) {
        2 -> if (isLeapYear) 29 else 28
        4, 6, 9, 11 -> 30
        else -> 31
    }
}

/**
 * Простая (2 балла)
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(
    x1: Double, y1: Double, r1: Double,
    x2: Double, y2: Double, r2: Double
): Boolean {

    // формула окр в математике: (x - a).pow(2) + (y - b).pow(2) = R.pow(2)
    // где a и b - центр окр, а x и y точка, лежащ. на окр тогда

    val deltaR = sqrt((x1 - x2).pow(2) + (y1 - y2).pow(2)) //расстояние (радиус) от центра окр №2 до центра окр. №1

    return deltaR + r1 <= r2
}

/**
 * Средняя (3 балла)
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    val sides = mutableListOf(a, b, c)
    var b1 = false
    var b2 = false

    var usedSide = -1
    for (i in sides) {
        if (i <= r) {
            usedSide = 1
            b1 = true
            break
        }
    }
    if (usedSide != 1) {

        sides.remove(usedSide)
        for (i in sides) {
            if (i <= s) {
                b2 = true
                break
            }
        }
    }
    return b1 && b2
}
