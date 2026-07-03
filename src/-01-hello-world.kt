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