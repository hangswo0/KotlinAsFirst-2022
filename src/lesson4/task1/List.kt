@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.math.*
import lesson3.task1.digitNumber

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
    val lowerCase = str.lowercase().filter { it != ' ' }
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
fun abs(v: List<Double>): Double = sqrt((v.map { it * it }).sum())

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if (list.isEmpty()) return 0.0 else return (list.sum() / list.size)
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
    val average = mean(list)
    for (i in list.indices) {
        list[i] -= average
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
fun times(a: List<Int>, b: List<Int>): Int {
    return a.mapIndexed { index, value -> a[index] * b[index] }.sum()
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    return p.mapIndexed { index, value -> p[index] * x.toDouble().pow(index).toInt() }.sum()
}

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
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    for (i in 1 until list.size) {
        list[i] = list[i] + list[i - 1]
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    var newN = n
    var i = 2
    while (newN != 1) {
        if (newN % i == 0) {
            list.add(i)
            newN /= i
        } else i++
    }
    return list
}
/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String = factorize(n).joinToString(separator = "*")

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var newN = n
    val list = mutableListOf<Int>()
    if (n == 0) return listOf(0)
    while (newN != 0) {
        list.add(0, newN % base)
        newN /= base
    }
    return list
}

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
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    val list = digits.toMutableList()
    while (list.size != 0) {
        result += list[0] * (base.toDouble().pow(list.size - 1)).toInt()
        list.removeAt(0)
    }
    return result
}

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
fun decimalFromString(str: String, base: Int): Int {
    val alf = "abcdefghijklmnopqrstuvwxyz"
    val digits = "0123456789"
    var newStr = str
    var n = '0'
    var result = 0
    while (newStr.isNotEmpty()) {
        n = newStr[0]
        if (n in digits) {
            result += n.digitToInt() * base.toDouble().pow(newStr.length - 1).toInt()
        } else if (n in alf) {
            result += (alf.indexOf(n, 0) + 10) * base.toDouble().pow(newStr.length - 1).toInt()
        }
        newStr = newStr.substring(1, newStr.length)
    }
    return result
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    val units = listOf("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")
    val dozens = listOf("X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
    val hundreds = listOf("C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
    val result = mutableListOf<String>()
    if (n / 1000 != 0) result.add("M".repeat(n / 1000))
    if ((n % 1000) / 100 != 0) result.add(hundreds[(n % 1000) / 100 - 1])
    if ((n % 100) / 10 != 0) result.add(dozens[(n % 100) / 10 - 1])
    if (n % 10 != 0) result.add(units[n % 10 - 1])
    return result.joinToString(separator = "")
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun triple(n: Int): MutableList<String> {
    val units1 = listOf("ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять")
    val units2 = listOf("ноль", "один", "два", "три", "четыр", "пят", "шест", "сем", "восем", "девят")
    val dozens = listOf("десять", "двенадцать", "надцать", "дцать", "сорок", "десят", "девяносто")
    val hundreds = listOf("сто", "двести", "ста", "сот")
    val result = mutableListOf<String>()
    if (digitNumber(n) > 0) {
        if ((n / 100) != 0) {
            if ((n / 100) < 3) {
                if ((n / 100) == 1) result.add(hundreds[0]) else result.add(hundreds[1])
            } else {
                if ((n / 100) == 3 || (n / 100) == 4) result.add(units1[n / 100] + hundreds[2])
                else result.add(units1[n / 100] + hundreds[3])
            }
        }
        if ((n % 100) / 10 == 1) {
            when {
                (n % 10 == 0) -> result.add(dozens[0])
                (n % 10 == 2) -> result.add(dozens[1])
                else -> result.add(units2[n % 10] + dozens[2])
            }
        }
        if ((n % 100) / 10 != 1) {
            if ((n % 100) / 10 != 0) {
                when {
                    ((n % 100) / 10 == 2 || (n % 100) / 10 == 3) -> result.add(units1[(n % 100) / 10] + dozens[3])
                    ((n % 100) / 10 == 4) -> result.add(dozens[4])
                    ((n % 100) / 10 == 9) -> result.add(dozens[6])
                    else -> result.add(units1[(n % 100) / 10] + dozens[5])
                }
            }
            if (n % 10 != 0) result.add(units1[n % 10])
        }
    }
    return result
}
fun russian(n: Int): String {
    val firstTriple = triple(n / 1000)
    val secondTriple = triple(n % 1000)
    val result: List<String>
    if (digitNumber(n) < 4) return secondTriple.joinToString(separator = " ")
    else {
        if ("один" in firstTriple) {
            firstTriple.remove("один")
            firstTriple.add("одна тысяча")
            result = firstTriple + secondTriple
        } else if ("два" in firstTriple) {
            firstTriple.remove("два")
            firstTriple.add("две тысячи")
            result = firstTriple + secondTriple
        } else if (("три" in firstTriple) || ("четыре" in firstTriple)) {
            firstTriple.add("тысячи")
            result = firstTriple + secondTriple
        } else {
            firstTriple.add("тысяч")
            result = firstTriple + secondTriple
        }
    }
    return result.toList().joinToString(separator = " ")
}
