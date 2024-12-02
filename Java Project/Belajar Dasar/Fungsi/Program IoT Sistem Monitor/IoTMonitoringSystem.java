public class IoTMonitoringSystem {
    
    public static void main(String[] args) {
        IoTMonitoringSystem system = new IoTMonitoringSystem();
        system.startMonitoring();
    }

    public void startMonitoring() {
        System.out.println("Initialize Systems");
        checkSystem();
        int cycles = 1; // Ubah jika membutuhkan rekursif berlanjut
        gatherData(cycles);
    }

    public void checkSystem() {
        System.out.println("Checking all sensors and systems...");
    }

    public void gatherData(int times) {
        if(times > 0) {
            double[] moistureReadings = {25.3, 22.5, 27.0, 24.1};
            double[] temperaturReadings = {18.5, 21.0, 22.5, 25.4};
            
            double averageMoisture = calculateAverage (moistureReadings);
            double averageTemperature = calculateAverage (temperaturReadings);

            System.out.println("Average moisture: " + averageMoisture);
            System.out.println("Average temperature: " + averageTemperature);

            if (averageMoisture < 20.0 || averageTemperature > 25.0) {
                displayNotification("Attention: check your systems.");
            }
            gatherData(times - 1); // Menggunakan rekursi untuk melakukan pemantauan berulang
        }
    }

    public double calculateAverage (double... readings) {
        double sum = 0;
        for (double reading : readings) {
            sum += reading;
        } 
        return sum / readings.length;
    }

    public void displayNotification(String message) {
        System.out.println("Notification: " + message);
    }
    
}