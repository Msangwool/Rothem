package org.haram.rothem.util;

import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@UtilityClass
public class ReservationUtil {

    Random random = new Random();

    public String makeReservationCode() {
        String datePart = new SimpleDateFormat("yyyyMMdd").format(new Date());

        return datePart + (10000000L + random.nextLong(90000000L));
    }

}
