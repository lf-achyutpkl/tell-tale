package com.lftechnology.tell.tale;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author Achyut Pokhrel <achyutpokhrel@lftechnology.com>
 *
 */
public class Resources {

    @Produces
    @PersistenceContext
    private EntityManager em;

}
