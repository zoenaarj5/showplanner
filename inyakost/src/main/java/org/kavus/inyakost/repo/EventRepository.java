package org.kavus.inyakost.repo;

import org.kavus.inyakost.ntt.Event;
import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event,Long> {
}
