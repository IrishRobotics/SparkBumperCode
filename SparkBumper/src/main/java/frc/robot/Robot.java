/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Spark;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  Spark frontRight = new Spark(RobotMap.sparkFrontRightMotorController);
  Spark frontLeft = new Spark(RobotMap.sparkFrontleftMotorController);
  Spark backRight = new Spark(RobotMap.sparkBackRightMotorController);
  Spark backLeft = new Spark(RobotMap.sparkBackLeftMotorController);

  DifferentialDrive diffDrive = new DifferentialDrive(frontLeft, frontRight);

  Joystick joyLeft = new Joystick(RobotMap.joystickLeft);
  Joystick joyRight = new Joystick(RobotMap.joystickRight);

  Boolean reverse = false;

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    double left = (reverse?1: -1) * joyLeft.getRawAxis(1) * 0.85; /* positive is forward */
    double right = (reverse?1: -1) * joyRight.getRawAxis(1) * 0.8; /* positive is right */
  
    left = Math.abs(left) < 0.10?0:left;
    right = Math.abs(right) < 0.10?0:right;

    diffDrive.tankDrive(left, right);
  }
  
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    /*TODOTODO
    #####################################
    #####################################
    #####################################
    #####################################
    ADD FOllow to sparks new TODO
    #####################################
    #####################################
    #####################################
    #####################################
    */ 


  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
