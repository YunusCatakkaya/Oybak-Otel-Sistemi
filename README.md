OybakOtel Yönetim Sistemi 🏨🗝️
OybakOtel, modern otel işletmeciliği için geliştirilmiş, Nesne Yönelimli Programlama (OOP) prensiplerini temel alan kapsamlı bir masaüstü uygulamasıdır. Rezervasyon yönetiminden teknik bakım takibine, gelişmiş oda haritalandırmasından finansal raporlamaya kadar bir otelin tüm operasyonel ihtiyaçlarını modern ve modüler tek bir platformda birleştirir.

🌟 Öne Çıkan Özellikler
👥 Çoklu Kullanıcı Rolleri
Yönetim: Merkezi panel üzerinden personel ekleme/çıkarma, maaş güncellemeleri, geçmiş müşteri kayıtlarının analizi ve sistemin genel kontrolü.

Resepsiyon: Görsel oda haritası üzerinden müşteri girişi (Check-in), ödeme tahsilatı ve müşteri çıkış (Check-out) işlemleri.

Teknik Ekip: Odaların teknik durum takibi, arızalı odaları bakıma alma ve bakım sürecini sonlandırma operasyonları.

🖼️ Görsel Oda Yönetimi
Dinamik Oda Haritası: Odaların durumunu OdaDurumu enum yapısına göre renk kodlarıyla (Yeşil: Müsait, Kırmızı: Dolu, Sarı: Bakımda) anlık olarak gösterir.

Akıllı Filtreleme: Kapasiteye göre (1-4 kişilik) gerçek zamanlı hızlı buton filtreleme sistemi.

HTML Detay Sunumu: Her odanın teknik özellikleri veritabanından çekilerek şık bir arayüz ile listelenir.

🛡️ Güvenlik ve Validasyon
Kimlik Doğrulama: Algoritmik TC No kontrolü ve veritabanı üzerinden mükerrer kayıt engelleme sistemi.

Tarih Kontrolü: Geçmiş tarihe rezervasyon yapılmasını engelleyen otomatik validasyon (JDateChooser entegrasyonu).

Sistem Logları: Personel tarafından yapılan tüm kritik işlemler tarih ve saat damgasıyla veritabanına kaydedilir.

🛠️ Kullanılan Teknolojiler
Programlama Dili: Java (JDK 25)

Kullanıcı Arayüzü: Java Swing

Veritabanı: SQLite (JDBC Driver ile)

Proje Yönetimi: Maven

Mimari: Nesne Yönelimli Programlama (Arayüzler, Soyut Sınıflar, Kalıtım)

🚀 Kurulum ve Çalıştırma
Projeyi yerel bilgisayarınızda çalıştırmak için aşağıdaki adımları izleyin:

Adımlar
Projeyi klonlayın:

Bash
git clone https://github.com/yunusemre/OybakOtel.git
Proje dizinine gidin:

Bash
cd OybakOtel
Uygulamayı başlatın:
Bağımlılıkları yüklemek ve projeyi doğrudan Giriş Sayfası (Ana Sayfa) üzerinden başlatmak için terminale şu komutu girin:

Bash
mvn clean compile exec:java -Dexec.mainClass="com.oybakotel.GUI.GirisSayfasi"

Ön Koşullar
Bilgisayarınızda Java Development Kit (JDK) 25 yüklü olmalıdır.

Maven yüklü olmalıdır.
