# Kotlin Notes - 3
#### @ogzhntutucu

---
## 3.1
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

Since Kotlin 1.5, you can use other functions to convert `String` to `Boolean`. The function `toBooleanStrict()` returns `true` if the string is equal to the word "true", case sensitive, and `false` if the string is equal to "false"; otherwise, your program will crash with an `java.lang.IllegalArgumentException`. 

The function `toBooleanStrictOrNull()` returns `true` if the string is equal to the word "true", case sensitive, and `false` if the string is equal to "false"; otherwise, it returns a special value `null`.

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
## 3.2
### Logical operators

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

XOR (exclusive OR) is a binary operator that returns `true` if the Boolean operands have different values. Otherwise, it is `false`.

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
## 3.3
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
## 3.4
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
## 3.5
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
## 3.6
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

---
## 3.7
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

---
## 3.8
### Type overflow

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
## 3.9
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
## 3.10
### Standart input & readln

Here is a simple program that reads a line from the standard input and sends it to the standard output:

```kotlin
fun main() {
    val line = readln()
    println(line)
}
```

#### Reading multiple values in one line

You can use this construction. You can read up to **four values** per line:

```kotlin
val (a, b, c, d) = readln().split(" ")
println(a)
println(b)
println(c)
println(d)
```

Here is an example of input data:

```no-highlight
Have a nice Kotlin
```

The output would be the following:

```no-highlight
Have
a
nice
Kotlin
```

This construction splits the input string at spaces and stores the words in the variables.

---
## 3.11
### Explicitly conversion

```kotlin
fun main() {  
    val a: Int = 10
    val b: Long = a  // Error: Initializer type mismatch: expected 'Long', actual 'Int'.
    
    // a.toLong() ile açıkça dönüştürmelisin.
}
```

Kotlin'de bu hatayı almanızın temel sebebi, Kotlin'in örtük tip dönüşümünü (implicit conversion) desteklememesidir. Java gibi dillerin aksine Kotlin, veri kaybı olmasa bile (Int'ten Long'a geçiş gibi) bir tipi otomatik olarak başka bir tipe dönüştürmez.

Bunun birkaç önemli sebebi vardır:
1. Tip Güvenliği (Type Safety): Kotlin, kodun daha öngörülebilir olmasını ister. Küçük bir tipin büyük bir tipe otomatik atanması, bazen karmaşık aşırı yüklenmiş (overloaded) fonksiyonlarda hangi fonksiyonun çağrılacağı konusunda kafa karışıklığına yol açabilir.
2. Nesne Yapısı: Kotlin'de Int ve Long aslında birer sınıftır. Teknik olarak Int, Long'un bir alt sınıfı değildir; bu yüzden birbirlerinin yerine doğrudan kullanılamazlar.

---
## 3.12
### Type coercion

But what happens if we calculate the sum of `Int` and `Long` variables? In this case, the type is inferred from the context.

In such cases, the compiler automatically sets all components (it's called **type coercion**) and the result type to the widest type in the expression. The picture below illustrates the direction of this casting:

![](resources/_attachments/Pasted%20image%2020260707232408.png)

Since the type of the result is wider than the previous type, there is no loss of information.
Type coercion is rare in Kotlin. It works only with numbers and strings.

from `Int` to `Long`:

```kotlin
val num: Int = 100
val longNum: Long = 1000
val result = num + longNum // 1100, Long
```

from `Long` to `Double`:

```kotlin
val bigNum: Long = 100000
val doubleNum: Double = 0.0
val bigFraction = bigNum - doubleNum // 100000.0, Double
```

`Short` and `Byte`

```kotlin
val hundred: Short = 100
val five: Byte = 5
val zero = hundred % five // 0, Int
```

short-short, byte-byte, short-byte kullanimlarinda sonuc Int olur. bu ikisi icin boyle bir sey var.

So what should we do if we want to sum two `Byte` variables and get a `Byte` result? Well, in this case, you must manually perform type conversion:

```kotlin
val one: Byte = 1
val five: Byte = 5
val six = (one + five).toByte() // 6, Byte
```

Remember that `Byte` can store data in the range `-128.. 127.`

Look at the example below of how type overflow works:

```kotlin
fun main() {
    val a: Byte = 120
    println((a + a).toByte()) // prints -16 because 120+120 > 127
}
```

---
## 3.13
### Initializer type mismatch (Kata)

Select all **invalid** lines in the following snippet:

```kotlin
val b1: Byte = 5         // Line 1
val b2: Byte = 2 + 3     // Line 2 - invalid
val s1: Short = 2 + b1   // Line 3 - invalid
val s2: Short = 10 + 3L  // Line 4 - invalid

// Line 2 icin: Int + Int -> Int
// Sen koda 2 + 3 yazdığında, derleyici arka planda bunu şu şekilde okur:
// 2 bir Int değeridir. Üzerindeki 'plus' fonksiyonu çağrılır ve içine parametre olarak 3 gönderilir.
2.plus(3)
// yani 2+3 byte olmamis oldu.

// Line 3 icin: Int + Byte -> Int
// Line 4 icin: Int + Long -> Long
```

---
## 3.14
### Unsigned integers

We already know that all integer types in Kotlin — `Int`, `Long`, `Byte`, and `Short` can be both positive and negative. In addition to all these integer types, Kotlin provides the ability to create **unsigned** integers – integers that can contain only non-negative values.

Kotlin provides the following unsigned types:

|   |   |
|---|---|
|**Type**|**Description**|
|`UByte`|an unsigned 8-bit integer, ranges from 0 to 255|
|`UShort`|an unsigned 16-bit integer, ranges from 0 to 65535|
|`UInt`|an unsigned 32-bit integer, ranges from 0 to 4 294 967 295 (2^32-1)|
|`ULong`|an unsigned 64-bit integer, ranges from 0 to 18 446 744 073 709 551 615 (2^64-1)|

Unsigned numbers are created in the same way as any others. In order to indicate that you are creating an unsigned number, you need to add the suffix "`u`" or "`U`" to it.

```kotlin
val uByte: UByte = 5u
val uShort: UShort = 10U
```

In this example, we create variables of specified types. But, if you don't indicate the type directly, then the compiler will use `UInt` or `ULong` depending on the size of the literal:

```kotlin
val smallSize = 100u // UInt by default
val bigSize = 5_000_000_000u // ULong because the number doesn't fit in UInt
```

There is also a special suffix "`uL`" (or "`UL`"). If you tag a number with this suffix, then an `ULong` will be created regardless of the size of the number:

```kotlin
val smallLong = 10uL // ULong because it is marked with "uL"
```

---
## 3.15
### Data type overflow

All arithmetic operations for signed types are possible with their unsigned counterparts, except for the unary minus operation.

Let's look at the results of the following code:

```kotlin
// MAX_VALUE: Int = 2147483647
var d: Int = 2147483647
d += 1
println(d) // -2147483648
```

An unexpected result. A similar situation is called data type overflow. Note that these are details and it is not necessary to figure it out now. To understand what happened, imagine an empty glass into which water is poured. When the glass is full, the water overflows over the edges. The same situation happens with variables. When the variable value is close to the boundary value, there is a risk of a situation where the resulting value does not fit into the allocated memory for the variable. Overflow of the variable type leads to data loss, unexpected behavior of the program, etc.

Aşağıdaki noktaları hatırlamak önemlidir: 
- Veri tipi taşma hataları programcı hatalarıdır. 
- Veri türü taşması durumunda program davranışı öngörülemez. 
- Derleyici tür taşmaları için sizi uyarmayacaktır, bu nedenle değişkenler için veri türlerini doğru seçmeniz ve veri türlerinin dönüşümünü dikkatle izlemeniz gerekir.

---
## 3.16
### Java'daki if ile kotlin'deki if'in farki

**Kotlin'de `if` bir İfade'dir (Expression)**, yani bir değer üretir. Java'da `if` sadece bir Komut'tur (Statement) — akışı yönlendirir ama değer döndürmez. Kotlin'de ise `if`'in kendisi bir değere "eşit" olabilir.

`return`'ü iki yere de yazmak yerine, `if`'in bir expression olmasından yararlanıp **tek bir `return`** yazabilirsin. `if`'in ürettiği değeri döndürürsün:

```kotlin
// return "yukarı kaldırıldı" (lifted out): artık if'in ürettiği sonucu döndürüyoruz
return if (valueN % 2 == 0) valueN / 2 else (valueN + 1) / 2
```

"Lifted out of if" derken kastedilen bu: `return` kelimesini `if`'in **dışına** çıkardık.

---
