package com.busra;

import model.Assessment;
import model.User;
import service.AssessmentService;
import service.UserService;

import java.util.List;
import java.util.Scanner;

public class App { // Sınıf ismini App olarak güncelledik kanka, burası çok önemli!
    public static void main(String[] args) {
        UserService userService = new UserService();
        AssessmentService assessmentService = new AssessmentService();
        Scanner scanner = new Scanner(System.in);

        User loggedInUser = null;

        System.out.println("===============================================");
        System.out.println("   ÇEVRİMİÇİ SINAV VE ANKET SİSTEMİNE HOŞ GELDİNİZ   ");
        System.out.println("===============================================");

        while (true) {
            if (loggedInUser == null) {
                // Giriş Yapılmamışsa Gösterilecek Menü
                System.out.println("\n1. Kayıt Ol");
                System.out.println("2. Giriş Yap");
                System.out.println("3. Sistemden Çıkış");
                System.out.print("Seçiminiz: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Boşluk temizleme

                if (choice == 1) {
                    System.out.print("Adınız: ");
                    String name = scanner.nextLine();
                    System.out.print("E-posta Adresiniz: ");
                    String email = scanner.nextLine();
                    System.out.print("Rolünüz (Öğretmen için 'TEACHER', Öğrenci için 'STUDENT'): ");
                    String role = scanner.nextLine();

                    userService.registerUser(name, email, role);
                } else if (choice == 2) {
                    System.out.print("Giriş için E-posta Adresiniz: ");
                    String email = scanner.nextLine();
                    loggedInUser = userService.loginUser(email);
                } else if (choice == 3) {
                    System.out.println("Sistem kapatılıyor. İyi günler!");
                    break;
                } else {
                    System.out.println("Geçersiz seçim! Lütfen tekrar deneyin.");
                }
            } else {
                // Giriş Yapılmışsa Gösterilecek Ana Menü
                System.out.println("\n--- ANA MENÜ (Aktif Kullanıcı: " + loggedInUser.getName() + " [" + loggedInUser.getRole() + "]) ---");
                System.out.println("1. Mevcut Sınav/Anketleri Listele");

                // Rol Tabanlı Yetkilendirme (Öğretmen Özel Alanı)
                if (loggedInUser.getRole().equalsIgnoreCase("TEACHER")) {
                    System.out.println("2. Yeni Sınav/Anket Oluştur [Öğretmen Özel]");
                }

                System.out.println("3. Oturumu Kapat (Çıkış Yap)");
                System.out.print("Seçiminiz: ");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Boşluk temizleme

                if (choice == 1) {
                    List<Assessment> assessments = assessmentService.getAllAssessments();
                    if (assessments.isEmpty()) {
                        System.out.println("=> Sistemde henüz hiç sınav veya anket bulunmuyor.");
                    } else {
                        System.out.println("\n--- SİSTEMDEKİ SINAVLAR VE ANKETLER ---");
                        for (Assessment a : assessments) {
                            System.out.println("[" + a.getType() + "] ID: " + a.getId() + " - Başlık: " + a.getTitle());
                        }
                    }
                } else if (choice == 2 && loggedInUser.getRole().equalsIgnoreCase("TEACHER")) {
                    System.out.print("Sınav/Anket Başlığı: ");
                    String title = scanner.nextLine();
                    System.out.print("Tipi ('EXAM' veya 'SURVEY'): ");
                    String type = scanner.nextLine();

                    assessmentService.createAssessment(title, type, loggedInUser.getId());
                } else if (choice == 3) {
                    System.out.println("=> Oturum kapatıldı.");
                    loggedInUser = null;
                } else {
                    System.out.println("Geçersiz seçim veya yetkisiz işlem!");
                }
            }
        }
        scanner.close();
    }
}