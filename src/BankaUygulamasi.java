import java.util.Scanner;

public class BankaUygulamasi
{

    public static void main(String[] args) throws InterruptedException {
            HesapYonetimi hesapBilgileri = new HesapYonetimi();
            hesapBilgileri.musteriYukle();
            Scanner scan = new Scanner(System.in);
            boolean devamMi = true;
            int islem,index=-1,vadeSuresi;
            double tutar,faizOrani;
            String sifre,adSoyad,TC;

            do
            {
                boolean kullaniciBulundu = false;
                System.out.println("********Hoşgeldiniz********");
                System.out.println("1-)Hesap Giriş\n2-)Hesap Oluşturma\n3-)Hesap Kapatma\n4-)Bütün Hesapları Göster\n0-)Kaydet ve Çık");
                System.out.print("Yapacağınız işlemi seçiniz:");
                islem = scan.nextInt();

                if(islem == 0)//Direkt 0'a basılıp programdan çıkılabilir
                {
                    System.out.println(">İyi günler dileriz...");
                    break;
                }

                switch (islem)
                {

                    case 1: //Hesaba Giriş aysun
                        int islem2;
                        System.out.print("1-)Vadeli hesap\n2-)Vadesiz hesap\n>");
                        islem2 = scan.nextInt();
                        if(islem2 == 1) //vadeli hesaba giriş
                        {
                            System.out.println("Hesabın bilgilerini gir:");
                            System.out.print("TC:");
                            TC = scan.next();
                            System.out.print("Şifre:");
                            sifre = scan.next();
                            for(VadeliHesapIslem hesap:HesapYonetimi.vadeliHesaplar)
                            {

                                if(hesap.getTC().equals(TC) && hesap.getSifre().equals(sifre))
                                {
                                    kullaniciBulundu = true;
                                    index = HesapYonetimi.vadeliHesaplar.indexOf(hesap);//bulunan hesabın indexi
                                    break;
                                }

                            }

                            if(kullaniciBulundu) //kullaniciBulundu==true ise burası çalışır.
                            {
                                boolean devamMi2 =true;
                                System.out.println("Vadeli hesaba giriş yapıldı");
                                Thread.sleep(1200);
                                while (devamMi2)
                                {
                                    System.out.println("-------------------------------------");
                                    System.out.println("1-)Para Yatır\n2-)Para Çek\n3-)Bakiye Sorgula\n4-)Vade Bitiş Tarihi\n5-)Faiz Hesapla\n6-)Şifre Değiştir\n7-)Ana Menüye Dön");
                                    System.out.println("-------------------------------------");
                                    System.out.print("Yapacağınız işlemi seçiniz:");
                                    int islem4 = scan.nextInt();
                                    switch (islem4)
                                    {
                                        case 1:
                                            System.out.print("Yatırılacak Tutar:");
                                            tutar = scan.nextDouble();
                                            HesapYonetimi.vadeliHesaplar.get(index).paraYatir(tutar);
                                            System.out.println(">Para Başarıyla Yatırıldı");
                                            Thread.sleep(1200);
                                            break;
                                        case 2:
                                            System.out.print("Çekilecek Tutar:");
                                            tutar = scan.nextDouble();
                                            HesapYonetimi.vadeliHesaplar.get(index).paraCek(tutar);
                                            Thread.sleep(1200);
                                            break;
                                        case 3:
                                            System.out.println(">Bakiye:" + HesapYonetimi.vadeliHesaplar.get(index).bakiyeSorgula());
                                            Thread.sleep(1200);
                                            break;
                                        case 4:
                                            System.out.println(HesapYonetimi.vadeliHesaplar.get(index).getVadeBitisTarihi());
                                            Thread.sleep(1200);
                                            break;
                                        case 5:
                                            HesapYonetimi.vadeliHesaplar.get(index).faizHesapla();
                                            Thread.sleep(1200);
                                            break;

                                        case 6:
                                            System.out.print("Yeni şifreyi yazınız: ");
                                            String yeniSifre = scan.next();
                                            HesapYonetimi.vadeliHesaplar.get(index).sifreDegistir(yeniSifre);
                                            Thread.sleep(1200);
                                            break;
                                        case 7:
                                            devamMi2 = false;
                                            Thread.sleep(1200);
                                            break;
                                    }
                                }
                            }

                            else //kullaniciBulundu==false ise burası çalışır
                            {
                                System.out.println("Böyle bir hesabınız bulunmamaktadır.");
                                Thread.sleep(1200);
                            }

                        }

                        else if (islem2 == 2)//Vadesiz hesaba giriş
                        {
                            System.out.println("Hesabın bilgilerini giriniz:");
                            System.out.print("TC:");
                            TC = scan.next();
                            System.out.print("Şifre:");
                            sifre = scan.next();
                            for(VadesizHesapIslem hesap:HesapYonetimi.vadesizHesaplar)
                            {

                                if(hesap.getTC().equals(TC) && hesap.getSifre().equals(sifre))
                                {
                                    kullaniciBulundu = true;
                                    index = HesapYonetimi.vadesizHesaplar.indexOf(hesap);//bulunan hesabın indexi
                                    break;
                                }

                            }

                            if(kullaniciBulundu) //kullaniciBulundu==true ise burası çalışır.
                            {
                                boolean devamMi3 =true;
                                System.out.println("Vadesiz hesaba giriş yapıldı");
                                Thread.sleep(1200);
                                while (devamMi3)
                                {
                                    System.out.println("-------------------------------------");
                                    System.out.println("1-)Para Yatır\n2-)Para Çek\n3-)Bakiye Sorgula\n4-)Para Aktar\n5-)Şifre değiştir\n6-)Ana Menüye Dön");
                                    System.out.println("-------------------------------------");
                                    System.out.print("Yapacağınız işlemi seçiniz:");
                                    int islem4 = scan.nextInt();
                                    switch (islem4)
                                    {
                                        case 1:
                                            System.out.print("Yatırılacak Tutar:");
                                            tutar = scan.nextDouble();
                                            HesapYonetimi.vadesizHesaplar.get(index).paraYatir(tutar);
                                            System.out.println("Para Başarıyla Yatırıldı");
                                            Thread.sleep(1200);
                                            break;
                                        case 2:
                                            System.out.print("Çekilecek Tutar:");
                                            tutar = scan.nextDouble();
                                            HesapYonetimi.vadesizHesaplar.get(index).paraCek(tutar);
                                            Thread.sleep(1200);
                                            break;
                                        case 3:
                                            System.out.println(">Bakiye:" + HesapYonetimi.vadesizHesaplar.get(index).bakiyeSorgula());
                                            Thread.sleep(1200);
                                            break;
                                        case 4:
                                            System.out.print("Göndermek istediğiniz IBAN: ");
                                            String IBAN = scan.next();
                                            System.out.print("Göndermek istediğiniz para miktarı: ");
                                            double para = scan.nextDouble();
                                            VadesizHesapIslem suankiKullanici = HesapYonetimi.vadesizHesaplar.get(index);
                                            int m = suankiKullanici.paraAktar(IBAN,para);
                                            String[] mesajlar = {"Bakiye yetersiz!","Para aktarımı gerçekleştirildi.","Geçersiz IBAN!"};
                                            System.out.println(mesajlar[m]);
                                            Thread.sleep(1200);
                                            break;
                                        case 5:
                                            System.out.println("Yeni şifreyi yazınız: ");
                                            String yeniSifre = scan.next();
                                            HesapYonetimi.vadesizHesaplar.get(index).sifreDegistir(yeniSifre);
                                            Thread.sleep(1200);
                                            break;
                                        case 6:
                                            devamMi3 = false;
                                            Thread.sleep(1200);
                                            break;
                                    }

                                }

                            }
                            else //kullaniciBulundu==false ise burası çalışır
                            {
                                System.out.println("Böyle bir hesabınız bulunmamaktadır.");
                                Thread.sleep(1200);
                            }

                        }
                        else{
                            System.out.println("Hatali giriş");
                            continue;
                        }

                    break;

                    case 2: //Hesap Oluşturma barış
                        int islem3;
                        System.out.print("1-)Vadeli hesap\n2-)Vadesiz hesap\n>");
                        islem3 = scan.nextInt();
                        if(islem3 == 1) // Vadeli hesap oluşturur
                        {
                            System.out.print("Ad-Soyad:");
                            adSoyad = scan.next();
                            System.out.print("TC:");
                            TC = scan.next();
                            System.out.print("Şifre:");
                            sifre = scan.next();
                            System.out.print("Vade süresini giriniz (yıl cinsinden): ");
                            vadeSuresi = scan.nextInt();
                            HesapYonetimi.vadeliHesapOlustur(adSoyad,TC,sifre,vadeSuresi,"");//vadeliHesapOluştur metodu static oldğundan nesne oluşturmadan

                            System.out.println(">Vadeli Hesap Başarılı Bir Şekilde Oluşturuldu");//sınıf ismiyle metoda parametreleri verdik
                            Thread.sleep(1200);
                        }

                        else if (islem3 == 2) //Vadesiz hesap oluşturur
                        {
                            System.out.print("Ad-Soyad:");
                            adSoyad = scan.next();
                            System.out.print("TC:");
                            TC = scan.next();
                            System.out.print("Şifre:");
                            sifre = scan.next();
                            HesapYonetimi.vadesizHesapOlustur(adSoyad,TC,sifre,"");//vadesizHesapOluştur metodu static oldğundan nesne oluşturmadan
                            System.out.println(">Vadesiz Hesap Başarılı Bir Şekilde Oluşturuldu");//sınıf ismiyle metoda parametreleri verdik
                            Thread.sleep(1200);
                        }

                        break;

                    case 3:// Hesap kapatma barış
                        System.out.println("1. Vadeli Hesap Kapat");
                        System.out.println("2. Vadesiz Hesap Kapat");
                        int i = scan.nextInt();
                        scan.nextLine();
                        if (i == 1) {
                            System.out.println("Hesap Bilgilerini Giriniz.");
                            System.out.println("TC: ");
                            TC = scan.nextLine();
                            System.out.println("Şifre: ");
                            sifre = scan.nextLine();
                            if(HesapYonetimi.vadeliHesapKapat(TC,sifre)){
                                System.out.println("Vadeli hesabınız başarıyla kapatılmıştır.");
                            }
                            else{
                                System.out.println("Müşteri bulunamadı!");
                            }
                        } else if (i == 2) {
                            System.out.println("Hesap Bilgilerini Giriniz.");
                            System.out.println("TC: ");
                            TC = scan.nextLine();
                            System.out.println("Şifre: ");
                            sifre = scan.nextLine();
                            if(HesapYonetimi.vadesizHesapKapat(TC, sifre)){
                                System.out.println("Vadesiz hesabınız başarıyla kaptılmıştır.");
                            }else{
                                System.out.println("Müşteri bulunamadı!");
                            }
                        }
                        break;
                    case 4: // barış
                        HesapYonetimi.tumHesaplariGoster();
                        break;
                    default:
                        System.out.println(">Hatalı giriş");

                }

            }while(true);
        hesapBilgileri.musteriYazdir();
    }

}