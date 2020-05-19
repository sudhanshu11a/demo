/**
 * 
 */
package org.sudhanshu.demo.organization.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.sudhanshu.demo.organization.entity.Organization;


/**
 * 
 * 
 * @author Sudhanshu Sharma
 *
 */
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

	Optional<Organization> findByUuid(UUID uuid);
}
