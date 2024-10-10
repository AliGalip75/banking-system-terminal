import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;
import java.text.SimpleDateFormat;
public class HesapYonetimi {

    static ArrayList<VadesizHesapIslem> vadesizHesaplar = new ArrayList<>();//hesapOluştur metodlarını static tanımladığımız için
    //hesap listelerini de static tanımlamamız gerekti
    static ArrayList<VadeliHesapIslem> vadeliHesaplar = new ArrayList<>();
    private static final String dateFormat = "MMM dd yyyy";
    public static void vadeliHesapOlustur(String adSoyad, String TC, String sifre, int vadeSuresi, String dosyaAdi) {
        vadeliHesaplar.add(new VadeliHesapIslem(adSoyad, TC, sifre, vadeSuresi));//VadeliHesapIslem sınıfından nesne(hesap)
    }

    public static void vadesizHesapOlustur(String adSoyad, String TC, String sifre, String dosyaAdi){
        vadesizHesaplar.add(new VadesizHesapIslem(adSoyad, TC, sifre));//VadesizHesapIslem sınıfından nesne(hesap) oluşturup vadesizHesaplar
        //ArrayList'ine atıyor.
    }

    public static boolean vadeliHesapKapat(String TC, String sifre) {// barış
        for (VadeliHesapIslem vadeli : vadeliHesaplar){
            if(vadeli.getTC().equals(TC) && vadeli.getSifre().equals(sifre)){
                vadeliHesaplar.remove(vadeli);
                return true;
            }
        }
        return false;
    }

    public static boolean vadesizHesapKapat(String TC, String sifre) { // barış
        for(int i = 0; i< vadesizHesaplar.size(); i++){
            VadesizHesapIslem vadesiz = vadesizHesaplar.get(i);
            if(vadesiz.getTC().equals(TC) && vadesiz.getSifre().equals(sifre)){
                vadesizHesaplar.remove(i);
                return true;
            }
        }
        return false;
    }
    public static void tumHesaplariGoster(){// barış
        System.out.println("Vadeli Hesaplar: ");
        System.out.println("-------------------");
        for (VadeliHesapIslem vadeliHesap : vadeliHesaplar) {
            System.out.println(vadeliHesap.toString());
            System.out.println();

        }
        System.out.println("Vadesiz Hesaplar: ");
        System.out.println("--------------------");
        for (VadesizHesapIslem vadesizHesap : vadesizHesaplar){
            System.out.println(vadesizHesap.toString());
            System.out.println();
        }}


    public void musteriYazdir() { //barış
        try {
            PrintWriter yazici = new PrintWriter(new FileWriter("vadeliHesap.txt"));
            for(int i = 0; i < vadeliHesaplar.size(); i++){
                VadeliHesapIslem hesap = vadeliHesaplar.get(i);
                Date vadeBitis = hesap.getVadeBitisTarihi();
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                String tarih = sdf.format(vadeBitis);

                String tmp = hesap.getAdSoyad() + "," + hesap.getTC() + "," + hesap.getSifre() + ","
                + hesap.getVadeSuresi() + "," + tarih  + "," + hesap.getBakiye();
                yazici.println(tmp);
            }
            yazici.close();
        } catch (IOException e) {
            System.out.println("Vadeli hesap yazma sırasında hata olustu!");
            throw new RuntimeException(e);
        }
        try {
            PrintWriter yazici = new PrintWriter(new FileWriter("vadesizHesap.txt"));
            for(VadesizHesapIslem hesap : vadesizHesaplar){
                String tmp = hesap.getAdSoyad() + "," + hesap.getTC() +
                        "," + hesap.getSifre() + "," + hesap.getBakiye();
                yazici.println(tmp);
            }
            yazici.close();
        }catch (IOException e) {
            System.out.println("Vadesiz hesap yazma sırasında hata olustu!");
            throw new RuntimeException(e);
        }
    }
    public void musteriYukle() { // aysun
        try (FileReader fr = new FileReader("vadeliHesap.txt")) {
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parametreler = line.split(",");
                String adSoyad = parametreler[0];
                String TC = parametreler[1];
                String sifre = parametreler[2];
                int vadeSuresi = Integer.parseInt(parametreler[3]);
                String tarih = parametreler[4];
                double bakiye = Double.parseDouble(parametreler[5]);
                SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                Date vadeBitis = null;
                try {
                    vadeBitis = sdf.parse(tarih);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                VadeliHesapIslem vadeliHesap = new VadeliHesapIslem(adSoyad, TC, sifre, vadeSuresi, vadeBitis,bakiye);
                vadeliHesaplar.add(vadeliHesap);
            }
        }catch (IOException e){
            System.out.println("Hata");
        }
        try(FileReader fr = new FileReader("vadesizHesap.txt")){
            BufferedReader br = new BufferedReader(fr);
            String line = null;
            while ((line = br.readLine()) != null){
                String[] parametreler = line.split(",");
                String adSoyad = parametreler[0];
                String TC = parametreler[1];
                String sifre = parametreler[2];
                double bakiye = Double.parseDouble(parametreler[3]);
                VadesizHesapIslem vadesizHesap = new VadesizHesapIslem(adSoyad,TC,sifre,bakiye);
                vadesizHesaplar.add(vadesizHesap);
            }
        }catch(IOException e){
            System.out.println("Hata");
        }
    }
}