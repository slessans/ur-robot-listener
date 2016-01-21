package com.scottlessans.urcontroller;


import com.scottlessans.urcontroller.decoders.DecodeException;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws DecodeException, IOException {
        // this is the data from example, except modified as some packages were missing fields that were
        // specified in the spec
        final byte[] bytes = new byte[]{
                // message length, original is 209, added 9 (see package comments below)
                (byte) 0, (byte) 0, (byte) 1, (byte) 218,

                // robot state
                (byte) 16,

                // first package length (original is 29, added 9: 1 for control mode, 8 for scaling)
                (byte) 0, (byte) 0, (byte) 0, (byte) 38,

                (byte) 0, (byte) 0,
                (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 5, (byte) 7, (byte) 246, (byte) 1, (byte) 1, (byte) 1, (byte) 0,
                (byte) 0, (byte) 0, (byte) 0,

                (byte) 0, // robot mode
                (byte) 0, // control mode (added)

                // speed fraction
                (byte) 63, (byte) 240, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,

                // speed scaling (added)
                (byte) 63, (byte) 240, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,


                (byte) 0, (byte) 0, (byte) 0, (byte) 251, (byte) 1, (byte) 64, (byte) 1, (byte) 78, (byte) 244,
                (byte) 77, (byte) 189, (byte) 249, (byte) 149, (byte) 64, (byte) 1, (byte) 78, (byte) 247, (byte) 89, (byte) 95,
                (byte) 104, (byte) 85, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 188,
                (byte) 220, (byte) 97, (byte) 3, (byte) 66, (byte) 62, (byte) 0, (byte) 0, (byte) 66, (byte) 0, (byte) 102,
                (byte) 103, (byte) 66, (byte) 99, (byte) 153, (byte) 154, (byte) 253, (byte) 191, (byte) 246, (byte) 74,
                (byte) 170, (byte) 216, (byte) 242, (byte) 29, (byte) 102, (byte) 191, (byte) 246, (byte) 74, (byte) 178,
                (byte) 44, (byte) 92, (byte) 72, (byte) 137, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
                (byte) 0, (byte) 0, (byte) 192, (byte) 1, (byte) 106, (byte) 78, (byte) 66, (byte) 63, (byte) 153, (byte) 154,
                (byte) 66, (byte) 4, (byte) 204, (byte) 205, (byte) 66, (byte) 104, (byte) 0, (byte) 0, (byte) 253, (byte) 63,
                (byte) 253, (byte) 49, (byte) 202, (byte) 91, (byte) 202, (byte) 8, (byte) 64, (byte) 63, (byte) 253, (byte) 49,
                (byte) 210, (byte) 233, (byte) 51, (byte) 16, (byte) 35, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
                (byte) 0, (byte) 0, (byte) 0, (byte) 191, (byte) 155, (byte) 135, (byte) 34, (byte) 66, (byte) 62, (byte) 0,
                (byte) 0, (byte) 65, (byte) 249, (byte) 153, (byte) 154, (byte) 66, (byte) 100, (byte) 0, (byte) 0, (byte) 253,
                (byte) 191, (byte) 220, (byte) 115, (byte) 204, (byte) 104, (byte) 205, (byte) 239, (byte) 254, (byte) 191,
                (byte) 220, (byte) 118, (byte) 68, (byte) 109, (byte) 49, (byte) 34, (byte) 158, (byte) 0, (byte) 0, (byte) 0,
                (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 190, (byte) 60, (byte) 245, (byte) 109, (byte) 66,
                (byte) 63, (byte) 153, (byte) 154, (byte) 66, (byte) 25, (byte) 51, (byte) 51, (byte) 66, (byte) 116, (byte) 204,
                (byte) 205, (byte) 253, (byte) 63, (byte) 242, (byte) 146, (byte) 224, (byte) 105, (byte) 231, (byte) 66,
                (byte) 209, (byte) 63, (byte) 242, (byte) 146, (byte) 65, (byte) 3, (byte) 193, (byte) 196, (byte) 156, (byte) 0,
                (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 190, (byte) 115, (byte) 157,
                (byte) 190, (byte) 66, (byte) 62, (byte) 0, (byte) 0, (byte) 66, (byte) 21, (byte) 51, (byte) 51, (byte) 66,
                (byte) 119, (byte) 153, (byte) 154, (byte) 253, (byte) 191, (byte) 231, (byte) 207, (byte) 8, (byte) 215,
                (byte) 85, (byte) 22, (byte) 88, (byte) 191, (byte) 231, (byte) 206, (byte) 77, (byte) 130, (byte) 151,
                (byte) 190, (byte) 17, (byte) 191, (byte) 146, (byte) 242, (byte) 158, (byte) 148, (byte) 114, (byte) 240,
                (byte) 57, (byte) 188, (byte) 224, (byte) 224, (byte) 96, (byte) 66, (byte) 68, (byte) 102, (byte) 103,
                (byte) 66, (byte) 37, (byte) 153, (byte) 154, (byte) 66, (byte) 127, (byte) 153, (byte) 154, (byte) 253,
                (byte) 0, (byte) 0, (byte) 0, (byte) 53, (byte) 4, (byte) 63, (byte) 217, (byte) 153, (byte) 52, (byte) 224,
                (byte) 36, (byte) 238, (byte) 93, (byte) 191, (byte) 217, (byte) 153, (byte) 169, (byte) 67, (byte) 241,
                (byte) 211, (byte) 23, (byte) 63, (byte) 207, (byte) 255, (byte) 137, (byte) 8, (byte) 22, (byte) 253, (byte) 198,
                (byte) 63, (byte) 240, (byte) 0, (byte) 170, (byte) 111, (byte) 207, (byte) 54, (byte) 176, (byte) 63, (byte) 243,
                (byte) 51, (byte) 88, (byte) 137, (byte) 58, (byte) 151, (byte) 217, (byte) 63, (byte) 201, (byte) 148,
                (byte) 119, (byte) 151, (byte) 70, (byte) 237, (byte) 237, (byte) 0, (byte) 0, (byte) 0, (byte) 29, (byte) 5,
                (byte) 64, (byte) 143, (byte) 64, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 64, (byte) 143,
                (byte) 64, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 64, (byte) 143, (byte) 64, (byte) 0, (byte) 0,
                (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 61, (byte) 3, (byte) 0, (byte) 63, (byte) 0,
                (byte) 0, (byte) 0, (byte) 0, (byte) 63, (byte) 123, (byte) 129, (byte) 184, (byte) 27, (byte) 129, (byte) 184,
                (byte) 28, (byte) 63, (byte) 112, (byte) 225, (byte) 14, (byte) 16, (byte) 225, (byte) 14, (byte) 17, (byte) 0,
                (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0,
                (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 66, (byte) 87, (byte) 51, (byte) 51, (byte) 66,
                (byte) 66, (byte) 0, (byte) 0, (byte) 62, (byte) 35, (byte) 215, (byte) 11, (byte) 61, (byte) 241, (byte) 169,
                (byte) 253, (byte) 0, (byte) 0, (byte) 0, (byte) 37, (byte) 2, (byte) 0, (byte) 0, (byte) 63, (byte) 141,
                (byte) 83, (byte) 47, (byte) 180, (byte) 171, (byte) 196, (byte) 232, (byte) 63, (byte) 137, (byte) 46, (byte) 99,
                (byte) 102, (byte) 69, (byte) 149, (byte) 155, (byte) 66, (byte) 55, (byte) 51, (byte) 51, (byte) 0, (byte) 59,
                (byte) 68, (byte) 155, (byte) 166, (byte) 66, (byte) 92, (byte) 0, (byte) 0, (byte) 253, (byte) 0,
        };

        new Runner(new ByteArrayInputStream(bytes)).run();
    }

}
