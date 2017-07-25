package com.cyosp.mpa.api.rest.homebank.v1dot2.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by CYOSP on 2017-07-25.
 */
@Getter
@Setter
public class Operation {

    @XStreamAsAttribute
    @XStreamAlias("date")
    private long julianDate;

    @XStreamAsAttribute
    @XStreamAlias("amount")
    private BigDecimal amount;

    @XStreamAsAttribute
    @XStreamAlias("account")
    private int accountRef;

    @XStreamAsAttribute
    @XStreamAlias("paymode")
    private int paymentMode;

    @XStreamAsAttribute
    @XStreamAlias("flags")
    private int flags;

    @XStreamAsAttribute
    @XStreamAlias("payee")
    private int payeeRef;

    @XStreamAsAttribute
    @XStreamAlias("wording")
    private String wording;

    @XStreamAsAttribute
    @XStreamAlias("category")
    private int categoryRef;

    //--------------------------

    @XStreamOmitField
    private Date date;

    public void convertDateToJulian() {

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(getDate());

        Calendar calendar = new GregorianCalendar(1, GregorianCalendar.JANUARY, 1);
        long diffInMs = gregorianCalendar.getTimeInMillis() - calendar.getTimeInMillis();

        setJulianDate(diffInMs / (24 * 60 * 60 * 1000) - 1);
    }

    public void convertJulianToDate() {

        long diffInMs = getJulianDate() * (24 * 60 * 60 * 1000);

        GregorianCalendar gregorianCalendar = new GregorianCalendar(1, GregorianCalendar.JANUARY, 1);
        long dateInMs = diffInMs + gregorianCalendar.getTimeInMillis();
        int dayNbr = (int) (dateInMs / (24 * 60 * 60 * 1000));

        GregorianCalendar newGregorianCalendar = new GregorianCalendar();
        newGregorianCalendar.setTimeInMillis(0);
        newGregorianCalendar.add(GregorianCalendar.DAY_OF_MONTH, dayNbr + 2);

        setDate(newGregorianCalendar.getTime());
    }
}
