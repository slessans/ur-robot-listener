package com.scottlessans.urcontroller.parsers.packages;

import com.scottlessans.urcontroller.decoders.DecodeException;
import com.scottlessans.urcontroller.decoders.URControllerContentDecoder;
import com.scottlessans.urcontroller.models.ControlMode;
import com.scottlessans.urcontroller.models.Message;
import com.scottlessans.urcontroller.models.PackageHeader;
import com.scottlessans.urcontroller.models.RobotMode;
import com.scottlessans.urcontroller.models.packages.Package;
import com.scottlessans.urcontroller.models.packages.RobotModeData;
import com.scottlessans.urcontroller.parsers.PackageParser;


public class RobotModePackageParser implements PackageParser {

    @Override
    public Package parse(
            final Message message,
            final URControllerContentDecoder decoder,
            final PackageHeader packageHeader) throws DecodeException {

        final long timestamp = decoder.decodeTimestamp();
        final boolean physicalRobotConnected = decoder.decodeBoolean();
        final boolean realRobotEnabled = decoder.decodeBoolean();
        final boolean robotPowerOn = decoder.decodeBoolean();
        final boolean emergencyStopped = decoder.decodeBoolean();
        final boolean protectiveStopped = decoder.decodeBoolean();
        final boolean programRunning = decoder.decodeBoolean();
        final boolean programPaused = decoder.decodeBoolean();
        final RobotMode robotMode = decoder.decodeRobotMode();
        final ControlMode controlMode = decoder.decodeControlMode();
        final double targetSpeedFraction = decoder.decodeDouble();
        final double speedScaling = decoder.decodeDouble();

        return new RobotModeData(
                message,
                packageHeader.packageTypeCode,
                timestamp,
                physicalRobotConnected,
                realRobotEnabled,
                robotPowerOn,
                emergencyStopped,
                protectiveStopped,
                programRunning,
                programPaused,
                robotMode,
                controlMode,
                targetSpeedFraction,
                speedScaling
        );
    }

}
