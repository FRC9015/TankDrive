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
    //updates dashboard
    private void updateDashboard(double x, double y, double area) {
        SmartDashboard.putNumber("LimelightX", x);
        SmartDashboard.putNumber("LimelightY", y);
        SmartDashboard.putNumber("LimelightArea", area); 
    }
    //code that is ran
    @Override
    public void periodic() {
        System.out.println(tag);
        //takes the X,Y, and area values from the limelight networktable
        NetworkTableEntry tx = limelight.getEntry("tx");//Tag X value
        NetworkTableEntry ty = limelight.getEntry("ty");//Tag Y value
        NetworkTableEntry ta = limelight.getEntry("ta");//Tag Area
        
        //makes the X,Y, and Area into variables to be used
        double x = tx.getDouble(0.0);
        double y = ty.getDouble(0.0);
        double area = ta.getDouble(0.0);

        // Check if the Limelight sees an AprilTag
        if (area > MIN_APRILTAG_AREA) {
            tag = true;
            
            //loop so that the robot drives when it sees tag
            while(tag = true){
                // Control the robot based on Limelight 
                driveSubsystem.limelightControl(x, y, area);
                
                //updates values periodicly
                x = tx.getDouble(0.0);
                y = ty.getDouble(0.0);
                area = ta.getDouble(0.0);
                updateDashboard(x, y, area);
               
                //if statment so that loop stops when tag is not seen
                if(area < MIN_APRILTAG_AREA){
                    tag = false;
                }
            }
        }else{
            //print statment that shows it does not see tag
            System.out.println("no tag");
        }
    }
}
