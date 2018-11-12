package vn.elca.training.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vn.elca.training.ApplicationLauncher;

/**
 * @author lul
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationLauncher.class)

public class IProjectRepositoryTest {
//    @Autowired
//    private ProjectService projectService;
    @PersistenceContext
    private EntityManager em;


   
}
