package main.java.frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimelightInterface {
    
    // Get Limelight Instance 
    private static NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");

    @Override
    public void periodic() {
        NetworkTableEntry tx = limelight.getEntry("tx"); // Table X Value
        NetworkTableEntry ty = limelight.getEntry("ty"); // Table Y Value
        NetworkTableEntry ta = limelight.getEntry("ta"); // Table Area Value

        //read values periodically
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);

        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
    }
}
