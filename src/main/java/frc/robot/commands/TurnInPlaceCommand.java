package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class TurnInPlaceCommand extends CommandBase {
  private PIDController pid;

//  private double startAngle;
  private double target;

  private Drivetrain drive;

  public TurnInPlaceCommand(double turn) {
    this.pid = new PIDController(0.0, 0.0, 0.0);
    this.drive = Drivetrain.getInstance();
//    this.startAngle = this.drive.getYaw();
    this.target = this.drive.getYaw() + turn;
  }

  @Override
  public void execute() {
    double o = this.pid.calculate(this.target - this.drive.getYaw()) * Constants.Drivetrain.SLOW_WHEEL_TURN_GAIN;
    this.drive.setDriveVolts(-o, o);
  }

  @Override
  public boolean isFinished() {
    return Math.abs(this.drive.getYaw() - this.target) <= 0.1;
  }
  
  @Override
  public void end(boolean i) {
    this.drive.setDriveVolts(0.0, 0.0);
  }
}
