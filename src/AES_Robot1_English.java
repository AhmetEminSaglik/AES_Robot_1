
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class AES_Robot1_English {

    public static String[][] LabirentAyarlama(int row, int column) {

        String[][] Labyrinth_Path = new String[row][column];          //Dizilere boş satır atar daha sonra | | yazabilmek için   
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {

                Labyrinth_Path[i][j] = " ";

            }
        }

        return Labyrinth_Path;
    }

    public static void GezmeyeBaslayalim(int row, int column, String[][] Labirent) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("robot.txt"))) {
            Random random = new Random();
            String[][] Labyrinth_Path = new String[row][column];

            Labyrinth_Path = Labirent;

            int j_baslangıc = random.nextInt(column);
            int reference_i = 0, reference_j = j_baslangıc;

            Labyrinth_Path[reference_i][j_baslangıc] = "☯";

            String[][] Reference = new String[row][column];

            System.out.println("------------------------------------------------");
            System.out.println("----------------  LABYRINTH  ----------------");
            System.out.println("------------------------------------------------");
            writer.write("------------------------------------------------");
            writer.newLine();
            writer.write("----------------  LABYRINTH  ----------------");
            writer.newLine();
            writer.write("------------------------------------------------");
            writer.newLine();
            int exit = random.nextInt(column);
            Labyrinth_Path[row - 1][exit] = "☺";

            for (int i = row - 1; i >= 0; i--) {

                int rowlar_arasi_gecis = random.nextInt(column);

                for (int j = 0; j <= column - 1; j++) {

                    if (i % 2 == 1 && j != rowlar_arasi_gecis) {

                        Labyrinth_Path[i][j] = "X";
                        System.out.print("|" + Labyrinth_Path[i][j] + "|");  //+i+","+j+   i ve j lerin yerlerdine numaraları gösterir
                        System.out.print("  ");
                        writer.write("|" + Labyrinth_Path[i][j] + "|" + " ");

                    } else {
                        System.out.print("|" + Labyrinth_Path[i][j] + "|");  //+i+","+j+   i ve j lerin yerlerdine numaraları gösterir
                        System.out.print("  ");
                        writer.write("|" + Labyrinth_Path[i][j] + "|" + " ");

                    }
                }
                System.out.println("");
                writer.newLine();
            }
            writer.newLine();
            writer.write("------------------------------------------------");
            writer.newLine();
            writer.write("-----------  LABYRINTH  OVER -----------");
            writer.newLine();
            writer.write("------------------------------------------------");
            writer.newLine();
            writer.newLine();

            System.out.println("------------------------------------------------");
            System.out.println("-----------  LABYRINTH  OVER -----------");
            System.out.println("------------------------------------------------");

            String Returned_To_Reference = "To The Left";
            int i = reference_i;
            int j = reference_j;          // i ve je baslangıc  noktasi
            int Total_Unit_Progressing_to_the_Right = 0;
            int Sola_İlernen_Toplam_Birim = 0;
            while (true) {

                if (Labyrinth_Path[i][j].equals("☺")) {

                    System.out.println(" Robot has reached the exit");
                    writer.write(" Robot has reached the exit");

                    break;
                } else if (i < row - 1 && Labyrinth_Path[i + 1][j].equals(" ")) {
                    i += 2;

                    reference_i = i;
                    reference_j = j;

                    Returned_To_Reference = "To The Left";//Once To The Left sonra to The Left ilerleyecegimiz icin buraya  bunu koydum
                    System.out.println("Robot went up 2 br");
                    writer.write("Robot went up 2 br");
                    System.out.println("!!! Returned to reference --- Location : [" + i + "," + j + "]--> Progress to RIGHT");
                    writer.newLine();
                    writer.write("!!! Returned to reference --- Location : [" + i + "," + j + "]--> Progress to RIGHT");
                    writer.newLine();
                } else if (Returned_To_Reference.equals("To The Left") && reference_j == column - 1) {
                    System.out.println("We reached the right, we start moving to the left!!!!!!!!!!!!!!!!!!!!!!!!!");
                    writer.write("We reached the right, we start moving to the left!!!!!!!!!!!!!!!!!!!!!!!!!");
                    Returned_To_Reference = "to The Left";

                } else if (j == column - 1 && Returned_To_Reference.equals("To The Left")) {
                    Returned_To_Reference = "to The Left";

                    Sola_İlernen_Toplam_Birim += (j - reference_j);
                    System.out.println("!!!!!Moved  Maximum to the Right, now turning to left to go the reference point"
                            + "\nRobot  " + (j - reference_j) + "one unit moved left and returned to the reference point ");
                    writer.write("!!!!!Moved  Maximum to the Right, now turning to left to go the reference point");
                    writer.newLine();
                    writer.write("Robot  " + (j - reference_j) + "one unit moved left and returned to the reference point ");
                    j = reference_j;
                    System.out.println("progressed left  : [" + i + "," + j + "] br");
                    writer.newLine();

                } else if (j < column && Returned_To_Reference.equals("To The Left")) {
                    j++;
                    Total_Unit_Progressing_to_the_Right++;
                    System.out.println("Robot 1 br  progressed to the left  [" + i + "," + j + "]");
                    writer.write("Robot 1 br  progressed to the left  [" + i + "," + j + "]");
                    writer.newLine();
                } else if (j == 0) {
                    System.out.println("Max. progress has been made. Returning to the reference point and heading to the To The Left -> NOTE: !!! CAN BE ENDLESS CYCLE !!!");
                    writer.write("Max. progress has been made. Returning to the reference point and heading to the To The Left -> NOTE: !!! CAN BE ENDLESS CYCLE !!!");
                    writer.newLine();
                    Total_Unit_Progressing_to_the_Right += (reference_j - j);//j ile çıkarmaya gerek yok ama kafam karışmasın diye çıkardım
                    j = reference_j;
                    Returned_To_Reference = "To The Left";
                } else if (j >= 0 && Returned_To_Reference.equals("to The Left")) {
                    j--;
                    Sola_İlernen_Toplam_Birim++;
                    System.out.println("Robot 1 br  progressed to the left [" + i + "," + j + "]");
                    writer.write("Robot 1 br  progressed to the left [" + i + "," + j + "]");

                }

            }

            System.out.println("Total unit  of progressed to the Left : " + Sola_İlernen_Toplam_Birim);
            writer.write("Total unit  of progressed to the Left : " + Sola_İlernen_Toplam_Birim);
            writer.newLine();
            System.out.println("Total unit  of progressed to the Right : " + Total_Unit_Progressing_to_the_Right);
            writer.write("Total unit  of progressed to the Right : " + Total_Unit_Progressing_to_the_Right);
            writer.newLine();

            writer.write(Sola_İlernen_Toplam_Birim + Total_Unit_Progressing_to_the_Right);
        } catch (IOException ex) {
            System.out.println("Error opening file");
        }

    }

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int row = 0;
        int column = 0;

        System.out.print("enter the number of rows and columns with a space (at least 3,2) "
                + "\n(row need to be odd or row will be added 1) : ");
        if (scanner.hasNextInt()) {
            row = scanner.nextInt();

        }
        if (scanner.hasNextInt()) {
            column = scanner.nextInt();
        }

        if (row < 3) {
            row = 3;
            System.out.println("Row valued  ||" + row + "|| because of entering less than the specified value");
        }
        if (column < 2) {
            column = 2;
            System.out.println("Column valued ||" + column + "|| because of entering less than the specified value");
        }

        if (row % 2 == 0) {
            row++;
        }

 

        String[][] Labirent = LabirentAyarlama(row, column);

        GezmeyeBaslayalim(row, column, Labirent);

    }

}
