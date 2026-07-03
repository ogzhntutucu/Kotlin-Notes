## Temel Terminoloji (Basic Terminology)

- **Program (Program):** Bilgisayara ne yapması gerektiğini söyleyen komutlar dizisidir. İşlemlerin yukarıdan aşağıya, yazıldıkları sırayla tahmin edilebilir bir şekilde çalıştırılmasına **Sıralı Akış (Sequential Flow)** denir.
- **Komut (Statement):** İşletilecek tek bir eylemi temsil eden kod satırıdır. (Örneğin ekrana yazı yazdırmak veya bir değişken ataması yapmak).
- **Değer Üreten İfade (Expression):** Çalıştırıldığında ortaya tek bir _değer_ çıkaran kod parçasıdır. (Örneğin `2 * 2` bir ifadedir ve ürettiği değer `4`'tür).
- **Blok (Block):** Süslü parantezler `{ ... }` arasına alınmış komutlar grubudur. Bir bloğun içi, değişkenlerin bellekteki yaşam döngüsünü belirleyen bir **Kapsam (Scope)** yaratır.
- **Anahtar Kelime (Keyword):** Dili tasarlayanlar tarafından derleyici seviyesinde özel bir anlama sahip olduğu için rezerve edilmiş kelimelerdir (örneğin: `fun`, `val`, `if`). Bunları kendi değişken isimlerin olarak kullanamazsın.
- **Tanımlayıcı (Identifier):** Kod içindeki değişkenlere, fonksiyonlara veya sınıflara senin (programcının) verdiği isimlerdir.
- **Yorum (Comment):** Derleyici tarafından tamamen görmezden gelinen, ancak kodu okuyan diğer mühendisler (veya gelecekteki sen) için bırakılmış açıklama metinleridir. Kotlin'de `//` ile başlar.
- **Boşluk (Whitespace):** Boşluk karakteri, tab veya yeni satır karakterleridir. Kodu okunabilir kılmak için hayati önem taşır. Sisteme yüklediğin Kotlin Stil Rehberine (Kotlin Style Guide) göre, girintileme (indentation) için _tab_ yerine her zaman 4 adet _boşluk (space)_ kullanılmalıdır.
- **Kotlin'in Kalbi: Giriş Noktası (Entry Point):** İşletim sistemi veya Java Sanal Makinesi (JVM), programını başlattığında ilk olarak nereye bakacağını bilmek zorundadır. Kotlin'de bu **Giriş Noktası (Entry Point)**, `main` adındaki özel fonksiyondur.

#### Programs with multiple statements

As a rule, a program contains multiple statements. You should start a new line to write each statement. For example, the program below has two statements:

```kotlin
fun main() {
    println("Hello")
    println("World")
}
```

---

- Ctrl + J
	- Live Templates (Code snippets that you can insert into your code)
- Right Ctrl + (~ tusu)
	- hizli ide duzenlemesi yap
- Ctrl + Alt + L 
	- Code Reformat
- Ctrl + Shift + A
	- Help | Find Action
- Ctrl + F
	- Find
- Ctrl + R
	- Find and Replace
- Ctrl + Click
	- Quick Declaration or Usages
- Ctrl + Shift + I
	- Quick Definition
- Ctrl + /
	- Hizli yorum satirina al
- Ctrl + Shift + /
	- Blok comment
- Typing `/**` then pressing Enter
	- Javadoc stubs
- Ctrl + X
	- Satiri kes (kopyala ve sil)
- Ctrl + D
	- Satiri bir alt satira kopyala (Cogalt)
- Ctrl + Q (Zaten fareyle uzerine gelince acilan sey)
	- Quick documentation
	- iki kere kullanirsan dokumantasyon modu acilir ekranin bir kosesinde.
- Alt + Enter
	- Error solution

---

![[Pasted image 20260702162454.png]]

![[Pasted image 20260702162654.png]]

When you run a compiled program, JRE combines the program bytecode with necessary libraries and runs the JVM, which executes the resulting bytecode.

Derlenmiş bir programı çalıştırdığınızda, JRE programın bayt kodunu gerekli kütüphanelerle birleştirir ve ortaya çıkan bayt kodunu çalıştıran JVM'yi çalıştırır.

![JDK JRE components](https://ucarecdn.com/33aad287-2450-4386-8639-42c7a7eac874/)

Programları paketlemek için bir arşiv formatı.
An archive format for packing programs: JAR

---

## Declaring variables

Before you can start using a variable, you must declare it. To declare a variable, Kotlin provides two keywords:

- `val` (for _value_) declares a **read-only variable** (just a **named value**), which cannot be changed after it has been initialized so a value can be assigned once (this is actually not entirely true, we will discuss this issue in more detail later);
- `var` (for _variable_) declares a **mutable variable**, which can be changed (as many times as needed).
- `const` is used for variables (with val) **that are known at compile-time**.

yani const val ile birlikte kullanilir.

---

Ancak değişkenler (var anahtar sözcüğü ile bildirilenler) için bir kısıtlama vardır. Değerlerini yeniden atarken, yalnızca ilk değerle aynı türden yeni değerleri kullanabilirsiniz. Yani aşağıdaki kod parçası doğru değil:

There is one restriction for mutable variables (the ones declared with the keyword `var`), though. When reassigning their values, you can only use new values of the same type as the initial one. So, the piece of code below is not correct:

```kotlin
var number = 10
number = 11 // ok
number = "twelve" // an error here!
```

Please remember this restriction!

---

Lütfen mümkün olduğunca val kullanın! Kesinlikle mecbur kaldığınızda onu kolayca var ile değiştirebilirsiniz.

---

It is always possible to change the internal state of a `val` variable: while it is prohibited to reassign the variable, its content can be modified in some other ways.

So, the following code is correct:

```kotlin
// list creation
val myMutableList = mutableListOf(1, 2, 3, 4, 5)
// adding a new element
myMutableList.add(6)   // it works
// printing the list
println(myMutableList) // [1, 2, 3, 4, 5, 6]
```

As you can see, this code changed the internal state of the `myMutableList` by adding another integer number. When we invoked the `add()` function, we changed not the variable itself but the list it represents.

Gördüğünüz gibi, bu kod myMutableList'in dahili durumunu başka bir tam sayı ekleyerek değiştirdi. add() fonksiyonunu çağırdığımızda, değişkenin kendisini değil, temsil ettiği (**represent**) listeyi değiştirdik.

val -> değişkene yeniden değer atanmasını yasaklar, ancak nesnenin iç durumunun değiştirilmesine izin verir.

Referans (listenin bellekteki adresi) değişmez, sadece o adresteki objenin "iç durumu (internal state)" güncellenir.

---

There are some restrictions on when the `const` modifier can be applied. First, it can only be used with a `String` or a primitive type variable:

```kotlin
const val CONST_INT = 127
const val CONST_DOUBLE = 3.14
const val CONST_CHAR = 'c'
const val CONST_STRING = "I am constant"
const val CONST_ARRAY = arrayOf(1, 2, 3) // error: only primitives and strings are allowed
```

Normalde `val` anahtar kelimesi ile oluşturulan bir değişken **Sadece Okunur (Read-Only)** bir referanstır. Ancak `val` ile tanımlanan bir değişkenin değeri kod çalışırken, yani uygulamanın yürütüldüğü esnada **Çalışma Zamanı (Runtime)**'nda hesaplanabilir (Örneğin, bir fonksiyondan dönen değer veya veritabanından çekilen bir veri `val` değişkenine atanabilir).

Ancak başına `const` koyduğunda, derleyiciye şu kesin garantiyi verirsin: _"Bu değer asla değişmeyecek, herhangi bir hesaplama gerektirmeyecek, daha kod derlenirken (uygulama henüz kullanıcı cihazında çalışmaya başlamadan önce) ne olduğu %100 belli olan statik bir ilkel (primitive) veya String değeridir."_

Bunun iki devasa mühendislik avantajı vardır:

1. **Satıriçi Yerleştirme (Inlining - Performans Artışı):** Derleyici kodu makine koduna (veya JVM için Bytecode'a) çevirirken, `const val` değişkenini bellekte bir referans olarak tutmaz. Gider, o değişkenin kullanıldığı _her yere_ doğrudan o değeri "hardcode" (sabit) olarak yazar. Bu, çalışma zamanında belleğe (RAM'e) gidip "Acaba bu değişkenin değeri neydi?" diye bakma maliyetini (memory lookup overhead) tamamen ortadan kaldırır. Uygulaman daha hızlı ve bellek dostu çalışır.
    
2. **Anotasyonlarda (Annotations) Kullanım Zorunluluğu:** Anotasyonlar (örneğin `@Deprecated`, `@JvmName` veya Android'deki `@PrimaryKey`) derleme zamanında okunur ve işlenir. Çalışma zamanında hesaplanacak dinamik bir `val` değerini anotasyon içine parametre olarak veremezsin. Çünkü anotasyonun, program daha başlamadan o değeri bilmesi gerekir. `const val` bu garantiyi sağlar.

**İsimlendirme Standardı (Naming Convention):** İncelediğin Kotlin Style Guide'a göre, `const val` değişkenler her zaman **SCREAMING_SNAKE_CASE** (TÜMÜ_BÜYÜK_HARF_VE_ALT_TİRE) ile yazılmalıdır (Örn: `const val MAX_TIMEOUT_MS = 5000`). Bu, kodu okuyan mühendisin bunun çalışma zamanında hesaplanan sıradan bir `val` olmadığını, bir **Derleme Zamanı Sabiti (Compile-Time Constant)** olduğunu bir bakışta anlamasını sağlar.

For `val` variables use the **camelCase** format. Start with a lowercase letter and capitalize the first letter of each subsequent word. Here are some examples:

```kotlin
val numberOfWheels: Int
val isConnectionAvailable: Boolean
val userFirstName: String
```

---

![[Pasted image 20260703134745.png]]

```kotlin
/**
 * Verilen iki sayıyı böler ve sonucu döndürür.
 * @param dividend Bölünen sayı (pay). 
 * @param divisor Bölen sayı (payda). Kesinlikle 0 olmamalıdır!
 * @return Bölme işleminin sonucu olan ondalıklı sayı.
 * @throws IllegalArgumentException Eğer [divisor] 0 olarak verilirse fırlatılır.
 */
// Yukarıdaki kısım tamamen KDoc. Derleyici burayı okumaz, Dokka aracı ise HTML'e çevirir.

@Deprecated("Bu fonksiyon eski matematikte kaldı, yerine divideOptimized() kullanın.")
// Bu bir ANOTASYON. Derleyici bunu okur ve bu fonksiyonu kullanan birinin 
// IDE'sinde fonksiyonun üstünü çizer (strikethrough).
fun divide(dividend: Double, divisor: Double): Double {
    
    // İş kuralı: Bölen sıfır olamaz.
    if (divisor == 0.0) {
        throw IllegalArgumentException("Bölen sıfır olamaz!")
    }
    
    // Fonksiyonun gerçek mantığı
    return dividend / divisor
}

fun main() {
    // IDE'de 'divide' yazısının üzerine fareyi getirdiğinde, 
    // yukarıda yazdığımız KDoc açıklamaları harika bir arayüzle karşına çıkar.
    val result = divide(10.0, 2.0)
    println(result)
}
```

![[Pasted image 20260703134159.png]]
Boyle de goruntulenebilir:
![[Pasted image 20260703134226.png]]

---

```kotlin
// Giriş Noktası (Entry Point). 
// Açılış parantezi '{' satır sonunda yer alıyor. (K&R Style)
fun main() { 
    // Girinti (Indentation) için Tab değil, 4 adet Space (Boşluk) kullanıyoruz.
    val isSystemReady = true
    
    // 'if' Bloğu (Block) başlıyor. Açılış parantezi yine satır sonunda.
    if (isSystemReady) {
        // İfade (Statement) sonuna kesinlikle noktalı virgül (;) KOYMUYORUZ!
        println("Sistem hazır, parantez stilleri doğru!")
    } // Kapanış parantezi '}' kendi başına ayrı bir satırda yer alıyor.
}
```

### K&R Stili Parantezler

Bilgisayar mühendisliği literatüründe bu parantez kullanım stiline **Kernighan and Ritchie (K&R) Style** veya halk arasında **Mısır Parantezleri (Egyptian Brackets)** denir. Kotlin stil rehberi, dolu bloklar `(Blocks)` için bu stilin kullanılmasını emreder: Açılış parantezinden önce satır sonu (line break) olmaz, parantez açıldıktan sonra alt satıra geçilir ve kapanış parantezi ayrı bir satırda bloğu bitirir.

```kotlin
fun main() {
    // Sen klavyeden sadece [TAB] tuşuna basarsın:
    // IDE bunu arka planda dosyaya şu şekilde yazar:
    // "    val developerName = \"Senior Kotlin Dev\"" (Başında 4 adet fiziksel boşluk karakteri var)
    val developerName = "Senior Kotlin Dev"
    
    if (developerName.isNotEmpty()) {
        // Burada iç içe (nested) bir blok var. Sen yine bir kez [TAB]'a basarsın, 
        // IDE bir önceki seviyenin üzerine 4 boşluk daha ekler ve toplam 8 boşluk olur.
        // "        println(\"Giriş Yapıldı\")"
        println("Giriş Yapıldı")
    }
}
```

---

```kotlin
`name with space`

val `good name` = Oguzhan  
println(`good name`)

// OUTPUT
// Oguzhan
```

Kotlin: Language version 2.3 is experimental, there are no backwards compatibility guarantees for new language and library features

---

Kotlin has several naming **rules**:

- Names are case-sensitive (`number` is not the same as `Number`);
- Each name can include only letters, digits, and underscores;
- A name cannot start with a digit;
- A name cannot be a keyword (for example, `val`, `var`, `fun` are illegal).

Kotlin also provides the following **conventions**:

- If a variable name is a single word, put it in lowercase (for example — `number`, `value`);
- If a variable name includes multiple words, put them in `lowerCamelCase`, so that the first word is lowercase, while other words start with a capital letter (for example — `numberOfCoins`);
- Do not start variables with an underscore `_`. Technically, you can do it, though;
- Choose meaningful names for your variables, for example, `score` makes more sense than `s`, although they are both valid.

---

Create a constant, give it a meaningful name, and replace the number with it.
When naming constants, we use the `SCREAMING_SNAKE_CASE` style.

```kotlin
println(7)

// bunun yerine asagidaki gibi yap:

const val DAYS_OF_THE_WEEK = 7

fun main() {
    println(DAYS_OF_THE_WEEK)
}
```

Bu sekilde sabitligi kesin olan degerleri kendi basina birakma, bir isim ver ve en tepeye yaz. kodun icinde de o sabiti kullan. daha anlasilir olacaktir.

Bunun gibi sayilara magic number deniyor. ama sadece sayilar icin degil her turlu deger icin dusunebilirsin.

---

- UPPERCASE
- lowercase
- TitleCase
- camelCase
- snake_case
- kebab-case
- UPPER_SNAKE_CASE
- UPPER-KEBAB-CASE
- Title_Snake_Case
- Title-Kebab-Case
- camel_Snake_Case
- camel-Kebab-Case

---

In this case, Kotlin knows that `text` is a string and `n` is a number. Kotlin determines the types of both variables automatically. This mechanism is called **type inference**.

```kotlin
val text = "Hello, I am studying Kotlin now."
val n = 1
```

If we need to declare a variable and initialize it later, type inference won't work at all.

```kotlin
val greeting // error
greeting = "hello"
```

The example above is incorrect because Kotlin cannot infer the type of the variable when it is merely declared, while every variable must have a type. On the contrary, the example below does work because the type is specified by the programmer:

```kotlin
val greeting: String // ok
greeting = "hello"
```

---

Type mismatch

One of the most important functions of data types is to protect you from assigning an unsuitable value to a variable. Take a look at an example of code that doesn't work:

```kotlin
val n: Int = "abc" // Type mismatch: inferred type is String but Int was expected
```

So, if you see a **type mismatch** error, it means you've assigned something unsuitable to a variable. The same problem occurs when you try to assign an unsuitable value to a mutable variable declared with type inference.

```kotlin
var age = 30 // the type is inferred as Int
age = "31 years old" // Type mismatch
```

Note, you cannot change the type of a variable.

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

