package org.launchcode.models.data;

import org.launchcode.models.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

//enable store and retrieve instances of the menu class
//primary key is an Integer
@Repository
@Transactional
public interface MenuDao extends CrudRepository<Menu, Integer> {
}
