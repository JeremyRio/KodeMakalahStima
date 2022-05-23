import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver {
  public static List<Seed> readFile() {
    CSVReader csvReader = new CSVReader("\t");
    csvReader.setSkipHeader(true);
    List<Seed> listSeed = new ArrayList<>();
    try {
      File csvFile = new File("DataFile.csv");
      List<String[]> fileRows = csvReader.read(csvFile);
      for (String[] row : fileRows) {
        Seed seed = new Seed(row[0], Integer.parseInt(row[1]), Integer.parseInt(row[2]), row[3],
            Integer.parseInt(row[4]), Integer.parseInt(row[5]));
        listSeed.add(seed);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return listSeed;
  }

  public static void main(String[] args) {
    List<Seed> listSeed = new ArrayList<>();
    listSeed = Driver.readFile();
    Scanner sc = new Scanner(System.in);
    System.out.print("Uang pemain (M): ");
    int M = sc.nextInt();
    System.out.print("Tanggal permainan (t): ");
    int t = sc.nextInt();
    System.out.print("Musim permainan (Season): ");
    String season = sc.next();

    List<Seed> selectedSeed = listSeed.stream().filter(s -> s.getSeason().equals(season)).toList();
    String leftAlignFormat = "| %-2s | %-17s | %-5s | %-5s | %n";
    System.out.println("+----+-------------------+-------+-------+");
    System.out.format(leftAlignFormat, "i", "Nama", "W", "P");
    System.out.println("+----+-------------------+-------+-------+");
    int i = 1;
    for (Seed seed : selectedSeed) {
      seed.calculateProfit(t);
      System.out.format(leftAlignFormat, i, seed.getName(), seed.getBuyPrice(), seed.getProfit());
      i++;
    }
    System.out.println("+----+-------------------+-------+-------+");
    Solution.DynamicProgrammingSolution(selectedSeed, M);
    sc.close();
  }
}
