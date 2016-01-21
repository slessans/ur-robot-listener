package com.scottlessans.urcontroller.models.packages;

import com.scottlessans.urcontroller.models.ControlMode;
import com.scottlessans.urcontroller.models.Message;
import com.scottlessans.urcontroller.models.RobotMode;

public class RobotModeData extends BasePackage {

    final public long timestamp;
    final public boolean physicalRobotConnected;
    final public boolean realRobotEnabled;
    final public boolean robotPowerOn;
    final public boolean emergencyStopped;
    final public boolean protectiveStopped;
    final public boolean programRunning;
    final public boolean programPaused;
    final public RobotMode robotMode;
    final public ControlMode controlMode;
    final public double targetSpeedFraction;
    final public double	speedScaling;

    public RobotModeData(
            final Message message, final int packageTypeCode,
            long timestamp, boolean physicalRobotConnected, boolean realRobotEnabled, boolean robotPowerOn,
            boolean emergencyStopped, boolean protectiveStopped, boolean programRunning, boolean programPaused,
            RobotMode robotMode, ControlMode controlMode, double targetSpeedFraction, double speedScaling) {
        super(message, packageTypeCode);
        this.timestamp = timestamp;
        this.physicalRobotConnected = physicalRobotConnected;
        this.realRobotEnabled = realRobotEnabled;
        this.robotPowerOn = robotPowerOn;
        this.emergencyStopped = emergencyStopped;
        this.protectiveStopped = protectiveStopped;
        this.programRunning = programRunning;
        this.programPaused = programPaused;
        this.robotMode = robotMode;
        this.controlMode = controlMode;
        this.targetSpeedFraction = targetSpeedFraction;
        this.speedScaling = speedScaling;
    }

    @Override
    public String toString() {
        return "RobotModeData{" +
                "typeCode=" + this.getPackageTypeCode() +
                ", timestamp=" + timestamp +
                ", physicalRobotConnected=" + physicalRobotConnected +
                ", realRobotEnabled=" + realRobotEnabled +
                ", robotPowerOn=" + robotPowerOn +
                ", emergencyStopped=" + emergencyStopped +
                ", protectiveStopped=" + protectiveStopped +
                ", programRunning=" + programRunning +
                ", programPaused=" + programPaused +
                ", robotMode=" + robotMode +
                ", controlMode=" + controlMode +
                ", targetSpeedFraction=" + targetSpeedFraction +
                ", speedScaling=" + speedScaling +
                '}';
    }
}
