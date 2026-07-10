# Kotlin Notes - 1
#### [@ogzhntutucu](https://github.com/ogzhntutucu/Kotlin-Notes)

---
## 1.1
### Temel Terminoloji

- **Program (Program):** Bilgisayara ne yapması gerektiğini söyleyen komutlar dizisidir. İşlemlerin yukarıdan aşağıya, yazıldıkları sırayla tahmin edilebilir bir şekilde çalıştırılmasına **Sıralı Akış (Sequential Flow)** denir.
- **Komut (Statement):** İşletilecek tek bir eylemi temsil eden kod satırıdır. (Örneğin ekrana yazı yazdırmak veya bir değişken ataması yapmak).
- **Değer Üreten İfade (Expression):** Çalıştırıldığında ortaya tek bir _değer_ çıkaran kod parçasıdır. (Örneğin `2 * 2` bir ifadedir ve ürettiği değer `4`'tür).
- **Blok (Block):** Süslü parantezler `{ ... }` arasına alınmış komutlar grubudur. Bir bloğun içi, değişkenlerin bellekteki yaşam döngüsünü belirleyen bir **Kapsam (Scope)** yaratır.
- **Anahtar Kelime (Keyword):** Dili tasarlayanlar tarafından derleyici seviyesinde özel bir anlama sahip olduğu için rezerve edilmiş kelimelerdir (örneğin: `fun`, `val`, `if`). Bunları kendi değişken isimlerin olarak kullanamazsın.
- **Tanımlayıcı (Identifier):** Kod içindeki değişkenlere, fonksiyonlara veya sınıflara senin (programcının) verdiği isimlerdir.
- **Yorum (Comment):** Derleyici tarafından tamamen görmezden gelinen, ancak kodu okuyan diğer mühendisler (veya gelecekteki sen) için bırakılmış açıklama metinleridir. Kotlin'de `//` ile başlar.
- **Boşluk (Whitespace):** Boşluk karakteri, tab veya yeni satır karakterleridir. Kodu okunabilir kılmak için hayati önem taşır. Kotlin Stil Rehberine göre, girintileme (indentation) için _tab_ yerine her zaman 4 adet _boşluk (space)_ kullanılmalıdır.
- **Kotlin'in Kalbi: Giriş Noktası (Entry Point):** İşletim sistemi veya Java Sanal Makinesi (JVM), programını başlattığında ilk olarak nereye bakacağını bilmek zorundadır. Kotlin'de bu **Giriş Noktası (Entry Point)**, `main` adındaki özel fonksiyondur.

---
## 1.2
### Some Keybindings

- _Ctrl + J_
	- Live Templates (Code snippets that you can insert into your code)
- _Right Ctrl + \`_
	- hizli ide duzenlemesi yap
- _Ctrl + Alt + L_
	- Code Reformat
- _Ctrl + Shift + A_
	- Help | Find Action
- _Ctrl + F_
	- Find
- _Ctrl + R_
	- Find and Replace
- _Ctrl + Click_
	- Quick Declaration or Usages
- _Ctrl + Shift + I_
	- Quick Definition
- _Ctrl + /_
	- Hizli yorum satirina al
- _Ctrl + Shift + /_
	- Blok comment
- _Typing `/**` then pressing Enter_
	- Javadoc stubs
- _Ctrl + X_
	- Satiri kes (kopyala ve sil)
- _Ctrl + D_
	- Satiri bir alt satira kopyala (Cogalt)
- _Ctrl + Q_ (Zaten fareyle uzerine gelince acilan sey)
	- Quick documentation
	- iki kere kullanirsan dokumantasyon modu acilir ekranin bir kosesinde.
- _Alt + Enter_
	- Error solution
- _Shift + Delete_
	- Satiri sil, imleci bir alta tasi

---
## 1.3
### JDK - JRE - JVM etc.

Think of them as people...
JDK - the big boss, under whom JRE and development tools like compiler, debugger, archiver etc. work. If you want to develop java programs, you go to this person.
JRE - the execution environment provider, under whom JVM and the java class libraries work. When we want to run our compiled source code, we go to this person. The Java class library is a very cool person. This guy helps a lot in writing programs.
JVM - the guy who interprets the compiled source code (i.e. bytecode) into native machine code that the computer can understand. This person acts as a mediator between computer and our bytecode. Think of him as a linguist who helps you translate what other person is speaking.

![565](../resources/_attachments/Pasted%20image%2020260702162454.png)

![527](../resources/_attachments/Pasted%20image%2020260702162654.png)

When you run a compiled program, JRE combines the program bytecode with necessary libraries and runs the JVM, which executes the resulting bytecode.

Derlenmiş bir programı çalıştırdığınızda, JRE programın bayt kodunu gerekli kütüphanelerle birleştirir ve ortaya çıkan bayt kodunu çalıştıran JVM'yi çalıştırır.

![426](../resources/_attachments/Pasted%20image%2020260706123905.png)

Programları paketlemek için bir arşiv formatı.
An archive format for packing programs: JAR

---
## 1.4
### Declaring variables

Before you can start using a variable, you must declare it. To declare a variable, Kotlin provides two keywords:

- `val` (for _value_) declares a **read-only variable** (just a **named value**), which cannot be changed after it has been initialized so a value can be assigned once (this is actually not entirely true, we will discuss this issue in more detail later);
- `var` (for _variable_) declares a **mutable variable**, which can be changed (as many times as needed).
- `const` is used for variables (with val) **that are known at compile-time**.

yani const, val ile birlikte kullanilir.

---
## 1.5
### var tip engeli

Ancak değişkenler (var anahtar sözcüğü ile bildirilenler) için bir kısıtlama vardır. Değerlerini yeniden atarken, yalnızca ilk değerle aynı türden yeni değerleri kullanabilirsiniz. Yani aşağıdaki kod parçası doğru değil:

There is one restriction for mutable variables (the ones declared with the keyword `var`), though. When reassigning their values, you can only use new values of the same type as the initial one. So, the piece of code below is not correct:

```kotlin
var number = 10
number = 11 // ok
number = "twelve" // an error here!
```

Please remember this restriction!

---
## 1.6
### val listeye deger atanabilme durumu

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

This code changed the internal state of the `myMutableList` by adding another integer number. When we invoked the `add()` function, we changed not the variable itself but the list it represents.

Bu kod myMutableList'in dahili durumunu başka bir tam sayı ekleyerek değiştirdi. add() fonksiyonunu çağırdığımızda, değişkenin kendisini değil, temsil ettiği (**represent**) listeyi değiştirdik.

val -> değişkene yeniden değer atanmasını yasaklar, ancak nesnenin iç durumunun değiştirilmesine izin verir.

Referans (listenin bellekteki adresi) değişmez, sadece o adresteki objenin "iç durumu (internal state)" güncellenir.

---
## 1.7
### const val

There are some restrictions on when the `const` modifier can be applied. First, it can only be used with a `String` or a primitive type variable:

```kotlin
const val CONST_INT = 127
const val CONST_DOUBLE = 3.14
const val CONST_CHAR = 'c'
const val CONST_STRING = "I am constant"
const val CONST_ARRAY = arrayOf(1, 2, 3) // error: only primitives and strings are allowed
```

val runtime'da, const val compile time'da hesaplanir.

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
## 1.8
### Comment types

```kotlin
// a comment       // end-of-line comment

/* a comment */    // multi line comment

/** a comment */   // documentation comments
```

---
## 1.9
### KDoc

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

![594](../resources/_attachments/Pasted%20image%2020260703134159.png)

Ust taraf ide'de boyle de goruntulenebilir:

![516](../resources/_attachments/Pasted%20image%2020260703134226.png)

---
## 1.10
### K&R Stili Parantezler

Bilgisayar mühendisliği literatüründe bu parantez kullanım stiline **Kernighan and Ritchie (K&R) Style** veya halk arasında **Mısır Parantezleri (Egyptian Brackets)** denir. Kotlin stil rehberi, dolu bloklar `(Blocks)` için bu stilin kullanılmasını emreder: Açılış parantezinden önce satır sonu (line break) olmaz, parantez açıldıktan sonra alt satıra geçilir ve kapanış parantezi ayrı bir satırda bloğu bitirir.

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

---
## 1.11
### Tab ( \t ) degil 4 bosluk kullaniriz

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

yani aslinda tab tusuna bastigimizda IDE koda tab karakterini degil 4 boslugu inject eder. bu sayede evrensel kod kuralina uyulmus olur. eger herkes tab kullansaydi, idelerin bilgisayarlarin yazilimcilarin kararlarina gore farkli tab buyuklukleri olurdu ve kodlar tutarli olmazdi.

---
## 1.12
### Experimental bir degisken adi kullanimi

```kotlin
`name with space`

val `good name` = Oguzhan  
println(`good name`)

// OUTPUT
// Oguzhan
```

Kotlin: Language version 2.3 is experimental, there are no backwards compatibility guarantees for new language and library features

---
## 1.13
### Naming - rules and conventions

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
## 1.14
### Magic Numbers and Const Val

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

Bunun gibi sayilara magic number deniyor. ama sadece sayilar icin degil her turlu deger icin dusunebilirsin. (magic value)

---
## 1.15
### Case names

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
## 1.16
### Type inference

tip cikarimi. degiskenin degerinin otomatik anlasilmasi.

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
## 1.17
### Type mismatch

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
