Otel Yönetim Sistemi (OybakOtel) 🏨🗝️
OybakOtel, profesyonel otel işletmeciliği için geliştirilmiş, Nesne Yönelimli Programlama (OOP) prensiplerini temel alan kapsamlı bir masaüstü uygulamasıdır. Rezervasyon yönetiminden teknik bakım takibine, gelişmiş oda haritalandırmasından finansal raporlamaya kadar bir otelin tüm operasyonel ihtiyaçlarını modern ve modüler bir yapıda birleştirir.

🌟 Öne Çıkan Özellikler
👥 Çoklu Kullanıcı Rolleri ve Yetkilendirme
Yönetim (Yönetici): Personel ekleme/çıkarma, maaş güncellemeleri, geçmiş müşteri kayıtlarının analizi ve oda özelliklerinin dinamik yönetimi.

Resepsiyon: Görsel oda haritası üzerinden müşteri girişi (Check-in), ödeme tahsilatı ve müşteri çıkış (Check-out) işlemleri.

Teknik Ekip: Odaların teknik durum takibi, arızalı odaları bakıma alma ve bakım sürecini sonlandırma yetkisi.

🖼️ Görsel Oda Yönetimi
Dinamik Oda Haritası: Odaların durumunu OdaDurumu enum yapısına göre renk kodlarıyla (Yeşil: Müsait, Kırmızı: Dolu, Sarı: Bakımda) anlık olarak gösterir.

Akıllı Filtreleme: Oda kapasitesine göre (1-4 kişilik) gerçek zamanlı buton filtreleme sistemi.

HTML Detay Gösterimi: Her odanın teknik özellikleri (Jakuzi, balkon, manzara vb.) veritabanından çekilerek şık bir HTML formatında kullanıcıya sunulur.

🛡️ Güvenlik ve Veri Doğrulaması
TC Kimlik Doğrulama: Algoritmik TC No kontrolü ve veritabanı üzerinden mükerrer kayıt engelleme sistemi.

Tarih Kontrolü: Geçmiş tarihe rezervasyon yapılmasını ve mantıksal tarih hatalarını (çıkış < giriş) engelleyen otomatik validasyon.

Loglama Sistemi: Tüm personel aksiyonlarını (oda boşaltma, kayıt ekleme vb.) tarih ve saat damgasıyla log_kayitlar tablosuna işler.

🛠️ Kullanılan Teknolojiler
Programlama Dili: Java (JDK 25)

Kullanıcı Arayüzü: Java Swing & AWT

Veritabanı: SQLite (JDBC Driver ile)

Tarih Yönetimi: JCalendar / JDateChooser kütüphanesi

Mimari: OOP (Interface, Abstract Classes, Inheritance, Polymorphism)

📂 Proje Mimarisi
com.oybak.otel: Temel veri modelleri (Musteri, Oda, Personel) ve ana iş mantığı.

com.oybak.otel.enums: OdaDurumu, UserRole, OdaOzelligi gibi tip güvenliğini sağlayan enum yapıları.

com.oybakotel.GUI & com.oybak.otel.GUIResepsiyon: Kullanıcı dostu arayüz bileşenleri.

VeriTabani.java: Veritabanı sorgularının ve loglama sisteminin yönetildiği merkezi Interface.

Resepsiyon.java: OOP prensiplerine uygun, nesne tabanlı müşteri ekleme ve çıkarma servisleri.

🚀 Kurulum ve Çalıştırma
Ön Koşullar
Bilgisayarınızda Java Development Kit (JDK) 25 yüklü olmalıdır.

Maven veya projenin bağımlılıklarını yöneten bir yapı (JDBC SQLite Driver) yüklü olmalıdır.

Adımlar
Projeyi klonlayın:

Bash
git clone https://github.com/yunusemre/OtelYonetimSistemi.git
Veritabanı Dosyası:
veritabani_dosyan.db dosyasının projenin ana dizininde olduğundan emin olun.

Çalıştırın:
IDE'niz (NetBeans/IntelliJ) üzerinden Main sınıfını veya giriş ekranını (GirisSayfasi.java) başlatın.

📊 Yazılım Tasarımı
Proje geliştirilirken Nesne Yönelimli Tasarım kurallarına titizlikle uyulmuştur:

Kalıtım (Inheritance): Person soyut sınıfından türetilen Musteri ve Personel sınıfları.

Arayüzler (Interfaces): VeriTabani, Hatalar, OdaSecim gibi yapılarla kodun modülerliği artırılmıştır.

Kapsülleme (Encapsulation): Tüm sınıf verileri private erişim belirleyicileri ve getter/setter metotları ile korunmaktadır.
