package main.java.frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class LimelightInterface {

    private static NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");
    private DriveSubsystem driveSubsystem;
    private static final double MIN_APRILTAG_AREA_THRESHOLD = 0.1;
    public LimelightInterface(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
    }

    public void periodic() {
        NetworkTableEntry tx = limelight.getEntry("tx");//Tag X value
        NetworkTableEntry ty = limelight.getEntry("ty");//Tag Y value
        NetworkTableEntry ta = limelight.getEntry("ta");//Tag Area
        //sets the X,Y, and Area values to 0
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);

        // Check if the Limelight sees an AprilTag
        if (area > MIN_APRILTAG_AREA_THRESHOLD) {
            // Control the robot based on Limelight data
            driveSubsystem.limelightControl(x, y, area);
        } else {
            // No AprilTag detected, stop the robot
            driveSubsystem.teleop(0.0, 0.0); // Stop the robot
        }

        // Update SmartDashboard and Shuffleboard
        updateDashboard(x, y, area);
    }
    //updates shuffleboard
    private void updateDashboard(double x, double y, double area) {
        Shuffleboard.putNumber("LimelightX", x);
        Shuffleboard.putNumber("LimelightY", y);
        Shuffleboard.putNumber("LimelightArea", area);
        
        //makes window for X on shuffleboard
        Shuffleboard.getTab("SmartDashboard")
                .add("LimelightX", x)
                .withWidget(BuiltInWidgets.kNumberBar)
                .withPosition(0, 0)
                .withSize(2, 1);

        //updates suffleboard
        Shuffleboard.update();
    }
}
