package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimelightInterface extends SubsystemBase{

    private static NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");
    boolean tag = false;
    //private DriveSubsystem driveSubsystem;
    private static final double MIN_APRILTAG_AREA = 0.1;
    // public LimelightInterface(DriveSubsystem driveSubsystem) {
    //     this.driveSubsystem = driveSubsystem;
    
    //updates dashboard
    private void updateDashboard(double x, double y, double area) {
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area); 
    }
    //code that is ran
    @Override
    public void periodic() {
        
        //takes the X,Y, and area values from the limelight networktable
        NetworkTableEntry tx = limelight.getEntry("tx");//Tag X value
        NetworkTableEntry ty = limelight.getEntry("ty");//Tag Y value
        NetworkTableEntry ta = limelight.getEntry("ta");//Tag Area
        
        //makes the X,Y, and Area into variables to be used
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);

        // Check if the Limelight sees an AprilTag

        while(area > MIN_APRILTAG_AREA){
                // Control the robot based on Limelight 
                
                //driveSubsystem.limelightControl(x, y, area);
                
                //updates values periodicly
                x = tx.getDouble(0.0);
                y = ty.getDouble(0.0);
                area = ta.getDouble(0.0);
                updateDashboard(x, y, area);
                 
            }
    }
}
