package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.RobotSelf;

public class SelfDriveSubsystem extends SubsystemBase{

    private XboxController controller;
    private RobotSelf robotSelf;
    public SelfDriveSubsystem(XboxController controller, RobotSelf robotSelf) {
        this.controller = controller;
        this.robotSelf = robotSelf;
        
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
    }
}
