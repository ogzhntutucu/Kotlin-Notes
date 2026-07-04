# Kotlin Notes 1
#### ogzhntutucu
---

**Integer numbers** (0, 1, 2, ...) are represented by the following four types: `Long`, `Int`, `Short`, `Byte` (from the largest to the smallest). These types have different sizes and may represent different ranges of values. The integer type range can be calculated as −(2n−1) to (2n−1)−1, where **n** is the number of bits. The range includes 0, that's why we subtract 1 from the upper bound.

- `Byte`: 8 bits (1 byte), range varies from -128 to 127;
- `Short`: 16 bits (2 bytes), range varies from -32768 to 32767;
- `Int`: 32 bits (4 bytes), range varies from −(2^31) to (2^31)−1;
- `Long`: 64 bits (8 bytes), range varies from −(2^63) to (2^63)−1.

```kotlin
val zero = 0 // Int
val one = 1  // Int
val oneMillion = 1_000_000  // Int

val twoMillion = 2_000_000L           // Long because it is tagged with L
val bigNumber = 1_000_000_000_000_000 // Long, Kotlin automatically chooses it (Int is too small)
val ten: Long = 10                    // Long because the type is specified

val shortNumber: Short = 15 // Short because the type is specified
val byteNumber: Byte = 15   // Byte because the type is specified
```

Floating-point types represent numbers with fractional parts. Kotlin has two such types: `Double` (64 bits) and `Float` (32 bits). These types can store only a limited number of decimal digits (~6-7 for `Float` and ~14-16 for `Double`). The `Double` type is more common in practice:

```kotlin
val pi = 3.1415              // Double
val e = 2.71828f             // Float because it is tagged with f

val fraction: Float = 1.51f  // Float because the type is specified
// Float diye tipini belirtmis bile olsak degerin sagina f ya da F birakmak zorundayiz. cunku birakmazsak o degerin tipi double sayilacaktir ve bu satir icin type mismatch olur.
```

- Integer types: Byte < Short < Int < Long
- Floating-point types: Float < Double

For type inference -> Kotlin neredeyse her zaman bir sayının tamsayı (**integer**) olduğu sonucunu çıkaracaktır (aksini belirtmediğiniz veya ondalık sayı kullanmadığınız sürece)

---

## Characters

Kotlin has a `Char` type to represent various letter characters (uppercase and lowercase), digits, and other symbols. Each character is a letter character in **single quotes**. The size is similar to the `Short` type (2 bytes = 16 bits):

```kotlin
val lowerCaseLetter = 'a'
val upperCaseLetter = 'Q'
val number = '1'
val space = ' '
val dollar = '$'
```

## Booleans

Kotlin provides a type called `Boolean`. It can store only two values: `true` and `false`. It represents only one bit of information, but its size is not precisely defined.

```kotlin
val enabled = true
val bugFound = false
```

---

```kotlin
@Suppress("VariableNaming", "MagicNumber")  
val `good name` = 14  
println(`good name`)  
  
@Suppress("MagicNumber")  
val fraction: Float = 1.51f  
println(fraction)
```

@Supress ile uyarilar bastirilabiliyor.

`@Suppress` bir **Anotasyon (Annotation)** türüdür ve derleyiciye (compiler) veya projene entegre ettiğin kod kalite araçlarına (örneğin Ktlint, Detekt veya IDE'nin kendi analizörü) şunu söyler: _"Evet, burada kurallara aykırı bir şey yazdığımın farkındayım ama bilerek yaptım. Lütfen altını sarı/kırmızı çizmeyi bırak ve bu kural ihlalini görmezden gel."_

---

Well, you may guess, how to print `$` in Kotlin. Here some ways to do it:

```kotlin
val a = 20

// There is a dot after $, not variable, so no problem here
println("The price is $a$.") // Output:  The price is 20$. 

// The second $ insert the variable and the first a common symbol here
println("The price is $$a.") // Output: The price is $20. 

// \$ means that you take $ and don't mind about special interpretation
println("The price is \$a.") // Output: The price is $a.

//Another example with \$
println("The price is \$$a.") // Output: The price is $20. 
```

---

## Functions arguments

When we want to use a function, we can **invoke (or call)** it using its name followed by parentheses. If a function takes one or more arguments (input data), they should be passed in the parentheses.

```kotlin
function1() // invokes function1 without an argument
function2(arg1) // invokes function2 while passing an argument
function3(arg1, arg2) // invokes function3 while passing two arguments
// ... and so on
```

All functions **return** a result, even the `println` function.

```kotlin
val result = println("text")
println(result) // kotlin.Unit
```

The result is a special value called `Unit`, which practically means `no result`. When your function returns nothing, it means it returns `Unit`, that's all you need to understand for now. If you're coming from another language like C or Java, you can think of it as `Void`.

---

## What is Java Scanner?

> If `Scanner` isn't needed, always use `readln()`.

Scanner is another way to obtain data from standard input. You can access it directly from Kotlin because it is interoperable with other Java libraries. `Scanner` allows a program to read values of **different types** (strings, numbers, etc.) from standard input.

To use it, you need to add the following import statement to the top of your file with the source code:

```kotlin
import java.util.Scanner
```

or

```kotlin
import java.util.*
```

You can use either method to add `Scanner` to your program. We will discuss importing in more detail later.

Let's create a variable initialized by `Scanner`:

```kotlin
val scanner = Scanner(System.`in`)
```

To read different types of data, Scanner has methods like `next()`, `nextInt()`, and `nextLine()`.
Now we can read data from standard input:

```kotlin
val line = scanner.nextLine() // read a whole line, e.g., "Hello, Kotlin"
val num = scanner.nextInt()   // read a number, e.g., 123
val string = scanner.next()   // read a string, e.g., "Hello"
```

Ok, now we've read the data and don't need `Scanner` anymore. When we used `readln()`, we just proceeded with our code and moved further. But `Scanner` can consume PC resources all the time. To stop it, we need to use the method `close()`, which... cancels `Scanner`:)

```kotlin
scanner.close()
```

One important thing: we can input data directly in `Scanner`. Let's see:

```kotlin
val scanner = Scanner("123_456")
```

But how can we read next word if we don't have whitespaces or new lines? For these cases, `Scanner` has a convenient tool — `useDelimiter()`. With its help, we can customize our delimiters for methods like `next()`, `nextInt()`, etc. 

<u>the default value of delimiter is a whitespace</u> : yani usedelimeter kullanma ve ifadeler arasinda bosluk birak yeter. bu ornek icin, _ koymak yerine bosluk biraksan sorunsuz gecerdi zaten. ama istersen useDelimeter ile herhangi bir ayirici yani delimeter belirleyebiliyorsun. bu ayirici virgul, nokta falan da olabilir yani.

To read these words and integers separately, we have to set a new delimiter symbol – `"_"`:

```kotlin
scanner.useDelimiter("_")
```

Now, we can read this data:

```kotlin
println(scanner.nextInt()) // 123
println(scanner.nextInt()) // 456
```

If you don't know how many words the input has, use the method `hasNext()`.

```kotlin
val scanner = Scanner("Hello, Kotlin!")

if (scanner.hasNext()) {
    val word1 = scanner.next() // Hello,
}
if (scanner.hasNext()) {
    val word2 = scanner.next() // Kotlin!
}
if (scanner.hasNext()) {
    val word3 = scanner.next() // This block will not run
}
```

---

## Appending values to strings

The `+` also works for [appending](https://hyperskill.org/learn/step/12553 "In Kotlin, the term append refers to the process of adding elements or values to a string. | The `append` function can be used to add values of different types to a string, which are then automatically converted to strings and concatenated to the target. It's important to note that an expression must start with a string for appending to work. Appending can be useful in various scenarios, such as string concatenation, logging and formatting, generating HTML or XML markup, concurrent data processing, multi-threaded text generation, and thread-safe string manipulation.") values of different types to a `String`. The value is automatically converted to a `String` and then concatenated to the target `String`:

```kotlin
val stringPlusBoolean = "abc" + 10 + true
println(stringPlusBoolean) // abc10true

val code = "123" + 456 + "789"
println(code) // 123456789

val charPlusString = 'a' + "bc" 
println(charPlusString) // abc
```

An expression must start with a `String`.
Take a look at the example below. It wouldn't work because the first operand is a number:

```kotlin
val errorString = 10 + "abc" // an error here!
```

```kotlin
// char ile de baslarsa olur. sikinti olmaz. char ya da string disindakilerde sikinti olur.
println('1' + "2" + 3)
```

## Repeating the string

If you need to repeat one string two or more times, then hold your loops: Kotlin provides the `repeat` function:

```kotlin
print("Hello".repeat(4)) // HelloHelloHelloHello
```

python'da vardi ama burada yok:

```kotlin
// 9999 yazmak icin sunu yapamazsin:
println("9" * 4)

// bunlar yapilabilir:
println("99" + "" + "99")
println("" + 9999)
println("9".repeat(4))
```

---

## Raw string

Sometimes you need some special symbols like tabs or quote marks in your string. You can do it with the help of **escape sequences**. For example:

```kotlin
// prints 'H' is the first letter of "Hello world!" string.
println("\'H\' is the first letter of \"Hello world!\" string.")
```

This looks a little heavy. If you need to write a fairly large text with newlines and special characters, it can be difficult to read.

```kotlin
val largeString = """
    This is the house that Jack built.
      
    This is the malt that lay in the house that Jack built.
       
    This is the rat that ate the malt
    That lay in the house that Jack built.
       
    This is the cat
    That killed the rat that ate the malt
    That lay in the house that Jack built.
""".trimIndent() // removes the first and the last lines and trims indents
print(largeString)
```

As you can see, we also used the function `.trimIndent()`. It cuts off all the lines of the common minimal indent and removes the first and last lines if they are empty. For example:

```kotlin
val unevenString = """
        123
         456
          789""".trimIndent()
print(unevenString)
println()

val rawString = """123
         456
          789
""".trimIndent()
print(rawString)

// This code prints:
123
 456
  789

123
         456
          789
          
// Ortak minimum girintinin tüm satırlarını keser.
```

