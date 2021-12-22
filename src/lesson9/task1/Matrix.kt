@file:Suppress("UNUSED_PARAMETER", "unused")

package lesson9.task1

// Урок 9: проектирование классов
// Максимальное количество баллов = 40 (без очень трудных задач = 15)

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)


/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E

    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)

    operator fun set(cell: Cell, value: E)
}

/**
 * Простая (2 балла)
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> {
    val matrix = MatrixImpl<E>(width, height)
    matrix.init(e)
    return matrix
}

/**
 * Средняя сложность (считается двумя задачами в 3 балла каждая)
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val width: Int, override val height: Int) : Matrix<E> {
    companion object {
        fun toCell(index: Int, height: Int): Cell = Cell(index / height, index % height)

        fun toIndex(cell: Cell, width: Int): Int = toIndex(cell.row, cell.column, width)

        fun toIndex(row: Int, column: Int, width: Int): Int = row * width + column
    }

    init {
        if (width <= 0 || height <= 0) throw IllegalArgumentException("Weight or height must be > 0")
    }

    private val data = mutableMapOf<Int, E>()

    fun init(defaultValue: E) {
        for (i in 0 until height * width) {
            data[i] = (defaultValue)
        }
    }

    override fun get(row: Int, column: Int): E {
        if (!indexIsExist(row, column)) {
            throw IllegalArgumentException("The cell ${toIndex(row, column, width)} not exist")
        }
        return data[toIndex(row, column, width)]!!
    }

    private fun indexIsExist(row: Int, column: Int): Boolean {
        val index = toIndex(row, column, width)
        return !(index >= width * height || index < 0)
    }

    override fun get(cell: Cell): E {
        return get(cell.row, cell.column)
    }

    fun get(index: Int): E {
        if ((index >= width * height || index < 0)) throw IllegalArgumentException("The index $index not exist")
        return data[index]!!
    }

    override fun set(row: Int, column: Int, value: E) {
        if (!indexIsExist(row, column)) {
            throw IllegalArgumentException("The cell ${toIndex(row, column, width)} not exist")
        }
        data[toIndex(row, column, width)] = value
    }

    override fun set(cell: Cell, value: E) {
        set(cell.row, cell.column, value)
    }

    override fun equals(other: Any?): Boolean {
        if ((other as? Matrix<*>) != null) {
            if (width != other.width || height != other.height) return false
            for ((i, v) in data) {
                if (other[toCell(i,other.height)] != v) return false
            }
        }
        return true
    }

    override fun toString(): String {
        return data.toString()
    }

    override fun hashCode(): Int {
        var result = width
        result = 31 * result + height
        result = 31 * result + data.hashCode()
        return result
    }
}

