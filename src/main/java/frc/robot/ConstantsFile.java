package frc.robot;

public class ConstantsFile {
  private static ConstantsFile instance;

  private String filename;

  private ConstantsFile() {

  }

  public static ConstantsFile getInstance() {
    return (ConstantsFile.instance == null) ? ConstantsFile.instance = new ConstantsFile() : ConstantsFile.instance;
  }
}
