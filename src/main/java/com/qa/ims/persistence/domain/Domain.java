package com.qa.ims.persistence.domain;

import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public enum Domain {

    CUSTOMER("Information about customers"), ITEM("Individual Items"), ORDER("Purchases of items"),
    STOP("To close the application");

    public static final Logger LOGGER = LogManager.getLogger();

    private final String description;

    Domain(String description) {
        this.description = description;
    }

    public static void printDomains() {
        for (Domain domain : Domain.values()) {
            LOGGER.info(domain.getDescription());
        }
    }

    public static Domain getDomain(Utils utils) {
        Domain domain;
        while (true) {
            try {
                domain = Domain.valueOf(utils.getString().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                LOGGER.error("Invalid selection please try again");
            }
        }
        return domain;
    }

    public String getDescription() {
        return this.name() + ": " + this.description;
    }

}
