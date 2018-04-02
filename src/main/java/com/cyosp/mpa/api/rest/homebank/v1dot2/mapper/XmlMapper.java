package com.cyosp.mpa.api.rest.homebank.v1dot2.mapper;

import com.cyosp.mpa.api.rest.common.exception.DataNotSavedException;
import com.cyosp.mpa.api.rest.common.exception.DuplicatedNameException;
import com.cyosp.mpa.api.rest.homebank.v1dot2.model.*;
import com.cyosp.mpa.api.rest.homebank.v1dot2.repository.HomebankRepository;
import com.cyosp.mpa.api.rest.homebank.v1dot2.request.OperationRequest;
import com.cyosp.mpa.api.rest.homebank.v1dot2.response.OperationResponse;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
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
@Repository
@Primary
@Getter
public class XmlMapper implements HomebankRepository {

    private static final int DEFAULT_KEY = 0;
    private static final int DEFAULT_CURRENCY_KEY = 1;
    private static final String DEFAULT_NAME = "";

    @Value("${repository.homebank.v1dot2.file}")
    private File homebankFilePath;

    @Value("${mpa.version}")
    @Getter(AccessLevel.PRIVATE)
    private String mpaVersion;

    @Getter
    private static HomeBank homeBank = null;

    @Getter
    private static XStream xstream;

    final DbMapper dbMapper;

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

    @Autowired
    public XmlMapper(DbMapper dbMapper) {
        this.dbMapper = dbMapper;
    }

    @PostConstruct
    @Override
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
                getDbMapper().addCategoryWithKey(category);
                Payee payee = new Payee();
                payee.setKey(DEFAULT_KEY);
                payee.setName(DEFAULT_NAME);
                getDbMapper().addPayeeWithKey(payee);

                if (getHomeBank() != null)
                    getDbMapper().addHomebank(getHomeBank());
                if (getHomeBank().getCurrencies() != null)
                    getDbMapper().addCurrencies(getHomeBank().getCurrencies());
                if (getHomeBank().getProperties() != null)
                    getDbMapper().addProperties(getHomeBank().getProperties());
                if (getHomeBank().getAccounts() != null)
                    getDbMapper().addAccounts(getHomeBank().getAccounts());
                if (getHomeBank().getCategories() != null)
                    getDbMapper().addCategories(getHomeBank().getCategories());
                if (getHomeBank().getPayees() != null)
                    getDbMapper().addPayees(getHomeBank().getPayees());
                if (getHomeBank().getFavorites() != null)
                    getDbMapper().addFavorites(getHomeBank().getFavorites());
                if (getHomeBank().getTags() != null)
                    getDbMapper().addTags(getHomeBank().getTags());
                if (getHomeBank().getOperations() != null)
                    getDbMapper().addOperations(getHomeBank().getOperations());
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            // MPA must not manage HomeBank 1.2
            homeBank = null;
        }
    }

    public void saveXmlFile(Boolean useTemporaryFile) throws DataNotSavedException {

        try {
            homeBank = getDbMapper().getHomebank();

            // Specify file has not been saved by HomeBank
            getHomeBank().setMpa(getMpaVersion());

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

            getHomeBank().formatOutputData();

            String xmlContent = getXstream().toXML(getHomeBank());
            // Format content like HomeBank
            xmlContent = xmlContent.replaceAll(" \\?>", "?>");
            xmlContent = xmlContent.replaceAll("></(properties|cur|account|pay|cat|tag|fav|ope)>", " />");
            xmlContent = xmlContent.replaceAll("'", "&apos;");
            String xmlContentIndent = xmlContent.replaceAll("><", ">\n<");

            String outputFilePath = getHomebankFilePath().getAbsolutePath();
            if (useTemporaryFile) outputFilePath += ".mpa-server.tmp";

            File outputFile = new File(outputFilePath);
            Path path = Paths.get(outputFile.toURI());
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                writer.write(xmlContentIndent);
            }
        } catch (Exception e) {
            throw new DataNotSavedException();
        }
    }

    @Override
    public HomeBank getInfos() {
        return getDbMapper().getHomebank();
    }

    @Override
    public Properties getProperties() {
        return getDbMapper().getProperties();
    }

    //
    // Accounts
    //

    @Override
    public List<Account> getAccounts() {
        return getDbMapper().getAccounts();
    }

    @Override
    @Transactional(value = HomebankDatasourceConfig.TX_MANAGER)
    public List<Operation> getOperationsByAccount(int id) {
        getDbMapper().setBalanceVariable(id);
        return getDbMapper().getOperationsByAccount(id);
    }

    @Override
    public List<Category> getCategoriesByAccount(int id) {
        return getDbMapper().getCategoriesByAccount(id);
    }

    @Override
    public List<Payee> getPayeesByAccount(int id) {
        return getDbMapper().getPayeesByAccount(id);
    }

    @Override
    @Transactional(value = HomebankDatasourceConfig.TX_MANAGER)
    public OperationResponse addOperationByAccount(int id, OperationRequest operationRequest) throws DataNotSavedException {

        OperationResponse ret = new OperationResponse();

        try {

            Payee payee = getDbMapper().getPayeeByName(operationRequest.getPayee());
            if (payee == null) {
                payee = new Payee();
                payee.setName(operationRequest.getPayee());
                int lineInserted = getDbMapper().addPayee(payee);
                if (lineInserted < 1) System.err.println("Payee not inserted: " + operationRequest.getPayee());
            }

            Category category = getDbMapper().getCategoryByName(operationRequest.getCategory());
            if (category == null) {
                category = new Category();
                category.setName(operationRequest.getCategory());
                int lineInserted = getDbMapper().addCategory(category);
                if (lineInserted < 1) System.err.println("Category not inserted: " + operationRequest.getCategory());
            }

            Operation operation = new Operation();
            operation.setJavaDate(operationRequest.getDate());
            operation.setPaymode(operationRequest.getPaymode());
            operation.convertDateToJulian();
            operation.setPayee(payee.getKey());
            operation.setAccount(id);
            operation.setCategory(category.getKey());
            operation.setWording(operationRequest.getWording());
            operation.setAmount(operationRequest.getAmount());

            // TODO Next : remove hard coded value
            operation.setFlags(0);

            getDbMapper().addOperation(operation);

            saveXmlFile(true);
        } catch (Exception e) {
            throw new DataNotSavedException();
        }

        return ret;
    }

    @Override
    public int addAccount(Account account) throws DuplicatedNameException, DataNotSavedException {
        int ret = -1;

        // Set currency to default if needed
        if (account.getCurr() == DEFAULT_KEY) account.setCurr(DEFAULT_CURRENCY_KEY);

        try {
            ret = getDbMapper().addAccount(account);
            saveXmlFile(true);
        } catch (DuplicateKeyException dke) {
            throw new DuplicatedNameException();
        } catch (Exception e) {
            throw new DataNotSavedException();
        }

        return ret;
    }

    //
    // Currencies
    //

    @Override
    public List<Currency> getCurrencies() {
        return getDbMapper().getCurrencies();
    }

    //
    // Favorites
    //

    @Override
    public List<Favorite> getFavorites() {
        return getDbMapper().getFavorites();
    }

    //
    // Operations
    //

    @Override
    public List<Operation> getOperations() {
        return getDbMapper().getOperations();
    }

    //
    // Tags
    //

    @Override
    public List<Tag> getTags() {
        return getDbMapper().getTags();
    }
}
