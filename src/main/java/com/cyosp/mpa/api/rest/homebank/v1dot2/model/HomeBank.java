package com.cyosp.mpa.api.rest.homebank.v1dot2.model;

import com.cyosp.mpa.api.rest.common.exception.VersionNotSupportedException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.Getter;
import lombok.Setter;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by CYOSP on 2017-07-23.
 */
@Getter
@Setter
@XStreamAlias("homebank")
public class HomeBank {

    private static final int BIGDECIMAL_SCALE = 2;
    private static final RoundingMode BIGDECIMAL_ROUNDINGMODE = RoundingMode.HALF_UP;

    @XStreamAlias("v")
    @XStreamAsAttribute
    private String v;

    @XStreamAlias("d")
    @XStreamAsAttribute
    private String d;

    @XStreamAlias("mpa")
    private String mpa;

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

    public void formatInputData() {
        setAccounts(getAccounts().stream().map(HomeBank::inputFormatter).collect(Collectors.toList()));
        setOperations(getOperations().stream().map(HomeBank::inputFormatter).collect(Collectors.toList()));
    }

    public void formatOutputData() {
        setAccounts(getAccounts().stream().map(HomeBank::outputFormatter).collect(Collectors.toList()));
        setOperations(getOperations().stream().map(HomeBank::outputFormatter).collect(Collectors.toList()));
    }

    //---------------------------------------------------------

    private static Account inputFormatter(Account account) {

        account.setInitial(account.getInitial().setScale(BIGDECIMAL_SCALE, BIGDECIMAL_ROUNDINGMODE));
        account.setMinimum(account.getMinimum().setScale(BIGDECIMAL_SCALE, BIGDECIMAL_ROUNDINGMODE));

        return account;
    }

    private static Operation inputFormatter(Operation operation) {

        operation.setAmount(operation.getAmount().setScale(BIGDECIMAL_SCALE, BIGDECIMAL_ROUNDINGMODE));

        return operation;
    }

    private static Account outputFormatter(Account account) {

        account.setInitial(account.getInitial().setScale(BIGDECIMAL_SCALE));
        account.setMinimum(account.getMinimum().setScale(BIGDECIMAL_SCALE));

        return account;
    }

    private static Operation outputFormatter(Operation operation) {

        operation.setAmount(operation.getAmount().setScale(BIGDECIMAL_SCALE));

        return operation;
    }
}
