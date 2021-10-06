@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val remainder = age % 10

    val year: String = when {
        age % 100 in 10..20 -> "лет"
        remainder == 1 -> "год"
        remainder in 1..4 -> "года"
        else -> "лет"
    }

    return "$age $year"
}

/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {

    val s1 = t1 * v1
    val s2 = t2 * v2
    val s3 = t3 * v3
    val halfS = (s1 + s2 + s3) / 2
    val delta: Double

    return when {
        s1 > halfS -> {
            delta = (s1) - halfS
            (s1 - delta) / v1
        }
        s1 + s2 > halfS -> {
            delta = (s1 + s2) - halfS
            t1 + (s2 - delta) / v2
        }
        else -> {
            delta = (s1 + s2 + s3) - halfS
            t1 + t2 + (s3 - delta) / v3
        }
    }

}

/**
 * Простая (2 балла)
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    // 1) Значит, теоретически я должен определить поля подконтрольные каждой фигуре (тут двум ладьям)
    // 2) Как это сделать?
    // 3) Хммм, а можно использовать массивы, списки?
    // 4) Какое самое универсальное и в тоже время самое простое решение?
    // Пусть у меня будет метод (inControlCells()), который на вход принимает 3 аргумента: тип фигуры, x, y.
    // 1. Тогда вернуть этот метод должен массив клеток которые находятся под контролем фигуры,
    // после этого можно проверить находится ли третья фигура в этой зоне (хотя на пути может встретится другая фигура, но тут это не учитывается)
    // Но так как мы не изучали еще списки (да и не факт, что это эффеткивано), то будет еще 2 ургумента: x, y сторонней фигуры, и метод будет возвращать true
    // если клетка будет найдена в процессе работы inControlCells

    val inControlRook1 = inControlCells(TypeFigure.ROOK, rookX1, rookY1, kingX, kingY)
    val inControlRook2 = inControlCells(TypeFigure.ROOK, rookX2, rookY2, kingX, kingY)

    return when {
        inControlRook1 && inControlRook2 -> 3
        inControlRook2 -> 2
        inControlRook1 -> 1
        else -> 0
    }
}

enum class TypeFigure {
    // Я понимаю, что это малость я ухожу вперед и можно было ограничиться только String, но это решение мне показалось более удобным
    BISHOP, ROOK, QUEEN
}

fun inControlCells(
    currentFigureType: TypeFigure, currentFigureX: Int, currentFigureY: Int,
    otherFigureX: Int, otherFigureY: Int
): Boolean {

    // Нужно сделать эффективной по памяти и времени O(n)...
    return when (currentFigureType) {
        TypeFigure.BISHOP -> {
            // Если диагональ слева на право, то сумма x и y увел. на 2, иначе == тому же знач.
            // Чтобы опр. диагональ достаточно найти 1 из крайних клеток этой диагонали,
            // а затем определить, такая же крайняя клетка ли у диагонали otherFigure

            inBishopControlCells(currentFigureX, currentFigureY, otherFigureX, otherFigureY)
        }
        TypeFigure.ROOK -> {
            inRookControlCells(currentFigureX, currentFigureY, otherFigureX, otherFigureY)
        }
        TypeFigure.QUEEN -> {
            inRookControlCells(currentFigureX, currentFigureY, otherFigureX, otherFigureY)
                    || inBishopControlCells(currentFigureX, currentFigureY, otherFigureX, otherFigureY)
        }
        // Может по приколу сделать для ферзя, короля, коня и пешки?
        // Ферзь, очевидно, объединяет логику слона и ладьи, потому по-хорошему вынести бы их логику в отдельные методы
        // у короля модуль разности для x'ов и y'ов не должен превышать 1
        // пешка - слон на миниалках, y += 1(или -= 1 для черного цвета, т.е необходим еще аргумент цвета фигуры), а x +=1 и x-=1. А так же проверка на выход за пределы доски
        // конь - в процессе, но напоминает пешку
    }

}

private fun inRookControlCells(
    currentFigureX: Int,
    currentFigureY: Int,
    otherFigureX: Int,
    otherFigureY: Int
) = currentFigureX == otherFigureX || currentFigureY == otherFigureY

private fun inBishopControlCells(
    currentFigureX: Int,
    currentFigureY: Int,
    otherFigureX: Int,
    otherFigureY: Int
): Boolean {

    return (currentFigureX + currentFigureY) == (otherFigureX + otherFigureY) // Косая диагональ
            || (currentFigureX - currentFigureY) == (otherFigureX - otherFigureY) // Простая диагональ
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {

    val inControlRook = inControlCells(TypeFigure.ROOK, rookX, rookY, kingX, kingY)
    val inControlBishop = inControlCells(TypeFigure.BISHOP, bishopX, bishopY, kingX, kingY)

    return when {
        inControlRook && inControlBishop -> 3
        inControlBishop -> 2
        inControlRook -> 1
        else -> 0
    }
}

/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    val side1 = min(a, b) // Первая наименьшая сторона - "катет"
    val side2 = min(c, max(a, b)) // Вторая наименьшая сторона - "катет"
    val side3 = max(c, max(a, b)) // Наибольшая сторона - "гипотенуза"

    val sum = side1.pow(2) + side2.pow(2)
    return when {
        side1 + side2 < side3 -> -1
        sum > side3.pow(2) -> 0
        sum == side3.pow(2) -> 1
        else -> 2
    }
}

/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    val endFirst = min(b, d)
    val startOther = max(a, c)
    // Конец первого меньше начала второго

    return if (endFirst >= startOther) {
        endFirst - startOther
    } else {
        -1
    }
}
