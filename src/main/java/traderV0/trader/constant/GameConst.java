package traderV0.trader.constant;

public abstract class GameConst {
    public static final int initialMoney = 100_000;
    public static final int stickTime = 120;
    public static final int maximumPlayerNumber = 2;
    public static final int newsGenerateTime = 120;
    public static final int gameEndTime = 600;
    public static final int maximumSessionMinute = 10;
    public static final int totalCompanyCount = 5;

    public static final int variationRatio_News = 100;
    public static final int variationRatio_Basic = 0;
    public static final String serverUrlNews = "http://localhost:5000/api/news";
    public static final String serverUrlMarket = "http://localhost:5000/api/market?totalCompanyCount=" + totalCompanyCount;
}
