package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.RobotSelf;


public class SelfDriveSubsystem extends SubsystemBase{

    private DriveSubsystem drive;
    private LimelightInterface limelight;
    private XboxController controller;
    private RobotSelf robotSelf;
    
    public SelfDriveSubsystem(XboxController controller, RobotSelf robotSelf, LimelightInterface limelight, DriveSubsystem drive) {
        this.controller = controller;
        this.robotSelf = robotSelf;
        this.limelight = limelight;
        this.drive = drive;
        
    }
    @Override
    public void periodic(){
        //checks to see if the X button has been pressed
        if(controller.getXButtonReleased()){
            //toggles selfdrive boolean
            robotSelf.toggleselfdrive();
            //puts selfdrive onto smartdashboard
            SmartDashboard.putBoolean("Self Drive", robotSelf.getselfdrive());
        }
        //makes new X Y and Area values seperate from the limelight ones.
        double x = limelight.getX();
        double y = limelight.getY();
        double area = limelight.getArea();
        //checks to see if self drive is true
        if(robotSelf.getselfdrive()){
            //if statment that uses a TagCheck from limelightinterface
            while(limelight.TagCheck()){
                //updates values
                x = limelight.getX();
                y = limelight.getY();
                area = limelight.getArea();
                //makes it drive
                drive.limelightControl(x, y, area);
            }
        } 
    }
}
