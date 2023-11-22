package main.java.frc.robot.subsystems;


import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;

public class LimelightInterface {
    private static NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");

    public double getDoubleEntry(String entry) {
        return limelight.getEntry(entry).getDouble(0);
    }
    public double[] getArrayEntry(String entry){
        return limelight.getEntry(entry).getDoubleArray(new double[6]);
    }
    public double getID(){
        return getDoubleEntry("tid");
    }
    public double getTargetArea(){
        return getDoubleEntry("ta");
    }
    public boolean hasValidTargets(){
        return getDoubleEntry("tv") == 1.0;
    }
    public double getXOffset(){
        return getDoubleEntry("tx");
    }
    public double[] getBotPose(){
        return getArrayEntry("botpose_targetspace");
    }


}
