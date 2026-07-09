# Kotlin Notes - 4
#### @ogzhntutucu

---
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

### Unicode

Unicode is standard in form of a table that defines the mapping between symbols and numbers.

Unicode can also be viewed as an extension of the ASCII table: in fact, the first 128 numbers in the Unicode represent ASCII symbols. All in all, Unicode can accommodate 1,112,064 code points, but as of May 2019, only 137,994 are occupied. The fact that most code points aren't designated yet is also one of Unicode's advantages. It means that for years and years to come, we can add new letters, scripts, punctuation marks, and pictograms to the standard without running out of space.

Unicode consists of two parts: **Universal character set, UCS,** and **Universal transformation format, UTF**.

**UCS** is essentially an index of all symbols supported by the Unicode and codes for those symbols. Let's take a look at a segment of this index. This is a part of Basic Latin, the symbols that have traveled from ASCII:

![](resources/_attachments/Pasted%20image%2020260708223639.png)

**UTF-8** is the most commonly used encoding in the world as around 94% of the Internet is encoded in UTF-8. UTF-8 uses from 1 to 4 bytes per code point and is capable of representing all code points of the Unicode. UTF-8 is also backward compatible with ASCII. UTF-16 is similar and the difference between the two is mostly technical. You could use UTF-16, of course, but we recommend sticking to UTF-8 like most of the Internet.

---

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

### Escape sequences

- `'\n'` is the newline character;
- `'\t'` is the tab character;
- `'\r'` is the carriage return character; (satir basi)
- `'\\'` is the backslash character itself;
- `'\''` is the single quote mark;
- `'\"'` is the double quote mark.

---

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

