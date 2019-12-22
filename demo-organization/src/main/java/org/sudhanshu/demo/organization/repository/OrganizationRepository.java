/**
 * 
 */
package org.sudhanshu.demo.organization.repository;

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
public interface OrganizationRepository extends JpaRepository<Organization, UUID> {

}
