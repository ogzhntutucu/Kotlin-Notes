# Kotlin Notes

> Kotlin öğrenirken çeşitli kaynaklardan (kurslar, videolar, dokümantasyon) aldığım notların kişisel arşivi.

Repo, Kotlin öğrenme sürecimde topladığım notları içerir. Tek bir kursa bağlı değildir; zamanla farklı kurslardan, videolardan ve dokümanlardan öğrendiğim her şeyi buraya eklemeye devam edeceğim. Yani "Kotlin'e dair öğrendiklerimin" biriktiği yaşayan bir arşivdir.

Notları [Obsidian](https://obsidian.md) ile tutuyorum, bu yüzden repo aynı zamanda bir Obsidian vault'udur. Öncelikli amacı kişisel notlar olmakla birlikte ayrıca faydalanmak isteyen olursa diye herkese açık.

### İçindekiler

| Last Notes | Topics |
|-----|--------|
| [notes-4](notes/notes-4.md) | Comparing Integer Numbers & Relational Operations, Unicode & Chars (Escape Sequences, Processing Characters), Input & Output Streams, Working with Strings (Immutability, Comparing, Substrings, Replacing Parts), Floating-Point Arithmetic (Computation Errors, Decimal Separator) |
| [notes-3](notes/notes-3.md) | Boolean Variables & Logical Operators, Logical Operator Precedence, Standard Input (`readln`, Reading Multiple Values), Numeric & String Type Conversion, Type Coercion, Unsigned Integers & Data Type Overflow, `if` in Kotlin vs. Java, Practice Katas |

<details>
<summary>Other Notes</summary>

| Other Notes | Topics |
| ----- | -------- |
| [notes-2](notes/notes-2.md) | Numeric Types (Integer & Floating-Point), Characters & Booleans, Java Scanner, String Operations (Concatenation, Raw Strings, Templates), Operators (Arithmetic, Unary, Compound Assignment, Increment/Decrement), Precedence & Evaluation Order, Side Effects & Pure Functions |
| [notes-1](notes/notes-1.md) | Core Terminology, JDK vs JRE vs JVM, Declaring Variables (`val` / `var` / `const`), Comment Types & KDoc, Code Style (K&R Braces, Indentation), Naming Rules & Conventions, Magic Numbers, Type Inference & Type Mismatch |

</details>

### Kaynaklar

- **[Jetbrains Academy Course — Kotlin Developer](https://hyperskill.org/courses/3-kotlin-developer)** — Hyperskill
- **[KeKod Course — Android Development](https://www.youtube.com/@KeKod)** — YouTube
- **[Kotlin Docs](https://kotlinlang.org/docs/home.html)** — Resmî Kotlin dokümantasyonu
- **[Android & Kotlin Docs](https://developer.android.com/kotlin/learn)** — Android geliştirici dokümantasyonu
- **[Codewars](https://www.codewars.com/)** — Alıştırma / kod çözme

### Kotlin Mentor (Claude Project)

Kurs sırasında anlamadığım konularda yapay zekadan yardım alıyorum. Bunun için Claude'da bir "Kotlin Mentor" projesi oluşturdum ve bu repoyu projeye bağladım; böylece her sohbette güncel notlarıma erişip gerektiğinde referans verebiliyor. Projeye özel yazdığım talimatlar ve resmi Kotlin style guide, [kotlin-mentor-docs/](kotlin-mentor-docs/) altında tutuluyor:

- **[instructions.md](kotlin-mentor-docs/instructions.md)** — Projenin "system prompt"u: seviyem, hedeflerim, dil kuralları, öğretim tarzı tercihlerim
- **[kotlin-style-guide.md](kotlin-mentor-docs/kotlin-style-guide.md)** — Google/Android'in resmî Kotlin stil rehberi (idiomatic yazım için referans kaynak)

Notlarım Obsidian vault'uma, vault'um GitHub repo'ma, repo'm da Claude projeme bağlı. Bu üç platform iç içe çalışıyor. Ve bu çalışma sistemi ile öğrendiklerimi unutmamak için bir de tekrar sistemi kurdum.

### Anki (Recall) Sistemi

Öğrendiklerimi kalıcı hale getirmek için notlarımı bir aralıklı tekrar (spaced repetition) sistemiyle destekliyorum. Her `notes-N.md` dosyasından, o notun "unutmak istemediğim" kısımlarını Anki'ye içe aktarılabilir kart dosyalarına (`cards-N.txt`) dönüştürüyorum. Bu dosyalar [anki-cards/](anki-cards/) altında yaşıyor.

Kartlar; kavramlar, sezgiye aykırı davranışlar (gotcha), karşılaştırmalar ve İngilizce terminoloji üzerine odaklanır — her gün yazarak zaten pekişen temel sözdizimi kartlaştırılmaz. Kartların nasıl üretildiğine, format ve içerik kurallarına dair kılavuz ile Anki not tipi (note type) şablonları [anki-cards/card-type-docs/](anki-cards/card-type-docs/) altındadır.

### Yapı

```
notes/                  # Markdown notlar
resources/_attachments/ # Notlarda kullanılan görseller
kotlin-mentor-docs/     # Kotlin Mentor (Claude Project) yapılandırma dosyaları
anki-cards/             # Anki kart dosyaları (cards-N.txt) + üretim kılavuzu ve şablonlar
```

---

Obsidian'ın `.obsidian/` yapılandırma klasörü, `.gitignore` dosyası aracılığıyla kasıtlı olarak hariç tutulmuştur.
