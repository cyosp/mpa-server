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

    @XStreamOmitField
    private Integer key;

    //-----------------------------

    @XStreamAsAttribute
    @XStreamAlias("date")
    private Long date;

    @XStreamAsAttribute
    @XStreamAlias("amount")
    private BigDecimal amount;

    @XStreamAsAttribute
    @XStreamAlias("account")
    private Integer account;

    @XStreamAsAttribute
    @XStreamAlias("paymode")
    private Integer paymode;

    @XStreamAsAttribute
    @XStreamAlias("flags")
    private Integer flags;

    @XStreamAsAttribute
    @XStreamAlias("payee")
    private Integer payee;

    @XStreamAsAttribute
    @XStreamAlias("category")
    private Integer category;

    @XStreamAsAttribute
    @XStreamAlias("wording")
    private String wording;

    //--------------------------

    @XStreamOmitField
    private Date javaDate;

    @XStreamOmitField
    private String payeeName;

    @XStreamOmitField
    private String categoryName;

    @XStreamOmitField
    private Currency currency;

    @XStreamOmitField
    private BigDecimal balance;

    public void convertDateToJulian() {

        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTime(getJavaDate());

        Calendar calendar = new GregorianCalendar(1, GregorianCalendar.JANUARY, 1);
        long diffInMs = gregorianCalendar.getTimeInMillis() - calendar.getTimeInMillis();

        setDate(diffInMs / (24 * 60 * 60 * 1000) - 1);
    }

    public void convertJulianToDate() {

        long diffInMs = getDate() * (24 * 60 * 60 * 1000);

        GregorianCalendar gregorianCalendar = new GregorianCalendar(1, GregorianCalendar.JANUARY, 1);
        long dateInMs = diffInMs + gregorianCalendar.getTimeInMillis();
        int dayNbr = (int) (dateInMs / (24 * 60 * 60 * 1000));

        GregorianCalendar newGregorianCalendar = new GregorianCalendar();
        newGregorianCalendar.setTimeInMillis(0);
        newGregorianCalendar.add(GregorianCalendar.DAY_OF_MONTH, dayNbr + 2);

        setJavaDate(newGregorianCalendar.getTime());
    }
}
