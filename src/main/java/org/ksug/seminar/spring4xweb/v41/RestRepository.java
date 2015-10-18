package org.ksug.seminar.spring4xweb.v41;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestRepository extends JpaRepository<Rest, Long> {

}
