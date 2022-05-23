import java.util.List;

public class Solution {
  public static void DynamicProgrammingSolution(List<Seed> selectedSeed, int M) {
    int total_profit = 0;
    int n = selectedSeed.size();

    // Membuat list tahapan dengan tahap maksimum adalah n
    int f[][] = new int[n + 1][M + 1];
    for (int i = 0; i <= n; i++) {
      for (int j = 0; j <= M; j++) {
        f[i][j] = 0;
      }
    }

    // Menghitung nilai keuntungan (p) dari semua bibit tanaman dari tahap ke-1 sampai tahap ke-n
    // menggunakan prinsip optimalitas
    for (int k = 1; k <= n; k++) {
      for (int y = 0; y <= M; y++) {
        int w = selectedSeed.get(k - 1).getBuyPrice(); // W[k]
        int p = selectedSeed.get(k - 1).getProfit(); // P[k]
        if (w > y) {
          f[k][y] = f[k - 1][y];
        } else {
          f[k][y] = Math.max(f[k - 1][y], p + f[k - 1][y - w]);
        }
      }
    }

    // Pencarian solusi dengan pendekatan program dinamis mundur
    System.out.println("Program dinamis mundur: ");
    int solusi[] = new int[n];
    int k = n;
    while (k != 0) {
      if (f[k][M] != f[k - 1][M]) {
        System.out.println("Membeli bibit ke-" + k);
        solusi[k - 1] = 1;
        total_profit += selectedSeed.get(k - 1).getProfit();
        M = M - selectedSeed.get(k - 1).getBuyPrice();
      } else {
        solusi[k - 1] = 0;
      }
      k--;
    }

    // Menampilkan solusi optimal, uang sisa pemain, serta total keuntungan yang didapatkan pemain
    System.out.print("\nSolusi Optimal: X = (" + solusi[0]);
    for (int i = 1; i < n; i++) {
      System.out.print(", ");
      System.out.print(solusi[i]);
    }
    System.out.print(")");
    System.out.println("\nUang pemain yang tersisa: " + M);
    System.out.println("Total keuntungan pemain yang didapatkan: " + total_profit);
  }
}
