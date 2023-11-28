package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  private static Intake instance;
  private CANSparkMax motor;

  private double nextThrowVolts = Constants.Intake.OFF;

  private Intake() {
    this.motor = new CANSparkMax(11, CANSparkMaxLowLevel.MotorType.kBrushed);
  }

  public static Intake getInstance() {
    return frc.robot.subsystems.Intake.instance == null ? frc.robot.subsystems.Intake.instance = new Intake() : frc.robot.subsystems.Intake.instance;
  }

  public CommandBase intakeCone() {
    return run(() -> {
      this.motor.setSmartCurrentLimit(Constants.Intake.INTAKE_CURRENT_LIMIT);
      this.motor.set(Constants.Intake.INTAKE_CONE_VOLTS);
    });
  }

  public CommandBase holdCone() {
    return run(() -> {
      this.motor.setSmartCurrentLimit(Constants.Intake.HOLD_CURRENT_LIMIT);
      this.motor.setVoltage(Constants.Intake.HOLD_CONE_VOLTS);
      this.nextThrowVolts = Constants.Intake.THROW_CONE_VOLTS;
    });
  }

  public CommandBase intakeCube() {
    return run(() -> {
      this.motor.set(Constants.Intake.INTAKE_CUBE_VOLTS);
      this.motor.setSmartCurrentLimit(Constants.Intake.INTAKE_CURRENT_LIMIT);
    });
  }

  public CommandBase holdCube() {
    return run(() -> {
      this.motor.setSmartCurrentLimit(Constants.Intake.HOLD_CURRENT_LIMIT);
      this.motor.setVoltage(Constants.Intake.HOLD_CUBE_VOLTS);
      this.nextThrowVolts = Constants.Intake.THROW_CUBE_VOLTS;
    });
  }

  public CommandBase throwItem() {
    return run(() -> {
      this.motor.setVoltage(this.nextThrowVolts);
      this.motor.setSmartCurrentLimit(Constants.Intake.INTAKE_CURRENT_LIMIT);
    });
  }

  public Command off() {
    return runOnce(() -> this.motor.setVoltage(Constants.Intake.OFF));
  }

  @Override
  public void periodic() {}
}
