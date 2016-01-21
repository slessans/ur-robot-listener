package com.scottlessans.urcontroller.decoders;

import com.scottlessans.urcontroller.models.ControlMode;
import com.scottlessans.urcontroller.models.RobotMode;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;


public class URControllerContentDecoder {

    protected ByteBuffer _buffer;

    public URControllerContentDecoder(ByteBuffer buffer) {
        this._buffer = buffer;
    }

    public void decodeBytes(byte [] bytes) {
        this._buffer.get(bytes);
    }

    /**
     * Decodes next 4 bytes, treating them as a
     * big-endian integer.
     * @return integer value
     */
    public int decodeInt() throws DecodeException  {
        return this
                ._buffer
                .order(ByteOrder.BIG_ENDIAN)
                .getInt();
    }

    /**
     * Decodes next 8 bytes, treating them as a big-endian double.
     * @return integer value
     */
    public double decodeDouble() throws DecodeException {
        return this._buffer.order(ByteOrder.BIG_ENDIAN).getDouble();
    }

    /**
     * Next 1 byte as unsigned char, returned as int as use
     * is number
     * @return unsigned char int value
     */
    public int decodeUnsignedCharIntValue() throws DecodeException {
        return Byte.toUnsignedInt(this._buffer.get());
    }

    /**
     * Decode a timestamp as long, time since ?robot started?
     * Technically this is a 64-bit unsigned integer, java does
     * not support unsigned natively, so this checks to make sure it
     * wont wrap around
     * @return timestamp
     */
    public long decodeTimestamp() throws DecodeException {
        final long value = this._buffer.order(ByteOrder.BIG_ENDIAN).getLong();

        // check if overflowed
        if (value < 0) {
            throw new DecodeException("timestamp is too big.");
        }

        return value;
    }

    /**
     * Parses next 1 byte as boolean
     * @return bool val
     * @throws DecodeException
     */
    public boolean decodeBoolean() throws DecodeException {
        return this._buffer.get() != 0;
    }

    /**
     * Decode next byte as robot mode
     * @return robot mode
     * @throws DecodeException
     */
    public RobotMode decodeRobotMode() throws DecodeException {
        /*
        0		ROBOT_MODE_DISCONNECTED
        1		ROBOT_MODE_CONFIRM_SAFETY
        2		ROBOT_MODE_BOOTING
        3		ROBOT_MODE_POWER_OFF
        4		ROBOT_MODE_POWER_ON
        5		ROBOT_MODE_IDLE
        6		ROBOT_MODE_BACKDRIVE
        7		ROBOT_MODE_RUNNING
        8		ROBOT_MODE_UPDATING_FIRMWARE
        */
        final int value = this.decodeUnsignedCharIntValue();
        switch (value) {
            case 0:
                return RobotMode.Disconnected;
            case 1:
                return RobotMode.ConfirmSafety;
            case 2:
                return RobotMode.Booting;
            case 3:
                return RobotMode.PowerOff;
            case 4:
                return RobotMode.PowerOn;
            case 5:
                return RobotMode.Idle;
            case 6:
                return RobotMode.BackDrive;
            case 7:
                return RobotMode.Running;
            case 8:
                return RobotMode.UpdatingFirmware;
            default:
                throw new DecodeException("invalid robot mode value: " + value);
        }
    }

    /**
     * Decode next byte as control mode
     * @return control mode
     */
    public ControlMode decodeControlMode() throws DecodeException {
        /*
            0		CONTROL_MODE_POSITION
            1		CONTROL_MODE_TEACH
            2		CONTROL_MODE_FORCE
            3		CONTROL_MODE_TORQUE
        */
        final int value = this.decodeUnsignedCharIntValue();
        switch (value) {
            case 0:
                return ControlMode.Position;
            case 1:
                return ControlMode.Teach;
            case 2:
                return ControlMode.Force;
            case 3:
                return ControlMode.Torque;
            default:
                throw new DecodeException("invalid control mode value: " + value);
        }
    }

}
