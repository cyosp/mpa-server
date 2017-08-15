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
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by CYOSP on 2017-07-14.
 */
@Configuration
@Getter
public class XmlMapper {

    private static final int DEFAULT_KEY = 0;
    private static final int DEFAULT_CURRENCY_KEY = 1;
    private static final String DEFAULT_NAME = "";

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

            try {
                getDbMapper().init();

                // Add default
                Category category = new Category();
                category.setKey(DEFAULT_KEY);
                category.setName(DEFAULT_NAME);
                getDbMapper().addCategory(category);
                Payee payee = new Payee();
                payee.setKey(DEFAULT_KEY);
                payee.setName(DEFAULT_NAME);
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
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            // MPA must not manage HomeBank 1.2
            homeBank = null;
        }
    }

    public void saveXmlFile() throws DataNotSavedException {

        try {
            homeBank = getDbMapper().getHomebank();
            getHomeBank().setProperties(getDbMapper().getProperties());
            getHomeBank().setCurrencies(getDbMapper().getCurrencies());
            getHomeBank().setAccounts(getDbMapper().getAccounts());

            List<Payee> payees = getDbMapper().getPayees();
            payees.remove(DEFAULT_KEY);
            getHomeBank().setPayees(payees);

            List<Category> categories = getDbMapper().getCategories();
            categories.remove(DEFAULT_KEY);
            getHomeBank().setCategories(categories);

            getHomeBank().setTags(getDbMapper().getTags());
            getHomeBank().setFavorites(getDbMapper().getFavorites());
            getHomeBank().setOperations(getDbMapper().getOperations());


            String xmlContent = getXstream().toXML(getHomeBank());
            // Format content like HomeBank
            xmlContent = xmlContent.replaceAll("></(properties|cur|account|pay|cat|tag|fav|ope)>", "/>");
            String xmlContentIndent = xmlContent.replaceAll("><", ">\n<");

            Path path = Paths.get(getHomebankFilePath().toURI());
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write(xmlContentIndent);
            }
        } catch (Exception e) {
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

    @Transactional(value = HomebankDatasourceConfig.TX_MANAGER)
    public List<Operation> getOperationsByAccount(int id) {
        getDbMapper().setBalanceVariable(id);
        return getDbMapper().getOperationsByAccount(id);
    }

    public List<Category> getCategoriesByAccount(int id) {
        return getDbMapper().getCategoriesByAccount(id);
    }

    public int addAccount(Account account) throws DuplicatedNameException, DataNotSavedException {
        int ret = -1;

        // Set currency to default if needed
        if (account.getCurr() == DEFAULT_KEY) account.setCurr(DEFAULT_CURRENCY_KEY);

        try {
            ret = getDbMapper().addAccount(account);
            saveXmlFile();
        } catch (DuplicateKeyException dke) {
            throw new DuplicatedNameException();
        } catch (Exception e) {
            throw new DataNotSavedException();
        }

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
