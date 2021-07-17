/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Rect;

import edu.wpi.first.hal.util.UncleanStatusException;
import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.Robot;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class JeVois {
    SerialPort jevois;
    Contour contour;

    public JeVois() {
        try {
            jevois = new SerialPort(Constants.JEVOIS_BAUD_RATE, SerialPort.Port.kUSB);
            jevois.setFlowControl(SerialPort.FlowControl.kNone);
        } catch (UncleanStatusException e) {
            System.out.println("Exception Caught");
        }
    }
    public Contour parseStream() {
        if (jevois != null) {
            String cameraOutput = jevois.readString();
            System.out.println("Output: " + cameraOutput);
            if (cameraOutput != null && !cameraOutput.trim().isEmpty()) {
                    String[] contourValues = cameraOutput.split(",");
                    Contour contour = new Contour(contourValues[0].trim(), contourValues[1].trim(),
                            contourValues[2].trim(), contourValues[3].trim(), contourValues[4].trim());
                     System.out.println("output2: " + contour.toString());
                    return contour;
            }
            return null;

        }
//        System.out.println("Jevois Null");
        return null;
    }
}