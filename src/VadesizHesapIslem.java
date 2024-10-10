public class VadesizHesapIslem extends Musteri implements IHesapIslem //İmplement edilen interface'deki metotları override ettik
{
    public VadesizHesapIslem(String adSoyad,String TC,String sifre)
    {
        super(adSoyad, TC, sifre);
    }
    public VadesizHesapIslem(String adSoyad,String TC,String sifre,double bakiye)
    {
        super(adSoyad, TC, sifre);
        setBakiye(bakiye);
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
    public int paraAktar(String IBAN, double para ){
        if(getBakiye() < para){
            return 0;
        }
        // vadesiz hesaplarda arıyor
        for(int i = 0; i < HesapYonetimi.vadesizHesaplar.size(); i++){
            if(HesapYonetimi.vadesizHesaplar.get(i).getIBAN().equals(IBAN)){
                HesapYonetimi.vadesizHesaplar.get(i).setBakiye(HesapYonetimi.vadesizHesaplar.get(i).getBakiye()+para);
                setBakiye(getBakiye() - para);
                return 1;
            }
        }
        //vadeli hesaplarda arıyor
        for(int i = 0; i < HesapYonetimi.vadeliHesaplar.size(); i++){
            if(HesapYonetimi.vadeliHesaplar.get(i).getIBAN().equals(IBAN)){
                HesapYonetimi.vadeliHesaplar.get(i).setBakiye(HesapYonetimi.vadeliHesaplar.get(i).getBakiye()+para);
                setBakiye(getBakiye() - para);
                return 1;
            }
        }
        return 2;
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
    public String toString(){
     return "Ad Soyad: " + this.getAdSoyad() +
             "\nTC: " + this.getTC() +
             "\nBakiye: " + this.bakiyeSorgula() +
             "\nIBAN: " + this.getIBAN();
}}
