package org.launchcode.models.data;

import org.launchcode.models.Menu;
import org.springframework.data.repository.CrudRepository;

//enable store and retrieve instances of the menu class
public interface MenuDao extends CrudRepository<Menu, Integer> {
}
