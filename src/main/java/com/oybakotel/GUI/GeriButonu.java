/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.oybakotel.GUI;

import com.oybak.otel.GUIResepsiyon.ResepsiyonSayfa;
import com.oybak.otel.GUITeknikEkip.TeknikPersonelSayfasi;
import com.oybak.otel.GUIYonetim.YonetimEkran;
import com.oybak.otel.Personel;
import com.oybak.otel.enums.UserRole;
import static com.oybak.otel.enums.UserRole.BAKIM;
import static com.oybak.otel.enums.UserRole.GMUSTERIARAMA;
import static com.oybak.otel.enums.UserRole.MUSTERI;
import static com.oybak.otel.enums.UserRole.MUSTERIEKLEME;
import static com.oybak.otel.enums.UserRole.MUSTERICIKARMA;
import static com.oybak.otel.enums.UserRole.RESEPSIYON;
import static com.oybak.otel.enums.UserRole.TEKNIKPERSONEL;
import static com.oybak.otel.enums.UserRole.YONETIM;
import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author ahmet
 */
public interface GeriButonu {
    public default void geriButonu(Personel p){
        //switch-case yapısı ile karmaşık if bloklarının önüne geçerek konunn okunaklığını ve bakımını kolaylaştırıyoz
        switch(p.getIsTipi()){
            //iş tipi musteri ise geri butonları anasayfaya atar
            case  MUSTERI->{ 
                JOptionPane.showMessageDialog((Component) this, "Anasayfaya aktarılıyorsunuz.");
                Anasayfa t = new Anasayfa();
                t.setLocationRelativeTo(null);
                t.setVisible(true);                
            }
            
            //iş tipi resepsiyon ve onun alt rolleri ise geri butonları resepsiyon anasayfaya atar
            case  RESEPSIYON, MUSTERIEKLEME, MUSTERICIKARMA->{ 
                p.setIsTipi(UserRole.RESEPSIYON);
                JOptionPane.showMessageDialog((Component) this, "Resepsiyon anasayfasına aktarılıyorsunuz.");
                ResepsiyonSayfa r = new ResepsiyonSayfa(p);
                r.setLocationRelativeTo(null);
                r.setVisible(true);
            }
            
            //iş tipi yönetim ve onun alt rolleri ise geri butonları yönetim anasayfaya atar
            case  YONETIM, GMUSTERIARAMA->{ 
                p.setIsTipi(UserRole.YONETIM);
                JOptionPane.showMessageDialog((Component) this, "Yonetim anasayfasına aktarılıyorsunuz.");
                YonetimEkran y = new YonetimEkran(p);
                y.setLocationRelativeTo(null);
                y.setVisible(true);
            }
            
            //iş tipi teknikpersonel ve onun alt rolleri ise geri butonları teknikpersonel anasayfaya atar
            case  TEKNIKPERSONEL, BAKIM->{ 
                JOptionPane.showMessageDialog((Component) this, "Teknik personel anasayfayasına aktarılıyorsunuz.");
                TeknikPersonelSayfasi t = new TeknikPersonelSayfasi(p);
                t.setLocationRelativeTo(null);
                t.setVisible(true);
            }
        }
    }
}
