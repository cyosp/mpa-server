package com.cyosp.mpa.api.rest.homebank.v1dot2.model;

import com.cyosp.mpa.api.rest.common.exception.DuplicatedNameException;
import com.cyosp.mpa.api.rest.common.exception.VersionNotSupportedException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by CYOSP on 2017-07-23.
 */
@Getter
@Setter
@XStreamAlias("homebank")
public class HomeBank {

    @XStreamAlias("v")
    @XStreamAsAttribute
    private String v;

    @XStreamAlias("d")
    @XStreamAsAttribute
    private String d;

    @XStreamAlias("properties")
    private Properties properties;

    @XStreamImplicit(itemFieldName = "cur")
    private List<Currency> currencies = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "account")
    private List<Account> accounts = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "pay")
    private List<Payee> payees = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "cat")
    private List<Category> categories = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "tag")
    private List<Tag> tags = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "fav")
    private List<Favorite> favorites = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "ope")
    private List<Operation> operations = new ArrayList<>();

    //---------------------------------------------------------

    public void checkVersion() throws VersionNotSupportedException {
        if (!"1.2".equals(getV())) throw new VersionNotSupportedException();
    }
}
