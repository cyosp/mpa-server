package com.cyosp.mpa.api.rest.homebank.v1dot2.model;

import com.cyosp.mpa.api.rest.common.exception.DuplicatedNameException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CYOSP on 2017-07-23.
 */
@Getter
@Setter
@XStreamAlias("homebank")
public class HomeBank {

    @XStreamOmitField
    private int nextAccountKey = -1;

    @XStreamAlias("v")
    @XStreamAsAttribute
    private String version = "1.2";

    @XStreamAlias("d")
    @XStreamAsAttribute
    private String d;

    @XStreamAlias("properties")
    private Properties properties;

    @XStreamAlias("cur")
    private Currency currency;

    @XStreamImplicit(itemFieldName = "account")
    private List<Account> accounts = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "pay")
    private List<Payee> payees = new ArrayList<>();

    @XStreamImplicit(itemFieldName = "cat")
    private List<Category> categories = new ArrayList<>();


    public void initKeys() {
        for (Account account : getAccounts()) {
            if (account.getKey() > nextAccountKey) setNextAccountKey(account.getKey());
        }
    }

    public int getNextAccountKey() {
        nextAccountKey++;
        return nextAccountKey;
    }

    public void addAccount(Account account) throws DuplicatedNameException {
        for (Account accountFromList : getAccounts()) {
            if (accountFromList.getName().equals(account.getName())) throw new DuplicatedNameException();
        }

        account.setKey(getNextAccountKey());

        getAccounts().add(account);
    }
}
