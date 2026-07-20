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
### subject ile argument farki

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
