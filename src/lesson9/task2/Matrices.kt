@file:Suppress("UNUSED_PARAMETER")

package lesson9.task2

import lesson9.task1.Cell
import lesson9.task1.Matrix
import lesson9.task1.MatrixImpl.Companion.toCell
import lesson9.task1.MatrixImpl.Companion.toIndex
import lesson9.task1.createMatrix
import kotlin.math.abs

// Все задачи в этом файле требуют наличия реализации интерфейса "Матрица" в Matrix.kt

/**
 * Пример
 *
 * Транспонировать заданную матрицу matrix.
 * При транспонировании строки матрицы становятся столбцами и наоборот:
 *
 * 1 2 3      1 4 6 3
 * 4 5 6  ==> 2 5 5 2
 * 6 5 4      3 6 4 1
 * 3 2 1
 */
fun <E> transpose(matrix: Matrix<E>): Matrix<E> {
    if (matrix.width < 1 || matrix.height < 1) return matrix
    val result = createMatrix(height = matrix.width, width = matrix.height, e = matrix[0, 0])
    for (i in 0 until matrix.width) {
        for (j in 0 until matrix.height) {
            result[i, j] = matrix[j, i]
        }
    }
    return result
}

/**
 * Пример
 *
 * Сложить две заданные матрицы друг с другом.
 * Складывать можно только матрицы совпадающего размера -- в противном случае бросить IllegalArgumentException.
 * При сложении попарно складываются соответствующие элементы матриц
 */
operator fun Matrix<Int>.plus(other: Matrix<Int>): Matrix<Int> {
    require(!(width != other.width || height != other.height))
    if (width < 1 || height < 1) return this
    val result = createMatrix(height, width, this[0, 0])
    for (i in 0 until height) {
        for (j in 0 until width) {
            result[i, j] = this[i, j] + other[i, j]
        }
    }
    return result
}

/**
 * Сложная (5 баллов)
 *
 * Заполнить матрицу заданной высоты height и ширины width
 * натуральными числами от 1 до m*n по спирали,
 * начинающейся в левом верхнем углу и закрученной по часовой стрелке.
 *
 * Пример для height = 3, width = 4:
 *  1  2  3  4
 * 10 11 12  5
 *  9  8  7  6
 */
fun generateSpiral(height: Int, width: Int): Matrix<Int> = TODO()

/**
 * Сложная (5 баллов)
 *
 * Заполнить матрицу заданной высоты height и ширины width следующим образом.
 * Элементам, находящимся на периферии (по периметру матрицы), присвоить значение 1;
 * периметру оставшейся подматрицы – значение 2 и так далее до заполнения всей матрицы.
 *
 * Пример для height = 5, width = 6:
 *  1  1  1  1  1  1
 *  1  2  2  2  2  1
 *  1  2  3  3  2  1
 *  1  2  2  2  2  1
 *  1  1  1  1  1  1
 */
fun generateRectangles(height: Int, width: Int): Matrix<Int> = TODO()

/**
 * Сложная (5 баллов)
 *
 * Заполнить матрицу заданной высоты height и ширины width диагональной змейкой:
 * в левый верхний угол 1, во вторую от угла диагональ 2 и 3 сверху вниз, в третью 4-6 сверху вниз и так далее.
 *
 * Пример для height = 5, width = 4:
 *  1  2  4  7
 *  3  5  8 11
 *  6  9 12 15
 * 10 13 16 18
 * 14 17 19 20
 */
fun generateSnake(height: Int, width: Int): Matrix<Int> = TODO()

/**
 * Средняя (3 балла)
 *
 * Содержимое квадратной матрицы matrix (с произвольным содержимым) повернуть на 90 градусов по часовой стрелке.
 * Если height != width, бросить IllegalArgumentException.
 *
 * Пример:    Станет:
 * 1 2 3      7 4 1
 * 4 5 6      8 5 2
 * 7 8 9      9 6 3
 */
fun <E> rotate(matrix: Matrix<E>): Matrix<E> = TODO()

/**
 * Сложная (5 баллов)
 *
 * Проверить, является ли квадратная целочисленная матрица matrix латинским квадратом.
 * Латинским квадратом называется матрица размером n x n,
 * каждая строка и каждый столбец которой содержат все числа от 1 до n.
 * Если height != width, вернуть false.
 *
 * Пример латинского квадрата 3х3:
 * 2 3 1
 * 1 2 3
 * 3 1 2
 */
fun isLatinSquare(matrix: Matrix<Int>): Boolean = TODO()

/**
 * Средняя (3 балла)
 *
 * В матрице matrix каждый элемент заменить суммой непосредственно примыкающих к нему
 * элементов по вертикали, горизонтали и диагоналям.
 *
 * Пример для матрицы 4 x 3: (11=2+4+5, 19=1+3+4+5+6, ...)
 * 1 2 3       11 19 13
 * 4 5 6  ===> 19 31 19
 * 6 5 4       19 31 19
 * 3 2 1       13 19 11
 *
 * Поскольку в матрице 1 х 1 примыкающие элементы отсутствуют,
 * для неё следует вернуть как результат нулевую матрицу:
 *
 * 42 ===> 0
 */
fun sumNeighbours(matrix: Matrix<Int>): Matrix<Int> = TODO()

/**
 * Средняя (4 балла)
 *
 * Целочисленная матрица matrix состоит из "дырок" (на их месте стоит 0) и "кирпичей" (на их месте стоит 1).
 * Найти в этой матрице все ряды и колонки, целиком состоящие из "дырок".
 * Результат вернуть в виде Holes(rows = список дырчатых рядов, columns = список дырчатых колонок).
 * Ряды и колонки нумеруются с нуля. Любой из спискоов rows / columns может оказаться пустым.
 *
 * Пример для матрицы 5 х 4:
 * 1 0 1 0
 * 0 0 1 0
 * 1 0 0 0 ==> результат: Holes(rows = listOf(4), columns = listOf(1, 3)): 4-й ряд, 1-я и 3-я колонки
 * 0 0 1 0
 * 0 0 0 0
 */
fun findHoles(matrix: Matrix<Int>): Holes = TODO()

/**
 * Класс для описания местонахождения "дырок" в матрице
 */
data class Holes(val rows: List<Int>, val columns: List<Int>)

/**
 * Средняя (3 балла)
 *
 * В целочисленной матрице matrix каждый элемент заменить суммой элементов подматрицы,
 * расположенной в левом верхнем углу матрицы matrix и ограниченной справа-снизу данным элементом.
 *
 * Пример для матрицы 3 х 3:
 *
 * 1  2  3      1  3  6
 * 4  5  6  =>  5 12 21
 * 7  8  9     12 27 45
 *
 * К примеру, центральный элемент 12 = 1 + 2 + 4 + 5, элемент в левом нижнем углу 12 = 1 + 4 + 7 и так далее.
 */
fun sumSubMatrix(matrix: Matrix<Int>): Matrix<Int> = TODO()

/**
 * Простая (2 балла)
 *
 * Инвертировать заданную матрицу.
 * При инвертировании знак каждого элемента матрицы следует заменить на обратный
 */
operator fun Matrix<Int>.unaryMinus(): Matrix<Int> = TODO(this.toString())

/**
 * Средняя (4 балла)
 *
 * Перемножить две заданные матрицы друг с другом.
 * Матрицы можно умножать, только если ширина первой матрицы совпадает с высотой второй матрицы.
 * В противном случае бросить IllegalArgumentException.
 * Подробно про порядок умножения см. статью Википедии "Умножение матриц".
 */
operator fun Matrix<Int>.times(other: Matrix<Int>): Matrix<Int> = TODO(this.toString())

/**
 * Сложная (7 баллов)
 *
 * Даны мозаичные изображения замочной скважины и ключа. Пройдет ли ключ в скважину?
 * То есть даны две матрицы key и lock, key.height <= lock.height, key.width <= lock.width, состоящие из нулей и единиц.
 *
 * Проверить, можно ли наложить матрицу key на матрицу lock (без поворота, разрешается только сдвиг) так,
 * чтобы каждой единице в матрице lock (штырь) соответствовал ноль в матрице key (прорезь),
 * а каждому нулю в матрице lock (дырка) соответствовала, наоборот, единица в матрице key (штырь).
 * Ключ при сдвиге не может выходить за пределы замка.
 *
 * Пример: ключ подойдёт, если его сдвинуть на 1 по ширине
 * lock    key
 * 1 0 1   1 0
 * 0 1 0   0 1
 * 1 1 1
 *
 * Вернуть тройку (Triple) -- (да/нет, требуемый сдвиг по высоте, требуемый сдвиг по ширине).
 * Если наложение невозможно, то первый элемент тройки "нет" и сдвиги могут быть любыми.
 */
fun canOpenLock(key: Matrix<Int>, lock: Matrix<Int>): Triple<Boolean, Int, Int> = TODO()

/**
 * Сложная (8 баллов)
 *
 * В матрице matrix размером 4х4 дана исходная позиция для игры в 15, например
 *  5  7  9  1
 *  2 12 14 15
 *  3  4  6  8
 * 10 11 13  0
 *
 * Здесь 0 обозначает пустую клетку, а 1-15 – фишки с соответствующими номерами.
 * Напомним, что "игра в 15" имеет квадратное поле 4х4, по которому двигается 15 фишек,
 * одна клетка всегда остаётся пустой. Цель игры -- упорядочить фишки на игровом поле.
 *
 * В списке moves задана последовательность ходов, например [8, 6, 13, 11, 10, 3].
 * Ход задаётся номером фишки, которая передвигается на пустое место (то есть, меняется местами с нулём).
 * Фишка должна примыкать к пустому месту по горизонтали или вертикали, иначе ход не будет возможным.
 * Все номера должны быть в пределах от 1 до 15.
 * Определить финальную позицию после выполнения всех ходов и вернуть её.
 * Если какой-либо ход является невозможным или список содержит неверные номера,
 * бросить IllegalStateException.
 *
 * В данном случае должно получиться
 * 5  7  9  1
 * 2 12 14 15
 * 0  4 13  6
 * 3 10 11  8
 */

fun fifteenGameMoves(matrix: Matrix<Int>, moves: List<Int>): Matrix<Int> {
    // 1) Найти index нуля в Cell.
    // 2) Цикл foreach для moves
    // 3) Вернуть измененныую матрицу
//    val countCells = matrix.width * matrix.height
    if (moves.isEmpty()) return matrix

    var voidPosition = getPosition(0, matrix.get())

    var currentIndex = voidPosition
    moves.forEach {
        if (it !in 1..15) throw IllegalArgumentException()
        currentIndex = getIndexToValue(currentIndex, it, matrix)

        print("$it ")
        if (currentIndex == -1) {
            println(moves)
            throw IllegalArgumentException("$it")
        }
        voidPosition = simpleMove(currentIndex, voidPosition, null, matrix)
    }
    return matrix
}

private fun getPosition(value: Int, data: MutableMap<Int, Int>): Int {
    var position = -1
    for (index in 0 until data.size) {
        if (data[index] == value) {
            position = index
            break
        }
    }
    if (position == -1) throw IllegalArgumentException("value $value")
    return position
}

fun getIndexToValue(currentIndex: Int, value: Int, matrix: Matrix<Int>): Int {
    val ways = getNeighbors(currentIndex, matrix.width, matrix.height)

    for (index in ways) {
        if (isIllegalIndex(index, matrix)) continue

        if (matrix[toCell(index, matrix.height)] == value)
            return index
    }

    return -1
}

private fun isIllegalIndex(index: Int, matrix: Matrix<Int>) =
    index >= matrix.width * matrix.height || index < 0

private fun getNeighbors(currentIndex: Int, width: Int, height: Int): Array<Int> {
    return arrayOf(
        if (currentIndex % width == 0) -1 else currentIndex - 1,//left
        currentIndex - width, //top
        if (currentIndex % width == 3) -1 else currentIndex + 1,//right
        currentIndex + width//bottom
    )
}

/**
 * Очень сложная (32 балла)
 *
 * В матрице matrix размером 4х4 дана исходная позиция для игры в 15, например
 *  5  7  9  2
 *  1 12 14 15
 *  3  4  6  8
 * 10 11 13  0
 *
 * Здесь 0 обозначает пустую клетку, а 1-15 – фишки с соответствующими номерами.
 * Напомним, что "игра в 15" имеет квадратное поле 4х4, по которому двигается 15 фишек,
 * одна клетка всегда остаётся пустой.
 *
 * Цель игры -- упорядочить фишки на игровом поле, приведя позицию к одному из следующих двух состояний:
 *
 *  1  2  3  4          1  2  3  4
 *  5  6  7  8   ИЛИ    5  6  7  8
 *  9 10 11 12          9 10 11 12
 * 13 14 15  0         13 15 14  0
 *
 * Можно математически доказать, что РОВНО ОДНО из этих двух состояний достижимо из любой исходной позиции.
 *
 * Вернуть решение -- список ходов, приводящих исходную позицию к одной из двух упорядоченных.
 * Каждый ход -- это перемена мест фишки с заданным номером с пустой клеткой (0),
 * при этом заданная фишка должна по горизонтали или по вертикали примыкать к пустой клетке (но НЕ по диагонали).
 * К примеру, ход 13 в исходной позиции меняет местами 13 и 0, а ход 11 в той же позиции невозможен.
 *
 * Одно из решений исходной позиции:
 *
 * [8, 6, 14, 12, 4, 11, 13, 14, 12, 4,
 * 7, 5, 1, 3, 11, 7, 3, 11, 7, 12, 6,
 * 15, 4, 9, 2, 4, 9, 3, 5, 2, 3, 9,
 * 15, 8, 14, 13, 12, 7, 11, 5, 7, 6,
 * 9, 15, 8, 14, 13, 9, 15, 7, 6, 12,
 * 9, 13, 14, 15, 12, 11, 10, 9, 13, 14,
 * 15, 12, 11, 10, 9, 13, 14, 15]
 *
 * Перед решением этой задачи НЕОБХОДИМО решить предыдущую
 */
fun <E> createMatrix(height: Int, width: Int, values: List<List<E>>): Matrix<E> {
    val matrix = createMatrix(height, width, values[0][0])
    for (row in 0 until height) {
        for (column in 0 until width) {
            matrix[row, column] = values[row][column]
        }
    }
    return matrix
}

fun Matrix<Int>.get(): MutableMap<Int, Int> {
    val data = mutableMapOf<Int, Int>()
    for (i in 0 until height * width) {
        data[i] = this[toCell(i, height)]
    }
    return data
}

fun main() {
    val matrix = lesson9.task2.createMatrix(
        4, 4, listOf(
            listOf(0, 1, 2, 3),
            listOf(4, 5, 6, 7),
            listOf(8, 9, 10, 11),
            listOf(12, 13, 14, 15)
        )
    )
    println(fifteenGameSolution(matrix))
}

fun fifteenGameSolution(matrix: Matrix<Int>): List<Int> {
    TODO()
    // По большей части я использовал след. приемы для решения головоломки:
    // 1) вращение окружающих цифр для прохода текущей цифры к своему месту;
    // 2) перемещения нуля к текущей цифре, в зону его радиуса (3х3);
    // 3) прием для прямоугольника 2х3, когда место текущей цифры есть краняя колонка в ряду.
    // 4) просто вращение 3 цифр по квадрату 2х2
    // Используя эти техники можно решить любую подобную головоломку, количество ячеек в которой есть квадрат числа.
    // План прохода:
    // Сперва заполняются верхние ряды не включая нижние два. С крайними правыми ячейчами в ряду используется прием 3
    // Когда останутся 2 нижних ряда, необходимо заполнять сперва крайнюю левую ячейку у предпоследнего ряда, затем у последнего.
    // Повторять пока ячеек сумарно не останется 3. Затем их достаточно просто повращать.

    if (matrix.width <= 0 || matrix.height <= 0) throw IllegalArgumentException()
    if (!dataIsCorrect(matrix)) throw IllegalArgumentException("Incorrect data")

    val data = mutableMapOf<Int, Int>()
    data.putAll(matrix.get())

    val listWithReadyPosition = Array(matrix.width * matrix.height) { false }
    val log = mutableListOf<Int>()
    val planOfPosition = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 10, 14, 11, 12, 15)
    var voidPosition = getPosition(0, data)
    val planOfValues = getPlanOfValues(isCorrectVariant(matrix, (voidPosition / matrix.width)))

    for (i in 0 until 15) {
        if (i == 12) break
        if (i != 0) voidPosition = getPosition(0, data)
        val currentPosition = getPosition(planOfValues[i], data)
        val targetPosition = planOfPosition[i] - 1

        println("New round $i planOfValues[i] ${planOfValues[i]} $data")
        if (currentPosition == targetPosition) {
            // Если уже занимает правильную позицию
            println("Luck $currentPosition")
            listWithReadyPosition[targetPosition] = true
            continue
        }

        if (!moveIfPossible(
                currentPosition,
                targetPosition,
                voidPosition,
                listWithReadyPosition,
                log,
                data,
                matrix
            )
        ) {
            throw IllegalArgumentException()
        }
        listWithReadyPosition[targetPosition] = true
    }
    println(matrix.get())
    println(data)
    return log
}

fun dataIsCorrect(matrix: Matrix<Int>): Boolean {
    val set = mutableSetOf<Int>()
    set.addAll(matrix.get().values)
    if (set.size != matrix.width * matrix.height) {
        return false
    }
    for (v in set) {
        if (isIllegalIndex(v, matrix)) return false
    }
    return true
}

fun isCorrectVariant(matrix: Matrix<Int>, zeroRow: Int): Boolean {
    var sum = zeroRow + 1 // учитывая нумерацию с нуля
    val end = matrix.height * matrix.width
    for (i in 0 until end) {
        for (j in i + 1 until end - 1) {
            if (matrix[toCell(i, matrix.height)] > matrix[toCell(i + 1, matrix.height)]) {
                sum++
            }
        }
    }
    return sum % 2 == 0
}

private fun getPlanOfValues(isCorrectVariant: Boolean): List<Int> {
    val list = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 13, 10, 14, 11, 12, 15)
    if (!isCorrectVariant) {
        list[11] = 15
        list[14] = 14
    }
    return list
}

fun rotateTrajectory(
// Вращает стрелки (передвигает) квадратную границу
    startPosition: Int, // Позиция нуля
    endPosition: Int,
    centerPosition: Int, // Если ноль центра нет, то вращаются 4 квадрата
    listWithReadyPosition: Array<Boolean>,
    matrix: Matrix<Int>
): List<Int> {
    val result = mutableListOf<Int>()
    val nearPositions = getNearPosition(centerPosition, matrix.width)
    val startInNearPosition = nearPositions.indexOf(startPosition)
    println("NearPositions $nearPositions start $startPosition end $endPosition center $centerPosition")
    var tmp: Int
    var isCorrect = true
    var current = startInNearPosition
    for (i in nearPositions.indices) {
        if (current <= 0) current = nearPositions.size
        current--
        tmp = nearPositions[current]
        if (isIllegalIndex(tmp, matrix) || listWithReadyPosition[tmp]) {
            isCorrect = false
            break
        }
        result.add(tmp)
        if (tmp == endPosition) break
    }
    if (!isCorrect) {
        current = startInNearPosition
        result.clear()
        for (i in nearPositions.indices) {
            current++
            if (current == nearPositions.size) current = 0
            tmp = nearPositions[current]
//            if (tmp == startPosition) continue // Был костыль. Не понял почему он лишний раз при непонятных обстоятельствах учитывает себя
            result.add(tmp)
            if (tmp == endPosition) break
        }
    }
    println("Rotate $result")
    return result
}

fun moveIfPossible( // Сторит тракторию, проверяет ее и идет к ней, иначе возвращет false
    currentPosition: Int,// Позиция текущего выбранного элемента
    targetPosition: Int,// Позиция куда следует перемстить выббранный элемент
    voidPosition: Int,// Позиция нуля
    listWithReadyPosition: Array<Boolean>,// Уже готовые позиции
    log: MutableList<Int>,// Журнал действий
    data: MutableMap<Int, Int>,// Копия данных матрицы которые будут менятся в процессе работы
    matrix: Matrix<Int>// матрица, которую менять не следует, наверное.
): Boolean {
    var actualVoidPosition = voidPosition
    var actualCurrentPosition = currentPosition
    var actualTargetPosition = getGuidingPosition(actualCurrentPosition, targetPosition, matrix.width)

    println("targetPosition == voidPosition $targetPosition $voidPosition")
    if (targetPosition == voidPosition && isNear(currentPosition, voidPosition, matrix)) {
        simpleMove(actualCurrentPosition, actualVoidPosition, log, data)
        return true
    }

    var trajectory = getTrajectory(
        actualCurrentPosition,
        actualTargetPosition,
        actualVoidPosition,
        listWithReadyPosition,
        matrix
    )

    if (trajectory.isEmpty()) {
        return if (targetPosition != actualCurrentPosition) {
            //TODO
            /*actualTargetPosition = getGuidingPosition(actualCurrentPosition, targetPosition, matrix.width)
            println("actualTargetPosition $actualTargetPosition")
            trajectory = getTrajectory(
                actualCurrentPosition, actualTargetPosition,
                actualVoidPosition, listWithReadyPosition, matrix
            )

            actualVoidPosition = moveVoidTo(trajectory, actualVoidPosition, log, data) // Доводит до current
            val tmp = actualVoidPosition
            actualVoidPosition =
                simpleMove(actualCurrentPosition, actualVoidPosition, log, data) // меняет местами с current
            actualCurrentPosition = tmp
            println("actualVoidPosition $actualVoidPosition")
            println("actualCurrentPosition $actualCurrentPosition")*/

            unNamedMove(
                targetPosition,
                matrix,
                actualCurrentPosition,
                actualVoidPosition,
                listWithReadyPosition,
                log, data
            )
            true
        } else {
            false
        }
    }

    while (trajectory.isNotEmpty()) {
        actualVoidPosition = moveVoidTo(trajectory, actualVoidPosition, log, data) // Доводит до current
        val tmp = actualVoidPosition
        actualVoidPosition =
            simpleMove(actualCurrentPosition, actualVoidPosition, log, data) // меняет местами с current

        actualCurrentPosition = tmp
        actualTargetPosition = getGuidingPosition(actualCurrentPosition, targetPosition, matrix.width)
//        println("start $actualVoidPosition center $actualCurrentPosition end $actualTargetPosition")
        trajectory = getTrajectory(
            actualCurrentPosition,
            actualTargetPosition, actualVoidPosition,
            listWithReadyPosition, matrix
        )
        println("data $data")
    }
    if (actualTargetPosition != actualCurrentPosition) {
        unNamedMove(
            targetPosition,
            matrix,
            actualCurrentPosition,
            actualVoidPosition,
            listWithReadyPosition,
            log, data
        )
    }
    return true
}

fun isNear(currentPosition: Int, center: Int, matrix: Matrix<Int>): Boolean {
    return getNeighbors(center, matrix.width, matrix.height).contains(currentPosition)
}

private fun unNamedMove(
    targetPosition: Int,
    matrix: Matrix<Int>,
    currentPosition: Int,
    voidPosition: Int,
    listWithReadyPosition: Array<Boolean>,
    log: MutableList<Int>,
    data: MutableMap<Int, Int>
) {
    println("unNamedMove")
    println("voidPosition $voidPosition targetPosition $targetPosition currentPosition $currentPosition")
    // Крайняя ячейка для которой необходим спец прием
    var actualCurrentPosition = currentPosition
    var trajectory: List<Int>
    var actualVoidPosition = voidPosition
    val targetCell = toCell(targetPosition, matrix.width)
    val currentCell = toCell(actualCurrentPosition, matrix.width)
    val isVertical = targetCell.column == currentCell.column

    // Должен довести до current
    trajectory = if (!isVertical) {// Горизонталь
        getTrajectory(
            actualCurrentPosition,
            toIndex(Cell(currentCell.row, currentCell.column + 1), matrix.width),
            actualVoidPosition,
            listWithReadyPosition, matrix
        )
    } else {// Вертикаль
        getTrajectory(
            actualCurrentPosition,
            toIndex(Cell(currentCell.row + 1, currentCell.column), matrix.width),
            actualVoidPosition,
            listWithReadyPosition, matrix
        )
    }

    actualVoidPosition = moveVoidTo(trajectory, actualVoidPosition, log, data)
    var tmp = actualVoidPosition
    actualVoidPosition = simpleMove(actualCurrentPosition, actualVoidPosition, log, data)
    println("data1 $data")

    actualVoidPosition = rotateFourCells(actualVoidPosition, isVertical, 1, matrix.width, log, data)
    println("rotate4 $data")
    actualCurrentPosition = tmp
    tmp = actualVoidPosition
    actualVoidPosition = simpleMove(actualCurrentPosition, actualVoidPosition, log, data)
    actualCurrentPosition = tmp
    listWithReadyPosition[targetPosition - 1] = false

    trajectory = if (!isVertical) {// Горизонталь
        getTrajectory(
            actualCurrentPosition,
            toIndex(Cell(currentCell.row - 1, targetCell.column), matrix.width),
            actualVoidPosition,
            listWithReadyPosition, matrix
        )
    } else {// Вертикаль
        getTrajectory(
            actualCurrentPosition,
            toIndex(Cell(targetCell.row, currentCell.column - 1), matrix.width),
            actualVoidPosition,
            listWithReadyPosition, matrix
        )
    }
    actualVoidPosition = moveVoidTo(trajectory, actualVoidPosition, log, data)
//    println("data2 $data")
    actualVoidPosition = if (targetCell.row == currentCell.row) {
        simpleMove(actualCurrentPosition - 1, actualVoidPosition, log, data)
    } else {
        simpleMove(actualCurrentPosition - matrix.width, actualVoidPosition, log, data)
    }
//    println("data3 $data")
    simpleMove(actualCurrentPosition, actualVoidPosition, log, data)
//    println("data4 $data")
    listWithReadyPosition[targetPosition - 1] = true
}

fun rotateFourCells(
    voidPosition: Int,
    isCounterclockwise: Boolean,
    countRotates: Int,
    width: Int,
    log: MutableList<Int>,
    data: MutableMap<Int, Int>
): Int {
    val top = voidPosition - width
    val tLeft = top - 1
    val left = voidPosition - 1
    val list =
        if (isCounterclockwise) listOf(top, tLeft, left, voidPosition) else listOf(left, tLeft, top, voidPosition)
    println("rotateFourCells $list")
    var actualVoidPosition = voidPosition
    for (i in 0 until list.size * countRotates) {
        actualVoidPosition = simpleMove(list[i], actualVoidPosition, log, data)
    }
    return actualVoidPosition
}

fun getGuidingPosition(currentPosition: Int, targetPosition: Int, width: Int): Int {
    val targetCell = toCell(targetPosition, width)
    val currentCell = toCell(currentPosition, width)
    val deltaColumn = targetCell.column - currentCell.column
    val deltaRow = targetCell.row - currentCell.row

    if (deltaColumn == 0 && deltaRow == 0) return targetPosition

    if (deltaColumn == 0) return currentPosition + deltaRow / abs(deltaRow) * width

    return currentPosition + deltaColumn / abs(deltaColumn)
}

fun moveVoidTo(
    // Выполняет всю траеторию
    trajectoryMove: List<Int>,
    voidPosition: Int,
    log: MutableList<Int>,
    data: MutableMap<Int, Int>,
): Int {// Возвращает позицию нуля
    var actualVoidPosition = voidPosition
    if (trajectoryMove.isEmpty()) println("IsEmptyTrajectory")
    for (target in trajectoryMove) {
        actualVoidPosition = simpleMove(target, actualVoidPosition, log, data)
    }
    return actualVoidPosition
}

fun getTrajectory( // Возращает позиции, по которым должен пройти ноль
    currentPosition: Int,
    targetPosition: Int,
    voidPosition: Int,
    listWithReadyPosition: Array<Boolean>,
    matrix: Matrix<Int>
): List<Int> {
    val result = mutableListOf<Int>()
    if (currentPosition == targetPosition) return result

    val currentCell = toCell(currentPosition, matrix.height)
    val targetCell = toCell(targetPosition, matrix.height)
    val voidCell = toCell(voidPosition, matrix.height)

    var rowDelta = targetCell.row - voidCell.row
    var columnDelta = targetCell.column - voidCell.column
    // проверка - на замкнутость конечной позиции
    var count = 0
    for (i in getNeighbors(targetPosition, matrix.width, matrix.height)) {
        if (isIllegalIndex(i, matrix)) {
            count++
        } else if (listWithReadyPosition[i]) {
            count++
        }
    }
    if (count >= 3) {
        println("count $count + target $targetPosition")
        return result
    } // это случай для приема №3

    var actualVoidPosition = voidPosition

    // Стандартная реализация
    if ((rowDelta != 0 &&
                ((targetCell.column == voidCell.column && targetCell.column == currentCell.column))) ||
        (columnDelta != 0 &&
                ((targetCell.row == voidCell.row && targetCell.row == currentCell.row)))
    ) {
        rowDelta = targetCell.row - currentCell.row
        columnDelta = targetCell.column - currentCell.column
        // Довожу до радиуса вокруг выбранной точки
        for (i in 1 until abs(rowDelta)) {
            actualVoidPosition += rowDelta / abs(rowDelta) * matrix.width
            result.add(actualVoidPosition)
            rowDelta += if (rowDelta > 0) -1 else 1 // уменьшаю дельту
        }
        for (i in 1 until abs(columnDelta)) {
            actualVoidPosition += columnDelta / abs(columnDelta)
            result.add(actualVoidPosition)
            columnDelta += if (columnDelta > 0) -1 else 1 // уменьшаю дельту
        }
        result.addAll(
            rotateTrajectory(
                actualVoidPosition, targetPosition,
                currentPosition, listWithReadyPosition, matrix
            )
        )

    } else {
        println("voidPosition $voidPosition columnDelta $columnDelta rowDelta $rowDelta currentPosition $currentPosition")
        if (voidPosition + columnDelta == currentPosition && abs(rowDelta) > 0) { // Если Void и Current на одной строке
            actualVoidPosition += rowDelta / abs(rowDelta) * matrix.width
            result.add(actualVoidPosition) // Сперва поднимаю на 1 клетку (кейса, где нужно опускать на 1 клетку в ст. реализации нет)
            rowDelta += if (rowDelta > 0) -1 else 1 // уменьшаю дельту
        }
        var isCorrectWay = true
        for (i in 0 until abs(columnDelta)) {
            val index = actualVoidPosition + (i + 1) * columnDelta / abs(columnDelta)
            if (listWithReadyPosition[index] || index == currentPosition) {
                isCorrectWay = false
                break
            }
        }
        println("isCorrectWay $isCorrectWay")
        if (!isCorrectWay) {
            for (i in 0 until abs(rowDelta)) {
                actualVoidPosition += rowDelta / abs(rowDelta) * matrix.width
                result.add(actualVoidPosition)
            }
            for (i in 0 until abs(columnDelta)) {
                actualVoidPosition += columnDelta / abs(columnDelta)
                result.add(actualVoidPosition)

            }
        } else {
            for (i in 0 until abs(columnDelta)) {
                actualVoidPosition += columnDelta / abs(columnDelta)
                result.add(actualVoidPosition)

            }
            for (i in 0 until abs(rowDelta)) {
                actualVoidPosition += rowDelta / abs(rowDelta) * matrix.width
                result.add(actualVoidPosition)
            }
        }
    }
    println("Trajectory $result")
    return result
}

fun simpleMove( // Передвижение на 1 клетку
    targetPosition: Int,
    voidPosition: Int,
    log: MutableList<Int>?,
    data: MutableMap<Int, Int>
): Int { // Возвращает позицию нуля
//    println("targetPosition $targetPosition")
    val tmp = data[targetPosition]!!
    data[targetPosition] = 0
    data[voidPosition] = tmp
//    println("target $targetPosition targetValue $tmp void $voidPosition")
    log?.add(tmp)
    return targetPosition
}

fun simpleMove( // Передвижение на 1 клетку
    targetPosition: Int,
    voidPosition: Int,
    log: MutableList<Int>?,
    matrix: Matrix<Int>
): Int { // Возвращает позицию нуля
//    println("targetPosition $targetPosition")
    val tmp = matrix[toCell(targetPosition, matrix.height)]!!
    matrix[toCell(targetPosition, matrix.height)] = 0
    matrix[toCell(voidPosition, matrix.height)] = tmp
//    println("target $targetPosition targetValue $tmp void $voidPosition")
    log?.add(tmp)
    return targetPosition
}

fun getNearPosition(center: Int, width: Int): List<Int> {
    val top = center - width
    val bottom = center + width
    return listOf(top - 1, top, top + 1, center + 1, bottom + 1, bottom, bottom - 1, center - 1)
}