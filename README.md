# OybakOtel 🏨🗝️

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![SQLite](https://img.shields.io/badge/sqlite-%2307405e.svg?style=for-the-badge&logo=sqlite&logoColor=white)
![Maven](https://img.shields.io/badge/apache_maven-%23C71A36.svg?style=for-the-badge&logo=apachemaven&logoColor=white)

**OybakOtel**, profesyonel otel işletmeciliği için geliştirilmiş, Nesne Yönelimli Programlama (OOP) prensiplerini temel alan kapsamlı bir masaüstü uygulamasıdır. Rezervasyon yönetiminden teknik bakım takibine, gelişmiş oda haritalandırmasından finansal raporlamaya kadar bir otelin tüm operasyonel ihtiyaçlarını modern ve sadeleştirilmiş tek bir platformda birleştirir.

## 🌟 Öne Çıkan Özellikler

### 👥 Çoklu Kullanıcı Rolleri

* **Yönetim:** Merkezi panel üzerinden personel ekleme/çıkarma, maaş güncellemeleri, geçmiş müşteri kayıtlarının analizi ve sistemin genel kontrolünü sağlar.
* **Resepsiyon:** Görsel oda haritası üzerinden müşteri girişi (Check-in), ödeme tahsilatı ve müşteri çıkış (Check-out) işlemlerini yönetir.
* **Teknik Ekip:** Odaların teknik durum takibi, arızalı odaları bakıma alma ve bakım sürecini sonlandırma operasyonlarını yürütür.

### 🖼️ Görsel Oda Yönetimi

* **Dinamik Oda Haritası:** Odaların durumunu `OdaDurumu` enum yapısına göre renk kodlarıyla (**Yeşil:** Müsait, **Kırmızı:** Dolu, **Sarı:** Bakımda) anlık olarak gösterir.
* **Akıllı Filtreleme:** Kapasiteye göre (1-4 kişilik) gerçek zamanlı hızlı buton filtreleme sistemi.
* **HTML Detay Sunumu:** Her odanın teknik özellikleri veritabanından çekilerek şık bir arayüz ile listelenir.

### 🛡️ Güvenlik ve Validasyon

* **Kimlik Doğrulama:** Algoritmik TC No kontrolü ve veritabanı üzerinden mükerrer kayıt engelleme sistemi.
* **Tarih Kontrolü:** Geçmiş tarihe rezervasyon yapılmasını engelleyen otomatik validasyon.
* **Sistem Logları:** Personel tarafından yapılan tüm kritik işlemler tarih ve saat damgasıyla veritabanına kaydedilir.

## 🛠️ Kullanılan Teknolojiler

* **Programlama Dili:** Java
* **Kullanıcı Arayüzü:** Java Swing
* **Veritabanı:** SQLite (JDBC Driver ile)
* **Proje Yönetimi:** Maven

## 🚀 Kurulum ve Çalıştırma

Projeyi yerel bilgisayarınızda çalıştırmak için aşağıdaki adımları izleyin:

### Ön Koşullar

* Bilgisayarınızda **Java Development Kit (JDK) 25** yüklü olmalıdır.
* **Maven** yüklü olmalıdır.

Markdown
### Adımlar

1. **Projeyi klonlayın:**
```bash
git clone https://github.com/yunusemre/OybakOtel.git
Proje dizinine gidin:

Bash
cd OybakOtel
Bağımlılıkları yükleyin, projeyi derleyin ve uygulamayı başlatın:

Bash
mvn clean compile exec:java -Dexec.mainClass="com.oybakotel.GUI.GirisSayfasi"
(Not: Uygulama direkt olarak GirisSayfasi.java üzerinden başlatılacaktır. Sisteme test girişi yapmak için aşağıdaki hesapları kullanabilirsiniz:)

Resepsiyon: TC 12345678916 | Şifre 123

Teknik Ekip: TC 12345678938 | Şifre 123

Yönetim: TC 12345678950 | Şifre 123

📂 Proje Mimarisi
com.oybak.otel : Temel veri modelleri (Musteri, Oda, Personel) ve iş mantığı servisleri.

com.oybakotel.GUI : Kullanıcı arayüzü bileşenleri. Uygulamanın merkezi giriş noktası GirisSayfasi.java dosyasıdır.

VeriTabani.java : SQLite veritabanı işlemleri ve loglama kayıtlarının tutulduğu arayüz.

Resepsiyon.java : OOP standartlarına uygun, müşteri nesnesi üzerinden işlem yapan resepsiyon servisleri.
'

com.oybak.otel.enums : Sistemin tip güvenliğini sağlayan roller ve durum bildirimleri.

OybakOtel ile otel yönetiminde profesyonelliği yakala! 🏆
