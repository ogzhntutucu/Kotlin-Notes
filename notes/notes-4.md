# Kotlin Notes - 4
#### [@ogzhntutucu](https://github.com/ogzhntutucu/Kotlin-Notes)

---
## 4.1
### Comparing integer numbers

Let's take a look at the example below. First, Kotlin calculates two sums, and after that, they are compared with the `>` operator:

```kotlin
val number = 1000
val result = number + 10 > number + 9 // 1010 > 1009 is true
```

Note that you cannot check the equality of `Int` and `Long`! You can compare `Int` and `Long` freely with `>`, `<`. `>=`, `<=`, but cannot use == and !=. You can check equality only for the same types, so you need to convert `Int` to `Long`:

```kotlin
val one: Long = 1
val zero: Int = 0

println(one >= zero)          // OK, prints true  
// println(one == zero)          Error
println(one == zero.toLong()) // OK, prints false
```

---
## 4.2
### Joining relational operations

Kotlin cannot process expressions like:

```kotlin
a <= b <= c
```

To do that, we should write something like this:

```java
number > 100 && number < 200
```

---
## 4.3
### Unicode

Unicode is standard in form of a table that defines the mapping between symbols and numbers.

Unicode can also be viewed as an extension of the ASCII table: in fact, the first 128 numbers in the Unicode represent ASCII symbols. All in all, Unicode can accommodate 1,112,064 code points, but as of May 2019, only 137,994 are occupied. The fact that most code points aren't designated yet is also one of Unicode's advantages. It means that for years and years to come, we can add new letters, scripts, punctuation marks, and pictograms to the standard without running out of space.

Unicode consists of two parts: **Universal character set, UCS,** and **Universal transformation format, UTF**.

**UCS** is essentially an index of all symbols supported by the Unicode and codes for those symbols. Let's take a look at a segment of this index. This is a part of Basic Latin, the symbols that have traveled from ASCII:

![](../resources/_attachments/Pasted%20image%2020260708223639.png)

**UTF-8** is the most commonly used encoding in the world as around 94% of the Internet is encoded in UTF-8. UTF-8 uses from 1 to 4 bytes per code point and is capable of representing all code points of the Unicode. UTF-8 is also backward compatible with ASCII. UTF-16 is similar and the difference between the two is mostly technical. You could use UTF-16, of course, but we recommend sticking to UTF-8 like most of the Internet.

---
## 4.4
### Chars

A character can also be represented by its hexadecimal code in the Unicode table. The code starts with `\u`:

```kotlin
val ch = '\u0040' // it represents '@'
println(ch) // @
```

You can also convert numbers to characters and vice versa. Let's take a look at how it works:

```kotlin
val ch = 'a'
println(ch.code)   // 97
val num = 97
println(num.toChar()) // a
```

Kotlin does not provide a convenient function to read a `Char`. However, you can do it another way: if you need to read one `Char` in a whole line, use this construction:

```kotlin
val ch: Char = readln().first()
```

There are two operators for adding (`+`) and subtracting (`-`) integer numbers in order to get the next and previous characters according to the Unicode order:

```kotlin
val ch1 = 'b'
val ch2 = ch1 + 1 // 'c'
val ch3 = ch2 - 2 // 'a'

// mesela
val toplam = 'a' + 'c'.code
// boyle yapinca cikti su oldu:
// Ä
// a degeriyle int toplamisim gibi oldu.
```

You also cannot sum up two characters:

```kotlin
val charsSum = 'a' + 'b' // Error

// 2 string toplanabilir ama 2 karakter toplanamaz
```

It is possible to use the increment (`++`) and decrement (`--`) operators in their prefix and postfix forms. The assignment operator combined with `+` or `-` also works for characters, as well as `+=` and `-=`.

```kotlin
var ch = 'A'

ch += 10
println(ch)   // 'K'
println(++ch) // 'L'
println(++ch) // 'M'
println(--ch) // 'L'
```

You can compare characters using relational operations (`(==)`, `<`, `>`, `<=`, `>=`, and `!=`) according to their position in the Unicode table.

```kotlin
println('a' < 'c')  // true
println('x' >= 'z') // false

println('D' == 'D') // true
println('Q' != 'q') // true because capital and lowercase letters are not the same

println('A' < 'a')  // true because capital Latin letters are placed before lowercase ones
```

---
## 4.5
### Escape sequences

- `'\n'` is the newline character;
- `'\t'` is the tab character;
- `'\r'` is the carriage return character; (satir basi)
- `'\\'` is the backslash character itself;
- `'\''` is the single quote mark;
- `'\"'` is the double quote mark.

---
## 4.6
### Processing characters

There's a variety of useful functions to work with characters. You can use these functions instead of dealing with character codes.

- `isDigit()` returns `true` if the given character represents a digit (`'1'`, `'2'`, etc); otherwise, `false`;
- `isLetter()` returns `true` if the given character represents a letter (`'a'`, `'B'`, `'m'`, etc); otherwise, `false`;
- `isLetterOrDigit()` returns `true` if the given character represents a letter or a digit; otherwise, `false`;
- `isWhitespace()` returns `true` if the given character represents a whitespace (`' '`, or `'\t'`, or `'\n'`); otherwise, `false`;
- `isUpperCase()` returns `true` if the given character is an uppercase character; otherwise, `false`;
- `isLowerCase()` returns `true` if the given character is a lowercase character; otherwise, `false`;
- `toUpperCase()` returns the uppercase form of the given character (used before Kotlin 1.5; this function is now deprecated and should not be used);
- `uppercaseChar()` returns the uppercase form of the given character (available since Kotlin 1.5);
- `uppercase()` returns a `String` with the uppercase form of the given character (available since Kotlin 1.5);
- `toLowerCase()` returns the lowercase form of the given character (used before Kotlin 1.5; this function is now deprecated and should not be used);
- `lowercaseChar()` returns the lowercase form of the given character (available since Kotlin 1.5);
- `lowercase()` returns a `String` with the lowercase form of the given character (available since Kotlin 1.5).

Let's take a look at some examples of the functions listed above:

```kotlin
val one = '1'

val isDigit = one.isDigit()   // true
val isLetter = one.isLetter() // false

val capital = 'A'
val small = 'e'

val isLetterOrDigit = capital.isLetterOrDigit() // true

val isUpperCase = capital.isUpperCase() // true
val isLowerCase = capital.isLowerCase() // false

val capitalEString = small.uppercase() // "E"
val capitalE = small.uppercaseChar()   // 'E'
```

---
## 4.7
### Input and output streams

```kotlin
fun main() {  
    val val1 = readln().first().isDigit()  
    val val2 = readln().first().isDigit()  
    val val3 = readln().first().isDigit()  
    val val4 = readln().first().isDigit()  
  
    println(val1)  
    println(val2)  
    println(val3)  
    println(val4)  
}

fun main() {  
    repeat(4) { println(readln().first().isDigit()) }  
}

// ikisi ayni sey sayilir.
```

There is no "first read input, then output". Input and output streams are independent. Your programs reads the input from the platform, and the platform simply reads its output.

---
## 4.8
### Working with strings

Kotlin provides several convenient ways to access the first and the last character of a string:

```kotlin
println(greeting.first())   // 'H'
println(greeting.last())    // 'o'
println(greeting.lastIndex) // 4
```

You can use the length and check if it's greater than 0. But a much more elegant way is using the function `isEmpty()`:

```kotlin
val emptyString = ""
println(emptyString.length == 0) //true
println(emptyString.isEmpty()) //true
```

---
## 4.9
### Immutability

Strings are **immutable**, meaning that once created, the string stays the same. You cannot modify an element of a string. So, the example below would not work:

```kotlin
val valString = "string"
valString[3] = 'o' // an error here!
var varString = "string"
varString[3] = 'o' // an error here too!
```

If you need to change the string, you can reassign it:

```kotlin
var varString = "string"
varString = "strong" // legal
val valString = "string"
valString = "strong" // error, you cannot reassign val
```

Actually, we do not modify the stored value in the `varString` variable. Instead, we assign a new value to it. So, it is absolutely legal. This is one of the ways to work with strings. If you need to modify a string, just create a new one.

---
## 4.10
### Comparing strings

To compare two strings, use == (equals) and `!=` (not equals) operators. Both operators take two strings as their operands and return a value of the `Boolean` type (`true` or `false`).

```kotlin
val first = "first"
val second = "second"
var str = "first"

println(first == str)    // true
println(first == second) // false
println(first != second) // true
```

---
## 4.11
### Getting a part of a string

The `substring` function accepts `startIndex` (inclusive) and `lastIndex` (exclusive) as arguments and returns a string that starts at the `startIndex` and ends right before the `lastIndex`.

```kotlin
val greeting = "Hello"
println(greeting.substring(0, 3)) // "Hel"
println(greeting.substring(1, 3)) // "el"

println(greeting.substring(2))    // "llo"
// The parameter `lastIndex` can be omitted; then you will get a substring from the `startIndex` element to the end of the original string.

println(greeting.substring(4, 5)) // "o"

//Keep it in mind that `lastIndex` must not be greater than the string length.
println(greeting.substring(4, 6)) // Error: Range [4, 6) out of bounds for length 5
```

`substring(startIndex, lastIndex)` için geçerli aralık `0 <= startIndex <= lastIndex <= length`'dir.
- `startIndex`: `0`'ın altına inemez.
- `lastIndex`: string uzunluğunu **geçemez** (ama uzunluğa **eşit olabilir**, çünkü exclusive).
- `startIndex == lastIndex` sorun değil, sadece boş string verir.

---
## 4.12
### `substringAfter` & `substringBefore`

The `substring` method is not the only way to get part of a string. You can also use the `substringAfter` and `substringBefore` functions:

```kotlin
// greeting = "Hello"
println(greeting.substringAfter('l'))  // "lo"
println(greeting.substringAfter('e'))  // "llo"  // yani e haric e den sonrasi
println(greeting.substringBefore('o')) // "Hell"
println(greeting.substringBefore('z')) // "Hello"
```

Önemli detay: **"first occurrence"** yani karakterin **ilk göründüğü yeri** baz alıyor. `"Hello"`'da iki tane `l` var (index 2 ve 3), ama `substringAfter('l')` ilk `l`'yi (index 2) baz alıyor, ikinciyi değil. Bu yüzden `"lo"` çıkıyor (ikinci `l` dahil, ondan sonrası).

Eğer aradığın karakter string'te **hiç yoksa**, hata vermiyor — bütün string'i olduğu gibi geri veriyor:

```kotlin
println(greeting.substringBefore('z')) // "Hello"
// 'z' yok, o yüzden değişiklik yapmadan tüm string'i döndürüyor
```

İstersen bulunamama durumunda özel bir mesaj da belirleyebilirsin (2. parametre olarak):

```kotlin
println(greeting.substringBefore('z', "bulunamadı"))
// 'z' yok -> "bulunamadı" döner, "Hello" değil
```

---
## 4.13
### `substringBeforeLast` / `substringAfterLast`

Bunlar aynı mantık ama **son (last) occurrence**'ı baz alıyor, ilkini değil:

```kotlin
// greeting = "Hello"
println(greeting.substringAfterLast('l'))  // "o"
// SON 'l' index 3'te. ondan sonrası -> "o"

println(greeting.substringBeforeLast('l')) // "Hel"
// SON 'l' index 3'te. ondan öncesi -> "Hel" (H, e, l -> ilk l dahil)
```

Farkı görmek için `substringAfter('l')` (`"lo"`) ile `substringAfterLast('l')` (`"o"`) sonuçlarını karşılaştır — ilki ilk `l`'yi baz alıp ondan sonrasını (ikinci `l` dahil) veriyor, ikincisi son `l`'yi baz alıp gerçekten ondan sonrasını veriyor.

#### Ne zaman hangisini kullanmalısın?

Gerçek hayattan bir örnek, dosya uzantısı almak istediğini düşün:

```kotlin
val fileName = "notes.backup.kt"

// substringAfter ilk noktayı baz alır -> istemediğin sonuç
println(fileName.substringAfter('.'))     // "backup.kt"

// substringAfterLast SON noktayı baz alır -> gerçek uzantı bu
println(fileName.substringAfterLast('.')) // "kt"
```

Burada net bir kural çıkıyor: **elinde tek bir ayraç (delimiter) olacağını biliyorsan** `substringBefore`/`After` yeterli. Ama **birden fazla aynı ayraç olabileceği** ve seni en **son (veya en gerçek)** olanı ilgilendiriyorsa, `...Last` versiyonlarını kullanmalısın.

`substring(start, end)` ise bunlardan farklı — bir karaktere göre değil, **doğrudan pozisyona (index)** göre kesme yapıyorsun. Karakterin kendisini bilmiyorsan ama "3. ile 7. index arası" gibi net bir aralık istiyorsan bu senin aracın.

---
## 4.14
### Replacing parts of a string

You've probably been in a situation when you need to replace just one word in a string. The `replace` function replaces all occurrences of the first argument in the string with the second argument.

```kotlin
val example = "Good morning..."
println(example.replace("morning", "bye")) // "Good bye..."
println(example.replace('.', '!'))         // "Good morning!!!"
```

As you know, strings are immutable, so the `replace` function returns a new string without changing the original string. So, if you run this code:

```kotlin
val example = "Good morning"	
example.replace("morning", "bye")
println(example)
```

It still prints `Good morning`.

If you need to replace only the **first occurrence** of an argument, use `replaceFirst`.

```kotlin
val example = "one one two three"
println(example.replaceFirst("one", "two")) // "two one two three"
```

---
## 4.15
### Floating-point arithmetic operations

For fractional operands, the operator `/` performs division with a remainder, not integer division.

```kotlin
val pi = 3.1415 // double

val squaredPi = pi * pi // 9.86902225

println(squaredPi / 2) // prints 4.934511125
```

hani integer division vardi ya, iki sayiyi bolunce kalani atiyordu. burada diyor ki iste onu yapmaz gercekten bolme yapar demek istiyor. kayan noktali sayilar "gercek" sayi olarak goruluyor.

### Errors during computation

Be careful — operations with floating-point numbers can produce an inaccurate result:

```java
println(3.3 / 3) // prints 1.0999999999999999
```

Errors can accumulate during computation:

```java
val num = 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1 + 0.1
println(num) // it prints 0.9999999999999999
```

Remember that the output might contain many zeroes, as shown below, because operations with floating-point numbers can produce inexact results. This happens because computers work with binary numbers, and many floating-point numbers cannot be represented finitely in base 2. Working with these inexact numbers leads to imprecise results.

---
## 4.16
### Decimal seperator

Ondalıklı bir sayıyı yazarken kullandığın ayraç karakteri — Türkiye'de virgül (`3,14`), ABD'de nokta (`3.14`) kullanılır. Buna **Locale (Yerel Ayar)** deniyor; işletim sistemi/JVM hangi ülke formatını kullandığını bilir ve buna göre sayı ayraçlarını yorumlar.

Kotlin'de `toDouble()` / `toFloat()` gibi fonksiyonlar, **sistemin locale'inden bağımsız olarak her zaman nokta (`.`) bekler.** Yani:

```kotlin
val n = "3.14".toDouble() // çalışır -> 3.14
val n2 = "3,14".toDouble() // ÇÖKER! NumberFormatException
```

kontrol su sekilde yapilmali:

```kotlin
import java.util.Locale
// String.format bir template + locale + değer alır 
val num = 3.14 
println(String.format(Locale.US, "%.2f", 3.14159)) // "3.14" <- nokta
println(String.format(Locale("tr"), "%.2f", 3.14159)) // "3,14" <- virgül!
```

---
