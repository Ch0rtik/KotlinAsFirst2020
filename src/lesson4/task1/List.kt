@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.getNumberList
import kotlin.math.min
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double = TODO()

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) return 0.0

    var sum = 0.0
    for (i in list) {
        sum += i
    }

    return sum / list.size
}

/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val arithmeticMean = mean(list)

    for (i in 0 until list.size) {
        if (arithmeticMean == 0.0) break
        list[i] -= arithmeticMean
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int = TODO()

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> = TODO()

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val divisors = mutableListOf<Int>()

    var x = n
    var currentDivisor = 2
    while (x != 1){
        if(x % currentDivisor == 0){
            divisors.add(currentDivisor)
            x /= currentDivisor
        }else{
            currentDivisor++
        }
    }
    return divisors
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String = TODO()

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int = TODO()

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int = TODO()

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    // 1 - I
    // 5 - V
    // 10 - X
    // 50 - L
    // 100 - C
    // 500 - D
    // 1000 - M
    // 5000 - ↁ
    // 10_000 - ↂ
    // 50_000 - ↇ
    // 100_000 - ↈ
    // 1_000_000 - W (не нашел символа для миллиона в табл. Юникод, поэтому W)
    // Алгорит должен постепенно двигаться справа налево
    TODO()
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun russian(n: Int): String {
    val numbers = getNumberList(n)
    val nameTriple = RussianNumbers.nameTriple // Знаю, что миллион не понадобится.
    // Разрабатываю логику с возможность масштабирования
    val units = RussianNumbers.units
    val tens = RussianNumbers.tens
    val hundreds = RussianNumbers.hundreds

    val results = mutableListOf<String>()

    var countTriple = 0

    var currentList: MutableList<String>

    for (i in numbers.size - 1 downTo 0) {
        val rang = (numbers.size - 1) - i
        val currentTriple = rang / 3
        if (currentTriple != countTriple) {
            countTriple = currentTriple

            if (countTriple < nameTriple.size) {
                // Добавляет "тысяча", "миллион" и т.д.
                val ten = if (i - 1 >= 0 && numbers[i - 1] == 1) {
                    1
                } else 0
                results.add(RussianNumbers.renameFromCount(numbers[i] + ten * 10, nameTriple[countTriple].word))
            }
        }

        // Если число > 10 and < 20, то когда его заменять? -> На момент, когда обрабатываю десятки
        currentList = when (rang % 3) {
            0 -> units
            1 -> tens
            else -> hundreds
        }
        if (rang % 3 == 1 && numbers[i] == 1 && numbers[min(i + 1, numbers.size - 1)] > 0) {
            // если число > 10 and < 20, то заменяю единицы на исключения (11-19)
            results[min(rang - 1 + countTriple, results.size - 1)] =
                RussianNumbers.getExceptionYears(10 + numbers[i + 1])
        } else if (rang % 3 == 0 && rang > 0) {
            // если разряд единиц во второй и более тройке
            results.add(RussianNumbers.renameFromGender(numbers[i], currentList[numbers[i]], nameTriple[countTriple].gender))
        } else {
            results.add(currentList[numbers[i]])
        }
    }

    return buildString {
        for (i in results.size - 1 downTo 0) {
            if (results[i].isNotEmpty()) {
                append(results[i])
                if (i != 0) append(" ")
            }
        }
    }.trim()
}

object RussianNumbers {
    val nameTriple = mutableListOf(
        Word(""),
        Word("тысяча", WordHelper.Gender.WOMAN),
        Word("миллион")) // Знаю, что миллион не понадобится.
    // Разрабатываю логику с возможность масштабирования

    val units = mutableListOf("", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    val tens = mutableListOf(
        "",
        "десять",
        "двадцать",
        "тридцать",
        "сорок",
        "пятьдесят",
        "шестьдесят",
        "семьдесят",
        "восемьдесят",
        "девяносто"
    )
    val hundreds = mutableListOf(
        "",
        "сто",
        "двести",
        "триста",
        "четыреста",
        "пятьсот",
        "шестьсот",
        "семьсот",
        "восемьсот",
        "девятьсот"
    )

    fun renameFromCount(n: Int, word: String): String {
        val name = word/*if (n > 1) word else word*/ // TODO перевод в множ. число
        return when (n) {
            1 -> name
            in 2..4 -> name.dropLast(1) + WordHelper.getEnding(n, name)
//                in 11..19, 0 -> name.dropLast(1)
            else -> {
                name.dropLast(1)
            }
        }
    }

    fun getExceptionYears(n: Int): String {
        return when (n) {
            11 -> "одиннадцать"
            12 -> "двенадцать"
            13 -> "тринадцать"
            14 -> "четырнадцать"
            15 -> "пятнадцать"
            16 -> "шестнадцать"
            17 -> "семнадцать"
            18 -> "восемнадцать"
            19 -> "девятнадцать"
            else -> ""
        }
    }

    fun renameFromGender(n: Int, word: String, gender: WordHelper.Gender): String {
        return when (gender) {
            // Подумай как сделать более универсально
            // TODO метод для опр. рода
            WordHelper.Gender.MAN -> word //temp
            WordHelper.Gender.WOMAN -> when (n) {
                1 -> "одна"
                2 -> "две"
                else -> word
            }
            else -> {
                word//temp
            }
        }
    }

}

class Word(
    val word: String,
    val gender: WordHelper.Gender = WordHelper.Gender.MAN){
}

object WordHelper {
    enum class Case {
        NOMINATIVE, GENITIVE, ACCUSATIVE, DATIVE, INSTRUMENTAL, PREPOSITION
    }

    enum class Gender {
        MAN, WOMAN, NEUTER
    }

    fun getEnding(n: Int, name: String): String {
        //тысяча ,миллион ,биллион (миллиард)[3] ,триллион ,квадриллион ,квинтиллион ,секстиллион ,септиллион ,октиллион ,нониллион ,дециллион ,ундециллион
        val declination = getDeclination(name)
        return getEndFromDeclination(declination, Case.GENITIVE)
    }

    private fun getEndFromDeclination(declination: Int, case: Case): String {
        //TODO доделать для всех склонений и родов
        return when (declination) {
            1 -> {
                when (case) {
                    Case.GENITIVE -> "и"
                    else -> "" // temp
                }
            }
            2 -> {
                when (case) {
                    Case.GENITIVE -> "ов"
                    else -> "" // temp
                }
            }
            else -> {
                when (case) {
                    Case.GENITIVE -> "и"
                    else -> "" // temp
                }
            }
        }
    }

    private fun getDeclination(word: String): Int {
        //тысяча ,миллион ,биллион (миллиард)[3] ,триллион ,квадриллион ,квинтиллион ,секстиллион ,септиллион ,октиллион ,нониллион ,дециллион ,ундециллион
        if (word.length > 2) return -1
        val lastChar = word[word.length - 1]
//            val ends = mutableListOf('а', 'я', 'о', 'е', 'ь')
        val preEndingLetters = mutableListOf(
            'ч', 'ш', 'щ', 'ж', 'б', 'в', 'т', 'з', 'д', 'л'
        )

        return when (lastChar) {
            'а', 'я' -> 1
            'о', 'е' -> 2
            in preEndingLetters -> 3
            else -> 2
        }
    }
}