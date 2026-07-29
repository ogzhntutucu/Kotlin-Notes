# Kotlin Notes - 6

---
## 6.1
### Data Structures - Common principles

_Algorithms + Data Structures = Programs_

1. provide a systematic way to **organize and structure** data, ensuring efficient storage and retrieval.
2. are designed to **optimize operations** such as searching, insertion, deletion, and so on, ensuring efficient algorithmic performance.
3. provide **scalability and flexibility**, allowing systems to handle growing or changing datasets without sacrificing performance.
4. **optimize memory** usage and manage memory allocation and deallocation efficiently.
5. promote **code reusability** by encapsulating data and operations into reusable modules, enhancing software development productivity and maintainability.

---
## 6.2

### ADT vs CDT

Abstract Data Type (ADT) vs Concrete Data Type / Data Structure (CDT)

**ADT (Soyut Veri Tipi)**, bir veri yapısının _ne yaptığını_ (davranışını, desteklediği işlemleri) tanımlar. **CDT (Somut Veri Yapısı / Data Structure)** ise bunun _nasıl_ gerçekleştiğini (bellekte nasıl saklandığını, hangi algoritmanın kullanıldığını) tanımlar.

- ADT → kullanıcının (user) bakış açısı, bir **sözleşme (contract)**
- CDT → uygulayanın (implementer) bakış açısı, sözleşmenin **gerçek implementasyonu**

ADT tanımı hiçbir zaman "array ile mi, linked list ile mi" demez — sadece davranışı ve kuralları anlatır.

#### Örnek: Stack

Stack ADT'sinin tanımı:

- `push(item)` — üste eleman koy
- `pop()` — en üstteki elemanı çıkar ve döndür
- `peek()` — en üstteki elemana bakmadan gör
- Kural: **LIFO** (Last In, First Out)

Bu davranışı array tabanlı da linked list tabanlı da implemente edebilirsin — ikisi de aynı ADT sözleşmesini sağladığı için kullanıcı açısından fark etmez.

```kotlin
// CDT secimi: Array/List tabanli Stack implementasyonu
class ArrayStack<T> {
    private val elements = mutableListOf<T>()

    fun push(item: T) {
        elements.add(item) // listenin sonuna ekle
    }

    fun pop(): T {
        return elements.removeAt(elements.lastIndex) // son elemani cikar
    }

    fun peek(): T = elements.last()
}
```

#### Kotlin bağlantısı

Kotlin'in standart kütüphanesinde `List` bir **interface**'tir (ADT gibi düşünülebilir), arkasında `ArrayList` gibi somut implementasyonlar çalışır:

```kotlin
val liste: List<Int> = listOf(1, 2, 3)
// List arayuzunu (interface) kullaniyoruz, arkadaki gercek implementasyonla dogrudan ilgilenmiyoruz
```

#### Özet tablo

|              | ADT                                | CDT (Data Structure)                       |
| ------------ | ---------------------------------- | ------------------------------------------ |
| Ne tanımlar? | Davranış, işlemler (`push`, `pop`) | Gerçek implementasyon (array, linked list) |
| Bakış açısı  | Kullanıcının bakış açısı           | Uygulayanın bakış açısı                    |
| Örnek        | Stack, Queue (kavram olarak)       | Array-based Stack, Linked-list-based Queue |

Bu ayrım aynı zamanda **Kapsülleme (Encapsulation)** ile de bağlantılı: ADT kullanan biri iç detaylarla değil, sadece dışa açık davranış sözleşmesiyle muhatap olur.

---
## 6.3
### Queue (sira)

Here's what's important to understand about it:

- A queue is a data structure based on the First In, First Out principle.
- ilk eleman FRONT ile, son eleman REAR ile isaretlenerek siranin basi ve sonu bilinir. 
- A queue supports two operations – enqueue and dequeue – that have a time complexity of O(1).
- A queue can be implemented using different data structures (array, linked list vs.)

With a queue data structure, you can maintain the sequence of data as it arrives and access the first elements with ease, making it ideal for scenarios where order matters.

---
## 6.4
### Big O Notasyonu

#### Zaman Karmaşıklığı (Time Complexity) nedir?

Bir işlemin (operation) veri büyüdükçe **ne kadar yavaşladığını** ölçen bir kavram. "Big O" notasyonu (`O(...)`) bunu ifade etmenin standart yolu. Soru şu: **eleman sayısı (`n`) arttıkça, bu işlem kaç adımda bitiyor?**

#### O(1) ne demek?

**O(1) = Sabit Zaman (Constant Time)**. Yani işlemin süresi, veri yapısında **kaç eleman olduğundan bağımsızdır.** İster 10 eleman olsun ister 10 milyon, işlem hep aynı sürede (tek adımda) biter.

Queue'nun `enqueue` (sıraya ekleme) işlemini düşün — yeni eleman her zaman **sıranın en sonuna** eklenir:

```kotlin
val queue = ArrayDeque<Int>()

queue.addLast(1) // sıra: [1]
queue.addLast(2) // sıra: [1, 2]
queue.addLast(3) // sıra: [1, 2, 3]
```

Sırada 3 eleman olsun ya da 3 milyon eleman olsun, `addLast()` işlemi her seferinde **aynı şeyi yapıyor**: direkt sonuna bir eleman koyuyor. Öndeki elemanları saymana, aralarında gezmene gerek yok. Bu yüzden O(1).

Aynı mantık `dequeue` (çıkarma) için de geçerli — sıranın **en başındaki** elemanı çıkarıyorsun, önce kaç eleman var diye bakmana gerek yok:

```kotlin
val ilkEleman = queue.removeFirst() // direkt bastaki elemani cikar, O(1)
```

#### O(n) ne demek?

Farkı görmen için karşıt bir örnek vereyim. Diyelim ki bir listede belirli bir elemanı arıyorsun:

```kotlin
val liste = listOf(1, 2, 3, 4, 5, /* ... */, 1000)
val bulundumu = liste.contains(500) // O(n)
```

Burada Kotlin, `500` sayısını bulana kadar listeyi **baştan sona tek tek** gezmek zorunda kalabilir. Liste 10 elemanlıysa en fazla 10 adım, 1000 elemanlıysa en fazla 1000 adım sürer. Yani **eleman sayısı (`n`) arttıkça işlem de yavaşlar** — bu yüzden `O(n)` (n ile doğru orantılı).

#### Özet mantık

|Notasyon|Anlamı|Örnek|
|---|---|---|
|`O(1)`|Veri büyüklüğünden bağımsız, hep sabit sürede biter|Queue'ya `enqueue`/`dequeue`, array'de index ile erişim (`liste[5]`)|
|`O(n)`|Veri büyüdükçe işlem de orantılı yavaşlar|Listede eleman arama (`contains`), listeyi baştan sona gezme|

Queue'nun O(1) olmasının pratik önemi şu: **çok büyük veri setlerinde bile** enqueue/dequeue işlemleri hep aynı hızda kalır, veri büyüdükçe performans düşmez. Bu da onu "sıra" mantığı gereken senaryolar için (örneğin bir görev kuyruğu, mesaj kuyruğu) ideal yapan şeylerden biri.

---
## 6.5
### Stack (yigin)

- LIFO (son giren ilk cikar) prensibine dayalidir.
- son giren eleman TOP pointeri ile tutulur. TOP ile gosterilen veri POP ile cikarilir, PUSH ile yenisi koyulur.
- queue gibi, O(1) zaman karmasilikligindadir. (TOP'un konumu her zaman bilindigi icin arama gerekmez)
* array (sabit boyutlu ya da dinamik/resizable) ya da linked list ile implemente edilebilir.

---

## 6.6
### Diger Veri Yapilari (kisaca)

#### Doğrusal (Linear) Yapılar

- **Array (Dizi)** — Sabit index'lerle erişilen, sıralı eleman koleksiyonu. Index ile erişim O(1), ama araya eleman ekleme/çıkarma pahalı (O(n)).
- **Linked List (Bağlı Liste)** — Her eleman (node) bir sonrakinin adresini tutar. Araya ekleme/çıkarma hızlı (O(1)), ama index ile erişim yavaş (O(n)) — baştan gezmen gerekir.
- **Stack** ve **Queue** — Zaten bildiğin ikisi.
- **Deque (Double-Ended Queue)** — Hem baştan hem sondan ekleme/çıkarma yapılabilen, Stack + Queue'nun birleşimi gibi düşünebileceğin bir yapı. Kotlin'in `ArrayDeque`'i tam olarak bu.

#### Ağaç (Tree) Tabanlı Yapılar

- **Tree (Ağaç)** — Hiyerarşik veri yapısı; her node'un bir üst (parent) ve birden fazla alt (child) node'u olabilir. Örnek: dosya sistemi klasör yapısı.
- **Binary Tree (İkili Ağaç)** — Her node'un en fazla 2 çocuğu olan ağaç türü.
- **Binary Search Tree (BST)** — Sıralı arama için optimize edilmiş ikili ağaç; sol taraf hep küçük, sağ taraf hep büyük değerleri tutar. Arama O(log n).
- **Heap (Yığın — Stack ile karıştırma!)** — En küçük ya da en büyük elemana hızlıca ulaşmak için kullanılan özel bir ağaç yapısı. Öncelik kuyrukları (priority queue) genelde heap ile implemente edilir.
- **Trie (Prefix Tree)** — Özellikle string'lerde ortak önekleri (prefix) verimli saklamak için kullanılır. Otomatik tamamlama (autocomplete) sistemlerinde sık görülür.

#### Hash Tabanlı Yapılar

- **Hash Table / Hash Map (Özet Tablosu)** — Key-value (anahtar-değer) çiftlerini saklar; bir "hash fonksiyonu" ile key'i doğrudan bir konuma eşler, bu sayede arama/ekleme ortalama O(1)'dir. Kotlin'deki `Map` bunun soyutlaması (ADT), `HashMap` ise yaygın implementasyonu (CDT).
- **Set (Küme)** — Tekrarsız (unique) elemanlar tutan koleksiyon; genelde hash table üzerine kurulur (`HashSet`).

#### Graf (Graph) Tabanlı Yapılar

- **Graph (Çizge)** — Node'ların (vertex) birbirine kenarlarla (edge) bağlandığı, hiyerarşik olmak zorunda olmayan genel yapı. Örnek: sosyal ağdaki arkadaşlık bağlantıları, harita/rota uygulamaları.

---
## 6.7
### 