public class Seed {
  private String name;
  private int buy_price;
  private int sell_price;
  private String season;
  private int growth;
  private int regrowth;
  private int profit;

  public Seed(String name, int buy_price, int sell_price, String season, int growth, int regrowth) {
    this.name = name;
    this.buy_price = buy_price;
    this.sell_price = sell_price;
    this.season = season;
    this.growth = growth;
    this.regrowth = regrowth;
  }

  public void calculateProfit(int t) {
    int d = 0;
    int s = sell_price;
    int b = buy_price;
    int g = growth;
    int r = regrowth;
    if (r == 0) {
      profit = (s - b) * ((int) Math.floor((float) (30 - t) / g));
    } else {
      d = 30 - (g + t);
      profit = (int) Math.ceil((double) d / 30) * (s - b + (s * (int) Math.floor((double) d / r)));
    }
  }

  public int getProfit() {
    return profit;
  }

  public String getName() {
    return name;
  }

  public int getBuyPrice() {
    return buy_price;
  }

  public int getSellPrice() {
    return sell_price;
  }

  public String getSeason() {
    return season;
  }

}
