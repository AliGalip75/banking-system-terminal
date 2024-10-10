import java.util.Calendar;
import java.util.Date;

public class VadeliHesapIslem extends Musteri implements IHesapIslem  //İmplement edilen class'daki metotları override ettik
{
    private final int vadeSuresi;
    private Date vadeBitisTarihi;
    private final double faizOrani = 5;
    public VadeliHesapIslem(String adSoyad,String TC,String sifre,int vadeSuresi)
    {
        super(adSoyad, TC, sifre);
        this.vadeSuresi = vadeSuresi;
        this.vadeBitisTarihi = getVadeSonuTarihi();
    }
 // alttaki constructor ı yazma sebebimiz, vade bitiş tarihinin her seferinde tekrar hesaplanmaması için
    public VadeliHesapIslem(String adSoyad,String TC,String sifre,int vadeSuresi, Date vadeBitis, double bakiye)
    {
        super(adSoyad, TC, sifre);
        this.vadeSuresi = vadeSuresi;
        this.vadeBitisTarihi = vadeBitis;
        setBakiye(bakiye);
    }

    public Date getVadeSonuTarihi() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,vadeSuresi);
        return calendar.getTime();
    }

    public Date getVadeBitisTarihi(){ return  vadeBitisTarihi;}
    public double getFaizOrani() {
        return faizOrani;
    }

    @Override
    public void paraYatir(double miktar)
    {
        setBakiye(getBakiye() + miktar);
    }

    @Override
    public void paraCek(double miktar)
    {
        if (miktar <= getBakiye()) {
            setBakiye(getBakiye() - miktar);
            System.out.println(miktar + " TL çekildi.");
        } else {
            System.out.println("Yetersiz bakiye...");
        }
    }

    @Override
    public double bakiyeSorgula()
    {
        return getBakiye();
    }

    @Override
    public void sifreDegistir(String yeniSifre) {
        setSifre(yeniSifre);
        System.out.println("Şifre değiştirme işlemi başarıyla tamamlandı.");
    }
    public void faizHesapla()
    {
        double birikenFaiz = getBakiye() * (faizOrani / 100);
        double yeniBakiye = getBakiye() + birikenFaiz;
        System.out.println("Biriken Faiz: " + birikenFaiz + " TL/Vade Sonu Bakiye: " + yeniBakiye + " TL");
    }

    public int getVadeSuresi() {
        return vadeSuresi;
    }

    public String toString(){ //aysun
        return "Ad Soyad: " + this.getAdSoyad() +
                "\nTC: " + this.getTC() +
                "\nBakiye: " + this.bakiyeSorgula() +
                "\nVade Sonu Tarihi: " + this.getVadeSonuTarihi() +
                "\nFaiz Oranı: " + this.getFaizOrani() +
                "\nIBAN: " + this.getIBAN();
    }
}