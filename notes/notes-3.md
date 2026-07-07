# Kotlin Notes - 3
#### @ogzhntutucu

---
### Boolean variables

You cannot assign an integer value to a Boolean variable. In Kotlin, `0` is not the same as `false`.

Kotlin 1.6, you can read a Boolean value like this:

```kotlin
val b: Boolean = readln().toBoolean()
```

`toBoolean()` returns `true` if the input is "true", case insensitive. Otherwise, it will return `false`.

So, suppose you have the following input:

```kotlin
true
True
TRuE
T
false
```

The output will be as follows:

```kotlin
println(readln().toBoolean()) // true
println(readln().toBoolean()) // true
println(readln().toBoolean()) // true
println(readln().toBoolean()) // false
println(readln().toBoolean()) // false
```

Since Kotlin 1.5, you can use other functions to convert `String` to `Boolean`. The function `toBooleanStrict()` returns `true` if the string is equal to the word "true", case sensitive, and `false` if the string is equal to "false"; otherwise, your program will crash with an `java.lang.IllegalArgumentException`. The function `toBooleanStrictOrNull()` returns `true` if the string is equal to the word "true", case sensitive, and `false` if the string is equal to "false"; otherwise, it returns a special value `null`. We will discuss it later.

```kotlin
println("true".toBooleanStrict())     // true
// println("True".toBooleanStrict())  // program crashes
println("false".toBooleanStrict())    // false
// println("faLse".toBooleanStrict()) // program crashes

println("true".toBooleanStrictOrNull())  // true
println("false".toBooleanStrictOrNull()) // false
println("faLse".toBooleanStrictOrNull()) // null
```

---

### logical operators

Boolean type variables construct logical statements with the help of logical operators. Kotlin has four logical operators: **NOT**, **AND**, **OR**, and **XOR:**

NOT is a unary operator that reverses the Boolean value. It can be denoted with `!`.

```kotlin
val f = false // f is false
val t = !f    // t is true
```

AND is a binary operator that returns `true` if both operands are `true`. Otherwise, it returns `false`. It can be denoted with `&&`.

```kotlin
val b1 = false && false // false
val b2 = false && true // false
val b3 = true && false // false
val b4 = true && true  // true 
```

OR is a binary operator that returns `true` if at least one operand is `true`. Otherwise, it returns `false`. It can be denoted with `||`.

```kotlin
val b1 = false || false // false
val b2 = false || true  // true
val b3 = true || false  // true
val b4 = true || true   // true
```

- XOR (exclusive OR) is a binary operator that returns `true` if the Boolean operands have different values. Otherwise, it is `false`.

```kotlin
val b1 = false xor false // false
val b2 = false xor true  // true
val b3 = true xor false  // true
val b4 = true xor true   // false
```

mantiksal operatorleri TRUE uzerinden aklinda tut:
- `!` NOT -> bir deger false ise tersi true'dur.
- `xor` XOR -> iki deger birbirlerinin zitti olmalilar.
- `&&` AND -> iki degerden ikisi de true ya da ikisi de false olmali.
- `||` OR -> iki degerden herhangi birinin true olmasi yeterli.

---
### Logical operator precedence

Take a look at the **precedence** of logical operations in Kotlin below. Precedence determines how other variables are grouped in the absence of parentheses:

1. NOT
2. XOR
3. AND
4. OR

You can use parentheses `(...)` to change the order of execution.

For example, let's write a **Boolean expression** that determines the possibility of going on a hike during the summer and in other seasons:

```kotlin
var isCold = false
var isDry = true
var isSummer = false

val canGoHiking = isDry && (!isCold || isSummer)

if (canGoHiking) {
    println("Let's go hiking!")
}
```

Gündelik hayatta hava durumu sürekli değişir; programlamada bunları `Değişken (Variable)` olarak tanımlarız (`isDry`, `isCold`, `isSummer`). Çünkü bu değerler uygulamanın çalıştığı anki duruma, bir sensörden gelen veriye veya kullanıcının girdisine göre farklılık gösterir.

Ancak, `canGoHiking` ifadesi uygulamanın **kuralıdır**. Buna yazılım dünyasında `İş Mantığı (Business Logic)` deriz. "Hangi şartlar altında yürüyüşe çıkılır?" sorusunun cevabı, yani kural seti, sistemin gereksinimleri değişmediği sürece sabittir.

1. **"Hava kuru olmalı, eğer kuru değilse diğer ikisinin önemi yok":** Bu tam olarak `isDry && ...` kısmıdır. `Ve (AND)` operatörü soldan sağa çalışır. Eğer `isDry` değeri `false` ise, Kotlin sağ tarafın sonucunu umursamaz ve orayı **hiç okumaz**. Çünkü sonuç zaten `false` çıkacaktır. Buna `Kısa Devre (Short-Circuit)` denir ve harika bir performans optimizasyonudur.
2. **"Yaz olmalı veya yaz değilse bile soğuk olmamalı":** Bu da parantez içindeki `(!isCold || isSummer)` kısmıdır. `Veya (OR)` mantığında iki seçenekten birinin kurtarması yeterlidir.

Madem bu `İş Mantığı (Business Logic)` sabit ve programın farklı yerlerinde tekrar tekrar kullanılabilir, bunu tek satırlık bir değişkende tutmak yerine bir `Fonksiyon (Function)` haline getirmek çok daha profesyoneldir.

Kotlin stil rehberine göre, sadece tek bir ifadeden oluşan fonksiyonları süslü parantez `{}` açmadan, doğrudan = işaretiyle yazmak en şık (idiomatic) yöntemdir. Buna `İfade Fonksiyonu (Expression Function)` denir.

```kotlin
// İş mantığımızı (Business Logic) dışarıdan parametre alan bir fonksiyona çevirdik.
// Tek bir ifadeden (expression) oluştuğu için süslü parantez kullanmadık ve return tipini eşittir (=) ile verdik.
fun checkHikingConditions(isDry: Boolean, isCold: Boolean, isSummer: Boolean): Boolean =
    isDry && (!isCold || isSummer) // Eğer çok uzun olsaydı, = işaretinden sonra alt satıra geçip girinti (indent) eklemek gerekirdi.

fun main() {
    // Sensörlerden veya API'den gelen anlık veriler (Değişkenler)
    val currentDry = false
    val currentCold = false
    val currentSummer = true

    // Fonksiyonumuzu çağırarak anlık duruma göre yürüyüşe çıkıp çıkamayacağımızı test ediyoruz.
    // currentDry 'false' olduğu için fonksiyon anında false dönecek (Short-Circuit).
    if (checkHikingConditions(isDry = currentDry, isCold = currentCold, isSummer = currentSummer)) {
        println("Çantayı hazırla, çıkıyoruz!")
    } else {
        println("Bugün evde oturmak daha iyi.")
    }
}
```

---

### Go to the store (Kata)

Imagine you need to determine whether you should go to a store. There are two variables to help you make a choice:

- `isClosingSoon` determines that this store is closing soon (in a few minutes)
- `isNear` determines that this store is near you

Write a boolean expression that is `true` if at least one condition is true:

- the store is not closing soon 
- the store is near you

Otherwise, the expression is `false`.

```kotlin
// Business Logic in Expression Function
fun shouldGo(isClosingSoon: Boolean, isNear: Boolean): Boolean = 
	!isClosingSoon || isNear
```

|**isClosingSoon (Kapanıyor mu?)**|**isNear (Yakın mı?)**|**!isClosingSoon**|**!isClosingSoon \| isNear (Sonuç)**|**Gidilir mi?**|
|---|---|---|---|---|
|**false** (Vakit var)|**false** (Uzak)|true|**true**|✅ Evet (Vakit bol, uzak da olsa gidilir)|
|**false** (Vakit var)|**true** (Yakın)|true|**true**|✅ Evet (Hem vakit var hem yakın)|
|**true** (Kapanıyor!)|**true** (Yakın)|false|**true**|✅ Evet (Kapanıyor ama yakın, yetişirim)|
|**true** (Kapanıyor!)|**false** (Uzak)|false|**false**|❌ Hayır (Hem kapanıyor hem uzak, yetişemem)|

---

### Reverse a user-input string (Kata)

Write a program in Kotlin that reads a string and returns the reversed version of that string. Your program should interactively ask the user for a single string and should display the reversed string as the output.

```kotlin
import java.util.Scanner

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val inputString = scanner.nextLine()

    var reversedString = ""

    for (i in inputString.length - 1 downTo 0) {
        reversedString += inputString[i]
    }

    println(reversedString)
    
    scanner.close()
}
```

---

### Conversion between numeric types

The three most common numeric types are: `Int`, `Long`, and `Double`. Sometimes, you may need to assign a value of one numeric type to a variable of another numeric type. In this case, you need to carry out **type conversion** by invoking a special function, for example, `toInt()`, `toLong()`, `toDouble()`, and so on.

```kotlin
val num: Int = 100
val bigNum: Long = num.toLong() // 100
```

Normally, you can use functions `toShort()` and `toByte()` to convert something to these types. Since Kotlin 1.4, you should avoid these functions when you try to convert `Double` or `Float` type. This feature has already been removed in current releases. The main problem here is that the conversion can lead to unexpected results due to the variable's smaller size. Now, you need to convert `Double` or `Float` to `Int` and then convert the result to `Short` or `Byte`:

```kotlin
val floatNumber = 10f
val doubleNumber = 1.0

val shortNumber = floatNumber.toShort() // avoid this
val byteNumber = doubleNumber.toByte()  // avoid this

val shortNumber = floatNumber.toInt().toShort() // correct way
val byteNumber = doubleNumber.toInt().toByte()  // correct way

// zaten ide izin vermiyor buna.
```

### String conversion

A string can be converted to a number or even to a boolean value but not to a single character.

```kotlin
val n = "8".toInt() // Int
val d = "10.09".toDouble() // Double
val b = "true".toBoolean() // Boolean
```

If you convert a string to a boolean value, no errors will occur. If the string is `"true"` (**case insensitive**), it will produce a `true` boolean value, otherwise a `false` one.

```kotlin
val b1 = "false".toBoolean() // false
val b2 = "tru".toBoolean()   // false
val b3 = "true".toBoolean()  // true
val b4 = "TRUE".toBoolean()  // true
```

If you have a value of a floating type, a `Double` value, for example, you may convert it to a value of an integer type, such as `Int` or `Long`. Let's check what happens:

```kotlin
val d: Double = 12.5
val n: Long = d.toLong() // 12
```

As you can see, the fractional part is simply dropped. So, you get a result but lose some precision. Be careful with this conversion!

This conversion may truncate the value, as `Long` and `Double` can store numbers larger than the truncated `Int` number.

### type overflow

```kotlin
val bigNum: Long = 100_000_000_000_000

val n: Int = bigNum.toInt() // 276447232; oops
```

As a result, we receive a truncated value. This problem is known as **type overflow**. The same problem may occur if you try to convert `Int` to `Short` or `Byte`. So, if you want to convert a larger type value into a smaller one, make sure that the truncation is not going to mess up your program.

The program below demonstrates the functions discussed above. It reads a string representation of a number, converts it to several other types, and then prints the results of all conversions.

```kotlin
fun main() {
    val something = readln()

    val d = something.toDouble()
    val f = d.toFloat()
    val i = f.toInt()
    val b = i.toByte()

    println(d)
    println(f)
    println(i)
    println(b)
    println(something.toBoolean())
}
```

Imagine, we have the following input:

```kotlin
1000.0123456789
```

The program will output the following:

```kotlin
1000.0123456789
1000.0123
1000
-24
false
```

Let's take a closer look at the output. 
- The number represented by a string is successfully converted to `Double`, as it has a suitable format. This number can be saved as a `Double` type safely.
- Then the number is converted to `Float`. 
- We see a loss here, as this type can store fewer decimal numbers. The `Int` conversion cuts the fractional part. 
- The number 1000 is larger than the `Byte` type can store (from -128 to 127), so we have a **type overflow** (-24). 
- The result of converting the input string to `Boolean` is `false`, because the value is not `"true"` (**case insensitive**).

---

### Cutting the fraction (Kata)

Write a program that reads a number, cuts off its fractional part, and prints the integer part of the input number as the result.
Use `Double` as the input type and `Long` as the result type (values may be quite large).
**Input**: single number of type `Double`.
**Output**: single number of type `Long`.

```kotlin
import java.util.Scanner  
  
fun main() {  
	// cozum 1
    val scanner = Scanner(System.`in`)  
    val input = scanner.nextDouble()  
    println(input.toLong())  
    scanner.close()  
	
	// cozum 2
    readlnOrNull()?.toDoubleOrNull()?.toLong().let(::println)  
}
```

`let { ... }:`
- Bu bir "Scope Function"dır. Zincirdeki bir önceki değer null değilse çalışır.
- İçindeki değeri (yani bizim Long sayımızı) bir sonraki fonksiyona parametre olarak gönderir.
`::println` (Function Reference):
- let içindeki değeri doğrudan ekrana yazdırmak için kullanılan kısa bir yazımdır.
- `let { println(it) }` yazmakla aynı şeydir.

---

