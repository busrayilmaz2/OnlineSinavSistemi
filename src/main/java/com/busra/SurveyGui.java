package com.busra;

import javax.swing.*;
import java.awt.*;

public class SurveyGui extends JFrame {

    private String kullaniciAdi = "Misafir";
    private String cevap1 = "";
    private String cevap2 = "";
    private String cevap3 = "";
    private String cevap4 = "";
    private String cevap5 = "";



    public SurveyGui() {
        setTitle("Çevrimiçi Sınav ve Anket Sistemi");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Çevrimiçi Sınav ve Anket Sistemi", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 24));

        JButton registerButton = new JButton("Kayıt Ol");
        JButton loginButton = new JButton("Giriş Yap");
        JButton surveyButton = new JButton("Anketi Çöz");
        JButton resultButton = new JButton("Sonuçları Görüntüle");
        JButton exitButton = new JButton("Çıkış");

        registerButton.addActionListener(e -> registerUser());
        loginButton.addActionListener(e -> loginUser());
        surveyButton.addActionListener(e -> openSurveyWindow());
        resultButton.addActionListener(e -> showResults());
        exitButton.addActionListener(e -> System.exit(0));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));
        panel.add(registerButton);
        panel.add(loginButton);
        panel.add(surveyButton);
        panel.add(resultButton);
        panel.add(exitButton);

        add(title, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    private void registerUser() {
        String name = JOptionPane.showInputDialog(this, "İsminizi giriniz:");
        String email = JOptionPane.showInputDialog(this, "E-posta adresinizi giriniz:");

        if (name != null && !name.trim().isEmpty()) {
            kullaniciAdi = name;
            JOptionPane.showMessageDialog(this,
                    "Kayıt başarılı!\nAd: " + name + "\nE-posta: " + email);
        }
    }

    private void loginUser() {
        String email = JOptionPane.showInputDialog(this, "E-posta adresinizi giriniz:");

        if (email != null && !email.trim().isEmpty()) {
            kullaniciAdi = email.split("@")[0];
            JOptionPane.showMessageDialog(this,
                    "Giriş başarılı!\nHoş geldin, " + kullaniciAdi);
        }
    }

    private void openSurveyWindow() {
        cevap1 = JOptionPane.showInputDialog(this,
                "Soru 1:\nHangi sınava girdiniz?");

        cevap2 = JOptionPane.showInputDialog(this,
                "Soru 2:\nSorun yaşadığınız ya da kontrol etmek istediğiniz özel bir durum var mı?");

        cevap3 = JOptionPane.showInputDialog(this,
                "Soru 3:\nProjedeki sınav ve anket zorluk derecesi sizce nasıldı? (Kolay / Orta / Zor)");
        cevap4 = JOptionPane.showInputDialog(this,
                "Soru 4:\nBina sınav sorumlusu ve diğer görevliler arasındaki koordinasyon verimli miydi?");
        cevap5 = JOptionPane.showInputDialog(this,
                "Soru 5:\nSınav sırasında adli bir vaka, kopya girişimi, sağlık problemi veya sistemsel bir kriz yaşandı mı?");

        JOptionPane.showMessageDialog(this,
                "Tüm anket yanıtlarınız başarıyla kaydedildi!\nTeşekkür ederiz, " + kullaniciAdi);
    }

    private void showResults() {
        JOptionPane.showMessageDialog(this,
                "Kaydedilen Anket Sonuçları:\n\n"
                        + "Kullanıcı: " + kullaniciAdi + "\n\n"
                        + "1. Cevap: " + cevap1 + "\n"
                        + "2. Cevap: " + cevap2 + "\n"
                        + "3. Cevap: " + cevap3 + "\n"
                + "4. Cevap: " + cevap4 + "\n"
                + "5. Cevap: " + cevap5,
                "Sonuçlar",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SurveyGui gui = new SurveyGui();
            gui.setVisible(true);
        });
    }
}