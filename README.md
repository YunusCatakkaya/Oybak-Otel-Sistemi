# OybakOtel Yönetim Sistemi 🏨🗝️

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![SQLite](https://img.shields.io/badge/sqlite-%2307405e.svg?style=for-the-badge&logo=sqlite&logoColor=white)
![NetBeans](https://img.shields.io/badge/Apache_NetBeans-1B6AC6.svg?style=for-the-badge&logo=apache-netbeans-ide&logoColor=white)

**OybakOtel**, modern otel işletmeciliği için geliştirilmiş, Nesne Yönelimli Programlama (OOP) prensiplerini temel alan kapsamlı bir masaüstü uygulamasıdır. Rezervasyon yönetiminden teknik bakım takibine, gelişmiş oda haritalandırmasından finansal raporlamaya kadar bir otelin tüm operasyonel ihtiyaçlarını modern ve modüler tek bir platformda birleştirir.

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
* **Tarih Kontrolü:** Geçmiş tarihe rezervasyon yapılmasını engelleyen otomatik validasyon (`JDateChooser` entegrasyonu).
* **Sistem Logları:** Personel tarafından yapılan tüm kritik işlemler tarih ve saat damgasıyla veritabanına kaydedilir.

## 🛠️ Kullanılan Teknolojiler

* **Programlama Dili:** Java
* **Kullanıcı Arayüzü:** Java Swing
* **Veritabanı:** SQLite (JDBC Driver ile)
* **Tarih Yönetimi:** JCalendar / JDateChooser
* **Geliştirme Ortamı (IDE):** Apache NetBeans IDE 29
* **Mimari:** Nesne Yönelimli Programlama (Arayüzler, Soyut Sınıflar, Kalıtım, Polimorfizm)

## 🚀 Kurulum ve Çalıştırma

Projeyi yerel bilgisayarınızda çalıştırmak için aşağıdaki adımları izleyin:

### 📋 Ön Koşullar

* Projeyi sorunsuz açmak için **Apache NetBeans IDE 29** kurulu olması önerilir.

### 🛠️ Ekstra Araçlar (Tercihe Bağlı)

* Veritabanı tablolarını aktif olarak görüntülemek ve yönetmek için **[DB Browser for SQLite](https://sqlitebrowser.org/)** programını bilgisayarınıza kurabilirsiniz.

### 🏁 Adımlar

**1. Projeyi klonlayın veya indirin:**
`git clone https://github.com/yunusemre/OybakOtel.git`

**2. Projeyi NetBeans ile Açın:**
* **Apache NetBeans IDE 29** programını başlatın.
* Üst menüden `File` > `Open Project` yolunu izleyerek bilgisayarınıza indirdiğiniz **OybakOtel** klasörünü seçip açın.

**3. Uygulamayı Başlatın:**
* Proje yüklendikten sonra, sol taraftaki *Projects* menüsünden `com.oybakotel.GUI` paketinin altındaki `Anasayfa.java` dosyasına sağ tıklayın.
* **Run File** (Dosyayı Çalıştır) seçeneğine tıklayarak projeyi doğrudan başlatabilirsiniz.

## 🔐 Test Bilgileri (Giriş Hesapları)

Sisteme hızlıca test girişi yapmak için aşağıdaki hazır hesapları kullanabilirsiniz:

* **Resepsiyon:** TC `12345678916` | Şifre `123`
* **Teknik Ekip:** TC `12345678938` | Şifre `123`
* **Yönetim:** TC `12345678950` | Şifre `123`

## 📂 Proje Mimarisi

* `com.oybak.otel` : Temel veri modelleri (Musteri, Oda, Personel) ve iş mantığı servisleri.
* `com.oybakotel.GUI` : Kullanıcı arayüzü bileşenleri. Uygulamanın merkezi giriş noktası `Anasayfa.java` dosyasıdır.
* `VeriTabani.java` : SQLite veritabanı işlemleri ve loglama kayıtlarının tutulduğu arayüz.
* `Resepsiyon.java` : OOP standartlarına uygun, müşteri nesnesi üzerinden işlem yapan resepsiyon servisleri.
* `com.oybak.otel.enums` : Sistemin tip güvenliğini sağlayan roller ve durum bildirimleri.

---

*OybakOtel ile otel yönetiminde profesyonelliği yakala!* 🏆
