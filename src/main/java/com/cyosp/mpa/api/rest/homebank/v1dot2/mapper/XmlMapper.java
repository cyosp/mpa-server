package com.cyosp.mpa.api.rest.homebank.v1dot2.mapper;

import com.cyosp.mpa.api.rest.common.exception.DataNotSavedException;
import com.cyosp.mpa.api.rest.common.exception.DuplicatedNameException;
import com.cyosp.mpa.api.rest.homebank.v1dot2.model.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * Created by CYOSP on 2017-07-14.
 */
@Configuration
@Getter
public class XmlMapper {

    @Value("${repository.homebank.v1dot2.file}")
    private File homebankFilePath;

    @Getter
    private static HomeBank homeBank = null;

    @Getter
    private static XStream xstream;

    @Autowired
    DbMapper dbMapper;

    static {

        // Allow to manage attributes with _ in their names
        xstream = new XStream(new StaxDriver(new NoNameCoder()));

        // Allow to:
        // * ignore XML elements/attributes not mapped
        // * manage attributes with _ in it's name
        /*xstream = new XStream(new StaxDriver(new NoNameCoder())) {
            @Override
            protected MapperWrapper wrapMapper(MapperWrapper next) {
                return new MapperWrapper(next) {
                    @Override
                    public boolean shouldSerializeMember(Class definedIn,
                                                         String fieldName) {
                        if (definedIn == Object.class) {
                            return false;
                        }
                        return super.shouldSerializeMember(definedIn, fieldName);
                    }
                };
            }
        };*/

        // XStream must be using different class loader
        // Solution: set same class loader than current thread
        // It allows to recognize object in loading
        getXstream().setClassLoader(Thread.currentThread().getContextClassLoader());

        getXstream().processAnnotations(HomeBank.class);
    }

    @PostConstruct
    public void loadXmlFile() {
        try {
            homeBank = (HomeBank) getXstream().fromXML(getHomebankFilePath());
            getHomeBank().checkVersion();
            getHomeBank().addMissingValues();

            getDbMapper().init();

            // Add default
            Category category = new Category();
            category.setKey(0);
            category.setName("__##MPA##__##DEFAUT##__CATEGORY");
            getDbMapper().addCategory(category);
            Payee payee = new Payee();
            payee.setKey(0);
            payee.setName("__##MPA##__##DEFAUT##__CATEGORY");
            getDbMapper().addPayee(payee);

            getDbMapper().addHomebank(getHomeBank());
            getDbMapper().addCurrencies(getHomeBank().getCurrencies());
            getDbMapper().addProperties(getHomeBank().getProperties());
            getDbMapper().addAccounts(getHomeBank().getAccounts());
            getDbMapper().addCategories(getHomeBank().getCategories());
            getDbMapper().addPayees(getHomeBank().getPayees());
            getDbMapper().addFavorites(getHomeBank().getFavorites());
            getDbMapper().addTags(getHomeBank().getTags());
            getDbMapper().addOperations(getHomeBank().getOperations());

            // FOR DEBUG
            /*String xmlContent = getXstream().toXML(getHomeBank());
            xmlContent = xmlContent.replaceAll("></(properties|cur|account|pay|cat|tag|fav|ope)>", "/>");
            String xmlContentIndent = xmlContent.replaceAll("><", ">\n<");
            System.out.println(xmlContentIndent);*/

        } catch (Exception e) {
            // MPA must not manage HomeBank 1.2
            homeBank = null;
        }
    }

    public void saveXmlFile() throws DataNotSavedException {

        try {
            FileOutputStream outputStream = new FileOutputStream(getHomebankFilePath());
            // TODO remove default values like default category and payee
            getXstream().toXML(getHomeBank(), outputStream);
        } catch (Exception e1) {
            throw new DataNotSavedException();
        }

    }

    public HomeBank getInfos() {
        return getDbMapper().getHomebank();
    }

    public Properties getProperties() {
        return getDbMapper().getProperties();
    }

    //
    // Accounts
    //

    public List<Account> getAccounts() {
        return getDbMapper().getAccounts();
    }

    public int addAccount(Account account) throws DuplicatedNameException, DataNotSavedException {
        int ret = 0;

        getHomeBank().addAccount(account);
        saveXmlFile();

        ret++;

        return ret;
    }

    //
    // Categories
    //

    public List<Category> getCategories() {
        return getDbMapper().getCategories();
    }

    //
    // Currencies
    //

    public List<Currency> getCurrencies() {
        return getDbMapper().getCurrencies();
    }

    //
    // Favorites
    //

    public List<Favorite> getFavorites() {
        return getDbMapper().getFavorites();
    }

    //
    // Operations
    //

    public List<Operation> getOperations() {
        return getDbMapper().getOperations();
    }

    //
    // Payees
    //

    public List<Payee> getPayees() {
        return getDbMapper().getPayees();
    }

    //
    // Tags
    //

    public List<Tag> getTags() {
        return getDbMapper().getTags();
    }
}
