import java.util.Random;

public class AutoSensorClassOne {

    private static double horizontal = 0;
    private static double vertical = 0;

    private static double horizontalChange;
    private static double verticalChange;

    private static double HorizontalChangeTol;
    private static double verticalChangeTol;
    private static double highestDistance = 0;

    private static int loopTime = 5000;
    private static int numberOfAdjustments = 0;
    private static int boosterHeat = 0;

    public static void main(String[] args){
        positionRandomize();
    }


    public static void positionRandomize(){
        Random random = new Random();

        //overall position randomization
        for(int j = 0; j < loopTime; j++){
            System.out.println("Run Time: " + j);
            positionChangeValue();
            int horizontalControl = random.nextInt(2);
            int verticalControl = random.nextInt(2);

            if(true) {
                if (horizontalControl == 0) {
                    horizontal += horizontalChange;
                    HorizontalChangeTol += horizontalChange;
                } else if (horizontalControl == 1) {
                    horizontal -= horizontalChange;
                    HorizontalChangeTol -= horizontalChange;
                }

                if (verticalControl == 0) {
                    vertical += verticalChange;
                    verticalChangeTol += verticalChange;
                } else if (horizontalControl == 1) {
                    vertical -= verticalChange;
                    verticalChangeTol -= verticalChange;
                }
                sensor();
            }
            System.out.println(" ");
            if(j == (loopTime - 1)){
                System.out.println("========== Auto-adjustment Successful ==========");
                System.out.println("Number of Adjustments: " + numberOfAdjustments);
                System.out.println("Number of boosters used: " + boosterHeat);
                System.out.println("Largest distance away from the center: " + String.format("%.2f", highestDistance));
                System.out.println("Position change total: " + "(" + String.format("%.2f", HorizontalChangeTol)
                        + ", " + String.format("%.2f", verticalChangeTol) + ")");
            }
        }
    }


    public static void positionChangeValue(){
        Random random = new Random();

        //horizontal randomization
        for(int i = 0; i < 5; i++){
            int horizontalPosChange = random.nextInt(9);
            horizontalChange += horizontalPosChange;
        }
        horizontalChange = horizontalChange / 5; //average of five different random numbers

        //vertical randomization
        for(int r = 0; r < 5; r++){
            int verticalPosChange = random.nextInt(9);
            verticalChange += verticalPosChange;
        }
        verticalChange = verticalChange / 5; //average of five different random numbers
    }

    public static void sensor(){
        double horizontalSquare = horizontal * horizontal;
        double verticalSquare = vertical * vertical;

        double sensorOutput = Math.sqrt(horizontalSquare + verticalSquare);

        if(sensorOutput >= highestDistance){
            highestDistance = sensorOutput;
        }

        //fail control
        if(sensorOutput > 50){
            System.out.println("========== Auto-adjustment error. Program terminated ==========");
            System.out.println("Number of Adjustments: " + numberOfAdjustments);
            System.out.println("Number of boosters used: " + boosterHeat);
            System.out.println("Largest distance away from the center: " + String.format("%.2f", highestDistance));
            System.out.println("Position change total: " + "(" + String.format("%.2f", HorizontalChangeTol)
                    + ", " + String.format("%.2f", verticalChangeTol) + ")");
            System.exit(0);
        }

        if(boosterHeat > 6){
            System.out.println("========== Booster Overheated. Project fail ==========");
            System.exit(0);
        }

        System.out.println("Current position away from center: " + String.format("%.2f", sensorOutput)
                + "(" + String.format("%.2f", horizontal) + ", " + String.format("%.2f", vertical) + ")");


        //Speed-booster active
        if(sensorOutput > 40 && boosterHeat != 4 && boosterHeat != 5 && boosterHeat != 6) {
            if (horizontal > 30) {
                horizontal -= 5;
                System.out.println("Booster active: Horizontal - 5");
                boosterHeat++;
            } else if (horizontal < 30) {
                horizontal += 5;
                System.out.println("Booster active: Horizontal + 5");
                boosterHeat++;
            }

            if (vertical > 30) {
                vertical -= 5;
                System.out.println("Booster active: Vertical - 5");
                boosterHeat++;
            } else if (vertical < 30) {
                vertical += 5;
                System.out.println("Booster active: Vertical + 5");
                boosterHeat++;
            }
        }

        //Final booster active
        if(sensorOutput > 48 && boosterHeat == 4) {
            if (horizontal > 30) {
                horizontal -= 10;
                System.out.println("Booster active: Horizontal - 10");
                boosterHeat++;
            } else if (horizontal < 30) {
                horizontal += 10;
                System.out.println("Booster active: Horizontal + 10");
                boosterHeat++;
            }

            if (vertical > 30) {
                vertical -= 10;
                System.out.println("Booster active: Vertical - 10");
                boosterHeat++;
            } else if (vertical < 30) {
                vertical += 10;
                System.out.println("Booster active: Vertical + 10");
                boosterHeat++;
            }
        }

        //normal adjustments
        if (sensorOutput > 10 && horizontal > 7) {
            horizontal -= 3;
            System.out.println("Horizontal - 3");
        } else if (sensorOutput > 10 && horizontal < 7) {
            horizontal += 3;
            System.out.println("Horizontal + 3");
        }

        if (sensorOutput > 10 && vertical > 7) {
            vertical -= 3;
            System.out.println("Vertical - 3");
        } else if (sensorOutput > 10 && vertical < 7) {
            vertical += 3;
            System.out.println("Vertical + 3");
        }
    }
}
