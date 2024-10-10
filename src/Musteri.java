import java.util.Random;
public abstract class Musteri //Müsterinin özellikleri,nesne oluşturmamıza gerek olmadığı için abstarct
{
    public Musteri(){

    }
    public Musteri(String adSoyad, String TC, String sifre){
        Random r = new Random();
        this.adSoyad = adSoyad;
        this.TC = TC;
        this.sifre= sifre;
        this.IBAN = "TR" + (HesapYonetimi.vadeliHesaplar.size() + HesapYonetimi.vadesizHesaplar.size());
        for (int i = 0; i < 4; i++) {
            this.IBAN = this.IBAN + r.nextInt(10);
        }
    }
    private String adSoyad;
    private String TC;
    private String sifre;
    private String IBAN;
    private double bakiye = 0;

    public void setAdSoyad(String adSoyad)
    {
        this.adSoyad = adSoyad;
    }
    public String getAdSoyad()
    {
        return adSoyad;
    }
    public void setSifre(String sifre)
    {
        this.sifre = sifre;
    }
    public String getSifre()
    {
        return sifre;
    }
    public String getTC()
    {
        return TC;
    }
    public void setTC(String TC)
    {
        this.TC = TC;
    }
    public double getBakiye(){ return bakiye;}
    public void setBakiye(double v) {
        this.bakiye = v;
    }
    public String getIBAN() { return IBAN;}



}

