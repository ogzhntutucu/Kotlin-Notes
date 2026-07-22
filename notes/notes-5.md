# Kotlin Notes - 5
#### [@ogzhntutucu](https://github.com/ogzhntutucu/Kotlin-Notes)

---
## 5.1
### Expression-style "if"

Unlike the case of some other programming languages, Kotlin's `if` is an expression, not a [statement](https://hyperskill.org/learn/step/4625 "In Kotlin, a statement is a single command to be executed, such as printing a text or assigning a value to a variable. | Unlike expressions, which produce a single value as a result of computation, statements are standalone commands that have a side effect, like changing the state of the program. The result of a statement is not used in the program, whereas the result of an expression often is. For example, in the statement `val x = 2 * 2`, the expression `2 * 2` is evaluated, and its result is assigned to the variable `x`. However, the result of the statement itself is not used in the program. In contrast, if you write `println(2 * 2)`, the expression `2 * 2` is evaluated, and its result is passed directly to the `println` function, which then prints the result. It's important to note that a statement can be an expression, but not all expressions are statements. For instance, `2 * 2` is an expression, but it's not a statement because it doesn't change the state of the program."). This means it can return a value as a result of computations. The result must be the last expression in the body. For example:

```kotlin
val max = if (a > b) {
    println("Choose a")
    a
} else {
    println("Choose b")
    b
}
```

In the above example, the variable `max` is assigned the value of the last expression in the body. It's important to note that if you use an expression-style `if`, it must have an `else` branch.

You can skip braces if all the bodies contain only a single statement:

```kotlin
val max = if (a > b) a else b
```

### Idiom

Prefer using the expression-style `if` when you need to get results, as it can help avoid potential issues caused by mutable variables or forgotten changes. For example:

```kotlin
val max = if (a > b) a else b // one line
```

---
## 5.2
### Using "when" as an expression

Similar to the `if` expression, `when` can also be used as an expression that returns a value. The value returned by the `when` expression is the result of the last expression in the matching branch.

```kotlin
val number = 3
val message = when (number) {
    1 -> "One"
    2 -> "Two"
    3 -> "Three"
    4 -> "Four"
    else -> "Number is greater than four"
}

println(message) // Output: Three
```

### Using "when" with ranges and conditions

`when` expressions can also be used with ranges and more complex conditions. Here's an example:

```kotlin
val number = 15

when {
    number < 0 -> println("Negative number")
    number in 1..10 -> println("Number between 1 and 10")
    number % 2 == 0 -> println("Even number")
    else -> println("Odd number greater than 10")
}
```

In this example, the `when` expression checks the value of `number` against different conditions, including ranges and custom conditions.

### Ranges in when

```kotlin
when (n) {
    0 -> println("n is zero")
    in 1..10, in 15..20 -> println("n is between 1 and 10 OR n is between 15 and 20")
    in 25..30 -> println("n is between 25 and 30")
    else -> println("n is outside a range")
}
```

---
## 5.3
### Triangle (Kata)

Read three natural numbers a, b, c. Define if a triangle with such side lengths exists.

If the triangle exists, output the **YES** string, otherwise output **NO**.

```kotlin
fun main() {
    val (a, b, c) = List(3) { readln().toInt() }.sorted()
    println(if (a + b > c) "YES" else "NO")
}
```

sorted() ile kucukten buyuge siralayip oyle yerlestiriyor. sonra en kucuk iki kenarin buyuk kenardan buyuk oldugunu kontrol ediyor.

---
## 5.4
### Ranges

```kotlin
val within = c in a..b
```

Here, `a..b` is a range of numbers from `a` to `b` (including both border values), `in` is a **special keyword** that is used to check whether a value is within a range. Later you will see that this keyword can be used with other types as well. **This is a closed-ended range**.

Also, we have an **open-ended** range: `a..<b` is a range of numbers from `a` until `b` (excluding the border value, `b`).

If you need to exclude the right border, you may subtract 1 from it or use `..<` to get the open-ended range (the recommended way).

```kotlin
val withinExclRight = c in a..b - 1 
// a <= c && c < b

val withinExclRight = c in a..<b 
// a <= c && c < b (the recommended way)
```

If you need to check that a value is not within a range, just add `!` (not) before `in`.

```kotlin
val notWithin = 100 !in 10..99 // true
```

You may combine ranges using standard logical operators. The code below checks if `c` is within one of three ranges.

```kotlin
val within = c in 5..10 || c in 20..30 || c in 40..50 
// true if c is within at least one range
```

You can assign a range to a variable and use it later.

```kotlin
val range = 100..200
println(300 in range) // false
```

In addition to integer ranges, you can also use ranges of characters and even strings (according to dictionary order).

```kotlin
println('b' in 'a'..'c') // true
println('k' in 'a'..'e') // false

println("hello" in "he".."hi") // true
println("abc" in "aab".."aac") // false
```

---
## 5.5
### Trailing comma (sondaki fazladan virgül)

```kotlin
when (op) {  
    "+", "plus" -> println(a + b)  
    "-", "minus", -> println(a - b) 
//     ^        ^
// bu ayırıcı   bu trailing comma
    "*", "times" -> { 
	    val product = a * b 
        println(product) 
    }  
    else -> println("Unknown operator")  
}
```

- **Birinci virgül** (`"-", "minus"`): `when` branch'inde birden fazla koşulu `veya (OR)` mantığıyla birleştiriyor. "Eğer `op` ya `-` ya da `minus` ise" demek.
- **İkinci virgül** (`"minus",` sonrası): Listedeki **son elemandan sonra** bırakılan, hiçbir işlevi olmayan, tamamen isteğe bağlı bir virgül. Kaldırsan da kod aynı şekilde çalışır.

Kotlin 1.4'ten itibaren dil, virgülle ayrılan listelerin **son elemanından sonra** da bir virgül bırakılmasına izin veriyor — bu sadece `when` koşulları için değil, birçok yerde geçerli: fonksiyon parametreleri, fonksiyon çağrısındaki argümanlar, lambda parametreleri, `enum` sabitleri, `destructuring` (`val (a, b, c) = ...`) gibi.

```kotlin
fun sum(
    a: Int,
    b: Int,
    c: Int, // <- trailing comma, izin verilir
) {
    println(a + b + c)
}
```

Bunun amacı **saflık (pürizm)** değil, pratik bir kolaylık. Şöyle düşün: elinde alt alta yazılmış bir parametre/argüman listesi var ve sona yeni bir eleman eklemek istiyorsun:

```kotlin
"-", "minus" -> println(a - b)   // trailing comma yoksa
```

Yeni bir eş anlamlı kelime eklemek istediğinde (`"subtract"` diyelim), önce mevcut son elemanın sonuna virgül eklemen, sonra yeni elemanı yazman gerekiyor — iki ayrı satırı değiştirmiş oluyorsun. Ama trailing comma zaten oradaysa:

```kotlin
"-", "minus", -> println(a - b)   // trailing comma varsa
```

Sadece yeni satırı ekleyip listenin arasına sıkıştırıyorsun, **var olan satırı değiştirmene gerek kalmıyor**. Bu küçük bir detay gibi görünse de, Git gibi versiyon kontrol sistemlerinde `diff` (değişiklik farkı) çıktısını temizler: sadece eklediğin satır "değişmiş" görünür, bir üstteki satır gereksiz yere "değişmiş" olarak işaretlenmez. Ayrıca elemanları IDE'de kopyala-yapıştırla yeniden sıralarken de kolaylık sağlar.

Zorunlu degil, tamamen opsiyonel — kullanmasan da kod doğru ve derlenir. Kotlin resmi style guide'ında da bu konuda net bir zorunluluk yok, bir tercih meselesi. Kendi kodunda kullanıp kullanmamak sana kalmış; ikisi de idiomatic kabul edilir.

---
## 5.6
### When as an expression

`When` can also return a result. In this case, every branch should return something, and an `else` branch is **required**. In the code example below, every branch returns a result of the corresponding operation.

```kotlin
val result = when (op) {
    "+" -> a + b
    "-" -> a - b
    "*" -> a * b
    else -> "Unknown operator"
}
println(result)
```

You don't need to declare an additional variable and can immediately pass the result to a function. Take a look at the example below:

```kotlin
println(when(op) {
    "+" -> a + b
    // ...
    else -> "Unknown operator"
})
```

If a branch contains a block with multiple statements enclosed in `{...}`, the last line must be a single value or a complex expression that will be performed and returned as the result of the `when` expression. Take a look at the modified branch from our example above:

```kotlin
"+" -> {
    val sum = a + b
    sum
}
```

Choose shorter forms without `{...}` in branches to make your code easier to understand.

---
## 5.7
### when'de siralamanin onemi

If you work with other programming languages like Java or C#, you may note that `when` is similar to a `switch` statement. `When` provides more complex checking, not only directly matching a value.

The program below reads three integer numbers `a`, `b`, and `c`, and then tries to determine how to calculate `c` using `a` and `b`. If there are many ways to calculate `c`, it will print only the first one:

```kotlin
fun main(){
    val (var1, var2, var3) = readln().split(" ")

    val a = var1.toInt()
    val b = var2.toInt()
    val c = var3.toInt()

    println(when (c) {
        a + b -> "$c equals $a plus $b"
        a - b -> "$c equals $a minus $b"
        a * b -> "$c equals $a times $b"
        else -> "We do not know how to calculate $c"
    })
}
```

If input values are `5 3 2` the program outputs `2 equals 5 minus 3`. 
If input values are `0 0 0`, it outputs `0 equals 0 plus 0`.

yani when icindeki dallarin siralamasinin onemi var diyor. tum degerlerin 0 oldugu durumda tum dallar eslesebilecekse bile eger ilk dal eslesiyorsa o calisir ve sonuc belirlenir, digerlerine bakilmaz. o yuzden siralamayi onemse.

---
## 5.8
### When without arguments

You can use a `when` expression without arguments. In this case, every branch condition is a simple boolean expression, and a branch is executed when its condition is `true`. If several conditions are `true`, only the first one will be executed.

The program below shows how it works:

```kotlin
fun main(){
    val n = readln().toInt()
    
    when {
        n == 0 -> println("n is zero")
        n in 100..200 -> println("n is between 100 and 200")
        n > 300 -> println("n is greater than 300")
        n < 0 -> println("n is negative")
    }
}
```

Every branch condition is a boolean expression that may include any operations producing booleans.

---
## 5.9
### when'deki else'in opsiyonelligi

- **Statement (komut) olarak kullanıyorsan** (yani `when` bloğunun ürettiği bir değeri hiçbir yere atamıyorsan, sadece içindeki `println` gibi yan etkiler için çalıştırıyorsan) → `else` **opsiyonel**, subject (argument) olsa da olmasa da.
- **Expression (ifade) olarak kullanıyorsan** (yani `when`'in sonucunu bir değişkene atıyorsan, `return` ediyorsan, ya da `println(when(...) {...})` gibi başka bir fonksiyona parametre olarak veriyorsan) → derleyici **tüm olası durumların karşılanmasını (exhaustive olmasını)** ister, çoğu zaman bunun tek yolu `else` eklemektir.

**Subject var, statement olarak kullanılmış — `else` gerekmiyor:**

```kotlin
val op = "+"

when (op) { // subject var (op)
    "+" -> println("toplama")
    "-" -> println("çıkarma")
    // else yok ama sorun değil, çünkü sonucu kullanmıyoruz (statement)
}
```

**Subject yok, ama expression olarak kullanılmış — `else` gerekiyor:**

```kotlin
val sonuc = when { // subject yok
    5 > 3 -> "büyük"
    5 < 3 -> "küçük"
    // else yoksa derleyici hata verir!
    // çünkü sonuc'a bir değer atanacak, kapsanmayan durum olamaz
}
```

---
## 5.10
### Subject ile argument farki

- **Parametre/argüman** ikilisi → fonksiyonlarla ilgili bir dünya.
- **Subject** → `when` (ve kavramsal olarak `if`'in koşulu gibi) kontrol yapılarıyla ilgili bir dünya.

```kotlin
fun sum(a: Int, b: Int) = a + b  // a, b -> parametre (fonksiyon tanımında)
sum(3, 5)                        // 3, 5 -> argüman (fonksiyon çağrısında)

when (x) { ... }                 // x -> subject (kontrol yapısında)
```

`Argüman (Argument)` terimi, normalde bir **fonksiyona** çağrı sırasında verdiğin değerler için kullanılır.

`when` bir fonksiyon değil, bir **kontrol yapısı (control structure)** — `if`, `for`, `while` gibi. Dolayısıyla ona "argüman veriyorsun" demek, `when`'i bir fonksiyon çağrısıymış gibi kavramsallaştırıyor ki bu yanlış bir zihinsel model. Bu küçük farkın önemi şurada ortaya çıkıyor: bir fonksiyona argüman verdiğinde o değer fonksiyonun _içine girer_ ve orada işlenir; ama `when`'e verdiğin değer öyle "işlenmiyor" — sadece her dalın koşuluyla **karşılaştırılıyor**.

`Subject (Konu/Özne)`, İngilizce Kotlin dokümantasyonunun ve dilin resmi terminolojisinin kullandığı isim. Kotlin'in kendi [resmi dokümanına](https://kotlinlang.org/docs/control-flow.html#when-expression) baktığında da `when (x) { ... }` yapısındaki `x` için `"subject"` kelimesi geçiyor.

Kelimenin kendisi de aslında işi anlatıyor: `when (x)`, "x **konusunda/hakkında** karar veriyorum, x'i çeşitli koşullarla eşleştiriyorum" demek. Tıpkı bir cümledeki `özne (subject)` gibi — "bu yapı **neyle ilgili**" sorusunun cevabı.

Konuşurken/yazarken `subject` demeye alışman, ileride İngilizce resmi kaynaklarla (kotlinlang.org, Kotlin dilinin GitHub tartışmaları, konferans videoları vb.) karşılaştığında terimlerin örtüşmesini sağlar. Ama birilerinin buna `argument` demesi de "hatalı" değil, sadece daha gevşek bir kullanım — bunu görünce şaşırma, ikisinin de aynı şeyi kastettiğini bil.

---
## 5.11
### Repeat loop

To use the simplest loop, write `repeat(n)` and a sequence of statements in curly braces `{...}`. `n` is an integer that determines how many times these statements are going to be repeated.

For example, the program below prints `Hello` three times:

```kotlin
fun main() {
    repeat(3) {
        println("Hello")
    }
}
```

If `n` is zero or a negative number, Kotlin will ignore the loop. If `n` is one, the statements will be executed only once.

Kotlin has the opportunity to control the current iteration with the `it` name:

```kotlin
fun main() {
    repeat(3) {
        println(it)
    }
}
```

`repeat(n) { ... }`, bloğu `n` kez çalıştırır ve her çalıştırmada `it` parametresine **`0`'dan başlayarak** artan bir değer verir. Yani `repeat(5) { ... }` dersen, `it` sırasıyla şu değerleri alır:

```
it = 0, 1, 2, 3, 4   (toplam 5 kez, ama 0'dan başlıyor!)
```

`5` değil `4`'e kadar gidiyor, çünkü `0` da bir "tur" sayılıyor.

---
## 5.12
### For loop - Iterating through a range

It is possible to iterate through a range of characters:

```kotlin
for (ch in 'a'..'c') {
    println(ch)
}
// prints
// a
// b
// c
```

### Iterating through a String

Also, you may iterate over strings. The following code prints each symbol of a `String`:

```kotlin
val str = "Hello!"
for (ch in str) {
    println(ch)    
}
```

### Iterating in the backward order

You can also iterate a range in the backward order.

```kotlin
for (i in 4 downTo 1) {
    println(i)
}
```

This loop prints numbers from 4 to 1.
Note that it is required to use `in 4 downTo 1`, not `in 4..1`, to iterate the range in reverse order.

### Excluding the upper limit

If we need to exclude the upper limit from a range, we can subtract one from it or write `until` instead of `..`:

```kotlin
for (i in 1 until 4) {
    println(i)
}

// until yerine ..< da kullaniliyor su an. hatta oneriliyor.
```

This loop prints numbers from 1 to 3.

### Specifying a step

If we do not specify a step, it is assumed that the step is equal to one (e.g. `1, 2, 3, ...`). Although if we want to change the step, we need to specify it explicitly.

In the example below, we print only odd numbers from the range `1..7`.

```kotlin
for (i in 1..7 step 2) {
    println(i)
}
//prints
//1
//3
//5
//7
```

You can also use it for backward iteration.

```kotlin
for (i in 7 downTo 1 step 2) {
    println(i)
}
```

Quick reminder:

```kotlin
for (i in 1..6) { ... }        // closed range: 1, 2, 3, 4, 5, 6
for (i in 1 until 6) { ... }   // half-open range: 1, 2, 3, 4, 5
for (x in 1..6 step 2) { ... } // step 2: 1, 3, 5
for (x in 6 downTo 1) { ... }  // closed range, backward order: 6, 5, 4, 3, 2, 1 
```

istersek boyle bir sey yapabiliyormusuz:

```kotlin
for (i in 0L until 40L step 2) {
    // iki long deger arasinda int bir degerle iterasyon.
}
```

---
## 5.13
### Check the order (Kata)

Write a program that checks if N numbers are sorted according to the ascending order (from the smallest number to the largest one).
The first line contains the number N.  
The other lines contain N numbers.  
Output "YES" if N numbers are sorted in ascending order, otherwise, output "NO".

```kotlin
fun main() {  
    val numberOfNumbers = readln().toInt()  
    var number = readln().toInt()  
    var result = "YES"  
  
    for (i in 1..<numberOfNumbers) {  
        val next = readln().toInt()  
  
        if (number > next) {  
            result = "NO"  
            break  
        } else {  
            number = next // update number  
        }  
    }  
  
    println(result)  
}
```

- Bütün sayıları bir `List`'te tutup sonra karşılaştırmak yerine, sadece **tek bir değişkende** (`number`) "önceki sayı"yı tutuyor — bellek açısından çok tutumlu.
- Tek geçişte (`O(N)`) işi bitiriyor, gereksiz ikinci bir döngü ya da karşılaştırma yok.

---
## 5.14
### While loop

The `while` loop includes a block of code and a **condition**, which is a boolean expression. If the condition is `true`, the loop initiates the statements. They are repeated until the condition becomes `false`. 
This loop checks the condition **before** the execution, so it is also known as a **pre-test loop**. Take a look at the example:

```kotlin
while (condition) {
    // body: do something repetitive
}
```

This example contains a program that reads any number of words from the standard input and then prints them. It uses the `hasNext` function of `Scanner` to check whether the input has a value.

```kotlin
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    while (scanner.hasNext()) {
        val next = scanner.next()
        println(next)
    }
}
```

Input:

```no-highlight
Kotlin is a modern language
```

Output:

```no-highlight
Kotlin
is
a
modern
language
```

---
## 5.15
### Do...while loop

A `do...while` loop is executed first, after that the program tests a condition. If the condition is `true`, it repeats the loop until the condition becomes `false`. Since `do...while` loops check the condition after execution, the loop is also known as a **post-test loop**. In contrast with the `while` loop, which tests the condition before the execution, the `do..while` loop is an exit-condition loop. So, the body is always executed at least once.

This loop contains three parts: the `do` keyword, a body, and `while(condition)`:

```kotlin
do {
    // body: do something
} while (condition)
```

The program below reads an integer number from the standard input and displays the number. If a user enters `0`, the program prints it and then stops. The example below shows the work of the loop:

```kotlin
fun main() {
    do {
        val n = readln().toInt()
        println(n)
    } while (n > 0)
}
```

You can set a variable in the loop body and then use it in the condition.

---
## 5.16
### Structural jump expressions

break -> bu donguden ayril (yani icinde bulundugu donguden. eger baska bir dongu varsa o devam eder). "bu turu kes VE döngüyü tamamen bitir" (bir daha `j` döngüsüne hiç girme, dıştaki döngüye dön).
continue -> dongudeki bu iterasyonu gec, digeriyle devam et. "bu turu burada kes, ama döngüye devam et" (bir sonraki `j` değerine geç)
return -> bu da bir jump expression ancak donguler icin degil fonksiyonlar icin.

Look at the following example:

```kotlin
for (i in 1..4) {
    for (j in 1..4) {
        if (j == 2) continue // you want to ignore j = 2
        if (i <= j) break    // you will print the string if i is greater than j
        println("i = $i, j = $j")
    }
    println("Finished to examine i = $i")
}
```

The result is:

```no-highlight
Finished to examine i = 1
i = 2, j = 1
Finished to examine i = 2
i = 3, j = 1
Finished to examine i = 3
i = 4, j = 1
i = 4, j = 3
Finished to examine i = 4
```

As you can see, `break` and `continue` terminate only one loop. But what should we do if we need to terminate the outer loop? In this case, you should use labels. Let' take a look at them.

---
## 5.17
### Labels

What is a **label**? Technically, it's just an identifier with the `@` sign at the end, for example: `loop@`, `kotlin@`. You can use almost any word with it, apart from the reserved words in Kotlin.

Here is an example of using the `break` statement with a label:

```kotlin
loop@ for (i in 1..3) { 
    for (j in 1..3) {
        println("i = $i, j = $j")   
        if (j == 3) break@loop  
    }  
}  
```

Both the inner and outer `for` loops are terminated at the third iteration. The output is this:

```kotlin
i = 1, j = 1
i = 1, j = 2
i = 1, j = 3
```

So, labels help us break not only the inner loop but also the outer one. Once again, `break` with a label terminates the execution of the labeled loop.

Now, let's modify the previous example and use the `continue` keyword:

```kotlin
loop@ for (i in 1..3) {
    for (j in 1..3) {
        for (k in 1..3) {
            if (k == 2) continue@loop
            println("i = $i, j = $j, k = $k")
        }
    }
}
```

The output result is as follows:

```kotlin
i = 1, j = 1, k = 1
i = 2, j = 1, k = 1
i = 3, j = 1, k = 1
```

With the label, we changed the default behavior of the `continue` keyword. Instead of skipping the execution of the inner loop at the second iteration and continuing with the next iteration, we returned to the outer loop and continued its execution. Without labels, such a trick wouldn't be possible.

### When and structural jump expressions

break ve continue sadece looplar icindir. yani for, while ve do-while icindir. eger olur da bir loop icinde when kullanilirsa, onun icinde de break ve continue kullanilabilir. ancak bu when icin degil, loop icindir.

eskiden (kotlin 1.4 oncesi) when icinde break ve continue icin label kullanman gerekiyormus ancak artik direkt kullanabiliyorsun.

```kotlin
for (i in 1..10) {
    when (i) {
        3 -> continue
        6 -> break
        else -> println(i)
    }
}
```

---
## 5.18
### The Return Statement

Without a label, the `return` statement lets us return the result to the nearest enclosing function. It can be really helpful if we want to jump out of a loop for some reason and skip the remaining loop code or exit the current function:

```kotlin
fun foo() {
    val number = intArrayOf(1, 2, 3, 4, 5)
    for (it in number) {
        if (it == 3) return // non-local return directly to the caller of foo()
        print(it)
    }
    println("this point is unreachable")
}

fun main() { 
    foo() // calling foo()
    println()
    println("foo() is over")
    for (i in 1..10) {
        for (j in 1..10) {
            println("i = $i, j = $j")
            if (j == 3) return // local return to the caller of main()
        }
        println("this point is unreachable")
    }
    println("this point is unreachable")
}
```

In the example above, the internal loop will be interrupted at the third iteration and return the flow of program execution. This way, the outer loop will never reach further than the first iteration.

normalde return'u bir fonksiyonda deger dondurmek icin kullanirdik. buradaki kullanimi farkli.
icinde bulundugu fonksiyonu tam o noktada sonlandirmaya yariyor.

Yani bu `return`'ün işlevi değer üretmek değil, **"bu noktada fonksiyonun çalışmasını tamamen durdur, çağırana geri dön" demek.** Fonksiyonun geri kalanı (varsa daha aşağıdaki kodlar) hiç çalıştırılmıyor.

| Jump expressions    | Ne yapar                          | Nereyi etkiler                               |
| ------------------- | --------------------------------- | -------------------------------------------- |
| `continue`          | Bu turu atla, döngüye devam et    | Sadece en içteki döngü                       |
| `break`             | Döngüyü bitir                     | Sadece en içteki döngü, fonksiyon devam eder |
| `return` (değersiz) | Fonksiyonu tamamen bitir          | Döngüler dahil, **fonksiyonun tamamı**       |
| `return değer`      | Bir sonuç üretip fonksiyonu bitir | Aynı, ama üstüne bir değer de taşıyor        |

Remember, it's hard to read and fix code with a lot of structural jump expressions. Be very careful and do not overuse them!

---
## 5.19
### Label and continue

If you put a label on an outer loop and then use this label with continue within an inner loop you are basically breaking(exiting) the inner loop and continue in outer.

```kotlin
outer@ for (i in 1..3) {
    for (j in 1..3) {
        if (someCondition) continue@outer // <- burası
        println("i=$i, j=$j")
    }
}
```

Şimdi `continue@outer` yazınca ne oluyor:

1. **İçteki `j` döngüsü için:** o an bulunduğun turu tamamen terk ediyorsun — sanki `break` demişsin gibi, `j` döngüsüne bir daha hiç dönmüyorsun (kalan `j` değerlerine bakılmıyor).
2. **Dıştaki `i` döngüsü için:** ama tamamen çıkmıyorsun — `i` döngüsü **bir sonraki turuna** geçiyor (`continue` mantığıyla), yani `i++` olup dış döngü devam ediyor.

Yani cümledeki "you are basically breaking the inner loop and continue in outer" tam olarak bunu özetliyor: **iç döngüde `break` gibi davranıyor (tamamen terk ediyorsun), dış döngüde `continue` gibi davranıyor (dış döngü devam ediyor, sadece bir sonraki tura atlıyor).**

#### Neden böyle bir şeye ihtiyaç duyulur

Etiketsiz `continue`, sadece en içteki döngüyü etkileyebildiği için, "iç döngüden tamamen çık ama dış döngüye devam et" demenin normal yolu yok — bunu yapmak istiyorsan ya karmaşık `Boolean` bayrak değişkenleri (`flag`) kullanman ya da label kullanman gerekir. Label, bu ihtiyacı temiz bir şekilde çözüyor.

---
