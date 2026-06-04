package com.busra;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String loggedInUser = null;

        while (true) {
            if (loggedInUser == null) {
                System.out.println("\n===========================================");
                System.out.println("ÇEVRİMİÇİ SINAV VE ANKET SİSTEMİNE HOŞ GELDİNİZ");
                System.out.println("===========================================");
                System.out.println("1. Kayıt Ol");
                System.out.println("2. Giriş Yap");
                System.out.println("3. Sistemden Çıkış");
                System.out.print("Seçiminiz: ");

                String choiceStr = scanner.nextLine();

                if (choiceStr.equals("1")) {
                    System.out.print("İsminiz: ");
                    String name = scanner.nextLine();
                    System.out.print("E-posta Adresiniz: ");
                    String email = scanner.nextLine();
                    System.out.print("Rolünüz (Öğretmen için 'TEACHER', Öğrenci için 'STUDENT'): ");
                    String role = scanner.nextLine();

                    System.out.println("=> [" + name + "] Başarıyla sisteme kaydedildi! Şimdi giriş yapabilirsiniz.");

                } else if (choiceStr.equals("2")) {
                    System.out.print("E-posta Adresiniz: ");
                    String email = scanner.nextLine();

                    // Giriş simülasyonu - Girilen e-posta ile oturum açıyoruz
                    loggedInUser = email.split("@")[0];
                    System.out.println("=> Giriş Başarılı! Hoş geldin, " + loggedInUser);

                } else if (choiceStr.equals("3")) {
                    System.out.println("=> Sistem kapatılıyor. İyi günler kanka!");
                    break;
                } else {
                    System.out.println("Geçersiz seçim!");
                }
            } else {
                // Kullanıcı Giriş Yapmışsa Gösterilecek Menü
                System.out.println("\n-------------------------------------------");
                System.out.println("AKTİF KULLANICI: " + loggedInUser);
                System.out.println("-------------------------------------------");
                System.out.println("1. Sınav/Anketleri Listele ve Çöz");
                System.out.println("2. Oturumu Kapat");
                System.out.print("Seçiminiz: ");

                String choiceStr = scanner.nextLine();

                if (choiceStr.equals("1")) {
                    System.out.println("\n=== AKTİF ANKET: Renk Tabanlı Nesne Takibi Literatür Anketi ===");

                    System.out.println("\n[Soru 1] İncelediğiniz 10 akademik makale içinde renk tabanlı nesne takibinde en başarılı algoritma hangisiydi?");
                    System.out.print("Cevabınız: ");
                    scanner.nextLine();

                    System.out.println("\n[Soru 2] Görüntü işlemede arka plan gürültüsünü (noise) azaltmak için hangi filtreleme yöntemini kullandınız?");
                    System.out.print("Cevabınız: ");
                    scanner.nextLine();

                    System.out.println("\n[Soru 3] Projedeki sınav ve anket zorluk derecesi sizce nasıldı? (Kolay/Orta/Zor)");
                    System.out.print("Cevabınız: ");
                    scanner.nextLine();

                    System.out.println("\n=======================================================");
                    System.out.println("=> Tüm anket yanıtlarınız başarıyla yerel simülasyona kaydedildi!");
                    System.out.println("=> Katkılarınız için teşekkür ederiz, " + loggedInUser + ".");
                    System.out.println("=======================================================");

                    System.out.println("=> Oturumunuz güvenli bir şekilde kapatıldı. Menüye dönülüyor...\n");
                    loggedInUser = null;

                } else if (choiceStr.equals("2")) {
                    System.out.println("=> Oturum kapatıldı.");
                    loggedInUser = null;
                } else {
                    System.out.println("Geçersiz seçim!");
                }
            }
        }
        scanner.close();
    }
}