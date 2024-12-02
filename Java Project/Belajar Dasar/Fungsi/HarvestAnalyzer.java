public class HarvestAnalyzer {

    public static void main(String[] args) {
        HarvestAnalyzer analyzer = new HarvestAnalyzer();
        analyzer.startAnalysis();

        double[] yieldData = {23.5, 26.1, 24.7, 27.3, 25.4};
        double averageYield = analyzer.calculateAverageYield(yieldData);
        analyzer.printYieldReport(averageYield);

        int[] monthlyYields = {2350, 2610, 2470, 2730, 2540};
        int totalYield = analyzer.calculateTotalYield(monthlyYields, monthlyYields.length - 1);
        System.out.println("Total Hasil Panen: " + totalYield + " kg");
    }

    public void startAnalysis() {
        System.out.println("Memulai analisis data hasil panen...");
    }

    public double calculateAverageYield(double[] yields) {
        double total = 0;
        for (double yield : yields) {
            total += yield;
        }
        return total / yields.length;
    }

    public void printYieldReport(double averageYield) {
        System.out.println("Rata-rata hasil panen: " + averageYield + " ton");
    }

    public int calculateTotalYield(int[] yields, int index) {
        if (index < 0) {
            return 0;
        }
        return yields[index] + calculateTotalYield(yields, index - 1);
    }
}