# Kotlin Anki Kart Üretim Kılavuzu

Bu belge, Oğuzhan'ın (@ogzhntutucu) Kotlin öğrenme notlarından **Anki'ye içe aktarılabilir kart dosyaları** (`cards-N.txt`) üretmek için gereken her şeyi içerir. Amaç: bu kılavuz + birkaç örnek `cards-*.txt` dosyası verildiğinde, geçmiş sohbete hiç ihtiyaç duymadan, temiz bir sohbette tutarlı ve doğru kart dosyaları üretebilmek.

> **Nasıl kullanılır:** Yeni bir kart üretim sohbeti açılır; bu kılavuz ve daha önce üretilmiş 1-2 `cards-*.txt` dosyası eklenir. Sonra "notes-5'ten kart üret" gibi bir istekle çalışılır. Repo (Kotlin-Notes) zaten projeye bağlı olduğundan, ilgili `notes-N.md` oradan okunur.

---

## 1. Sistemin genel işleyişi

Akış şudur:

```
kursa çalış → Obsidian'a not al → GitHub'a pushla
   → notu "unutmak istemem" filtresinden geçir (Oğuzhan'ın recall turu)
   → cards-N.txt üret (bu belgenin işi)
   → Anki Desktop ile import et → GUID sayesinde çoğaltmadan güncellenir
   → AnkiWeb telefona senkronlar → boş vakitte tekrar
```

Temel ilkeler:

- **Repo tek doğruluk kaynağıdır (source of truth).** `cards-N.txt` dosyaları repoda kalıcı yaşar; hem kartların master kopyası hem de import dosyasıdır. Anki yalnızca bir "tekrar yüzeyi"dir; koleksiyon bozulsa dosyalar tekrar import edilerek her şey geri kurulur.
- **`cards/` klasörü `notes/` klasörünü aynalar.** Her `notes-N.md` → bir `cards-N.txt`.
- **Tek bir `Kotlin` destesi** vardır; alt deste yoktur. Konu/tür ayrımı **etiketlerle** yapılır (aşağıda).
- **Tek bir `Kotlin` not tipi (note type)** vardır. Kartların dört "türü" (gotcha/concept/compare/term/when-to-use) Anki'nin note/card type'ı değil, sadece **içerik kategorisi + etiket**tir.

---

## 2. Kart üretim ilkeleri (felsefe)

Kaynak: Piotr Woźniak, "20 Rules of Formulating Knowledge". İşe yarayan çekirdek:

- **Anki öğretmez, tazeler.** Bir konuyu Anki'yle ilk kez öğrenmeye çalışma; sadece **zaten anlaşılmış** bilgiyi kartla.
- **Her şey kart olmaz — agresif ele.** En büyük tuzak, her gün yazarak zaten pekişecek temel sözdizimini (nasıl `fun main()` yazılır gibi) kartlamaktır. Ayrıca Oğuzhan yeni mezun bir bilgisayar mühendisidir; CS temellerini (operand, unary/binary, iki'nin tümleyeni, encoding temelleri vb.) zaten bilir — bunları kartlamaya değmez. Kart yalnızca şu değerlerde üretilir:
    - **Kavramlar ve "neden"i** (val vs const val farkı, side effect nedir)
    - **Gotcha'lar** — çıktısı/sonucu sezgiye aykırı olan şeyler
    - **Ne zaman hangisi** (`substringBefore` mı `substringBeforeLast` mı)
    - **Terminoloji** — İngilizce teknik terimi tanıma (Oğuzhan'ın İngilizce hedefine de hizmet eder)
- **Minimum bilgi ilkesi:** her kart _tek_ şey sorar. Bileşik bilgiyi böl. **Karşılaştırmalar (compare) bu ilkenin meşru istisnasıdır** — çünkü "karşılaştırmanın kendisi" tek bir bilgi birimidir; üç maddeli bir arka yüz normaldir.
- **Aktif hatırlama (active recall):** ön yüz tanımayı değil, üretmeyi zorlamalı. Gotcha'larda cevabı ele vermeyen "Çıktı ne olur?" / "Bu kod derlenir mi?" kalıbı kullanılır.
- **Enumeration'dan kaçın:** sırasız liste ezberi (ör. "4 integer tipini say") beynin en zorlandığı şeydir. Böyle şeyler birebir liste kartı yapılmaz; bir **örüntüye** (ör. 8/16/32/64 ikişer katlanır) veya anlamaya bağlanır. Örnek karar: sayısal tiplerin **birebir aralıkları** (−128..127 vb.) ezber kartı olmaz; bit boyutu + işaretlilik + gerekiyorsa aralık formülü yeterlidir.
- **Karışmayı (interference) önle:** birbirine çok benzeyen kartların **önleri ayırt edilebilir** olmalı, yoksa hangisinin sorulduğu karıştırılır. Oğuzhan'ın notlarında bol karşılaştırma vardır; önleri bilinçli farklılaştır.
- **Arkada örnek:** mümkün olduğunca, arka yüzde kavramı gösteren **küçük (1-3 satır) bir kod örneği** olsun. Bazı saf tanım/terminoloji kartlarında doğal bir örnek olmayabilir; o zaman zorlama.
- **Kolay kartlardan korkma.** Anki'nin algoritması kolay kartları hızla uzun aralıklara atar; neredeyse maliyetsizdirler. Temeldeki bir kavramı sağlamlaştırıyorsa veya terminoloji pekiştiriyorsa değerlidir.

**Kart sayısı:** dosya yoğunluğuyla orantılı, ama minimal. Referans: notes-1 → 11 kart, notes-2 → 16, notes-3 → 14, notes-4 → 11. Kabaca not-bloğu başına ~1 kart; bazı bloklar bölünür (bir kavram + bir gotcha), bazıları birleştirilir (aynı temanın parçaları), bazıları kesilir.

**Bölme / birleştirme / kesme örnekleri (geçmişten):**

- _Böl:_ `val`/`var`/`const val` karşılaştırması ayrı bir kart, "val read-only ama iç durumu değişir" ayrı bir gotcha.
- _Birleştir:_ "atamada implicit conversion yok" ile "ifadede coercion otomatik" gerilimini tek kartta topla (en öğretici kartlardan biri olur). `2 + 3`'ün Byte'a atanamaması ile `Short % Byte → Int` aynı "aritmetik Int üretir" kartında birleşir.
- _Kes:_ KDoc etiketleri (`@param` vb. — kendini açıklayan referans), Unicode istatistikleri (trivia), escape sequence listesi (sürekli kullanılıp içselleşen), scope function / function reference (henüz düzgün işlenmemiş ileri konu — "önce anla sonra ezberle").

---

## 3. İçerik kategorileri

Beş kategori vardır. Her biri `kotlin::kind::...` etiketiyle işaretlenir.

- **gotcha** — çıktısı/davranışı sezgiye aykırı olan şey. Ön yüz genelde koddur ve "Çıktı ne olur? / Derlenir mi?" diye sorar; cevabı ele vermez. (Ör. `num++ + ++num`, type overflow, `val b: Byte = 2 + 3`.)
- **concept** — bir kavramın tanımı ve "neden"i. (Ör. type inference nedir, side effect nedir.)
- **compare** — iki/üç şey arasındaki fark. Arka yüz doğal olarak maddeli ve biraz uzun olabilir. (Ör. `val`/`var`/`const val`, prefix/postfix, `compareTo` vs `equals`.)
- **when-to-use** — hangi durumda hangisi seçilir. (Ör. `substringAfter` vs `substringAfterLast`.)
- **term** — Türkçe tanımdan İngilizce terimi hatırlama. Ön yüz Türkçe tanım + "(İngilizce terim)", arka yüz İngilizce terim + kısa açıklama. **Tek yönlüdür** (TR tanım → EN terim); ters yön kartı üretilmez.

---

## 4. Dil ve yazım kuralları

- **Türkçe.** Cümle yapısı Türkçe kurulur; içinde İngilizce terim kullanmak serbest, hatta iyi.
- **`Türkçe (İngilizce)` terim formatı.** Kayda değer kavramlar bu biçimde yazılır (ör. Yan Etki (Side Effect), Tip Zorlaması (Type Coercion)). Kartlar tek başına görüldüğü için — sohbetteki "ilk geçişte bir kez" mantığı burada geçerli değildir — terim **her kartta** çift yazılır. `value`, `variable`, `function` gibi aşırı temel şeyler çevrilmez, İngilizce bırakılır.
- **Tam, okunur cümleler; sıkıştırma yok.** Sadelik iyidir ama Türkçe anlaşılırlık pahasına değil. Bir şeyi tek kelimeye kırpıp anlaşılmaz kılma; birkaç kelime daha ekleyerek net yaz. (Bu, Oğuzhan'ın açık isteğidir.)
- Notlar Oğuzhan'ın dağınık üslubunu taklit etmez; temiz, düzenli yazım kullanılır. Notlarda hatalı/eksik bir şey görülürse düzeltilir (gerekirse resmi Kotlin dokümanı / güncel bilgi kullanılır); notlar otoriter kaynak değildir.
- **Çıktı dürüstlüğü:** Kotlin kodu gerçekten çalıştırılamıyor; çıktılar zihinsel izlemeyle söyleniyor. Bir çıktıdan emin olunmadan yazılmaz; şüphede resmi dokümana bakılır.

---

## 5. Dosya formatı (teknik spesifikasyon) — en kritik bölüm

### 5.1 Başlık (header) bloğu

Her `cards-N.txt` dosyası tam olarak şu satırlarla başlar:

```
#separator:tab
#html:true
#notetype:Kotlin
#deck:Kotlin
#guid column:1
#tags column:4
#columns:GUID	Front	Back	Tags
```

(`#columns:` satırındaki ayraçlar da TAB'dir.) Bu başlıklar sayesinde import elle ayar gerektirmez: ayırıcı TAB, not tipi Kotlin, deste Kotlin, HTML açık, 1. sütun GUID, 4. sütun etiket.

### 5.2 Sütun yapısı

Her veri satırı **4 sütun**, aralarında gerçek **TAB** karakteri:

```
GUID <TAB> Front <TAB> Back <TAB> Tags
```

- **GUID** hiçbir yüze basılmaz; yalnızca kimliktir.
- **Front** → kartın ön yüzü. **Back** → arka yüzü. **Tags** → boşlukla ayrılmış etiketler.
- İçerikte asla ham TAB kullanılmaz (sütunları bozar). Kod girintileri boşlukla yazılır.

### 5.3 GUID şeması

Format: **`nN-XX`** — `N` = not dosyası numarası, `XX` = sıfır dolgulu sıra (`01`, `02`, …). Örnek: `n3-05` (notes-3'ün 5. kartı).

- GUID **kalıcıdır; asla değişmez, asla yeniden kullanılmaz.**
- Bir kartın içeriği sonradan düzeltilse bile **GUID aynı kalır**; böylece tekrar import edildiğinde Anki kartı çoğaltmaz, **yerinde günceller** ve tekrar programını (scheduling) korur. Kimliği içerikten ayırmanın tüm amacı budur.
- Gerçek kartlarda **`test` etiketi kullanılmaz** (o yalnızca pilot denemelerine özgüydü).

### 5.4 Tırnaklama (quoting) kuralı

Sütunlar TAB ile ayrıldığından virgül sorun değildir. Bir alan yalnızca şu durumlarda çift tırnakla `"..."` sarılır:

1. İçinde **gerçek satır sonu** (literal newline) varsa — ki çok satırlı `<pre>` bloklarında hep vardır.
2. İçinde **TAB** varsa (normalde olmaz).
3. İçinde **literal `"` karakteri** varsa.

Sarılı bir alanın içindeki her literal `"`, **çiftlenerek** (`""`) yazılır.

**Pratik kolaylık — `&quot;` hilesi:** Tırnak çiftleme hatalarından kaçınmak için, kod içindeki çift tırnakları literal `"` yerine **`&quot;` HTML varlığı** olarak yaz. `&quot;` içinde gerçek `"` olmadığından alan yalnızca satır sonu için sarılır, çiftleme gerekmez. Renklendirici (highlighter) kartı render ederken `&quot;`'u tekrar `"`'a çevirdiği için Kotlin string'leri yine doğru boyanır. (Tek tırnak `'` hiçbir zaman çiftlenmez; Char literal'leri `'a'` ve Türkçe metinde 'şu şekilde' serbestçe kullanılır.)

### 5.5 HTML kaçışı (escaping)

`#html:true` olduğundan alanlar HTML olarak yorumlanır. Literal görünmesi gereken şu karakterler **her yerde** (kod dahil, özellikle `<pre>` içinde) kaçışlanır:

- `<` → `&lt;`
- `>` → `&gt;`
- `&` → `&amp;`

Örnekler: generic `List&lt;Int&gt;`, mantıksal `&amp;&amp;`, karşılaştırma `a &gt; b`. Kaçışlanmazsa etiket sanılır ve kart bozulur.

**Ters bölü (backslash) literaldir.** Anki metin import'u `\` kaçışı işlemez; `'\u0040'`, `\n` gibi şeyler kodda olduğu gibi yazılır.

### 5.6 `<pre>` bloklarında GERÇEK satır sonu zorunluluğu

Kod blokları `<pre>...</pre>` ile yazılır ve **satırlar arasında gerçek satır sonu (literal newline) kullanılır — `<br>` DEĞİL.**

Sebebi: not tipinin renklendiricisi kodu `pre.textContent` üzerinden okur. `<br>` elemanları `textContent`'e satır sonu katmaz; dolayısıyla `<br>` kullanılırsa çok satırlı kod tek satıra çöker ve renklendirme bozulur. Gerçek satır sonu kullanıldığı için de o alan birden çok fiziksel satıra yayılır ve **mutlaka tırnakla sarılır** (5.4).

Tek satırlık `<pre>` kodda satır sonu olmadığından, o alan (içinde `"` yoksa) sarılmayabilir.

Arka yüzdeki **düz metin** satır sonları ise `<br>` ile yazılır (bunlar `<pre>` dışıdır, sorun değil).

### 5.7 Kaynak künyesi

Her arka yüz, en altında, kaynağı işaret eden soluk bir satırla biter:

```
<small>— notes-N · N.XX</small>
```

Bu, tekrar sırasında "aslını görmek istiyorum" dendiğinde hangi numaralı not bloğuna dönüleceğini gösterir. `<small>` genellikle `</pre>`'den hemen sonra ya da `<br><br>` ardından konur.

---

## 6. Etiket taksonomisi

Üç eksen, hepsi hiyerarşik (`::`) ve boşlukla ayrılır:

- **Kaynak:** `kotlin::src::notes-N`
- **Konu:** `kotlin::topic::XXX` — kullanılan konular: `basics`, `variables`, `types`, `type-conversion`, `operators`, `booleans`, `strings`, `chars`, `functions`, `io`, `control-flow`, `code-style`, `naming`. (Uygun yeni bir konu gerekirse aynı biçimde eklenebilir. Gerekirse ekle.)
- **Tür:** `kotlin::kind::YYY` — `gotcha` / `concept` / `compare` / `when-to-use` / `term`

Örnek etiket alanı:

```
kotlin::src::notes-3 kotlin::topic::type-conversion kotlin::kind::compare
```

Tek deste + bu etiketler sayesinde "sadece gotcha'lar" veya "sadece string kartları" filtreli deste (filtered deck) ile çalışılabilir.

---

## 7. Örnek kartlar (birebir satır formatı)

Aşağıda `<TAB>` gerçek tab karakterini, satır içindeki gerçek alt satırlar da `<pre>` içindeki literal newline'ları temsil eder. (En güvenilir referans, sohbete eklenen gerçek `cards-*.txt` dosyalarıdır.)

**gotcha (çok satırlı `<pre>`, ön yüz sarılı):**

```
n2-13<TAB>"<pre>var num = 0
println(num++ + ++num)</pre>Çıktı ne olur?"<TAB><b>2</b><br><br>Soldan Sağa Değerlendirme (Left-to-Right Evaluation): <code>num++</code> önce 0'ı kullanır (num=1 olur), sonra <code>++num</code> önce num=2 yapıp 2'yi kullanır → <b>0 + 2 = 2</b>.<br><br><small>— notes-2 · 2.19</small><TAB>kotlin::src::notes-2 kotlin::topic::operators kotlin::kind::gotcha
```

**compare (arka yüz sarılı çünkü `<pre>` çok satırlı; `&quot;` kullanımı):**

```
n1-03<TAB>val, var ve const val arasındaki fark nedir?<TAB>"• <b>val</b> — salt-okunur (read-only) referans; bir kez atanır, sonra yeniden atanamaz.<br>• <b>var</b> — değiştirilebilir (mutable); istediğin kadar yeniden atanır.<br>• <b>const val</b> — derleme zamanı sabiti (compile-time constant); yalnızca primitive ve String olur.<br><br><pre>val ad = &quot;Oğuzhan&quot;   // yeniden atanamaz
var yas = 25          // yas = 26 yapılabilir
const val PI = 3.14   // derleme zamanı sabiti</pre><small>— notes-1 · 1.4</small>"<TAB>kotlin::src::notes-1 kotlin::topic::variables kotlin::kind::compare
```

**term (ön yüz Türkçe tanım, arka yüz İngilizce terim):**

```
n2-05<TAB>Bir fonksiyon anlamlı bir değer döndürmediğinde Kotlin'de ne döner? (İngilizce terim)<TAB>"<b>Unit</b> — pratikte 'sonuç yok' (no result) demektir; Java'daki <code>void</code> gibi. <code>println</code> bile Unit döndürür.<br><br><pre>val result = println(&quot;text&quot;)
println(result)  // kotlin.Unit</pre><small>— notes-2 · 2.6</small>"<TAB>kotlin::src::notes-2 kotlin::topic::functions kotlin::kind::term
```

**concept (sarılmamış alan — satır sonu yok, `"` yok):**

```
n3-11<TAB>Unsigned (işaretsiz) integer tipleri nedir; işaretli hallerinden farkı ve nasıl yazılırlar?<TAB>Yalnızca <b>negatif olmayan</b> değerler tutan tiplerdir: <b>UByte, UShort, UInt, ULong</b> (yine sırasıyla 8/16/32/64 bit).<br><br>Boyutları aynı kalır; ama negatif aralık olmadığından o kısım pozitife kayar, yani aynı bit sayısıyla <b>iki kat büyük pozitif</b> değer tutabilirler (ör. UByte 0–255).<br><br>Değerin sonuna <code>u</code> ya da <code>U</code> eki koyarak yazılır: <code>val x: UByte = 5u</code>.<br><br><small>— notes-3 · 3.14</small><TAB>kotlin::src::notes-3 kotlin::topic::types kotlin::kind::concept
```

---

## 8. Yeni bir `cards-N.txt` üretme süreci

1. **Oku ve seviyeyi kalibre et.** İlgili `notes-N.md`'yi repodan oku. Oğuzhan bazen ayrıca "kart adayı" notları verir (kartlaşabilir gördüğü şeylerin listesi) — verildiyse onu esas al, ders notlarıyla çapraz kontrol ederek seviyesini anla (bildiğini baştan anlatıp sıkma, bilmediğini "biliyordur" diye atlama).
2. **Süz (agresif eleme).** Bölüm 2'deki ilkelerle: neyi kart yapacağına, neyi keseceğine/böleceğine/birleştireceğine karar ver. Kestiklerini ve sebebini kullanıcıya kısaca bildir (şeffaflık + kullanıcı itiraz edebilsin).
3. **Kategorize et ve etiketle** (Bölüm 3 ve 6).
4. **GUID ata** sırayla (`nN-01`, `nN-02`, …).
5. **Yaz** (Bölüm 4 dil kuralları + Bölüm 5 format kuralları). Çıktıları zihinsel izlemeyle doğrula; emin olamadığında dokümana bak.
6. **Dosyayı üret** ve indirilebilir olarak sun (`cards-N.txt`).
7. **Kısa özet ver:** kaç kart, hangi kararlar (kesme/birleştirme), nelere bakılmasını istediğin. Kalıp hatası yakalamak için kullanıcıdan içerik/dil geri bildirimini iste; onayı gelince (gerekiyorsa) sonraki dosyaya geç.

---

## 9. Bu projeye özgü alınmış kararlar (karar günlüğü)

Sıfırdan gelen bir Claude'un bilemeyeceği, bu sistemde sabitlenmiş kararlar:

- **Tek deste** (`Kotlin`), alt deste yok; ayrım etiketlerle.
- **Tek not tipi** (`Kotlin`); dört "tür" içerik kategorisi + etikettir, ayrı not/card type değildir.
- **Terminoloji tek yönlü** (TR tanım → EN terim); ters yön kartı yok.
- **GUID ile güncelleme** (çoğaltma değil); GUID kalıcı.
- **Sayısal aralıklar hafif** (bit boyutu + işaretlilik; birebir aralık ezberi yok).
- **İleri konular ertelenir:** henüz düzgün işlenmemiş konular (scope functions, generics, coroutines, DSL vb.) kartlaştırılmaz — "önce anla sonra ezberle". Gerekliyse kısa bir "ileride göreceksin" notu düşülür.
- **CS temelleri kartlaştırılmaz** (operand, unary/binary, encoding temelleri gibi Oğuzhan'ın zaten bildiği şeyler).
- **Kart dili Türkçe, tam cümleli**; aşırı sıkıştırma yok.

---

## 10. `Kotlin` not tipi kurulumu (sistemi yeniden kurulabilir kılmak için)

Not tipi bir kez kurulur, bir daha değişmez. Bozulma/yeniden kurulum durumunda birebir geri getirmek için üç parça aşağıdadır. Kurulum: Anki → **Tools → Manage Note Types → Add → "Clone: Basic"** → adı **`Kotlin`** → seç → **Cards…** → aşağıdaki üç kutuyu doldur.

Renklendirme, çevrimiçiyken CDN'den yüklenen **highlight.js 11.11.1** (monokai teması) ile yapılır; ilk yüklemeden sonra webview önbelleğe aldığı için çevrimdışı da çalışır.

**Front template:**

```
{{Front}}

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.11.1/styles/monokai.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.11.1/highlight.min.js"></script>
<script>
(function () {
  function run() {
    document.querySelectorAll('pre:not([data-hl])').forEach(function (pre) {
      pre.setAttribute('data-hl', '1');
      var code = document.createElement('code');
      code.className = 'language-kotlin';
      code.textContent = pre.textContent;
      pre.textContent = '';
      pre.appendChild(code);
      window.hljs.highlightElement(code);
    });
  }
  function wait(n) {
    if (window.hljs && window.hljs.highlightElement) run();
    else if (n > 0) setTimeout(function () { wait(n - 1); }, 40);
  }
  wait(50);
})();
</script>
```

**Back template:**

```
{{FrontSide}}

<hr id=answer>

{{Back}}

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.11.1/styles/monokai.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.11.1/highlight.min.js"></script>
<script>
(function () {
  function run() {
    document.querySelectorAll('pre:not([data-hl])').forEach(function (pre) {
      pre.setAttribute('data-hl', '1');
      var code = document.createElement('code');
      code.className = 'language-kotlin';
      code.textContent = pre.textContent;
      pre.textContent = '';
      pre.appendChild(code);
      window.hljs.highlightElement(code);
    });
  }
  function wait(n) {
    if (window.hljs && window.hljs.highlightElement) run();
    else if (n > 0) setTimeout(function () { wait(n - 1); }, 40);
  }
  wait(50);
})();
</script>
```

**Styling:**

```css
.card {
  background-color: #2b2b2b;
  color: #c9cbcf;
  font-family: -apple-system, system-ui, "Segoe UI", Roboto, sans-serif;
  font-size: 17px;
  line-height: 1.6;
  text-align: left;
  padding: 18px 22px;
}

.card pre {
  background-color: #1e1f22;
  border: 0.5px solid #3c3f41;
  border-radius: 8px;
  padding: 14px 16px;
  margin: 12px 0;
  overflow-x: auto;
  font-family: "JetBrains Mono", "SF Mono", Menlo, Consolas, monospace;
  font-size: 14px;
  line-height: 1.55;
}

.card code {
  background-color: #3c3f41;
  border-radius: 4px;
  padding: 1px 6px;
  font-family: "JetBrains Mono", Menlo, Consolas, monospace;
  font-size: 0.85em;
  color: #d4d4d4;
}

.card pre code,
.card pre code.hljs {
  background: transparent;
  padding: 0;
  font-size: inherit;
  color: #a9b7c6;
}

.card b, .card strong {
  color: #e0a367;
  font-weight: 600;
}

.card hr#answer {
  border: none;
  border-top: 0.5px solid #3c3f41;
  margin: 20px 0;
}

.card small {
  display: inline-block;
  margin-top: 14px;
  font-size: 12px;
  font-style: italic;
  color: #6b6b6b;
}
```

---

## 11. Import adımları

1. Anki masaüstü → **File → Import** → `cards-N.txt` seç.
2. Header'lar sayesinde şunlar otomatik gelir: not tipi **Kotlin**, deste **Kotlin**, ayırıcı **Tab**, HTML açık. Not tipinin gerçekten **Kotlin** olduğunu bir teyit et (bazı sürümlerde `#notetype` header'ı göz ardı edilebiliyor).
3. **Field mapping:** **Ön → 2: Front**, **Geri → 3: Back**, **Tags → 4: Tags**. (1. sütun GUID hiçbir yüze gitmez.) Otomatik eşleşme bazen GUID'i ön yüze koyar; elle düzelt.
4. **Existing notes → Update** (tekrar import'ta günceller, çoğaltmaz). Eşleşme GUID üzerinden gider.
5. Import et. Kontrol: kod kutuları renkli mi, HTML kaçışları (`<`, `>`, `&&`) doğru mu, Türkçe karakterler bozulmamış mı, künye görünüyor mu.
6. AnkiWeb'e senkronla → telefona gider.

---

## 12. Dürüst kapsam notu

Bu belge **format ve spesifikasyonu** otomatikleştirir: kurallar her seferinde sıfırdan türetilmez, çıktı tutarlı olur. Ama asıl değerli kısım — hangi notun kart olacağına karar vermek, iyi bir gotcha önü kurgulamak, notlardaki hatayı fark etmek — yargı gerektirir ve bir script'e tam devredilemez. Yani sistem "Claude'suz üretim" değil; "Claude + temiz bağlam + hazır spesifikasyon"dur. (İleride, üretilen dosyanın biçimsel doğruluğunu —tırnak dengesi, sütun sayısı, GUID tekrarı, kaçış— denetleyen küçük bir doğrulama script'i eklenebilir; o ayrı ve opsiyonel bir adımdır.)