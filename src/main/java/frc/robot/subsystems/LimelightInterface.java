package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.PerpetualCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;

public class LimelightInterface extends SubsystemBase{

    private static NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");
    boolean tag = false;
    private DriveSubsystem driveSubsystem;
    private static final double MIN_APRILTAG_AREA = 0.1;
    public LimelightInterface(DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
    }
    
    @Override
    public void periodic() {
        
        NetworkTableEntry tx = limelight.getEntry("tx");//Tag X value
        NetworkTableEntry ty = limelight.getEntry("ty");//Tag Y value
        NetworkTableEntry ta = limelight.getEntry("ta");//Tag Area
        //sets the X,Y, and Area values to 0
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);
        
        // Check if the Limelight sees an AprilTag
        if (area > MIN_APRILTAG_AREA) {
            tag = true;
            System.out.println("dis works");
            //loop so that the robot drives
            while(tag == true){
                // Control the robot based on Limelight 
                driveSubsystem.limelightControl(x, y, area);
                System.out.println("moves");
                //if statment so that loop stops when tag is gone
                if(area < 0.1){
                    tag = false;
                    System.out.println("tag is gone");
                }
            }
        } else {
            // No AprilTag detected, makes sure robot is not moving
            driveSubsystem.teleop(0.0, 0.0); // makes robot not move
            System.out.println("no tag");
        }

        // Update SmartDashboard and Shuffleboard
        updateDashboard(x, y, area);
    }
    
    //updates shuffleboard
    private void updateDashboard(double x, double y, double area) {
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area);
        
        //updates suffleboard
        Shuffleboard.update();
    }   
}
