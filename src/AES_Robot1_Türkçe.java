
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class AES_Robot1_Türkçe {

    public static String[][] LabirentAyarlama(int satir, int sutun) {
        Random random = new Random();
        String[][] Labirent_Yolu = new String[satir][sutun];          //Dizilere boş satır atar daha sonra | | yazabilmek için   
        for (int i = 0; i < satir; i++) {
            for (int j = 0; j < sutun; j++) {

                Labirent_Yolu[i][j] = " ";

            }
        }

        return Labirent_Yolu;
    }

    public static void GezmeyeBaslayalim(int satir, int sutun, String[][] Labirent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("robot.txt"))) {
            Random random = new Random();
            String[][] Labirent_Yolu = new String[satir][sutun];

            Labirent_Yolu = Labirent;

            int j_baslangıc = random.nextInt(sutun);
            int referans_i = 0, referans_j = j_baslangıc;

            Labirent_Yolu[referans_i][j_baslangıc] = "☯";

            String[][] Referans = new String[satir][sutun];

            System.out.println("------------------------------------------------");
            System.out.println("----------------  LABİRENTİMİZ  ----------------");
            System.out.println("------------------------------------------------");
            writer.write("------------------------------------------------");
            writer.newLine();
            writer.write("----------------  LABİRENTİMİZ  ----------------");
            writer.newLine();
            writer.write("------------------------------------------------");
            writer.newLine();
            int cikis = random.nextInt(sutun);
            Labirent_Yolu[satir - 1][cikis] = "☺";

            for (int i = satir - 1; i >= 0; i--) {

                int satirlar_arasi_gecis = random.nextInt(sutun);

                for (int j = 0; j <= sutun - 1; j++) {

                    if (i % 2 == 1 && j != satirlar_arasi_gecis) {

                        Labirent_Yolu[i][j] = "X";
                        System.out.print("|" + Labirent_Yolu[i][j] + "|");  //+i+","+j+   i ve j lerin yerlerdine numaraları gösterir
                        System.out.print("  ");
                        writer.write("|" + Labirent_Yolu[i][j] + "|" + " ");

                    } else {
                        System.out.print("|" + Labirent_Yolu[i][j] + "|");  //+i+","+j+   i ve j lerin yerlerdine numaraları gösterir
                        System.out.print("  ");
                        writer.write("|" + Labirent_Yolu[i][j] + "|" + " ");

                    }
                }
                System.out.println("");
                writer.newLine();
            }
            writer.newLine();
            writer.write("------------------------------------------------");
            writer.newLine();
            writer.write("-----------  LABİRENTİMİZ  BİTMİŞTİR -----------");
            writer.newLine();
            writer.write("------------------------------------------------");
            writer.newLine();
            writer.newLine();

            System.out.println("------------------------------------------------");
            System.out.println("-----------  LABİRENTİMİZ  BİTMİŞTİR -----------");
            System.out.println("------------------------------------------------");

            String Referansa_Dönüldü = "saga";
            int i = referans_i;
            int j = referans_j;          // i ve je baslangıc  noktasi
            int Saga_İlerlenen_Toplam_Birim = 0;
            int Sola_İlernen_Toplam_Birim = 0;
            while (true) {

                if (Labirent_Yolu[i][j].equals("☺")) {

                    System.out.println("Arabamiz cıkısa ulasmistir");
                    writer.write("Arabamiz cıkısa ulasmistir");

                    break;
                } else if (i < satir - 1 && Labirent_Yolu[i + 1][j].equals(" ")) {
                    i += 2;

                    referans_i = i;
                    referans_j = j;

                    Referansa_Dönüldü = "saga";//Once saga sonra sola ilerleyecegimiz icin buraya  bunu koydum
                    System.out.println("Arabamiz  2 br yukari ilerledi");
                    writer.write("Arabamiz  2 br yukari ilerledi");
                    System.out.println("!!! Referansa Dönüldü --- Konumu : [" + i + "," + j + "]--> İlerlenecek yon SAG taraf");
                    writer.newLine();
                    writer.write("!!! Referansa Dönüldü --- Konumu : [" + i + "," + j + "]--> İlerlenecek yon SAG taraf");
                    writer.newLine();
                } else if (Referansa_Dönüldü.equals("saga") && referans_j == sutun - 1) {
                    System.out.println("Referans noktamız en sagda doğruca sol tarafa doğru ilerlemeye başlıyoruz!!!!!!!!!!!!!!!!!!!!!!!!!");
                    writer.write("Referans noktamız en sagda doğruca sol tarafa doğru ilerlemeye başlıyoruz!!!!!!!!!!!!!!!!!!!!!!!!!");
                    Referansa_Dönüldü = "sola";

                } else if (j == sutun - 1 && Referansa_Dönüldü.equals("saga")) {
                    Referansa_Dönüldü = "sola";

                    Sola_İlernen_Toplam_Birim += (j - referans_j);
                    System.out.println("!!!!!Maximum sag tarafa ilerledi, Sola Gitmek icin referans noktasina donuluyor"
                            + "\nArabamiz  " + (j - referans_j) + "birim sola kaydı ve referans noktasina geri döndü  ");
                    writer.write("!!!!!Maximum sag tarafa ilerledi, Sola Gitmek icin referans noktasina donuluyor");
                    writer.newLine();
                    writer.write("Arabamiz  " + (j - referans_j) + "birim sola kaydı ve referans noktasina geri döndü  ");
                    j = referans_j;
                    System.out.println("br kadar sola ilerledi [" + i + "," + j + "]");
                    writer.newLine();

                } else if (j < sutun && Referansa_Dönüldü.equals("saga")) {
                    j++;
                    Saga_İlerlenen_Toplam_Birim++;
                    System.out.println("Arabamiz 1 br saga ilerledi [" + i + "," + j + "]");
                    writer.write("Arabamiz 1 br saga ilerledi [" + i + "," + j + "]");
                    writer.newLine();
                } else if (j == 0) {
                    System.out.println("Sol tarafa max. ilerleme kaydedildi. Referans noktasina dönülüyor ve saga tarafa gidilicek --> NOT : !!! SONSUZ DÖNGÜ OLABİLİR !!!");
                    writer.write("Sol tarafa max. ilerleme kaydedildi. Referans noktasina dönülüyor ve saga tarafa gidilicek --> NOT : !!! SONSUZ DÖNGÜ OLABİLİR !!!");
                    writer.newLine();
                    Saga_İlerlenen_Toplam_Birim += (referans_j - j);//j ile çıkarmaya gerek yok ama kafam karışmasın diye çıkardım
                    j = referans_j;
                    Referansa_Dönüldü = "saga";
                } else if (j >= 0 && Referansa_Dönüldü.equals("sola")) {
                    j--;
                    Sola_İlernen_Toplam_Birim++;
                    System.out.println("Arabamiz 1 br sola ilerledi [" + i + "," + j + "]");
                    writer.write("Arabamiz 1 br sola ilerledi [" + i + "," + j + "]");

                }

            }

            System.out.println("Sola ilerlenen toplam Birim : " + Sola_İlernen_Toplam_Birim);
            writer.write("Sola ilerlenen toplam Birim : " + Sola_İlernen_Toplam_Birim);
            writer.newLine();
            System.out.println("Saga ilerlenen toplam Birim : " + Saga_İlerlenen_Toplam_Birim);
            writer.write("Saga ilerlenen toplam Birim : " + Saga_İlerlenen_Toplam_Birim);
            writer.newLine();

            writer.write(Sola_İlernen_Toplam_Birim + Saga_İlerlenen_Toplam_Birim);
        } catch (IOException ex) {
            System.out.println("Dosya açılırken hata oluştu");
        }

    }

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int satir = 0;
        int sutun = 0;

        System.out.print("satir ve sutun sayisini birer boslukla giriniz (en az 3,2 giriniz)"
                + "\n(NOT:satır çift olmalıdır aksi halde satıra 1 eklenecektir) : ");
        if (scanner.hasNextInt()) {
            satir = scanner.nextInt();

        }
        if (scanner.hasNextInt()) {
            sutun = scanner.nextInt();
        }

        if (satir < 2) {
            satir = 2;
            System.out.println("Belirtilen degerden kucuk deger girdiginiz icin satir =" + satir + " alınmıştır");
        }
        if (sutun < 2) {
            sutun = 2;
            System.out.println("Belirtilen degerden kucuk deger girdiginiz icin sutun =" + sutun + " alınmıştır");
        }

        if (satir % 2 == 0) {
            satir++;
        }

        System.out.println("1 tur attırıyorum");
      
            String[][] Labirent = LabirentAyarlama(satir, sutun);

            GezmeyeBaslayalim(satir, sutun, Labirent);
       
      
    }
} 