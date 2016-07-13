package AdSuMuDiSecurity;

import java.io.File;

public class CheckIfDirectoryExists {

    static String existDirectoryPath = "";
    static String operatingSystemDrive = System.getenv("SystemDrive");

    public static String isCDirectoryExists() {

        File dir = new File(operatingSystemDrive + "//Program Files//Common Files//System");

        boolean exists = dir.exists();

        System.out.println("Directory " + dir.getPath() + " exists: " + exists);

        if (exists) {
            existDirectoryPath = dir.getPath();
        } else {
            existDirectoryPath = operatingSystemDrive + "//";
        }

        return existDirectoryPath;
    }
//    public static String isDDirectoryExists() {
//
//        File dir = new File("D://");
//
//        boolean exists = dir.exists();
//
//        System.out.println("Directory " + dir.getPath() + " exists: " + exists);
//
//        if (exists) {
//            existDirectoryPath = dir.getPath();
//        } else {
//            existDirectoryPath = "D://";
//        }
//
//        return existDirectoryPath;
//    }
}